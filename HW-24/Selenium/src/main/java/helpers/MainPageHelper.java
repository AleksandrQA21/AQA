package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class MainPageHelper extends BaseHelper{

    WebDriver driver;

    public MainPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step(value = "User open Home Page for {url}")
    public void openMainPage() {
        driver.get("http://automationpractice.com/index.php");
    }
}
