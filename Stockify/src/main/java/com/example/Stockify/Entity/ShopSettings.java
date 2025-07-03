package com.example.Stockify.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shop_settings")
@Data
public class ShopSettings {
    @Id
    private String id;
    private String shopName;
    private String address;
    private String phone;
    private String email;
    private String gstin;
} 