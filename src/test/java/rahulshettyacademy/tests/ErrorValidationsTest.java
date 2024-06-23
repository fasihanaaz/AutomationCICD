package rahulshettyacademy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.ProductCatalog;
import rahulshettyacademy.resources.Retry;


import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"})//,retryAnalyzer = Retry.class
    public void LoginErrorValidation() throws IOException {
        landingPage.loginApplication("fasihanaaz123@gmail.com", "Fasiha456123");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() {
        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApplication("fasihanaaz@gmail.com", "Fasiha123");

        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);

        CartPage cartPage = productCatalog.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 34");
        Assert.assertFalse(match);
    }


}

