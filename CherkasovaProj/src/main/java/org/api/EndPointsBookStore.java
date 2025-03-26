package org.api;

public interface EndPointsBookStore {

        String BASE_URL = "https://demoqa.com";

        String LOGIN = BASE_URL + "/Account/v1/Login";
        String USER_BOOKS = BASE_URL + "/Account/v1/User/{0}";
        String BOOKS = BASE_URL + "/BookStore/v1/Books";

}
