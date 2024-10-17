package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.LoginApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.AccountPage;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AccountTest extends BaseTest {
    @Test
    public void viewExistingOrder() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        User user = new User()
                .setUserName("qwertysh")
                .setPassword("qwertysh");

        LoginApi loginApi = new LoginApi();
        loginApi.login(user);
        CartApi cartApi = new CartApi(loginApi.getCookies());
        Product product = new Product(1209);
        cartApi.addToCart(product.getId(),1);


        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        injectCookiesToBrowser(loginApi.getCookies());
        checkOutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .clickPlaceOrderBtn();

        Assert.assertEquals(
                checkOutPage.getConfirmationMessage(),
                "Thank you. Your order has been received.");
        int previousOrderNumber = checkOutPage.getOrderNumber();

        AccountPage accountPage = checkOutPage.clickAccountMenuLink()
                .clickOrdersTab()
                .clickViewOrderBtn();
        Assert.assertEquals(accountPage.getOrderNumber(),previousOrderNumber,"Most recent order is not being viewed");
    }

    @Test
    public void navigateAndViewOrderInAccount() {
         new AccountPage(getDriver())
                .load()
                .enterUsername("qwerty")
                .enterPassword("qwerty")
                .clickLoginBtn()
                .clickOrdersTab()
                .clickViewOrderBtn();
    }
}
