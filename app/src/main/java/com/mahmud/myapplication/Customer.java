package com.mahmud.myapplication;

public class Customer {

    String UserName, UserEmail;

    public Customer(){

    }

    public Customer(String userName, String userEmail) {
        UserName = userName;
        UserEmail = userEmail;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }
}
