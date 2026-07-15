package com.example.SpringDataJPADemo.repository;

import com.example.SpringDataJPADemo.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
    @Override
    Page<Users> findAll(Pageable pageable);
}

