
import MainMenu.*;
import RightClick.RightClick;
import org.openqa.selenium.*;

import java.util.List;

public class Menu {
    private WebDriver driver;

    public Menu(WebDriver driver) {
        this.driver = driver;
    }

    public void testDropDownMenu(List<WebElement> menuItems) {
        for (WebElement menuItem : menuItems) {
            if (menuItem.getAttribute("id").equals("docs-file-menu")) {
                File file = new File(driver, menuItem);
                file.testFile();
            } else if (menuItem.getAttribute("id").equals("docs-edit-menu")) {
                Edit edit = new Edit(driver, menuItem);
                edit.testEdit();
            } else if (menuItem.getAttribute("id").equals("docs-view-menu")) {
                View view = new View(driver, menuItem);
                view.testView();
            } else if (menuItem.getAttribute("id").equals("docs-format-menu")) {
                Format format = new Format(driver, menuItem);
                format.testFormat();
            } else if (menuItem.getAttribute("id").equals("punch-slide-menu")) {
                Slide slide = new Slide(driver, menuItem);
                slide.testSlide();
            } else if (menuItem.getAttribute("id").equals("sketchy-arrange-menu")) {
                Arrange arrange = new Arrange(driver, menuItem);
                arrange.testArrange();
            } else if (menuItem.getAttribute("id").equals("docs-tools-menu")) {
                Tools tools = new Tools(driver, menuItem);
                tools.testTools();
            } else if (menuItem.getAttribute("id").equals("docs-help-menu")) {
                Help help = new Help(driver, menuItem);
                help.testHelp();
            }
        }
        RightClick rightClick = new RightClick(driver);
        rightClick.testRightClick();
    }
}
