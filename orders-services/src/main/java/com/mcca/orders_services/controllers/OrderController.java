package com.mcca.orders_services.controllers;


import com.mcca.orders_services.model.dtos.OrderRequest;
import com.mcca.orders_services.services.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        this.orderServices.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}
