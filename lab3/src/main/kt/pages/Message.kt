package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class Message(private val driver: AndroidDriver<MobileElement>, private val wait: WebDriverWait) {
    fun createNewMessagePage() {
        val button = driver.findElementByAccessibilityId("New Message")
        wait.until(ExpectedConditions.visibilityOf(button))
        button.click() //кнопка нового сообщения внизу страницы, чтобы перейти к выбору получателя
    }

    fun chooseRecipient() {
        val searchField = driver.findElementById("com.twitter.android:id/suggestion_edit_text")
        wait.until(ExpectedConditions.visibilityOf(searchField))
        searchField.sendKeys("pavpka") //поиск себя в списке возможных получателей
        val account = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout")
        wait.until(ExpectedConditions.visibilityOf(account))
        account.click() //выбрать найденного получателя
        val nextButton = driver.findElementById("com.twitter.android:id/compose_next")
        wait.until(ExpectedConditions.visibilityOf(nextButton))
        nextButton.click() //перейти к написанию сообщения
    }

    fun writeMessage() {
        val messageField = driver.findElementById("com.twitter.android:id/tweet_text")
        wait.until(ExpectedConditions.visibilityOf(messageField))
        messageField.sendKeys("test")

        val sendButton = driver.findElementByAccessibilityId("Send")
        wait.until(ExpectedConditions.visibilityOf(sendButton))
        sendButton.click()
    }

    fun messageIsSent(): Boolean {
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS)
        return try {
            val logIn = driver.findElement(By.id("com.twitter.android:id/bubble"))
            true
        } catch (e: Exception) {
            false
        }
    }
    fun openDialog(){
        val button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]")
        wait.until(ExpectedConditions.visibilityOf(button))
        button.click()
    }
    fun deleteMessage(){
        val message =  driver.findElementById("com.twitter.android:id/content")
        val act = AndroidTouchAction(driver)
                .longPress(PointOption.point(1300, 2295)).release().perform()
        val modalWindow = driver.findElementById("android:id/select_dialog_listview")
        wait.until(ExpectedConditions.visibilityOf(modalWindow))

        val confirmDeletion =  driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]");
        wait.until(ExpectedConditions.visibilityOf(modalWindow))
        confirmDeletion.click()
    }
    fun sendGif(){
        val gifButton = driver.findElementById("com.twitter.android:id/found_media_compose")
        wait.until(ExpectedConditions.visibilityOf(gifButton))
        gifButton.click()
        val gifCategory = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.ImageView")
        wait.until(ExpectedConditions.visibilityOf(gifCategory))
        gifCategory.click()
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
        val gif = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ListView/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.ImageView")
        wait.until(ExpectedConditions.visibilityOf(gif))
        gif.click()
        val sendButton = driver.findElementByAccessibilityId("Send")
        wait.until(ExpectedConditions.visibilityOf(sendButton))
        sendButton.click()
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    }

}
