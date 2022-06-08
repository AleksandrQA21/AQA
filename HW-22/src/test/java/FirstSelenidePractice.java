import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FirstSelenidePractice {

    @BeforeMethod
    public void setUp(){
        Configuration.browserSize = "1920x1080";
        open("http://automationpractice.com/index.php");
        //Иногда страница долго грузится, поэтому вставил таймаут, чтобы тесты не падали.
        Configuration.timeout = 10000;
    }

    @Test (priority = 5, enabled = true)
    public void isLogoDisplayed(){

        $(By.xpath("//img[@class='logo img-responsive']")).shouldBe(visible);
    }

    @Test (priority = 4, enabled = false)
    public void isSearchFieldDisplayed(){

        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(visible);
        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(empty);
    }

    @Test (priority = 3, enabled = true, invocationCount = 3)
    public void checkSearchFieldFunctionality(){

        $(By.xpath("//input[@class='search_query form-control ac_input']")).shouldBe(empty);
        $(By.xpath("//input[@class='search_query form-control ac_input']")).setValue("Blouse");
        $(By.xpath("//button[@name='submit_search']")).click();

        $(By.xpath("//h5[@itemprop='name']/a[@class='product-name']")).shouldHave(text("Blouse"));

    }

    @Test(dataProvider = "userEmail", enabled = true)
    public void checkCreateAccountPage(String email){
        $(By.xpath("//a[@class='login']")).should(visible);
        $(By.xpath("//a[@class='login']")).click();

        $(By.xpath("//h3[contains(text(), 'Create an account')]")).shouldBe(visible);
        $(By.xpath("//input[@id='email_create']")).setValue(email);
        Configuration.timeout = 10000;
        $(By.xpath("//input[@value='Create an account']")).should(exist);
        $(By.xpath("//i[@class='icon-user left']")).click();
        $(By.xpath("//div[@id='columns']")).shouldBe(visible);
    }

    @DataProvider
    public Object[][]userEmail(){
        return new Object[][]{{"test@gmail.com"},{"test@hotmail.com"},{"test@yahoo.com"}};
    }

    @Test(enabled = true, dataProvider = "invalidEmails")
    public void checkInputInvalidEmail(String val){
        $(By.xpath("//a[@class='login']")).should(visible);
        $(By.xpath("//a[@class='login']")).click();
        $(By.xpath("//h3[contains(text(), 'Create an account')]")).shouldBe(visible);
        $(By.xpath("//input[@id='email_create']")).setValue(val);
        $(By.xpath("//i[@class='icon-user left']")).click();
        Configuration.timeout = 10000;
        $(By.xpath("//div[@id='create_account_error']/ol/li")).shouldHave(exactText("Invalid email address."));
    }

    @DataProvider
    public Object[][] invalidEmails(){
        return new Object[][]{{"testgmail.com"},{"test@@gmail.com"},{"test @gmail.com"},{" "}};
    }

    @Test(enabled = true)
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
