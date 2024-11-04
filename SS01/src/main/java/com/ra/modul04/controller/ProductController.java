package com.ra.modul04.controller;

import com.ra.modul04.model.entity.Product;
import com.ra.modul04.model.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
@GetMapping
    public String product(Model model) {
    List<Product> products = productService.findAll();
    model.addAttribute("products", products);
    return "/product/product";
}

@GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/product/add";
}

@PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute Product product, Model model, BindingResult result) {
    if (result.hasErrors()) {
        model.addAttribute("product", new Product());
        return "/product/add";
    }
    if(productService.save(product) != null) {
        return "redirect:/product";
    }
    model.addAttribute("product", new Product());
    return "/product/add";
}

@GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product/edit";
}

@PostMapping("/edit/{id}")
    public String editProduct(@Valid @ModelAttribute Product product, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "redirect:/product/edit/{id}";
        }
        if(productService.save(product) != null) {
            model.addAttribute("product", product);
            return "redirect:/product";
        }
    model.addAttribute("product", product);
    return "redirect:/product/edit/{id}";
}

@GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/product";
}
}
