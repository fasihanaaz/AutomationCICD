package rahulshettyacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css="[class*='flyInOut']")
    WebElement loginError;

    public void goToLandingPage() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalog loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        ProductCatalog productCatalog=new ProductCatalog(driver);
        return productCatalog;
    }

public String getErrorMessage()
{
    waitforElementToAppear(loginError);
    return loginError.getText();

}
}
