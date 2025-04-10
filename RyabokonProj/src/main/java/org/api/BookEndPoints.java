package org.api;

public interface BookEndPoints {


    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String OPERATIONS_BOOKS = BASE_URL + "/BookStore/v1/Books";
    String USER_BOOKS = BASE_URL + "/Account/v1/User/{0}";

}
