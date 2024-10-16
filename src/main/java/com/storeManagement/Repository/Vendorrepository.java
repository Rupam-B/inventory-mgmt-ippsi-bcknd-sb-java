package com.storeManagement.Repository;

import com.storeManagement.Entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Vendorrepository extends JpaRepository<VendorEntity, Long> {
}
