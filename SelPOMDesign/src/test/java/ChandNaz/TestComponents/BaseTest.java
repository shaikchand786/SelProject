package ChandNaz.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ChandNaz.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage loginPage;
	DataFormatter formatter = new DataFormatter();
	public WebDriver initializeDriver() throws IOException  {
		//properties class
		Properties prop = new Properties();

			//How to send file as an input stream? In java, there's a class known as 'FileInputStream'.
		////to send this object into load() argument, add 'FileInputStream' class.
//		FileInputStream	fis = new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\ChandNaz\\resources\\GlobalData.properies");  //local path (runs in your system)	
//		FileInputStream	fis = new FileInputStream(System.getProperty("user+dir") + "//src//main//java//ChandNaz//resources//GlobalData.properies");
		
		FileInputStream fis = new FileInputStream(".//src//main//java//ChandNaz//resources//GlobalData.properies");	//variable path (runs in any system)
			//System.getProperty("user+dir") --> automatically get the project path which you are working
		prop.load(fis);
//		Tribrowser - Java Ternary Operator(Conditional Operator) : It takes three operands (Syntax: condition ? argument1 : argument2;)
		String BrowserName = System.getProperty("browser") !=null ? System.getProperty("browser") :prop.getProperty("browser");
		
//		String BrowserName = prop.getProperty("browser");
		if(BrowserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(BrowserName.contains("Headless")) {
				options.addArguments("Headless");	
			}
		
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		else if(BrowserName.equalsIgnoreCase("firefox")) {
			//firefox
			WebDriverManager.firefoxdriver().setup();
//			System.setProperty("webdriver.geckodriver.driver", "C:\\Selenium Project\\Firefox WebDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(BrowserName.equalsIgnoreCase("MSEdge")) {
			//Microsoft Edge
			WebDriverManager.edgedriver().setup();
//			System.setProperty("webdriver.edgedriver.driver", "C:\\Selenium Project\\MS Edge Driver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
	
	driver.manage().window().maximize();//to fit all the objects on the screen that are not visible.
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	return driver;
	}
	
	//Its a generic method that you can use in any 'Test'
	//You can write no. of Utilities to scan your Json or retrieve the values.
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//read Json to String
//		String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user+dir") + "\\src\\test\\java\\ChandNaz\\data\\PurchaseOrders.json"));
		//How to fix deprecation: We have to tell that in which format we're trying to write a string (Standard format is 'UTF_8').
		String JsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//convert String content to HashMap (add dependency 'Jackson Databind' to 'pom.xml' file)
		ObjectMapper mapper = new ObjectMapper();
			//readValue('What we want to conver', 'How we want to convert') --> output: Creating 'HashMap' using .json file
			// We asking to put 'HashMap' inside the List<> the give me.
		List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		//'data' object returning HashMap data in the format of '{{map},{map1}....}'
		return data;
	
	}
	
	public Object[][] getExcelDataToMap(String filePath) throws IOException {
		
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheets = wb.getSheetAt(0);
		int RowCount = sheets.getPhysicalNumberOfRows();
		XSSFRow Rows = sheets.getRow(0); //1st row (0th index) --> it shows how many columns present on excel sheet. Note: We can't directly take Column count like Row.
		int ColumnCount = Rows.getLastCellNum();
		//What next? Iterate each & every column and through it into array.
		//sol: Creating memory allocation (Object[RowCount-1][ColumnCount]) to my multidimensional array (RowCol[][]).
		//		It will store in 'array' format (no. of rows) means each row = 'an array'.[in this way, data stored in backend.
		Object data[][] = new Object[RowCount-1][ColumnCount];	//create an object to class for two-dimensional array.
		for(int i=0;i<RowCount-1;i++) {				//Accessing the Row
			XSSFRow Row = sheets.getRow(i+1);	//In excel, we should access from 2nd row
			for(int j=0;j<ColumnCount;j++) {		//Accessing the Column
				XSSFCell cell = Row.getCell(j);
				
				data[i][j] = formatter.formatCellValue(cell);				
			}
		}
		return data;
	}
	
	
	@BeforeMethod(alwaysRun = true)		//in every group 'alwaysRun' runs this method.
	public LandingPage launchApplication() throws IOException {	
		driver = initializeDriver();
		loginPage = new LandingPage(driver);//parametarized constructor
		loginPage.enterURL();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun = true)	//after ran 'submitOrder()' method, this method should be run.
	public void tearDown() {
		driver.close();
	}
	
	//How to create screenshot utility?
	public String getScreenShot(String TestCaseFile, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(".\\reports\\"+ TestCaseFile + ".png");	
			//Destination path of file in type of 'string object'
		FileUtils.copyFile(source, file);
		return ".\\reports\\"+ TestCaseFile + ".png";
	}
	
}


