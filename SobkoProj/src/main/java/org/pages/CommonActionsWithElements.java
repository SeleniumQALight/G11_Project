package org.pages;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {

    protected WebDriver webdriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webdriver) {

        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this); // ініціалізує елементи описані FindBy
    }

    //method for select visible text in dropdown
    protected void selectTextInDD(WebElement dropDownElement, String textForSelect) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByVisibleText(textForSelect);
            logger.info(textForSelect + " was selected in DropDown" + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
  //select value in dropdown
    protected void selectValueInDD(WebElement dropDownElement, String valueForSelect) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByValue(valueForSelect);
            logger.info(valueForSelect + " value was selected in DropDown" + getElementName(dropDownElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //method for clearing and entering text into element
    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {

            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into input field" + getElementName(webElement));
        } catch (Exception e) {
            logger.error("Cannot work with element " + e);
            Assert.fail("Cannot work with element " + e);
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

    //method for clicking on element
    protected void clickOnElement(WebElement webElement) {
        try {
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + "Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webElement.click();
            logger.info(elementName + "Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element ");
        Assert.fail("Can not work with element ");
    }


    protected boolean isElementVisible(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info(getElementName(webElement) + "Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    protected boolean isElementVisible(String locator) {
        try {
          return isElementVisible(webdriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }

    // check if element is visible
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementVisible(webElement));
    }
    protected void checkIsElementVisible(String locator) {
        Assert.assertTrue("Element is not visible", isElementVisible(locator));
    }

    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementVisible(webElement));
    }

    protected void checkTextInElement(WebElement webElement, String text) {
        logger.error("Text in element not expected" + getElementName(webElement));
        Assert.assertEquals("Text in element not expected", text, webElement.getText());
        logger.info("Text in element is expected");

    }

    protected void setCheckedOrNotifyIfAlreadyChecked(WebElement webElement) {
        try {
            boolean state = webElement.isSelected();
            if (state) {
                logger.info("Checkbox has already been selected" + getElementName(webElement));
            } else {
                webElement.click();
                logger.info("Checkbox is selected");
            }
        } catch (Exception e) {
            logger.info("Element is not found");
        }
    }

    protected void setUncheckedOrNotifyIfAlreadyClear(WebElement webElement) {
        try {
            boolean state = webElement.isSelected();
            if (state) {
                webElement.click();
                logger.info("Checkbox is cleared");
            } else {
                logger.info("Checkbox is already cleared");
            }
        } catch (Exception e) {
            logger.info("Element is not found");
        }
    }

}
