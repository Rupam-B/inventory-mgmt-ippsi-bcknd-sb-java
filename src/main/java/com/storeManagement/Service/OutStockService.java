package com.storeManagement.Service;

import com.storeManagement.Entity.OutStockEntity;
import com.storeManagement.Repository.OutStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutStockService {

    @Autowired
    OutStockRepository outStockRepository;

    public OutStockEntity addOutStock(OutStockEntity outStockEntity) {
        return outStockRepository.save(outStockEntity);
    }

    public List<OutStockEntity> getAllOutStock() {
        return outStockRepository.findAll();
    }

    public String deleteOutStock(Long id) {
        outStockRepository.deleteById(id);
        return "Removed OutStock with id: " + id;
    }
}
