package com.example.Stockify.Service;

import com.example.Stockify.Entity.Product;
import com.example.Stockify.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) throws Exception {
        // Check if barcode already exists for a different product
        if (product.getBarcode() != null && !product.getBarcode().trim().isEmpty()) {
            Optional<Product> existingProduct = productRepository.findByBarcode(product.getBarcode());
            if (existingProduct.isPresent() && !existingProduct.get().getId().equals(product.getId())) {
                throw new Exception("Barcode '" + product.getBarcode() + "' is already in use by another product");
            }
        }
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public boolean isBarcodeExists(String barcode, String excludeProductId) {
        if (barcode == null || barcode.trim().isEmpty()) {
            return false;
        }
        Optional<Product> existingProduct = productRepository.findByBarcode(barcode);
        return existingProduct.isPresent() && !existingProduct.get().getId().equals(excludeProductId);
    }
}