package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class Search(private val driver: AndroidDriver<MobileElement>, private val wait: WebDriverWait) {
    fun inputSearch(search: String) {
        val searchField = driver.findElementById("com.twitter.android:id/query_view")
        wait.until(ExpectedConditions.visibilityOf(searchField))
        searchField.click()
        val input = driver.findElementByAccessibilityId("Search")
        wait.until(ExpectedConditions.visibilityOf(input))
        input.sendKeys(search)

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
    }

    fun comeBack() {
        val backButton = driver.findElementByAccessibilityId("Collapse")
        wait.until(ExpectedConditions.visibilityOf(backButton))
        backButton.click()
    }
}
