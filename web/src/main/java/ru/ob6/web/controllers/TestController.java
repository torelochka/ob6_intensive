package ru.ob6.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ob6.api.services.MailService;

@RestController
public class TestController {

    @Autowired
    private MailService mailService;

    @GetMapping("/test")
    public void test() {
        mailService.sendEmailForConfirm("test@gmail.com", "sadasdasd");
    }
}
