package com.example.Stockify.Repository;

import com.example.Stockify.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<Product> findByBarcode(String barcode);
}
