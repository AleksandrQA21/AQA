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

public class FirstSeleniumPractice {

    WebDriver driver;
    MainPage mainPage;
    SignInPage signInPage;

    @BeforeMethod
    public void setUpDriver(){
        driver = new DriverFactory().getDriver("CHROME");
        mainPage = new MainPage(driver);
        signInPage = new SignInPage(driver);
        driver.get("http://automationpractice.com/index.php");

    }

    @AfterMethod (alwaysRun = true)
    public void setDownDriver (){
        driver.quit();
    }

    @Test
    public void isLogoDisplayed(){

        mainPage.getLogo().click();
        assertTrue(mainPage.getLogo().isDisplayed());

        driver.quit();
    }

    @Test
    public void isSearchFieldDisplayed(){

        assertTrue(mainPage.getSearchInputField().isDisplayed());

    }

    @Test
    public void checkSearchFieldFunctionality(){

        assertTrue(mainPage.getSearchInputField().isDisplayed());
        mainPage.getSearchInputField().clear();
        mainPage.getSearchInputField().sendKeys("Blouse");
        mainPage.getSubmitButton().click();

        Assert.assertEquals("Blouse",mainPage.getItemBlouse().getText());
    }

    @Test(priority = 1, dataProvider = "userEmail")
    public void checkCreateAccountPage(String email){

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

    @Test (priority = 1, dataProvider = "invalidEmails")
    public void checkInputInvalidEmail(String val){

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
    public void checkDaysDropDown31Days(){

        assertTrue(mainPage.getSignInButton().isEnabled());
        mainPage.getSignInButton().click();

        assertTrue(signInPage.getCreateAccountForm().isDisplayed());
        signInPage.getEmailInputField().sendKeys("test+8@gmail.com");

        assertTrue(signInPage.getCreateAccountButton().isDisplayed());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        signInPage.getCreateAccountButton().click();

        //Применяем явное ожидание
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInPage.getDaysDropdown()));

        assertTrue(signInPage.getDaysDropdown().isEnabled());
    }
}
