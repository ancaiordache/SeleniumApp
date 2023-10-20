package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By loginButton = By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/form/input");
    private By loginEmailErrorElement = By.xpath("//*[@id=\"account-login\"]/div[1]");
    private By loginPasswordErrorElement = By.xpath("//*[@id=\"account-login\"]/div[1]");

    public void insertEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void insertPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getInsertEmailErrorMessage() {
        return driver.findElement(loginEmailErrorElement).getText();
    }
    public String getInsertPasswordErrorMessage() {
        return driver.findElement(loginPasswordErrorElement).getText();
    }
}