package com.app.course.service.order;

import com.app.course.models.Course;
import com.app.course.models.Order;
import com.app.course.models.User;
import com.app.course.repository.CourseRepository;
import com.app.course.repository.OrderRepository;
import com.app.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderServiceIpm implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Order insertOrder(Order order) {
        try {
            Order orderResult = orderRepository.save(order);
            return orderResult;
        } catch (Exception e) {
            return null;
        }
    }

//    @Override
//    public Order insertOrder(long Id, long userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//
//        User user = userOptional.get();
//
//        Order order = new Order();
//        order.setId(Id);
//        order.setOrderDate(LocalDateTime.now());
//        order.setUser(user);
//
//        re
//    }
}
