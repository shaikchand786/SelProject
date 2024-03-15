package nextGenerationAutomationLearnTestNG;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
	@Test (dataProvider="getData")
	public void loginTest(String Uid, String PWD){
		System.out.println("UserName is "+ Uid);
		System.out.println("Password is "+ PWD);
	}
	@DataProvider(name="getData")
	public Object[][] getData() {
		Object[][] data1 = {{"FirstUid","FirstPWD"},{"SecUid","SecPWD"}};
		
			Object[][] data = new Object[2][2];
			data[0][0] = "FirstUid";
			data[0][1] = "FirstPWD";
			data[1][0] = "SecondUid";
			data[1][1] = "SecondPWD";
			return data1;
		}
	}
