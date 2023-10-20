import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.pages.RegisterAccountPage;
import org.pages.SearchResultsPage;
import org.pages.WishlistPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class WishlistFlowsTests extends BaseTest {

    private RegisterAccountPage registerAccountPage;
    private WishlistPage wishlistPage;
    private SearchResultsPage searchResultsPage;
    private Actions action;
    private String loginPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        registerAccountPage = new RegisterAccountPage(driver);
        wishlistPage = new WishlistPage(driver);
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
        wishlistPage.clickRemoveItemFromWishlistButton();
        actualResult = wishlistPage.getNoResultsElementText();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
    }
}
