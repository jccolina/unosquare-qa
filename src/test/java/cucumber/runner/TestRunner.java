package cucumber.runner;

import core.utils.Constants;
import core.utils.EnvReader;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/scenarios",
        glue = "cucumber",
//        tags = "@ui",
        monochrome = true,
        plugin = {
                "pretty",
                "json:reports/json/tests-output.json",
                //HTML Report generated on ./reports folder
                "me.jvt.cucumber.report.PrettyReports:reports"
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {
    @BeforeClass(alwaysRun = true)
    @Parameters({"threadcount"})
    public void openBrowser(String threadcount) {
        System.setProperty("dataproviderthreadcount", threadcount);
        System.out.println("browser: " + EnvReader.getInstance().getValue(Constants.BROSWER_JSON_PATH));
        System.out.println("webUrl: " + EnvReader.getInstance().getValue(Constants.WEB_BASE_URL_JSON_PATH));
        System.out.println("apiUrl: " + EnvReader.getInstance().getValue(Constants.API_BASE_URL_JSON_PATH));
        System.out.println("threadcount: " + threadcount);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
