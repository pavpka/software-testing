package RightClick;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextAreaRightClick extends RightClickDropDown{

    private WebDriver driver;

    public TextAreaRightClick(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void testTextAreaRightClick() {
        WebElement workSpace = driver.findElement(By.xpath("//div[@id='workspace-container']"));
        Actions act = new Actions(driver);
        act.click(workSpace)
                .sendKeys(workSpace, "skjdfkj")
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .contextClick(workSpace)
                .build().perform();
        testCopy(workSpace);
        testPaste(workSpace);
        testFind(workSpace);
        testAdd(workSpace);
        testAlternativeText(workSpace);
        testLink(workSpace);
        testFormatting(workSpace);
    }
    private void testCopy(WebElement targetSlide){
        String itemXpath = "/div[5]";
        testClickItem(71, targetSlide, itemXpath);
    }
    private void testPaste(WebElement targetSlide){
        String itemXpath = "/div[6]";
        testClickItem(72, targetSlide, itemXpath);
    }
    private void testFind(WebElement targetSlide){
        String itemXpath = "/div[10]";
        testClickItem(72, targetSlide, itemXpath);
    }
    private void testAdd(WebElement targetSlide){
        String itemXpath = "/div[11]";
        testClickItem(72, targetSlide, itemXpath);
    }

    private void testAlternativeText(WebElement targetSlide){
        String itemXpath = "/div[14]";
        String windownXpath = "//div[@class='modal-dialog-title modal-dialog-title-draggable']";
        testClickItemWithWindow(76, targetSlide, itemXpath, windownXpath);
    }
    private void testLink(WebElement targetSlide){
        String itemXpath = "/div[19]";
        testClickItem(72, targetSlide, itemXpath);
    }
    private void testFormatting(WebElement targetSlide){
        String itemXpath = "/div[21]";
        testClickItem(72, targetSlide, itemXpath);
        WebElement menuItem = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='docs-sidebar-tile sketchy-format-options-size-position-tile docs-material']")));
        Actions act = new Actions(driver);
        act.doubleClick(menuItem).build().perform();
        menuItem = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='docs-sidebar-tile-header-checkbox docs-material-gm-checkbox'][1]")));
        menuItem.click();

    }

}
