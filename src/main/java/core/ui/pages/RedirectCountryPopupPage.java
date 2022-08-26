package core.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RedirectCountryPopupPage extends AbstractPage {

    private By popupContainer = By.cssSelector("#R1MarketRedirect");
    @FindBy(css = "#R1MarketRedirect-close")
    private WebElement stayInMexicoButton;

    public RedirectCountryPopupPage() {
        super();
    }

    public void closePopup() {
        if (this.isElementPresent(popupContainer)) {
            this.waitForAttribute(popupContainer, "aria-hidden", "false");
            this.click(stayInMexicoButton);
            this.waitForAttribute(popupContainer, "aria-hidden", "true");
        }

    }
}
