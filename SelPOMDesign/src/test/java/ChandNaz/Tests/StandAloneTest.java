package ChandNaz.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String ProdName = "IPHONE 13 PRO";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();//to fit all the objects on the screen that are not visible.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.get("https://www.rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("dummy786@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("!T34567t");
		driver.findElement(By.name("login")).click();
		
		//explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));	//globally defined.
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //click on that product

	 	WebElement prod = products.stream().filter(product->
	 		product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);//selection of product
	 	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();//click on that product
	 	
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body button:last-of-type")));
	 	
	 	WebElement prod1 = products.stream().filter(product->
 			product.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
	 	prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	 	
	 	//explicit wait
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));	//wait until pop-up message ('Product added to Cart') display on screen.	
	 	//".toast-bottom-right.toast-container"				// It conform that product is added & you can proceed with further actions.
	 	
	 	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));
	 	//wait until Animating Icon disappears (bubble enabled while clicking on 'Add to Cart' button. 
	 	
	 	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();	//scanning partial text
//	 	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[routerlink*='cart']")));
	 	
	 	List<WebElement> cartitems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	 	boolean match = cartitems.stream().anyMatch(cartitem->cartitem.getText().equalsIgnoreCase(ProdName));	
	 	//using 'filter()'-->to capture list of items & 'anyMatch()'-->to compare list of products (ex: 10 products in a list) in cart 
	 			//with Expected product(selected products)
//	 	System.out.println(match);
	 	Assert.assertTrue(match);
	 	
	       JavascriptExecutor js1 = (JavascriptExecutor) driver;
	       
	       //Identify the xpath of the object.
	        js1.executeScript("window.scrollBy(40,3000)");
	 	
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
	 	//Wrapping up end-to-end automation script on purchasing order in Ecom app.
	 	driver.findElement(By.cssSelector(".totalRow button")).click();
	 	
	 	Actions a = new Actions(driver);
	 	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	 	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	 	
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details__user")));
//	 	Thread.sleep(2000);
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
    
	       //Identify the xpath of the object.
	    js.executeScript("window.scrollBy(40,3000)");

	 	driver.findElement(By.cssSelector(".action__submit")).click();
	 	
	 	String check = driver.findElement(By.cssSelector(".hero-primary")).getText();
	 	boolean pickData = check.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
	 	Assert.assertTrue(pickData, check);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		driver.close();
		
	}

}
