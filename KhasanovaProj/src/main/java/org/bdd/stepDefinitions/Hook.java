package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.bdd.helpers.WebDriverHelper;

public class Hook {
    WebDriverHelper webDriverHelper;

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper; //за допомогою пікоконтейнеру ми можемо передати об'єкт класу WebDriverHelper
        //перед створенням об'єкта він дивиться, чи є все що потрібно для створення об'єкта в піконтейнері
        //якщо є, то створюється, якщо немає, то створюється все що потрібно для створення об'єкта
    }

    @Before
    public void setUp() {
//        webDriverHelper = new WebDriverHelper();
    }

    @After
    public void tearDown() {
        webDriverHelper.quiteDriver();
    }
}
