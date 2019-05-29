package MainMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Format extends Hotkeys {
    private WebDriver driver;
    private WebElement menuButton;

    public Format(WebDriver driver, WebElement menuButton) {
        super(driver);
        this.driver = driver;
        this.menuButton = menuButton;
    }

    public void testFormat() {
        WebElement newSlide = driver.findElement(By.xpath("//div[@id='newSlideButton']"));
        newSlide.click();
        WebElement workSpace = driver.findElement(By.xpath("//div[@id='workspace-container']"));
        Actions act = new Actions(driver);
        act.click(workSpace)
                .sendKeys(workSpace, "test")
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .click(workSpace)
                .build().perform();
        String allMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics docs-menu-attached-button-above']";
        testText();
        testFormattingOptions(allMenuXpath);

    }

    private void testText() {
        testSub();
        testSup();
        testUnderline();
        testItalic();
        testBold();
    }

    private void testSub() {
        testHotkeys(46, "Подстрочный", ",");
    }

    private void testSup() {
        testHotkeys(47, "Надстрочный", ".");
    }

    private void testUnderline() {
        testHotkeys(48, "Подчеркнутый", "U");
    }

    private void testItalic() {
        testHotkeys(49, "Курсивный", "I");
    }

    private void testBold() {
        testHotkeys(50, "Полужирный", "B");
    }

    private void testFormattingOptions(String menuXpath) {
        try {
            menuButton.click();
            String itemXpath = menuXpath + "/div[15]";
            WebElement menuItem = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(itemXpath)));
            menuItem.click();
            WebElement sizeAndPosition = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='docs-sidebar-tile sketchy-format-options-size-position-tile docs-material']")));
            sizeAndPosition.click();
            WebElement weight = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sketchy-format-options-input-control sketchy-size-width-input']/div/div[2]/input")));
            Actions act = new Actions(driver);
            act.doubleClick(weight).sendKeys("20").build().perform();
            System.out.println("test-case#51: ширина изменена");
        } catch (Exception e) {
            System.out.println("test-case#51: ширина не изменена");
        }
    }
}
