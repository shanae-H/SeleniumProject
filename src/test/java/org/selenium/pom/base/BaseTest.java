package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.utils.CookieUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.List;

public class BaseTest {
    private ThreadLocal <WebDriver> driver= new ThreadLocal<>();

    private void setDriver(WebDriver driverLocal){
        this.driver.set(driverLocal);
    }
    protected WebDriver getDriver(){
        return this.driver.get();
    }


    @Parameters("browser")
    @BeforeMethod
    public void startDriver(String browser){
        browser = System.getProperty("browser",browser);
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD: "+ Thread.currentThread().threadId()+", "+"DRIVER = "+ getDriver());
//        driver = new DriverManager().initializeDriver(browser);
    }

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("CURRENT THREAD: "+ Thread.currentThread().threadId()+", "+"DRIVER = "+ getDriver());
        getDriver().quit();
       // driver.quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }



}
