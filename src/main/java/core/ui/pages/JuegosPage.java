package core.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class JuegosPage extends AbstractPage {

    private final String PAGINATION_NUMBER_LOCATOR = "//ul[@class='m-pagination']//li[*[text()='%d']]";
    private final String ITEM_PRICE_BY_TITLE = "//*[contains(@id,'coreui-productplacementlist') and h3[contains(text(),'%s')]]//*[@itemprop='price']";
    @FindBy(css = ".m-channel-placement-item")
    private List<WebElement> itemsList;
    @FindBy(css = ".m-channel-placement-item [id*='coreui-productplacementlist'] h3")
    private List<WebElement> itemsTitleList;
    @FindBy(css = ".c-price span s, [itemprop='price'][content*='$']")
    private WebElement firstItemPrice;
    @FindBy(css = ".m-pagination")
    private WebElement paginationContainer;
    private String ITEM_BY_NAME_LOCATOR = "//*[contains(@id,'coreui-productplacementlist')]//*[contains(text(),'%s')]";

    public JuegosPage() {
        super();
    }

    public void clickPageNumber(final int pageNumber) {
        By pageNumberLocator = By.xpath(String.format(PAGINATION_NUMBER_LOCATOR, pageNumber));
        this.click(pageNumberLocator);
    }

    public int getNumberOfItems() {
        return itemsList.size();
    }

    public String[] getItemsTitles() {
        String[] itemsTitles = itemsTitleList.stream().map(WebElement::getText).toArray(String[]::new);
        return itemsTitles;
    }

    public void clickItemWithTitle(String title) {
        By itemTitleLocator = By.xpath(String.format(ITEM_BY_NAME_LOCATOR, title));
        this.click(itemTitleLocator);
    }

    public String getPriceFromItem(String itemTitle) {
        By itemPriceLocator = By.xpath(String.format(ITEM_PRICE_BY_TITLE, itemTitle));
        return this.getText(itemPriceLocator);
    }
}
