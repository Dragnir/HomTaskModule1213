package ta.module12logging.core;

import org.openqa.selenium.WebDriver;

public abstract class DriverCreator {
	protected WebDriver driver;

	public abstract WebDriver createDriver();
}
