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
import pages.DashboardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.beans.Visibility;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {
    @Given("User is navigated into HRMS Website")
    public void user_is_navigated_into_hrms_website() throws IOException {
      openBrowserAndLaunchApplication();
    }

    @When("User enters {string} {string} as login credentials")
    public void user_enters_as_login_credentials(String userName, String password) {
       sendText(userName, loginPage.userNameField);
       sendText(password, loginPage.passwordField);
    }

    @When("User clicks on the Login button")
    public void user_clicks_on_the_login_button() {
        click(loginPage.loginButtonField);
    }

    @Then("System displays {string} as an error message")
    public void system_displays_as_an_error_message(String expectedErrorMessage) {
        //validating expected and actual error messages using the method from common methods
        if (expectedErrorMessage.equals("Invalid credentials")) {
            fieldValueValidationByText(expectedErrorMessage,loginPage.invalidCredentialsField);
        } else if (expectedErrorMessage.equals("Username cannot be empty")) {
            fieldValueValidationByText(expectedErrorMessage, loginPage.loginErrorField);
        } else if (expectedErrorMessage.equals("Password is empty")) {
            fieldValueValidationByText(expectedErrorMessage, loginPage.loginErrorField);
        }
    }

    @When("User attempts to log in with incorrect credentials correct error message is displayed")
    public void user_attempts_to_log_in_with_incorrect_credentials_correct_error_message_is_displayed(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>> loginCredentials=dataTable.asMaps();
        for (Map<String, String>loginCredential:loginCredentials){
            sendText(loginCredential.get("userName"), loginPage.userNameField);
            sendText(loginCredential.get("password"),loginPage.passwordField);
            click(loginPage.loginButtonField);
            String expectedErrorMessage=loginCredential.get("expectedErrorMessage");
            fieldValueValidationByText(expectedErrorMessage,loginPage.invalidCredentialsField);
        }
    }

    @When("User resets with correct credentials")
    public void user_resets_with_correct_credentials(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>> loginCredentials=dataTable.asMaps();
        for (Map<String, String>loginCredential:loginCredentials){
            sendText(loginCredential.get("userName"), loginPage.userNameField);
            sendText(loginCredential.get("password"),loginPage.passwordField);
        }
    }

    @Then("User is logged in successfully and can see the text {string}")
    //validating expected and actual error messages using the method from common methods
    public void user_is_logged_in_successfully_and_can_see_the_text(String dashboard) {
        fieldValueValidationByText(dashboard, dashboardPage.dashboardSign);
    }
}
