package com.example.Stockify.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "bills")
@Data
public class Bill {

    @Id
    private String id;
    private List<BillItem> item;
    private double total;
    private LocalDateTime createdAt;

    @Data
    public static class BillItem{
        private String productId;
        private String productName;
        private double price;
        private int quantity;
        private String barcode;

    }
}
