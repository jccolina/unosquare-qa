package core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends AbstractPage {

    @FindBy(css = "[href*='/shop']")
    private WebElement comprarTab;

    public SearchResultsPage() {
        super();
    }

    public void clickComprarTab() {
        this.click(comprarTab);

    }
}
