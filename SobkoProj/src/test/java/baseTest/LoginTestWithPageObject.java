package baseTest;

import org.junit.Test;

import static org.Data.TestData.VALID_LOGIN;
import static org.Data.TestData.VALID_PASSWORD;

public class LoginTestWithPageObject extends BaseTest {
    //test case for valid login
    @Test
    public void T0001_validLogin() {
        pageProvider.geLoginPage()
                .openPage()
                .enterTextIntoInputLogin(VALID_LOGIN)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }

}
