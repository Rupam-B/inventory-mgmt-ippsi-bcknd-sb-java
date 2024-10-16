package com.storeManagement.Service;

import com.storeManagement.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UsarService {
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(Long id);
    UserEntity addUser(UserEntity user);
    String deleteUser(Long id);
    UserEntity updateUser(Long id, UserEntity updatedUser);
}
