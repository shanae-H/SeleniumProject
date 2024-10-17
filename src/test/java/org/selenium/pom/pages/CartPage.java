package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {


    @FindBy(css = "td[class='product-name'] a")
    private WebElement productName;
    @FindBy(how = How.CSS, using = ".checkout-button")
    @CacheLookup
    private WebElement checkoutBtn;
    private final By enterCouponFld = By.id("coupon_code");
    private final By applyCouponBtn = By.name("apply_coupon");
    private final By couponName = By.cssSelector("td[data-title^='Coupon: ']");
    private final By cartTotal = By.cssSelector("td[data-title='Total'] bdi");
    private final By subTotal = By.cssSelector("td[data-title='Subtotal'] bdi");

    private final By stateTax = By.cssSelector("td[data-title$='State Tax']");
    private final By updateCartBtn = By.name("update_cart");

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage load (){
        load("/cart/");
        return this;
    }

    public String getUpdateBtnText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(updateCartBtn)).getText();
    }
    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public CheckOutPage checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckOutPage(driver);
    }

    public CartPage enterCoupon(String coupon) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enterCouponFld)).sendKeys(coupon);
        return this;
    }

    public CartPage clickApplyCouponBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponBtn)).click();
        return this;
    }

    public String getNameOfAppliedCoupon() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(couponName)).getText();
    }

    public Double getCartTotal(){
        String cartTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(this.cartTotal)).getText();
        return Double.valueOf(cartTotal.replace("$",""));
    }
    public Double getSubtotal(){
        String cartSubtotal = wait.until(ExpectedConditions.visibilityOfElementLocated(subTotal)).getText();
        return Double.valueOf(cartSubtotal.replace("$",""));
    }

    public String getTwentyFivePercentDiscount(){
        String discount =String.valueOf(getSubtotal()*0.25);
        System.out.println("The 25% discount in String is: $"+ discount );
        return discount;
    }

    public Double getStateTax(){
        String appliedStateTax = wait.until(ExpectedConditions.visibilityOfElementLocated(stateTax)).getText();
        return Double.valueOf(appliedStateTax.replace("$",""));
    }



}
