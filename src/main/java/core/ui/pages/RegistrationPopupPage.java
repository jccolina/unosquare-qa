package core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPopupPage extends AbstractPage {

    @FindBy(css = ".sfw-dialog")
    private WebElement popupContainer;
    @FindBy(css = ".sfw-dialog .glyph-cancel")
    private WebElement closeButton;

    public RegistrationPopupPage() {
        super();
    }

    public void closeRegistrationPopup() {
        if (this.isElementVisible(popupContainer)) {
            this.click(closeButton);
        }
    }
}
