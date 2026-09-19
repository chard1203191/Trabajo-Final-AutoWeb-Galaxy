package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private By updateCartButton = By.id("updatecart");
    private By magentoUpdateCartButton = By.name("update_cart_action");
    private By magentoQuantityInput = By.cssSelector(".cart.table-wrapper input[name$='[qty]']");
    private By productSubtotal = By.cssSelector(".cart.item .subtotal .price");
    private By termsOfServiceCheckbox = By.id("termsofservice");
    private By checkoutButton = By.cssSelector("button[data-role='proceed-to-checkout']");
    private By emptyCartMessage = By.cssSelector(".no-data");
    private By miniCartRemoveButton = By.cssSelector("#minicart-content-wrapper a.action.delete");
    private By removeConfirmationMessage = By.cssSelector(".modal-popup.confirm._show .modal-content");
    private By confirmRemoveButton = By.cssSelector(".modal-popup.confirm._show button.action-primary.action-accept");
    private By emptyMiniCartMessage = By.cssSelector("#minicart-content-wrapper .subtitle.empty");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        return isDisplayedElement(By.cssSelector(".cart.table-wrapper .product-item-name a[href*='"
                + productSlug(productName) + ".html']"));
    }

    public boolean isProductInCart(String productName, String productSlug) {
        By productLink = By.cssSelector(".cart.table-wrapper .product-item-name a[href$='"
                + productSlug + ".html']");
        return isDisplayedElement(productLink) && text(productLink).equals(productName);
    }

    public boolean isProductInMiniCart(String productName) {
        return isDisplayedElement(By.cssSelector("#minicart-content-wrapper .product-item-name a[href*='"
                + productSlug(productName) + ".html']"));
    }

    public String getProductQuantity(String productName) {
        return find(magentoQuantityInput).getAttribute("value");
    }

    public void changeProductQuantity(String productName, String quantity) {
        type(magentoQuantityInput, quantity);
        updateCart();
    }

    public void removeProduct(String productName) {
        WebElement cartRow = find(cartRowByProductName(productName));
        cartRow.findElement(By.cssSelector("button.remove-btn")).click();
    }

    public void removeProductFromMiniCart(String productName) {
        isProductInMiniCart(productName);
        click(miniCartRemoveButton);
    }

    public String getRemoveConfirmationMessage() {
        return text(removeConfirmationMessage);
    }

    public void confirmProductRemoval() {
        click(confirmRemoveButton);
    }

    public void updateCart() {
        if (isDisplayedElement(magentoUpdateCartButton)) {
            click(magentoUpdateCartButton);
        } else {
            click(updateCartButton);
        }
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

    public String getProductSubtotal(String productName) {
        return text(productSubtotal);
    }

    public String getProductSubtotal(String productName, String expectedSubtotal) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(productSubtotal, expectedSubtotal));
        return text(productSubtotal);
    }

    public String getEmptyMiniCartMessage() {
        return text(emptyMiniCartMessage);
    }

    private By cartRowByProductName(String productName) {
        return By.xpath("//table[contains(@class,'cart')]//tr[.//*[contains(@class,'product-name') and normalize-space()='"
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
