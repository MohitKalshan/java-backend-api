package com.example.SpringDataJPADemo.services;

import com.example.SpringDataJPADemo.dto.CreateOrderDto;
import com.example.SpringDataJPADemo.dto.OrderDto;
import com.example.SpringDataJPADemo.dto.UserDto;
import com.example.SpringDataJPADemo.entities.Orders;
import com.example.SpringDataJPADemo.entities.Users;
import com.example.SpringDataJPADemo.repository.OrderRepository;
import com.example.SpringDataJPADemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transient
    public OrderDto createOrder(Long userId, CreateOrderDto createOrderDto) {
       Users user = userRepository.findById(userId).orElseThrow();
        Orders order = new Orders();
        order.setUser(user);
        order.setProductName(createOrderDto.getProductName());

    Orders savedOrder = orderRepository.save(order);
        return new OrderDto(savedOrder.getId(),savedOrder.getProductName(),
                new UserDto(savedOrder.getUser().getId(),savedOrder.getUser().getName(),savedOrder.getUser().getEmail()));
}

    public List<OrderDto> getOrderByUserId(Long userId) {
        List<Orders> orders = orderRepository.findByUserId(userId);
        List<OrderDto> orderDtos = new ArrayList<>();

        orders.forEach(order -> {
            OrderDto orderDto = new OrderDto(
                    order.getId(),
                    order.getProductName(),
                    new UserDto(
                            order.getUser().getId(),
                            order.getUser().getName(),
                            order.getUser().getEmail()
                    )
            );

            orderDtos.add(orderDto);
        });

        return orderDtos;
    }
}
