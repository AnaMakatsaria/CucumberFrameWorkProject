package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {
    //Implementing page objects using the Page Factory design pattern
    //use @FindBy to define elements,
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

    //the constructor is responsible for initialization only.
    //When I create a new page object, the constructor runs and calls PageFactory.initElements(driver, this).
       public LoginPage(){

    /* PageFactory.initElements(driver, this) scans this page class for fields annotated with @FindBy.
     * For each @FindBy, it uses the given WebDriver to locate the element in the DOM
     * After that, initializes the fields by assigning real WebElement objects to them, so they’re no longer null. */
      PageFactory.initElements(driver,this);
    }
}
