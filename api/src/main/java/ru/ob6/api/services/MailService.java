package ru.ob6.api.services;

public interface MailService {
    void sendEmailForConfirm(String email, String code);
    void sendEmailForResetPassword(String email, String code);
}
