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

public class Payment {
	private TestObject testObject;

	/**
	 * Set payment information
	 * @param cardInformatio
	 * @return Payment
	 */
	public Payment setPaymentInformation(Map<String, String> cardInformatio) {
		setNameOnCard(cardInformatio.get('nameOnCard'))
		setCardNumber(cardInformatio.get('cardNumber'))
		setCardCVC(cardInformatio.get('cardCVC'))
		setCardExpirationMonth(cardInformatio.get('cardEpirationMonth'))
		setCardExpirationYear(cardInformatio.get('cardExpirationYear'))
		return this;
	}
	
	/**
	 * Fill name on card
	 * @param nameOnCard
	 * @return Payment
	 */
	public Payment setNameOnCard(String nameOnCard) {
		KeywordUtil.logInfo('Filling name on card')
		testObject = findTestObject('Object Repository/Web/Payment/input_nameOnCard')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.setEncryptedText(testObject, nameOnCard)
		KeywordUtil.markPassed('Name on card has been Successfully Filled')
		return this;
	}
	
	/**
	 * Fill card number
	 * @param cardNumber
	 * @return Payment
	 */
	public Payment setCardNumber(String cardNumber) {
		KeywordUtil.logInfo('Filling card number')
		testObject = findTestObject('Object Repository/Web/Payment/input_cardNumber')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.setEncryptedText(testObject, cardNumber)
		KeywordUtil.markPassed('Card number has been Successfully Filled')
		return this;
	}
	
	/**
	 * Fill card CVC
	 * @param cardCVC
	 * @return Payment
	 */
	public Payment setCardCVC(String cardCVC) {
		KeywordUtil.logInfo('Filling card CVC')
		testObject = findTestObject('Object Repository/Web/Payment/input_cvcNumber')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.setEncryptedText(testObject, cardCVC)
		KeywordUtil.markPassed('Card CVC has been Successfully Filled')
		return this;
	}
	
	/**
	 * Fill Card Expiration Month
	 * @param cardExpirationMonth
	 * @return Payment
	 */
	public Payment setCardExpirationMonth(String cardExpirationMonth) {
		KeywordUtil.logInfo('Filling Card Expiration Month')
		testObject = findTestObject('Object Repository/Web/Payment/input_cardExpirationMonth')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.setEncryptedText(testObject, cardExpirationMonth)
		KeywordUtil.markPassed('Card Expiration Month has been Successfully Filled')
		return this;
	}
	
	/**
	 * Fill Card Expiration Year
	 * @param cardExpirationYear
	 * @return Payment
	 */
	public Payment setCardExpirationYear(String cardExpirationYear) {
		KeywordUtil.logInfo('Filling Card Expiration Year')
		testObject = findTestObject('Object Repository/Web/Payment/input_cardExpirationYear')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.setEncryptedText(testObject, cardExpirationYear)
		KeywordUtil.markPassed('Card Expiration Year has been Card Expiration Year Successfully Filled')
		return this;
	}
	
	/**
	 * Pay and confirm order
	 * @return Payment
	 */
	public Payment payAndConfirmOrder() {
		KeywordUtil.logInfo('Pay and Confirm Order')
		testObject = findTestObject('Object Repository/Web/Payment/btn_payAndConfirmPayment')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.click(testObject)
		KeywordUtil.markPassed('Order payment has been completed successfully')
		return this;
	}
}
