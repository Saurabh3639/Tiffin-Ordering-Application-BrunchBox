package com.tiffin.app.AdminFoodPanel;

public class AdminFinalOrders1 {

    private String Address,GrandTotalPrice,MobileNumber,Name,RandomUID,Status;

    //adding time, from date and to date- create getter and setter
    private String Time, FromDate, ToDate;

    public AdminFinalOrders1(String address, String grandTotalPrice, String mobileNumber, String name, String randomUID,
                             String status, String time, String fromDate, String toDate) {
        Address = address;
        GrandTotalPrice = grandTotalPrice;
        MobileNumber = mobileNumber;
        Name = name;
        RandomUID = randomUID;
        Status = status;

        //time, from date and to date
        Time = time;
        FromDate = fromDate;
        ToDate = toDate;
    }

    public AdminFinalOrders1()
    {

    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGrandTotalPrice() {
        return GrandTotalPrice;
    }

    public void setGrandTotalPrice(String grandTotalPrice) {
        GrandTotalPrice = grandTotalPrice;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRandomUID() {
        return RandomUID;
    }

    public void setRandomUID(String randomUID) {
        RandomUID = randomUID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    //getter setter of time, to date ane from date
    public String getTime() { return Time; }

    public void setTime(String time) { Time = time; }

    public String getFromDate() { return FromDate; }

    public void setFromDate(String fromDate) { FromDate = fromDate; }

    public String getToDate() { return ToDate; }

    public void setToDate(String toDate) { ToDate = toDate; }
}