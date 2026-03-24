package pages;

import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout"))));
    }

    protected void switchToFrame(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void scrollBy(int x, int y) {
        executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    protected Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    protected abstract void waitForPageToLoad();
}