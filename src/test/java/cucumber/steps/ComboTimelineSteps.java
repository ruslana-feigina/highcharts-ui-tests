package cucumber.steps;

import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ComboTimelinePage;
import cucumber.Hooks;

public class ComboTimelineSteps {

    private ComboTimelinePage comboTimelinePage;

    @Given("I open combo timeline page")
    public void iOpenComboTimelinePage() {
        comboTimelinePage = new ComboTimelinePage(DriverManager.getDriver());
        comboTimelinePage.open();
    }

    @Then("the combo timeline page is opened")
    public void theComboTimelinePageIsOpened() {
        Assert.assertTrue(comboTimelinePage.isPageOpened());
    }
}