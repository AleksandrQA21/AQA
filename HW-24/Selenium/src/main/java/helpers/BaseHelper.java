package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BaseHelper {

    WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    @Step(value = "User open Page {url}")
    public void openPage(String url){
        driver.get(url);
    }
}
