package org.selenium.pom.utils;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.LoginApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckOutPage;

import java.io.IOException;

public class LoginAndCheckOutUtil extends BaseTest {

    public CheckOutPage loginAndPurchaseItem(User user, Product product, int quantityOfProduct) throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        LoginApi loginApi = new LoginApi();
        loginApi.login(user);
        CartApi cartApi = new CartApi(loginApi.getCookies());
        cartApi.addToCart(product.getId(),quantityOfProduct);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkOutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .clickPlaceOrderBtn();
        return checkOutPage;
    }
}
