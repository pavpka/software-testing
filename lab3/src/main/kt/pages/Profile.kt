package pages

import com.sun.org.apache.xpath.internal.operations.Bool
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.By
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class Profile(private val driver: AndroidDriver<MobileElement>, private val wait: WebDriverWait) {
    fun share() {
        val optionsButton = driver.findElementByAccessibilityId("More options")
        wait.until { ExpectedConditions.visibilityOf(optionsButton) }
        optionsButton.click() //нажать кнопку опции

        val shareButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")
        wait.until { ExpectedConditions.visibilityOf(shareButton) }
        shareButton.click() // нажать кнопку share
        wait.until { driver.findElementById("android:id/resolver_list").isDisplayed }
        val act = AndroidTouchAction(driver)
        act.press(PointOption.point(600, 600))
                .release().perform()
        //нажать на любое место на экране, чтобы скрыть появившееся меню "поделиться"
    }

    fun showDrafts() {
        val optionsButton = driver.findElementByAccessibilityId("More options")
        wait.until { ExpectedConditions.visibilityOf(optionsButton) }
        optionsButton.click() //нажать кнопку опции

        val draftsButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")
        wait.until { ExpectedConditions.visibilityOf(draftsButton) }
        draftsButton.click() //нажать кнопку drafts

        val backButton = driver.findElementByAccessibilityId("Navigate up")
        wait.until { ExpectedConditions.visibilityOf(backButton) }
        backButton.click() // назад в профиль
    }

    fun deleteTweet() {
        wait.until {driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]").isDisplayed}
        val act = AndroidTouchAction(driver)
        act.tap(PointOption.point(1371, 1477)).perform()

        //открыть выпадающее меню последнего твита

        val deleteButton = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView")
        wait.until { ExpectedConditions.visibilityOf(deleteButton) }
        deleteButton.click() // нажать кнопку удалить

        val confirmButton = driver.findElementById("android:id/button1")
        wait.until { ExpectedConditions.visibilityOf(confirmButton) }
        confirmButton.click() // подтвердить удаление в модальном окне
    }

    fun clickEditProfile() {
        val editProfileButton = driver.findElementById("com.twitter.android:id/button_edit_profile")
        wait.until { ExpectedConditions.visibilityOf(editProfileButton) }
        editProfileButton.click()
    }

    fun addBioInformation(text: String) {
        val informationField = driver.findElementById("com.twitter.android:id/edit_bio")
        wait.until { ExpectedConditions.visibilityOf(informationField) }
        informationField.sendKeys(text) //  добавить текс в био

        val saveButton = driver.findElementById("com.twitter.android:id/save")
        wait.until { ExpectedConditions.visibilityOf(saveButton) }
        saveButton.click() // нажать кнопку сохранить
    }

    fun checkBioInformation(text: String): Boolean {
        return driver.findElement(By.id("com.twitter.android:id/user_bio")).text == text
    }

}
