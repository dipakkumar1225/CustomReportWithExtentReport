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

public class CustomReport {

	private ExtentHtmlReporter extentHtmlReport
	private ExtentReports extentReport
	private ExtentTest testLogger
	String currentDir = System.getProperty('user.dir')
	String fileSeperator = System.getProperty("file.separator")
	String filePath = FilenameUtils.separatorsToSystem("${currentDir}${fileSeperator}Extents/Example.html")

	public CustomReport() {
		currentDir = System.getProperty('user.dir')
		fileSeperator = System.getProperty("file.separator")
		filePath = FilenameUtils.separatorsToSystem("${currentDir}${fileSeperator}Extents/Example.html")

		extentHtmlReport = new ExtentHtmlReporter(filePath)
		extentHtmlReport.config().setDocumentTitle("Automation Report")
		extentHtmlReport.config().setReportName("Functional Testing")
		extentHtmlReport.config().setTheme(Theme.DARK)
		extentReport = new ExtentReports()
		extentReport.attachReporter(extentHtmlReport)
	}

	public void systemInfo(String URL) {
		extentReport.setSystemInfo("OS", RC.getOS())
		extentReport.setSystemInfo("Host", RC.getHostAddress())
		extentReport.setSystemInfo("User", RC.getHostName())
		extentReport.setSystemInfo("Host URL", URL)
		extentReport.setSystemInfo("Environment", RC.getExecutionProfile())
		extentReport.setSystemInfo("Katalon Version", RC.getAppVersion())
	}

	public ExtentTest startTestCase(String testName) {
		testLogger = extentReport.createTest(testName)
		return testLogger;
	}

	public String captureScreen() throws IOException {
		TakesScreenshot screen = (TakesScreenshot) DriverFactory.getWebDriver()
		File src = screen.getScreenshotAs(OutputType.FILE)
		String dest = "${currentDir}${fileSeperator}Extents${fileSeperator}ScreenShots" + System.currentTimeMillis() + ".png"
		File target = new File(dest)
		FileUtils.copyFile(src, target)
		return target.getAbsolutePath()
	}

	public void endResult() {
		extentReport.flush()
	}
}
