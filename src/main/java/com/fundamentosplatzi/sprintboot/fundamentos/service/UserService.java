package com.fundamentosplatzi.sprintboot.fundamentos.service;

import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import com.fundamentosplatzi.sprintboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<User> users) {
        users.stream()
                .peek(user -> LOG.info("Usuario insertado : " + user))
                .forEach(userRepository::save);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
