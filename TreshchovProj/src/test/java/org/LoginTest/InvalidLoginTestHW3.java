package org.LoginTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN;

public class InvalidLoginTestHW3 extends BaseTest {

    @Test
    public void HW3_validLogin() {
        pageProvider.getLoginPage().openPage().
                enterTextIntoInputLogin(VALID_LOGIN).
                enterTextIntoInputPassword("123456asd").
                clickOnButtonSignInWithInvalidData().
                checkIsButtonSignInVisible().
                checkIsButtonSignOutVisible().
                checkIsErrorMessageVisible();
    }
}
