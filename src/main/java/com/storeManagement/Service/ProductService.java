package com.storeManagement.Service;

import com.storeManagement.Entity.Product;
import com.storeManagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    Save Product
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

//    Get Products all product and product by ID
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

//    Delete Product by Id
    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product deleted"+id;
    }

//    Update Product By Id
    public Product updateProduct(Long id, Product newProductDetails){
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProductDetails.getName());
                    product.setCategory(newProductDetails.getCategory());
                    product.setPrice(newProductDetails.getPrice());
                    product.setQuantity(newProductDetails.getQuantity());
                    product.setDescription(newProductDetails.getDescription());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
