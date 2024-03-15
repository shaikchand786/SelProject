package ChandNaz.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ChandNaz.AbstractComponent.AbstractComponent;

public class ConfirmPage extends AbstractComponent {
	
	WebDriver driver;
	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".hero-primary")
	WebElement getTextdata;
	By waituntilText = By.cssSelector(".hero-primary");
	
	public String getConfirmationMessage() {
		return getTextdata.getText();
	}
}
