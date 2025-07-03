package com.example.Stockify.Service;

import com.example.Stockify.Entity.ShopSettings;
import com.example.Stockify.Repository.ShopSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopSettingsService {
    @Autowired
    private ShopSettingsRepository shopSettingsRepository;

    private static final String SETTINGS_ID = "main_settings";

    public ShopSettings getShopSettings() {
        return shopSettingsRepository.findById(SETTINGS_ID).orElse(new ShopSettings());
    }

    public ShopSettings saveShopSettings(ShopSettings settings) {
        settings.setId(SETTINGS_ID);
        return shopSettingsRepository.save(settings);
    }
} 