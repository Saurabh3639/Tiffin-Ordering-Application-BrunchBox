package com.tiffin.app.AdminFoodPanel;

public class AdminPaymentOrders {

    private String AdminId,DishId,DishName,DishPrice,DishQuantity,RandomUID,TotalPrice,UserId;

    public AdminPaymentOrders(String adminId, String dishId, String dishName, String dishPrice, String dishQuantity, String randomUID, String totalPrice, String userId) {
        AdminId = adminId;
        DishId = dishId;
        DishName = dishName;
        DishPrice = dishPrice;
        DishQuantity = dishQuantity;
        RandomUID = randomUID;
        TotalPrice = totalPrice;
        UserId = userId;
    }

    public AdminPaymentOrders()
    {

    }

    public String getAdminId() {
        return AdminId;
    }

    public void setAdminId(String adminId) {
        AdminId = adminId;
    }

    public String getDishId() {
        return DishId;
    }

    public void setDishId(String dishId) {
        DishId = dishId;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public String getDishPrice() {
        return DishPrice;
    }

    public void setDishPrice(String dishPrice) {
        DishPrice = dishPrice;
    }

    public String getDishQuantity() {
        return DishQuantity;
    }

    public void setDishQuantity(String dishQuantity) {
        DishQuantity = dishQuantity;
    }

    public String getRandomUID() {
        return RandomUID;
    }

    public void setRandomUID(String randomUID) {
        RandomUID = randomUID;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}