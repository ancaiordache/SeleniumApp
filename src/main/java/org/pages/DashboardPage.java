package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstSectionHeader = By.xpath(".//div[@id='content']/div[1]/h2");
    private By secondSectionHeader = By.xpath(".//div[@id='content']/div[2]/h2");
    private By thirdSectionHeader = By.xpath(".//div[@id='content']/div[3]/h2");
    private By editAccountInformationElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/edit')]");
    private By changePasswordElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/password')]");
    private By modifyAddressElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/address')]");
    private By myWishlistElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/wishlist')]");
    private By newsletterElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/newsletter')]");
    private By orderHistoryElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/order')]");
    private By orderDownloadsElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/download')]");
    private By orderRewardPointsElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/reward')]");
    private By orderReturnRequestsElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/return')]");
    private By orderTransactionsElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/transaction')]");
    private By orderRecurringPaymentsElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/recurring')]");
    private By orderRegisterAffiliateAccountElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/affiliate/add')]");
    private By homePageLogoElement = By.xpath("//*[@id=\"entry_217821\"]/figure/a/img");

    public String getFirstSectionHeaderText() {
        return driver.findElement(firstSectionHeader).getText();
    }

    public String getSecondSectionHeaderText() {
        return driver.findElement(secondSectionHeader).getText();
    }

    public String getThirdSectionHeaderText() {
        return driver.findElement(thirdSectionHeader).getText();
    }

    public String getEditYourAccountInformationElement() {
        return driver.findElement(editAccountInformationElement).getText();
    }

    public String getChangeYourPasswordElement() {
        return driver.findElement(changePasswordElement).getText();
    }


    public String getModifyAddressBookEntriesElement() {
        return driver.findElement(modifyAddressElement).getText();
    }

    public String getMyWishlistElement() {
        return driver.findElement(myWishlistElement).getText();
    }

    public String getNewsletterElement() {
        return driver.findElement(newsletterElement).getText();
    }

    public String getOrderHistoryElement() {
        return driver.findElement(orderHistoryElement).getText();
    }

    public String getDownloadsElement() {
        return driver.findElement(orderDownloadsElement).getText();
    }

    public String getRewardPointsElement() {
        return driver.findElement(orderRewardPointsElement).getText();
    }

    public String getReturnRequestElement() {
        return driver.findElement(orderReturnRequestsElement).getText();
    }

    public String getTransactionsElement() {
        return driver.findElement(orderTransactionsElement).getText();
    }

    public String getRecurringPaymentsElement() {
        return driver.findElement(orderRecurringPaymentsElement).getText();
    }

    public String getRegisterAffiliateAccountElement() {
        return driver.findElement(orderRegisterAffiliateAccountElement).getText();
    }

    public void clickHomePageLogoButton() {
        driver.findElement(homePageLogoElement).click();
    }
}
