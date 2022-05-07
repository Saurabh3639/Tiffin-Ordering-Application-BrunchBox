package com.tiffin.app.CustomerFoodPanel;

public class CustomerPendingOrders {

    //fetch values of CustomerPendingOrders table
    private String AdminId, DishID, DishName, DishQuantity, Price, TotalPrice;

    public CustomerPendingOrders(String dishID, String dishName, String dishQuantity, String price, String totalPrice,
                                 String adminId) {
        AdminId = adminId;
        DishID = dishID;
        DishName = dishName;
        DishQuantity = dishQuantity;
        Price = price;
        TotalPrice = totalPrice;

    }

    public CustomerPendingOrders() {

    }

    public String getAdminId() {
        return AdminId;
    }

    public void setAdminId(String adminId) {
        AdminId = adminId;
    }

    public String getDishID() {
        return DishID;
    }

    public void setDishID(String dishID) {
        DishID = dishID;
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

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

}
