package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class SignInPageHelper extends BaseHelper {

    WebDriver driver;

    public SignInPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step(value = "User open SignIn Page for {url}")
    public void openSignInPage(){
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }
}
