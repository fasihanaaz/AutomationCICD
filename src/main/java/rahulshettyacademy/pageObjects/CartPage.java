package rahulshettyacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    @FindBy(css = ".cartSection h3")
    List<WebElement> productTitles;

    @FindBy(css = ".totalRow button")
    WebElement checkOutEle;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean VerifyProductDisplay(String productName) {
        Boolean match = productTitles.stream().anyMatch(product -> product.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckout() {
        checkOutEle.click();
        return new CheckoutPage(driver);
    }

}
