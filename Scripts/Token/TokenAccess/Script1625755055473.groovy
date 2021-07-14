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

'Send request for login'
response = WS.sendRequest(findTestObject('API/Token/Post Token', [('url') : GlobalVariable.aut_url, ('tokenaut') : GlobalVariable.tokenaut]))

def jsonSlurper = new JsonSlurper()

def res = jsonSlurper.parseText(response.getResponseText())

AutToken = res.access_token

//WS.verifyResponseStatusCode(response, 200)
//
//WS.verifyElementPropertyValue(response, 'expires_in', '3600')
//
println(AutToken)
//WS.getElementPropertyValue(response, 'access_token')
GlobalVariable.tokenaut = AutToken

'Send request with token'
response = WS.sendRequest(findTestObject('API/Token/Post Token', [('url') : GlobalVariable.aut_url, ('tokenaut') : GlobalVariable.tokenaut]))

response = WS.sendRequest(findTestObject('API/Token/Get Token', [('token') : res.access_token]))

//response = WS.sendRequest(findTestObject('API/Admin/Get Users', [('tokenaut') : res.access_token]))
//
//response = WS.sendRequest(findTestObject('API/Admin/User Login', [('tokenaut') : res.access_token]))
//
//response = WS.sendRequest(findTestObject('API/Admin/Get Organizations', [('tokenaut') : res.access_token]))
//
//response = WS.sendRequest(findTestObject('API/Attendance/Punch In', [('tokenaut') : res.access_token]))
//
//response = WS.sendRequest(findTestObject('API/Attendance/Punch Out', [('tokenaut') : res.access_token]))
//
//response = WS.sendRequest(findTestObject('API/Employee/Save Employee', [('tokenaut') : res.access_token]))

