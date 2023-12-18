package stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Zadanie1Steps {
    private WebDriver driver;

    @Given("An open browser with main page of the shop")
    public void userIsOnShopMainPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @When("User enter an email and password")
    public void userSingIn() {
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a")).click();
        driver.findElement(By.id("field-email")).sendKeys("kaszmir15@wp.pl");
        driver.findElement(By.id("field-password")).sendKeys("lalala");
        driver.findElement(By.id("submit-login")).click();

    }

    @And("User goes to addresses section")
    public void goingToAddressesSection() {
        driver.findElement(By.xpath("//*[contains(text(), \"Addresses\")]")).click();

    }
    @And("User selects \"Create new address\" option")
    public void createNewAddress(){
        driver.findElement(By.xpath("//*[contains(text(), \"Create new address\")]")).click();
    }
    @And("{string} alias is entered in alias input field,{string} address is entered in address input field,{string} city is entered in city input field,{string} zipCode is entered in zip code input field,{string} phone is entered in phone input field")
    public void enterDataAboutNewAddress (String alias,String address,String city,String zipCode,String phone){
        WebElement aliasInput = driver.findElement(By.id("field-alias"));
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        WebElement addressInput=driver.findElement(By.id("field-address1"));
        addressInput.clear();
        addressInput.sendKeys(address);

        WebElement cityInput = driver.findElement(By.name("city"));
        cityInput.clear();
        cityInput.sendKeys(city);

        WebElement zipCodeInput = driver.findElement(By.id("field-postcode"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys(zipCode);

        WebElement phoneInput=driver.findElement(By.id("field-phone"));
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        WebElement saveBtn = driver.findElement(By.className("form-control-submit"));
        saveBtn.click();

    }
    @Then("New address is added,user can see message {string}")
    public void addedAddress(String alertText){
        WebElement succesAlert =driver.findElement(By.className("alert-success"));
        Assertions.assertTrue(succesAlert.isDisplayed(),"Success alert should be visible");
        Assertions.assertEquals(alertText,succesAlert.getText());
    }
    @And ("Checking if {string} expectedAlias, {string} expectedAddress, {string} expectedCity, {string} expectedZipCode, {string} expectedPhone is entered correctly")
    public void fieldsAreEntered(String expectedAlias,String expectedAddress, String expectedCity, String expectedZipCode, String expectedPhone){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=addresses");
      WebElement newAddress = driver.findElement(By.id("addresses-link"));
        String newAddressField=newAddress.getText();
        Assertions.assertTrue(newAddressField.contains(expectedAlias));
        Assertions.assertTrue(newAddressField.contains(expectedAddress));
        Assertions.assertTrue(newAddressField.contains(expectedCity));
        Assertions.assertTrue(newAddressField.contains(expectedZipCode));
       Assertions.assertTrue(newAddressField.contains(expectedPhone));

    }
    @And("close browser")
    public void iCloseBrowser(){
        driver.quit();
    }
}
