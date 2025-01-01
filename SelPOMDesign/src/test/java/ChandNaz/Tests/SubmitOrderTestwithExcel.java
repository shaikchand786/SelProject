package ChandNaz.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ChandNaz.PageObjects.CartPage;
import ChandNaz.PageObjects.ConfirmPage;
import ChandNaz.PageObjects.OrderPage;
import ChandNaz.PageObjects.PlaceOrderPage;
import ChandNaz.PageObjects.ProductPage;
import ChandNaz.TestComponents.BaseTest;
      
public class SubmitOrderTestwithExcel extends BaseTest{
	
	@Test(dataProvider="getDataObject")
	public void submitOrderExcel(String email,String PW,String prod1,String prod2,String cname) throws IOException, InterruptedException
	{	
		ProductPage productPage = loginPage.LoginApplication(email,PW);
		productPage.addElementToCart(prod1); 
		productPage.addElementToCart(prod2);
		CartPage cartpage = productPage.clickOnCart();
		Boolean matchItem1 = cartpage.matchCartItems(prod1);
		Assert.assertTrue(matchItem1);
		Boolean matchItem2 = cartpage.matchCartItems(prod2);
		Assert.assertTrue(matchItem2);
		PlaceOrderPage placeOrderPage = cartpage.clickOnCheckoutBtn();
		placeOrderPage.selectCountry(cname);	//Country name changed in the Excel sheet
		ConfirmPage pickupPage = placeOrderPage.clickOnPlaceOrder();
		String confirmText = pickupPage.getConfirmationMessage();
		Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dataProvider="getDataObject", dependsOnMethods={"submitOrderExcel"})
	public void VerifyOrders(String email,String PW,String prod1,String prod2,String cname) {
		ProductPage productPage = loginPage.LoginApplication(email,PW);
		OrderPage orderpage = productPage.verifyOrders();
		Assert.assertTrue(orderpage.verifyOrderDisplay(prod1));
	}
	
	//Method_3: How to get the Data from 'Json' file.  
	@DataProvider(name="getDataObject")
	public Object[][] getData() throws IOException{
		Object[][] data = getExcelDataToMap("C:\\Users\\HP\\Desktop\\Automation\\CMDs\\submitOrderTest.xlsx");
		return data ;
	}
	
}
