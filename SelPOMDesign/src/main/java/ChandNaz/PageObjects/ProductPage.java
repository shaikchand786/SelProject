package ChandNaz.PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ChandNaz.AbstractComponent.AbstractComponent;

public class ProductPage extends AbstractComponent {

	WebDriver driver;
	public ProductPage(WebDriver driver) {	//In this class, this method is the first thing to execute. 
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	//'this' refers to current class driver
		//Construction of '@FindBy()' will be triggered when you call 'initElements()' element.
	}
	//PageFactory -->reduce the syntax for creating the WebElements
	@FindBy(css=".mb-3")	//original way = driver.findElement(By.id("userEmail"))		//@FindBy -->annotation 
	List<WebElement> noOfProds;
	By element = By.cssSelector(".mb-3");
	By selectelement = By.cssSelector("b");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastWait = By.cssSelector("#toast-container");
	By spinner = By.cssSelector("ng-animating");
		
	public List<WebElement> getListOfProducts() { 		
		WaitForElementToAppear(element);
		return noOfProds;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getListOfProducts().stream().filter(product->
			product.findElement(selectelement).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addElementToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		//Now, we have to get data from AbstractComponent.java class for Wait-time.
		WaitForElementToAppear(toastWait);
		WaitForElementToDisappear(spinner);
	}
}
