package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;

public class ProductPage extends BasePage {
    private final By addToCartBtnFromProductPage = By.cssSelector("button[type='submit'][name='add-to-cart']");
    private final By productTitle = By.className("product_title");
    private final By quantity = By.id("quantity_66bb673475fce");
    private final By viewCartBtn = By.xpath("(//a[@class='button wc-forward'][normalize-space()='View cart'])[3]");
    private final By productAddedAlert = By.cssSelector("div[class='woocommerce-message'][role='alert']");

    //input[id='quantity_66bb673475fce']


    public ProductPage (WebDriver driver){
        super(driver);
    }

    public ProductPage load(Product product){
        load("/product/"+ removeWhiteSpaceInProductName(product));
        return this;
    }

    public String removeWhiteSpaceInProductName(Product product){
        return product.getName().replace(" ","-");
    }

    public String getProductTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText();
    }

    public ProductPage clickAddToCartBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtnFromProductPage)).click();
        return this;
    }

    public ProductPage getQuantity(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantity));
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn)).click();
        return new CartPage(driver);
    }

    public ProductPage waitForAlert(){
        wait.until(ExpectedConditions.alertIsPresent());
        return this;
    }
}
