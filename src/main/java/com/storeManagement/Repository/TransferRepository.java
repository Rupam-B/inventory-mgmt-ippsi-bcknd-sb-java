package com.storeManagement.Repository;

import com.storeManagement.Entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    List<TransferEntity> findBySourceUserUserId(Long sourceUserId);

    List<TransferEntity> findByDestinationUserUserId(Long destinationUserId);
}
