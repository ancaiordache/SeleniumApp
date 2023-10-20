import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void loginReturningCustomerTest() {
        LoginPage loginPage = new LoginPage(driver);
        String actualUrl;
        String expectedUrl;
        loginPage.insertEmail("anca12345670811@email.com");
        loginPage.insertPassword("Password123");
        loginPage.clickLoginButton();
        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/account";
        Assert.assertEquals(actualUrl, expectedUrl, "Url is not the expected one");
    }
}
