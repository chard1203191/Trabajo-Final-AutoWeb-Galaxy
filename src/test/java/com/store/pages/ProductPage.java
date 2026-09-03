package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {

    private By quantityInput = By.cssSelector("input.qty-input[id^='product_enteredQuantity_']");
    private By addToCartButton = By.cssSelector("button.add-to-cart-button[id^='add-to-cart-button-']");
    private By successNotification = By.cssSelector(".bar-notification.success");
    private By closeNotificationButton = By.cssSelector(".bar-notification.success .close");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void openProduct(String productName) {
        click(productLinkByName(productName));
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
        return By.xpath("//h2[contains(@class,'product-title')]/a[normalize-space()='" + productName + "']");
    }

    private By productItemByName(String productName) {
        return By.xpath("//div[contains(@class,'product-item')][.//h2[contains(@class,'product-title')]/a[normalize-space()='"
                + productName + "']]");
    }
}
