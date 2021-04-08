package framework.base;

import org.apache.logging.log4j.LogManager;
import org.testng.Reporter;

public class MyLogger {

    private static MyLogger myLogger;
    private static org.apache.logging.log4j.Logger log = LogManager.getLogger();

    private MyLogger() {
    }

    public static MyLogger getMyLogger() {
        if (myLogger == null) {
            myLogger = new MyLogger();
        }
        return myLogger;
    }

    public void info(Object message) {
        Reporter.log(message.toString());
        log.info(message);
    }

    public void error(Object message) {
        Reporter.log(message.toString());
        log.error(message);
    }

    public void error(Object message, Throwable error) {
        Reporter.log(message.toString());
        log.error(message, error);
    }
}
