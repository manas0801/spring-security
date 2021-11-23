package com.smartbit.sswfl.controller;

import com.smartbit.sswfl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

@Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    public String getProduct(Model model){
        model.addAttribute("username",SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("productList",productRepository.findAll());
        return "product.html";
    }
}
