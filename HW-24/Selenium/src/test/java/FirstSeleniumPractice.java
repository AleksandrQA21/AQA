import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FirstSeleniumPractice {

    public static final By LOGO = By.xpath("//img[@class='logo img-responsive']");
    public static final By SEARCH_INPUT_FIELD = By.xpath("//input[@class='search_query form-control ac_input']");
    public static final By SUBMIT_BUTTON = By.xpath("//button[@name='submit_search']");
    public static final By PRODUCT_NAME = By.xpath("//h5[@itemprop='name']/a[@class='product-name']");
    public static final By SIGN_IN_BUTTON = By.xpath("//a[@class='login']");
    public static final By CREATE_AN_ACCOUNT_FORM = By.xpath("//h3[contains(text(), 'Create an account')]");
    public static final By EMAIL_INPUT_FIELD = By.xpath("//input[@id='email_create']");
    public static final By CREATE_AN_ACCOUNT_BUTTON = By.xpath("//button[@id='SubmitCreate']");
    public static final By CREATE_AN_ACCOUNT_PAGE = By.xpath("//div[@id='columns']");
    public static final By DAYS_DROPDOWN = By.xpath("//select[@id='days']");
    public static final By CREATE_ACCOUNT_ERROR_MESSAGE = By.xpath("//div[@id='create_account_error']/ol/li");

    WebDriver driver;


    @BeforeEach
    public void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().fullscreen();
//      Пример с использованием неявных ожиданий (implicitly). В этом случаи их было бы достаточно.
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterEach
    public void setDownDriver (){
        driver.quit();
    }

    @Test
    public void isLogoDisplayed(){

        driver.findElement(LOGO).click();
        assertTrue(driver.findElement(LOGO).isDisplayed());

    }

    @Test
    public void isSearchFieldDisplayed(){

        assertTrue(driver.findElement(SEARCH_INPUT_FIELD).isDisplayed());

    }

    @Test
    public void checkSearchFieldFunctionality(){

        assertTrue(driver.findElement(SEARCH_INPUT_FIELD).isDisplayed());
        driver.findElement(SEARCH_INPUT_FIELD).clear();
        driver.findElement(SEARCH_INPUT_FIELD).sendKeys("Blouse");
        driver.findElement(SUBMIT_BUTTON).click();

        //Применяю assertThat из библиотеки AsertJ
        assertThat(driver.findElement(PRODUCT_NAME).getText()).isEqualTo("Blouse");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@gmail.com", "test@hotmail.com", "test@yahoo.com"})
    public void checkCreateAccountPage(String email){

        assertTrue(driver.findElement(SIGN_IN_BUTTON).isDisplayed());
        driver.findElement(SIGN_IN_BUTTON).click();

        assertTrue(driver.findElement(CREATE_AN_ACCOUNT_FORM).isDisplayed());
        driver.findElement(EMAIL_INPUT_FIELD).sendKeys(email);

        assertTrue(driver.findElement(CREATE_AN_ACCOUNT_BUTTON).isDisplayed());
        driver.findElement(CREATE_AN_ACCOUNT_BUTTON).click();
        assertTrue(driver.findElement(CREATE_AN_ACCOUNT_PAGE).isDisplayed());
    }


    @ParameterizedTest
    @ValueSource(strings = {"testgmail.com", "test@@gmail.com", "test@@gmail.com", " "})
    public void checkInputInvalidEmail(String val){

        assertTrue(driver.findElement(SIGN_IN_BUTTON).isEnabled());
        driver.findElement(SIGN_IN_BUTTON).click();

        assertTrue(driver.findElement(CREATE_AN_ACCOUNT_FORM).isDisplayed());
        driver.findElement(EMAIL_INPUT_FIELD).sendKeys(val);
        driver.findElement(EMAIL_INPUT_FIELD).sendKeys(Keys.ENTER);


        // Используем стратегию явных ожиданий (explicitly)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //создаем объект кдасса WebDriverWait, передаем ему в параметрах наш драйвер и устанавливаем время ожидания 10 сек
        wait.until(ExpectedConditions.presenceOfElementLocated(CREATE_ACCOUNT_ERROR_MESSAGE)); // используем метод until объекта wait, выбираем условие выполнение которого заканчивает ожидание

        //Применяю assertThat из библиотеки AsertJ
        assertThat(driver.findElement(CREATE_ACCOUNT_ERROR_MESSAGE).getText()).isEqualTo("Invalid email address.");
    }

    @Test
    public void checkDaysDropDown31Days(){

       assertTrue(driver.findElement(SIGN_IN_BUTTON).isEnabled());
        driver.findElement(SIGN_IN_BUTTON).click();

        assertTrue(driver.findElement(CREATE_AN_ACCOUNT_FORM).isDisplayed());
        driver.findElement(EMAIL_INPUT_FIELD).sendKeys("test+8@gmail.com");

        assertTrue(driver.findElement(CREATE_AN_ACCOUNT_BUTTON).isDisplayed());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        driver.findElement(CREATE_AN_ACCOUNT_BUTTON).click();

        //Применяем явное ожидание
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(DAYS_DROPDOWN));

        assertTrue(driver.findElement(DAYS_DROPDOWN).isEnabled());
    }
}
