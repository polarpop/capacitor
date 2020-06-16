package com.infosys.capacitor.controllers;

import com.infosys.capacitor.dao.User;

import com.infosys.capacitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public User createUser(@RequestBody User data) {
    userRepository.save(data);
    return data;
  }
}