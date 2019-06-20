package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class Home(private val driver: AndroidDriver<MobileElement>, private val wait: WebDriverWait) {
    fun clickSearchButton() {
        val button = driver.findElement(By.id("com.twitter.android:id/moments"))
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
    }

    fun clickNotificationsButton() {
        val button = driver.findElement(By.id("com.twitter.android:id/notifications"))
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
    }

    fun clickMessagesButton() {
        val button = driver.findElement(By.id("com.twitter.android:id/dms"))
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
    }

    fun clickNewsButton() {
        val button = driver.findElement(By.xpath("//android.view.View[@content-desc='Home Tab']"))
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
    }

    fun clickBackButton() {
        val button = driver.findElementByAccessibilityId("Navigate up")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
    }

    fun clickFollowerRequestsButton() {
        openLeftMenu()
        val button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[5]")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
        clickBackButton()
    }

    fun clickMomentsButton() {
        openLeftMenu()
        val button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[4]")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
        clickBackButton()
    }

    fun clickBookmarksButton() {
        openLeftMenu()
        val button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
        clickBackButton()
    }

    fun clickListsButton() {
        openLeftMenu()
        val button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
        clickBackButton()
    }

    fun clickProfileButton() {
        openLeftMenu()
        val button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
    }

    fun openLeftMenu() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
        val button = driver.findElementByAccessibilityId("Show navigation drawer")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
    }

    fun clickSettingAndPrivacyButton() {
        openLeftMenu()
        val button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[6]")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
        clickBackButton()
    }

    fun clickHelpCenterButton() {
        openLeftMenu()
        val button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[7]")
        wait.until { ExpectedConditions.visibilityOf(button) }
        button.click()
        clickBackButton()
    }

    fun createANewTweet() {
        val createButton = driver.findElementByAccessibilityId("New Tweet")
        wait.until { ExpectedConditions.visibilityOf(createButton) }
        createButton.click() // нажать на перо внизу экрана
    }

    fun showMore() {
        val moreButton = driver.findElementById("com.twitter.android:id/text")
        wait.until { ExpectedConditions.visibilityOf(moreButton) }
        moreButton.click()
    }

    fun findFriends() {
        val findButton = driver.findElementById("com.twitter.android:id/prompt_btn")
        wait.until { ExpectedConditions.visibilityOf(findButton) }
        findButton.click()

        val rejectSyncButton = driver.findElementById("com.twitter.android:id/button_negative")
        wait.until { ExpectedConditions.visibilityOf(rejectSyncButton) }
        rejectSyncButton.click()

    }

    fun createNewAcc() {
        openLeftMenu()
        wait.until { driver.findElementById("com.twitter.android:id/drawer").isDisplayed }
        val act = AndroidTouchAction(driver)
        act.tap(PointOption.point(1080, 397)).perform()
        val createButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[1]") as MobileElement
        wait.until { ExpectedConditions.visibilityOf(createButton) }
        createButton.click()
    }

    fun closeModalWindow() {
        val button = driver.findElementById("android:id/button2")
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
        if (button.isDisplayed) {
            button.click()
        }

    }
}
