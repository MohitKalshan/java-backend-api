package com.example.SpringDataJPADemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateUserDto {
    private String name;
    private String email;
}
