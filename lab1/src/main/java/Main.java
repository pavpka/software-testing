import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Julia\\Downloads\\chrome\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized"); //Уставливаем размер окна на весь экран
        WebDriver driver = new ChromeDriver(option);//Конструктор класса ChromeDriver запускает ChromeDriver сервер и открывает веб-браузер Chrome с указанными аргументами
        //driver.get("https://www.google.ru/intl/ru/slides/about/"); //Переход на главную страницу Google Slides
        //driver.findElement(By.xpath("//a[@class='maia-button button-download']")).click();
        SignUp signUp = new SignUp(driver);//создание экземпляра класса регистрации
        //signUp.signUp();//запуск тестовых сценариев в модуле "регистрация"

        driver.get("https://www.google.ru/intl/ru/slides/about/");
        driver.findElement(By.xpath("//a[@class='maia-button button-download']")).click();
        SignIn signIn = new SignIn(driver);
        signIn.signIn();//запуск тестовых сценариев в модуле "авторизация" и последующих
        System.out.println("Тестирование завершено");
        //driver.quit();// завершение процесса
    }

}