package com.storeManagement.Controller;


import com.storeManagement.Entity.ModelEntity;
import com.storeManagement.Service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://store-mgmt-ang-sbjava.netlify.app","http://localhost:3000"})
public class ModelController {

    @Autowired
    ModelService modelService;

    @GetMapping(path = "/allModels")
    public List<ModelEntity> getAllModels(){
        return modelService.getAllModels();
    }

    @GetMapping(path = "/models/{modelId}")
    public Optional<ModelEntity> getModelById(@PathVariable Long modelId){
        return modelService.getModelById(modelId);
    }

    @GetMapping(path = "/models/vendor/{vendorId}")
    public List<ModelEntity> getVendorModels (@PathVariable Long vendorId){
        return modelService.getModelByVendor(vendorId);
    }

    @PostMapping(path = "/addModel/vendor/{vendorId}")
    public ModelEntity addNewModel (@RequestBody ModelEntity model, @PathVariable Long vendorId){
        return modelService.createModel(model, vendorId);
    }
}
