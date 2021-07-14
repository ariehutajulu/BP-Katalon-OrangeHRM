import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper

response = WS.sendRequest(findTestObject('API/Admin/Get Users', [('baseurl') : GlobalVariable.baseURL,('tokenaut') : GlobalVariable.tokenaut]))

def jsonSlurper = new JsonSlurper()

def res = jsonSlurper.parseText(response.getResponseText())

int x = GlobalVariable.ID

IDemployee = res.data[x].employeeId

println(IDemployee)

GlobalVariable.idP = IDemployee

WS.delay(2)

'Error code 400'
responseBaru = WS.sendRequest(findTestObject('API/Employee/Save Employee', [('baseurl') : GlobalVariable.baseURL,('id') : GlobalVariable.idP, ('tokenaut') : GlobalVariable.tokenaut
            , ('firstname') : 'Jaka', ('middlename') : 'Tingkir', ('lastname') : GlobalVariable.lastName, ('code') : GlobalVariable.code]))

WS.verifyResponseStatusCode(responseBaru, 400)

WS.verifyElementPropertyValue(responseBaru, 'error[0]', 'Failed To Save: Employee Code Exists')

'firstname empty'
responseBaru = WS.sendRequest(findTestObject('API/Employee/Save Employee', [('baseurl') : GlobalVariable.baseURL,('id') : GlobalVariable.idP, ('tokenaut') : GlobalVariable.tokenaut
			, ('firstname') : '', ('middlename') : 'Tingkir', ('lastname') : GlobalVariable.lastName, ('code') : GlobalVariable.code]))

WS.verifyResponseStatusCode(responseBaru, 202)

WS.verifyElementPropertyValue(responseBaru, 'error.text', 'firstName must not be empty')

'middlename empty'
responseBaru = WS.sendRequest(findTestObject('API/Employee/Save Employee', [('baseurl') : GlobalVariable.baseURL,('id') : GlobalVariable.idP, ('tokenaut') : GlobalVariable.tokenaut
			, ('firstname') : 'Jaka', ('middlename') : '', ('lastname') : GlobalVariable.lastName, ('code') : GlobalVariable.code]))

WS.verifyResponseStatusCode(responseBaru, 202)

WS.verifyElementPropertyValue(responseBaru, 'error.text', 'middleName must have a length between 1 and 30')

'lastname empty'
responseBaru = WS.sendRequest(findTestObject('API/Employee/Save Employee', [('baseurl') : GlobalVariable.baseURL,('id') : GlobalVariable.idP, ('tokenaut') : GlobalVariable.tokenaut
			, ('firstname') : 'Jaka', ('middlename') : 'Tingkir', ('lastname') : '', ('code') : GlobalVariable.code]))

WS.verifyResponseStatusCode(responseBaru, 202)

WS.verifyElementPropertyValue(responseBaru, 'error.text', 'lastName must not be empty')
