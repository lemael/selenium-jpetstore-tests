package com.example.tests;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;



public class CollectAllHtmlAttributesJpetstore {
    @Test
    void collectAllHtmlAttributes() throws Exception {

        ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(options);

         try {
            // öffnen der Webseite
            driver.get("https://jpetstore.aspectran.com/products/K9-BD-01");

            // warten bis das Dokument geladen ist
            new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(d ->
                    ((JavascriptExecutor) d)
                        .executeScript(
                            "return document.readyState"
                        ).equals("complete")
                );

            // alle Elemente und ihre Attribute abrufen
            JavascriptExecutor js = (JavascriptExecutor) driver;

            List<Map<String, Object>> elements =
                (List<Map<String, Object>>) js.executeScript("""
                    return Array.from(document.querySelectorAll('*'))
                        .map(element => {
                            const attributes = {};

                            for (const attr of element.attributes) {
                                attributes[attr.name] = attr.value;
                            }

                            return {
                                tag: element.tagName.toLowerCase(),
                                text: (element.innerText || '')
                                    .trim()
                                    .slice(0, 500),
                                attributes: attributes
                            };
                        });
                    """);

            // allgemeine Informationen vorbereiten
            Map<String, Object> result = Map.of(
                "url", driver.getCurrentUrl(),
                "title", driver.getTitle(),
                "elementCount", elements.size(),
                "elements", elements
            );

            // Ergebnis als JSON speichern
            Path output = Path.of(
                "target",
                "jpetstore-attributes-von-product.json"
            );

            Files.createDirectories(output.getParent());

            ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter()
                .writeValue(output.toFile(), result);

            System.out.println("URL : " + driver.getCurrentUrl());
            System.out.println("Titel : " + driver.getTitle());
            System.out.println("Gesammelte Elemente : " + elements.size());
            System.out.println("Erstellte Datei : " + output.toAbsolutePath());

        } finally {
            // Browser schließen
            driver.quit();
        }
    }

    
}
