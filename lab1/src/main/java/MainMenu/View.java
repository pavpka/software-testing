package MainMenu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class View extends DropDownMenu {
    private WebDriver driver;
    private WebElement menuButton;

    public View(WebDriver driver, WebElement menuButton) {
        super(driver);
        this.driver = driver;
        this.menuButton = menuButton;
    }

    public void testView() {
        String dropMenu = "//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics docs-menu-attached-button-above']";
        testMaster(dropMenu);
        testAsGrid(dropMenu);
        testStraightedge(dropMenu);
        testShowNotes(dropMenu);
    }

    private void testMaster(String menu) {
        String itemXpath = menu + "/div[5]";
        testCheckButton(42, itemXpath, menuButton);
    }

    private void testAsGrid(String menu) {
        String itemXpath = menu + "/div[6]";
        testCheckButton(43, itemXpath, menuButton);
    }

    private void testStraightedge(String menu) {
        String itemXpath = menu + "/div[9]";
        testCheckButton(44, itemXpath, menuButton);
    }

    private void testShowNotes(String menu) {
        String itemXpath = menu + "/div[13]";
        testCheckButton(45, itemXpath, menuButton);
    }

}
