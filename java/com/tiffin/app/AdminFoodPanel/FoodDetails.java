package com.tiffin.app.AdminFoodPanel;

public class FoodDetails {

    public String Dishes,Quantity,Price,Description,ImageURL,RandomUID,AdminId;

    public FoodDetails(String dishes, String quantity, String price, String description, String imageURL,String randomUID,String adminId) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID=randomUID;
        AdminId=adminId;
    }

}
