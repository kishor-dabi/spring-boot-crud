package com.test.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.test.demo.model.User;

//@Repository
@Service
public interface UserRepository extends JpaRepository<User, Long> {

}
