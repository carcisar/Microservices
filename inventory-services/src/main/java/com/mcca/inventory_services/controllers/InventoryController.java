package com.mcca.inventory_services.controllers;


import com.mcca.inventory_services.model.dtos.BaseResponse;
import com.mcca.inventory_services.model.dtos.OrderItemRequest;
import com.mcca.inventory_services.repositories.InventoryRepository;
import com.mcca.inventory_services.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku") String sku){
        return inventoryService.isInStock(sku);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse areInStock(@RequestBody List<OrderItemRequest> orderItem){
        return inventoryService.areInStock(orderItem);

    }
}
