
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//объектная модель главной страницы гугл презентаций
class MainPage {
    private WebDriver driver;

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //тестовый сценарий создания презентации
    void createNewPresentation() {
        try {
            // с помощью явных ожиданий указываем элемент и время, которое мы будем его ожидать
            WebElement newPresentation = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='docs-homescreen-templates-templateview-preview docs-homescreen-templates-templateview-preview-showcase'][1]")));
            newPresentation.click();
            //выводим сообщение о прохождении тестового сценария
            System.out.println("test-case#13: новая презентация создана");
            //в случае успешного создания презентации переходим к следующим тестам
            Presentation presentation = new Presentation(driver);
            presentation.workOnPresentationPage();
        } catch (Exception e) {
            //выводим сообщение о непрохождении тестового сценария
            System.out.println("test-case#13: новая презентация не создана");
        }
    }
}
