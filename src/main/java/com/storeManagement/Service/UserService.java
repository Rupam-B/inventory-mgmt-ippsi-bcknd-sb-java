package com.storeManagement.Service;

import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getallUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }

    public UserEntity addUser(UserEntity user){
        return  userRepository.save(user);
    }

    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "User deleted"+ id;
    }

    public UserEntity updateUser(Long id , UserEntity updatedUser){
        return userRepository.findById(id)
                .map(auser ->{
                    auser.setUserName(updatedUser.getUserName());
                    auser.setMobile(updatedUser.getMobile());
                    auser.setPassword(updatedUser.getPassword());

                    return  userRepository.save(auser);
                })
                .orElseThrow(()-> new RuntimeException("User Not Found"));
    }


}
