package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NavigationTest extends BaseTest {
    @Test
    public void NavigateFomHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver())
                .load()
                .navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(),"Store");
    }

    @Test
    public void NavigateFromStoreToProductPage() {
       ProductPage productPage = new HomePage(getDriver())
                .load()
                .navigateToStoreUsingMenu()
                .navigateToProduct();
        Assert.assertTrue(productPage.getProductTitle().contains("Anchor Bracelet"));
    }

//    @Test
//    public void NavigateFromStoreToFeaturedProductPage() {
//        StorePage storePage = new HomePage(getDriver())
//                .load()
//                .navigateToStoreUsingMenu()
//                .navigateToFeaturedProduct();
//        Assert.assertTrue(storePage.getProductTitle().contains("Dark Brown Jeans"));
//    }

    @Test
    public void NavigateFromStoreToFeaturedProductPage(){
        ProductPage productPage = new StorePage(getDriver())
                .load()
                .navigateToFeaturedProduct();
        Assert.assertTrue(productPage.getProductTitle().contains("Basic Blue Jeans"));
    }
    @Test
    public void NavigateFromHomeToFeaturedProductPage(){
        ProductPage productPage = new HomePage(getDriver())
                .load()
                .navigateToAFeaturedProduct();
        Assert.assertTrue(productPage.getProductTitle().contains("Blue Shoes"));
    }
}
