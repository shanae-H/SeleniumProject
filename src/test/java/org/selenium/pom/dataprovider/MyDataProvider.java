package org.selenium.pom.dataprovider;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {
    @DataProvider(name = "getFeaturedProducts", parallel = false)
    public Object[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJson("products.json",Product[].class);
    }

    @DataProvider(name = "getBillingAddresses")
    public Object[] getBillingAddresses () throws IOException{
        return JacksonUtils.deserializeJson("billingAddressList.json", BillingAddress[].class);
    }
}
