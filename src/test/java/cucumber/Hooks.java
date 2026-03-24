package cucumber;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}