import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FirstSelenidePractice {
    @Test
    public void isLogoDisplayed(){
        Configuration.browserSize = "1920x1080";
        open("http://automationpractice.com/index.php");

        $(By.xpath("//img[@class='logo img-responsive']")).shouldBe(visible);
    }

    @Test
    public void isSearchFieldDisplayed(){
        open("http://automationpractice.com/index.php");
        //Иногда страница долго грузится, поэтому вставил таймаут, чтобы тесты не падали.
        Configuration.timeout = 10000;

        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(visible);
        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(empty);
    }

    @Test
    public void checkSearchFieldFunctionality(){
        Configuration.browserSize = "1920x1080";
        open("http://automationpractice.com/index.php");

        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(empty);
        $(By.xpath("//input[@class='search_query form-control ac_input']")).setValue("Blouse");
        $(By.xpath("//button[@name='submit_search']")).click();

        $(By.xpath("//h5[@itemprop='name']/a[@class='product-name']")).shouldHave(text("Blouse"));

    }

    @Test
    public void checkCreateAccountPage(){
        Configuration.browserSize = "1920x1080";
        open("http://automationpractice.com/index.php");

        $(By.xpath("//a[@class='login']")).should(visible);
        $(By.xpath("//a[@class='login']")).click();

        $(By.xpath("//h3[contains(text(), 'Create an account')]")).shouldBe(visible);
        $(By.xpath("//input[@id='email_create']")).setValue("test@gmail.com");
        Configuration.timeout = 10000;
        $(By.xpath("//input[@value='Create an account']")).should(exist);
        $(By.xpath("//i[@class='icon-user left']")).click();
        $(By.xpath("//div[@id='columns']")).shouldBe(visible);
    }

    @Test
    public void checkInputInvalidEmail(){
        Configuration.browserSize = "1920x1080";
        open("http://automationpractice.com/index.php");

        $(By.xpath("//a[@class='login']")).should(visible);
        $(By.xpath("//a[@class='login']")).click();
        $(By.xpath("//h3[contains(text(), 'Create an account')]")).shouldBe(visible);
        $(By.xpath("//input[@id='email_create']")).setValue("testgmail.com");
        $(By.xpath("//i[@class='icon-user left']")).click();
        Configuration.timeout = 10000;
        $(By.xpath("//div[@id='create_account_error']/ol/li")).shouldHave(exactText("Invalid email address."));
    }

    @Test
    public void checkDaysDropDown31Days(){
        Configuration.browserSize = "1920x1080";
        open("http://automationpractice.com/index.php");

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
