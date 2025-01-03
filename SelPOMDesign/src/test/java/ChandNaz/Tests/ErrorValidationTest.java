package ChandNaz.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ChandNaz.PageObjects.CartPage;
import ChandNaz.PageObjects.ProductPage;
import ChandNaz.TestComponents.BaseTest;
   
public class ErrorValidationTest extends BaseTest{

	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		loginPage.LoginApplication("dummy78@gmail.com", "!T34567t");
		Assert.assertEquals("Incorrect email password....",loginPage.getErrorMsg()); //message: "Incorrect email or password."
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String ProdName1 = "IPHONE 13 PRO";
		ProductPage productPage = loginPage.LoginApplication("dummy786@gmail.com", "!T34567t");
//		List<WebElement> products = productPage.getListOfProducts();	//for future use
		productPage.addElementToCart(ProdName1);
		CartPage cartpage = productPage.clickOnCart();
		Boolean matchItem1 = cartpage.matchCartItems("IPHONE 13 PRO MAX....");
		Assert.assertFalse(matchItem1);
	}
	
}
