package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    public WebDriver driver;
    private By errorMessage = By.xpath(".//div[@class = 'alert alert-danger alert-dismissible']");
    private By wishlistHeartElement = By.xpath("//a[@aria-label = 'Wishlist']");
    private By cartElement = By.xpath(".//div[@class = 'cart-icon']");

    private By checkoutButton = By.xpath(".//*[@id='entry_217851']/a");

    private By searchInput = By.name("search");

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void clickWishlist() {
        driver.findElement(wishlistHeartElement).click();
    }

    public void clickCart() {
        driver.findElement(cartElement).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    public void enterTextSearch(String searchText) {
        driver.findElement(searchInput).sendKeys(searchText);
    }
}