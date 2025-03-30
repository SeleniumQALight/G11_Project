package org.api;

public interface EndPointsBooks {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String USER_WITH_ID = BASE_URL + "/Account/v1/User/{0}";
    String BOOKS = BASE_URL + "/BookStore/v1/Books";
}
