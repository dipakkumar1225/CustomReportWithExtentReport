import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.testdata.ExcelData
import com.kms.katalon.core.testdata.InternalData as InternalData
import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.markuputils.ExtentColor
import com.aventstack.extentreports.markuputils.MarkupHelper
import com.aventstack.extentreports.reporter.ExtentHtmlReporter
import com.aventstack.extentreports.reporter.configuration.Theme

WebUI.click(findTestObject('InputForms/linkHeaderSimpleFormDemo'))

WebUI.sendKeys(findTestObject('InputForms/txtUserMessage'), 'This is first demo example in Katalon Studio')

WebUI.click(findTestObject('InputForms/btnShowMessage'))

WebUI.scrollToElement(findTestObject('InputForms/headerTwoInputFields'), 0)

Object dataSource = findTestData('Sample1/TestData').getSourceUrl().toString()

Object excelFile = ExcelFactory.getExcelDataWithDefaultSheet(dataSource,'Demo', true)

List l = null

for (def index : (1..excelFile.getRowNumbers())) {
    l = new ArrayList()

    l.addAll(excelFile.allData)
	println index 
	
	WebUI.clearText(findTestObject('InputForms/txtInputA'))
	
	WebUI.clearText(findTestObject('InputForms/txtInputB'))
	
	WebUI.sendKeys(findTestObject('InputForms/txtInputA'), findTestData('Sample1/TestData').getValue(1, index))
	
	WebUI.sendKeys(findTestObject('InputForms/txtInputB'), findTestData('Sample1/TestData').getValue(2, index))
	
	WebUI.click(findTestObject('InputForms/btnSum'))
}

println(l)

String execID = RunConfiguration.getExecutionSourceName()

//ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("${currentDir}{fileSeperator}${reportFilePath}")
//ExtentReports extent = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.setupExtentReport'(execID)
//
//String tcID = 'Test Case 1'
//
//ExtentTest extentTest = CustomKeywords.'com.katalon.plugin.keyword.extentReport.Extent.startExtentTest'(tcID, 'Extent Test',
//	extent)
//
//CustomKeywords.'com'


