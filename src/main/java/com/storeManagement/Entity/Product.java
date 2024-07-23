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
@Table(name = "PRODUCT_TBL")

public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private String description;
    private String category;
}
