import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

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

public class LoginTests extends BaseTest {

    @Test
    public void invalidEmailFormatTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertEmail("@-+" + generateRandomEmail());
        loginPage.insertPassword("Password123");
        loginPage.clickLoginButton();
        String actualText = loginPage.getErrorMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void loginWithoutEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertPassword(generateRandomEmail());
        loginPage.clickLoginButton();
        String actualText = loginPage.getInsertEmailErrorMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        String expectedTextTwo = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
        Assert.assertEquals(actualText, expectedTextTwo, "Error message is not the expected one");
    }

    @Test
    public void loginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertEmail(generateRandomEmail());
        loginPage.clickLoginButton();
        String actualText = loginPage.getInsertPasswordErrorMessage();
        String expectedText = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }
}
