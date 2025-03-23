package org.apiTests;

import org.api.ApiHelperBookStore;
import org.api.dto.responseDTO.BookDTO;
import org.api.dto.responseDTO.LoginResponseDTO;
import org.api.dto.responseDTO.UserBooksDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BookStoreApiTests {
    ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();
    private String token;
    private String userId;

    @Before
    public void getTokenAndDeleteAllBooks() {
        LoginResponseDTO loginResponse = apiHelperBookStore.loginUser("tanyache", "Tanita1122!".toLowerCase());

        token = loginResponse.getToken();
        userId = loginResponse.getUserId();

        apiHelperBookStore.deleteAllBooks(userId, token);
    }

    @Test
    public void addBookToUserTest() {
        // Отримую перший ISBN
        String isbn = apiHelperBookStore.getFirstBookIsbn();
        // Додаю книгу
        apiHelperBookStore.addBookToUser(userId, token, isbn);
        // Перевіряю, що книга додана
        UserBooksDTO userBooks = apiHelperBookStore.getUserBooks(userId, token);
        Assert.assertEquals("Кількість книг у користувача повинна бути 1", 1, userBooks.getBooks().size());
        Assert.assertEquals("ISBN книги має співпадати", isbn, userBooks.getBooks().get(0).getIsbn());


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(userBooks.getBooks().get(0))
                .usingRecursiveComparison()
                .ignoringFields("title", "subTitle", "author", "publish_date",
                        "publisher", "pages", "description", "website")
                .isEqualTo(BookDTO.builder().isbn(isbn).build());
        softAssertions.assertAll();
    }


}
