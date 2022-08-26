package core.ui.browser;

import org.openqa.selenium.WebDriver;
/**
 * Interface to get driver from browser objects. In this way all kind of browsers are treated as the same type.
 */
public interface Browser {
    WebDriver getDriver();
}
