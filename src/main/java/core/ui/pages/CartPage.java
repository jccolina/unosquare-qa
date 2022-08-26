package core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(css = "[class*='lineItemRow']")
    private List<WebElement> itemsList;
    @FindBy(css = ".c-group button:nth-child(1)")
    private WebElement removeItemLink;
    @FindBy(css = ".c-paragraph-2")
    private WebElement emptyMessageText;
    @FindBy(css = ".cart-route-container h1[class*='header']")
    private WebElement cartHeader;

    public CartPage() {
        super();
    }

    public int getNumberOfItemsInCart() {
        return itemsList.size();
    }

    public void removeItemFromCart() {
        this.click(removeItemLink);
    }

    public String getEmptyMessage() {
        return this.getText(emptyMessageText).trim();
    }

}
