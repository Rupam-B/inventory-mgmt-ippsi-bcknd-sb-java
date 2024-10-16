package com.storeManagement.Service;


import com.storeManagement.Entity.ProductMaster;
import com.storeManagement.Repository.ProductMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMasterService {

    @Autowired
    private ProductMasterRepository productMasterRepository;

    // Save a new product master
    public ProductMaster saveProduct(ProductMaster productMaster) {
        // Check if a product with the same vendor, model, and category already exists
        Optional<ProductMaster> existingProduct = productMasterRepository.findByProductVendorAndProductModelAndProductCategory(
                productMaster.getProductVendor(),
                productMaster.getProductModel(),
                productMaster.getProductCategory()
        );

        if (existingProduct.isPresent()) {
            throw new RuntimeException("Product with the same vendor, model, and category already exists.");
        }

        return productMasterRepository.save(productMaster);
    }

    // Get all products
    public List<ProductMaster> getAllProducts() {
        return productMasterRepository.findAll();
    }

    // Get a product by ID
    public Optional<ProductMaster> getProductById(Long productId) {
        return productMasterRepository.findById(productId);
    }

    // Update a product
    public ProductMaster updateProduct(Long productId, ProductMaster updatedProduct) {
        Optional<ProductMaster> existingProductOpt = productMasterRepository.findById(productId);
        if (existingProductOpt.isPresent()) {
            ProductMaster existingProduct = existingProductOpt.get();
            existingProduct.setProductVendor(updatedProduct.getProductVendor());
            existingProduct.setProductModel(updatedProduct.getProductModel());
            existingProduct.setProductCategory(updatedProduct.getProductCategory());
            return productMasterRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + productId);
        }
    }

    // Delete a product by ID
    public void deleteProduct(Long productId) {
        productMasterRepository.deleteById(productId);
    }
}

