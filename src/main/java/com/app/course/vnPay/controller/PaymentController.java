package com.app.course.vnPay.controller;

import com.app.course.vnPay.DTO.VnPayResponse;
import com.app.course.vnPay.service.VnPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("vnpay")
public class PaymentController {
    @Autowired
    VnPayService vnPayService;

    @GetMapping("/createPayment")
    public ResponseEntity<?> createPaymentVnPay(HttpServletRequest req) throws UnsupportedEncodingException {

        String paymentUrl = vnPayService.createPayment(req);

        VnPayResponse vnPayResponse = new VnPayResponse();
        vnPayResponse.setStatus("OK");
        vnPayResponse.setMessage("successfully");
        vnPayResponse.setData(paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body(vnPayResponse);
    }

    @GetMapping("paymentVnPayInfo")
    public ResponseEntity<?> transaction(
            @RequestParam(value = "vnp_Amount") String amount,
            @RequestParam(value = "vnp_BankCode") String bankCode,
            @RequestParam(value = "vnp_OrderInfo") String order,
            @RequestParam(value = "vnp_ResponseCode") String responseCode
    ) {

        VnPayResponse vnPayResponse = new VnPayResponse();
        if (responseCode.equals("00")) {
            vnPayResponse.setStatus("OK");
            vnPayResponse.setMessage("successfully");
            vnPayResponse.setData("");
        } else {
            vnPayResponse.setStatus("No");
            vnPayResponse.setMessage("failed");
            vnPayResponse.setData("");
        }

        return ResponseEntity.status(HttpStatus.OK).body(vnPayResponse);
    }
}
