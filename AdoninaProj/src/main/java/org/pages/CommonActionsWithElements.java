package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;

import java.time.Duration;

public class CommonActionsWithElements {
  protected WebDriver webDriver;
  private Logger logger = Logger.getLogger(getClass());
  protected WebDriverWait webDriverWait10, webDriverWait15;

  public CommonActionsWithElements(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(webDriver, this); // ініціалізує елементи описані FindBy
    webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
    webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
  }

  // method for select visible text in dropdown
  protected void selectTextInDD(WebElement dropDownElement, String textForSelect) {
    try {
      Select optionsFromDD = new Select(dropDownElement);
      optionsFromDD.selectByVisibleText(textForSelect);
      logger.info(textForSelect + " was selected in DropDown " + getElementName(dropDownElement));
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  // select value in dropdown
  protected void selectValueInDD(WebElement dropDownElement, String valueInDD) {
    try {
      Select optionsFromDD = new Select(dropDownElement);
      optionsFromDD.selectByValue(valueInDD);
      logger.info(valueInDD + " value was selected in DropDown " + getElementName(dropDownElement));
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
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
      webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
      String elementName = getElementName(webElement);
      webElement.click();
      logger.info(elementName + " was clicked");
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  protected void clickOnElement(WebElement webElement, String elementName) {
    try {
      webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
      webElement.click();
      logger.info(elementName + " was clicked");
    } catch (Exception e) {
      logger.error("Can not work with element " + elementName);
      printErrorAndStopTest(e);
    }
  }

  protected boolean isElementVisible(String locator) {
    try {
      return isElementVisible(webDriver.findElement(By.xpath(locator)));
    } catch (Exception e) {
      logger.info("Element is not found");
      return false;
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

  protected void checkIsElementVisible(String locator) {
    Assert.assertTrue("Element is not visible", isElementVisible(locator));
  }

  protected void checkIsElementNotVisible(WebElement webElement) {
    Assert.assertFalse("Element is visible", isElementVisible(webElement));
  }

  //checkTextInElement
  protected void checkTextInElement(WebElement webElement, String text) {
    Assert.assertEquals("Text in element " + getElementName(webElement) + "not expected", text, webElement.getText());
    logger.info("Text in element" + getElementName(webElement) + " is expected");
  }

  protected void acceptAlert() {
    try {
      webDriverWait10.until(ExpectedConditions.alertIsPresent());
      webDriver.switchTo().alert().accept();
      logger.info("Alert was accepted");
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  protected void scrollToElement(WebElement webElement) {
    try {
      Actions actions = new Actions(webDriver);
      actions.moveToElement(webElement);
      actions.perform();
      logger.info("Scroll to element " + getElementName(webElement));
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  protected void openNewTab() {
    try {
      ((JavascriptExecutor) webDriver).executeScript("window.open()");
      logger.info("New tab was opened");
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  private void printErrorAndStopTest(Exception e) {
    logger.error("Can not work with element " + e);
    Assert.fail("Can not work with element " + e);
  }

  protected void selectCheckbox(WebElement checkbox) {
    try {
      if (!checkbox.isSelected()) {
        clickOnElement(checkbox);
        logger.info("Checkbox was selected");
      } else {
        logger.info("Checkbox is already selected");
      }
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  protected void unselectCheckbox(WebElement checkbox) {
    try {
      if (checkbox.isSelected()) {
        clickOnElement(checkbox);
        logger.info("Checkbox was deselected");
      } else {
        logger.info("Checkbox is already deselected");
      }
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }

  public void setCheckboxState(WebElement checkbox, String state) {
    try {
      if ("check".equalsIgnoreCase(state)) {
        selectCheckbox(checkbox);
      } else if ("uncheck".equalsIgnoreCase(state)) {
        selectCheckbox(checkbox);
      } else {
        logger.error("Invalid state: " + state);
      }
    } catch (Exception e) {
      printErrorAndStopTest(e);
    }
  }
}
