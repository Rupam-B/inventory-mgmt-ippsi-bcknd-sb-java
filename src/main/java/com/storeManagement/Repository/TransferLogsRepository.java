package com.storeManagement.Repository;


import com.storeManagement.Entity.TransferLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransferLogsRepository extends JpaRepository<TransferLogEntity, Long> {
        List<TransferLogEntity> findBySerialNumber(String serialNumber);
    }

