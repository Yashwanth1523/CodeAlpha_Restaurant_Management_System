package com.codealpha.restaurant.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
public record CreateOrderRequest(
 @NotBlank @Size(max=100) String customerName,
 @NotEmpty List<@Valid OrderItemRequest> items
) {}
