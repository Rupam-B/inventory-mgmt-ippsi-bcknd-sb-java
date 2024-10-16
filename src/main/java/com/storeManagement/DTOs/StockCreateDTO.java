package com.storeManagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCreateDTO {

    private Long usersId;

    private Long productId;

    private String serialNumber;

    private String deviceStatus;

    private LocalDate productPurchaseDate;

}
