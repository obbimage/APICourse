package com.app.course.controllers;

import com.app.course.repository.Response;
import com.app.course.service.orderDetail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/educator")
    public ResponseEntity<?> getAllByUserId(@RequestParam(value = "user_id") long userId) {
        var response = orderDetailService.getAllOrderDetailsByUserId(userId);
        return Response.resultOk(response);
    }
}
