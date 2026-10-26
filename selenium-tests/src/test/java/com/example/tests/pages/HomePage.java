package com.example.tests.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://jpetstore.aspectran.com/");
    }

    public void selectCategory(String category) {
        By locator = By.cssSelector(
                "a[href='/categories/" + category + "']:not(.nav-link):not(.dropdown-item)"
        );

        WebElement link = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                link
        );

        wait.until(ExpectedConditions.elementToBeClickable(link)).click();
    }
}
