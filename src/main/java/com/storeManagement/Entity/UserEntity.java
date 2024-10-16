package com.storeManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference; // Import the back reference annotation
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS_TBL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Optional for better serialization handling
public class UserEntity {

    @Id
    @GeneratedValue
    private Long userId;

    private String userName;
    private String password;
    private String mobile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OutStockEntity> outStocks;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("stocks-to-user")
    private List<StockEntity> stockList;

}
