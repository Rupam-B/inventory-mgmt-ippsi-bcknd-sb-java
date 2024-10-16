package com.storeManagement.Service;

import com.storeManagement.Entity.VendorEntity;
import com.storeManagement.Repository.Vendorrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VendorServices {

    @Autowired
    Vendorrepository vendorrepository;

    public List<VendorEntity> getAllVendors(){
        return vendorrepository.findAll();
    }

    public Optional<VendorEntity> getVendorbyId(Long vendorId){
        return vendorrepository.findById(vendorId);
    }

    public VendorEntity createVendor(VendorEntity vendor){
        return  vendorrepository.save(vendor);
    }
}
