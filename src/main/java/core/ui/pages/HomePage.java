package core.ui.pages;

import core.utils.Constants;
import core.utils.EnvReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(css = "#shellmenu_2")
    private WebElement windowsTab;

    public HomePage() {
        super();
    }

    public void open() {
        this.driver.get(EnvReader.getInstance().getValue(Constants.WEB_BASE_URL_JSON_PATH));

    }

    public void clickWindowsTab() {
        this.click(windowsTab);

    }
}
