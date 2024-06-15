package com.app.course.vnPay.service;

import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface VnPayService {
    String createPayment(HttpServletRequest request) throws UnsupportedEncodingException;
}
