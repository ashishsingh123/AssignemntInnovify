package runner;

import ConstantVariables.Constant;
import io.cucumber.testng.CucumberOptions;

/*
@Author - Ashish Kr Singh
 */
@CucumberOptions( tags = {Constant.tags1},glue = {Constant.glue,Constant.hooks}, plugin = {"html:target/cucumber-reports/HomePage/cucumber-pretty","json:target/json-cucumber-reports/homepage/cukejson.json",
		"testng:target/testng-cucumber-reports/HomePage/cuketestng.xml" }, features = {"src/test/resources/features/Assignment1"})
public class Assignment1Runner extends AbstractTestNGCucumberParallelTests {
	

}
