package ru.ob6.web.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FileSaver {
    public static String save(MultipartFile file, String directory) {
        try {
            String file_name = UUID.randomUUID().toString() +
                    "-" +
                    file.getOriginalFilename();

            IOUtils.copyLarge(
                    file.getInputStream(),
                    new FileOutputStream(directory +
                            File.separator +
                            file_name
                    )
            );
            return file_name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
