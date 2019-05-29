package MainMenu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Arrange extends DropDownMenu {
    private WebDriver driver;
    private WebElement menuButton;

    public Arrange(WebDriver driver, WebElement menuButton) {
        super(driver);
        this.driver = driver;
        this.menuButton = menuButton;
    }
    public void testArrange(){
        String allMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics docs-menu-attached-button-above']";
        testMove(allMenuXpath);
        testCenter(allMenuXpath);
        testTurn(allMenuXpath);
    }
    private void testMove(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[1]";
        testClickMenu(59, buttonXpath);
    }
    private void testCenter(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[5]";
        testClickMenu(60, buttonXpath);
    }
    private void testTurn(String menuXpath){
        menuButton.click();
        String buttonXpath = menuXpath + "/div[7]";
        testClickMenu(61, buttonXpath);
    }
}
