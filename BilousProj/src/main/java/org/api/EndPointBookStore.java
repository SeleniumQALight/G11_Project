package org.api;

public interface EndPointBookStore {
    String BASE_URL = "https://demoqa.com";
    String UserId = BASE_URL + "/Account/v1/Login";
    String ACTION_WITH_BOOKS = BASE_URL + "/BookStore/v1/Books";
    String BOOKS_FOR_USER = BASE_URL + "/Account/v1/User/{0}";

}
