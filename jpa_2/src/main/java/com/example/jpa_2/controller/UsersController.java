package com.example.jpa_2.controller;

import com.example.jpa_2.entity.UserEntity;
import com.example.jpa_2.entity.UserRequest;
import com.example.jpa_2.service.FileStorageService;
import com.example.jpa_2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/user")
public class UsersController {
    private UserService userService;
    private FileStorageService storageService;
    private ModelMapper modelMapper;
    public UsersController(UserService userService, ModelMapper modelMapper, FileStorageService storageService){
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }


    @GetMapping(value = {"", "/index"})
    private String showListUser(@RequestParam(name = "page", defaultValue = "1") int currentPage,
                                @RequestParam(name = "key", required = false) String key, Model model){
        int pageSize = 2;
        Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("email").ascending());

        Page<UserEntity> page;
        if (key == null || key.isEmpty()){
            page = userService.findAll(pageable);
        } else {
            page = userService.searchAndPagination(key, currentPage-1, pageSize);
        }

        List<UserEntity> userList = page.getContent();   // lấy danh sách người dùng ở trang hiện tại
        model.addAttribute("userList", userList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("key", key);
        return "/user/index";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new UserRequest());
        return "/user/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") UserRequest user, BindingResult bindingResult, Model model) {
        if (userService.checkExistEmail(user.getEmail()))
            bindingResult.addError(new FieldError("user", "email", "Email đã tồn tại!"));
        if (userService.checkExistPhone(user.getPhone()))
            bindingResult.addError(new FieldError("user", "phone", "Số điện thoại đã tồn tại!"));
        if (user.getImage() == null || user.getImage().isEmpty())
            bindingResult.addError(new FieldError("user", "image", "Ảnh không được bỏ trống"));
        if (bindingResult.hasErrors())
            return "/user/create";
        user.setAvatar(storageService.upload(user.getImage()));
        UserEntity entity = modelMapper.map(user, UserEntity.class);
        userService.add(entity);
        return "redirect:index";
    }

        @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        UserEntity userEntity = userService.findId(id);
        UserRequest user = modelMapper.map(userEntity, UserRequest.class);
        //UserRequest user = new UserRequest();
        //user.setAddress(userEntity.getAddress());
        //user.setId(userEntity.getId());
        // ... cho đến hết các thuộc tính
        model.addAttribute("user", user);
        return "/user/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, @Valid @ModelAttribute("user") UserRequest user, Model model) {
        if (!user.getImage().isEmpty())
            user.setAvatar(storageService.upload(user.getImage()));
        UserEntity entity = modelMapper.map(user, UserEntity.class);
        userService.update(entity);
        return "redirect:/admin/user/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        UserEntity entity = userService.findId(id);
        if (entity != null) {
            userService.delete(entity);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully.");
        }
        return "redirect:/admin/user/index";
    }
}
