package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    private WebDriver driver;

    public WebDriver getDriver(String name) {
        if ("CHROME".equalsIgnoreCase(name)) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
            driver = new ChromeDriver();
        } else if ("FIREFOX".equalsIgnoreCase(name)) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/gecko.exe");
            driver = new FirefoxDriver();
        } else {
            // TO DO DEFAULT BROWSER
            System.out.println("Error in browser initialization. Try again!");
        }
        if (driver != null) {
            driver.manage().window().fullscreen();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
        return driver;
    }
}
