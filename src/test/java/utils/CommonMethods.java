package utils;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class CommonMethods extends PageInitializer{
    public static WebDriver driver;
    public static void openBrowserAndLaunchApplication() throws IOException {

        //read the properties file and launch the browser specified in it.
        //The switch launches the browser set in the properties file without changing the code.
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
public void sendText(String text, WebElement element){
        element.clear();
        element.sendKeys(text);
}
public WebDriverWait getWait(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
}
public  void waitForElementToBeClickable(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
}
public void waitForElementToBeVisible(WebElement element){
        getWait().until(ExpectedConditions.visibilityOf(element));
}
public  void click(WebElement element){
        waitForElementToBeClickable(element);
        element.click();
}
//clear the input box and send the new text
    //after creating this method I noticed send text also clearing
// the input box beforehand, so I guess no need of this
public void resetAndSendText(WebElement element, String correctText){
        getWait().until(ExpectedConditions.visibilityOf(element));
        String value=element.getAttribute("value");
        if (value!=null && !value.isBlank()){
            element.clear();
        }
    getWait().until(ExpectedConditions.visibilityOf(element));
      element.sendKeys(correctText);
}
//this method validates by passing expected results, and element
public void fieldValueValidation(String expectedValue, WebElement element){
        getWait().until(ExpectedConditions.visibilityOf(element));
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(element,"value"));
        String actualValue=element.getAttribute("value");
    Assert.assertEquals(expectedValue,actualValue);
}
public static void selectFromDropDown(WebElement dropDown, int index){
        //to select by index
    Select sel=new Select(dropDown);
    sel.selectByIndex(index);
}
public static void selectFromDropDown(WebElement dropDown,String visibleText){
        Select sel= new Select(dropDown);
        sel.selectByVisibleText(visibleText);
}
public static void selectFromDropDown(String value,WebElement dropDown){
        Select sel=new Select(dropDown);
        sel.selectByValue(value);
}
}
