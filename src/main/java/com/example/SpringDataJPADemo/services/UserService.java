package com.example.SpringDataJPADemo.services;

import com.example.SpringDataJPADemo.dto.CreateUserDto;
import com.example.SpringDataJPADemo.dto.UserDto;
import com.example.SpringDataJPADemo.entities.Users;
import com.example.SpringDataJPADemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDto saveUser(CreateUserDto createUserDto) {
        Users user = new Users();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        Users savedUser = userRepository.save(user);

        return new UserDto(savedUser.getId(),savedUser.getName(),savedUser.getEmail());
    }

    public List<UserDto> getAllUsers() {
        List<Users> users = new ArrayList<>();
        users =  userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(Users user: users) {
            UserDto userDto =   new UserDto(user.getId(),user.getName(),user.getEmail());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public UserDto getUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        return new UserDto(user.getId(),user.getName(),user.getEmail());
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transient
    public UserDto updateUser(CreateUserDto updateUserDto, Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
//        no save()
        return new UserDto(user.getId(),user.getName(),user.getEmail());
    }

    public UserDto patchUser(CreateUserDto patchUserDto, Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        if(patchUserDto.getName() != null) user.setName(patchUserDto.getName());
        if(patchUserDto.getEmail() != null) user.setName(patchUserDto.getEmail());

        return new UserDto(user.getId(),user.getName(),user.getEmail());
    }

    public List<UserDto> getAllUsersPaginated(int page, int pageSize, String direction, String sortBy) {
        Sort sort;
        sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        Page<Users> usersPage = userRepository.findAll(pageable);
        List<UserDto> userDtoList = new ArrayList<>();
        usersPage.forEach(user -> userDtoList.add(new UserDto(user.getId(), user.getName(), user.getEmail())));
        return userDtoList;
    }
}
