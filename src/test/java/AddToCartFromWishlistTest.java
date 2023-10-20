import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.pages.CheckoutPage;
import org.pages.RegisterAccountPage;
import org.pages.SearchResultsPage;
import org.pages.WishlistPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class AddToCartFromWishlistTest extends BaseTest {
    private RegisterAccountPage registerAccountPage;
    private WishlistPage wishlistPage;
    private CheckoutPage checkoutPage;
    private SearchResultsPage searchResultsPage;
    private Actions action;
    private String loginPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        registerAccountPage = new RegisterAccountPage(driver);
        wishlistPage = new WishlistPage(driver);
        checkoutPage = new CheckoutPage(driver);
        action = new Actions(driver);
        searchResultsPage = new SearchResultsPage(driver);
        createAccount();
    }

    public void createAccount() {
        System.out.println("Creating new account to be used in tests ...");
        driver.get(loginPageURL);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0040755829345");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirm("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        System.out.println("Creating new account to be used in tests ...");
    }

    public void billingAddress() throws InterruptedException {
        checkoutPage.insertFirstName("John");
        checkoutPage.insertLastName("Doe");
        checkoutPage.insertAddress("Main Street, no. 111");
        checkoutPage.insertCity("London");
        checkoutPage.setPostalCode("123456");
    }

    @Test
    public void addItemToWishlist() throws Exception {
        String expectedResult = "No results!";
        wishlistPage.clickWishlist();
        String actualResult = wishlistPage.getNoResultsElementText();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishlistPage.enterTextSearch("iPod");
        wishlistPage.clickSearchButton();
        //Wait for items to load
        Thread.sleep(1000);
        WebElement item = searchResultsPage.getFirstItem();
        action.moveToElement(item).build().perform();
        //Wait for hoover element to be displayed
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddToWishlistButton();
        action.moveToElement(button).click().build().perform();
        //Wait for popup to be displayed
        Thread.sleep(1000);
        searchResultsPage.clickClosePopupButton();
        searchResultsPage.clickWishlist();
        int noOfItems = wishlistPage.getWishlistItems().size();
        Assert.assertTrue(noOfItems == 1, "Wishlist is empty");
        wishlistPage.clickAddToCartFromWishlistButton();
        Thread.sleep(1000);
        searchResultsPage.clickClosePopupButton();
        searchResultsPage.clickCart();
        Thread.sleep(1000);
        searchResultsPage.clickCheckoutButton();
        Thread.sleep(1000);
        String expectedResultCheckout = "iPod Touch";
        String actualResultCheckout = checkoutPage.getProductName();
        Assert.assertEquals(actualResultCheckout, expectedResultCheckout, "Text from element is not the expected one.");
        billingAddress();
        Thread.sleep(1000);
        checkoutPage.clickAgreeCheckbox();
        Thread.sleep(1000);
        checkoutPage.clickContinueButton();
        Thread.sleep(1000);
        String expectedResultConfirmOrder = "Confirm Order";
        WebElement exp = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        String actualResultConfirmOrder = exp.getText();
        Assert.assertEquals(actualResultConfirmOrder, expectedResultConfirmOrder, "Text from element is not the expected one.");
    }
}
