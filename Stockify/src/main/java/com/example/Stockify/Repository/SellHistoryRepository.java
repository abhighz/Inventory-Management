package com.example.Stockify.Repository;

import com.example.Stockify.Entity.SellHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellHistoryRepository extends MongoRepository<SellHistory, String> {
} 