package core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends AbstractPage {

    @FindBy(css = "[id*='ProductPrice_productPrice_PriceContainer'] span")
    private WebElement priceText;
    @FindBy(css = "#ButtonPanel_buttonPanel_OverflowMenuTrigger")
    private WebElement threeDotButton;
    @FindBy(css = "#buttonPanel_AddToCartButton")
    private WebElement addTocartOption;

    public ItemPage() {
        super();
    }

    public String getItemPrice() {
        return priceText.getText().replace("+", "");
    }


    public void addItemToCart() {
        this.click(threeDotButton);
        this.click(addTocartOption);
    }
}
