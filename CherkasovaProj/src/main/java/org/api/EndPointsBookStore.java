package org.api;

public interface EndPointsBookStore {

        String BASE_URL = "https://demoqa.com";

        String LOGIN = BASE_URL + "/Account/v1/Login";
        String USER_BOOKS = BASE_URL + "/Account/v1/User/{userId}";
        String GET_ALL_BOOKS = BASE_URL + "/BookStore/v1/Books";
        String ADD_BOOK = BASE_URL + "/BookStore/v1/Books";
        String DELETE_BOOKS = BASE_URL + "/BookStore/v1/Books?UserId={userId}";

}
