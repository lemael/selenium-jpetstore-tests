package com.example.tests.tests;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.tests.pages.CategoryPage;
import com.example.tests.pages.HomePage;
import com.example.tests.pages.ProductPage;

public class AddProductToCartTest {

    private WebDriver driver;

    @BeforeEach 
    void setUp() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
      
        // nicht im Headless-Modus starten
        // options.addArguments("--headless=new");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

           
    }

    @Test
    void shouldAddBulldogItemToCart() {

        // 1. öffnen der Startseite
        HomePage homePage = new HomePage(driver);
        homePage.open();

        // 2. Auswahl der Kategorie DOGS
        homePage.selectCategory("DOGS");

        // 3. Auswahl des Produkts Bulldog
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.debugProductLinks("K9-BD-01");
        categoryPage.selectProduct("K9-BD-01");

        // 4. Artikel EST-33 zum Warenkorb hinzufügen
        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCart("EST-33");

        // 5. Warenkorb öffnen
        productPage.openCart();

        // 6. Überprüfen, dass der Artikel im Warenkorb erscheint
        assertTrue(
                driver.getPageSource().contains("EST-33"),
                "Der Artikel EST-33 sollte im Warenkorb erscheinen."
        );
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
