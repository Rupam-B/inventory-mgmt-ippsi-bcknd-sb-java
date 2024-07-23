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
@Table(name = "USERS_TBL")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long userId;
    private String userName;
    private String password;
    private String mobile;
}
