package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {
    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }
    private By headerElement = By.xpath("//*[@id=\"content\"]/h1");
    public String getHeaderText() {
        return driver.findElement(headerElement).getText();
    }
}