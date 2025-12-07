package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {
    //Implementing page objects using the Page Factory design pattern
    @FindBy (xpath = "//input[@name='username']" )
    public WebElement userNameField;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordField;
    @FindBy(xpath = "//button[contains(@class,'login-button')]")
    public WebElement loginButtonField;
    @FindBy(xpath = "//span[contains(@class,'input-group__message')]")
    public WebElement loginErrorField;
    @FindBy(xpath = "//p[text()='Invalid credentials']")
    public WebElement invalidCredentialsField;
    @FindBy(xpath = "//h6[text()='Dashboard']")
    public WebElement dashboardSign;
    public LoginPage(){
        //pageFactory will initialize all @FindBy WebElements.
      PageFactory.initElements(driver,this);
    }
}
