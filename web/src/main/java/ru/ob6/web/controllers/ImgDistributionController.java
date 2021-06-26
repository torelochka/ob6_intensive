package ru.ob6.web.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
public class ImgDistributionController {

    @Value("${upload.dir.image}")
    private String uploadDir;

    @SneakyThrows
    @GetMapping(value = "/dist/img/{name}" ,produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String name) {
        InputStream in = new FileInputStream(uploadDir + File.separator + name);

        byte[] byteImage = new byte[in.available()];
        in.read(byteImage);

        return byteImage;
    }
}
