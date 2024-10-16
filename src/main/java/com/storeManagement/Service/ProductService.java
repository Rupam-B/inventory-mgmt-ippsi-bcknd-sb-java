

package com.storeManagement.Service;

import com.storeManagement.Entity.Product;
import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Repository.ProductRepository;
import com.storeManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Save Product
    public Product addProduct(Product product, Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            product.setUser(user.get());
            return productRepository.save(product);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Get product by Name
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    // Get products by user ID
    public List<Product> getProductsByUser(Long userId) {
        return productRepository.findByUser_UserId(userId);
    }

    // Delete product by ID
    public String deleteProduct(Long id, Long userId) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent() && product.get().getUser().getUserId().equals(userId)) {
            productRepository.deleteById(id);
            return "Product deleted: " + id;
        } else {
            throw new RuntimeException("Product not found or you do not have permission to delete this product.");
        }
    }

    // Update product by ID
    public Product updateProduct(Long id, Product
            newProductDetails, Long userId) {
        return productRepository.findById(id)
                .map(product -> {
                    if (!product.getUser().getUserId().equals(userId)) {
                        throw new RuntimeException("You do not have permission to update this product.");
                    }
                    product.setName(newProductDetails.getName());
                    product.setCategory(newProductDetails.getCategory());
                    product.setPrice(newProductDetails.getPrice());
                    product.setQuantity(newProductDetails.getQuantity());
                    product.setDescription(newProductDetails.getDescription());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }



    // Update product quantity by ID
    public Product updateProductQuantity(Long id, int newQuantity, Long userId) {
        return productRepository.findById(id)
                .map(product -> {
                    // Check if the product belongs to the user
                    if (!product.getUser().getUserId().equals(userId)) {
                        throw new RuntimeException("You do not have permission to update this product's quantity.");
                    }
                    // Update the quantity
                    product.setQuantity(newQuantity);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

}


