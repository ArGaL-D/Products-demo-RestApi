package com.products.example.demoproducts.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.products.example.demoproducts.entity.Product;
import com.products.example.demoproducts.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    
    private ProductRepository productRepository;

    @Autowired
    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /* Methods */

    // Get
    public List<Product> listOfProducts () {
        return productRepository.findAll();
    }

    public Product getProductById (Long id) {
        Optional <Product> optional = productRepository.findById(id);
        
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    // Post
    public void addNewProduct (Product product) {
        /*
        Optional <Product> productOptional = productRepository.findById(product.getId());

        if (productOptional.isPresent()){
            throw new IllegalStateException ("The ID already exists ["+ product.getId() +"]" );
        }
        */
        productRepository.save(product);
    }

    // Update
    @Transactional
    public void updateProduct (Long id, String name, String description, String country ,double price) {
        boolean productExists = productRepository.existsById(id);
        
        if (productExists) {
            if (name.length() > 0 && name != null && description.length() > 0 && description != null &&
                country.length() > 0 && country != null && price >= 0) {

                Optional <Product> optional = productRepository.findById(id);
                Product product = optional.get();

                product.setName(name);
                product.setPrice(price);
                product.setCountry(country);
                product.setDescription(description);
            }            
        } else {
            throw new IllegalStateException("ID ["+ id +"] does not exist");
        }
    }

    // Delete
    public void deleteProduct (Long id) {
        boolean productExists = productRepository.existsById(id);

        if (!productExists) {
            throw new IllegalStateException("ID ["+ id +"] does not exist");
        }
        productRepository.deleteById(id);
    }


}
