package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    private By firstNameInput = By.name("firstname");
    private By lastNameInput = By.name("lastname");
    private By streetAddressInput = By.name("street[0]");
    private By countrySelect = By.name("country_id");
    private By regionSelect = By.name("region_id");
    private By cityInput = By.name("city");
    private By postcodeInput = By.name("postcode");
    private By telephoneInput = By.name("telephone");
    private By shippingMethodOption = By.cssSelector("input[type='radio'][value='flatrate_flatrate']");
    private By nextButton = By.cssSelector("button.continue");
    private By paymentStep = By.id("checkout-step-payment");
    private By checkMoneyOrderOption = By.id("checkmo");
    private By checkMoneyOrderLabel = By.cssSelector("label[for='checkmo']");
    private By subtotal = By.cssSelector(".opc-block-summary tr.totals.sub .price");
    private By shippingTotal = By.cssSelector(".opc-block-summary tr.totals.shipping .price");
    private By grandTotal = By.cssSelector(".opc-block-summary tr.grand.totals .price");
    private By placeOrderButton = By.cssSelector("button.action.primary.checkout[title='Place Order']");
    private By orderCompletedMessage = By.cssSelector("h1.page-title span");
    private By orderNumber = By.cssSelector(".checkout-success a.order-number");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillShippingAddress(String firstName, String lastName, String streetAddress, String country,
                                    String region, String city, String postcode, String telephone) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(streetAddressInput, streetAddress);
        selectByValue(countrySelect, country);
        selectByValue(regionSelect, region);
        type(cityInput, city);
        type(postcodeInput, postcode);
        type(telephoneInput, telephone);
    }

    public void selectShippingMethod() {
        click(shippingMethodOption);
    }

    public void continueToPayment() {
        click(nextButton);
    }

    public void waitForPaymentStep() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStep));
    }

    public String getPaymentMethodValue() {
        wait.until(ExpectedConditions.presenceOfElementLocated(checkMoneyOrderOption));
        return find(checkMoneyOrderOption).getAttribute("value");
    }

    public boolean isCheckMoneyOrderSelected() {
        wait.until(ExpectedConditions.presenceOfElementLocated(checkMoneyOrderOption));
        return find(checkMoneyOrderOption).isSelected();
    }

    public String getPaymentMethodLabel() {
        return text(checkMoneyOrderLabel);
    }

    public String getSubtotal(String expectedSubtotal) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(subtotal, expectedSubtotal));
        return text(subtotal);
    }

    public String getShippingTotal(String expectedShipping) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(shippingTotal, expectedShipping));
        return text(shippingTotal);
    }

    public String getGrandTotal(String expectedTotal) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(grandTotal, expectedTotal));
        return text(grandTotal);
    }

    public void placeOrder() {
        click(placeOrderButton);
    }

    public String getOrderCompletedMessage(String expectedMessage) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(orderCompletedMessage, expectedMessage));
        return text(orderCompletedMessage);
    }

    public String getOrderNumber() {
        return text(orderNumber);
    }

    private void selectByValue(By locator, String value) {
        Select select = new Select(find(locator));
        select.selectByValue(value);
    }
}
