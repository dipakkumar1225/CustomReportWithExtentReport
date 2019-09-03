package util

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.reporter.ExtentHtmlReporter
import com.aventstack.extentreports.reporter.configuration.Theme
import com.kms.katalon.core.configuration.RunConfiguration as RC
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

@Singleton
public class CustomReport {

	private ExtentHtmlReporter extentHtmlReport
	private ExtentReports extentReport
	private ExtentTest testLogger
	private String currentDir
	private String fileSeperator

	public ExtentTest getExtentTest() {
		return testLogger
	}

	public void setExtentTest(ExtentTest testLogger) {
		this.testLogger =  testLogger
	}

	public ExtentReports initExtentReports(String filePath) {
		currentDir = System.getProperty('user.dir')
		fileSeperator = System.getProperty("file.separator")

		extentReport = new ExtentReports()

		extentHtmlReport = new ExtentHtmlReporter(FilenameUtils.separatorsToSystem(filePath))
		//		extentHtmlReport.config().setDocumentTitle("Automation Report")
		//		extentHtmlReport.config().setReportName("Functional Testing")
//				extentHtmlReport.config().setTheme(Theme.DARK)
		extentReport.attachReporter(extentHtmlReport)
		extentHtmlReport.loadXMLConfig("${currentDir}${fileSeperator}Extents${fileSeperator}extent-config.xml")

		extentReport.setSystemInfo("OS", RC.getOS())
		extentReport.setSystemInfo("Host", RC.getHostAddress())
		extentReport.setSystemInfo("User", RC.getHostName())
		extentReport.setSystemInfo("Host URL", WebUiBuiltInKeywords.getUrl())
		extentReport.setSystemInfo("Environment", RC.getExecutionProfile())
		extentReport.setSystemInfo("Katalon Version", RC.getAppVersion())
	}

	public ExtentTest startTestCase(String testCaseName) {
		testLogger = extentReport.createTest(testCaseName)
		return testLogger
	}

	public String captureScreen() throws IOException {
		TakesScreenshot screen = (TakesScreenshot) DriverFactory.getWebDriver()
		File src = screen.getScreenshotAs(OutputType.FILE)
		String dest = "${currentDir}${fileSeperator}Extents${fileSeperator}ScreenShots${fileSeperator}" + new Date().format('dd_MM_yyy_hh_mm_ss') + ".png"
		File target = new File(dest)
		FileUtils.copyFile(src, target)
		return target.getAbsolutePath()
	}

	public ExtentReports tearDownExtentReports() {
		extentReport.flush()
	}
}
