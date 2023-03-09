package com.microservices.user.service;

import com.microservices.user.model.User;
import com.microservices.user.repostitory.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ServiceBD  implements BDService{

    private UserRepository bd;
    @Override
    public List<User> findAll() {
        return bd.findAll();
    }

    @Override
    public User findById(int id) {
        return bd.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return bd.save(user);
    }
}
