package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.InventoryPage;

import static org.testng.Assert.assertEquals;

public class LoginTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    private WebDriver driver;
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        String title = inventoryPage.getTitleText();
        assertEquals(title, "Products");
        log.info("$$$$$$SUCCESS$$$$$$");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");

        String error = loginPage.getErrorMessage();
        assertEquals(error, "Epic sadface: Username and password do not match any user in this service");
        log.info("$$$$$$SUCCESS$$$$$$");

    }

    @Test
    public void testSortByPriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortBy("Price (low to high)");

        String selected = inventoryPage.getSelectedSortOption();
        assertEquals(selected, "Price (low to high)");
        log.info("$$$$$$SUCCESS$$$$$$");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
