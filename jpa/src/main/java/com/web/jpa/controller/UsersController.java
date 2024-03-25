package com.web.jpa.controller;


import com.web.jpa.entity.User;
import com.web.jpa.entity.UserRequest;
import com.web.jpa.repository.UsersRepository;
import com.web.jpa.service.FileStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/users")
public class UsersController {
    private FileStorageService storageService;
    private ModelMapper modelMapper;
    public UsersController(ModelMapper modelMapper, FileStorageService storageService){
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(value = {"", "/index"})
    private String ShowListUser(@RequestParam(name = "page", defaultValue = "1") int currentPage,
                                @RequestParam(name = "key", required = false) String key, Model model){
        int pageSize = 5;
        List<User> userList = usersRepository.findAll();
        if (key != null && !key.isEmpty()){
            userList = userList.stream()
                    .filter(u -> u.getEmail().contains(key.toLowerCase()) || u.getPhone().contains(key.toLowerCase()))
                    .collect(Collectors.toList());
        }

        int totalPage = (int) Math.ceil((double) userList.size() / pageSize);
        userList = userList.stream()
                .skip((long) (currentPage - 1) * pageSize).limit(pageSize)
                .collect(Collectors.toList());

        model.addAttribute("userList", userList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("key", key);
        return "/users/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new UserRequest());
        return "/users/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") UserRequest user, BindingResult bindingResult, Model model) {
        if (usersRepository.existsByEmail(user.getEmail()))
            bindingResult.addError(new FieldError("user", "email", "Email đã tồn tại!"));
        if (usersRepository.existsByPhone(user.getPhone()))
            bindingResult.addError(new FieldError("user", "phone", "Số điện thoại đã tồn tại!"));
        if (user.getImage() == null || user.getImage().isEmpty())
            bindingResult.addError(new FieldError("user", "image", "Ảnh không được bỏ trống"));
        if (bindingResult.hasErrors())
            return "/users/create";
        user.setAvatar(storageService.upload(user.getImage()));
        User entity = modelMapper.map(user, User.class);
        usersRepository.save(entity);
        return "redirect:index";
    }

//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") String id, Model model) {
//        User userEntity = userService.findId(id);
//        UserRequest user = modelMapper.map(userEntity, UserRequest.class);
//        //UserRequest user = new UserRequest();
//        //user.setAddress(userEntity.getAddress());
//        //user.setId(userEntity.getId());
//        // ... cho đến hết các thuộc tính
//        model.addAttribute("user", user);
//        return "/users/edit";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String edit(@PathVariable("id") String id, @Valid @ModelAttribute("user") UserRequest user, Model model) {
//        if (!user.getImage().isEmpty())
//            user.setAvatar(storageService.upload(user.getImage()));
//        User entity = modelMapper.map(user, User.class);
//        userService.edit(entity);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") String id) {
//        userService.remove(id);
//        return "redirect:/users";
//    }
}
