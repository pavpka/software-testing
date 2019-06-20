package pages

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

class SignIn(private val driver: AndroidDriver<MobileElement>, private val wait: WebDriverWait) {

    fun writeLogin(name: String) {
        val nameField = driver.findElement(By.id("com.twitter.android:id/login_identifier"))
        wait.until { ExpectedConditions.visibilityOf(nameField) }
        nameField.sendKeys(name)
    }

    fun writePasswd(passwd: String) {
        val passwdField = driver.findElement(By.id("com.twitter.android:id/login_password"))
        val nameField = driver.findElement(By.id("com.twitter.android:id/login_identifier"))
        wait.until { ExpectedConditions.visibilityOf(passwdField) }
        passwdField.sendKeys(passwd)
        nameField.click()

    }

    fun isLogIn(): Boolean {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        return try {
            val logIn = driver.findElement(By.id("com.twitter.android:id/login_login"))
            false
        } catch (e: Exception) {
            true
        }
    }

    fun clearFields() {
        val nameField = driver.findElement(By.id("com.twitter.android:id/login_identifier"))
        val passwdField = driver.findElement(By.id("com.twitter.android:id/login_password"))
        nameField.clear()
        passwdField.clear()
    }

    fun signIn() {
        val nextButton = driver.findElement(By.id("com.twitter.android:id/login_login"))
        wait.until { ExpectedConditions.visibilityOf(nextButton) }
        nextButton.click()
    }

}
