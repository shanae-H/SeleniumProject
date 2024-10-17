package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.constants.CouponCodes;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CouponTest extends BaseTest {

    @Test
    public void verifyFreeShippingCoupon() {
        CartPage cartPage = new HomePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCartBtn("Blue Shoes")
                .clickViewCart();
        Double previousTotal = cartPage.getCartTotal();
        System.out.println("Previous total: $"+previousTotal);

        cartPage.enterCoupon(CouponCodes.FREE.coupon)
                .clickApplyCouponBtn();
       Assert.assertTrue(cartPage.getNameOfAppliedCoupon().contains("Free shipping"));
       Assert.assertTrue(cartPage.getCartTotal()<previousTotal);
        System.out.println("Current value: $"+ cartPage.getCartTotal());
    }
    @Test
    public void verifyFiveDollarOffCoupon() {
        CartPage cartPage = new HomePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCartBtn("Blue Shoes")
                .clickViewCart();
        Double previousTotal = cartPage.getCartTotal();
        System.out.println("Previous value: $"+previousTotal);

        cartPage.enterCoupon(CouponCodes.FIVEOFF.coupon)
                .clickApplyCouponBtn();
        Assert.assertTrue(cartPage.getNameOfAppliedCoupon().contains("-$5.00"));
        Assert.assertTrue(cartPage.getCartTotal()<previousTotal);
        System.out.println("Current value: $"+ cartPage.getCartTotal());
    }

    @Test
    public void verifyTwentyFivePercentOffCoupon(){
        CartPage cartPage = new HomePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCartBtn("Blue Shoes")
                .clickViewCart();
        Double previousTotal = cartPage.getCartTotal();
        System.out.println("Previous Cart Total: $"+previousTotal);

        cartPage.enterCoupon(CouponCodes.TWENTYFIVEOFF.coupon)
                .clickApplyCouponBtn();
        Assert.assertTrue(cartPage.getNameOfAppliedCoupon().contains("-$"+cartPage.getTwentyFivePercentDiscount()));
        Assert.assertTrue(cartPage.getCartTotal() < previousTotal);
        System.out.println("Current Cart Total: $"+ cartPage.getCartTotal());
    }




}
