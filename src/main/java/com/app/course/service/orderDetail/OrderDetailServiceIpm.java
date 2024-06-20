package com.app.course.service.orderDetail;

import com.app.course.models.OrderDetail;
import com.app.course.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceIpm implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail insertOrderDetail(OrderDetail orderDetail) {
        try {
            OrderDetail orderDetailResult = orderDetailRepository.save(orderDetail);
            return orderDetailResult;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OrderDetail> getAllOrderDetailsByUserId(long userId) {
        var response = orderDetailRepository.findByUserIdForCourse(userId);
        return response;
    }
}
