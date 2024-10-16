package com.storeManagement.AdditionalFunctionalClass;

import com.storeManagement.DTOs.StockResponseDTO;
import com.storeManagement.Entity.ProductMaster;
import com.storeManagement.Entity.StockEntity;
import com.storeManagement.Entity.UserEntity;

public class StockConverter {

    // Method to convert StockEntity to StockResponseDTO
    public StockResponseDTO convertToStockResponseDTO(StockEntity stock) {
        StockResponseDTO dto = new StockResponseDTO();

        // Set stock details
        dto.setStockId(stock.getStockId());
        dto.setSerialNumber(stock.getSerialNumber());
        dto.setDeviceStatus(stock.getDeviceStatus());
        dto.setProductPurchaseDate(stock.getProductPurchaseDate());

        // Set product details from ProductMaster
        ProductMaster productMaster = stock.getProductMaster();
        dto.setProductId(productMaster.getProductId());
        dto.setProductVendor(productMaster.getProductVendor());
        dto.setProductModel(productMaster.getProductModel());
        dto.setProductCategory(productMaster.getProductCategory());


        // Set user details from UserEntity
        UserEntity user = stock.getUser();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());

        return dto;
    }
}

