import pages.HomePage;
import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework22Test extends BaseTest {

    @Test
    public void loginSuccessTest() {
        //GIVEN
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        //WHEN
        loginPage.enterEmail("marianna.tysovska@gmail.com");
        loginPage.enterPassword("marysia288940.");
        loginPage.clickLoginBtn();
        homePage.getAvatar();
        //THEN
        WebElement avatar = driver.findElement(By.cssSelector(".avatar"));
        Assert.assertTrue(avatar.isDisplayed());

    }
}
