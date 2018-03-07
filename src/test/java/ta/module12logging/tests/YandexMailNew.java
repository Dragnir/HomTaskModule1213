package ta.module12logging.tests;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ta.module12logging.bobjects.UserAccount;
import ta.module12logging.core.EnumDrivers;
import ta.module12logging.core.WebDriverFactory;
import ta.module12logging.pages.NewMailPage;
import ta.module12logging.pages.YandexGeneralPage;
import ta.module12logging.pages.YandexLoginPage;
import ta.module12logging.utils.MyLogger;
import ta.module12logging.utils.ValidationUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class YandexMailNew {

    private static final String URL_YANDEX = "https://passport.yandex.by/passport?mode=auth&from=mail&retpath=https%3A%2F%2Fmail.yandex.by&origin=hostroot_by_nol_mobile_enter";
    private WebDriver driver;
    private UserAccount account;
    private String login = "vadim.kuryan.vka";
    private String password = "Vka_6463296";

    @BeforeClass(description = "Start Browser")
    public void getUrl(){

        driver = WebDriverFactory.getDriverIns(EnumDrivers.CHROME);

        // Go to yandex mail
        MyLogger.info("Going to URL: " + URL_YANDEX);
        driver.get(URL_YANDEX);

        // Set timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(description = "Test Yandex mail")
    public void CreateMail() {

        account = new UserAccount();
        account.setLogin(login);
        account.setPassword(password);

        YandexLoginPage loginPage = new YandexLoginPage(driver);
        ValidationUtil valUtil = new ValidationUtil();

        loginPage.loginUser(account);

        YandexGeneralPage generPage = new YandexGeneralPage(driver);
        generPage.createMail();

        NewMailPage newMail = new NewMailPage(driver);
        newMail.setMailAdress();
        newMail.setMailAdress2();
        newMail.setMailTheme();
        newMail.saveAsDraft();

        valUtil.logAssert(generPage.isSavedMailDisp(), "Mail was saved");
        valUtil.logAssert(generPage.isContentMails(), "Content present");

        generPage.sendMail();

        valUtil.logAssert(generPage.isSentMail(), "Mail was send");
        valUtil.logAssert(generPage.checkSendMail(), "Draft folder is empty");

        }

    @Test(description = "Ignored test")
    @Ignore
    public void ignoredTest() {}

    @Test(description = "Failed test")
    public void failedTest() {

        MyLogger.error(" Second Test is failed");
        Assert.fail();

    }

    @AfterClass(description = "Close Browser")
    public  void quitDriver() {

        // Logout from yandex mail.
        driver.quit();

    }
}
