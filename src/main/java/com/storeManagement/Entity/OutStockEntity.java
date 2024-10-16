
package com.storeManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OUTSTOCK_TBL")
public class OutStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outStockId;

    private String outStockName;
    private String outStockCategory;
    private String outStockDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;
}