package MainMenu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Help extends DropDownMenu {
    private WebDriver driver;
    private WebElement menuButton;

    public Help(WebDriver driver, WebElement menuButton) {
        super(driver);
        this.driver = driver;
        this.menuButton = menuButton;
    }

    public void testHelp() {
        String allMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material docs-omnibox-parent docs-menu-hide-mnemonics docs-menu-attached-button-above']";
        testNews(allMenuXpath);
        testReportAProblem(allMenuXpath);
        testKeyShortcuts(allMenuXpath);
    }

    private void testGetHelp(String menuXpath) {
        menuButton.click();
        String buttonXpath = menuXpath + "/div[3]";
        String windowXpath = "//iframe[@id='google-feedback-wizard']";
        testClickButtonWithWindow(0, buttonXpath, windowXpath);
    }
    private void testNews(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[5]";
        String windowXpath = "//iframe[@id='google-feedback-wizard']";
        testClickButtonWithWindow(67, buttonXpath, windowXpath);
    }
    private void testReportAProblem(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[7]";
        String windowXpath = "//iframe[@id='google-feedback-wizard']";
        testClickButtonWithWindow(68, buttonXpath, windowXpath);
    }
    private void testKeyShortcuts(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[10]";
        String windowXpath = "//div[@class='apps-shortcutshelppopup']";
        testClickButtonWithWindow(69, buttonXpath, windowXpath);
    }
}
