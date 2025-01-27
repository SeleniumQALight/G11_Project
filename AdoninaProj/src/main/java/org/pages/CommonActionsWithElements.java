package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
  WebDriver webDriver;
  private Logger logger = Logger.getLogger(getClass());

  public CommonActionsWithElements(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(webDriver, this); // ініціалізує елементи описані FindBy
  }

  protected void clearAndEnterTextIntoInput(WebElement webElement, String text) {
    try {
      webElement.clear();
      webElement.sendKeys(text);
      logger.info(text + " was inputted into input");
    } catch (Exception e) {
      printErrorAndStopTest(e);

    }
  }

  protected void clickOnElement(WebElement webElement) {
    try {
      webElement.click();
      logger.info("Element was clicked");
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  protected boolean isElementVisible(WebElement webElement) {
    try {
      boolean state = webElement.isDisplayed();
      if (state) {
        logger.info("Element is visible");
      } else {
        logger.info("Element is not visible");
      }
      return state;
    } catch (Exception e) {
      logger.info("Element is not found");
      return false;
    }
  }

  protected void checkIsElementVisible(WebElement webElement) {
    Assert.assertTrue("Element is not visible", isElementVisible(webElement));
  }

  protected void checkIsElementNotVisible(WebElement webElement) {
    Assert.assertFalse("Element is visible", isElementVisible(webElement));
  }

  //checkTextInElement
  protected void checkTextInElement(WebElement webElement, String text) {
    Assert.assertEquals("Text in element not expected", text, webElement.getText());
    logger.info("Text in element is expected");
  }

  private void printErrorAndStopTest(Exception e) {
    logger.error("Can not work with element " + e);
    Assert.fail("Can not work with element " + e);
  }
}
