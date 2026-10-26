package com.example.tests.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectProduct(String productId) {
        By locator = By.cssSelector(
            "#jpetstore-content a[href='/products/" + productId + "']"
        );

        WebElement product = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );

        // Auf der Seite bis zum Link scrollen.
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});",
            product
        );

        // Warten, bis der Link anklickbar ist.
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    // Debug method to print product link details
    public void debugProductLinks(String productId) {
        By locator = By.cssSelector(
            "a[href='/products/" + productId + "']"
        );

        var elements = driver.findElements(locator);

        System.out.println("Anzahl der gefundenen Links: " + elements.size());

        for (int i = 0; i < elements.size(); i++) {
            var element = elements.get(i);

            System.out.println(
                "Link " + i
                + " | Text : " + element.getText()
                + " | Sichtbar : " + element.isDisplayed()
                + " | Aktiv : " + element.isEnabled()
            );
        }
    }

}
