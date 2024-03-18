package com.ra.springmvc2.controller;

import com.ra.springmvc2.model.UserRequest;
import com.ra.springmvc2.model.entity.User;
import com.ra.springmvc2.service.FileStorageService;
import com.ra.springmvc2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController {
    private UserService userService;
    private ModelMapper modelMapper;
    private FileStorageService storageService;

    public UsersController(UserService userService, ModelMapper modelMapper, FileStorageService storageService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }

    @GetMapping(value = {"", "/index"})
    public String ShowListUser(@RequestParam(name = "page", defaultValue = "1") int currentPage,
                               @RequestParam(name = "key", required = false) String key, Model model) {
        int pageSize = 2;

        List<User> userList = userService.findAll();
        if (key != null && !key.isEmpty()){
            userList = userList.stream()
                    .filter(u -> u.getEmail().contains(key.toLowerCase()) || u.getPhone().contains(key.toLowerCase()) || u.getAddress().contains(key.toLowerCase()))
                    .collect(Collectors.toList());
        }

        int totalPage = (int) Math.ceil((double) userList.size() / pageSize);
        userList = userList.stream().skip((long) (currentPage - 1) * pageSize).limit(pageSize).collect(Collectors.toList());


        model.addAttribute("userList", userList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("key", key);
        return "users/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new UserRequest());
        return "users/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") UserRequest user, BindingResult bindingResult, Model model) {
        if (userService.checkExist(u -> u.getEmail().equals(user.getEmail())))
            bindingResult.addError(new FieldError("user", "email", "Email đã tồn tại!"));
        if (userService.checkExist(u -> u.getPhone().equals(user.getPhone())))
            bindingResult.addError(new FieldError("user", "phone", "Số điện thoại đã tồn tại!"));
        if (user.getImage() == null || user.getImage().isEmpty())
            bindingResult.addError(new FieldError("user", "image", "Ảnh không được bỏ trống"));
        if (bindingResult.hasErrors())
            return "users/create";
        user.setAvatar(storageService.upload(user.getImage()));
        User entity = modelMapper.map(user, User.class);
        userService.add(entity);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        User userEntity = userService.findId(id);
        UserRequest user = modelMapper.map(userEntity, UserRequest.class);
        //UserRequest user = new UserRequest();
        //user.setAddress(userEntity.getAddress());
        //user.setId(userEntity.getId());
        // ... cho đến hết các thuộc tính
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, @Valid @ModelAttribute("user") UserRequest user, Model model) {
        if (!user.getImage().isEmpty())
            user.setAvatar(storageService.upload(user.getImage()));
        User entity = modelMapper.map(user, User.class);
        userService.edit(entity);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        userService.remove(id);
        return "redirect:/users";
    }
}

