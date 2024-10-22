package com.storeManagement.DTOs;

import com.storeManagement.Entity.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCreateDTO {

    private Long usersId;

    private Long productId;

    private String serialNumber;

    private Long statusId;

    private String description;

    private LocalDate productPurchaseDate;

}
