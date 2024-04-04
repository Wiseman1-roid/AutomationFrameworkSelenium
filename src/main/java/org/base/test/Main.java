package org.base.test;

import org.base.test.drivers.DriverSingleton;
import org.base.test.pages.*;
import org.base.test.utils.FrameworkProperties;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton = DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://bitheap.tech/");

        HomePage homePage = new HomePage();
        SignInPage signInPage = new SignInPage();
        homePage.clickSignIn();

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/framework.properties");){
            properties.load(input);
        } catch (Exception e) {
            System.out.println("File not found");
        }

        signInPage.logIn("laurentiu@bitheap.tech",properties.getProperty("password"));

        if (homePage.getUsername().equals("Hello, Laurentiu"))
            System.out.println("Test passed");
        else
            System.out.println("Test failed");

        ShopPage shopPage = new ShopPage();
        shopPage.addElementToCart();
        shopPage.proceedToCheckout();

        CartPage cartPage = new CartPage();
        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();

        if (checkoutPage.getOrderStatus().equals("Order received")){
            System.out.println("Order received");
        }else
            throw new RuntimeException();

//        DriverSingleton.closeObjectInstance();
    }
}
