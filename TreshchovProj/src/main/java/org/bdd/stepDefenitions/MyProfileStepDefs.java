package org.bdd.stepDefenitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class MyProfileStepDefs extends MainSteps{
    public MyProfileStepDefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I was redirected to MyProfile page")
    public void iWasRedirectedToMyProfilePage() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
    }

    @And("I see {} posts in Posts list on MyProfile Page")
    public void iSeeNumberOfPostsPostsInPostsListOnMyProfilePage(Integer numberOfPosts) {
            pageProvider.getMyProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}