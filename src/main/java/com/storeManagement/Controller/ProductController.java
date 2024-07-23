package com.storeManagement.Controller;

import com.storeManagement.Entity.Product;
import com.storeManagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://store-mgmt-ang-sbjava.netlify.app"})
public class ProductController {

    @Autowired
    ProductService productService;

//    Get Mapping
    @GetMapping(path = "/products")
    public List<Product> getProducts() {
        return productService.getAllProduct();
    }

    @GetMapping(path = "/products/{id}")
    public Optional<Product> getProductbyId(@PathVariable Long id) {
        return productService.getProductById(id);
    }

//    Post Mapping
    @PostMapping(path = "/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

//    Update Mapping
    @PutMapping(path = "updateProduct/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        return productService.updateProduct(id, newProduct);
    }

//    Delete Mapping
    @DeleteMapping(path = "/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
