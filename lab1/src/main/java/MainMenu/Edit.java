package MainMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//объектная модель выпадающего меню "Изменить"
public class Edit extends DropDownMenu {
    private WebDriver driver;
    private WebElement menuButton;

    public Edit(WebDriver driver, WebElement menuButton) {
        super(driver);
        this.driver = driver;
        this.menuButton = menuButton;
    }
    //метод, запускающий часть из тестовых сценариев в модуле "Проверка выпадающего меню"
    public void testEdit() {
        //Xpath области выпадающего меню
        String dropMenu = "//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics docs-menu-attached-button-above']";
        //тестовые сценарии
        cancel(dropMenu);
        repeat(dropMenu);
        selectAll(dropMenu);
        copy(dropMenu);
        paste(dropMenu);
        delete(dropMenu);
        findAndReplace(dropMenu);
    }

    private void cancel(String menu) {
        menuButton.click();
        //Xpath кнопки "Отменить" в выпадающем меню
        String menuItemPath = menu + "/div[3]";
        //вызов метода родительского класса, реализующий нажатие на кнопку с Xpath = menuItemPath
        testClickMenu(35, menuItemPath );
    }

    private void repeat(String menu) {
        menuButton.click();
        String menuItemPath = menu + "/div[4]";
        testClickMenu(36, menuItemPath );
    }

    private void selectAll(String menu) {
        menuButton.click();
        String menuItemPath = menu + "/div[12]";
        testClickMenu(37, menuItemPath);
    }

    private void copy(String menu) {
        menuButton.click();
        String menuItemPath = menu + "/div[7]";
        testClickMenu(38, menuItemPath);
    }

    private void paste(String menu) {
        try {
            WebElement dialog = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='docs-clipboardshortcutsdialog-shortcuts']")));
            Actions act = new Actions(driver);
            act.sendKeys(Keys.ESCAPE)
                    .build()
                    .perform();
        } catch (Exception e) {
        }
        try {
            //Thread.sleep(1000);
            menuButton.click();
            String menuItemPath = menu + "/div[11]";
            WebElement menuItem = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(menuItemPath)));
            menuItem.click();
            System.out.println("test-case#39: кнопка 'Создать копию' нажата");
        } catch (Exception e) {
            System.out.println("test-case#39: кнопка 'Создать копию' не нажата");
        }
    }

    private void delete(String menu) {
        menuButton.click();
        String menuItemPath = menu + "/div[10]";
        testClickMenu(40, menuItemPath);
    }

    private void findAndReplace(String menu) {
        menuButton.click();
        String itemXpath = menu + "/div[14]";
        String window = "/html/body/div[@class='modal-dialog docs-dialog docs-findandreplacedialog']";
        testClickButtonWithWindow(41, itemXpath, window);
    }

}
