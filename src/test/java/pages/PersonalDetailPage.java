package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class PersonalDetailPage extends CommonMethods {
    @FindBy(xpath = "//a[text()='Personal Details']")
    public WebElement personalDetSign;
    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement firstNameField;
    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement lastNameField;
    public PersonalDetailPage(){
        PageFactory.initElements(driver,this);
    }
}
