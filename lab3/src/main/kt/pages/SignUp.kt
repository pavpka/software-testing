package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class SignUp(private val driver: AndroidDriver<MobileElement>, private val wait: WebDriverWait) {

    fun writeName(name: String) {
        val nameField = driver.findElement(By.id("com.twitter.android:id/name_field"))
        wait.until { ExpectedConditions.visibilityOf(nameField) }
        nameField.sendKeys(name)
    }

    fun writeEmailOrPhone(data: String) {
        val phoneOrEmailField = driver.findElement(By.id("com.twitter.android:id/phone_or_email_field"))
        wait.until { ExpectedConditions.visibilityOf(phoneOrEmailField) }
        phoneOrEmailField.sendKeys(data)
    }

    fun clickNextButton() {
        val nextButton = driver.findElement(By.id("com.twitter.android:id/cta_button"))
        wait.until { ExpectedConditions.elementToBeClickable(nextButton) }
        nextButton.click()

    }

    fun setFieldType(type: String) {
        val phoneOrEmailField = driver.findElement(By.id("com.twitter.android:id/phone_or_email_field"))
        phoneOrEmailField.click()
        val changeTypeButton = driver.findElement(By.id("com.twitter.android:id/secondary_button"))
        if (type != phoneOrEmailField.text) changeTypeButton.click()

    }

    fun nextButtonIsEnable(): Boolean {
        val nameField = driver.findElement(By.id("com.twitter.android:id/name_field"))
        nameField.click()
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)
        val nextButton = driver.findElement(By.id("com.twitter.android:id/cta_button"))
        nextButton.click()
        return nextButton.isEnabled
    }

    fun clearFields() {
        val nameField = driver.findElement(By.id("com.twitter.android:id/name_field"))
        val phoneOrEmailField = driver.findElement(By.id("com.twitter.android:id/phone_or_email_field"))
        nameField.clear()
        phoneOrEmailField.clear()
    }


}
