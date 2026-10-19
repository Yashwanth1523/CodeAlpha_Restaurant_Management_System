package com.codealpha.restaurant.dto;
import com.codealpha.restaurant.model.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
public record OrderResponse(Long id, String customerName, Double totalAmount, OrderStatus status,
 LocalDateTime createdAt, List<OrderLineResponse> items) {}
