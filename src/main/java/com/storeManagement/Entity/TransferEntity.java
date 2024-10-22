package com.storeManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSFER_TBL")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_master_id", nullable = false)
    private ProductMaster productMaster;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_status_id", nullable = false)
    private DeviceStatus deviceStatus;

    @Column(nullable = false)
    private String desctription;

    @Column(nullable = false)
    private LocalDate productPurchaseDate;

    @ManyToOne
    @JoinColumn(name = "source_user_id", nullable = false)
    private UserEntity sourceUser;

    @ManyToOne
    @JoinColumn(name = "destination_user_id", nullable = false)
    private UserEntity destinationUser;
}

