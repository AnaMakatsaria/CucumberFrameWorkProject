package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;
import java.io.IOException;

public class Hooks extends CommonMethods {
//pre-condition for all feature file
    @Before
    public void  start() throws IOException {
        openBrowserAndLaunchApplication();
    }

// post-condition for all feature file
    @After
    public void end(Scenario scenario){
// Adding screenshots to the Cucumber report at the end of each test execution.
// We don't want to hard-code the screenshot file name, so we use the Scenario object.
// The Scenario class contains full test details (name, status, timestamps, etc.).
// We pass scenario.getName() into takeScreenshot() so each screenshot is named after the test case.
// Calling takeScreenshot() and passing scenario.getName() so the screenshot
// is labeled with the test's name. The method returns the screenshot as byte[],
// which we store in 'pic' so it can be attached to the Cucumber report.
// After capturing the screenshot, we close the browser to end the test session.
    byte [] pic;

//separate folders for failed and passed
        if (scenario.isFailed()) {
            pic = takeScreenshot("failed/" + scenario.getName());
        }else {
            pic=takeScreenshot("passed"+scenario.getName());
        }
        closeBrowser();
    }
}
