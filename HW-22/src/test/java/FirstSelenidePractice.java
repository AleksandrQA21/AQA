import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.aesh.readline.terminal.Key.d;

public class FirstSelenidePractice {

    @BeforeEach
    public void setUp(){
        Configuration.browserSize = "1920x1080";
        //Иногда страница долго грузится, поэтому вставил таймаут, чтобы тесты не падали.
        open("http://automationpractice.com/index.php");
    }

    @RepeatedTest(3)
    @Test
    public void isLogoDisplayed(){

        $(By.xpath("//img[@class='logo img-responsive']")).shouldBe(visible);
    }

    @Test
    public void isSearchFieldDisplayed(){

        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(visible);
        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(empty);
    }

    @Test
    public void checkSearchFieldFunctionality(){

        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(empty);
        $(By.xpath("//input[@class='search_query form-control ac_input']")).setValue("Blouse");
        $(By.xpath("//button[@name='submit_search']")).click();

        $(By.xpath("//h5[@itemprop='name']/a[@class='product-name']")).shouldHave(text("Blouse"));

    }

    @Test
    public void checkCreateAccountPage(){

        $(By.xpath("//a[@class='login']")).should(visible);
        $(By.xpath("//a[@class='login']")).click();

        $(By.xpath("//h3[contains(text(), 'Create an account')]")).shouldBe(visible);
        $(By.xpath("//input[@id='email_create']")).setValue("test@gmail.com");
        Configuration.timeout = 10000;
        $(By.xpath("//input[@value='Create an account']")).should(exist);
        $(By.xpath("//i[@class='icon-user left']")).click();
        $(By.xpath("//div[@id='columns']")).shouldBe(visible);
    }

    @ParameterizedTest
    @ValueSource(strings = {"testgmail.com", "test@@gmail.com"})
    public void checkInputInvalidEmail(String val){

        $(By.xpath("//a[@class='login']")).should(visible);
        $(By.xpath("//a[@class='login']")).click();
        $(By.xpath("//h3[contains(text(), 'Create an account')]")).shouldBe(visible);
        $(By.xpath("//input[@id='email_create']")).setValue(val);
        $(By.xpath("//i[@class='icon-user left']")).click();
        Configuration.timeout = 10000;
        $(By.xpath("//div[@id='create_account_error']/ol/li")).shouldHave(exactText("Invalid email address."));
    }


    @Test
    public void checkDaysDropDown31Days(){

        $(By.xpath("//a[@class='login']")).should(visible);
        $(By.xpath("//a[@class='login']")).click();

        $(By.xpath("//h3[contains(text(), 'Create an account')]")).shouldBe(visible);
        $(By.xpath("//input[@id='email_create']")).setValue("test+7@gmail.com");
        $(By.xpath("//button[@id='SubmitCreate']")).shouldBe(visible);
        $(By.xpath("//i[@class='icon-user left']")).scrollIntoView(true).click();
        Configuration.timeout = 10000;
        $(By.xpath("//select[@id='days']")).should(exist);

    }
}
