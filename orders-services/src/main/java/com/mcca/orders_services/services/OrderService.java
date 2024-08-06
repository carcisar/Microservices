package com.mcca.orders_services.services;


import com.mcca.orders_services.model.dtos.BaseResponse;
import com.mcca.orders_services.model.dtos.OrderItemRequest;
import com.mcca.orders_services.model.dtos.OrderRequest;
import com.mcca.orders_services.model.entities.OrderItems;
import com.mcca.orders_services.model.entities.Orders;
import com.mcca.orders_services.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder (OrderRequest orderRequest) {

        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if (result != null && !result.hasErrors()) {
            Orders order = new Orders();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream()
                    .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                    .toList());
            this.orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Some of the products are not in stock");
        }


        Orders order = new Orders();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderItems(orderRequest.getOrderItems().stream()
                .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                .toList());
        this.orderRepository.save(order);
    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemsRequest, Orders order) {
        return OrderItems.builder()
                .id(orderItemsRequest.getId())
                .sku(orderItemsRequest.getSku())
                .price(orderItemsRequest.getPrice())
                .price(orderItemsRequest.getPrice())
                .quantity(orderItemsRequest.getQuantity())
                .order(order)
                .build();
    }

}
