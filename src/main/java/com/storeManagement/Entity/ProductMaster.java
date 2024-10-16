package com.storeManagement.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_MASTER")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productVendor;
    private String productModel;
    private String productCategory;


    @OneToMany(mappedBy = "productMaster", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("stock-prod-master") // Managed reference to control serialization of stocks
    private List<StockEntity> stocks;
}

