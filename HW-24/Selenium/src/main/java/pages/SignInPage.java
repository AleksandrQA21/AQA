package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    private WebDriver driver;

    public SignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//h3[contains(text(), 'Create an account')]")
    private WebElement createAccountForm;

    @FindBy(xpath = "//input[@id='email_create']")
    private WebElement emailInputField;

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//div[@id='columns']")
    private WebElement daysDropdown;

    @FindBy(xpath = "//div[@id='create_account_error']/ol/li" )
    private WebElement createAccountErrorMessage;

    @FindBy(xpath = "//div[@id='columns']")
    private WebElement createAccountPage;

    public WebElement getCreateAccountForm() {
        return createAccountForm;
    }

    public WebElement getEmailInputField() {
        return emailInputField;
    }

    public WebElement getCreateAccountButton() {
        return createAccountButton;
    }

    public WebElement getDaysDropdown() {
        return daysDropdown;
    }

    public WebElement getCreateAccountErrorMessage() {
        return createAccountErrorMessage;
    }

    public WebElement getCreateAccountPage() {
        return createAccountPage;
    }
}
