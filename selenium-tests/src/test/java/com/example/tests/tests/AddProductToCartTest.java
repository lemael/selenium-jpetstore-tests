
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
        System.out.println("\n========================================");
        System.out.println("TEST SETUP: Starte Chrome WebDriver");
        System.out.println("========================================");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        System.out.println("[SETUP] Chrome WebDriver erfolgreich gestartet.");
    }

    @Test
    void shouldAddBulldogItemToCart() {

        System.out.println("\n========================================");
        System.out.println("TEST START: shouldAddBulldogItemToCart");
        System.out.println("========================================");

        // 1. Startseite öffnen
        System.out.println("[SCHRITT 1/6] Öffne die Startseite...");
        HomePage homePage = new HomePage(driver);
        homePage.open();
        System.out.println("[OK] Startseite geöffnet.");

        // 2. Kategorie DOGS auswählen
        System.out.println("[SCHRITT 2/6] Wähle die Kategorie DOGS...");
        homePage.selectCategory("DOGS");
        System.out.println("[OK] Kategorie DOGS ausgewählt.");

        // 3. Produkt Bulldog auswählen
        System.out.println("[SCHRITT 3/6] Suche das Produkt K9-BD-01...");
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.debugProductLinks("K9-BD-01");
        categoryPage.selectProduct("K9-BD-01");
        System.out.println("[OK] Produkt K9-BD-01 ausgewählt.");

        // 4. Artikel EST-33 zum Warenkorb hinzufügen
        System.out.println("[SCHRITT 4/6] Füge den Artikel EST-33 zum Warenkorb hinzu...");
        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCart("EST-33");
        System.out.println("[OK] Artikel EST-33 zum Warenkorb hinzugefügt.");

        // 5. Warenkorb öffnen
        System.out.println("[SCHRITT 5/6] Öffne den Warenkorb...");
        productPage.openCart();
        System.out.println("[OK] Warenkorb geöffnet.");

        // 6. Überprüfen, ob der Artikel im Warenkorb vorhanden ist
        System.out.println("[SCHRITT 6/6] Überprüfe, ob EST-33 im Warenkorb vorhanden ist...");

        boolean itemIsPresent = driver.getPageSource().contains("EST-33");

        assertTrue(
                itemIsPresent,
                "Der Artikel EST-33 sollte im Warenkorb erscheinen."
        );

        System.out.println("[OK] Assertion erfolgreich: EST-33 ist im Warenkorb vorhanden.");

        System.out.println("\n========================================");
        System.out.println("TEST ERFOLGREICH ABGESCHLOSSEN");
        System.out.println("========================================");
    }

    @AfterEach
    void tearDown() {
        System.out.println("[TEARDOWN] Beende Chrome WebDriver...");

        if (driver != null) {
            driver.quit();
            System.out.println("[TEARDOWN] Chrome WebDriver erfolgreich beendet.");
        }
    }
}
