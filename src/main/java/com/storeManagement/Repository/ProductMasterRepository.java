package com.storeManagement.Repository;

import com.storeManagement.Entity.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long> {
    Optional<ProductMaster> findByProductVendorAndProductModelAndProductCategory(String productVendor, String productModel, String productCategory);
}
