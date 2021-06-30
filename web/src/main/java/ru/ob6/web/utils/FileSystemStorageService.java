package ru.ob6.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService {

	@Value("${upload.name}")
	private String rootLocation;

	public String store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new IllegalArgumentException("Failed to store empty file.");
			}
			Path path = Paths.get(rootLocation);
			UUID name = UUID.randomUUID();
			String filename =  file.getOriginalFilename() + name;
			Path destinationFile = path.resolve(
					Paths.get(file.getOriginalFilename() + name))
					.normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(path.toAbsolutePath())) {
				// This is a security check
				throw new IllegalArgumentException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
			return filename;
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Failed to store file.", e);
		}
	}

	public Path load(String filename) {
		Path path = Paths.get(rootLocation);
		return path.resolve(filename);
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new IllegalArgumentException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new IllegalArgumentException("Could not read file: " + filename, e);
		}
	}

	public void init() {
		try {
			Path path = Paths.get(rootLocation);
			Files.createDirectories(path);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Could not initialize storage", e);
		}
	}
}
