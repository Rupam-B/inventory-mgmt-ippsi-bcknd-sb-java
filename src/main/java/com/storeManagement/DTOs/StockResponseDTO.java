package com.storeManagement.DTOs;

import com.storeManagement.Entity.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockResponseDTO {

    private Long stockId;
    private Long productId;
    private String serialNumber;
    private String description;
    private DeviceStatus deviceStatus;
    private String productVendor;
    private String productModel;
    private String productCategory;
    private LocalDate productPurchaseDate;

    private Long userId;
    private String userName;
}
