package rahulshettyacademy.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    //String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void SubmitOrder(HashMap<String, String> input) throws IOException {

        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("product"));

        CartPage cartPage = productCatalog.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        confirmationPage confirmationPage = checkoutPage.submitOrder();
        String message = confirmationPage.getConfirmationMessage();

        Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    //To verify ZARA is displaying in Orders page
    @Test(dependsOnMethods = {"SubmitOrder"}, dataProvider = "getData")
    public void OrderHistoryTest(HashMap<String, String> input) {
        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
        OrderPage orderpage = productCatalog.goToOrdersPage();
        Assert.assertTrue(orderpage.VerifyOrderDisplay(input.get("product")));
    }


    //Extent Reports
    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java/rahulshettyacademy//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}

    /* @DataProvider
     public Object[][] getData()
    {
        return new Object[][] {{"fasihanaaz@gmail.com","Fasiha123","ZARA COAT 3"},{"yasarabd@gmail.com","Yasar123","ADIDAS ORIGINAL"}}
    }*/
       /* {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("email","fasihanaaz@gmail.com");
        map.put("password","Fasiha123");
        map.put("product","ZARA COAT 3");

        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("email","yasarabd@gmail.com");
        map1.put("password","Yasar123");
        map1.put("product","ADIDAS ORIGINAL");*/




