package core.ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WindowsPage extends AbstractPage {

    @FindBy(css = "#search")
    private WebElement searchButton;
    @FindBy(css = "#cli_shellHeaderSearchInput")
    private WebElement searchBoxInput;

    public WindowsPage() {
        super();
    }

    public void searchByKeyword(String keyword) {
        this.click(searchButton);
        this.sendText(searchBoxInput, keyword);
        this.sendText(searchBoxInput, Keys.ENTER);
    }
}
