package ChandNaz.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ChandNaz.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	Actions act = new Actions(driver);
	act.
	public LandingPage(WebDriver driver) {	//In this class, this method is the first thing to execute. 
		// Initialization
		super(driver);
		this.driver = driver;
//		PageFactory.initElements(driver, this);	//'this' refers to current class driver
		//Construction of '@FindBy()' will be triggered when you call 'initElements()' element.
	}
	//PageFactory -->reduce the syntax for creating the WebElements
	@FindBy(id="userEmail")	//original way = driver.findElement(By.id("userEmail"))		//@FindBy -->annotation 
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement password;
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css = "[class*='toast-container']")
	WebElement errorMessage;
	
	public ProductPage LoginApplication(String email, String PW)
	{
		userEmail.sendKeys(email);
		password.sendKeys(PW);
		submit.click();
//		ProductPage productPage = new ProductPage(driver);
		return new ProductPage(driver);
	}
	public String getErrorMsg() {
		WaitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	public void enterURL() {
		driver.get("https://www.rahulshettyacademy.com/client/");
	}
	
}
