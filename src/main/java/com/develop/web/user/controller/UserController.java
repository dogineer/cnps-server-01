package com.develop.web.user.controller;

import com.develop.web.user.dto.UserDto;
import com.develop.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/new")
    public String signUpForm() {
        return "user/signUpForm";
    }

    @PostMapping("user/new")
    public String signUp(UserDto form) {
        UserDto userDto = new UserDto(
                form.getUserid(),
                form.getUserPassword()
        );

        userService.SignUp(userDto);

        return "redirect:/";
    }
}
