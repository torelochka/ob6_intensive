package ru.ob6.web.controllers;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ob6.web.utils.FileSystemStorageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
public class ImgDistributionController {

    /*@Value("${upload.dir.image}")
    private String uploadDir;*/
    private final FileSystemStorageService storageService;

    public ImgDistributionController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

    @SneakyThrows
    @GetMapping(value = "/dist/img/{name}" ,produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String name) {
        Resource file = storageService.loadAsResource(name);

        InputStream in = file.getInputStream();

        byte[] byteImage = new byte[in.available()];
        in.read(byteImage);

        return byteImage;
    }
}
