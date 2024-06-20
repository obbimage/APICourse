package com.app.course.service.orderDetail;

import com.app.course.models.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail insertOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> getAllOrderDetailsByUserId(long userId);
}
