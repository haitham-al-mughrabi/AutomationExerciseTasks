import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static org.junit.Assert.assertTrue

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Cart {
	//Local Variables
	private TestObject testObject;
	private String testObjectText;
	private String product;
	private WebElement productRow;
	private boolean productMatch;
	private WebElement productDescription;
	
	//Locators
	private String productRowsLocator = '//table[@id="cart_info_table"]/tbody/tr';
	private String productDescriptionLocator = "//td[@class='cart_description']//a";
	
	/**
	 * Check Delivery Address
	 * @param userInformation
	 * @return Cart
	 */
	public Cart checkDeliveryAddress(Map<String, Object> userInformation) {
		KeywordUtil.logInfo("Checking Delivery address information")
		testObject = findTestObject('Object Repository/Web/Cart/Delivery Address/txt_firstNameLastNameIndeliveryAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('firstName')))
		assertTrue(testObjectText.contains(userInformation.get('lastName')))
		
		testObject = findTestObject('Object Repository/Web/Cart/Delivery Address/txt_address1And2InDeliveryAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('address1')))
		
		testObject = findTestObject('Object Repository/Web/Cart/Delivery Address/txt_state_city_zipccodeInDeliveryAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('city')))
		assertTrue(testObjectText.contains(userInformation.get('state')))
		assertTrue(testObjectText.contains(userInformation.get('zipCode')))
				
		testObject = findTestObject('Object Repository/Web/Cart/Delivery Address/txt_countryNameInDeliveryAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('country')))
		
		testObject = findTestObject('Object Repository/Web/Cart/Delivery Address/txt_phoneNumberInDeliveryAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('mobileNumber')))
		
		KeywordUtil.markPassed('All information are correct')
		return this;
	}
	
	/**
	 * Check Billing Address
	 * @param userInformation
	 * @return Cart
	 */
	public Cart checkBillingAddress(Map<String, Object> userInformation) {
		KeywordUtil.logInfo("Checking billing address information")
		testObject = findTestObject('Object Repository/Web/Cart/Billing Address/txt_firstNameLastNameInBillingAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('firstName')))
		assertTrue(testObjectText.contains(userInformation.get('lastName')))
		
		testObject = findTestObject('Object Repository/Web/Cart/Billing Address/txt_address1And2InBillingAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('address1')))
		
		testObject = findTestObject('Object Repository/Web/Cart/Billing Address/txt_state_city_zipccodeInBillingAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('city')))
		assertTrue(testObjectText.contains(userInformation.get('state')))
		assertTrue(testObjectText.contains(userInformation.get('zipCode')))
		KeywordUtil.markPassed('Zip Code is correct')
		
		testObject = findTestObject('Object Repository/Web/Cart/Billing Address/txt_countryNameInBillingAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('country')))
		
		testObject = findTestObject('Object Repository/Web/Cart/Billing Address/txt_phoneNumberInBillingAddress')
		testObjectText = WebUI.getText(testObject)
		assertTrue(testObjectText.contains(userInformation.get('mobileNumber')))
		
		KeywordUtil.markPassed('All information are correct')
		return this;
	}
	
	/**
	 * Check Products in Cart
	 * @param products 
	 * @return Cart
	 */
	public Cart checkProductsInCart(List<String> products) {
		KeywordUtil.logInfo("Ckecing products in products table")
		WebDriver myDriver = DriverFactory.getWebDriver()
		List<WebElement> productRows = myDriver.findElements(By.xpath(productRowsLocator))
		for(productRow in productRows) {
			productMatch=false;
			productDescription = productRow.findElement(By.xpath(productDescriptionLocator))
			for(product in products) {
				testObjectText = productDescription.getText()
				if(product == testObjectText)
					productMatch = true
					KeywordUtil.markPassed("Product ${product} found")
			}
			if(productMatch==false) {
				KeywordUtil.markFailedAndStop("Product ${product} not found in cart")
			}
		}
		KeywordUtil.markPassed("All products in cart")
		return this;
	}
	
	/**
	 * Add Description to order
	 * @param description
	 * @return Cart
	 */
	public Cart addDescription(String description='') {
		KeywordUtil.logInfo('Setting up a comment on the order')
		if(description=='') {
			description = new RandomStrings().generateRandomName(30)
		}
		testObject = findTestObject('Object Repository/Web/Cart/textarea_commentOnTheOder')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.setText(testObject, description)
		KeywordUtil.markPassed("Comment set up")
		return this;
	}
	
	/**
	 * Place Order
	 * @return Cart
	 */
	public Cart placeOrder() {
		KeywordUtil.logInfo('Placing order')
		testObject = findTestObject('Object Repository/Web/Cart/btn_placeOrder')
		WebUI.waitForElementClickable(testObject, 10)
		WebUI.scrollToElement(testObject, 10)
		WebUI.click(testObject)
		KeywordUtil.markPassed("Order placed")
		return this;
	}
}
