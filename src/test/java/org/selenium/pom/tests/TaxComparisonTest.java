package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CheckOutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TaxComparisonTest extends BaseTest {
//california tax 7.5%, missouri tax 4.23 % , alabama 4.0%

    @Test(dataProviderClass = MyDataProvider.class, dataProvider = "getBillingAddresses")
    public void compareStateTax(BillingAddress billingAddress) throws IOException, InterruptedException {
        CartApi cartApi = new CartApi();
        Product product = new Product(1209);
        cartApi.addToCart(product.getId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkOutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer();

        String expectedTax = checkOutPage.calculateTax(billingAddress.getState());

        Assert.assertEquals(checkOutPage.getStateTax(), expectedTax);
        System.out.println("Actual Tax is: " + checkOutPage.getStateTax() + " WHILE Expected Tax was: " + expectedTax);

    }
}
