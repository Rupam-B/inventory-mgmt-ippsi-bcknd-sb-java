package com.storeManagement.Service;



import com.storeManagement.AdditionalFunctionalClass.StockConverter;
import com.storeManagement.DTOs.StockCreateDTO;
import com.storeManagement.DTOs.StockResponseDTO;
import com.storeManagement.Entity.DeviceStatus;
import com.storeManagement.Entity.ProductMaster;
import com.storeManagement.Entity.StockEntity;
import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Repository.DeviceStatusRepository;
import com.storeManagement.Repository.ProductMasterRepository;
import com.storeManagement.Repository.StockRepository;
import com.storeManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    ProductMasterRepository productMasterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeviceStatusRepository deviceStatusRepository;

    private final StockConverter stockConverter = new StockConverter();


    public StockResponseDTO addStock(StockCreateDTO newstock) {

        UserEntity user = userRepository.findById(newstock.getUsersId()).orElseThrow(() -> new RuntimeException("User not found"));
        ProductMaster productMaster = productMasterRepository.findById(newstock.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        DeviceStatus deviceStatus = deviceStatusRepository.findById(newstock.getStatusId()).orElseThrow(() -> new RuntimeException("Device Status not found"));

        StockEntity stock = new StockEntity();
        stock.setUser(user);
        stock.setProductMaster(productMaster);
        stock.setSerialNumber(newstock.getSerialNumber());
        stock.setDeviceStatus(deviceStatus);
        stock.setDescription(newstock.getDescription());
        stock.setProductPurchaseDate(newstock.getProductPurchaseDate());


        StockEntity savedStock = stockRepository.save(stock);
        return stockConverter.convertToStockResponseDTO(savedStock);
    }



    public List<StockResponseDTO> getAllStock() {
        List<StockEntity> stockEntities = stockRepository.findAll();
        return stockEntities.stream()
                .map(stockConverter::convertToStockResponseDTO)
                .collect(Collectors.toList());
    }

    public List<StockResponseDTO> getStocksByUserId(Long userId) {
        List<StockEntity> stockEntities = stockRepository.findByUser_UserId(userId);


        return stockEntities.stream()
                .map(stockConverter::convertToStockResponseDTO)
                .collect(Collectors.toList());
    }




    // Get stock by ID
    public Optional<StockEntity> getStockById(Long stockId) {
        return stockRepository.findById(stockId);
    }



    // Get stock by SerialNumber
    public StockResponseDTO getStockBySerialNumber(String serialNumber) {
        StockEntity stockEntity = stockRepository.searchBySerialNumber(serialNumber)
                .orElseThrow(() -> new RuntimeException("Stock with serial number " + serialNumber + " not found"));

        return stockConverter.convertToStockResponseDTO(stockEntity);
    }





    public StockResponseDTO updateStock(Long stockId, StockCreateDTO updatedStockDTO) {
        StockEntity existingStock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock item not found with id: " + stockId));

        // Update fields if they are provided in the DTO

        if (updatedStockDTO.getSerialNumber() != null) {
            existingStock.setSerialNumber(updatedStockDTO.getSerialNumber());
        }
        if (updatedStockDTO.getStatusId() != null) {
            DeviceStatus deviceStatus = deviceStatusRepository.findById(updatedStockDTO.getStatusId())
                    .orElseThrow(() -> new RuntimeException("Device Status not found"));
            existingStock.setDeviceStatus(deviceStatus);
        }
        if (updatedStockDTO.getUsersId() != null) {
            UserEntity user = userRepository.findById(updatedStockDTO.getUsersId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existingStock.setUser(user);
        }
        if (updatedStockDTO.getDescription() != null) {
            existingStock.setDescription(updatedStockDTO.getDescription());
        }
        if (updatedStockDTO.getProductId() != null) {
            ProductMaster productMaster = productMasterRepository.findById(updatedStockDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            existingStock.setProductMaster(productMaster);
        }

        if (updatedStockDTO.getProductPurchaseDate() != null) {
            existingStock.setProductPurchaseDate(updatedStockDTO.getProductPurchaseDate());
        }

        StockEntity savedStock = stockRepository.save(existingStock);
        return stockConverter.convertToStockResponseDTO(savedStock);
    }




    public List<StockResponseDTO> getDevicesByUserAndStatus(Long userId, Long statusId) {
        List<StockEntity> stockEntities= stockRepository.findAllByUserAndDeviceStatusId(userId, statusId);

        return stockEntities.stream()
                .map(stockConverter::convertToStockResponseDTO)
                .collect(Collectors.toList());
    }


    // Delete stock item by ID
    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }
}
