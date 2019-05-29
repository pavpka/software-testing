package MainMenu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tools extends DropDownMenu {
    private WebDriver driver;
    private WebElement menuButton;

    public Tools(WebDriver driver, WebElement menuButton) {
        super(driver);
        this.driver = driver;
        this.menuButton = menuButton;
    }

    public void testTools() {
        String allMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics docs-menu-attached-button-above']";
        testSearch(allMenuXpath);
        testDictionary(allMenuXpath);
        testQuestionsHistory(allMenuXpath);
        testSetting(allMenuXpath);
        testSpecialAbilities(allMenuXpath);
    }

    private void testSearch(String menuXpath) {
        menuButton.click();
        String buttonXpath = menuXpath + "/div[3]";
        testClickMenu(62, buttonXpath);
    }

    private void testDictionary(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[4]";
        testClickMenu(63, buttonXpath);
    }
    private void testQuestionsHistory(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[5]";
        testClickMenu(64, buttonXpath);
    }
    private void testSetting(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[10]";
        String windowXpath = "//div[@class='docs-preferencesdialog-content']";
        testClickButtonWithWindow(65, buttonXpath, windowXpath);
    }
    private void testSpecialAbilities(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[11]";
        String windowXpath = "//div[@class='docs-material-gm-dialog a11y-settings-dialog docs-gm']";
        testClickButtonWithWindow(66, buttonXpath, windowXpath);
    }
}
