package com.storeManagement.Controller;

import com.storeManagement.DTOs.TransferRequestDTO;
import com.storeManagement.Entity.TransferEntity;
import com.storeManagement.Service.DeviceTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000","https://inventory-mgmt-ipssi-rct.netlify.app"})
public class TransferController {

    @Autowired
    DeviceTransferService deviceTransferService;

    @PostMapping("/createTransfer")
    public String createTransfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        try {
            // Extract the data from the DTO
            List<String> serialNumbers = transferRequestDTO.getSerialNumbers();
            Long sourceUserId = transferRequestDTO.getSourceUserId();
            Long destinationUserId = transferRequestDTO.getDestinationUserId();

            System.out.println("Serial Numbers: " + serialNumbers);
            System.out.println("Source User ID: " + sourceUserId);
            System.out.println("Destination User ID: " + destinationUserId);


            // Call the service method to handle the transfer
            deviceTransferService.transferDevices(serialNumbers, sourceUserId, destinationUserId);
            return "Transfer request created successfully!";
        } catch (Exception e) {
            return "Error occurred during transfer: " + e.getMessage();
        }
    }

    // Endpoint to get transfers by source user ID
    @GetMapping("/source/{sourceUserId}")
    public List<TransferEntity> getTransfersBySourceUserId(@PathVariable Long sourceUserId) {
        return deviceTransferService.getTransfersBySourceUserId(sourceUserId);
    }

    // Endpoint to get transfers by destination user ID
    @GetMapping("/destination/{destinationUserId}")
    public List<TransferEntity> getTransfersByDestinationUserId(@PathVariable Long destinationUserId) {
        return deviceTransferService.getTransfersByDestinationUserId(destinationUserId);
    }

    @PutMapping("/mark-as-received/{transferId}")
    public ResponseEntity<?> markAsReceived(@PathVariable Long transferId) {
        deviceTransferService.markAsReceived(transferId);
        return ResponseEntity.ok("Transfer marked as received and stock updated.");
    }

}
