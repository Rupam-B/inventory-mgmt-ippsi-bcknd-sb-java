package com.storeManagement.Controller;

import com.storeManagement.Entity.TransferLogEntity;
import com.storeManagement.Repository.TransferLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "https://inventory-mgmt-ipssi-rct.netlify.app"})
public class TransferLogsController {

    @Autowired
    private TransferLogsRepository transferLogRepository;

    @GetMapping("/logs/{serialNumber}")
    public List<TransferLogEntity> getTransferLogsBySerialNumber(@PathVariable String serialNumber) {
        return transferLogRepository.findBySerialNumber(serialNumber);
    }
}

