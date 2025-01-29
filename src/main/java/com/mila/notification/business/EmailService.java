package com.mila.notification.business;

import com.mila.notification.business.dto.TaskDTO;
import com.mila.notification.infrastructure.exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${sent.email.from}")
    public String from;

    @Value("${sent.email.fromName}")
    private String fromName;

    public void sendEmail(TaskDTO dto) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(new InternetAddress(from, fromName));
            mimeMessageHelper.setTo(InternetAddress.parse(dto.getUserEmail()));
            mimeMessageHelper.setSubject("Task Notification");

            Context context = new Context();
            context.setVariable("taskName", dto.getTaskName());
            context.setVariable("eventDate", dto.getEventDate());
            context.setVariable("description", dto.getDescription());
            String template = templateEngine.process("notification", context);
            mimeMessageHelper.setText(template, true);
            javaMailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Unable to send email ", e.getCause());
        }


    }

}
