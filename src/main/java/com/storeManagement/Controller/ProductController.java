package com.storeManagement.Controller;

import com.storeManagement.Entity.Product;
import com.storeManagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://store-mgmt-ang-sbjava.netlify.app","http://localhost:3000"})
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get Mapping
    @GetMapping(path = "/products/user/{userId}")
    public List<Product> getProductsByUser(@PathVariable Long userId) {
        return productService.getProductsByUser(userId);
    }

    @GetMapping(path = "/products/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping(path = "/products/search/{prodName}")
    public List<Product> getProductByName(@PathVariable String prodName) {
        return productService.getProductByName(prodName);
    }

    // Post Mapping
    @PostMapping(path = "/addProduct/user/{userId}")
    public Product addProduct(@PathVariable Long userId, @RequestBody Product product) {
        return productService.addProduct(product, userId);
    }

    // Update Mapping
    @PutMapping(path = "updateProduct/{id}/user/{userId}")
    public Product updateProduct(@PathVariable Long id, @PathVariable Long userId, @RequestBody Product newProduct) {
        return productService.updateProduct(id, newProduct, userId);
    }

    // Update quantity Mapping
    @PutMapping(path = "updateProductQuantity/{id}/user/{userId}")
    public Product updateProductQuantity(@PathVariable Long id,@RequestParam int newProductQty, @PathVariable Long userId) {
        return productService.updateProductQuantity(id, newProductQty, userId);
    }

    // Delete Mapping
    @DeleteMapping(path = "/deleteProduct/{id}/user/{userId}")
    public String deleteProduct(@PathVariable Long id, @PathVariable Long userId) {
        return productService.deleteProduct(id, userId);
    }
}
