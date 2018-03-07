package ta.module12logging.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

public class ChromeCreator extends DriverCreator {

	@Override
	public WebDriver createDriver() {

		ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(
				new File("d:\\_WebDriver\\chromedriver.exe")).build();
		try {
			service.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = new ChromeDriver(service);
		return driver;
	}
}
