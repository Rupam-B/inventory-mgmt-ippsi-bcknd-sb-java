package com.storeManagement.Entity;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STOCK_TBL")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "stockId")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_master_id", nullable = false)
    @JsonBackReference("stock-prod-master") // Back reference to avoid recursion
    private ProductMaster productMaster;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_status_id", nullable = false)
    private DeviceStatus deviceStatus;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate productPurchaseDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("stocks-to-user") // Back reference to avoid recursion
    private UserEntity user;
}
