package com.web.jpa.controller;

import com.web.jpa.entity.User;
import com.web.jpa.repository.UsersRepository;
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
@RequestMapping("/users")
public class LoginController {
    @Autowired
    private UsersRepository usersRepository;
    @GetMapping("/login")
    public String login(){
        return "users/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
                        @Valid User userReq, BindingResult bindingResult, HttpServletRequest request){
        if (email.isEmpty()) {
            bindingResult.addError(new FieldError("userReq", "email", "email không bỏ trống"));
        }
        if (password.isEmpty()) {
            bindingResult.rejectValue("password", "error.user", "Password không được bỏ trống");
        }
        if (bindingResult.hasErrors()){
            return "users/login";
        }

        User user = usersRepository.findByEmailAndPassword(email, password);
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/admin/users/index";
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
