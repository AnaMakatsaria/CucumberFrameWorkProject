package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.DashboardPage;
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
        login();
    }

    @When("User clicks on PIM option")
    public void user_clicks_on_pim_option() {
       waitForElementToBeVisible(dashboardPage.dashboardSign);
       click(addEmployeePage.pimOption);
    }

    @When("User clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
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
        //validating expected and actual error messages using the method from common methods
        fieldValueValidationByValue(expectedFirstName, personalDetailPage.firstNameField);
        fieldValueValidationByValue(getExpectedLastName, personalDetailPage.lastNameField);

    }

    @Then("generated employee ID is not empty")
    public void generated_employee_id_is_not_empty() {
        waitForElementToBeVisible(addEmployeePage.idField);
        generatedEmpId=addEmployeePage.idField.getAttribute("value");
        Assert.assertTrue(generatedEmpId !=null &&!generatedEmpId.isBlank());
    }

    @Then("user deletes the created employee")
    public void user_deletes_the_created_employee() {
       deleteEmployeeByID(generatedEmpId);
    }

    @When("User enters {string} {string} {string} field values")
    public void user_enters_field_values(String firstName, String lastName, String ID) {
        String autoIdBefore = addEmployeePage.idField.getAttribute("value");
        expectedFirstName = firstName;
        getExpectedLastName = lastName;
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(lastName, addEmployeePage.lastNameField);
        sendText(ID,addEmployeePage.idField);
        click(addEmployeePage.saveButton);
        waitForElementToBeVisible(addEmployeePage.idField);
        generatedEmpId = addEmployeePage.idField.getAttribute("value");
        Assert.assertEquals("ID was manually appended — it should be system-generated or fully replaceable.",autoIdBefore, generatedEmpId);
     }

    @When("User enters first name {string} and last name {string}")
    public void user_enters_first_name_and_last_name(String firstName, String lastname) {
       sendText(firstName,addEmployeePage.firstNameField);
       sendText(lastname, addEmployeePage.lastNameField);
    }

    @Then("System should display {string} near the respective input fields {string}")
    public void system_should_display_near_the_respective_input_fields(String expectedErrorMessage, String inputField) {
        System.out.println("firstname "+addEmployeePage.firstNameError.getText());
        System.out.println("lastName "+addEmployeePage.lstNameError.getText());
        WebElement actualError;
        switch (inputField){
            case "firstName":
                actualError= addEmployeePage.firstNameError;
                break;
            case "lastName":
                actualError= addEmployeePage.lstNameError;
                break;
            default:
               throw new RuntimeException( ("invalid input field"+inputField));
        }
        waitForElementToBeVisible(actualError);
       //validating expected and actual error messages using the method from common methods
        fieldValueValidationByText(expectedErrorMessage,actualError);
    }
}
