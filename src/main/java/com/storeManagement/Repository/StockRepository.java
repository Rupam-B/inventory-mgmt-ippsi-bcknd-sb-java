package com.storeManagement.Repository;

import com.storeManagement.DTOs.StockResponseDTO;
import com.storeManagement.Entity.OutStockEntity;
import com.storeManagement.Entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
    List<StockEntity> findByUser_UserId(Long userId);
    StockEntity findBySerialNumber(String serialNumber);

    @Query(value = "SELECT * from stock_tbl WHERE serial_number = ?1", nativeQuery = true)
    Optional<StockEntity> searchBySerialNumber(String serialNumber);

    @Query("SELECT s FROM StockEntity s WHERE s.user.id = :userId AND s.deviceStatus.statusID = :statusId")
    List<StockEntity> findAllByUserAndDeviceStatusId(@Param("userId") Long userId, @Param("statusId") Long statusId);


}
