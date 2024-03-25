package com.ra.springmvc2.controller;

import com.ra.springmvc2.model.entity.User;
import com.ra.springmvc2.service.UserService;
import org.graalvm.compiler.printer.BinaryGraphPrinter;
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


public class LoginController {
    private UserService userService;
    public LoginController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        @Valid User userReq, BindingResult bindingResult, HttpServletRequest request){
        if (email.isEmpty()) {
            bindingResult.addError(new FieldError("userReq", "email", "email không bỏ trống"));
        //    bindingResult.rejectValue("email", "error.user", "Email không được bỏ trống");
        }
        if (password.isEmpty()) {
            bindingResult.rejectValue("password", "error.user", "Password không được bỏ trống");
        }
        if (bindingResult.hasErrors()){
            return "users/login";
        }

        User user = userService.findSingle(x -> x.getEmail().equals(email) && x.getPassword().equals(password));
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/users/index";
        } else {
            bindingResult.rejectValue("email", "error.user", "Email hoặc mật khẩu không chính xác");
            return "users/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/users/login";
    }
}
