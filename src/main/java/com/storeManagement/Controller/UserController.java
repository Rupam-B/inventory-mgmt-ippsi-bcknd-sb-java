package com.storeManagement.Controller;

import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000","https://store-mgmt-ang-sbjava.netlify.app"})
public class UserController {

    @Autowired
    private UserServiceImpl userService;

//    @PreAuthorize("@securityUtils.hasUserId(1L)")
    @GetMapping(path = "/users")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping(path = "/users/{id}")
    public Optional<UserEntity> geUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

//    @PreAuthorize("@securityUtils.hasUserId(1L)")
    @PostMapping(path = "/addUser")
    public UserEntity addUser(@RequestBody UserEntity user) {
       return userService.addUser(user);
    }

//    @PreAuthorize("@securityUtils.hasUserId(1L)")
    @PutMapping(path = "/updateUser/{id}")
    public UserEntity updateUser(@PathVariable Long id,  @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }

//    @PreAuthorize("@securityUtils.hasUserId(1L)")
    @DeleteMapping(path = "/delUser/{id}")

 
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted"+id;
    }


}
