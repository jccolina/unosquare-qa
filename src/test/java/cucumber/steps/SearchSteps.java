package cucumber.steps;

import core.ui.pages.*;
import core.utils.Constants;
import core.utils.EnvReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchSteps {
    private HomePage home;
    private WindowsPage windowsPage;
    private SearchResultsPage searchResultsPage;
    private SearchResultsComprarTabPage searchResultsComprarTabPage;
    private RedirectCountryPopupPage redirectCountryPopupPage;
    private JuegosPage juegosPage;
    private ItemPage itemPage;
    private RegistrationPopupPage registrationPopupPage;

    public SearchSteps(HomePage home, WindowsPage windowsPage, SearchResultsPage searchResultsPage, SearchResultsComprarTabPage searchResultsComprarTabPage, RedirectCountryPopupPage redirectCountryPopupPage, JuegosPage juegosPage, ItemPage itemPage, RegistrationPopupPage registrationPopupPage) {
        this.home = home;
        this.windowsPage = windowsPage;
        this.searchResultsPage = searchResultsPage;
        this.searchResultsComprarTabPage = searchResultsComprarTabPage;
        this.redirectCountryPopupPage = redirectCountryPopupPage;
        this.juegosPage = juegosPage;
        this.itemPage = itemPage;
        this.registrationPopupPage = registrationPopupPage;
    }

    @Given("search by keyword on Windows tab")
    public void searchByKeywordOnWindowsTab() {
        this.home.open();
        this.home.clickWindowsTab();
        String keyword = EnvReader.getInstance().getValue(Constants.SEARCH_KEYWORD_JSON_PATH);
        this.windowsPage.searchByKeyword(keyword);
    }

    @And("select Comprar tab in search results")
    public void selectComprarTabInSearchResults() {
        this.redirectCountryPopupPage.closePopup();
        this.searchResultsPage.clickComprarTab();
    }

    @When("select Juegos on category menu")
    public void selectJuegosOnCategoryMenu() {
        this.searchResultsComprarTabPage.clickJuegosCategory();
    }

    @Then("print items in the first {int} pages")
    public void printItemsInTheFirstPages(int numberOfPagesToPrint) {
        int sumOfItemsInAllPages = 0;
        for (int i = 0; i < numberOfPagesToPrint; i++) {
            this.juegosPage.clickPageNumber(i + 1);
            int numberOfItems = this.juegosPage.getNumberOfItems();
            sumOfItemsInAllPages += numberOfItems;
            String[] itemsTitles = this.juegosPage.getItemsTitles();
            System.out.println("Number of items in page #" + (i + 1) + " is: " + numberOfItems);
            System.out.println("List of item titles in page #" + (i + 1));
            for (String itemTitle : itemsTitles) {
                System.out.println("- " + itemTitle);
            }
        }
        System.out.println("Total number of items in " + numberOfPagesToPrint + " pages is : " + sumOfItemsInAllPages);
    }

    @And("verify non-free item's price in category page matches price in item page")
    public void verifyNonFreeItemSPriceInCategoryPageMatchesPriceInItemPage() {
        this.juegosPage.clickPageNumber(1);
        String firstItemPrice = this.juegosPage.getPriceFromItem(Constants.DEFAULT_ITEM_TITLE);
        this.juegosPage.clickItemWithTitle(Constants.DEFAULT_ITEM_TITLE);
        this.registrationPopupPage.closeRegistrationPopup();
        String priceInItemPage = this.itemPage.getItemPrice();
        Assert.assertEquals(firstItemPrice, priceInItemPage);
    }
}
