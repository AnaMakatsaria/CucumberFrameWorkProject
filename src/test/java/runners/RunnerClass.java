package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import utils.CommonMethods;

//because JUnit which will be used by default will not understand or run Cucumber Feature files,
//so use Cucumber to execute them.
@RunWith(Cucumber.class)
//Here are the instructions for how to run them.

@CucumberOptions(
        //path to the feature directory
        features = "src/test/resources/features/",

        //connecting feature files to steps with the glue keyword
        glue="steps",

        //dryRun provides missing step definition when true, and executes code when false
        dryRun = false,

        // true tags we can control, and filter test execution
        tags= "@empSearch",

        //pretty plugin simply formats output in the console, so it’s readable, and structured (for local reference)
        //html plugin creates an HTML report named cucumber.html inside the target folder.
        //json plugin tells Cucumber to store test execution results as a JSON file in the target/cucumber.json
        //This file is the Data Source for advanced reports and CI tools.
        //Rerun plugin - after the test run → writes all failed scenarios paths in failed.txt file
        //In the next run → reads that file and only reruns those failed tests
        plugin={"pretty", "html:target/cucumber.html", "json:target/cucumber.json", "rerun:target/failed.txt"}
)
public class RunnerClass extends CommonMethods {
}
