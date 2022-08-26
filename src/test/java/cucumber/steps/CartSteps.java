package cucumber.steps;

import core.ui.pages.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSteps {
    private JuegosPage aplicacionesPage;
    private ItemPage itemPage;
    private CartPage cartPage;
    private HeaderPage headerPage;
    private RegistrationPopupPage registrationPopupPage;

    public CartSteps(JuegosPage aplicacionesPage, ItemPage itemPage, CartPage cartPage, HeaderPage headerPage, RegistrationPopupPage registrationPopupPage){
        this.aplicacionesPage = aplicacionesPage;
        this.itemPage = itemPage;
        this.cartPage = cartPage;
        this.headerPage = headerPage;
        this.registrationPopupPage = registrationPopupPage;
    }
    @When("add non-free item to cart")
    public void addNonFreeItemToCart() {
        this.itemPage.addItemToCart();
    }

    @Then("single item should be on cart")
    public void singleItemShouldBeOnCart() {
        String expectedNumberOfItemsOnIcon = "1";
        String actualNumberOfItemsOnIcon = this.headerPage.getCartIconAmount();
        Assert.assertEquals(expectedNumberOfItemsOnIcon, actualNumberOfItemsOnIcon);
        int actualNumberOfItems = this.cartPage.getNumberOfItemsInCart();
        int expectedNumberOfItems = 1;
        Assert.assertEquals(expectedNumberOfItems, actualNumberOfItems);
    }

    @When("delete item from cart")
    public void deleteItemFromCart() {
        this.cartPage.removeItemFromCart();
    }

    @Then("cart displays empty message {string}")
    public void cartDisplaysEmptyMessage(String expectedEmptyMessage) {
        String actualEmptyMessage = this.cartPage.getEmptyMessage();
        Assert.assertEquals(expectedEmptyMessage, actualEmptyMessage);
    }
}
