package ChandNaz.PageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ChandNaz.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	public OrderPage(WebDriver driver) { 
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory -->reduce the syntax for creating the WebElements
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> verifyOrderList;
		
	public boolean verifyOrderDisplay(String ProdName) {
		boolean verifyItems = verifyOrderList.stream().anyMatch(orderitem->orderitem.getText().equalsIgnoreCase(ProdName));	
	 	return verifyItems;
	}
}
