package com.codealpha.restaurant.repository;
import com.codealpha.restaurant.model.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder, Long> {}
