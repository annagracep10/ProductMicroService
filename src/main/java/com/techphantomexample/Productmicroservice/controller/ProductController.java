package com.techphantomexample.Productmicroservice.controller;

import com.techphantomexample.Productmicroservice.model.BaseProduct;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.model.Seed;

import com.techphantomexample.Productmicroservice.service.ProductOperationException;
import com.techphantomexample.Productmicroservice.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showProducts(Model model) {
       model.addAttribute("listOfProducts", productService.getAllProducts());
       return "productList";
    }

    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        BaseProduct baseProduct= new BaseProduct () ;
        model.addAttribute("product", baseProduct);
        return "new_product";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("product") BaseProduct baseProduct ,Model model) {
        try {
            productService.createProduct(baseProduct);
            return "redirect:/product/productList";
        } catch (ProductOperationException e) {
            String errorMessage = e.getMessage();
            log.error("Product creation error: {}", errorMessage);
            model.addAttribute("error", errorMessage);
            return "new_Product";
        }
    }

    @PostMapping("/plant")
    public ResponseEntity<CreateResponse> createProduct(@RequestBody Plant Plant) {
        log.error("here");
        String result = productService.createProduct(Plant);
        HttpStatus httpStatus = null;
        if (result.equals("Product Created successfully")) {
            httpStatus = HttpStatus.CREATED;
        } else {
            httpStatus = HttpStatus.CONFLICT;
        }
        CreateResponse response = new CreateResponse(result, httpStatus.value());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PostMapping("/planter")
    public ResponseEntity<CreateResponse> createProduct(@RequestBody Planter Planter) {
        log.error("here");
        String result = productService.createProduct(Planter);
        HttpStatus httpStatus = null;
        if (result.equals("Product Created successfully")) {
            httpStatus = HttpStatus.CREATED;
        } else {
            httpStatus = HttpStatus.CONFLICT;
        }
        CreateResponse response = new CreateResponse(result, httpStatus.value());
        return ResponseEntity.status(httpStatus).body(response);
    }
}
