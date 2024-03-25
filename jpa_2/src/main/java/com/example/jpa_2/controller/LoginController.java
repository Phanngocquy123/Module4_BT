package com.example.jpa_2.controller;

import com.example.jpa_2.entity.UserEntity;
import com.example.jpa_2.entity.UserRequest;
import com.example.jpa_2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        @Valid UserRequest userReq, BindingResult bindingResult, HttpServletRequest request){
        if (email.isEmpty()) {
            bindingResult.addError(new FieldError("userReq", "email", "email không bỏ trống"));
        }
        if (password.isEmpty()) {
            bindingResult.addError(new FieldError("password", "error.user", "Password không được bỏ trống"));
        }
        if (bindingResult.hasErrors()){
            return "user/login";
        }

        UserEntity user = userService.findByEmailAndPassword(email, password);
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/admin/user/index";
        }

       return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/user/login";
    }
}
