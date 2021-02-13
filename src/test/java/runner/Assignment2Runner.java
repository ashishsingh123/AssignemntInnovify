package runner;


import io.cucumber.testng.CucumberOptions;
import ConstantVariables.Constant;

/*
@Author - Ashish Kr Singh
 */
@CucumberOptions( tags = {Constant.tags2},glue = {Constant.glue,Constant.hooks}, plugin = {"html:target/cucumber-reports/blogpage/cucumber-pretty","json:target/json-cucumber-reports/blogpage/cukejson.json",
		"testng:target/testng-cucumber-reports/blogpage/cuketestng.xml" }, features = {"src/test/resources/features/Assignment2"})
public class Assignment2Runner extends AbstractTestNGCucumberParallelTests {
	

}
