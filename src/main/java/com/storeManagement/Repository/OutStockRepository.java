package com.storeManagement.Repository;

import com.storeManagement.Entity.OutStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutStockRepository extends JpaRepository<OutStockEntity, Long> {
    List<OutStockEntity> findByUser_UserId(Long userId);

    @Query(value = "SELECT * from outStock_tbl WHERE outStock_tbl.out_stock_category = 'SECL'", nativeQuery = true)
    List<OutStockEntity> allOutstocks();

}