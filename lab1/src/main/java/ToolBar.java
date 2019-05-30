import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class ToolBar {

    private WebDriver driver;


    public ToolBar(WebDriver driver) {
        this.driver = driver;
    }

    public void testToolBar(List<WebElement> items) {
        List<WebElement> displayedItems = new ArrayList<WebElement>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isDisplayed() && items.get(i).getAttribute("id").contains("Button")) {
                displayedItems.add(items.get(i));
            }
        }
        testButtons(displayedItems);
    }
    //метод, запускающий тестовые сценарии в модуле "Проверка панели инструментов"
    private void testButtons(List<WebElement> items) {
        for (int i=0; i<items.size(); i++){
            if (items.get(i).getAttribute("id").equals("slideBackgroundButton")) {
                testSlideBackgroundButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("slideLayoutMenuButton")) {
                testSlideLayoutMenuButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("slideThemeButton")) {
                testSlideThemeButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("slideTransitionButton")) {
                testSlideTransitionButton(items.get(i));
            }
        }
        for (int i = 0; i<items.size(); i++) {
            if (items.get(i).getAttribute("id").equals("newSlideButton")) {
                testNewSlideButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("undoButton")) {
                testUndoButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("redoButton")) {
                testRedoButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("zoomButton")) {
                testZoomButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("textboxButton")) {
                testTextboxButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("insertImageMenuButton")) {
                //testInsertImageMenuButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("shapeButton")) {
                testShapeButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("lineButton")) {
                testLineButton(items.get(i));
            } else if (items.get(i).getAttribute("id").equals("insertCommentButton")) {
                testInsertCommentButton(items.get(i));
            }
        }
    }

    private void testNewSlideButton(WebElement button) {
        try {
            Thread.sleep(1000);
            button.click();
            System.out.println("test-case#15: добавлен новый слайд");

        } catch (Exception e) {
            System.out.println("test-case#15: новый слайд не был добавлен"+ e);
        }
    }

    private void testUndoButton(WebElement button) {
        try {
            button.click();
            System.out.println("test-case#16: предыдущее действие отменено");

        } catch (Exception e) {
            System.out.println("test-case#16: предыдущее действие не отменено");
        }
    }

    private void testRedoButton(WebElement button) {
        try {
            button.click();
            System.out.println("test-case#17: предыдущее действие повторено");

        } catch (Exception e) {
            System.out.println("test-case#17: предыдущее действие не повторено");
        }
    }

    private void testZoomButton(WebElement button) {
        try {
            button.click();
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.xpath("//*[@id=\"canvas-container\"]")))
                    .click()
                    .build()
                    .perform();
            button.click();
            System.out.println("test-case#18: масштаб изменен");

        } catch (Exception e) {
            System.out.println("test-case#18: масштаб не изменен");
        }
    }

    private void testTextboxButton(WebElement button) {
        try {
            button.click();
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.xpath("//*[@id=\"canvas-container\"]")))
                    .click()
                    .sendKeys("test")
                    .build()
                    .perform();
            System.out.println("test-case#19: текстовое поле добавлено");

        } catch (Exception e) {
            System.out.println("test-case#19: текстовое поле не добавлено");
        }
    }

   private void testInsertImageMenuButton(WebElement button) {
        try {
            button.click();
            Actions act = new Actions(driver);
            act.moveByOffset(10, 60)
                    .click()
                    .build()
                    .perform();
            driver.switchTo().frame(0);
            WebElement newFrame = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='docs-onepick-integrated-sidebar']")));
            newFrame.findElement(By.xpath("div/div[2]/iframe/html/body/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[1]/input[2]")).sendKeys("trees");

            WebElement foundImages = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"yDmH0d\"]/div/div[7]/div/div/div/div[2]/div/div/div[2]")));
            List<WebElement> images = foundImages.findElements(By.xpath("/*"));
            images.get(3).click();
            WebElement select = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"yDmH0d\"]/div/div[9]/div/div[3]/div")));
            select.click();
            System.out.println("test-case#20: изображение добавлено");

        } catch (Exception e) {
            System.out.println("test-case#20: изображение не добавлено" + e);
        }
    }

    private void testShapeButton(WebElement button) {
        try {
            WebElement workSpace = driver.findElement(By.xpath("//*[@id=\"canvas-container\"]"));
            button.click();
            WebElement dropDown = driver.findElement(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel'][2]/div[2]"));
            dropDown.click();
            List<WebElement> figures = driver.findElements(By.xpath("/html/body/div[@class='goog-menu goog-menu-vertical docs-material']/div[1]/table/tbody/tr/td/div"));
            Actions act = new Actions(driver);
            for (WebElement figure : figures) {
                act.click(figure)
                        .moveToElement(workSpace)
                        .click()
                        .click(button)
                        .click(dropDown)
                        .build()
                        .perform();
            }
            System.out.println("test-case#20: фигура добавлена");

        } catch (Exception e) {
            System.out.println("test-case#20: фигура не добавлена");
        }
    }

    private void testLineButton(WebElement button) {
        try {
            button.click();
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.xpath("//*[@id=\"canvas-container\"]")))
                    .click()
                    .moveToElement(driver.findElement(By.xpath("//*[@id=\"filmstrip\"]/div")))
                    .click()
                    .build()
                    .perform();
            System.out.println("test-case#21: линия добавлена");

        } catch (Exception e) {
            System.out.println("test-case#21: линия не добавлена" + e);
        }
    }

    private void testInsertCommentButton(WebElement button) {
        try {
            button.click();
            WebElement input = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]/div/div[2]/textarea")));
            input.sendKeys("test");
            driver.findElement(By.xpath("//*[@id=\"workspace\"]/div[2]/div/div[1]/div[1]/div[2]/div[7]/div[1]")).click();
            System.out.println("test-case#22: комментарий добавлен");

        } catch (Exception e) {
            System.out.println("test-case#22: комментарий не добавлен");
        }
    }

    private void testSlideBackgroundButton(WebElement button) {
        try {
            button.click();
            WebElement pic = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"punch-id-bg-image-container\"]/div")));
            pic.click();
            WebElement frame = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='picker modal-dialog picker-dialog']/div[2]/iframe")));
            driver.switchTo().frame(frame);
            driver.findElement(By.xpath("//*[@id=\":g\"]")).click();
            WebElement input = driver.findElement(By.xpath("//*[@id=\"doclist\"]/div/div[4]/div[2]/div/div[2]/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/input"));
            input.sendKeys("sky");
            WebElement search = driver.findElement(By.xpath("//*[@id=\"doclist\"]/div/div[4]/div[2]/div/div[2]/div/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]"));
            search.click();
            WebElement selectPic = (new WebDriverWait(driver, 5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"doclist\"]/div[1]/div[4]/div[2]/div[1]/div[2]/div/div[3]/div[1]/div/div[1]/div[1]/div[2]/div/span/div[1]")));
            selectPic.click();
            driver.findElement(By.xpath("//*[@id=\"doclist\"]/div[1]/div[4]/div[2]/div/div[2]/div/div[3]/div[2]/div[1]/div/div[1]")).click();
            driver.switchTo().defaultContent();
            WebElement buttonClose = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-dialog-buttons']/button[2]")));
            buttonClose.click();
            System.out.println("test-case#23: фон изменен");
        } catch (Exception e) {
            System.out.println("test-case#23: фон не изменен");
        }
    }

    private void testSlideLayoutMenuButton(WebElement button) {
        try{
            button.click();
            List<WebElement> layouts = driver.findElements(By.xpath("//div[@class='goog-palette punch-layouts-palette docs-preview-palette']/table/tbody/tr/td/div"));
            layouts.get(layouts.size()-4).click();
            System.out.println("test-case#24: макет изменен");
        }catch (Exception e){
            System.out.println("test-case#24: макет не изменен");
        }
    }

    private void testSlideThemeButton(WebElement button) {
        try {
            button.click();
            List<WebElement> themes = driver.findElements(By.xpath("/html/body/div[@class='punch-theme-sidebar docs-material']/div[2]/div/div[3]/div"));
            for (WebElement theme : themes) {
                Thread.sleep(1000);
                theme.click();
            }
                System.out.println("test-case#25: тема изменена");
        }catch (Exception e){
            System.out.println("test-case#25: тема не изменена");
        }
    }

    private void testSlideTransitionButton(WebElement button) {
        try{
            button.click();
            WebElement dropDown = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='punch-animation-sidebar-scroll']/div/div/div[2]/div[1]/div")));
            dropDown.click();
            WebElement transition = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='goog-menuitem goog-menuitem-highlight']")));
            List<WebElement> list = transition.findElements(By.xpath("/div"));
            for (WebElement item: list){
                item.click();
            }
            System.out.println("test-case#26: добавлен переход");
        }catch (Exception e){
            System.out.println("test-case#26: переход не добавлен" + e);
        }
    }
}
