package pages;

import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComboTimelinePage extends BasePage {

    private static final String url = ConfigReader.getProperty("base.url") + "demo/combo-timeline";
    private final By pageHeader = By.cssSelector(".demo-text-content h1");
    private final By cookieDialog = By.id("hc-cookie-dialog");
    private final By acceptCookiesButton = By.id("hc-cookie-dialog-accept");

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