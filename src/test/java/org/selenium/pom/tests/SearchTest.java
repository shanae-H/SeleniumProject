package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    @Test
    public void searchWithPartialMatch(){
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(searchFor);
        String currentUrl = getDriver().getCurrentUrl();
        System.out.println("Current Url is: "+ currentUrl);
        Assert.assertEquals(currentUrl,"https://askomdch.com/?s="+searchFor+"&post_type=product");
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");
    }

    @Test
    public void searchWithExactMatch(){
        String searchFor = "Blue TShirt";
        ProductPage productPage = new StorePage(getDriver()).load()
                .searchForExact(searchFor);
        String currentUrl = getDriver().getCurrentUrl();
        System.out.println("Current Url is: "+ currentUrl);
        Assert.assertTrue(currentUrl.contains("https://askomdch.com/product"));
        Assert.assertEquals(productPage.getProductTitle().toLowerCase(),searchFor.toLowerCase());
        System.out.println("Product page being viewed is: " + productPage.getProductTitle());
    }

    @Test
    public void searchNonExistingProduct(){
        String searchFor="Green Watch";
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(searchFor);
        String currentUrl = getDriver().getCurrentUrl();
        System.out.println("Current Url is: "+ currentUrl);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");
        Assert.assertEquals(storePage.getMessageDisplayed(),"No products were found matching your selection.","Assume product was found");
    }


}
