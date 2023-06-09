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
import internal.GlobalVariable

import org.junit.Assert
import org.openqa.selenium.Keys
import org.testng.Assert as Keys

RandomStrings randomStringsInstance = new RandomStrings();

//Test data
Map<String, Object> userData=[
	'name':randomStringsInstance.generateRandomName(12),
	'email':randomStringsInstance.generateRandomEmail(13, '@testcrow.com'),
	'password':GlobalVariable.UserEncryptedPassword,
	'firstName':randomStringsInstance.generateRandomName(12),
	'lastName':randomStringsInstance.generateRandomName(12),
	'address1':randomStringsInstance.generateRandomName(24),
	'country':'United States',
	'state':randomStringsInstance.generateRandomName(12),
	'city':randomStringsInstance.generateRandomName(12),
	'zipCode':randomStringsInstance.generateRandomNumber().toString(),
	'mobileNumber':'+15055555050'
	];

List<String> extensions = [GlobalVariable.ublockOriginPath]
	
TestObject testObject;

//Setup test
new Browser().openBrowserWithExtensions(extensions)

WebUI.navigateToUrl(GlobalVariable.signUpPage);

WebUI.maximizeWindow();

new SignUP().createNewAccount(userData)

testObject=findTestObject('Object Repository/Web/Home/btn_logout')

WebUI.waitForElementVisible(testObject, 10)

WebUI.click(testObject)

//Test script

WebUI.navigateToUrl(GlobalVariable.homePageURL);

WebUI.waitForPageLoad(60);

String elementAttribute = WebUI.getAttribute(findTestObject('Object Repository/Web/Home/btn_navHome'), 'style');

Assert.assertTrue(elementAttribute.contains('color: orange;'));

WebUI.click(findTestObject('Object Repository/Web/Home/btn_login_SignUp'));

new Login().login(GlobalVariable.UserEmail, GlobalVariable.UserEncryptedPassword)

testObject = findTestObject('Object Repository/Web/Home/txt_loggedInUser')

WebUI.waitForElementVisible(testObject, 10)

WebUI.verifyElementText(testObject, userData.get('name'))

new DeleteAccount().deleteAccount()

//Tear down
WebUI.closeBrowser()
