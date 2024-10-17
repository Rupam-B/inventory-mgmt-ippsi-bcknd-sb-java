package com.storeManagement.Controller;


import com.storeManagement.Entity.VendorEntity;
import com.storeManagement.Service.VendorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://inventory-mgmt-ipssi-rct.netlify.app","http://localhost:3000"})
public class VendorController {

    @Autowired
    VendorServices vendorServices;


    @GetMapping(path = "/vendors")
    public List<VendorEntity> getAllVendors(){
        return vendorServices.getAllVendors();
    }

    @GetMapping(path ="/vendors/{id}")
    public Optional<VendorEntity> getVendorById(@PathVariable Long id){
        return vendorServices.getVendorbyId(id);
    }

    @PostMapping(path = "/addVendor")
    public VendorEntity addNewVendor (@RequestBody VendorEntity vendor){
        return vendorServices.createVendor(vendor);
    }
}
