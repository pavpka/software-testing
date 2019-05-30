
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

class Presentation {
    private WebDriver driver;

    Presentation(WebDriver driver) {
        this.driver = driver;
    }

    private void testMenus() {
        List<WebElement> toolBarItems = driver.findElements(By.xpath("//*[@id=\"docs-toolbar\"]/*"));
        ToolBar toolBar = new ToolBar(driver);
        //toolBar.testToolBar(toolBarItems);

        List<WebElement> menuItems = driver.findElements(By.xpath("//div[@id='docs-menubar']/*"));
        Menu menu = new Menu(driver);
        menu.testDropDownMenu(menuItems);
    }

    private void setTitle() {
        String presentationName = "My Presentation";
        try {
            WebElement title = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"docs-title-widget\"]/input")));
            title.sendKeys(presentationName);
            System.out.println("test-case#14: добавлено название презентации: " + presentationName);
        } catch (Exception e) {
            System.out.println("test-case#14: название презентации не добавлено");
        }
    }

    void workOnPresentationPage() {
        setTitle();
        testMenus();
    }
}
