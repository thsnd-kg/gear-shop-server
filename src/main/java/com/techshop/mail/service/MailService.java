package com.techshop.mail.service;

public interface MailService {
    void sendVerifyMail(String email, String token);
}
