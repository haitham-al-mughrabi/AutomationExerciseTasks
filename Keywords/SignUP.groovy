import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class SignUP {
	/**
	 * Create new account
	 * @param userData
	 * @return SignUP
	 */
	public SignUP createNewAccount(Map<String, Object> userData) {
		fillName(userData.get('name'))
		fillEmail(userData.get('email'))
		clickSignUpButton()
		fillPassword(userData.get('password'))
		fillFirstName(userData.get('firstName'))
		fillLastName(userData.get('lastName'))
		FillAddress1(userData.get('address1'))
		SelectCountry(userData.get('country'))
		FillState(userData.get('state'))
		FillCity(userData.get('city'))
		FillZipCode(userData.get('zipCode'))
		FillMobileNumber(userData.get('mobileNumber'))
		clickCreateAccount()
		GlobalVariable.UserEmail=userData.get('email')
		confirmAccountCreation()
		clickContinueCreateAccount()
		KeywordUtil.markPassed("Account has been successfully created")
		return this;
	}
	
	/**
	 * Fill Email
	 * @param email
	 * @return SignUP
	 */
	public SignUP fillEmail(String email) {
		KeywordUtil.logInfo("Filling email using given email. Given email: ${email}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupUserEmail')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, email)
		return this;
	}
	
	/**
	 * Fill Name
	 * @param name
	 * @return SignUP
	 */
	public SignUP fillName(String name) {
		KeywordUtil.logInfo("Filling name using given name. Given name: ${name}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupUserName')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, name)
		return this;
	}
	
	/**
	 * Click Sign Up Button
	 * @return SignUP
	 */
	public SignUP clickSignUpButton() {
		KeywordUtil.logInfo("Submitting email and name")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/btn_submitNameAndEmail')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.click(testObject)
		return this;
	}
	
	
	
	/**
	 * Fill password
	 * @param password
	 * @return SignUP
	 */
	public SignUP fillPassword(String password) {
		KeywordUtil.logInfo("Filling password using given password")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupUserPassword')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setEncryptedText(testObject, password)
		return this;
	}
	
	/**
	 * Fill First Name
	 * @param firstName
	 * @return SignUP
	 */
	public SignUP fillFirstName(String firstName) {
		KeywordUtil.logInfo("Filling First Name using given First Name. Given First Name: ${firstName}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupFirstName')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, firstName)
		return this;
	}
	
	/**
	 * Fill Last Name
	 * @param lastName
	 * @return SignUP
	 */
	public SignUP fillLastName(String lastName) {
		KeywordUtil.logInfo("Filling name using given Last Name. Given Last Name: ${lastName}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupLastName')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, lastName)
		return this;
	}
	
	/**
	 * Select a Country 
	 * @param countryName
	 * @return SignUP
	 */
	public SignUP SelectCountry(String countryName) {
		KeywordUtil.logInfo("Select Country using given country name. Given Country Name: ${countryName}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/select_signupUserCountry')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.selectOptionByValue(testObject, countryName, false)
		return this;
	}
	
	/**
	 * Fill a state name
	 * @param stateName
	 * @return SignUP
	 */
	public SignUP FillState(String stateName) {
		KeywordUtil.logInfo("Filling a state name using given name. Given state name: ${stateName}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupUserState')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, stateName)
		return this;
	}
	
	/**
	 * Fill City Name
	 * @param cityName
	 * @return SignUP
	 */
	public SignUP FillCity(String cityName) {
		KeywordUtil.logInfo("Filling City Name using given City Name. Given City Name: ${cityName}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupUserCity')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, cityName)
		return this;
	}
	
	/**
	 * Fill Zip Code
	 * @param zipCode
	 * @return SignUP
	 */
	public SignUP FillZipCode(String zipCode) {
		KeywordUtil.logInfo("Filling Zip Code using given Zip Code. Given Zip Code: ${zipCode}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupUserZipCode')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, zipCode)
		return this;
	}
	
	/**
	 * Fill Mobile Number
	 * @param mobileNumber
	 * @return SignUP
	 */
	public SignUP FillMobileNumber(String mobileNumber) {
		KeywordUtil.logInfo("Filling Mobile Number using given Mobile Number. Given Mobile Number: ${mobileNumber}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupUserMobilePhone')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, mobileNumber)
		return this;
	}
	
	/**
	 * Fill User Address
	 * @param userAddress
	 * @return SignUP
	 */
	public SignUP FillAddress1(String userAddress) {
		KeywordUtil.logInfo("Filling User Address using given User Address. Given User Address: ${userAddress}")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/input_signupUserAddress1')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.setText(testObject, userAddress)
		return this;
	}
	
	/**
	 * Create a new account
	 * @return SignUP
	 */
	public SignUP clickCreateAccount() {
		KeywordUtil.logInfo("Creating a new account")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/btn_createNewAccount')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.click(testObject)
		return this;
	}
	
	/**
	 * Confirm create a new account
	 * @return SignUP
	 */
	public SignUP confirmAccountCreation() {
		KeywordUtil.logInfo("Confirm creating a new account")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/txt_signupConfirmCreation')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.verifyElementText(testObject, 'ACCOUNT CREATED!')
		return this;
	}
	
	/**
	 * Click continue create a new account
	 * @return SignUP
	 */
	public SignUP clickContinueCreateAccount() {
		KeywordUtil.logInfo("Click continue create a new account")
		TestObject testObject = findTestObject('Object Repository/Web/SignUp/btn_confinueCreateAccount')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.click(testObject)
		return this;
	}
	
}