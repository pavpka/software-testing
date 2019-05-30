package RightClick;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RightClick extends RightClickDropDown{

    private WebDriver driver;

    public RightClick(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void testRightClick(){
        WebElement firstSlide = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='punch-filmstrip-thumbnail-background']")));
        WebElement slidesArea = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='punch-filmstrip-scroll']")));
        //testCut(firstSlide);
        testCopy(firstSlide);
        testPaste(slidesArea);
        testNewSlide(firstSlide);
        testDoubleSlide(firstSlide);
        testSkipSlide(firstSlide);
        testChangeBackground(firstSlide);
        testChangeScheme(firstSlide);
        testChangeTransition(firstSlide);
        testMoveToStart(firstSlide);
    }
    private void testCut(WebElement targetSlide){
        String itemXpath = "/div[4]";
        testClickItem(70, targetSlide, itemXpath);
    }
    private void testCopy(WebElement targetSlide){
        String itemXpath = "/div[5]";
        testClickItem(71, targetSlide, itemXpath);
    }
    private void testPaste(WebElement targetSlide){
        String itemXpath = "/div[6]";
        testClickItem(72, targetSlide, itemXpath);
    }
    private void testDelete(WebElement targetSlide){
        String itemXpath = "/div[8]";
        testClickItem(0, targetSlide, itemXpath);
    }
    private void testNewSlide(WebElement targetSlide){
        String itemXpath = "/div[12]";
        testClickItem(73, targetSlide, itemXpath);
    }
    private void testDoubleSlide(WebElement targetSlide){
        String itemXpath = "/div[13]";
        testClickItem(74, targetSlide, itemXpath);
    }
    private void testSkipSlide(WebElement targetSlide){
        String itemXpath = "/div[14]";
        testClickItem(75, targetSlide, itemXpath);
    }
    private void testChangeBackground(WebElement targetSlide){
        String itemXpath = "/div[16]";
        String windownXpath = "//div[@class='punch-bg-options-content']";
        testClickItemWithWindow(76, targetSlide, itemXpath, windownXpath);
    }
    private void testLayout(WebElement targetSlide){
        String itemXpath = "/div[17]";
        testClickItem(8, targetSlide, itemXpath);
    }
    private void testChangeScheme(WebElement targetSlide){
        String itemXpath = "/div[18]";
        testClickItem(77, targetSlide, itemXpath);
    }
    private void testChangeTransition(WebElement targetSlide){
        String itemXpath = "/div[20]";
        testClickItem(78, targetSlide, itemXpath);
    }
    private void testMoveToStart(WebElement targetSlide){
        String itemXpath = "/div[22]";
        testClickItem(79, targetSlide, itemXpath);
    }
    private void testComment(WebElement targetSlide){
        String itemXpath = "/div[24]";
        Actions act = new Actions(driver);
        String dropDownMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material'][1]";
        try{
            act.contextClick(targetSlide).build().perform();
            WebElement item = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(dropDownMenuXpath + itemXpath)));
            System.out.println(item.getText());
            item.click();
            driver.switchTo().activeElement().sendKeys("comment");
            WebElement send = driver.findElement(By.xpath("//div[@class='sketchy-comment-anchor']/div/div[1]/div[1]/div[2]/div[7]/div[1]"));
            send.click();
            System.out.println("testCase#80: комментарий добавлен");
        }catch (Exception e){
            System.out.println("testCase#80: комментарий не добавлен"+e);
        }
    }
}

