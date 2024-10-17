package org.selenium.pom.constants;

public enum CouponCodes {
    FREE("freeship"),
    FIVEOFF("offcart5"),
    TWENTYFIVEOFF("off25");

    public final String coupon;

    CouponCodes(String coupon) {
        this.coupon=coupon;
    }
}
