package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishlistPage extends BasePage {

    private By noResultsElement = By.xpath(".//div[@id = 'content']/p");

    private By searchButton = By.xpath(".//button[@class = 'type-text']");
    private By itemsTableRow = By.xpath(".//table[@class = 'table table-hover border']/tbody/tr");
    private By removeItemFromWishlist = By.xpath(".//a[contains(@href, 'remove')]");
    private By addToCartFromWishlist = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/button");
    public String getNoResultsElementText() {
        return driver.findElement(noResultsElement).getText();
    }

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public List<WebElement> getWishlistItems() {
        return driver.findElements(itemsTableRow);
    }

    public void clickRemoveItemFromWishlistButton() {
        driver.findElement(removeItemFromWishlist).click();
    }

    public void clickAddToCartFromWishlistButton() {
        driver.findElement(addToCartFromWishlist).click();
    }
}
