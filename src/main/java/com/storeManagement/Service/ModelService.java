package com.storeManagement.Service;


import com.storeManagement.Entity.ModelEntity;
import com.storeManagement.Entity.VendorEntity;
import com.storeManagement.Repository.ModelRepository;
import com.storeManagement.Repository.Vendorrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    Vendorrepository vendorrepository;

    public List<ModelEntity> getAllModels(){
        return modelRepository.findAll();
    }

    public Optional<ModelEntity> getModelById(Long modelId){
        return modelRepository.findById(modelId);
    }

    public List<ModelEntity> getModelByVendor(Long vendorId){
        return modelRepository.findByVendor_VendorId(vendorId);
    }

    public ModelEntity createModel(ModelEntity model, Long vendorId){
        Optional <VendorEntity> vendor = vendorrepository.findById(vendorId);

        if(vendor.isPresent()){
            model.setVendor(vendor.get());
            return modelRepository.save(model);
        }
        else {
            throw new RuntimeException("Vendor doesnot Exist");
        }
    }


}
