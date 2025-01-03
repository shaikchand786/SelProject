package ChandNaz.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ChandNaz.PageObjects.CartPage;
import ChandNaz.PageObjects.ConfirmPage;
import ChandNaz.PageObjects.OrderPage;
import ChandNaz.PageObjects.PlaceOrderPage;
import ChandNaz.PageObjects.ProductPage;
import ChandNaz.TestComponents.BaseTest;
      
public class SubmitOrderTest extends BaseTest{
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{	
		ProductPage productPage = loginPage.LoginApplication(input.get("email"), input.get("PW"));
		List<WebElement> products = productPage.getListOfProducts();	//for future use only
		productPage.addElementToCart(input.get("ProdName1")); 
		productPage.addElementToCart(input.get("ProdName2"));
		CartPage cartpage = productPage.clickOnCart();
//		Boolean matchItem1 = cartpage.matchCartItems(input.get("ProdName1"));
//		Assert.assertTrue(matchItem1);
//		Boolean matchItem2 = cartpage.matchCartItems(input.get("ProdName2"));
//		Assert.assertTrue(matchItem2);
		PlaceOrderPage placeOrderPage = cartpage.clickOnCheckoutBtn();
		placeOrderPage.selectCountry(input.get("CountryName"));
		ConfirmPage pickupPage = placeOrderPage.clickOnPlaceOrder();
		String confirmText = pickupPage.getConfirmationMessage();
		Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dataProvider = "getData", dependsOnMethods = "submitOrder")
	public void VerifyOrders(HashMap<String, String> input) {
		ProductPage productPage = loginPage.LoginApplication(input.get("email"), input.get("PW"));
		OrderPage orderpage = productPage.verifyOrders();
		Assert.assertTrue(orderpage.verifyOrderDisplay(input.get("ProdName1")));
	}
	
//Method_3: How to get the Data from 'Json' file.  
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data = getJsonDataToMap(".\\src\\test\\java\\ChandNaz\\data\\PurchaseOrders.json");
		return new Object[][]  { {data.get(0)}, {data.get(1)} };
}
	
}



//Method_1: How to drive the data by using 'DataProvider'.
//@DataProvider
//public Object[][] getData() {
//	return new Object[][]  { {"dummy786@gmail.com","!T34567t","IPHONE 13 PRO"}, {"dummy123786@gmail.com","!T34567t","ADIDAS ORIGINAL"} };
//}

//Method_2: How to get the Data by using 'HashMap' (it will use, when you have to catch more parameters into 'method(parameters)')  
//@DataProvider
//public Object[][] getData1(){
//	1st set of parameters
//	HashMap<Object, Object> map = new HashMap<>();	//Object--> Generic Datatype which accepts any datatypes.
//	Map map = new HashMap();
//	map.put("email", "dummy786@gmail.com");
//	map.put("PW", "!T34567t");
//	map.put("ProdName1", "IPHONE 13 PRO");
//	map.put("CountryName", "Austria");
	//2nd set of parameters
//	HashMap<Object, Object> map1 = new HashMap<>();
//	Map map1 = new HashMap();
//	map1.put("email", "dummy123786@gmail.com");
//	map1.put("PW", "!T34567t");
//	map1.put("ProdName1", "ADIDAS ORIGINAL");
//	map1.put("CountryName", "Italy");
//	return new Object[][]  { {map}, {map1} };
//}
//}
