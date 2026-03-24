package cucumber.steps;

import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.ComboTimelinePage;
import utils.ResourceReader;
import utils.TooltipAssertions;

import java.util.List;

public class ComboTimelineSteps {

    private ComboTimelinePage comboTimelinePage;
    private List<String> actualTooltips;

    @Given("I open combo timeline page")
    public void iOpenComboTimelinePage() {
        comboTimelinePage = new ComboTimelinePage(DriverManager.getDriver());
        comboTimelinePage.open();
    }

    @Then("the combo timeline page is opened")
    public void theComboTimelinePageIsOpened() {
        Assert.assertTrue(comboTimelinePage.isPageOpened());
    }

    @When("I hide Revenue series")
    public void iHideRevenueSeries() {
        comboTimelinePage.hideRevenueSeries();
    }

    @Then("Revenue series should be hidden")
    public void revenueSeriesIsHidden() {
        Assert.assertTrue(comboTimelinePage.isRevenueHidden());
    }

    @When("I collect employee tooltips")
    public void iCollectEmployeeTooltips() {
        actualTooltips = comboTimelinePage.collectEmployeeTooltips();
    }

    @Then("employee tooltip texts should match expected values")
    public void employeeTooltipTextsShouldMatchExpectedValues() {
        List<String> expectedTooltips = ResourceReader.readLines("testdata/comboTimelineEmployeeTooltips.txt");

        TooltipAssertions.assertTooltipsMatch(actualTooltips, expectedTooltips);
    }
}