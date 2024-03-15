package ChandNaz.PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ChandNaz.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cartSection']/h3")	
	List<WebElement> pickCartItems;
	@FindBy(css = ".totalRow button")
	WebElement checkoutbutton;
	By clickonbtn = By.cssSelector(".totalRow button");
	By getItems	= By.xpath("//div[@class='cartSection']/h3");
	
	public List<WebElement> getCartItems() {
		WaitForElementToAppear(getItems);
		return pickCartItems;
	}
	
	public boolean matchCartItems(String ProdName) {
		boolean matchItem = getCartItems().stream().anyMatch(cartitem->cartitem.getText().equalsIgnoreCase(ProdName));	
	 	return matchItem;
	 		
	}

	public PlaceOrderPage clickOnCheckoutBtn() throws InterruptedException {
		scrollDownPage(checkoutbutton);
		Thread.sleep(2000);
//		WaitForElementToAppear(clickonbtn);
		checkoutbutton.click();
		PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
		return placeOrderPage;
	}
}
