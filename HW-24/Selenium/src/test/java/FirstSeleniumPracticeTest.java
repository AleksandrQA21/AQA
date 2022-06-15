import helpers.MainPageHelper;
import helpers.SignInPageHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.SignInPage;
import utils.DriverFactory;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.PropertyFactory.property;

public class FirstSeleniumPracticeTest {

    WebDriver driver;
    MainPage mainPage;
    SignInPage signInPage;
    MainPageHelper mainPageHelper;
    SignInPageHelper signInPageHelper;

    @BeforeMethod
    public void setUpDriver(){
        driver = new DriverFactory().getDriver(property("browser"));
        mainPage = new MainPage(driver);
        signInPage = new SignInPage(driver);
        mainPageHelper = new MainPageHelper(driver);
        signInPageHelper = new SignInPageHelper(driver);

    }

    @AfterMethod (alwaysRun = true)
    public void setDownDriver (){
        driver.quit();
    }

    @Test
    @Description("Test Description: check Logo is displayed on Main Page")
    @Severity(SeverityLevel.MINOR)
    @Link("https://example.org")
    public void checkLogoDisplayed(){

        mainPageHelper.openMainPage();
        mainPage.getLogo().click();
        assertTrue(mainPage.getLogo().isDisplayed());

        driver.quit();
    }

    @Test
    @Description("Test Description: check search input field is displayed on the Main Page")
    @Severity(SeverityLevel.NORMAL)
    public void checkSearchFieldDisplayed(){

        mainPageHelper.openMainPage();
        assertTrue(mainPage.getSearchInputField().isDisplayed());

    }

    @Test
    @Description("Test Description: check functionality of search input field on the Main Page")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSearchFieldFunctionality(){

        mainPageHelper.openMainPage();
        assertTrue(mainPage.getSearchInputField().isDisplayed());
        mainPage.getSearchInputField().clear();
        mainPage.getSearchInputField().sendKeys("Blouse");
        mainPage.getSubmitButton().click();

        Assert.assertEquals("Blouse",mainPage.getItemBlouse().getText());
    }

    @Test(priority = 1, dataProvider = "userEmail", enabled = false)
    @Description("Test Description: check create account page is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateAccountPage(String email){

        mainPageHelper.openMainPage();

        assertTrue(mainPage.getSignInButton().isDisplayed());
        mainPage.getSignInButton().click();

        assertTrue(signInPage.getCreateAccountForm().isDisplayed());
        signInPage.getEmailInputField().sendKeys(email);

        assertTrue(signInPage.getCreateAccountButton().isDisplayed());
        signInPage.getCreateAccountButton().click();
        assertTrue(signInPage.getCreateAccountPage().isDisplayed());
    }

    @DataProvider
    public Object[][]userEmail(){
        return new Object[][]{{"test@gmail.com"},{"test@hotmail.com"},{"test@yahoo.com"}};
    }

    @Test (priority = 1, dataProvider = "invalidEmails", enabled = false)
    @Description("Test Description: check input invalid Email to the input Email Field on the create account form")
    @Severity(SeverityLevel.CRITICAL)
    public void checkInputInvalidEmail(String val){

        mainPageHelper.openMainPage();

        assertTrue(mainPage.getSignInButton().isEnabled());
        mainPage.getSignInButton().click();

        assertTrue(signInPage.getCreateAccountForm().isDisplayed());
        signInPage.getEmailInputField().sendKeys(val);
        signInPage.getEmailInputField().sendKeys(Keys.ENTER);


        // Используем стратегию явных ожиданий (explicitly)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //создаем объект кдасса WebDriverWait, передаем ему в параметрах наш драйвер и устанавливаем время ожидания 10 сек
        wait.until(ExpectedConditions.visibilityOf(signInPage.getCreateAccountErrorMessage())); // используем метод until объекта wait, выбираем условие выполнение которого заканчивает ожидание

        assertEquals(signInPage.getCreateAccountErrorMessage().getText(), "Invalid email address.", "Something went wrong");
    }

    @DataProvider
    public Object[][] invalidEmails(){
        return new Object[][]{{"testgmail.com"},{"test@@gmail.com"},{"test @gmail.com"},{" "}};
    }

    @Test
    @Description("Test Description: check days dropdown on the SignIn Page")
    @Severity(SeverityLevel.NORMAL)
    public void checkDaysDropDown31Days(){

        mainPageHelper.openMainPage();

        assertTrue(mainPage.getSignInButton().isEnabled());
        mainPage.getSignInButton().click();

        assertTrue(signInPage.getCreateAccountForm().isDisplayed());
        signInPage.getEmailInputField().sendKeys("test+8@gmail.com");

        assertTrue(signInPage.getCreateAccountButton().isDisplayed());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        signInPage.getCreateAccountButton().click();

//        //Применяем явное ожидание
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(signInPage.getDaysDropdown()));

        assertTrue(signInPage.getDaysDropdown().isEnabled());
    }
}
