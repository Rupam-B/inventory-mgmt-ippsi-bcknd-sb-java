package com.storeManagement.Service;

import com.storeManagement.DTOs.UserDto;
import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//Changed

@Service
public class UserServiceImpl implements UserDetailsService, UsarService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted: " + id;
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUserName(updatedUser.getUserName());
                    existingUser.setMobile(updatedUser.getMobile());
                    // Only update password if a new one is provided
                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Encode password
                    }
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.debug("Attempting to load user by username: {}", userName);
        UserEntity user = userRepository.findByUserName(userName);
        if (user == null) {
            logger.error("User not found with username: {}", userName);
            throw new UsernameNotFoundException("User not found: " + userName);
        }
        logger.debug("User found: {}", userName);
        return new User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    // New method to find user ID by username
    public UserDto findUserIdByUsername(String username) {
        UserEntity user = userRepository.findByUserName(username);
        if (user != null) {
            return new UserDto(user.getUserId(),user.getUserName());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


}
