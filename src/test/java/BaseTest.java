import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
     WebDriverWait wait;
    @BeforeSuite
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();}
    @BeforeMethod

    public void setUpBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notification*");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    public void openUrl() {
        String url = "https://bbb.testpro.io/";
        driver.get(url);
    }
    public void enterEmail(String email) {
       // WebElement emailInput = driver.findElement(By.cssSelector("[type='email']"));
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
//        emailInput.sendKeys("andrei.bryliakov01@testpro.io");
    }
    public void enterPassword(String password) {
       // WebElement passwordInput = driver.findElement(By.cssSelector("[type='password']"));
        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='password']")));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
//        passwordInput.sendKeys("te$t$tudent");
    }
    public void clickLoginBtn() {
      //  WebElement submitLogin = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement submitLogin = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        submitLogin.click();
       // WebElement avatar = driver.findElement(By.cssSelector(".avatar"));
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".avatar")));
        Assert.assertTrue(avatar.isDisplayed());
    }

}