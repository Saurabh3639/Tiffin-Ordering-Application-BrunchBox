package com.tiffin.app.AdminFoodPanel;

public class AdminPendingOrders {

    //fetch values of AdminPendingOrders table
    private String AdminId,DishId,DishName,DishQuantity,Price,RandomUID,TotalPrice,UserId;

    public AdminPendingOrders(String adminId, String dishId, String dishName, String dishQuantity, String price, String randomUID, String totalPrice, String userId ) {
        AdminId = adminId;
        DishId = dishId;
        DishName = dishName;
        DishQuantity = dishQuantity;
        Price = price;
        RandomUID=randomUID;
        TotalPrice = totalPrice;
        UserId = userId;

    }

    public AdminPendingOrders()
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

    public String getDishQuantity() {
        return DishQuantity;
    }

    public void setDishQuantity(String dishQuantity) {
        DishQuantity = dishQuantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
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
