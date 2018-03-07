package ta.module12logging.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

    protected static WebDriver driver;

    public AbstractPage(WebDriver driver){

        this.driver = driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public static void hightlightElement(WebElement element) {

        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = ' " + "yellow" + "'", element);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        js.executeScript("arguments[0].style.backgroundColor = ' " + "bg" + "'", element);

    }
}
