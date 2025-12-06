package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.beans.Visibility;
import java.io.IOException;
import java.time.Duration;

public class LoginSteps extends CommonMethods {
    @Given("User is on HRM login page")

    public void user_is_on_hrm_login_page() throws IOException {
        openBrowserAndLaunchApplication();
    }

    @When("User leaves userName filed empty")
    public void user_leaves_user_name_filed_empty() {
        //intentionally leaving empty
    }
   
    @When("User enters a valid password")
    public void user_enters_a_valid_password() throws IOException, InterruptedException {
       getWait().until(ExpectedConditions.visibilityOf(loginPage.passwordField));
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }

    @When("User clicks on the Login button")
    public void user_clicks_on_the_login_button() {
       click(loginPage.loginButtonField);
    }

    @Then("System displays {string} near the userName field")
    public void system_displays_near_the_user_name_field(String expectedErrorMessage) {
        String actualErrorMessage =loginPage.loginErrorField.getText();
        Assert.assertEquals(expectedErrorMessage,actualErrorMessage);
    }

    @When("User enters a valid userName")
    public void user_enters_a_valid_user_name() throws IOException {
       sendText(ConfigReader.read("userName"),loginPage.UserNameField);
    }
    @When("User leaves a password empty")
    public void user_leaves_a_password_empty() {
        //intentionally leaving empty
    }
    @Then("System displays {string} near the password field")
    public void system_displays_near_the_password_field(String expectedErrorMessage) {
        String actualErrorMessage =loginPage.loginErrorField.getText();
        Assert.assertEquals(expectedErrorMessage,actualErrorMessage);
    }
    @When("User enters {string} in the userName field")
    public void user_enters_in_the_user_name_field(String incorrectUserName) {
        sendText(incorrectUserName, loginPage.UserNameField);
    }
        @Then("System displays {string} as an error message")
    public void system_displays_as_an_error_message(String expectedErrorMessage) {
        String actualErrorMessage= loginPage.invalidCredentialsField.getText();
       Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
        @When("User enters {string} in the password field")
    public void user_enters_in_the_password_field(String incorrectPassword) {
       sendText(incorrectPassword, loginPage.passwordField);
    }

    @When("User corrects the userName field with a correct userName")
    public void user_corrects_the_user_name_field_with_a_correct_user_name() throws IOException {
       resetAndSendText(loginPage.UserNameField,ConfigReader.read("userName"));
    }
    @When("User corrects the password field with a correct password")
    public void user_corrects_the_password_field_with_a_correct_password() throws IOException {
        resetAndSendText(loginPage.passwordField, ConfigReader.read("password"));
    }
    @Then("User is logged in successfully")
    //is it better to get currentUrl and check if it contains dashboard to confirm successfully login
    //or get the locator of dashboard sign and confirm if its presented
    public void user_is_logged_in_successfully() {
        getWait().until(ExpectedConditions.visibilityOf(loginPage.dashboardSign));
       Assert.assertTrue(loginPage.dashboardSign.isDisplayed());
    }

}
