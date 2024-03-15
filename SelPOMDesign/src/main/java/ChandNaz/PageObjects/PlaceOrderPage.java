package ChandNaz.PageObjects;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ChandNaz.AbstractComponent.AbstractComponent;

public class PlaceOrderPage extends AbstractComponent{
	
	WebDriver driver;
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "[placeholder='Select Country']")
	WebElement addCountryName;
	@FindBy(css = ".ta-results button span")
	List<WebElement> clickOnCountryName;
	@FindBy(css = ".action__submit")
	WebElement clickonplaceorderbtn;
	By waitUntilNameDisplay = By.cssSelector(".ta-results button span");
	By waitUntilButtonDisplay = By.cssSelector(".details__user");

	public void selectCountry(String CountryName) throws InterruptedException {
		
 	Actions a = new Actions(driver);
 	a.sendKeys(addCountryName, CountryName).build().perform();
	WaitForElementToAppear(waitUntilNameDisplay);
	clickOnCountryName.stream().filter(product->product.getText().equalsIgnoreCase(CountryName)).findFirst().ifPresent(WebElement::click);

	}

	public ConfirmPage clickOnPlaceOrder() throws InterruptedException {
		Thread.sleep(3000);
//		WaitForElementToAppear(waitUntilButtonDisplay);
		scrollDownPage(clickonplaceorderbtn);
		clickonplaceorderbtn.click();
		return new ConfirmPage(driver);
	}
}
