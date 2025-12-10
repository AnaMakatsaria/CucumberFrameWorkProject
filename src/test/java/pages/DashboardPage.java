package pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class DashboardPage extends CommonMethods {
    @FindBy(xpath = "//h6[text()='Dashboard']")
    public WebElement dashboardSign;
    @FindBy(xpath = "//a[text()='Employee List']")
    public WebElement employeeList;
    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    public WebElement employeeId;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement searchButton;
    @FindBy(xpath = "(//span[contains(@class,'checkbox-input')])[1]")
    public WebElement checkboxToDeleteEmp;
    @FindBy(xpath = "//i[@class='oxd-icon bi-trash']")
    public WebElement deleteEmp;
    @FindBy(xpath = "//button[contains(@class,'danger orangehrm-button-margin')]")
    public WebElement confirmDeleteEmp;

    public DashboardPage(){
        PageFactory.initElements(driver,this);
    }
}
