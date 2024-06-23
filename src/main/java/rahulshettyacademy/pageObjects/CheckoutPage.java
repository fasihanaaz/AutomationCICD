package rahulshettyacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".action__submit")
    WebElement submit;

    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement selectCountry;

    By results= By.cssSelector(".ta-results");

    public void selectCountry(String countryName)
    {
        Actions action = new Actions(driver);
        action.sendKeys(country, countryName).build().perform();
        waitforElementToAppear(results);
        selectCountry.click();
    }

    public confirmationPage submitOrder()
    {
        submit.click();
        return new confirmationPage(driver);
    }

}
