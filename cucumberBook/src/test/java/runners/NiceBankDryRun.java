package src.test.java.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = { "pretty", "html:target/cucumber-html-report", },
		glue = {"src.test.java.nicebankStepDefinitions", "src.test.java.hooks" }, 
		features = "cucumberBook/src/test/resources/features",
//		features = "C:/at/CucumberLearning/cucumberBook/nicebank/resources/features",
		monochrome = true,
		dryRun = true,
		snippets = SnippetType.CAMELCASE,
		tags = {"@SingleAccount"}
		)
public class NiceBankDryRun {
}
