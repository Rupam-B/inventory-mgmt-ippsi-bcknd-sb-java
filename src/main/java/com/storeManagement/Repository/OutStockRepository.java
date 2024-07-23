package com.storeManagement.Repository;

import com.storeManagement.Entity.OutStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutStockRepository extends JpaRepository<OutStockEntity, Long> {
}
