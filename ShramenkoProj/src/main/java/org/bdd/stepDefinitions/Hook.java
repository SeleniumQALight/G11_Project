package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before; //обов'язково CUCUMBER!!!!
import org.bdd.helpers.WebDriverHelper;

public class Hook {
    //клас для before and after секцій

    WebDriverHelper webDriverHelper;

    public Hook (WebDriverHelper webDriverHelper){
        this.webDriverHelper = webDriverHelper;
    }

    @Before
    public void setUp() {
    //    webDriverHelper = new WebDriverHelper();
        //    так не робити, тому що ми не зможемо достукатися до нього

    }

    @After
    public void tearDown() {
        webDriverHelper.quiteDriver();
    }


}
