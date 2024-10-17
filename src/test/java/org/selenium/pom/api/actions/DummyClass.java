package org.selenium.pom.api.actions;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class DummyClass {
    public static void main(String[] args) throws IOException {
       // new SignUpApi().getAccount();
        //System.out.println(new SignUpApi().fetchRegisterNonceValue());
//        String username= "demouser"+ new FakerUtils().generateRandomNumber();
//        User user = new User().
//                setUserName(username).
//                setPassword("demopwd")
//                .setEmail(username+ "@askomdch.com");
//        SignUpApi signUpApi = new SignUpApi();
//        signUpApi.register(user);
//        System.out.println("REGISTER COOKIES: "+signUpApi.getCookies());
        //System.out.println(new SignUpApi().fetchRegisterNonceValue());
//        CartApi cartApi = new CartApi();
//        cartApi.addToCart(1215,1);
//        System.out.println(cartApi.getCookies());

       // new LoginApi().getAccount();
       // System.out.println(new LoginApi().fetchLoginNonceValueUsingJsoup());
//        User user = new User().setUserName("qwerty").setPassword("qwerty");
//        LoginApi loginApi = new LoginApi();
//        loginApi.login(user);
//        System.out.println("***************\nLOGIN COOKIES: "+ loginApi.getCookies()+"\n\n*********************");
//        CartApi cartApi = new CartApi();
//        cartApi.addToCart(1215,1);
//        System.out.println("*****************CART API COOKIES: " +cartApi.getCookies());


        BillingAddress[] billing = new BillingAddress[]{JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class)};
        for (BillingAddress b : billing){
            System.out.println("Address: "+b.toString());
        }
    }
}
