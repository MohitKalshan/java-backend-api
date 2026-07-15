package com.example.SpringDataJPADemo.controllers;

import com.example.SpringDataJPADemo.dto.CreateOrderDto;
import com.example.SpringDataJPADemo.dto.OrderDto;
import com.example.SpringDataJPADemo.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users/{userId}/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId, @RequestBody CreateOrderDto createOrderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userId,createOrderDto));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderByUserId ( @PathVariable Long userId){
    return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByUserId(userId));
    }
}
