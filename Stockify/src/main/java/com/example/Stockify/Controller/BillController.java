package com.example.Stockify.Controller;

import com.example.Stockify.Entity.Bill;
import com.example.Stockify.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable String id) {
        Optional<Bill> bill = billService.getBillById(id);
        return bill.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createBill(@RequestBody Map<String, Object> request) {
        try {
            String productId = (String) request.get("productId");
            int quantity = (int) request.get("quantity");
            double sellingPrice = Double.parseDouble(request.get("sellingPrice").toString());
            
            Bill createdBill = billService.sellProductAndCreateHistory(productId, quantity, sellingPrice);
            return ResponseEntity.ok(createdBill);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellProduct(@RequestBody Map<String, Object> payload) {
        try {
            String productId = (String) payload.get("productId");
            int quantity = (int) payload.get("quantity");
            double price = Double.parseDouble(payload.get("price").toString());
            Bill bill = billService.sellProductAndCreateHistory(productId, quantity, price);
            return ResponseEntity.ok(bill);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable String id) {
        if (billService.getBillById(id).isPresent()) {
            billService.deleteBill(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}