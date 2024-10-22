package com.storeManagement.Repository;

import com.storeManagement.Entity.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceStatusRepository extends JpaRepository<DeviceStatus,Long> {
}
