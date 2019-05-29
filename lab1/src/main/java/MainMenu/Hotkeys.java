package MainMenu;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

abstract public class Hotkeys {
    private WebDriver driver;

    public Hotkeys(WebDriver driver) {
        this.driver = driver;
    }

    protected void testHotkeys(int testCase, String testName, String letter) {
        Actions act = new Actions(driver);
        try {
            act.sendKeys(Keys.chord(Keys.CONTROL, letter))
                    .build().perform();
            System.out.println("test-case#" + testCase + ": '" + testName + "'применен ");
        } catch (Exception e) {
            System.out.println("test-case#" + testCase + ": '" + testName + "' не применен ");
        }
    }

}
