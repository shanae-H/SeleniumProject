package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;
import org.selenium.pom.constants.TaxRate;

public class CheckOutPage extends BasePage {
    private final By billingFName = By.id("billing_first_name");
    private final By billingLName= By.id("billing_last_name");
    private final By billingAddress= By.id("billing_address_1");
    private final By billingCity = By.id("billing_city");
    private final By billingPostcode = By.id("billing_postcode");
    private final By billingEmail= By.id("billing_email");
    private final By placeOrderBtn= By.id("place_order");
    private final By confirmationMessage = By.cssSelector(".woocommerce-notice");

    private final By startLoginBtn = By.className("showlogin");
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By altCountryDropdown = By.id("select2-billing_country-container");
    private final By altStateDropDown = By.id("select2-billing_state-container");

    private final By productName = By.cssSelector("td[class='product-name']");
    private final By cashOnDeliveryBtn = By.id("payment_method_cod");
    private final By loginError = By.cssSelector("ul[class='woocommerce-error']");

    private final By orderNumber = By.cssSelector("li[class='woocommerce-order-overview__order order'] strong");
    private final By accountMenuLink = By.cssSelector("#menu-item-1237 > a");

    private final By cartSubtotal = By.cssSelector("tr[class='cart-subtotal'] bdi");
    private final By cartTotal = By.cssSelector("tr[class='order-total'] bdi");
    private final By shippingDetails = By.cssSelector("tr[class='woocommerce-shipping-totals shipping'] ");
    private final By stateTax = By.cssSelector("tr[class^='tax-rate'] td ");


    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage load (){
        load("/checkout/");
        return this;
    }

    public CheckOutPage enterFirstName(String firstName){
       WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingFName));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }
    public CheckOutPage enterLastName(String lastName){
        driver.findElement(billingLName).clear();
        driver.findElement(billingLName).sendKeys(lastName);
        return this;
    }
    public CheckOutPage enterAddressLineOne(String address){
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(address);
        return this;
    }
    public CheckOutPage enterCity(String city){
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(city);
        return this;
    }
    public CheckOutPage enterPostCode(String postcode){
        driver.findElement(billingPostcode).clear();
        driver.findElement(billingPostcode).sendKeys(postcode);
        return this;
    }

    public CheckOutPage enterEmail(String email){
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(email);
        return this;
    }

    public CheckOutPage selectCountry(String country){
        wait.until(ExpectedConditions.elementToBeClickable(altCountryDropdown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='"+country+"']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }

    public CheckOutPage selectState(String state){
        wait.until(ExpectedConditions.elementToBeClickable(altStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='"+state+"']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }

    public CheckOutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .selectCountry(billingAddress.getCountry())
                .enterAddressLineOne(billingAddress.getAddressLineOne())
                .enterCity(billingAddress.getCity())
                .selectState(billingAddress.getState())
                .enterPostCode(billingAddress.getPostalCode())
                .enterEmail(billingAddress.getEmail());
    }

    public CheckOutPage clickPlaceOrderBtn(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getConfirmationMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).getText();
    }

    public CheckOutPage startLoginToAccount(){
        wait.until(ExpectedConditions.elementToBeClickable(startLoginBtn)).click();
        return this;
    }
    public CheckOutPage enterUsername(String loginName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(loginName);
        return this;
    }

    public CheckOutPage enterPassword(String loginPassword){
        driver.findElement(password).sendKeys(loginPassword);
        return this;
    }
    public CheckOutPage clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }
   public CheckOutPage loginToAccount(User user){
        return enterUsername(user.getUserName())
                .enterPassword(user.getPassword())
                .clickLoginBtn();
   }
   public CheckOutPage selectDirectBankTransfer(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if (!e.isSelected()){
            e.click();
        }
        return this;
    }

   public String getProductName() {
      return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
   }

   public CheckOutPage selectCashOnDelivery() {
       waitForOverlaysToDisappear(overlay);
       WebElement e = wait.until(ExpectedConditions.elementToBeClickable(cashOnDeliveryBtn));
       if (!e.isSelected()){
           e.click();
       }
       return this;
   }

   public String getLoginErrorMessage(){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(loginError)).getText();
   }

   public AccountPage clickAccountMenuLink(){
        wait.until(ExpectedConditions.elementToBeClickable(accountMenuLink)).click();
        return new AccountPage(driver);
   }

   public int getOrderNumber (){
       return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber)).getText());
    }

   public String getShippingDetails(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shippingDetails)).getText();
   }

   public Double getSubtotal(){
       String cartSubtotal = wait.until(ExpectedConditions.visibilityOfElementLocated(this.cartSubtotal)).getText();
       return Double.valueOf(cartSubtotal.replace("$",""));
   }

   public Double getCartTotal(){
       String cartTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(this.cartTotal)).getText();
       return Double.valueOf(cartTotal.replace("$",""));
   }

   public String calculateTax(String state){
       Double rate = switch (state.toLowerCase()) {
           case "california" -> TaxRate.CALIFORNIA.rate;
           case "missouri" -> TaxRate.MISSOURI.rate;
           case "alabama" -> TaxRate.ALABAMA.rate;
           default -> 0.0;
       };
       return "$" + String.format("%.2f",getSubtotal() * rate);
   }

   public String getStateTax () {
        waitForOverlaysToDisappear(overlay);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(stateTax)).getText();
   }

}
