package com.storeManagement.Repository;

import com.storeManagement.Entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    List<ModelEntity> findByVendor_VendorId(Long vendorId);
}
