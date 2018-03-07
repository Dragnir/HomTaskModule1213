package ta.module12logging.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ta.module12logging.bobjects.UserAccount;
import ta.module12logging.core.WebElementDecor;
import ta.module12logging.utils.MyLogger;
import java.io.IOException;

public class YandexLoginPage extends AbstractPage {

    private static final String LOGIN_LOCATOR = "passport-Input-Controller";
    private static final By PASSWORD_LOCATOR = By.name("passwd");
    private static final String SUBMIT = "passport-Button-Text";
    public static MyLogger logger = new MyLogger();

    public YandexLoginPage(WebDriver driver){
        super(driver);
    }

    public void setLogin(String login) {

        // Find and set login

        WebElement loginButton = driver.findElement(By.className(LOGIN_LOCATOR));
        hightlightElement(loginButton);
        MyLogger.info("Set login: " + login);
        loginButton.sendKeys(login);
    }

    public void setPassword(String password) {

        // Find and set password
        WebElement passwordButton = driver.findElement(PASSWORD_LOCATOR);
        hightlightElement(passwordButton);
        MyLogger.info("Set password: " + password);
        passwordButton.sendKeys(password);

    }


    public void logIn() {

        // Find and click on submit button
        WebElementDecor webElSub = new WebElementDecor(driver.findElement(By.className(SUBMIT)));
        logger.takeScreenshot(driver);
        MyLogger.info("Login to mailbox ");
        webElSub.clickElement();

    }

    public void loginUser(UserAccount account){
        try {
            setLogin(account.getLogin());
            setPassword(account.getPassword());
            logIn();
        } catch (Exception e) {
            MyLogger.debug("Login or password is wrong: " + e.getMessage());
            logger.takeScreenshot(driver);
        }

    }
}
