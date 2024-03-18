package com.ra.springmvc2.controller;

import com.ra.springmvc2.model.Product;
import com.ra.springmvc2.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ComputerService computerService;

    @GetMapping(value = {"", "/index"})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("home/index");
        view.addObject("txt", "Hoàng Văn Trung");
        view.addObject("data", computerService.findAll());
        return view;
    }
    @GetMapping("/create")
    public String create(Model model) {
        Product p = new Product();
        p.setCreated(new Date());
        model.addAttribute("pro", p);
        return "home/create";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("pro") Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        redirectAttributes.addAttribute("txt", "Update thành công!");
        if (bindingResult.hasErrors()) {
            return "home/create";
        }
        return "redirect:/home";
    }
}