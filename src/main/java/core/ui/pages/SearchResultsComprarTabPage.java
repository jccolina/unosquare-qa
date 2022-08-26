package core.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsComprarTabPage extends AbstractPage {

    @FindBy(css = "[id*='refine-by-menu-title'] [href*='/games']")
    private WebElement juegosCategoryMenuItem;

    public SearchResultsComprarTabPage() {
        super();
    }

    public void clickJuegosCategory() {
        this.click(juegosCategoryMenuItem);

    }
}
