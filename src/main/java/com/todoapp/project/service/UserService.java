package com.todoapp.project.service;

import com.todoapp.project.entity.User;
import com.todoapp.project.repositories.UserRepository;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import java.util.ArrayList;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUser(User user) {
        System.out.println("Service GET *******");
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
    public boolean getUserByUsername(String username, String password) {
        boolean username_present;
        boolean password_present;

        try {
            username_present = userRepository.findTopByUsername(username) != null;
            System.out.println("Username present: " + username_present);
            password_present = userRepository.findTopByPassword(password) != null;
            System.out.println("Password present: " + password_present);
        } catch(NoUniqueBeanDefinitionException nre) {
            return true;
        }
        return username_present && password_present;
    }

    public boolean findUserByUsername(String username) {
        boolean username_present;
        try {
            username_present = userRepository.findTopByUsername(username) != null;
            System.out.println("Username present (U): " + username_present);
        } catch(NonUniqueResultException nre) {
            return true;
        }
        return username_present;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
