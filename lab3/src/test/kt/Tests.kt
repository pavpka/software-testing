import io.appium.java_client.MobileElement
import io.appium.java_client.android.Activity
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.WebDriverWait
import pages.*
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Tests {
    private val driver: AndroidDriver<MobileElement>
    private val wait: WebDriverWait

    init {
        //fun setting() {
        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel 2 XL")
        capabilities.setCapability(MobileCapabilityType.UDID, "192.168.147.108:5555")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0")
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2")
        capabilities.setCapability("appPackage", "com.twitter.android")
        capabilities.setCapability("appActivity", "com.twitter.app.main.MainActivity")
        //capabilities.setCapability("app", "C:\\study\\com.twitter.android.apk")

        driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)
        wait = WebDriverWait(driver, 20)
        val homePage = Home(driver, wait)
        homePage.closeModalWindow()
    }

    @Test
    fun `signUp without name and email`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.createNewAcc()
        val signUp = SignUp(driver, wait)
        signUp.clickNextButton()
        assertFalse(signUp.nextButtonIsEnable()) //test without name and email/phone
    }

    @Test
    fun `signUp with correct name and incorrect email`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.createNewAcc()

        val signUp = SignUp(driver, wait)
        signUp.writeName("Julia")
        signUp.setFieldType("Email")
        signUp.writeEmailOrPhone("julia.pitsik")
        assertFalse(signUp.nextButtonIsEnable()) //test with correct name and incorrect email
        signUp.clearFields()
    }

    @Test
    fun `signUp with incorrect name and incorrect phone`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.createNewAcc()

        val signUp = SignUp(driver, wait)
        signUp.writeName("1")
        signUp.setFieldType("Phone")
        signUp.writeEmailOrPhone("1")
        assertFalse(signUp.nextButtonIsEnable()) //test with incorrect name and incorrect phone
        signUp.clearFields()

    }
    @Test
    fun `signUp with correct name and correct email`(){
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.createNewAcc()

        val signUp = SignUp(driver, wait)
        signUp.writeName("Julia")
        signUp.setFieldType("Email")
        signUp.writeEmailOrPhone("julia.pitsik@yandex.ru")
    }

    @Test
    fun `signIn with incorrect password`() {
        driver.startActivity(Activity("com.twitter.android", "LoginActivity"))
        val signIn = SignIn(driver, wait)

        signIn.writeLogin("pavpka")
        signIn.writePasswd("12345")
        signIn.signIn()
        assertFalse(signIn.isLogIn())// test with incorrect passwd
        signIn.clearFields()
    }

    @Test
    fun `signIn with correct name and password`() {
        driver.startActivity(Activity("com.twitter.android", "LoginActivity"))
        val signIn = SignIn(driver, wait)
        signIn.writeLogin("pavpka")
        signIn.writePasswd("")
        signIn.signIn()
        assertTrue(signIn.isLogIn()) //test with correct data
    }

    @Test
    fun `click bottom menu's buttons`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickSearchButton()
        homePage.clickNotificationsButton()
        homePage.clickMessagesButton()
        homePage.clickNewsButton()
    }

    @Test
    fun `send a message`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickMessagesButton()

        val newMessagePage = Message(driver, wait)
        newMessagePage.createNewMessagePage()
        newMessagePage.chooseRecipient()
        newMessagePage.writeMessage()
        assertTrue(newMessagePage.messageIsSent())
    }

    @Test
    fun `search user`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickSearchButton()

        val searchPage = Search(driver, wait)
        searchPage.inputSearch("test")
        searchPage.comeBack()
    }

    @Test
    fun `click left menu's buttons`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)

        homePage.clickFollowerRequestsButton()
        homePage.clickMomentsButton()
        homePage.clickBookmarksButton()
        homePage.clickListsButton()
        homePage.clickSettingAndPrivacyButton()
        homePage.clickHelpCenterButton()
    }

    @Test
    fun `create a tweet`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickProfileButton()
        homePage.createANewTweet()

        val newTweetPage = NewTweet(driver, wait)
        val currentTime = (Calendar.getInstance().time).toString()
        newTweetPage.writeTweet(currentTime)
        newTweetPage.addImage()
        newTweetPage.backToTweet()

        newTweetPage.addGif()
        newTweetPage.backToTweet()

        newTweetPage.addAndDeletePoll()

        newTweetPage.sendTweet()
    }

    @Test
    fun `set a widget`() {
        driver.startActivity(Activity("com.microsoft.launcher", ".Launcher"))
        val homeScreen = HomeScreen(driver, wait)
        homeScreen.openMenu()
        homeScreen.findApp("twitter")
        homeScreen.setWidget()
    }

    @Test
    fun `click profile options`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickProfileButton()

        val profilePage = Profile(driver, wait)
        profilePage.share()
        profilePage.showDrafts()
    }

    @Test
    fun `delete a tweet`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickProfileButton()

        val profilePage = Profile(driver, wait)
        profilePage.deleteTweet()
    }

    @Test
    fun `add a bio information on the profile`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickProfileButton()

        val profilePage = Profile(driver, wait)
        val bio = "some information"
        profilePage.clickEditProfile()
        profilePage.addBioInformation(bio)
        assertTrue(profilePage.checkBioInformation(bio))
    }

    @Test
    fun `send a gif message without text`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickMessagesButton()

        val messagePage = Message(driver, wait)
        messagePage.openDialog()
        messagePage.sendGif()
    }

    @Test
    fun `delete a message`() {
        driver.startActivity(Activity("com.twitter.android", "com.twitter.app.main.MainActivity"))
        val homePage = Home(driver, wait)
        homePage.clickMessagesButton()

        val messagePage = Message(driver, wait)
        messagePage.openDialog()
        messagePage.deleteMessage()
    }

    @AfterAll
    fun after() {
        driver.quit()
    }
}
