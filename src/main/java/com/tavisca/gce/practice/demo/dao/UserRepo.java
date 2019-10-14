package com.tavisca.gce.practice.demo.dao;

import com.tavisca.gce.practice.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByName(String name);
}
