package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstNameInput = By.id("input-payment-firstname");
    private By lastNameInput = By.id("input-payment-lastname");
    private By addressInput = By.id("input-payment-address-1");
    private By cityInput = By.id("input-payment-city");
    private By postalCodeInput = By.id("input-payment-postcode");
    private By Country = By.id("input-payment-country");
    private By Region = By.id("input-payment-zone");
    private By agreeCheckbox = By.xpath("//*[@id=\"form-checkout\"]/div/div[2]/div/div[5]/label");
    private By continueButton = By.xpath("//*[@id=\"button-save\"]");
    private By productName = By.xpath("//*[@id=\"checkout-cart\"]/table/tbody/tr/td[2]/a");

    public void insertFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void insertLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void insertAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void insertCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }

    public void setPostalCode(String postalCode) {
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    public void clickAgreeCheckbox() {
        driver.findElement(agreeCheckbox).click();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).sendKeys(Keys.SPACE);
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }
}
