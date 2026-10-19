
package com.codealpha.restaurant.service;

import com.codealpha.restaurant.dto.*;
import com.codealpha.restaurant.model.*;
import com.codealpha.restaurant.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    private final MenuItemRepository menu;
    private final RestaurantOrderRepository orders;

    public RestaurantService(
            MenuItemRepository menu,
            RestaurantOrderRepository orders) {
        this.menu = menu;
        this.orders = orders;
    }

    public MenuItem addMenuItem(MenuItemRequest r) {
        return menu.save(
                new MenuItem(
                        r.name().trim(),
                        r.description().trim(),
                        r.price(),
                        r.stock()
                )
        );
    }

    public List<MenuItem> getMenu() {
        return menu.findAll();
    }

    @Transactional
    public OrderResponse placeOrder(CreateOrderRequest request) {
        RestaurantOrder order =
                new RestaurantOrder(request.customerName().trim());

        for (OrderItemRequest requested : request.items()) {

            MenuItem item = menu.findById(requested.menuItemId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Menu item not found: "
                                            + requested.menuItemId()
                            )
                    );

            if (!item.isAvailable()
                    || item.getStock() < requested.quantity()) {
                throw new ConflictException(
                        "Item unavailable or insufficient stock: "
                                + item.getName()
                );
            }

            item.reduceStock(requested.quantity());

            order.addItem(
                    new OrderLine(item, requested.quantity())
            );
        }

        return toResponse(orders.save(order));
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders() {
        return orders.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long id) {
        RestaurantOrder order = orders.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found: " + id
                        )
                );

        return toResponse(order);
    }

    @Transactional
    public OrderResponse updateStatus(Long id, OrderStatus status) {
        RestaurantOrder order = orders.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found: " + id
                        )
                );

        if (order.getStatus() == OrderStatus.CANCELLED
                || order.getStatus() == OrderStatus.COMPLETED) {
            throw new ConflictException(
                    "A completed or cancelled order cannot be changed"
            );
        }

        if (status == OrderStatus.CANCELLED) {
            for (OrderLine line : order.getItems()) {
                MenuItem item = line.getMenuItem();
                item.addStock(line.getQuantity());
            }
        }

        order.setStatus(status);

        return toResponse(order);
    }

    private OrderResponse toResponse(RestaurantOrder order) {
        List<OrderLineResponse> lines = order.getItems()
                .stream()
                .map(line -> new OrderLineResponse(
                        line.getMenuItem().getId(),
                        line.getMenuItem().getName(),
                        line.getQuantity(),
                        line.getUnitPrice(),
                        line.getLineTotal()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                lines
        );
    }
}
