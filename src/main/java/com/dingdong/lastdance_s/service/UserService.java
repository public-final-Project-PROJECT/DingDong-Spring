package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.entity.User;
import com.dingdong.lastdance_s.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            userRepository.save(user);
        } else {
            System.out.println("User already exists");
        }
    }
}
