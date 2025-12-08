package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {
    @FindBy(xpath = "//span[text()='PIM']")
    public WebElement pimOption;
    @FindBy(xpath = "//a[text()='Add Employee']")
    public WebElement addEmployeeOption;
    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement firstNameField;
    @FindBy(xpath = "//input[@name='middleName']")
    public WebElement middleNameField;
    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement lastNameField;
    @FindBy(xpath ="(//input[@class='oxd-input oxd-input--active'])[2]" )
    public WebElement idField;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;
    @FindBy(xpath = "//input[@name='firstName']/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]")
    public WebElement firstNameError;
    @FindBy(xpath = "//input[@name='lastName']/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]")
    public WebElement lstNameError;

    public AddEmployeePage(){
        PageFactory.initElements(driver,this);
    }

}
