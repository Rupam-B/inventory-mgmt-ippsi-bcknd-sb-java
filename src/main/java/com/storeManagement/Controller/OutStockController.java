package com.storeManagement.Controller;

import com.storeManagement.Entity.OutStockEntity;
import com.storeManagement.Service.OutStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://store-mgmt-ang-sbjava.netlify.app"})
public class OutStockController {

    @Autowired
    OutStockService outStockService;

    @GetMapping(path = "/outStocks")
    public List<OutStockEntity> getOutStocks() {
        return outStockService.getAllOutStock();
    }

    @PostMapping(path = "/addOutStock")
    public OutStockEntity addOutstock(@RequestBody OutStockEntity outStock) {
        return outStockService.addOutStock(outStock);
    }

    @DeleteMapping(path = "/delOutStock/{id}")
    public String deleteOutstock(@PathVariable Long id) {
        outStockService.deleteOutStock(id);
        return "successfully deleted";
    }
}
