package com.storeManagement.Repository;

import com.storeManagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser_UserId(Long userId);
    List<Product> findByName(String name);


        @Query("SELECT p FROM Product p WHERE p.id = :productId AND p.user.id = :userId")
        Optional<Product> findByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Long userId);



}
