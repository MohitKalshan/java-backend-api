package com.example.SpringDataJPADemo.controllers;

import com.example.SpringDataJPADemo.dto.CreateUserDto;
import com.example.SpringDataJPADemo.dto.UserDto;
import com.example.SpringDataJPADemo.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(createUserDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
    }
//    Pagination
    @GetMapping("/paginated")
    public ResponseEntity<List<UserDto>> getAllUsersPaginated(@RequestParam int page, @RequestParam int pageSize,
    @RequestParam(defaultValue = "asc") String direction, @RequestParam(defaultValue = "name") String sortBy){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsersPaginated(page,pageSize,direction,sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody CreateUserDto updateUserDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(updateUserDto, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> patchUser(@RequestBody CreateUserDto patchUserDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.patchUser(patchUserDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUSer( @PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
