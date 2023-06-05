package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Locale;

public class PlaylistPage extends BasePage{
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By plusBtn = By.cssSelector("[data-testid='sidebar-create-playlist-btn']");
    By createNewPlaylist = By.cssSelector("[data-testid='playlist-context-menu-create-simple']");
    By playlistNameInput = By.cssSelector(".create input");
    By plNameInput = By.cssSelector("[id='songResultsWrapper'] [placeholder='Playlist name']");

    public void createNewPlaylistWhileAddingSong(String playlistName) {
        WebElement newPlaylistNameInput = driver.findElement(plNameInput);
        newPlaylistNameInput.click();
        newPlaylistNameInput.clear();
        newPlaylistNameInput.sendKeys(playlistName);
        newPlaylistNameInput.click();
        newPlaylistNameInput.clear();
        newPlaylistNameInput.sendKeys(playlistName);
        // click Enter
        new Actions(driver)
                .keyDown(Keys.ENTER)
                .perform();
    }

    public String generateRandomPlaylistName(){
        Faker faker = new Faker(new Locale("en-US"));
        String newName = faker.address().country();
        return newName;
    }

    public void createNewPlaylistUsingPlusBtn(String playlistName) {
        WebElement plusButton = wait.until(ExpectedConditions.visibilityOfElementLocated(plusBtn));
        plusButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(createNewPlaylist)).click();
        // Add playlist name
        WebElement inputPlaylistName = wait.until(ExpectedConditions
                .visibilityOfElementLocated(playlistNameInput));
        inputPlaylistName.click();
        inputPlaylistName.clear();
        inputPlaylistName.sendKeys(playlistName);
        // click Enter
        new Actions(driver)
                .keyDown(Keys.ENTER)
                .perform();
    }

}
