package com.codealpha.restaurant.dto;
import jakarta.validation.constraints.*;
public record MenuItemRequest(
 @NotBlank @Size(max=120) String name,
 @NotBlank @Size(max=500) String description,
 @NotNull @DecimalMin("0.01") Double price,
 @NotNull @Min(0) Integer stock
) {}
