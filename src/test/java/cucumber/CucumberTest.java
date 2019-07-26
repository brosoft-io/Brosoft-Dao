package cucumber;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict=false)
@SuiteClasses({})
public class CucumberTest {

}
