package org.api;

public interface BooksEndPoints {
    final String BASE_URL = "https://demoqa.com";
    final String LOGIN = BASE_URL + "/Account/v1/Login";
    final String DELETE_BOOKS = BASE_URL + "/BookStore/v1/Books?UserId={0}";
    final String BOOKS = BASE_URL + "/BookStore/v1/Books";
    final String USER_CHECK = BASE_URL + "/Account/v1/User/{0}";
}
