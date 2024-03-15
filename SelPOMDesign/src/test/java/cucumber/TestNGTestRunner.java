package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "ChandNaz/StepDefinition", monochrome = true, tags = "@tag1 @Regression",
plugin = {"html: target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
