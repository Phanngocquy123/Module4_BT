package com.web.jpa.controller;
import com.web.jpa.entity.Computer;
import com.web.jpa.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ComputerRepository computerRepository;

    @GetMapping(value = {"", "/index"})
    public String index(Model model) {
        List<Computer> data = computerRepository.findAll();
        model.addAttribute("txt", "phan ngoc quy");
        model.addAttribute("data", data);
        return "home/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("test","aaaaaaaaa");
        return "home/create";
    }

}
