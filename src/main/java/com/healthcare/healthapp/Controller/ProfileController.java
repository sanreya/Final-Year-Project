package com.healthcare.healthapp.Controller;

import com.healthcare.healthapp.Entity.User;
import com.healthcare.healthapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    /*@GetMapping
    public User profile(){
        return userService.fetchUser();
    }*/
}
