package core.ui.pages;

import core.ui.driver.DriverManager;
import core.utils.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

/**
 * Abstract class to initialize webdriver which will be shared with all child page objects.
 * Also groups common actions over web elements like click, send text, etc.
 */
public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(Constants.WAIT_TIMEOUT_IN_SECONDS));
        PageFactory.initElements(this.driver, this);
    }

    public void click(final By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void click(final WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void clickOnIntercepted(final WebElement element) {
        wait.ignoring(ElementClickInterceptedException.class)
                        .ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                element.click();
                return element;
            }
        });
    }

    public void sendText(final WebElement element, final Keys keys) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public void sendText(final WebElement element, final String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public String getText(final By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element.getText();
    }

    public String getText(final WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public boolean isElementVisible(final WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        List<WebElement> foundElements = driver.findElements(locator);
        return foundElements.size() > 0;
    }

    public void waitForAttribute(By locator, String attribute, String value) {
        wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

}
