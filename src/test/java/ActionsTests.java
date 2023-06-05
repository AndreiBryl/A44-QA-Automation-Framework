import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

import java.util.List;

public class ActionsTests extends BaseTest {
    @Test
    public void playSongTest() {
        LoginPage loginPage = new LoginPage(driver);
        // hover over in clickPlayBtn
        loginPage.login("demo@class.com", "te$t$tudent");
        clickFooterPlayBtn();
        Assert.assertTrue(pauseBtnExists());

        // Comparing numbers of elements example
        List<WebElement> songs = driver.findElements(By.cssSelector("[data-test='song-card']"));

        int songsNumberBefore = songs.size();
        System.out.println(songsNumberBefore);
        // Just an example: here we would add or delete an element but we didn't
        int songsNumberAfter = songs.size();
        System.out.println(songsNumberAfter);

        // Soft assert example
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(songsNumberBefore == songsNumberAfter,
                "=== Songs number before should be equal songs number after ===");
        softAssert.assertEquals(driver.getCurrentUrl(), "https://bbb.testpro.io/#!/queue");
        System.out.println("Hello world");
        softAssert.assertAll();
    }

    @Test
    public void renamePlaylist() {
        LoginPage loginPage = new LoginPage(driver);
        // double click
        String playlistName = "Summer songs";

        loginPage.login("demo@class.com", "te$t$tudent");
        doubleClickChoosePlaylist();
        enterPlaylistName(playlistName);
        String newName = getPlaylistName();
        Assert.assertEquals(playlistName, newName, "=== PlaylistNames expected to be equals ===");
    }

    @Test
    public void playSongFromListTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("demo@class.com", "te$t$tudent");
        goToAllSongs();
        // right click on first song
        rightClickOnFirstSong();
        // click play button
        clickPlayBtn();
        // verify
        Assert.assertTrue(isEqualizerDisplayed());
    }

    private boolean isEqualizerDisplayed() {
        return driver.findElement(By.cssSelector("[data-testid='sound-bar-play']")).isDisplayed();
    }


    private void clickPlayBtn() {
        WebElement playBtn = driver.findElement(By.cssSelector(".playback"));
        playBtn.click();
    }

    private void rightClickOnFirstSong() {
        WebElement firstSong = driver.findElement(By.cssSelector(".song-item"));
        Actions actions = new Actions(driver);
        actions.contextClick(firstSong).perform();
    }

    public void clickFooterPlayBtn() {
        Actions action = new Actions(driver);
        WebElement playBtn = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        action
                .moveToElement(playBtn)
                .perform();
        playBtn.click();
    }

    public boolean pauseBtnExists() {
        return driver.findElement(By.cssSelector("[data-testid='pause-btn']")).isDisplayed();
    }


    private void goToAllSongs() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".songs"))).click();
    }


    public void doubleClickChoosePlaylist() {
        WebElement playlistElement = wait.until(ExpectedConditions.
                elementToBeClickable(By.cssSelector(".playlist:nth-child(3)")));
        Actions actions = new Actions(driver);
        actions.doubleClick(playlistElement).perform();
    }

    public void enterPlaylistName(String name) {
        WebElement playlistInputField = driver.findElement(By.cssSelector("input[name='name']"));
        playlistInputField.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), name);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    private String getPlaylistName() {
        WebElement playlistElement = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)>a")));
        String name = playlistElement.getText();
        return name;
    }

    @Test
    public void countSongsInPlaylist() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("demo@class.com", "te$t$tudent");
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(4)")));
        playlist.click();
        List<WebElement> songs = driver.findElements(By.cssSelector("#playlistWrapper .song-item"));
        int number = songs.size();
        Assert.assertEquals(number, 1); // can fail, depends on current number. This is just an example

    }
}
