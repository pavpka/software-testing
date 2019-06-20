package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Exception

class NewTweet(private val driver: AndroidDriver<MobileElement>, private val wait: WebDriverWait) {
    fun writeTweet(tweet: String) {
        val tweetField = driver.findElementById("com.twitter.android:id/tweet_text")
        wait.until { ExpectedConditions.visibilityOf(tweetField) }
        tweetField.sendKeys(tweet) //написать твит
    }

    fun addImage() {
        val imageButton = driver.findElementByAccessibilityId("Photos")
        wait.until { ExpectedConditions.visibilityOf(imageButton) }
        imageButton.click() // добавить картинку
        //возможно появится модальное окно с разрешением доступа к галерее:
        try {
            val allowAccess = driver.findElementById("com.android.packageinstaller:id/permission_allow_button")
            wait.until { ExpectedConditions.visibilityOf(allowAccess) }
            allowAccess.click()
        } catch (e: Exception) {
        }
    }

    fun addGif() {
        val gifButton = driver.findElementByAccessibilityId("GIF")
        wait.until { ExpectedConditions.visibilityOf(gifButton) }
        gifButton.click()
    }

    fun addAndDeletePoll() {
        val pullButton = driver.findElementByAccessibilityId("Poll")
        wait.until { ExpectedConditions.visibilityOf(pullButton) }
        pullButton.click()

        val deleteButton = driver.findElementByAccessibilityId("Remove poll")
        wait.until { ExpectedConditions.visibilityOf(deleteButton) }
        deleteButton.click()

    }

    fun sendTweet() {
        val sendButton = driver.findElementById("com.twitter.android:id/button_tweet")
        wait.until { ExpectedConditions.visibilityOf(sendButton) }
        sendButton.click()
    }

    fun backToTweet() {
        val backButton = driver.findElementByAccessibilityId("Navigate up")
        wait.until { ExpectedConditions.visibilityOf(backButton) }
        backButton.click()

    }
}
