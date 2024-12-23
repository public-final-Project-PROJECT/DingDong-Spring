package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.entity.User;
import com.dingdong.lastdance_s.exceptions.UserNotFoundException;
import com.dingdong.lastdance_s.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<String> getSchoolNameByEmail(String email) {
        return userRepository.findSchoolNameByEmail(email);
    }

    public void saveOrUpdateUser(User user)
    {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent())
        {
            User updatedUser = existingUser.get();

            if (user.getName() != null)
            {
                updatedUser.setName(user.getName());
            }
            if (user.getPicture() != null)
            {
                updatedUser.setPicture(user.getPicture());
            }
            if ("RESET".equals(user.getSchoolName())) {
                updatedUser.setSchoolName(null);
            } else if (user.getSchoolName() != null) {
                updatedUser.setSchoolName(user.getSchoolName());
            }

            userRepository.save(updatedUser);
            return;
        }
        userRepository.save(user);
    }

    public void withdraw(String email)
    {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        userRepository.delete(user.get());
    }
}
