package com.storeManagement.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {

    private List<String> serialNumbers;
    private Long sourceUserId;
    private Long destinationUserId;
}
