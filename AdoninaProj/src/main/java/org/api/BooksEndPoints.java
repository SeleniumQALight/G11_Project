package org.api;

public interface BooksEndPoints {
  String BASE_URL = "https://demoqa.com";
  String LOGIN = BASE_URL + "/Account/v1/Login";
  String DELETE_ALL_BOOKS = BASE_URL + "/BookStore/v1/Books";
  String BOOKS = BASE_URL + "/BookStore/v1/Books";
  String GET_BOOKS_OF_USER = BASE_URL + "/Account/v1/User/{0}";
}
