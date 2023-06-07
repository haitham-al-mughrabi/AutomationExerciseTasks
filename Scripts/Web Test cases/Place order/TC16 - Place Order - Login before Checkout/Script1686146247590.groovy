import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import static org.junit.Assert.assertTrue

import org.junit.Assert
import org.openqa.selenium.Keys

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

	
Map<String, String> cardInformation = [
	'nameOnCard':'SveWVNiStgXzx3+SHZwihsToH2FK9S4i',
	'cardNumber':'gECNrpRrZE8c58gpTl5H5m8EM7csNIO/',
	'cardCVC':'f4JIPmZZZgQ=',
	'cardEpirationMonth':'IdwLPcOKu+o=',
	'cardExpirationYear':'Rseqxu0Qrtc='
	]
	
List<String> extensions = [GlobalVariable.ublockOriginPath]

List<String> products = ['Sleeveless Dress','Men Tshirt']
	
TestObject testObject;

//Setup test
new Browser()
	.openBrowserWithExtensions(extensions)

WebUI.navigateToUrl(GlobalVariable.signUpPage);

WebUI.maximizeWindow();

new SignUP()
	.createNewAccount(userData)

testObject=findTestObject('Object Repository/Web/Home/btn_logout')

WebUI.waitForElementVisible(testObject, 10)

WebUI.click(testObject)

//Test script

WebUI.navigateToUrl(GlobalVariable.homePageURL);

WebUI.waitForPageLoad(60);

String elementAttribute = WebUI.getAttribute(findTestObject('Object Repository/Web/Home/btn_navHome'), 'style');

Assert.assertTrue(elementAttribute.contains('color: orange;'));

WebUI.click(findTestObject('Object Repository/Web/Home/btn_login_SignUp'));

new Login()
	.login(GlobalVariable.UserEmail, GlobalVariable.UserEncryptedPassword)

testObject = findTestObject('Object Repository/Web/Home/txt_loggedInUser')

WebUI.waitForElementVisible(testObject, 10)

WebUI.verifyElementText(testObject, userData.get('name'))


for(String product in products) {
	new Products()
		.addProduct(product)	
}

WebUI.click(findTestObject('Object Repository/Web/Home/btn_cart'))

WebUI.waitForPageLoad(60)

String pageURL = WebUI.getUrl()

assertTrue(pageURL.contains('view_cart'))

WebUI.click(findTestObject('Object Repository/Web/Cart/btn_proceedToCheckout'))

new Cart()
	.checkDeliveryAddress(userData)
	.checkBillingAddress(userData)
	.checkProductsInCart(products)
	.addDescription()
	.placeOrder()

new Payment()
	.setPaymentInformation(cardInformation)
	.payAndConfirmOrder() 

testObject = findTestObject('Object Repository/Web/Payment/txt_orderPlacedTitle')
WebUI.waitForElementVisible(testObject, 10)
WebUI.verifyElementText(testObject, 'ORDER PLACED!')
	
new DeleteAccount()
	.deleteAccount()

//Tear down
WebUI.closeBrowser()
