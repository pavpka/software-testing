import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {
    public WebDriver driver;

    SignUp(WebDriver driver) {
        this.driver = driver;
    }

    void signUp() {
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div/div/span/span")).click();
        WebElement registerButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"initialView\"]/div[2]/div[3]/div/div/content[1]/div[2]")));
        registerButton.click();
        FieldForSignUp testCase1 = new FieldForSignUp(1, "", "", "", "", "");
        testSignUp(testCase1);
        FieldForSignUp testCase2 = new FieldForSignUp(2, "Ю", "П", "julia.pitsik", "0123julia", "0123julia");
        testSignUp(testCase2);
        FieldForSignUp testCase3 = new FieldForSignUp(3, "Юлия", "П", "julia.pitsik", "0123julia", "0123julia");
        testSignUp(testCase3);
        FieldForSignUp testCase4 = new FieldForSignUp(4, "Юлия*&()", "@#+Пицик", "julia.pitsik", "0123julia", "0123julia");
        testSignUp(testCase4);
        FieldForSignUp testCase5 = new FieldForSignUp(5, "Юлия", "Пицик", "123", "0123julia", "0123julia");
        testSignUp(testCase5);
        FieldForSignUp testCase6 = new FieldForSignUp(6, "Юлия", "Пицик", "pitsikj", "0123julia", "0123julia");
        testSignUp(testCase6);
        FieldForSignUp testCase7 = new FieldForSignUp(7, "Юлия", "Пицик", "julia.pitsik", "0123", "0123");
        testSignUp(testCase7);
        FieldForSignUp testCase8 = new FieldForSignUp(8, "Юлия", "Пицик", "julia.pitsik", "0123julia", "0123juliaa");
        testSignUp(testCase8);
        FieldForSignUp testCase9 = new FieldForSignUp(9, "Юлия", "Пицик", "julia.pitsik", "0123julia", "0123julia");
        testSignUp(testCase9);
    }

    private void testSignUp(FieldForSignUp testCase) {
        driver.navigate().refresh();
        sendNames(testCase.firstName, testCase.secondName);
        sendUsername(testCase.userName);
        sendPassword(testCase.pass, testCase.repeatPass);
        if (!nextClick()) {
            System.out.println("test-case#" + testCase.num + ": регистрация не выполнена");
        }
        else System.out.println("test-case#" + testCase.num + ": регистрация выполнена");
    }

    private void sendNames(String firstName, String secondName) {
        WebElement firstField = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"firstName\"]")));
        firstField.sendKeys(firstName);
        driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys(secondName);
    }

    private void sendUsername(String userName) {
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(userName);
    }

    private void sendPassword(String pass, String repeatPass) {
        driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@name='ConfirmPasswd']")).sendKeys(repeatPass);
    }

    private boolean nextClick() {
        WebElement nextButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"accountDetailsNext\"]")));
        nextButton.click();
        try {
            WebElement next = (new WebDriverWait(driver, 2))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gradsIdvPhoneNext\"]")));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
