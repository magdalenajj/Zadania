package StepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;


public class Zadanie2Steps {
    private WebDriver driver;

    @Given("Open browser with main page")
    public void userIsOnMainPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://mystore-testlab.coderslab.pl/");
    }
    @When("User enter an email and psswd")
    public void userIsSingingIn() {
        driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
        driver.findElement(By.name("email")).sendKeys("pikotova@gmail.com");
        driver.findElement(By.name("password")).sendKeys("lalala");
        driver.findElement(By.id("submit-login")).click();

    }
    @And("User finds product")
    public void userFindsProduct(){
        WebElement product = driver.findElement(By.className("ui-autocomplete-input"));
        product.sendKeys("Hummingbird Printed Sweater");
        product.submit();
        driver.findElement(By.xpath("//a//img[@alt='Brown bear printed sweater']")).click();

    }
    @And("User selects the size and quantity of the product")
    public void userSelectsSizeAndQuantity(){
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys("5");
        driver.findElement(By.xpath("//div//select//option[@title='M']")).click();

    }
    @And("User adds product to the cart")
    public void userAddsProductToTheCart(){
        driver.findElement(By.xpath("//*[@data-button-action='add-to-cart']")).click();
    }
    @And("User goes to the checkout section")
    public void userGoesToTheCheckoutSection(){
        driver.findElement(By.xpath("//*[contains(text(),\"Proceed to checkout\")]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//*[contains(text(),\"Proceed to checkout\")]")).click();
    }
    @And("User verify address")
    public void userVerifyAddress(){
        driver.findElement(By.xpath("//div//button[@name='confirm-addresses']")).click();
    }

    @And("User selects the payment and delivery method")
    public void userSelectsPaymentAndDeliveryMethod(){
        //driver.findElement(By.id("delivery_option_8")).click();
        driver.findElement(By.name("confirmDeliveryOption")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-option-1\"]")).click();
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        driver.findElement(By.xpath("//*[contains(text(),\"Place order\")]")).click();

    }
    @Then("Order is confirmed,user sees a message {string}")
    public void orderIsConfirmed(String alertText){
        WebElement orderConfirmationAlert = driver.findElement(By.className("card-title"));
        Assertions.assertTrue(orderConfirmationAlert.isDisplayed(),"Confirmation alert should be visible");
        Assertions.assertEquals(alertText,orderConfirmationAlert.getText());
    }
    @And("Do a screenshot")
    public void doAscreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File(("D:\\CodersLab - materiały\\screnshot1.jpg")));

    }
    @And("The user closes the browser")
    public void closeBrowser(){
        driver.quit();

    }

}