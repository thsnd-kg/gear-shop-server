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
        String text = String.format("Chào mừng đến với Gear Shop\n" +
                "Nhấn vào đường link để xác thực tài khoản của bạn, link sẽ hết hạn sau 1 ngày: %s/xac-thuc/xac-nhan-email?token=%s.", domain,token);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendVerifyResetPassword(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Quên mật khẩu");
        String text = String.format("Chào mừng đến với Gear Shop\n" +
                "Nhấn vào đường link để đặt lại mật khẩu của bạn, link sẽ hết hạn sau 10 phút: %s/xac-thuc/quen-mat-khau?token=%s.", domain,token);

        message.setText(text);
        emailSender.send(message);
    }
}
