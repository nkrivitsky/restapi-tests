package framework.base;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.TestResult;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static MyLogger logger = MyLogger.getMyLogger();
    private static Date suiteStartTime;
    private static PropertiesResourceManager messages = new PropertiesResourceManager("messages");

    protected static String formatMessage(String key, Object... args) {
        return String.format(getMessage(key), args);
    }

    private static String getMessage(String key) {
        return messages.getProperty(key);
    }

    private static String formatDuration(long milliseconds) {
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds - TimeUnit.HOURS.toMillis(hours));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds - TimeUnit.HOURS.toMillis(hours) - TimeUnit.MINUTES.toMillis(minutes));
        return formatMessage("baseTest.durationFormat", hours, minutes, seconds);
    }

    @BeforeSuite
    public static void beforeSuite(ITestContext testContext) {
        logger.info(formatMessage("baseTest.suiteStart", testContext.getSuite().getName()));
        suiteStartTime = new Date();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info(formatMessage("baseTest.testStart", method.getName()));
    }


    @AfterMethod
    public void afterMethod(Method method, ITestResult testResult) {
        TestStatus testStatus;
        if (testResult.getStatus() == TestResult.SUCCESS) {
            testStatus = TestStatus.PASSED;
        } else {
            testStatus = TestStatus.FAILED;
        }
        logger.info(formatMessage("baseTest.testEnd", method.getName(), testStatus.getName()));
    }

    @AfterSuite
    public static void afterSuite(ITestContext testContext) {
        logger.info(formatMessage("baseTest.suiteEnd", testContext.getSuite().getName(),
                formatDuration(new Date().getTime() - suiteStartTime.getTime())));
    }
}
