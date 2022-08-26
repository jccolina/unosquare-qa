package core.ui.driver;

import core.ui.browser.BrowserName;
import core.utils.Constants;
import core.utils.EnvReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements singleton pattern to handle a single driver for all the pages
 */
public class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private static final List<WebDriver> STORED_DRIVERS = new ArrayList<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> STORED_DRIVERS.forEach(WebDriver::quit)));
    }

    private static DriverManager driverManager = new DriverManager();

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        return driverManager;
    }

    public static void addDriver(final WebDriver driver) {
        STORED_DRIVERS.add(driver);
        DRIVER.set(driver);
    }
    // Create new driver and add to thread safe list, if driver already exist for current thread then return current driver
    public static WebDriver getDriver() {
        WebDriver currentDriver = DRIVER.get();
        if (currentDriver == null || ((RemoteWebDriver) currentDriver).getSessionId() == null) {
            String browser = EnvReader.getInstance().getValue(Constants.BROSWER_JSON_PATH);
            WebDriver driver = DriverFactory.getDriver(BrowserName.CHROME.valueOf(browser.toUpperCase()));
            addDriver(driver);
            System.out.println("created driver: " + DRIVER.get());
        }
        return DRIVER.get();
    }
}
