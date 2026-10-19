# CodeAlpha Backend Project: Restaurant Management System

REST API for menu management, order processing, inventory stock, and order status using Java 17, Spring Boot, JPA, and MySQL.

## Features
- Add and list menu items
- Place an order containing one or more menu items
- Calculate order total from current menu prices
- Reduce stock when an order is placed
- Reject orders when stock is insufficient
- View all orders or one order
- Update order status (PLACED, PREPARING, READY, COMPLETED, CANCELLED)
- Restore stock when an order is cancelled
- Validation and structured error responses

## Requirements
JDK 17+, Maven 3.6+, MySQL 8+, Postman.

## Setup
1. Create database `codealpha_restaurant` or let the JDBC URL create it.
2. Edit `src/main/resources/application.properties` and set your MySQL password.
3. Run `mvn spring-boot:run`.
4. Base URL: `http://localhost:8080`.

## API examples

### Add menu item
`POST /api/menu`
```json
{
  "name": "Veg Burger",
  "description": "Burger with vegetables and cheese",
  "price": 120.0,
  "stock": 30
}
```

### View menu
`GET /api/menu`

### Place order
`POST /api/orders`
```json
{
  "customerName": "Asha",
  "items": [
    {"menuItemId": 1, "quantity": 2}
  ]
}
```

### View all orders
`GET /api/orders`

### View one order
`GET /api/orders/1`

### Change order status
`PATCH /api/orders/1/status?status=PREPARING`

Allowed statuses: `PLACED`, `PREPARING`, `READY`, `COMPLETED`, `CANCELLED`. Cancelling an order restores the stock for its items.

## Before submission
- This is a backend API project; it does not include a frontend or authentication.
- Set your own local database password and never commit credentials.
- Test all endpoints in Postman before submitting.
- Be ready to explain controllers, service logic, JPA entities, repositories, and inventory updates.
