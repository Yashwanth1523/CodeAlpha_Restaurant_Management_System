package com.codealpha.restaurant.repository;
import com.codealpha.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {}
