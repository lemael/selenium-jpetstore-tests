package com.example.tests.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

        // Einen Artikel anhand seiner ID auswählen
    public void selectItem(String itemId) {
        By itemLink = By.cssSelector(
                "#jpetstore-content a[href$='/items/" + itemId + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(itemLink)
        ).click();
    }

    // Einen Artikel zum Warenkorb hinzufügen
   
    public void addItemToCart(String itemId) {
        By locator = By.cssSelector(
            "#jpetstore-content a[href='/cart/addItemToCart?itemId=" + itemId + "']"
        );

        WebElement button = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});",
            button
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(locator)
        ).click();
    }

    // Den Warenkorb öffnen
    public void openCart() {
        By cartLink = By.cssSelector(
                "a[href='/cart/viewCart']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(cartLink)
        ).click();
    }
}
