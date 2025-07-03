package com.example.Stockify.Repository;

import com.example.Stockify.Entity.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill,String> {
}
