package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test
    public void loginDuringCheckout() throws IOException {
        String username= "demouser"+ new FakerUtils().generateRandomNumber();
        User user = new User().
                setUserName(username).
                setPassword("demopwd")
                .setEmail(username+ "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkOutPage.load();
        checkOutPage.startLoginToAccount()
                .loginToAccount(user);

        Assert.assertTrue(checkOutPage.getProductName().contains(product.getName()));
    }

    @Test
    public void loginFails() throws IOException {
        User user = new User()
                .setUserName("sabrina")
                .setPassword("theWitch")
                .setEmail("sabrina_the_witch@askomdch.com");
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkOutPage.load();
        checkOutPage.startLoginToAccount()
                .loginToAccount(user);


        Assert.assertEquals(checkOutPage.getLoginErrorMessage(),
                "Error: The username "+user.getUserName()+" is not registered on this site. If you are unsure of your username, try your email address instead.");
    }

}
