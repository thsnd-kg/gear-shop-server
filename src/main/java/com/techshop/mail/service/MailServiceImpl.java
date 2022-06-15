package com.techshop.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String from;

    @Value("${client.domain}")
    private String domain;

    @Override
    public void sendVerifyMail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Xác thực Mail");
        String text = String.format("Wellcome to Gear Shop\n" +
                "Follow this link to confirm your email address, this link is only valid in 1 day: %s/xac-thuc/xac-nhan-email?token=%s.", domain,token);
        message.setText(text);
        emailSender.send(message);

    }
}
