package com.storeManagement.Controller;


import com.storeManagement.DTOs.StockCreateDTO;
import com.storeManagement.DTOs.StockResponseDTO;
import com.storeManagement.Entity.StockEntity;
import com.storeManagement.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = {"http://localhost:4200", "https://store-mgmt-ang-sbjava.netlify.app","http://localhost:3000"})
public class StockController {

    @Autowired
    private StockService stockService;

    // Endpoint to add new stock
    @PostMapping("/add")
    public StockResponseDTO addStock(@RequestBody StockCreateDTO stock) {
        return stockService.addStock(stock);
    }


    // Endpoint to get all stock
    @GetMapping("/all")
    public List<StockResponseDTO> getAllStocks() {
        return stockService.getAllStock();
    }

    // Endpoint to get stock by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<StockEntity>> getStockById(@PathVariable Long id) {
        Optional<StockEntity> stock = stockService.getStockById(id);
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/user/{userId}")
    public List<StockResponseDTO> getStocksByUserId(@PathVariable Long userId) {
        return stockService.getStocksByUserId(userId);
    }

    @GetMapping("/search-stock/{serialNumber}")
    public StockResponseDTO getStocksByUserId(@PathVariable String serialNumber) {
        return stockService.getStockBySerialNumber(serialNumber);
    }

    // Endpoint to update stock
//    @PutMapping("/update/{id}")
//    public ResponseEntity<StockEntity> updateStock(@PathVariable Long id, @RequestBody StockEntity stock) {
//        StockEntity updatedStock = stockService.updateStock(id, stock);
//        return ResponseEntity.ok(updatedStock);
//    }

    // Endpoint to delete stock by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
