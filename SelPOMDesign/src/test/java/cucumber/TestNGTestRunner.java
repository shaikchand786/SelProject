package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//RunWith(Cucumber.class) --> This tells JUnit to run the tests with the Cucumber test runner.
// @CucumberOptions --> Used to configure Cucumber’s behavior

@CucumberOptions(features = "src/test/java/cucumber",	//Specifies the path to the feature files
					glue = "ChandNaz/StepDefinition", //Specifies the package where step definitions (glue code) are located
					monochrome = true, 				//Makes the console output more readable (removes unnecessary characters)
					tags = "@Test1 @Regression",		//Runs only scenarios with the specified tag (e.g., @SmokeTest, @Regression)
					plugin = {"html: target/cucumber.html"})	//Defines plugins for reporting (such as HTML or JSON reports)

public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
