package steps;

import io.cucumber.java.Scenario;
import org.junit.After;
import org.junit.Before;
import utils.CommonMethods;

import java.io.IOException;

public class Hooks extends CommonMethods {
    @Before
    public void start() throws IOException {
        //pre-condition for every test case
        openBrowserAndLaunchApplication();
    }
    @After
    public void end(){
        closeBrowser();
    }
}
