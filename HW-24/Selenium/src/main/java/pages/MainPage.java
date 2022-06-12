package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//img[@class='logo img-responsive']")
    private WebElement logo;

    @FindBy(xpath = "//input[@class='search_query form-control ac_input']")
    private WebElement searchInputField;

    @FindBy(xpath = "//button[@name='submit_search']")
    private WebElement submitButton;

    @FindBy(xpath = "//h5[@itemprop='name']/a[@title='Blouse'][1]")
    private WebElement itemBlouse;

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInButton;

    public WebElement getLogo() {
        return logo;
    }

    public WebElement getSearchInputField() {
        return searchInputField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getItemBlouse() {
        return itemBlouse;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }
}
