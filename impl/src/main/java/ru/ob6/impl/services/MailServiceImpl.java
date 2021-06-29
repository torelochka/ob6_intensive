package ru.ob6.impl.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import ru.ob6.api.services.MailService;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

@Component
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private final Template confirmMailTemplate;
    private final Template forgotPasswordTemplate;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            this.confirmMailTemplate = configuration.getTemplate("mail/confirm_mail.ftlh");
            this.forgotPasswordTemplate = configuration.getTemplate("mail/forgot_password.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmailForConfirm(String email, String code) {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("confirm_code", code);
        String mailText = getEmailText(attributes, EmailTypes.CONFIRM_EMAIL);
        MimeMessagePreparator messagePreparator = getEmail(email, mailText, "Регистрация");
        javaMailSender.send(messagePreparator);
    }

    @Override
    public void sendEmailForResetPassword(String email, String code) {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("password_code", code);
        String mailText = getEmailText(attributes, EmailTypes.FORGOT_PASSWORD);
        MimeMessagePreparator messagePreparator = getEmail(email, mailText, "Восстановление пароля");
        javaMailSender.send(messagePreparator);
    }

    private MimeMessagePreparator getEmail(String email, String mailText, String subject) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(mailFrom);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(mailText, true);
        };
    }

    private String getEmailText(HashMap<String, String > attributes, EmailTypes type) {
        StringWriter writer = new StringWriter();
        try {
            switch (type) {
                case CONFIRM_EMAIL:
                    confirmMailTemplate.process(attributes, writer);
                    break;
                case FORGOT_PASSWORD:
                    forgotPasswordTemplate.process(attributes, writer);
            }
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }
        return writer.toString();
    }

    private enum EmailTypes {
        CONFIRM_EMAIL,
        FORGOT_PASSWORD
    }
}
