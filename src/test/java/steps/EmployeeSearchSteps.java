package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

public class EmployeeSearchSteps extends CommonMethods {
      @Then("the generated employee ID is saved")
    public void the_generated_employee_id_is_saved() {
          String empId = pimPage.idField.getAttribute("value");
          TestContext.employeeIDs.add(empId);
          System.out.println("generated employee id = " + empId);
    }
    @Then("User clicks on employee list option")
    public void user_clicks_on_employee_list_option() {
       click(pimPage.employeeList);
    }
    @When("the user searches for an employee using {string} with value {string}")
    public void the_user_searches_for_an_employee_using_with_value(String searchType, String searchValue) {
        if (searchValue.equals("dynamic_employee_id")) {
            searchValue = TestContext.employeeIDs.get(TestContext.employeeIDs.size() - 1);
        }
        switch (searchType) {
            case "Employee ID":
                sendText(searchValue, pimPage.idField);
                break;

            case "Full Name":
                sendText(searchValue, pimPage.employeeName);
                break;

            case "Partial Name":
                sendText(searchValue, pimPage.employeeName);
                break;
            default:
                throw new RuntimeException("Invalid search type: " + searchType);
        }
                click(pimPage.searchButton);
    }
    @Then("the system should display employee record")
    public void the_system_should_display_employee_record() {
        waitForElementToBeVisible(pimPage.recordFound);
        Assert.assertTrue(pimPage.recordFound.isDisplayed());
    }
    @Then("the displayed employee records should contain {string}")
    public void the_displayed_employee_records_should_contain(String expectedValue) {
        if (expectedValue.equalsIgnoreCase("dynamic_employee_id")) {
            expectedValue = TestContext.employeeIDs.get(TestContext.employeeIDs.size() - 1);;
        }

        boolean found=false;
       for (WebElement row:pimPage.employeeListRows){
           if (row.getText().contains(expectedValue)) {
               found = true;

               break;
           }
       }
        Assert.assertTrue("Expected value not found in employee list: " + expectedValue, found);
    }
    @Then("the system should display a {string} message")
    public void the_system_should_display_a_message(String expectedMessage) {
       fieldValueValidationByText(expectedMessage,pimPage.noRecordField);
    }
}
