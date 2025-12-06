package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonMethods;
import utils.ConfigReader;

import java.io.IOException;

public class AddEmployeeSteps extends CommonMethods {

//defining global variables here,so the whole class can use them
// for later validating the data
    String expectedFirstName;
    String getExpectedLastName;
    String generatedEmpId;

    @Given("User is logged and navigated to dashboardPage")
    public void user_is_logged_and_navigated_to_dashboard_page() throws IOException {
        openBrowserAndLaunchApplication();
        sendText(ConfigReader.read("userName"),loginPage.UserNameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
        click(loginPage.loginButtonField);
    }
    @When("User clicks on PIM option")
    public void user_clicks_on_pim_option() {
       waitForElementToBeVisible(loginPage.dashboardSign);
       click(addEmployeePage.pimOption);
    }
    @When("User clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
       getWait().until(ExpectedConditions.visibilityOf(addEmployeePage.addEmployeeOption));
       click(addEmployeePage.addEmployeeOption);
    }
    @When("User enters first name {string} and middle name {string} and last name {string}")
    public void user_enters_first_name_and_middle_name_and_last_name(String firstName, String middleName, String lastName) {
        //storing the entered data into global variables to use for validation
        expectedFirstName=firstName;
        getExpectedLastName=lastName;
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);
    }
    @When("User clicks on the save button")
    public void user_clicks_on_the_save_button() {
        click(addEmployeePage.saveButton);
    }
    @Then("employee personal details is displayed")
    public void employee_personal_details_is_displayed() {
        waitForElementToBeVisible(personalDetailPage.personalDetSign);
        Assert.assertTrue(personalDetailPage.personalDetSign.isDisplayed());
        System.out.println(personalDetailPage.personalDetSign.getText());
        System.out.println("expected"+expectedFirstName);
        System.out.println("actual"+personalDetailPage.firstNameField.getAttribute("value"));
        fieldValueValidation(expectedFirstName, personalDetailPage.firstNameField);
        fieldValueValidation(getExpectedLastName, personalDetailPage.lastNameField);
    }
    @Then("generated employee ID is not empty")
    public void generated_employee_id_is_not_empty() {
        waitForElementToBeVisible(addEmployeePage.idField);
        String idValue=addEmployeePage.idField.getAttribute("value");
        generatedEmpId=idValue;
        Assert.assertFalse(idValue.isBlank());

    }
    @Then("user deletes the created employee")
    public void user_deletes_the_created_employee() {
        click(dashboardPage.employeeList);
        sendText(generatedEmpId,dashboardPage.employeeId);
        click(dashboardPage.searchButton);
        click(dashboardPage.checkboxToDeleteEmp);
        click(dashboardPage.deleteEmp);
        click(dashboardPage.confirmDeleteEmp);
    }

}
