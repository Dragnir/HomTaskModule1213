package ta.module12logging.utils;

import org.testng.Assert;

public class ValidationUtil {

	public void logAssert(boolean var, String message){

		Assert.assertTrue(var);
		System.out.println(message);

	}

}
