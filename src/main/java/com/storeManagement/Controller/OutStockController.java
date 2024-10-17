package com.storeManagement.Controller;

import com.storeManagement.Entity.OutStockEntity;
import com.storeManagement.Service.OutStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://inventory-mgmt-ipssi-rct.netlify.app"})
public class OutStockController {

    @Autowired
    private OutStockService outStockService;

    @GetMapping(path = "/outStocks")
    public List<OutStockEntity> getOutStocks() {
        return outStockService.getAllOutStock();
    }
    @GetMapping(path = "/allOutStocks")
    public List<OutStockEntity>allListoutStocks(){return outStockService.ListAllOutStocks();}

    @GetMapping(path = "/outStocks/{userId}")
    public List<OutStockEntity> getOutStocksByUser(@PathVariable Long userId) {
        return outStockService.getOutStockByUser(userId);
    }

    @GetMapping(path = "/outStock/{id}")
    public Optional<OutStockEntity> getOutStockById(@PathVariable Long id) {
        return outStockService.getOutStockById(id);
    }

    @PostMapping(path = "/addOutStock/{userId}")
    public OutStockEntity addOutstock(@RequestBody OutStockEntity outStock, @PathVariable Long userId) {
        return outStockService.addOutStock(outStock, userId);
    }

    @DeleteMapping(path = "/delOutStock/{id}/{userId}")
    public String deleteOutstock(@PathVariable Long id, @PathVariable Long userId) {
        outStockService.deleteOutStock(id, userId);
        return "successfully deleted";
    }



    @PutMapping(path = "/updateOutStock/{id}/{userId}")
    public OutStockEntity updateOutStock(@PathVariable Long id, @RequestBody OutStockEntity newOutStockDetails, @PathVariable Long userId) {
        return outStockService.updateOutStock(id, newOutStockDetails, userId);
    }
}
