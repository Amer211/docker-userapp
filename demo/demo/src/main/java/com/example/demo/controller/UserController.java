package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/",  "/home"})
    public String home(){
        logger.info("APi: home was requested.");
        return "Hello";
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user){
        logger.info("received user "+ user);
        userService.saveUser(user);
        logger.info("{} has been saved **Controller**", user.getUsername());
    }


    @GetMapping("/{id}")                    //=> next line to add Redis for caching ..
    @Cacheable(value = "users", key = "#id") // @cacheable package: org.springframework.
    public User getUserById(@PathVariable Long id){     // .cache.annotation.Cacheable;
     logger.info("fetching user with id :{}",id);
     return userService.getUserById(id);
    }



}
