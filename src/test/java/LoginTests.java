import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Create a happy flow Login test.
 * 1. Create a page object LoginPage class with the locators and methods to interact with the elements.
 * This class need to extend BasePage
 * 2. Create a new class named LoginTests and add a new happy flow test. An account need to be created
 * in advance in order to have valid credentials.
 * 3. A new page object  MyAccountPage class need to be created with locators and methods for page
 * where the user lands after log in.
 * Extra work:
 * Create negative tests for Login page.
 */

public class LoginTests {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
    }

    @Test
    public void loginReturningCustomerTest() {
        String actualUrl;
        String expectedUrl;
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertEmail("anca12345670811@email.com");
        loginPage.insertPassword("Password123");
        loginPage.clickLoginButton();
        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/account";
        Assert.assertEquals(actualUrl, expectedUrl, "Url is not the expected one");
    }

    @Test
    public void invalidEmailFormatTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertEmail("anca1.com");
        loginPage.insertPassword("Password123");
        loginPage.clickLoginButton();
        String actualText = loginPage.getErrorMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void loginWithoutEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertPassword("Password123");
        loginPage.clickLoginButton();
        String actualText = loginPage.getInsertEmailErrorMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void loginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertEmail("anca12345670811@email.com");
        loginPage.clickLoginButton();
        String actualText = loginPage.getInsertPasswordErrorMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }
}