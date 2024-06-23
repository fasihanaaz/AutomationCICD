package rahulshettyacademy.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalog productCatalog;
    public confirmationPage confirmationPage;

    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void logged_in_with_username_and_password(String username, String password)
    {
        productCatalog = landingPage.loginApplication(username,password);
    }
    @When("^I add the product (.+) to Cart$")
    public void I_add_the_product_to_Cart(String productName) throws InterruptedException
    {
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName)
    {
        CartPage cartPage = productCatalog.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage confirmationPage = checkoutPage.submitOrder();
    }
    @Then("{string} message is displayed on Confirmation Page")
    public void message_displayed_confirmationPage(String string)
    {
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase(string));
    }
    @Then("^\"([^\"]*)\" message is displayed$")
    public void message_is_displayed(String expectedMessage) {
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }
    }


