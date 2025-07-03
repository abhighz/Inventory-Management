package com.example.Stockify.Service;

import com.example.Stockify.Entity.SellHistory;
import com.example.Stockify.Repository.SellHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellHistoryService {
    @Autowired
    private SellHistoryRepository sellHistoryRepository;

    public List<SellHistory> getAllSellHistory() {
        return sellHistoryRepository.findAll();
    }
} 