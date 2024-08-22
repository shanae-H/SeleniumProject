package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.components.ProductThumbnail;

import java.util.List;

public class HomePage extends BasePage {

    private final By storeMenuLink = By.cssSelector("li[id=menu-item-1227] a[class=menu-link]");
    private final By featuredProduct = By.cssSelector("ul[class='products columns-5'] li");
    private final By addToCart = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");
    private final By allFeaturedProduct = By.cssSelector("ul[class='products columns-5'] li div[class='astra-shop-summary-wrap'] h2");
    private final By viewCart= By.cssSelector("a[title='View cart']");

    private ProductThumbnail productThumbnail;

    public HomePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public HomePage load(){
        load("/");
        return this;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public StorePage navigateToStoreUsingMenu(){
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }

    public ProductPage navigateToAFeaturedProduct(){
        wait.until(ExpectedConditions.elementToBeClickable(featuredProduct)).click();
        return new ProductPage(driver);
    }

    public HomePage getFeaturedProducts(){
        List<WebElement> featuredProducts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allFeaturedProduct));
        for(WebElement e: featuredProducts){
            System.out.println("Retrieving text from element: "+ e.getText());
            System.out.println("Retrieving accessible name: "+ e.getAccessibleName());
            System.out.println("\n\n\n******************************");
        }
        return this;
    }


}
