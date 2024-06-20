package com.app.course.vnPay.controller;

import com.app.course.config.Constants;
import com.app.course.models.*;
import com.app.course.repository.BuyRepository;
import com.app.course.repository.CourseRepository;
import com.app.course.repository.UserRepository;
import com.app.course.service.order.OrderService;
import com.app.course.service.orderDetail.OrderDetailService;
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
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("vnpay")
public class PaymentController {
    @Autowired
    VnPayService vnPayService;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    BuyRepository buyRepository;

    @GetMapping("/createPayment")
    public ResponseEntity<?> createPaymentVnPay(@RequestParam(value = "user_id") long userId,
                                                @RequestParam(value = "course_id") long courseId,
                                                @RequestParam(value = "return_url", required = false) String returnUrl,
                                                HttpServletRequest req) throws UnsupportedEncodingException {

        String paymentUrl = vnPayService.createPayment(userId,courseId,returnUrl,req);

        VnPayResponse vnPayResponse = new VnPayResponse();
        vnPayResponse.setStatus("OK");
        vnPayResponse.setMessage("successfully");
        vnPayResponse.setData(paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body(vnPayResponse);
    }

    @GetMapping("/info")
    public ResponseEntity<?> transaction(@RequestParam(value = "vnp_Amount") long amount, @RequestParam(value = "vnp_BankCode") String bankCode,
                                         @RequestParam(value = "vnp_OrderInfo") String orderInfo, @RequestParam(value = "vnp_ResponseCode") String responseCode,
                                         @RequestParam(value = "vnp_TxnRef") long orderId, @RequestParam(value = "user_id") long userId,
                                         @RequestParam(value = "course_id") long courseId) {

        VnPayResponse vnPayResponse = new VnPayResponse();
        if (responseCode.equals("00")) {
            // cong tien cho admin
            User admin = userRepository.findByUsername("admin");
            admin.setWallet(admin.getWallet() + amount/100.0);
            userRepository.save(admin);


            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Course> courseOptional = courseRepository.findById(courseId);


            // user mua hang
            User user = userOptional.get();


            Course course = courseOptional.get();

            // them tien vao cho educator
            User educator = course.getUser();
            educator.setWallet(educator.getWallet()+amount/100.0);
            userRepository.save(educator);


            LocalDateTime now = LocalDateTime.now();
            Order order = new Order(orderId, user, now);
            var orderResult = orderService.insertOrder(order); // lưu vào cơ sở dữ liệu

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(orderResult);
            orderDetail.setCourse(course);
            orderDetail.setPrice(amount/100.0);
            var orderDetailResult = orderDetailService.insertOrderDetail(orderDetail);

            boolean buyExist = buyRepository.existsByCourse_IdAndUser_Id(courseId,userId);
            // buy chưa tồn tại
            if(!buyExist){
                Buy buy = new Buy();
                buy.setUser(user);
                buy.setCourse(course);
                buyRepository.save(buy);
            }

            vnPayResponse.setStatus("Ok");
            vnPayResponse.setMessage("successfully");
            vnPayResponse.setData("Thanh Toán thành công");

            return ResponseEntity.status(HttpStatus.OK).body(vnPayResponse);

        } else {
            vnPayResponse.setStatus("No");
            vnPayResponse.setMessage("failed");
            vnPayResponse.setData("Thanh toán thất bại");
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(vnPayResponse);
        }

    }
}
