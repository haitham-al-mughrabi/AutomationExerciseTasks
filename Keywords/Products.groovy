import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

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
import org.openqa.selenium.By;

import internal.GlobalVariable

public class Products {
	/**
	 * Find and add product to cart
	 * @param productName
	 * @return Products
	 */
	public Products addProduct(String productName) {
		KeywordUtil.logInfo("Adding ${productName} to cart")
		WebDriver myDriver = DriverFactory.getWebDriver()
		WebElement productCard = myDriver.findElement(By.xpath("//div[@class='features_items']//img/following-sibling::p[text()='${productName}']"));
        new Actions(myDriver)
            .moveToElement(productCard)
            .perform();
		
		WebUI.click(findTestObject('Object Repository/Web/Products/btn_addToCartAnItem',['productName':productName]))
		TestObject testObject = findTestObject('Object Repository/Web/Products/btn_continueShopping')
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.click(testObject)
		return this;
	}
}
