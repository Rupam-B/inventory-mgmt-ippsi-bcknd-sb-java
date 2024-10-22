package com.storeManagement.Controller;


import com.storeManagement.Entity.CategoryEntity;
import com.storeManagement.Entity.DeviceStatus;
import com.storeManagement.Service.DeviceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://inventory-mgmt-ipssi-rct.netlify.app","http://localhost:3000"})
public class DeviceStatusController {

    @Autowired
    DeviceStatusService deviceStatusService;

    @GetMapping(path = "/allDeviceStatus")
    public List<DeviceStatus> getAllStatus(){
        return deviceStatusService.getAllStatus();
    }

    @GetMapping(path = "/deviceStatus/{statusId}")
    public Optional<DeviceStatus> getStatusById(@PathVariable Long statusId){
        return deviceStatusService.getStatusById(statusId);
    }

    @PostMapping(path = "/addStatus")
    public DeviceStatus saveCategory(@RequestBody DeviceStatus status){
        return deviceStatusService.addStatus(status);
    }


    @DeleteMapping(path = "/deleteDeviceStatus/{statusId}")
    public String deleteStatus (@PathVariable Long statusId){
        deviceStatusService.deleteStatus(statusId);
        return "Status Deleted Successfully";
    }
}
