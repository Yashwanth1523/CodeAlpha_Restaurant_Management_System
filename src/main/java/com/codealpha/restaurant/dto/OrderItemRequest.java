package com.codealpha.restaurant.dto;
import jakarta.validation.constraints.*;
public record OrderItemRequest(@NotNull @Positive Long menuItemId, @NotNull @Min(1) @Max(100) Integer quantity) {}
