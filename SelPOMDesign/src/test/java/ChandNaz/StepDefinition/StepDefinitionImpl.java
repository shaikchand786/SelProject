package ChandNaz.StepDefinition;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ChandNaz.PageObjects.CartPage;
import ChandNaz.PageObjects.ConfirmPage;
import ChandNaz.PageObjects.LandingPage;
import ChandNaz.PageObjects.PlaceOrderPage;
import ChandNaz.PageObjects.ProductPage;
import ChandNaz.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductPage productPage;
	public PlaceOrderPage placeOrderPage;
	public ConfirmPage pickupPage;
	public CartPage cartpage;
	
	@Given("I landed on Rahul Shetty Academy website")
	public void I_landed_on_Rahul_Shetty_Academy_website() throws IOException 
	{
		landingPage = launchApplication();
	}
	
	@Given("^Loggedin with username (.+) and password (.+)$")
	public void Loggedin_with_username_and_password(String username, String password) {
		productPage = loginPage.LoginApplication(username, password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void I_add_the_product_to_cart(String productname) throws InterruptedException {
		List<WebElement> products = productPage.getListOfProducts();	//for future use
		productPage.addElementToCart(productname);
	}
	
	@When("^checkout the productname (.+) and clickon submit order button$")
	public void checkout_clickon_submit(String ProductName) throws InterruptedException{
	
		cartpage = productPage.clickOnCart();
		
		Boolean matchItem1 = cartpage.matchCartItems(ProductName);
		Assert.assertTrue(matchItem1);
		
		placeOrderPage = cartpage.clickOnCheckoutBtn();
	}
	@Then("^select the countryname (.+) from dropdown in Place Order page$")
	public void select_countryname(String countryname) throws InterruptedException {
	
		placeOrderPage.selectCountry(countryname);
		
	}
	@Then("clickon Place Order button")
	public void clickon_placeorder_btn() throws InterruptedException {
		pickupPage = placeOrderPage.clickOnPlaceOrder();
	}	
		
	@Then("check place order message as {string}")
	public void check_place_order_message(String string)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	{
		String confirmText = pickupPage.getConfirmationMessage();
		Assert.assertTrue(confirmText.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then ("^check error message as (.+)$")
	public void check_error_message(String errormessage) {
		Assert.assertEquals(errormessage,loginPage.getErrorMsg());
		driver.close();
	}
	
}
