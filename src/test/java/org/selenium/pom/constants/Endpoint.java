package org.selenium.pom.constants;

public enum Endpoint {
    STORE("/store"),
    CHECKOUT("/checkout"),
    ACCOUNT_EDIT_BILLING_ADDRESS("/account/edit-address/billing/"),
    ADD_TO_CART("/?wc-ajax=add_to_cart"),
    ACCOUNT("/account");

    public final String url;

    Endpoint(String url) {
        this.url=url;
    }
}
