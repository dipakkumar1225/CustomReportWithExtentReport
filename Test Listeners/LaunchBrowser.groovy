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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import util.CustomReport

class LaunchBrowser {

	static private boolean BROWSER_OPENED = false
	CustomReport customReport = CustomReport.instance

	def openBrowser() {
		BROWSER_OPENED = true
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GlobalVariable.BASEURL)
		customReport.initExtentReports("Extents/ExtentReport.html")
	}

	@BeforeTestCase
	def checkBrowserOpen(TestCaseContext testCaseContext) {
		if (!BROWSER_OPENED) {
			openBrowser()
		}
		customReport.startTestCase(testCaseContext.getTestCaseId())
	}

	@AfterTestCase
	def getResultToExtentReport(TestCaseContext testCaseContext) {
		switch (testCaseContext.getTestCaseStatus()){
			case "PASSED" :
				customReport.getExtentTest().log(Status.PASS,MarkupHelper.createLabel("${testCaseContext.getTestCaseId()} \t PASSED", ExtentColor.GREEN))
			//				MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(customReport.captureScreen())
//				MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(customReport.captureScreen(), testCaseContext.getTestCaseId(), false).build()
				customReport.getExtentTest().addScreenCaptureFromPath(customReport.captureScreen())
				customReport.getExtentTest().assignAuthor("Deepak ")
				customReport.getExtentTest().assignCategory("Input Field")
				customReport.getExtentTest().assignDevice("Test Assign Devices")
				break
			case "FAILED" :
				customReport.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel("${testCaseContext.getTestCaseId()} \t FAILED ", ExtentColor.RED))
				customReport.getExtentTest().fail(testCaseContext.getMessage())
				break
			case "ERROR" :
				customReport.getExtentTest().log(Status.ERROR, MarkupHelper.createLabel("${testCaseContext.getTestCaseId()} \t ERROR ", ExtentColor.ORANGE))
				customReport.getExtentTest().error(testCaseContext.getMessage())
				break
			default :
				customReport.getExtentTest().log(Status.SKIP, MarkupHelper.createLabel("${testCaseContext.getTestCaseId()} \t SKIP ", ExtentColor.CYAN))
				customReport.getExtentTest().skip(testCaseContext.getMessage())
		}
		customReport.tearDownExtentReports()
	}

	@BeforeTestSuite
	def openBrowser(TestSuiteContext testSuiteContext) {
		openBrowser()
	}
}