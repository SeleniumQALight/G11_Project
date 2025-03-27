package org.bdd.stepDefenitions;

import org.bdd.helpers.WebDriverHelper;
import org.pages.PageProvider;

public class MainSteps {
    protected WebDriverHelper webDriverHelper;
    protected PageProvider pageProvider;
    public static final String DEFAULT = "default";

    public MainSteps(WebDriverHelper webDriverHelper){
        this.webDriverHelper = webDriverHelper;
        pageProvider = new PageProvider(webDriverHelper.getWebDriver());

    }
}
