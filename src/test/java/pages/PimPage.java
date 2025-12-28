package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class PimPage extends CommonMethods {

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
        @FindBy(xpath = "//input[starts-with(@placeholder, 'Type for hints')]")
        public WebElement employeeName;
        @FindBy(xpath = "//a[text()='Employee List']")
        public WebElement employeeList;
       @FindBy(xpath = "//button[@type='submit']")
       public WebElement searchButton;
       @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span'])[1]")
       public WebElement recordFound;
       @FindBy(xpath = "//div[contains(@class,'oxd-table-row')]")
       public List<WebElement>employeeListRows;
       @FindBy(xpath = "//span[text()='No Records Found']")
       public WebElement noRecordField;

        public PimPage(){
            PageFactory.initElements(driver,this);
}
}
