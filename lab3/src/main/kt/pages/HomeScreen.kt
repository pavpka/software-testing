package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class HomeScreen(private val driver: AndroidDriver<MobileElement>, private val wait: WebDriverWait){
    fun openMenu(){
        val button = driver.findElementByAccessibilityId("Apps,Button: 3 of 5")
        wait.until(ExpectedConditions.visibilityOf(button))
        button.click()
    }
    fun findApp(name: String){
        val input =  driver.findElementByAccessibilityId("Search Apps:Edit box")
        wait.until(ExpectedConditions.visibilityOf(input))
        input.sendKeys(name) // найти твиттер среди приложений
    }
    fun setWidget(){
        wait.until { driver.findElementByAccessibilityId("Twitter,Apps: 1 of 1:Swipe up with two fingers to see next group apps in view.").isDisplayed }
        val act = AndroidTouchAction(driver)
        println("act")
        act
                .longPress(PointOption.point(138, 534))
                .moveTo(PointOption.point(600, 1400))
                .release().perform()
    }
}
