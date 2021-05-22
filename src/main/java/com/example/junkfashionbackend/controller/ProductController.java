package com.example.junkfashionbackend.controller;

import com.example.junkfashionbackend.model.Product;
import com.example.junkfashionbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    private List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/add")
    private ResponseEntity<String> addProduct(@RequestBody Product product){
        if(!productRepository.findByProductName(product.getProductName()).isEmpty()) {
            return ResponseEntity.of(Optional.of("Product already available in product list"));
        }
        else {
            product.setId(null);
            productRepository.save(product);
            return ResponseEntity.of(Optional.of("New Product Added"));
        }
    }

    @PostMapping("/search")
    private List<Product> findProductByName(@RequestBody Product product){
        return productRepository.findByProductNameContainingIgnoreCase(product.getProductName());
    }

}
