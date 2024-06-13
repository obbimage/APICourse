package com.app.course;

import com.app.course.service.email.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmailServicTest {
    @Autowired
    private EmailService emailService;
    @Test
    public void testSendEmail() {
       boolean result = emailService.sendEmail("sinhtien0125@gmail.com", "Test Subject", "Test Content");

        assertEquals(result, true);
    }
}
