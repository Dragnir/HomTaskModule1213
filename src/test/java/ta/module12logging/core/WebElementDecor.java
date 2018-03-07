package ta.module12logging.core;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;


public class WebElementDecor {

	public static WebElement webElement;

	public WebElementDecor(WebElement webElement) {
		this.webElement = webElement;
	}

	public void clickElement() {

		webElement.click();
		Reporter.log("Click was successfuly");
		System.out.println("Click was successfuly");

	}
}
