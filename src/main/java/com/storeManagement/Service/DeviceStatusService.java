package com.storeManagement.Service;


import com.storeManagement.Entity.DeviceStatus;
import com.storeManagement.Repository.DeviceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceStatusService {


    @Autowired
    DeviceStatusRepository deviceStatusRepository;


    public List<DeviceStatus> getAllStatus(){
        return deviceStatusRepository.findAll();
    }

    public Optional<DeviceStatus> getStatusById(Long statusId){
        return deviceStatusRepository.findById(statusId);
    }

    public DeviceStatus addStatus(DeviceStatus deviceStatus){
        return deviceStatusRepository.save(deviceStatus);
    }

    public String deleteStatus (Long statusId){
        deviceStatusRepository.deleteById(statusId);
        return "Device Status Deleted SuccessFully";
    }
}
