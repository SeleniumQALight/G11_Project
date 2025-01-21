package baseTest;

import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    //test case for valid login
    @Test
    public void T0001_validLogin() {
        pageProvider.geLoginPage().openPage();
        pageProvider.geLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.geLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.geLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }

}
