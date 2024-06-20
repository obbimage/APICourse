package com.app.course.vnPay.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

public interface VnPayService {
    String createPayment(HttpServletRequest request) throws UnsupportedEncodingException;

    String createPayment(long userId, long courseId, String returnUrl,
                         HttpServletRequest request) throws UnsupportedEncodingException;
}
