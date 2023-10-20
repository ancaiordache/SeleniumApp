import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.AccountCreatedPage;
import org.pages.RegisterAccountPage;
import org.testng.Assert;
import org.testng.annotations.*;

import static util.TestUtil.generateRandomEmail;

public class RegisterAccountTests {
    private WebDriver driver;
    private RegisterAccountPage registerAccountPage;
    private String loginPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigate to " + loginPageURL);
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        registerAccountPage = new RegisterAccountPage(driver);
    }

    @Test
    public void registerNewAccountMandatoryFieldsTest() {
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0040755829345");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String actualText = accountCreatedPage.getHeaderText();
        String expectedText = "Your Account Has Been Created!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }

    @Test
    public void registerWithoutPrivacyPolicyTest() {
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0040755829345");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getErrorMessage();
        String expectedText = "Warning: You must agree to the Privacy Policy!";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void registerAccountWithoutFirstNameTest() {
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0040755829345");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getFirstNameErrorMessage();
        String expectedText = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void registerAccountWithoutLastNameTest() {
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0040755829345");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getLastNameErrorMessage();
        String expectedText = "Last Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void registerAccountWithoutEmailTest() {
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertPhoneNumber("0040755829345");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getEmailErrorMessage();
        String expectedText = "E-Mail Address does not appear to be valid!";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void registerAccountWithoutTelephoneTest() {
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getTelephoneErrorMessage();
        String expectedText = "Telephone must be between 3 and 32 characters!";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void registerAccountWithoutPasswordTest() {
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0040755829345");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getPasswordErrorMessage();
        String expectedText = "Password must be between 4 and 20 characters!";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @Test
    public void registerAccountWithoutPasswordConfirmTest() {
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0040755829345");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        String actualText = registerAccountPage.getPasswordConfirmErrorMessage();
        String expectedText = "Password confirmation does not match password!";
        Assert.assertEquals(actualText, expectedText, "Error message is not the expected one");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
