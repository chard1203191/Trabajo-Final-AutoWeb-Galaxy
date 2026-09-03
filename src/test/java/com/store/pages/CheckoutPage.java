package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    private By billingFirstNameInput = By.id("BillingNewAddress_FirstName");
    private By billingLastNameInput = By.id("BillingNewAddress_LastName");
    private By billingEmailInput = By.id("BillingNewAddress_Email");
    private By billingCountrySelect = By.id("BillingNewAddress_CountryId");
    private By billingCityInput = By.id("BillingNewAddress_City");
    private By billingAddressInput = By.id("BillingNewAddress_Address1");
    private By billingZipCodeInput = By.id("BillingNewAddress_ZipPostalCode");
    private By billingPhoneInput = By.id("BillingNewAddress_PhoneNumber");
    private By billingAddressNextButton = By.id("billingaddress-next-button");
    private By shippingMethodOption = By.cssSelector("input[name='shippingoption']");
    private By shippingMethodNextButton = By.cssSelector("button.shipping-method-next-step-button");
    private By paymentMethodOption = By.cssSelector("input[name='paymentmethod']");
    private By paymentMethodNextButton = By.cssSelector("button.payment-method-next-step-button");
    private By paymentInfoNextButton = By.cssSelector("button.payment-info-next-step-button");
    private By confirmOrderButton = By.cssSelector("button.confirm-order-next-step-button");
    private By orderCompletedMessage = By.cssSelector(".order-completed");
    private By orderNumber = By.cssSelector(".order-number");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillBillingAddress(String firstName, String lastName, String email, String country, String city,
                                   String address, String zipCode, String phone) {
        type(billingFirstNameInput, firstName);
        type(billingLastNameInput, lastName);
        type(billingEmailInput, email);
        selectCountry(country);
        type(billingCityInput, city);
        type(billingAddressInput, address);
        type(billingZipCodeInput, zipCode);
        type(billingPhoneInput, phone);
    }

    public void continueBillingAddress() {
        click(billingAddressNextButton);
    }

    public void selectFirstShippingMethod() {
        click(shippingMethodOption);
    }

    public void continueShippingMethod() {
        click(shippingMethodNextButton);
    }

    public void selectFirstPaymentMethod() {
        click(paymentMethodOption);
    }

    public void continuePaymentMethod() {
        click(paymentMethodNextButton);
    }

    public void continuePaymentInfo() {
        click(paymentInfoNextButton);
    }

    public void confirmOrder() {
        click(confirmOrderButton);
    }

    public String getOrderCompletedMessage() {
        return text(orderCompletedMessage);
    }

    public String getOrderNumber() {
        return text(orderNumber);
    }

    private void selectCountry(String country) {
        Select countrySelect = new Select(find(billingCountrySelect));
        countrySelect.selectByVisibleText(country);
    }
}
