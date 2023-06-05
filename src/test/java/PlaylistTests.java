import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlaylistPage;

import java.util.ArrayList;
import java.util.List;

public class PlaylistTests extends BaseTest {

    @Test
    public void deletePlaylist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        PlaylistPage playlistPage = new PlaylistPage(driver);
        String playlist = playlistPage.generateRandomPlaylistName();
        loginPage.login("demo@class.com", "te$t$tudent");
        // CREATE PLAYLIST
        // click Plus btn
        // click Create new playlist

        playlistPage.createNewPlaylistUsingPlusBtn(playlist);

        // check playlist name in header
        WebElement playlistHeader = driver.findElement(By.cssSelector("#playlistWrapper h1"));
        wait.until(ExpectedConditions
                .textToBePresentInElement(playlistHeader, playlist));
        // delete playlist
        WebElement deletePlaylistBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-delete-playlist")));
        deletePlaylistBtn.click();
        // verify banner
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success")));
        // refresh page
        driver.navigate().refresh();
        // get all playlist elements
        List<WebElement> playlists = driver.findElements(By.cssSelector("#playlists a"));
        // get playlist names from playlist elements
        List<String> playlistNames = new ArrayList<>();

        for (int i = 0; i < playlists.size(); i++) {
            String playlistName = playlists.get(i).getText();
            playlistNames.add(playlistName);
        }
        System.out.println(playlistNames);
        // assert playlist was deleted
        Assert.assertFalse(playlistNames.contains(playlist));
    }

}
