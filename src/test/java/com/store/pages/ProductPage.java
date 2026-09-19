package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {

    private By quantityInput = By.id("qty");
    private By addToCartButton = By.id("product-addtocart-button");
    private By successNotification = By.cssSelector(".page.messages .message-success div");
    private By closeNotificationButton = By.cssSelector(".bar-notification.success .close");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void openProduct(String productName) {
        click(productLinkByName(productName));
    }

    public void openProduct(String productName, String productSlug) {
        click(productLinkBySlug(productSlug));
    }

    public void addProductFromDetails() {
        click(addToCartButton);
    }

    public void addProductFromDetails(String quantity) {
        type(quantityInput, quantity);
        click(addToCartButton);
    }

    public void addProductFromSearchResult(String productName) {
        WebElement productItem = find(productItemByName(productName));
        productItem.findElement(By.cssSelector("button.product-box-add-to-cart-button")).click();
    }

    public String getSuccessNotification() {
        return text(successNotification);
    }

    public void closeNotification() {
        click(closeNotificationButton);
    }

    private By productLinkByName(String productName) {
        return By.cssSelector("a.product-item-link[href*='" + productSlug(productName) + ".html']");
    }

    private By productLinkBySlug(String productSlug) {
        return By.cssSelector("a.product-item-link[href$='" + productSlug + ".html']");
    }

    private By productItemByName(String productName) {
        return By.xpath("//div[contains(@class,'product-item')][.//h2[contains(@class,'product-title')]/a[normalize-space()='"
                + productName + "']]");
    }

    private String productSlug(String productName) {
        if ("Apple AirPods Pro 2".equals(productName)) {
            return "airpods-pro-2";
        }
        if ("Adidas Ultraboost".equals(productName)) {
            return "ultraboost-running";
        }
        if ("Ray-Ban Wayfarer".equals(productName)) {
            return "rayban-wayfarer";
        }
        return productName.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
    }
}
