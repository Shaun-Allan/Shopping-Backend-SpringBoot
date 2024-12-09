package com.shaun.shopping.controller;

import com.shaun.shopping.model.Product;
import com.shaun.shopping.service.ProductRepository;
import com.shaun.shopping.service.SearchRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SearchRepositoryImpl searchRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/products")
    public Product addProduct(
            @RequestPart("image") MultipartFile imageFile,
            @RequestParam("pid") String pid,
            @RequestParam("pname") String pname,
            @RequestParam("pdesc") String pdesc,
            @RequestParam("cost") int cost) {
        try {
            // Convert the image to Base64
            String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());

            // Create and save the product
            Product product = new Product(pid, pname, pdesc, cost, base64Image);
            return productRepository.save(product);
        } catch (IOException e) {
            throw new RuntimeException("Error while processing the file: " + e.getMessage());
        }
    }


    @PutMapping("/products")
    public Product updateProduct(
            @RequestPart("image") MultipartFile imageFile,
            @RequestParam("pid") String pid,
            @RequestParam("pname") String pname,
            @RequestParam("pdesc") String pdesc,
            @RequestParam("cost") int cost) {
        try {
            // Convert the image to Base64
            String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());

            // Create and save the product
            Product product = new Product(pid, pname, pdesc, cost, base64Image);
            return productRepository.save(product);
        } catch (IOException e) {
            throw new RuntimeException("Error while processing the file: " + e.getMessage());
        }
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable String id){
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product with ID " + id + " has been deleted successfully.";
        } else {
            return "Product with ID " + id + " not found.";
        }
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable String id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found."));
    }


    @GetMapping("search/{text}")
    public List<Product> search(@PathVariable String text){
        return searchRepository.findByText(text);
    }
}

