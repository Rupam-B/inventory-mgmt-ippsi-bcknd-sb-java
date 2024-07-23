package com.storeManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OUTSTOCK_TBL")
public class OutStockEntity {

    @Id
    @GeneratedValue
    private Long outStockId;
    private String outStockName;
    private String outStockCategory;
    private String outStockDescription;
}
