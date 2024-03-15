package ChandNaz.AbstractComponent;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ChandNaz.PageObjects.CartPage;
import ChandNaz.PageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {//para constructor
			this.driver=driver;
			PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement clickOnOrdersbtn;
	
	By clickCart = By.cssSelector("button[routerlink*='cart']");

	public void WaitForElementToAppear(By element) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
 	wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
 	public void WaitForWebElementToAppear(WebElement element) {
 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
 	 	wait.until(ExpectedConditions.visibilityOf(element));
 	 	
	}
	
	public void WaitForElementToDisappear(By element) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		}

	public void scrollDownPage(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
//	    js.executeScript("window.scrollBy(0, 250)");
	    js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public CartPage clickOnCart() {
//		WaitForElementToAppear(clickCart);
		cartButton.click();
//		CartPage cartpage = new CartPage(driver);
		return new CartPage(driver);
	} 
	public OrderPage verifyOrders() {
		clickOnOrdersbtn.click();
		return new OrderPage(driver);
	
	}
}
