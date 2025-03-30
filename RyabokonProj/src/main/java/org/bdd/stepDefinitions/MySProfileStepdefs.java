package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class MySProfileStepdefs extends MainSteps{
    public MySProfileStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I was redirected to MyProfile page")
    public void iWasRedirectedToMyProfilePage() {
        pageProvider.getMyProfilePage().checkIsRedirectToProfilePage();
    }

    @And("I see {} posts in Posts list on MyProfile Page")
    public void iSeePostsInPostsListOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
