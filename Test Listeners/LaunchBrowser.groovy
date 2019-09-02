import org.openqa.selenium.WebDriver

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.MediaEntityModelProvider
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.markuputils.ExtentColor
import com.aventstack.extentreports.markuputils.MarkupHelper
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.relevantcodes.extentreports.ExtentTest

import internal.GlobalVariable as GlobalVariable
import util.CustomReport

class LaunchBrowser {

	static private boolean BROWSER_OPENED = false
	CustomReport customReport = null

	def openBrowser() {
		BROWSER_OPENED = true
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GlobalVariable.BASEURL)
	}

	@BeforeTestCase
	def checkBrowserOpen(TestCaseContext testCaseContext) {
		if (!BROWSER_OPENED) {
			openBrowser()
		}
		String URL = WebUI.getUrl()
		customReport = new CustomReport()
		customReport.systemInfo(URL)
		customReport.startTestCase(testCaseContext.getTestCaseId())
	}

	@AfterTestCase
	def getResultToExtentReport(TestCaseContext testCaseContext) {
		switch (testCaseContext.getTestCaseStatus()){
			case "PASSED" :
				
				customReport.testLogger.log(Status.PASS,MarkupHelper.createLabel("${testCaseContext.getTestCaseId()} \t PASSED", ExtentColor.GREEN))
//				MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(customReport.captureScreen())
//				MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(customReport.captureScreen(), testCaseContext.getTestCaseId(), false).build()
				customReport.testLogger.addScreenCaptureFromPath(customReport.captureScreen())
				customReport.testLogger.assignAuthor("deepak")
				customReport.testLogger.assignCategory("demo category")
				customReport.testLogger.assignDevice("windows")
				break
			case "FAILED" :
				customReport.testLogger.log(Status.FAIL, MarkupHelper.createLabel("${testCaseContext.getTestCaseId()} \t FAILED ", ExtentColor.RED))
				customReport.testLogger.fail(testCaseContext.getMessage())
				break
			case "ERROR" :
				customReport.testLogger.log(Status.ERROR, MarkupHelper.createLabel("${testCaseContext.getTestCaseId()} \t ERROR ", ExtentColor.ORANGE))
				customReport.testLogger.error(testCaseContext.getMessage())
				break
			default :
				customReport.testLogger.log(Status.SKIP, MarkupHelper.createLabel("${testCaseContext.getTestCaseId()} \t SKIP ", ExtentColor.CYAN))
				customReport.testLogger.skip(testCaseContext.getMessage())
		}
		customReport.endResult()
	}

	@BeforeTestSuite
	def openBrowser(TestSuiteContext testSuiteContext) {
		openBrowser()
	}

	

}