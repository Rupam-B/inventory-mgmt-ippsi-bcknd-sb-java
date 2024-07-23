package com.storeManagement.Controller;

import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://store-mgmt-ang-sbjava.netlify.app"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public List<UserEntity> getAllUsers() {
        return userService.getallUsers();
    }
    @GetMapping(path = "/users/{id}")
    public Optional<UserEntity> geUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(path = "/addUser")
    public UserEntity addUser(@RequestBody UserEntity user) {
        return userService.addUser(user);
    }

    @PutMapping(path = "/updateUser/{id}")
    public UserEntity updateUser(@PathVariable Long id,  @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }
    @DeleteMapping(path = "/delUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted"+id;
    }


}
