package RightClick;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//
abstract public class RightClickDropDown {

    private WebDriver driver;
    private String title;

    public RightClickDropDown(WebDriver driver) {
        this.driver = driver;
    }

    protected void testClickItem(int testCase, WebElement slide, String itemXpath) {
        Actions act = new Actions(driver);
        String dropDownMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material'][1]";
        try {
            title = rightClick(act, slide, dropDownMenuXpath, itemXpath);
            if (driver.findElements(By.xpath("//div[@class='docs-clipboardshortcutsdialog-shortcuts']")).size() > 0) {
                act.sendKeys(Keys.ESCAPE)
                        .build()
                        .perform();
            }

            System.out.println("test-case#" + testCase + ": кнопка " + title + " нажата ");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("test-case#" + testCase + ": кнопка " + title + " не нажата " + e);
        }
    }

    protected void testClickItemWithWindow(int testCase, WebElement slide, String itemXpath, String windowXpath) {
        Actions act = new Actions(driver);
        try {
            String dropDownMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material'][1]";
            title = rightClick(act, slide, dropDownMenuXpath, itemXpath);
            WebElement window = (new WebDriverWait(driver, 11))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(windowXpath)));
            act.sendKeys(Keys.ESCAPE)
                    .build()
                    .perform();
            System.out.println("test-case#" + testCase + ": кнопка " + title + " нажата");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("test-case#" + testCase + ": кнопка " + title + " не нажата " + e);
        }

    }

    private String rightClick(Actions act, WebElement slide, String dropDownMenuXpath, String itemXpath) {
        act.contextClick(slide).build().perform();
        WebElement item = (new WebDriverWait(driver, 12))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(dropDownMenuXpath + itemXpath)));
        title = item.getText();
        item.click();
        return title;
    }
}
