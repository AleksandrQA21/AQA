import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FirstSeleniumPractice {

    @Test
    public void isLogoDisplayed() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().fullscreen();
        driver.findElement(By.xpath("//img[@class='logo img-responsive']")).click();
        assertTrue(driver.findElement(By.xpath("//img[@class='logo img-responsive']")).isDisplayed());

        driver.quit();
    }

    @Test
    public void isSearchFieldDisplayed(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().fullscreen();

        assertTrue(driver.findElement(By.xpath("//input[@class='search_query form-control ac_input']")).isDisplayed());

        driver.quit();
    }

    @Test
    public void checkSearchFieldFunctionality(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().fullscreen();

        assertTrue(driver.findElement(By.xpath("//input[@class='search_query form-control ac_input']")).isDisplayed());
        driver.findElement(By.xpath("//input[@class='search_query form-control ac_input']")).clear();
        driver.findElement(By.xpath("//input[@class='search_query form-control ac_input']")).sendKeys("Blouse");
        driver.findElement(By.xpath("//button[@name='submit_search']")).click();

        Assert.assertEquals("Blouse",driver.findElement(By.xpath("//h5[@itemprop='name']/a[@class='product-name']")).getText());
        driver.quit();
    }

    @Test
    public void checkCreateAccountPage(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().fullscreen();

        assertTrue(driver.findElement(By.xpath("//a[@class='login']")).isDisplayed());
        driver.findElement(By.xpath("//a[@class='login']")).click();

        assertTrue(driver.findElement(By.xpath("//h3[contains(text(), 'Create an account')]")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("test@gmail.com");

        assertTrue(driver.findElement(By.xpath("//i[@class='icon-user left']")).isDisplayed());
        driver.findElement(By.xpath("//i[@class='icon-user left']")).click();
        assertTrue(driver.findElement(By.xpath("//div[@id='columns']")).isDisplayed());
        driver.quit();
    }

    @Test
    public void checkInputInvalidEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().fullscreen();

        assertTrue(driver.findElement(By.xpath("//a[@class='login']")).isEnabled());
        driver.findElement(By.xpath("//a[@class='login']")).click();

        assertTrue(driver.findElement(By.xpath("//h3[contains(text(), 'Create an account')]")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("testgmail.com");
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(Keys.ENTER);


        Thread.sleep(7000);
        assertEquals(driver.findElement(By.xpath("//div[@id='create_account_error']/ol/li")).getText(), "Invalid email address.", "Something went wrong");
        driver.quit();
    }

    @Test
    public void checkDaysDropDown31Days() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().fullscreen();


        assertTrue(driver.findElement(By.xpath("//a[@class='login']")).isEnabled());
        driver.findElement(By.xpath("//a[@class='login']")).click();

        assertTrue(driver.findElement(By.xpath("//h3[contains(text(), 'Create an account')]")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("test+8@gmail.com");

        assertTrue(driver.findElement(By.xpath("//button[@id='SubmitCreate']")).isDisplayed());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        driver.findElement(By.xpath("//i[@class='icon-user left']")).click();
        Thread.sleep(5000);
        assertTrue(driver.findElement(By.xpath("//select[@id='days']")).isEnabled());
        driver.quit();
    }
}
