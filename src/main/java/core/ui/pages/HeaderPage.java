package core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends AbstractPage {

    @FindBy(css = ".shopping-cart-amount")
    private WebElement cartIconAmount;

    public HeaderPage() {
        super();
    }

    public String getCartIconAmount() {
        return this.getText(cartIconAmount);
    }
}
