package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        logger.info("saving new user :{}",user.getUsername());
        userRepository.save(user);
        logger.info("{} has been saved.Assigned Id :{}",user.getUsername(),user.getUserId());

    }

    @Override
    public User getUserById(Long id) {
        logger.info("fetching user with id: "+id);
        return userRepository.findById(id).
                orElseThrow(()->new RuntimeException("User Not found for id "+id)
                );
    }
}
