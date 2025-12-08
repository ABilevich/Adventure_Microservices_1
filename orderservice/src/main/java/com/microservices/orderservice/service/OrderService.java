package com.microservices.orderservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.microservices.orderservice.client.InventoryClient;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if(isProductInStock){
            // map OrderRequest to Order entity
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            // save Order to orderRepository
            orderRepository.save(order);
        }else{
            throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
