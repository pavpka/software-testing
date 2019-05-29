package MainMenu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Slide extends DropDownMenu {
    private WebDriver driver;
    private WebElement menuButton;

    public Slide(WebDriver driver, WebElement menuButton) {
        super(driver);
        this.driver = driver;
        this.menuButton = menuButton;
    }
    public void testSlide(){
        String allMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics docs-menu-attached-button-above']";
        testNewSlide(allMenuXpath);
        testRepeatSlide(allMenuXpath);
        testDeleteSlide(allMenuXpath);
        testSkipSlide(allMenuXpath);
        testTransition(allMenuXpath);
        testTemplate(allMenuXpath);
        testChangeBackground(allMenuXpath);

    }
    private void testNewSlide(String menuXpath){
        menuButton.click();
        String itemXpath = menuXpath+"/div[1]";
        testClickMenu(52, itemXpath);
    }
    private void testRepeatSlide(String menuXpath){
        menuButton.click();
        String itemXpath = menuXpath+"/div[2]";
        testClickMenu(53, itemXpath);
    }
    private void testDeleteSlide(String menuXpath){
        menuButton.click();
        String itemXpath = menuXpath+"/div[3]";
        testClickMenu(54, itemXpath);
    }
    private void testSkipSlide(String menuXpath){
        menuButton.click();
        String itemXpath = menuXpath+"/div[4]";
        testClickMenu(55, itemXpath);
    }
    private void testTransition(String menuXpath){
        menuButton.click();
        String itemXpath = menuXpath+"/div[9]";
        testClickMenu(56, itemXpath);
    }
    private void testTemplate(String menuXpath){
        menuButton.click();
        String itemXpath = menuXpath+"/div[12]";
        testClickMenu(57, itemXpath);
    }
    private void testChangeBackground(String mainXpath) {
        menuButton.click();
        String itemXpath = mainXpath + "/div[7]";
        String window = "//div[@class='punch-bg-options-content']";
        testClickButtonWithWindow(58, itemXpath, window);
    }
}
