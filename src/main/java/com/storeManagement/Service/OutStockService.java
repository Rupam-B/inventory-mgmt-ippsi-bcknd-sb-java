package com.storeManagement.Service;

import com.storeManagement.Entity.OutStockEntity;
import com.storeManagement.Entity.UserEntity;
import com.storeManagement.Repository.OutStockRepository;
import com.storeManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutStockService {

    @Autowired
    private OutStockRepository outStockRepository;

    @Autowired
    private UserRepository userRepository;

    // Save OutStock
    public OutStockEntity addOutStock(OutStockEntity outStockEntity, Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            outStockEntity.setUser(user.get());
            return outStockRepository.save(outStockEntity);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Get all OutStock
    public List<OutStockEntity> getAllOutStock() {
        return outStockRepository.findAll();
    }

    public List<OutStockEntity> ListAllOutStocks(){return outStockRepository.allOutstocks();}

    // Get OutStock by ID
    public Optional<OutStockEntity> getOutStockById(Long id) {
        return outStockRepository.findById(id);
    }

    // Get OutStock by user ID
    public List<OutStockEntity> getOutStockByUser(Long userId) {
        return outStockRepository.findByUser_UserId(userId);
    }

    // Delete OutStock by ID
    public String deleteOutStock(Long id, Long userId) {
        Optional<OutStockEntity> outStockEntity = outStockRepository.findById(id);
        if (outStockEntity.isPresent() && outStockEntity.get().getUser().getUserId().equals(userId)) {
            outStockRepository.deleteById(id);
            return "Removed OutStock with id: " + id;
        } else {
            throw new RuntimeException("OutStock not found or you do not have permission to delete this OutStock.");
        }
    }

    // Update OutStock by ID
    public OutStockEntity updateOutStock(Long id, OutStockEntity newOutStockDetails, Long userId) {
        return outStockRepository.findById(id)
                .map(outStockEntity -> {
                    if (!outStockEntity.getUser().getUserId().equals(userId)) {
                        throw new RuntimeException("You do not have permission to update this OutStock.");
                    }
                    outStockEntity.setOutStockName(newOutStockDetails.getOutStockName());
                    outStockEntity.setOutStockCategory(newOutStockDetails.getOutStockCategory());
                    outStockEntity.setOutStockDescription(newOutStockDetails.getOutStockDescription());
                    return outStockRepository.save(outStockEntity);
                })
                .orElseThrow(() -> new RuntimeException("OutStock not found"));
    }
}
