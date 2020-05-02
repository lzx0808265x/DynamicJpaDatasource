package com.example.repository;

import com.example.annotation.DataSource;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @DataSource("slave")
    List<User> findAll();
}
