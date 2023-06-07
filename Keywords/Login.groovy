import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Login {
	public Login login(String email, String password) {
		KeywordUtil.logInfo("Loggin in using email ${email}")
		
		TestObject testObject = findTestObject('Object Repository/Web/Login/txt_loginTitle')
		
		WebUI.waitForElementVisible(testObject, 10)
		
		WebUI.verifyElementVisible(testObject)
		
		WebUI.verifyElementText(testObject, 'Login to your account')
		
		WebUI.setText(findTestObject('Object Repository/Web/Login/input_userLoginEmail'), email)
		
		WebUI.setEncryptedText(findTestObject('Object Repository/Web/Login/input_userLoginPassword'), password)
		
		WebUI.click(findTestObject('Object Repository/Web/Login/btn_login'))
		
		KeywordUtil.markPassed("Logged in successfully")
		return this;
	}
}
