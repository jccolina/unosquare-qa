package cucumber.hooks;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;
/**
 * Listener to implement custom retry functionality.
 */
public class Retry implements IRetryAnalyzer {
    private static int maxTry = 1;
    private static Map<String, Integer> retryCount = new HashMap<>();

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            // It takes scenario name as unique Id, if two scenarios have same description
            // conflicts could happen
            Object[] params = iTestResult.getParameters();
            String methodId = params[0].toString();
            Integer count = retryCount.get(methodId);
            if (count == null) {
                retryCount.put(methodId, 0);
                count = 0;
            }
            if (count < maxTry) {
                iTestResult.setStatus(ITestResult.SKIP);
                retryCount.put(methodId, ++count);
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
