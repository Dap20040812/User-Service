package com.microservices.user.service;

import com.microservices.user.model.User;

import java.util.List;

public interface BDService {

    List<User> findAll();

    User findById(int id);

    User save(User user);

}
