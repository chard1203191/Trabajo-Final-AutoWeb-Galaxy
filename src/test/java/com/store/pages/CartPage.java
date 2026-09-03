package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private By updateCartButton = By.id("updatecart");
    private By termsOfServiceCheckbox = By.id("termsofservice");
    private By checkoutButton = By.id("checkout");
    private By emptyCartMessage = By.cssSelector(".no-data");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        return isDisplayedElement(cartRowByProductName(productName));
    }

    public String getProductQuantity(String productName) {
        WebElement cartRow = find(cartRowByProductName(productName));
        return cartRow.findElement(By.cssSelector("input.qty-input[name^='itemquantity']")).getAttribute("value");
    }

    public void changeProductQuantity(String productName, String quantity) {
        WebElement cartRow = find(cartRowByProductName(productName));
        WebElement quantityInput = cartRow.findElement(By.cssSelector("input.qty-input[name^='itemquantity']"));
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        updateCart();
    }

    public void removeProduct(String productName) {
        WebElement cartRow = find(cartRowByProductName(productName));
        cartRow.findElement(By.cssSelector("button.remove-btn")).click();
    }

    public void updateCart() {
        click(updateCartButton);
    }

    public void acceptTermsOfService() {
        click(termsOfServiceCheckbox);
    }

    public void startCheckout() {
        click(checkoutButton);
    }

    public String getEmptyCartMessage() {
        return text(emptyCartMessage);
    }

    private By cartRowByProductName(String productName) {
        return By.xpath("//table[contains(@class,'cart')]//tr[.//*[contains(@class,'product-name') and normalize-space()='"
                + productName + "']]");
    }
}
