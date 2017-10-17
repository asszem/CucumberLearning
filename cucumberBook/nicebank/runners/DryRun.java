package nicebank.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = { "pretty", "html:target/cucumber-html-report", },
		glue = {"nicebank.stepDefinitionsForNiceBank" }, 
		features = "C:/githubIBM/CucumberLearning/cucumberBook/nicebank/resources/features",
		monochrome = true,
		dryRun = true,
		snippets = SnippetType.CAMELCASE,
		tags = {"@HappyPath"}
		)
public class DryRun {
}
