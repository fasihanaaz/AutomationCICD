package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import rahulshettyacademy.pageObjects.LandingPage;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) {
        String productName="ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage=new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("fasihanaaz@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Fasiha123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        // waiting for the page load and visibilty of elements
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        //1.Adding Products to Cart
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //2. Validating product is added to cart, by checking the success msg

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        //3.After adding the product clicking on Cart button
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        //4. Getting list of items present in the cart
        List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
        //5. Verifying if the productname matches
        Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
        Assert.assertTrue(match);
        //6.Clicking on Checkout button
        driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions action=new Actions(driver);
        //7.Selecting the country
        action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
        driver.findElement(By.cssSelector(".action__submit ")).click();

        //8. Verifying the Thank you msg
        String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
}
