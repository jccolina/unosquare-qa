package core.ui.driver;

import core.ui.browser.*;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
/**
 * Returns browser type: Chrome or Firefox.
 */

public class DriverFactory {
    private static final Map<BrowserName, Supplier<Browser>> DRIVERS = new HashMap<>();

    static {
        DRIVERS.put(BrowserName.CHROME, Chrome::new);
        DRIVERS.put(BrowserName.FIREFOX, Firefox::new);
    }

    public static WebDriver getDriver(final BrowserName browser) {
        return DRIVERS.get(browser).get().getDriver();
    }
}
