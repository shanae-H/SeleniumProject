package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.components.ProductThumbnail;

public class StorePage extends BasePage {

    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value=Search]");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");

    private final By viewCartBtn = By.cssSelector("a[title='View cart'");


    private final By storeProductLink = By.cssSelector("a[class=ast-loop-product__link]");
    private final By storeFeaturedProductLink = By.cssSelector("ul[class='product_list_widget'] li a");
    private final By storeFeaturedProducts = By.id("woocommerce_top_rated_products-3");

    private final By addToCartBtnFromProductPage = By.cssSelector("button[type='submit'][name='add-to-cart']");
    private final By messageBar= By.cssSelector("p[class='woocommerce-info woocommerce-no-products-found']");


    private ProductThumbnail productThumbnail;

    public StorePage(WebDriver driver){
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    private StorePage enterTextInSearchFld(String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(text);
        return this;
    }

    public StorePage load(){
        load("/store");
        return this;
    }
    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public StorePage search(String text){
        enterTextInSearchFld(text).clickSearchBtn();
        return this;
    }
    public ProductPage searchForExact(String text){
        enterTextInSearchFld(text).clickSearchBtn();
        return new ProductPage(driver);
    }
    private StorePage clickSearchBtn(){
        driver.findElement(searchBtn).click();
        return this;
    }

    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public By getAddToCartBtn(String productName){
        return By.cssSelector("a[aria-label='Add “"+productName+"” to your cart']");
    }
    public StorePage clickAddToCartBtn (String productName){
        By addToCartBtn = getAddToCartBtn(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn)).click();
        return new CartPage(driver);
    }

    public ProductPage navigateToProduct(){
        wait.until(ExpectedConditions.elementToBeClickable(storeProductLink)).click();
        return new ProductPage(driver);
    }

    public ProductPage navigateToFeaturedProduct(){
        wait.until(ExpectedConditions.elementToBeClickable(storeFeaturedProductLink)).click();
        return new ProductPage(driver);
    }

    public String getMessageDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(messageBar)).getText();
    }


}






