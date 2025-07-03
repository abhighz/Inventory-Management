package com.example.Stockify.Controller;

import com.example.Stockify.Entity.SellHistory;
import com.example.Stockify.Service.SellHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sell-history")
public class SellHistoryController {
    @Autowired
    private SellHistoryService sellHistoryService;

    @GetMapping
    public List<SellHistory> getAllSellHistory() {
        return sellHistoryService.getAllSellHistory();
    }
} 