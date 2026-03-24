package pages;

import config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TooltipTextUtils;

import java.util.ArrayList;
import java.util.List;

public class ComboTimelinePage extends BasePage {

    private static final String url = ConfigReader.getProperty("base.url") + "demo/combo-timeline";
    private final By pageHeader = By.cssSelector(".demo-text-content h1");
    private final By cookieDialog = By.id("hc-cookie-dialog");
    private final By acceptCookiesButton = By.id("hc-cookie-dialog-accept");

    private final By demoFrame = By.id("demo");
    private final By chartContainer = By.cssSelector("div.highcharts-container");
    private final By revenueLegendButton = By.cssSelector("button.highcharts-a11y-proxy-element[aria-label='Show Revenue']");
    private final By hiddenRevenueLegendItem = By.cssSelector("g.highcharts-legend-item.highcharts-series-0.highcharts-legend-item-hidden");


    public ComboTimelinePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(url);
        acceptCookiesIfDisplayed();
        waitForPageToLoad();
    }

    public boolean isPageOpened() {
        return driver.getCurrentUrl().contains("combo-timeline") && driver.findElement(pageHeader).isDisplayed();
    }

    public void hideRevenueSeries() {
        scrollBy(0, 500);
        switchToFrame(demoFrame);
        wait.until(ExpectedConditions.elementToBeClickable(revenueLegendButton)).click();
        switchToDefaultContent();
    }

    public boolean isRevenueHidden() {
        switchToFrame(demoFrame);
        boolean isHidden = !driver.findElements(hiddenRevenueLegendItem).isEmpty();
        switchToDefaultContent();
        return isHidden;
    }

    public List<String> collectEmployeeTooltips() {
        List<String> tooltips = new ArrayList<>();

        switchToFrame(demoFrame);
        wait.until(ExpectedConditions.visibilityOfElementLocated(chartContainer));

        int pointsCount = getEmployeePointsCount();
        String previousTooltip = "";

        for (int i = 0; i < pointsCount; i++) {
            showEmployeeTooltipByPointIndex(i);
            String tooltipText = waitForTooltipTextChange(previousTooltip);
            tooltips.add(tooltipText);
            previousTooltip = tooltipText;
        }

        switchToDefaultContent();
        return tooltips;
    }

    private int getEmployeePointsCount() {
        Object result = executeScript("var chart = Highcharts.charts[0];" +
                "if (!chart) return 0;" + "var series = chart.series[1];" +
                "if (!series || !series.points) return 0;" +
                "return series.points.length;");

        if (result == null) {
            return 0;
        }

        return ((Number) result).intValue();
    }

    private void showEmployeeTooltipByPointIndex(int index) {
        executeScript("var chart = Highcharts.charts[0];" +
                "if (!chart) return;" + "var series = chart.series[1];" +
                "if (!series || !series.points || !series.points[" + index + "]) return;" +
                "var point = series.points[" + index + "];" +
                "point.onMouseOver();" +
                "chart.tooltip.refresh(point);");
    }

    private String waitForTooltipTextChange(String previousTooltip) {
        wait.until(driver -> {
            String text = readTooltipText();
            return !text.isEmpty() && !text.equals(previousTooltip);
        });

        return readTooltipText();
    }

    private String readTooltipText() {
        Object result = executeScript("var tooltip = document.querySelector('g.highcharts-label.highcharts-tooltip');" +
                "if (!tooltip) return '';" +
                "return (tooltip.textContent || '').trim();");

        return result == null ? "" : TooltipTextUtils.normalizeTooltipText(result.toString());
    }

    public void acceptCookiesIfDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cookieDialog));
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieDialog));
        } catch (TimeoutException e) {
            System.out.println("Cookie banner was not displayed");
        }
    }

    @Override
    protected void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
    }
}