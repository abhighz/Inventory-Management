package com.example.Stockify.Controller;

import com.example.Stockify.Entity.ShopSettings;
import com.example.Stockify.Service.ShopSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class ShopSettingsController {

    @Autowired
    private ShopSettingsService shopSettingsService;

    @GetMapping
    public ResponseEntity<ShopSettings> getSettings() {
        return ResponseEntity.ok(shopSettingsService.getShopSettings());
    }

    @PostMapping
    public ResponseEntity<ShopSettings> saveSettings(@RequestBody ShopSettings settings) {
        return ResponseEntity.ok(shopSettingsService.saveShopSettings(settings));
    }
} 