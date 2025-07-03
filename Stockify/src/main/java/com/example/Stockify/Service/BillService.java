package com.example.Stockify.Service;

import com.example.Stockify.Entity.Bill;
import com.example.Stockify.Entity.Product;
import com.example.Stockify.Entity.SellHistory;
import com.example.Stockify.Repository.BillRepository;
import com.example.Stockify.Repository.ProductRepository;
import com.example.Stockify.Repository.SellHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellHistoryRepository sellHistoryRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBillById(String id) {
        return billRepository.findById(id);
    }

    // Selling a product and updating stock is handled in this method
    public Bill createBill(List<Bill.BillItem> items) throws Exception {
        double total = 0;
        for (Bill.BillItem item : items) {
            Optional<Product> productOpt = productRepository.findById(item.getProductId());
            if (productOpt.isEmpty()) {
                throw new Exception("Product not found: " + item.getProductId());
            }
            Product product = productOpt.get();
            if (product.getStock() < item.getQuantity()) {
                throw new Exception("Insufficient stock for product: " + product.getName());
            }
            item.setProductName(product.getName());
            item.setPrice(product.getPrice());
            item.setBarcode(product.getBarcode());
            total += product.getPrice() * item.getQuantity();
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
            // Save sell history
            SellHistory history = new SellHistory();
            history.setProductId(product.getId());
            history.setProductName(product.getName());
            history.setQuantity(item.getQuantity());
            history.setPrice(product.getPrice());
            history.setTotal(product.getPrice() * item.getQuantity());
            history.setSoldAt(java.time.LocalDateTime.now());
            sellHistoryRepository.save(history);
        }
        Bill bill = new Bill();
        bill.setItem(items);
        bill.setTotal(total);
        bill.setCreatedAt(LocalDateTime.now());
        return billRepository.save(bill);
    }

    public void deleteBill(String id) {
        billRepository.deleteById(id);
    }

    // Robust method to sell a product, create a bill, and save sell history
    public Bill sellProductAndCreateHistory(String productId, int quantity, double price) throws Exception {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new Exception("Product not found: " + productId);
        }
        Product product = productOpt.get();
        if (product.getStock() < quantity) {
            throw new Exception("Insufficient stock for product: " + product.getName());
        }
        // Update stock
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        // Create bill item
        Bill.BillItem billItem = new Bill.BillItem();
        billItem.setProductId(productId);
        billItem.setProductName(product.getName());
        billItem.setQuantity(quantity);
        billItem.setPrice(price);
        billItem.setBarcode(product.getBarcode());
        // Create bill
        Bill bill = new Bill();
        bill.setItem(List.of(billItem));
        bill.setTotal(price * quantity);
        bill.setCreatedAt(LocalDateTime.now());
        Bill savedBill = billRepository.save(bill);
        // Save sell history
        SellHistory history = new SellHistory();
        history.setProductId(product.getId());
        history.setProductName(product.getName());
        history.setQuantity(quantity);
        history.setPrice(price);
        history.setTotal(price * quantity);
        history.setSoldAt(LocalDateTime.now());
        sellHistoryRepository.save(history);
        return savedBill;
    }
}