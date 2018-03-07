package ta.module12logging.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ta.module12logging.bobjects.NewMail;
import ta.module12logging.utils.MyLogger;

import java.util.concurrent.TimeUnit;

public class NewMailPage extends AbstractPage {

    private static final String TEST_THEME = "Test mail";
    private static final String NEW_MAIL = "dragnir@tut.by";
    private static final String NEW_MAIL2 = " vadim.kuryan@gmail.com";
    private static final By SEND_TO_FIELD_LOCATOR = By.name("to");
    private static final By COPY_TO_FIELD_LOCATOR = By.name("cc");
    private static final By SUBJECT_LOCATOR = By.name("subj");
    private static final String MAIL_ADRESS = "//*[text()='dragnir@tut.by']";
    private NewMail newMail;
    public static MyLogger logger = new MyLogger();

    @FindBy(xpath  = "//*[text()='Черновики']")
    private WebElement draft;

    @FindBy(xpath  = "//*[text()='Сохранить и перейти']")
    private WebElement saveAndGoButton;

    @FindBy(xpath  = "//*[text()='Копия']")
    private WebElement copyButton;

    public NewMailPage(WebDriver driver) {
        super(driver);
        newMail = new NewMail(NEW_MAIL, NEW_MAIL2, TEST_THEME);
        PageFactory.initElements(driver, this);
    }

    public void setMailAdress() {

        // Set mail address
        WebElement mailAddress = driver.findElement(SEND_TO_FIELD_LOCATOR);
        MyLogger.info("Set mail address: " + NEW_MAIL);
        hightlightElement(mailAddress);
        mailAddress.sendKeys(newMail.getNewMail());
    }

    public void setMailAdress2() {

        // Set mail address
        WebElement mailAddress2 = driver.findElement(SEND_TO_FIELD_LOCATOR);
        hightlightElement(mailAddress2);
        MyLogger.info("Set second mail address: " + NEW_MAIL2);
        mailAddress2.sendKeys(newMail.getNewMail2());
    }

    public void setCopyAddress() {

        // Set copy mail address
        copyButton.click();
        WebElement mailAddress = driver.findElement(By.xpath(MAIL_ADRESS));
        hightlightElement(mailAddress);

        WebElement copyAddress = driver.findElement(COPY_TO_FIELD_LOCATOR);
        hightlightElement(copyAddress);

        new Actions(driver).dragAndDrop(mailAddress, copyAddress).build().perform();

        setMailAdress2();

    }

    public void setMailTheme() {

        // Set mail theme
        WebElement themeMail = driver.findElement(SUBJECT_LOCATOR);
        hightlightElement(themeMail);
        MyLogger.info("Set mail theme: " + newMail.getTestTheme());
        themeMail.sendKeys(newMail.getTestTheme());
    }

    public void saveAsDraft() {

        // Save as a draft
        logger.takeScreenshot(driver);
        draft.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        saveAndGoButton.click();

    }
}
