package com.codealpha.restaurant.dto;
public record OrderLineResponse(Long menuItemId, String itemName, Integer quantity, Double unitPrice, Double lineTotal) {}
