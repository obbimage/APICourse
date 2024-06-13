package com.app.course.service.email;

public interface EmailService {
    boolean sendEmail(String to, String subject, String content);
}
