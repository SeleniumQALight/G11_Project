package org.apiTests;

import org.apache.log4j.Logger;
import org.api.ApiHelperBooks;
import org.api.dto.responseDTO.AllBooksDTO;
import org.api.dto.responseDTO.UserDTO;
import org.data.TestDataBooks;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ApiTestsBooks {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    String token;
    String userId;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void getTokenAndUserIdAndDeleteExistingBooks() {
        apiHelperBooks.getLoginResponse(TestDataBooks.USER_NAME_BOOKS, TestDataBooks.PASSWORD_BOOKS);
        token = apiHelperBooks.getTokenBooks();
        userId = apiHelperBooks.getUserId();
        apiHelperBooks.deleteAllBooksByUser(userId, token);
    }

    @Test
    public void testAddBookToUser() {
        int numberOfBooksBefore = apiHelperBooks.getListOfBooksByUserId(token, userId).getBooks().size();
        logger.info("User had " + numberOfBooksBefore + " books before adding " + "\n"
                + "-----------------------------------------------------------" + "\n");

        String isbnOfFirstBook;
        UserDTO userDTO = new UserDTO();
        AllBooksDTO allBooksDTO = new AllBooksDTO();
        allBooksDTO.setBooks(apiHelperBooks.getListOfBooks().getBooks());
        isbnOfFirstBook = allBooksDTO.getBooks().get(0).getIsbn();

        apiHelperBooks.postNewBookInUserList(token, userId, isbnOfFirstBook);

        userDTO.setBooks(apiHelperBooks.getListOfBooksByUserId(token, userId).getBooks());
        String isbnBookByUser = userDTO.getBooks().get(0).getIsbn();

        int numberOfBooksAfter = apiHelperBooks.getListOfBooksByUserId(token, userId).getBooks().size();
        logger.info("User has now " + numberOfBooksAfter + " book after adding " + "\n"
                + "-----------------------------------------------------------" + "\n");
        logger.info("User's book has ISBN =         " + isbnBookByUser);
        logger.info("First book in store had ISBN = " + isbnOfFirstBook);

        Assert.assertEquals("Number of books after adding  ",
                numberOfBooksBefore + 1, numberOfBooksAfter);

        Assert.assertEquals("ISBN of user's book ", isbnOfFirstBook, isbnBookByUser);

    }
}
