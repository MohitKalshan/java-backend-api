package com.example.SpringDataJPADemo.dto;

import com.example.SpringDataJPADemo.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private String productName;
    private UserDto user;
}
