package com.products.example.demoproducts.controller;

import java.util.List;

import com.products.example.demoproducts.entity.Product;
import com.products.example.demoproducts.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public List <Product> getProducts() {
        return productService.listOfProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable(name = "productId") Long id ) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void addNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    @PutMapping(value= "update/{id}")
    public void updateProduct(@PathVariable Long id, 
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "price") double price,
                              @RequestParam(name = "country") String country,
                              @RequestParam(name = "description") String description ) {

        productService.updateProduct(id, name, description, country, price);   
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
