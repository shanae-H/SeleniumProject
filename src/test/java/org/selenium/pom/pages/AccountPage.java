package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class AccountPage extends BasePage {
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.name("login");

    private final By orderTab= By.cssSelector("li[class='woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--orders'] a");
    private final By orderLink = By.linkText("Orders");

    private final By viewOrderBtn = By.cssSelector("td[data-title='Actions'] a");
    private final By orderNumber = By.cssSelector("mark[class='order-number']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage load (){
        load("/account/");
        return this;
    }

    public AccountPage enterUsername(String loginName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(loginName);
        return this;
    }

    public AccountPage enterPassword(String loginPassword){
        driver.findElement(password).sendKeys(loginPassword);
        return this;
    }
    public AccountPage clickLoginBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    public AccountPage clickOrdersTab(){
        wait.until(ExpectedConditions.elementToBeClickable(orderLink)).click();
        return this;
    }

    public AccountPage clickViewOrderBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(viewOrderBtn)).click();
        return this;
    }

    public int getOrderNumber(){
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber)).getText());
    }


}
