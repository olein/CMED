package com.cmed.controller;

import com.cmed.dto.LoginRequest;
import com.cmed.dto.LoginResponse;
import com.cmed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getMessage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginRequest loginRequest, Model model, HttpSession session) {

        LoginResponse loginResponse = userService.loginUser(loginRequest);

        if (loginResponse != null) {
            model.addAttribute("loginId", loginResponse.getLoginId());
            model.addAttribute("userId", loginResponse.getUserId());
            return "home";
        } else {
            model.addAttribute("message", "Invalid username/password");
            return "login";
        }
    }
}
