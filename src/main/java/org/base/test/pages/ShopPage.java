package org.base.test.pages;

import org.base.test.drivers.DriverSingleton;
import org.base.test.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShopPage {
    private WebDriver driver;
    public ShopPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#main > ul > li.product.type-product.post-211.status-publish.first.instock.product_cat-uncategorized.purchasable.product-type-simple > a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart")
    private WebElement addToCartButton;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a > span")
    private WebElement numberOfProducts;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a")
    private WebElement cartButton;

    @FindBy(css = "#main > div.shop-top-info-wrapper > form > select")
    private WebElement navigation;

    @FindBy(id = "menu-item-1310")
    private WebElement shopButton;

    public void setNavigation(){
        Select select = new Select(navigation);
        select.selectByIndex(4);
    }
    public void addElementToCart(){
        shopButton.click();
        setNavigation();
        addToCartButton.click();
        if(numberOfProducts.getText().contains(Constants.CART_QUANTITY)){
            System.out.println("Cart has been updated");
        }else
            System.out.println("Cart has not been updated");
    }

    public void proceedToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        cartButton.click();
    }
}
