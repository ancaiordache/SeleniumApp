import org.openqa.selenium.By;
import org.pages.DashboardPage;
import org.pages.RegisterAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class DashboardTests extends BaseTest {
    private RegisterAccountPage registerAccountPage;
    private DashboardPage dashboardPage;
    private String registerUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    public void createAccount() {
        driver.get(registerUrl);
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("01233456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirm("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
    }

    @BeforeClass
    public void setUpPreconditions() {
        System.out.println("Creating new account to be logged in...");
        registerAccountPage = new RegisterAccountPage(driver);
        dashboardPage = new DashboardPage(driver);
        createAccount();
        System.out.println("Creating new account to be logged in ... Done");
        driver.findElement(By.xpath("//*[@id='content']/div/a")).click();
    }

    @Test
    public void verifyDashboardFirstSection() {
        String expectedFirstSectionHeaderText = "My Account";
        String expectedSecondSectionHeaderText = "My Orders";
        String expectedThirdSectionHeaderText = "My Affiliate Account";

        Assert.assertEquals(dashboardPage.getFirstSectionHeaderText(), expectedFirstSectionHeaderText,
                "First section header text is not the expected one");
        Assert.assertEquals(dashboardPage.getSecondSectionHeaderText(), expectedSecondSectionHeaderText,
                "Edit account element text is not the expected one");
        Assert.assertEquals(dashboardPage.getThirdSectionHeaderText(), expectedThirdSectionHeaderText,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyDashboardAccountInformation() {
        String expectedEditYourAccountInformation = "Edit your account information";
        Assert.assertEquals(dashboardPage.getEditYourAccountInformationElement(), expectedEditYourAccountInformation,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyDashboardChangePassword() {
        String expectedChangeYourPasswordInformation = "Change your password";
        Assert.assertEquals(dashboardPage.getChangeYourPasswordElement(), expectedChangeYourPasswordInformation,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyModifyAddressBookEntries() {
        String expectedModifyAddressBook = "Modify your address book entries";
        Assert.assertEquals(dashboardPage.getModifyAddressBookEntriesElement(), expectedModifyAddressBook,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyModifyWishList() {
        String expectedModifyWishList = "Modify your wish list";
        Assert.assertEquals(dashboardPage.getMyWishlistElement(), expectedModifyWishList,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifySubscribeToNewsletter() {
        String expectedSubscribeToNewsletter = "Subscribe / unsubscribe to newsletter";
        Assert.assertEquals(dashboardPage.getNewsletterElement(), expectedSubscribeToNewsletter,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyOrderHistory() {
        String expectedOrderHistory = "View your order history";
        Assert.assertEquals(dashboardPage.getOrderHistoryElement(), expectedOrderHistory,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyDownloads() {
        String expectedDownloads = "Downloads";
        Assert.assertEquals(dashboardPage.getDownloadsElement(), expectedDownloads,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyYourRewardPoints() {
        String expectedRewardPoints = "Your Reward Points";
        Assert.assertEquals(dashboardPage.getRewardPointsElement(), expectedRewardPoints,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyYourReturnRequests() {
        String expectedReturnRequests = "View your return requests";
        Assert.assertEquals(dashboardPage.getReturnRequestElement(), expectedReturnRequests,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyTransactions() {
        String expectedTransactions = "Your Transactions";
        Assert.assertEquals(dashboardPage.getTransactionsElement(), expectedTransactions,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyRecurringPayments() {
        String expectedRecurringPayments = "Recurring payments";
        Assert.assertEquals(dashboardPage.getRecurringPaymentsElement(), expectedRecurringPayments,
                "Edit account element text is not the expected one");
    }

    @Test
    public void verifyAffiliateAccount() {
        String expectedAffiliateAccount = "Register for an affiliate account";
        Assert.assertEquals(dashboardPage.getRegisterAffiliateAccountElement(), expectedAffiliateAccount,
                "Edit account element text is not the expected one");
    }
}