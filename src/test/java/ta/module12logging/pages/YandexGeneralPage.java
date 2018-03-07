package ta.module12logging.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ta.module12logging.utils.MyLogger;

import java.util.concurrent.TimeUnit;

public class YandexGeneralPage extends AbstractPage {

    private static final String DRAFT_FOLDER = "//*[text()='Test mail']";
    private static final String SEND_BUT = "//*[text()='Отправить']";
    private static final String DRAFT_EMPTY = "//*[text()='В папке «Черновики» нет писем.']";
    public static MyLogger logger = new MyLogger();

    @FindBy(className = "mail-ComposeButton-Text")
    private WebElement newMailButton;

    @FindBy(xpath  = "//*[text()='Черновики']")
    private WebElement draft;

    @FindBy(xpath  = "//*[text()='Отправленные']")
    private WebElement sentMail;

    public YandexGeneralPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void createMail() {

        // Write new mail
        MyLogger.info("Create new mail by click on button: " + newMailButton.getText());
        newMailButton.click();

    }

    public boolean isSavedMailDisp() {

        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement savedMail = driver.findElement(By.xpath(DRAFT_FOLDER));
        hightlightElement(savedMail);
        savedMail.click();
        return savedMail.isDisplayed();
    }


    public boolean isContentMails() {

        // Check content of the saved mail present in Draft folder
        WebElement contetMail = driver.findElement(By.xpath(DRAFT_FOLDER));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return contetMail.isDisplayed();
    }

    public void sendMail() {
        // Send mail
        WebElement sendMail = driver.findElement(By.xpath(SEND_BUT));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", sendMail);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    }

    public boolean isSentMail() {

        // Check content of the saved mail present in Draft folder
        sentMail.click();
        WebElement sentMail = driver.findElement(By.xpath(DRAFT_FOLDER));
        MyLogger.info("Checked that mail sent in folder " + sentMail.getText());

        return sentMail.isDisplayed();
    }

    public boolean checkSendMail(){

        // Go to draft folder and check that mail is Not available
        draft.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement emptyFolder = driver.findElement(By.xpath(DRAFT_EMPTY));
        MyLogger.info("Checked that mail sent in folder " + draft.getText());
        logger.takeScreenshot(driver);
        return emptyFolder.isDisplayed();

    }

}

