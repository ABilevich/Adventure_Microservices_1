package com.microservices.inventoryservice.service;

import org.springframework.stereotype.Service;

import com.microservices.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity){
        // Find an inventory for a given skuCode where quantity is greater than or equal to 0
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

}
