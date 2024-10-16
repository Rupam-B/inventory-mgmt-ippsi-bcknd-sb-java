package com.storeManagement.Service;

import com.storeManagement.Entity.StockEntity;
import com.storeManagement.Entity.TransferEntity;
import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Repository.StockRepository;
import com.storeManagement.Repository.TransferRepository;
import com.storeManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTransferService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private UserRepository userRepository;

    public void transferDevices(List<String> serialNumbers, Long sourceUserId, Long destinationUserId) {
        for (String serial : serialNumbers) {
            // Step 1: Fetch the device from stock
            StockEntity stockDevice = stockRepository.findBySerialNumber(serial);
            if (stockDevice != null) {
                // Step 2: Create a new transfer record
                TransferEntity transferDevice = new TransferEntity();
                transferDevice.setProductMaster(stockDevice.getProductMaster());
                transferDevice.setSerialNumber(stockDevice.getSerialNumber());
                transferDevice.setDeviceStatus(stockDevice.getDeviceStatus());
                transferDevice.setProductPurchaseDate(stockDevice.getProductPurchaseDate());

                // Fetch the destination user from the UserRepository
                UserEntity destinationUser = userRepository.findById(destinationUserId)
                        .orElseThrow(() -> new RuntimeException("Destination user not found!"));

                // Set source and destination users
                transferDevice.setSourceUser(stockDevice.getUser());
                transferDevice.setDestinationUser(destinationUser);

                // Step 3: Save the transfer record
                transferRepository.save(transferDevice);

                // Step 4: Remove the device from stock
                stockRepository.delete(stockDevice);
            } else {
                // Handle case where the device is not found
                throw new RuntimeException("Device not found: " + serial);
            }
        }
    }

    public List<TransferEntity> getTransfersBySourceUserId(Long sourceUserId) {
        return transferRepository.findBySourceUserUserId(sourceUserId);
    }

    public List<TransferEntity> getTransfersByDestinationUserId(Long destinationUserId) {
        return transferRepository.findByDestinationUserUserId(destinationUserId);
    }


    public void markAsReceived(Long transferId) {

        TransferEntity transferEntity = transferRepository.findById(transferId)
                .orElseThrow(() -> new RuntimeException("Transfer record not found"));


        StockEntity newStock = new StockEntity();
        newStock.setProductMaster(transferEntity.getProductMaster());
        newStock.setSerialNumber(transferEntity.getSerialNumber());
        newStock.setDeviceStatus(transferEntity.getDeviceStatus());
        newStock.setProductPurchaseDate(transferEntity.getProductPurchaseDate());


        newStock.setUser(transferEntity.getDestinationUser());


        stockRepository.save(newStock);


        transferRepository.delete(transferEntity);
    }
}

