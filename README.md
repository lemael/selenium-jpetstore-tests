# Selenium JPetStore Tests

Automated web testing project using **Java, Selenium WebDriver, Maven and JUnit 5**.

This project automates user interactions with the [JPetStore](https://jpetstore.aspectran.com/) web application to validate key e-commerce functionalities, such as product selection and adding items to the shopping cart.

## Technologies

- **Java 21** — Programming language
- **Selenium WebDriver** — Browser automation
- **JUnit 5** — Test execution and assertions
- **Maven** — Dependency management and build automation
- **ChromeDriver** — Chrome browser automation

## Project Architecture

The project follows the **Page Object Model (POM)** design pattern. This pattern separates test scenarios from page-specific interactions, making the test suite easier to maintain, reuse and extend.

```text
selenium-jpetstore-tests/
├── src/
│   └── test/
│       └── java/
│           └── com/example/tests/
│               ├── pages/
│               │   ├── HomePage.java
│               │   ├── CategoryPage.java
│               │   └── ProductPage.java
│               └── tests/
│                   └── AddProductToCartTest.java
├── pom.xml
├── .gitignore
└── README.md
```

### `pages/` — Page Objects

Each Page Object represents a page or a functional area of the web application. It encapsulates the elements and interactions required to operate that page.

| Class | Responsibility |
|---|---|
| `HomePage` | Opens the home page and selects a product category. |
| `CategoryPage` | Locates and selects a product within a category. |
| `ProductPage` | Adds a specific item to the shopping cart and opens the cart. |

**Why use Page Objects?**

- Separate page interactions from test logic.
- Reuse actions across multiple test scenarios.
- Centralize locator and interaction changes.
- Improve readability and maintainability.

### `tests/` — Test Scenarios

The `tests/` package contains the test classes that define the expected user behavior and verify application functionality.

`AddProductToCartTest.java` uses JUnit 5 to execute the following scenario:

1. Initialize the Chrome WebDriver.
2. Open the JPetStore home page.
3. Select the `DOGS` category.
4. Select the Bulldog product (`K9-BD-01`).
5. Add item `EST-33` to the shopping cart.
6. Open the shopping cart.
7. Assert that `EST-33` appears in the page source.

The test uses JUnit's `assertTrue()` to validate the final result.

### Test Lifecycle

JUnit 5 lifecycle annotations manage browser initialization and cleanup:

- `@BeforeEach` initializes the WebDriver before each test.
- `@Test` executes the test scenario.
- `@AfterEach` closes the browser and releases WebDriver resources.

## Execution Flow

```text
AddProductToCartTest
        |
        v
    HomePage
        |
        | Select DOGS
        v
   CategoryPage
        |
        | Select Bulldog (K9-BD-01)
        v
    ProductPage
        |
        | Add item EST-33
        | Open shopping cart
        v
   Cart Verification
        |
        v
   JUnit Assertion
```

The test class orchestrates the scenario, while the Page Objects handle the interactions with individual pages.

## Prerequisites

Before running the tests, make sure you have installed:

- Java JDK 21 or a compatible version
- Apache Maven
- Google Chrome
- A compatible ChromeDriver

Depending on your Selenium configuration, Selenium Manager can automatically manage the appropriate browser driver.

## Run the Tests

Clone the repository:

```bash
git clone https://github.com/lemael/selenium-jpetstore-tests.git
cd selenium-jpetstore-tests
```

Run the complete test suite:

```bash
mvn test
```

Run only the shopping-cart test:

```bash
mvn -Dtest=AddProductToCartTest test
```

Maven generates test reports under:

```text
target/surefire-reports/
```

## Current Test Coverage

| Feature | Validation |
|---|---|
| Home page navigation | Opens the application |
| Category selection | Selects `DOGS` |
| Product selection | Selects Bulldog `K9-BD-01` |
| Add to cart | Adds item `EST-33` |
| Cart verification | Checks that `EST-33` appears in the page source |

## Future Improvements

Potential improvements include:

- Add explicit waits to improve test stability.
- Introduce reusable assertions for shopping-cart contents.
- Implement additional Page Objects for other application pages.
- Add positive and negative test scenarios.
- Integrate test execution into a GitHub Actions CI/CD workflow.
- Generate and publish test reports as CI artifacts.

## Author

**Mael Rostand Tchinde Fosso**

GitHub: [@lemael](https://github.com/lemael)