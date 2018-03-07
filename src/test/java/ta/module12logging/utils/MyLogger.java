package ta.module12logging.utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLogger {

    private static final Logger LOGGER = Logger.getLogger(MyLogger.class);
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";
    private WebDriver driver;

    public void takeScreenshot(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "./target/screenshots/" + getTimeStamp() + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
        } catch (IOException e) {
            MyLogger.error("Failed to make screenshot" + e.getMessage());
        }
        MyLogger.info(" Screenshot saved at: " + screenshotPath);
    }

    private String getTimeStamp() {
        return new SimpleDateFormat("yyyy_dd-MMM_HH_mm_sss").format(new Date());
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] captureScreenshot(WebDriver driver) {
        byte[] screenshot = null;
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

}
