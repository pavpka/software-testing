import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SignIn {
    private WebDriver driver;

    SignIn(WebDriver driver) {
        this.driver = driver;
    }

    void signIn() {
        FieldForSignIn testCase10 = new FieldForSignIn(10, "pitsik.julia", "");
        sendLogin(testCase10);
        FieldForSignIn testCase11 = new FieldForSignIn(11, "julia.pitsik", "julia0123");
        sendLogin(testCase11);
        FieldForSignIn testCase12 = new FieldForSignIn(12, "julia.pitsik", "0123julia");
        if (sendLogin(testCase12)) {
            MainPage mainPage = new MainPage(driver);
            mainPage.createNewPresentation();
        } else {
        }
    }

    private boolean sendLogin(FieldForSignIn testCase) {
        driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(testCase.userName);
        driver.findElement(By.xpath("//*[@id=\"identifierNext\"]")).click();
        try {
            WebElement inputPass = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")));
            return sendPassword(testCase, inputPass);
        } catch (Exception e) {
            System.out.println("test-case#" + testCase.num + ": авторизация не выполнена - неверный логин");
            driver.navigate().refresh();
            return false;
        }
    }

    private boolean sendPassword(FieldForSignIn testCase, WebElement input) {
        input.sendKeys(testCase.pass);
        driver.findElement(By.xpath("//*[@id=\"passwordNext\"]")).click();
        try {
            WebElement newPresentation = (new WebDriverWait(driver, 15))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='docs-homescreen-container docs-homescreen-slides']")));
            System.out.println("test-case#" + testCase.num + ": авторизация выполнена");
            return true;
        } catch (Exception e) {
            System.out.println("test-case#" + testCase.num + ": авторизация не выполнена - неверный пароль");
            driver.navigate().refresh();
            return false;
        }
    }
}
