package MainMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

//базовый класс для всех объектных моделей выпадающего меню
abstract public class DropDownMenu {

    private WebDriver driver;
    private String title;

    public DropDownMenu(WebDriver driver) {
        this.driver = driver;
    }
    //функция, которая нажимает кнопку, вызывающую появление модального окна
    protected void testClickButtonWithWindow(int testCase, String buttonXpath, String windowXpath) {
        //объявление экземпляра класса Actions для эмуляции цепочек сложных действий пользователя
        Actions act = new Actions(driver);
        try {
            //явное ожидание кнопки
            WebElement button = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
            title = button.getText();
            button.click();
            //явное ожидание модального окна
            WebElement window = (new WebDriverWait(driver, 11))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(windowXpath)));
            //создаем цепочку действия для закрытия модального окна
            act.sendKeys(Keys.ESCAPE)
                    .build()
                    .perform();
            System.out.println("test-case#" + testCase + ": кнопка " + title + " нажата");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("test-case#" + testCase + ": кнопка " + title + " не нажата " + e);
        }

    }

    protected void testCheckButton(int testCase, String buttonXpath, WebElement menuButton) {
        try {
            menuButton.click();
            WebElement button = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
            title = button.getText();
            button.click();
            menuButton.click();
            WebElement button2 = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
            button2.click();
            System.out.println("test-case#" + testCase + ": кнопка " + title + " нажата");

        } catch (Exception e) {
            System.out.println("test-case#" + testCase + ": кнопка " + title + " не нажата");
        }
    }

    protected void testClickMenu(int testCase, String buttonXpath) {
        try {
            WebElement button = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
            title = button.getText();
            button.click();
            System.out.println("test-case#" + testCase + ": кнопка " + title + " нажата");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("test-case#" + testCase + ": кнопка " + title + " не нажата");

        }
    }

    protected void testDropDownButton(int testCase, String buttonXpath, String dropDownXpath) {
        try {
            WebElement button = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
            title = button.getText();
            button.click();
            WebElement dropDown = (new WebDriverWait(driver, 11))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(dropDownXpath)));
            List<WebElement> buttons = dropDown.findElements(By.xpath("/div"));
            for (WebElement item : buttons) {
                item.click();
            }
            System.out.println("test-case#" + testCase + ": все пукты выпадающего меню " + title + " нажаты");
        } catch (Exception e) {
            System.out.println("test-case#" + testCase + ": все пукты выпадающего меню " + title + " не нажаты" + e);
        }
    }
}
