package com.journal.app.models.services;

import com.journal.app.controllers.App;
import com.journal.app.controllers.AppController;
import com.journal.app.models.DTO.UserDTO;
import com.journal.app.models.domain.User;
import com.journal.app.models.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    //returns all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    //add user
    public void addUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);
    }

    //update user
    public void updateUser(User user) {
        userRepository.save(user);
    }

    //get user with id
    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    //delete user with id
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public static boolean isTableEmpty(Session session) {
        boolean b;
        Long total = (Long) session
                .createQuery("select count(c) from User c")
                .getSingleResult();
        b = total <= 0;
        return b;
    }

    public User findByUserAndPassword(String username, String password) {
//        try {
        return userRepository.findByUserAndPassword(username, password);
//        } catch (Exception e) {
//            logger.warn("no data");
//            return null;
//        }
    }
}

