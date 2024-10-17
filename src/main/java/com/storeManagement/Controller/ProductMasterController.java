package com.storeManagement.Controller;

import com.storeManagement.Entity.ProductMaster;
import com.storeManagement.Service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-master")
@CrossOrigin(origins = {"http://localhost:4200", "https://inventory-mgmt-ipssi-rct.netlify.app","http://localhost:3000"})
public class ProductMasterController {

    @Autowired
    private ProductMasterService productMasterService;

    // Create a new product
    @PostMapping("/add")
    public ResponseEntity<ProductMaster> addProduct(@RequestBody ProductMaster productMaster) {
        ProductMaster savedProduct = productMasterService.saveProduct(productMaster);
        return ResponseEntity.ok(savedProduct);
    }

    // Get all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductMaster>> getAllProducts() {
        List<ProductMaster> products = productMasterService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductMaster> getProductById(@PathVariable Long id) {
        Optional<ProductMaster> product = productMasterService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a product by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductMaster> updateProduct(@PathVariable Long id, @RequestBody ProductMaster productMaster) {
        ProductMaster updatedProduct = productMasterService.updateProduct(id, productMaster);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete a product by ID
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productMasterService.deleteProduct(id);
        return "Device Deleted succesfully";
    }
}

