package MainMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class File extends DropDownMenu {
    private WebDriver driver;
    private WebElement menuButton;

    public File(WebDriver driver, WebElement menuButton) {
        super(driver);
        this.driver = driver;
        this.menuButton = menuButton;
    }

    public void testFile() {
        String allMenuXpath = "//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics docs-menu-attached-button-above']";
        testOpen(allMenuXpath);
        testImport(allMenuXpath);
        testCopy(allMenuXpath);
        testEmail(allMenuXpath);
        testRename(allMenuXpath);
        testPublish(allMenuXpath);
        testInform(allMenuXpath);
        testSetting(allMenuXpath);
    }

    private void testEmail(String mainXpath) {
        menuButton.click();
        String itemXpath = mainXpath + "/div[12]";
        String window = "/html/body/div[@class='modal-dialog share-client-dialog']";
        testClickButtonWithWindow(30, itemXpath, window);
    }


    private void testOpen(String mainXpath) {
        menuButton.click();
        String itemXpath = mainXpath + "/div[6]";
        String window = "/html/body/div[@class='picker modal-dialog picker-dialog'][2]/div[2]";
        testClickButtonWithWindow(27, itemXpath, window);
    }

    private void testImport(String mainXpath) {
        menuButton.click();
        String itemXpath = mainXpath + "/div[7]";
        String window = "/html/body/div[@class='picker modal-dialog picker-dialog'][3]/div[2]";
        testClickButtonWithWindow(28, itemXpath, window);
    }

    private void testCopy(String mainXpath) {
        menuButton.click();
        String itemXpath = mainXpath + "/div[8]";
        String window = "/html/body/div[@class='modal-dialog docs-dialog docs-copydocdialog']";
        testClickButtonWithWindow(29, itemXpath, window);
    }

    private void testRename(String mainXpath) {
        try {
            menuButton.click();
            WebElement button = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(mainXpath + "/div[16]")));
            button.click();
            driver.switchTo().activeElement().sendKeys("Version 2");
            System.out.println("test-case#31: презентация переименована");
        } catch (Exception e) {
            System.out.println("test-case#31: презентация не переименована");
        }
    }

    private void testPublish(String mainXpath) {
        menuButton.click();
        String itemXpath = mainXpath + "/div[21]";
        String window = "/html/body/div[@class='modal-dialog docs-dialog']";
        testClickButtonWithWindow(32, itemXpath, window);
    }

    private void testInform(String mainXpath) {
        menuButton.click();
        String itemXpath = mainXpath + "/div[24]";
        String window = "/html/body/div[@class='modal-dialog docs-dialog docs-details-dialog']";
        testClickButtonWithWindow(33, itemXpath, window);
    }

    private void testSetting(String mainXpath) {
        menuButton.click();
        String itemXpath = mainXpath + "/div[26]";
        String window = "/html/body/div[@class='modal-dialog docs-dialog sketchy-pagesetup']";
        testClickButtonWithWindow(34, itemXpath, window);
    }

}
