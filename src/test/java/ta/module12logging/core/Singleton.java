package ta.module12logging.core;

import org.openqa.selenium.WebDriver;

public class Singleton {

	private static WebDriver driver;

	private Singleton() {
	}

	public static WebDriver getCromeDriverIns() {
		if (driver == null) {

			driver = new ChromeCreator().createDriver();
		}
		return driver;
	}

	public static WebDriver getFirefoxDriverIns() {
		if (driver == null) {

			driver = new FirefoxCreator().createDriver();
		}
		return driver;
	}
}
