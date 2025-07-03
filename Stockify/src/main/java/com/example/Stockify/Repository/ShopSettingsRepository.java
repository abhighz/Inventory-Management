package com.example.Stockify.Repository;

import com.example.Stockify.Entity.ShopSettings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopSettingsRepository extends MongoRepository<ShopSettings, String> {
} 