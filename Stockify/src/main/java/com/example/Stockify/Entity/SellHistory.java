package com.example.Stockify.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "sell_history")
@Data
public class SellHistory {
    @Id
    private String id;
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private double total;
    private LocalDateTime soldAt;
} 