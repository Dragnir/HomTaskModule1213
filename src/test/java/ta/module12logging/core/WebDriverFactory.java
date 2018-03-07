package ta.module12logging.core;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

	private static WebDriver driver;

	public static WebDriver getDriverIns(EnumDrivers browser) {

			driver = Singleton.getCromeDriverIns();

		return driver;
	}

}
