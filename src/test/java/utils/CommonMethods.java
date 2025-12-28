package utils;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CommonMethods extends PageInitializer{
    public static WebDriver driver;
    public static void openBrowserAndLaunchApplication() throws IOException {

    // read the properties file and launch the browser specified in it.
    // the switch launches the browser set in the properties file without changing the code.
        switch (ConfigReader.read("browser")){
            case "Chrome":
                driver= new ChromeDriver();
                break;
            case "FireFox":
                driver=new FirefoxDriver();
                break;
            case "Edge":
                driver=new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.read("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        //this method will call all the objects when we launch the application
        initializePageObjects();
    }

    public static void closeBrowser(){
        if (driver!=null){
        driver.quit();
    }
}

public void login() throws IOException {
        sendText(ConfigReader.read("userName"),loginPage.userNameField);
        sendText(ConfigReader.read("password"),loginPage.passwordField);
        click(loginPage.loginButtonField);
}

//waits for element to be visible, then clears and sends keys
public void sendText(String text, WebElement element){
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
}

//This method creates and returns a reusable explicit wait object
   public WebDriverWait getWait(){
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
}

//waits for element to bi clickable
   public  void waitForElementToBeClickable(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
}

//will wait for element to bi visible
   public void waitForElementToBeVisible(WebElement element){
        getWait().until(ExpectedConditions.visibilityOf(element));
}

//waits for element to bi clickable and then clicks
    public  void click(WebElement element){
        waitForElementToBeClickable(element);
        element.click();
}

//clear the input box and send the new text
//after creating this method I noticed send text also clearing
//the input box beforehand, so I guess no need of this
    public void resetAndSendText(WebElement element, String correctText){
        getWait().until(ExpectedConditions.visibilityOf(element));
        String value=element.getAttribute("value");
        if (value!=null && !value.isBlank()){
            element.clear();
        }
    getWait().until(ExpectedConditions.visibilityOf(element));
      element.sendKeys(correctText);
}

//this method validates by attribute value, passing expected results as a String, and element(for the actual results)
//not sure about attributeToBeNotEmpty() method
    public void fieldValueValidationByValue(String expectedValue, WebElement element){
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(element,"value"));
        String actualValue=element.getAttribute("value");
    Assert.assertEquals(expectedValue,actualValue);
}

// overloading the above method With optional custom message
    public void fieldValueValidationByValue(String expectedValue, WebElement element, String message){
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
        String actualValue = element.getAttribute("value");

        Assert.assertEquals(message, expectedValue, actualValue);
    }

//this method validates by attribute Text, passing expected results as a String, and element(for the actual results)
    public void fieldValueValidationByText(String expectedValue, WebElement element){
        getWait().until(ExpectedConditions.visibilityOf(element));
        String actualValue=element.getText();
        Assert.assertEquals(expectedValue,actualValue);
    }

// overloading the above method With optional custom message
public void fieldValueValidationByText(String expectedValue, WebElement element, String Message){
    getWait().until(ExpectedConditions.visibilityOf(element));
    String actualValue=element.getText();
    Assert.assertEquals(expectedValue,actualValue);


}

//method to delete employee by ID
    public void deleteEmployeeByID(String empId){
        if (empId==null || empId.isBlank()){
            Assert.fail("Employee ID is null or blank. Deletion aborted.");
        }
        //navigate to employee list
        click(pimPage.employeeList);
        //search for employee ID
        sendText(empId, pimPage.idField);
        click(pimPage.searchButton);
        //wait for checkbox next to employee id to be visible and then click
        waitForElementToBeVisible(dashboardPage.checkboxToDeleteEmp);
        click(dashboardPage.checkboxToDeleteEmp);
        //click delete and after the confirmation window pops up, click delete
        click(dashboardPage.deleteEmp);
        waitForElementToBeVisible(dashboardPage.confirmDeleteEmp);
        click(dashboardPage.confirmDeleteEmp);
    }
    // New overloaded method: delete multiple employees
    public void deleteEmployeeByID(List<String> empIds) {
        if (empIds == null || empIds.isEmpty()) {
            Assert.fail("Employee ID list is null or empty. Deletion aborted.");
        }
        // Loop through the list and delete each employee
        for (String empId : empIds) {
            deleteEmployeeByID(empId);  // reuse single-ID method
        }
    }

//to select by index from the dropdown with the select tag
   public void selectFromDropDown(WebElement dropDown, int index){
 //to select by index
    Select sel=new Select(dropDown);
    sel.selectByIndex(index);
}

//select by visible text
    public void selectFromDropDown(WebElement dropDown,String visibleText){
        Select sel= new Select(dropDown);
        sel.selectByVisibleText(visibleText);
}

//select by value
    public void selectFromDropDown(String value,WebElement dropDown){
        Select sel=new Select(dropDown);
        sel.selectByValue(value);
}

//take screenshot
public byte[] takeScreenshot(String fileName){
    //it accepts array of byte in cucumber for the screenshot
    //Treat this WebDriver as something that can take screenshots.
    //ts is now an object that can actually capture screenshots.
    TakesScreenshot ts = (TakesScreenshot) driver;
    //actually takes a screenshot
    //The screenshot is stored in the variable picByte.
    byte[] picByte = ts.getScreenshotAs(OutputType.BYTES);
    File sourceFile = ts.getScreenshotAs(OutputType.FILE);

    try {
        FileUtils.copyFile(sourceFile,
                new File(Constants.SCREENSHOT_FILEPATH +
                        fileName+" "+
                        getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    return picByte;
}

    public String getTimeStamp(String pattern){
        //this method will return the timestamp which we will add in ss method
        Date date = new Date();

        //yyyy-mm-dd-hh-mm-ss
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
