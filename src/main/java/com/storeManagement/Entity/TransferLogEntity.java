package com.storeManagement.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSFER_LOGS")
public class TransferLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Column(nullable = false)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "source_user_id", nullable = false)
    private UserEntity sourceUser;

    @ManyToOne
    @JoinColumn(name = "destination_user_id", nullable = false)
    private UserEntity destinationUser;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transferDate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private DeviceStatus status;

    @Column
    private String description;
}
