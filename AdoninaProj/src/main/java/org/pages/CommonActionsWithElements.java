package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.elementName;

public class CommonActionsWithElements {
  protected WebDriver webDriver;
  private Logger logger = Logger.getLogger(getClass());

  public CommonActionsWithElements(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(webDriver, this); // ініціалізує елементи описані FindBy
  }

  protected void clearAndEnterTextIntoInput(WebElement webElement, String text) {
    try {
      webElement.clear();
      webElement.sendKeys(text);
      logger.info(text + " was inputted into element " + getElementName(webElement));
    } catch (Exception e) {
      printErrorAndStopTest(e);

    }
  }

  private String getElementName(WebElement webElement) {
    String elementName;
    try {
      elementName = webElement.getAccessibleName();
    } catch (Exception e) {
      elementName = "";
    }
    return elementName;
  }

  protected void clickOnElement(WebElement webElement) {
    try {
      String elementName = getElementName(webElement);
      webElement.click();
      logger.info(elementName + " was clicked");
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  protected void clickOnElement(WebElement webElement, String elementName) {
    try {
      webElement.click();
      logger.info(elementName + " was clicked");
    } catch (Exception e) {
      logger.error("Can not work with element " + elementName);
      printErrorAndStopTest(e);
    }
  }

  protected boolean isElementVisible(WebElement webElement) {
    try {
      boolean state = webElement.isDisplayed();
      if (state) {
        logger.info(getElementName(webElement) + " is visible");
      } else {
        logger.info(getElementName(webElement) + " is not visible");
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

  //checkTextInElement
  protected void checkTextInElement(WebElement webElement, String text) {
    Assert.assertEquals("Text in element " + getElementName(webElement) + "not expected", text, webElement.getText());
    logger.info("Text in element" + getElementName(webElement) + " is expected");
  }

  private void printErrorAndStopTest(Exception e) {
    logger.error("Can not work with element " + e);
    Assert.fail("Can not work with element " + e);
  }
}
