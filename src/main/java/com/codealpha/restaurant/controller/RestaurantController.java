package com.codealpha.restaurant.controller;

import com.codealpha.restaurant.dto.*;
import com.codealpha.restaurant.model.*;
import com.codealpha.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {
    private final RestaurantService service;
    public RestaurantController(RestaurantService service){this.service=service;}

    @PostMapping("/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItem addMenuItem(@Valid @RequestBody MenuItemRequest request){return service.addMenuItem(request);}

    @GetMapping("/menu")
    public List<MenuItem> getMenu(){return service.getMenu();}

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@Valid @RequestBody CreateOrderRequest request){return service.placeOrder(request);}

    @GetMapping("/orders")
    public List<OrderResponse> getOrders(){return service.getOrders();}

    @GetMapping("/orders/{id}")
    public OrderResponse getOrder(@PathVariable Long id){return service.getOrder(id);}

    @PatchMapping("/orders/{id}/status")
    public OrderResponse updateStatus(@PathVariable Long id, @RequestParam OrderStatus status){
        return service.updateStatus(id, status);
    }
}
