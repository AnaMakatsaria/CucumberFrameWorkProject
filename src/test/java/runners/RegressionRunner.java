package runners;

import io.cucumber.junit.CucumberOptions;
//smoke runner to execute regression test suite
//JUnit which will be used by default will not understand or run Cucumber Feature files
//so use Cucumber to execute them.

public class RegressionRunner {
    //Here are the instructions for how to run them.
    @CucumberOptions(
            //path to the feature directory
            features = "src/test/resources/features/",

            //connecting feature files to steps with the glue keyword
            glue="steps",

            //dryRun provides missing step definition when true, and executes code when false
            dryRun = false,

            // true tags we can control, and filter test execution
            tags= "@regression",
            //pretty plugin simply formats output in the console, so it’s readable, and structured
            //html plugin creates an HTML report named cucumber.html inside the target folder.
            //json plugin tells Cucumber to store test execution results as a JSON file in the target/cucumber.json
            //This file is the Data Source for advanced reports and CI tools.
            plugin={"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
    )
    public class SmokeRunner {

    }
}
