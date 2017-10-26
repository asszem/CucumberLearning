package src.test.java.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin= {"pretty", "html:out"},
		glue = {"src.test.java.nicebankStepDefinitions", "src.test.java.hooks" }, 
		features = "cucumberBook/src/test/resources/features",
		monochrome = true,
		dryRun = false,
		snippets = SnippetType.CAMELCASE
		)
public class NicebankFullRun {

}