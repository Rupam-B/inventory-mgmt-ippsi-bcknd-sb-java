package com.storeManagement.Service;

import com.storeManagement.Entity.StockEntity;
import com.storeManagement.Entity.TransferEntity;
import com.storeManagement.Entity.TransferLogEntity;
import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Repository.StockRepository;
import com.storeManagement.Repository.TransferLogsRepository;
import com.storeManagement.Repository.TransferRepository;
import com.storeManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceTransferService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransferLogsRepository transferLogsRepository;

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
                transferDevice.setDesctription(stockDevice.getDescription());
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

    public void deleteTransferRequest(Long transferId) {

        TransferEntity transferEntity = transferRepository.findById(transferId)
                .orElseThrow(() -> new RuntimeException("Transfer request not found with ID: " + transferId));


        StockEntity restoredDevice = new StockEntity();
        restoredDevice.setProductMaster(transferEntity.getProductMaster());
        restoredDevice.setSerialNumber(transferEntity.getSerialNumber());
        restoredDevice.setDeviceStatus(transferEntity.getDeviceStatus());
        restoredDevice.setDescription(transferEntity.getDesctription());
        restoredDevice.setProductPurchaseDate(transferEntity.getProductPurchaseDate());
        restoredDevice.setUser(transferEntity.getSourceUser());


        stockRepository.save(restoredDevice);

        transferRepository.delete(transferEntity);
    }


    private void logTransfer(TransferEntity transferEntity, String description) {
        TransferLogEntity log = new TransferLogEntity();
        log.setSerialNumber(transferEntity.getSerialNumber());
        log.setSourceUser(transferEntity.getSourceUser());
        log.setDestinationUser(transferEntity.getDestinationUser());
        log.setTransferDate(LocalDateTime.now().withNano(0));
        log.setStatus(transferEntity.getDeviceStatus());
        log.setDescription(description);
        transferLogsRepository.save(log);
    }


    public void markAsReceived(Long transferId,String description) {

        TransferEntity transferEntity = transferRepository.findById(transferId)
                .orElseThrow(() -> new RuntimeException("Transfer record not found"));


        StockEntity newStock = new StockEntity();
        newStock.setProductMaster(transferEntity.getProductMaster());
        newStock.setSerialNumber(transferEntity.getSerialNumber());
        newStock.setDeviceStatus(transferEntity.getDeviceStatus());
        newStock.setDescription(description);
        newStock.setProductPurchaseDate(transferEntity.getProductPurchaseDate());


        newStock.setUser(transferEntity.getDestinationUser());


        stockRepository.save(newStock);


        logTransfer(transferEntity,description);


        transferRepository.delete(transferEntity);
    }
}

