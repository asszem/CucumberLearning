package nicebank.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = { "pretty", "html:target/cucumber-html-report", },
		glue = {"nicebank.stepDefinition" }, 
		features = "C:/githubIBM/CucumberLearning/cucumberBook/nicebank/resources/features",
		monochrome = true,
		dryRun = false,
		snippets = SnippetType.CAMELCASE,
		tags = {"@NicebankTag1","@NicebankTag2"}
		)
public class Nicebank {

}
