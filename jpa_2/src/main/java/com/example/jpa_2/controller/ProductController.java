package com.example.jpa_2.controller;

import com.example.jpa_2.entity.ProductEntity;
import com.example.jpa_2.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/index")
    public String showProductList(Model model){
        List<ProductEntity> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "products/index";
    }
}
