import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable

import org.json.JSONObject
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.json.JsonBuilder

public class Booking {
	private RandomStrings randomStringsInstance;
	private Map<String, Object> bookInformation;
	private List<String> randomStartAndEndDates;
	private JsonSlurper jsonSlurper;
	private ResponseObject response;
	private RequestObject requestObject;
	private Map<String, Object> responseBody; 
	
	Booking(){
		randomStringsInstance = new RandomStrings();
		jsonSlurper = new JsonSlurper();
	}
	
	Booking(Map<String, Object> bookInformation, List<String> randomStartAndEndDates){
		this.bookInformation = bookInformation;
		this.randomStartAndEndDates = randomStartAndEndDates;
		randomStringsInstance = new RandomStrings();
		jsonSlurper = new JsonSlurper();
	}
	
	/**
	 * Initializing Booking information using Random data
	 * @return Booking
	 */
	public Booking setupRandomTestData() {
		KeywordUtil.logInfo("Initializing Booking information using Random data")
		randomStartAndEndDates = randomStringsInstance.generateRandomDates("yyyy-MM-dd", 3, 1)
		bookInformation= [
			"firstname": randomStringsInstance.generateRandomName(10),
			"lastname": randomStringsInstance.generateRandomName(10),
			"totalprice": randomStringsInstance.generateRandomNumber(5),
			"depositpaid": true,
			"bookingdates": [
				"checkin": randomStartAndEndDates[0],
				"checkout": randomStartAndEndDates[1]
				],
			"additionalneeds": randomStringsInstance.generateRandomName(10)
			]
		return this;
	}
		
	/**
	 * Initializing Booking information using real data
	 * @param realData
	 * @return Booking
	 */
	public Booking setBookInformationWithRealData(Map<String, Object> realData) {
		KeywordUtil.logInfo("Initializing Booking information using real data")
		bookInformation = realData;
		return this;
	}
	
	/**
	 * Converting Book information Object from Map to JSON Then store it in Global Variables
	 * @return Booking
	 */
	public Booking convertBookInformationToJson() {
		KeywordUtil.logInfo("Converting Book information Object from Map to JSON")
		JsonBuilder bookInformationAsJson = new JsonBuilder(bookInformation)
		GlobalVariable.bookInformationAsJson = bookInformationAsJson
		return this;
	}
	
	/**
	 * Authenticating Request To Generate Token
	 * @param username
	 * @param password
	 * @return Booking
	 */
	public Booking authenticateRequest(String username, String password) {
		KeywordUtil.logInfo(" using username ${username}")
		
		requestObject = findTestObject('Object Repository/API/Create Token',['admin':username,'password':password]);
		
		response = WS.sendRequest(requestObject)
		
		return this;
	}
	
	/**
	 * Store User Token in Global Variables (Profile)
	 * @return Booking
	 */
	public Booking storeUserToken() {
		KeywordUtil.logInfo("Store User Token in Global Variables (Profile)")
		
		responseBody = jsonSlurper.parseText(response.responseText)
		
		GlobalVariable.userToken = responseBody['token']
		
		return this;
	}
	
	/**
	 * Parse response body as map
	 * @return Booking
	 */
	public Booking parseResponseBodyAsMap() {
		KeywordUtil.logInfo("Parse Response body to Map")
		
		responseBody = jsonSlurper.parseText(response.responseText)
		
		return this;
	}
	
	/**
	 * Get Response body as map
	 * @return Object
	 */
	public Object getResponseBodyAsMap() {
		KeywordUtil.logInfo("Get Response body as Map")
		
		return responseBody;
	}
	
	/**
	 * Create Booking using pre-initialized book information json object
	 * @param bookInformationAsJson
	 * @return Booking
	 */
	public Booking createBooking(def bookInformationAsJson) {
		KeywordUtil.logInfo("Creating Booking using pre-initialized book information json object");
		
		requestObject = findTestObject('Object Repository/API/Create Booking',['bookInformation':bookInformationAsJson, 'cookies':"token=${GlobalVariable.userToken}"]);
		
		response = WS.sendRequest(requestObject);
		
		return this;
	}
	
	/**
	 * Get Response Status Code
	 * @return status code 
	 */
	public int checkResponseStatus() {
		KeywordUtil.logInfo("Get Response Status Code");
		return response.statusCode;
	}
	
	/**
	 * Get Booking information as Map
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getBookingInformationAsMap(){
		KeywordUtil.logInfo("Get Booking information Object");
		return bookInformation;
	}
	
	/**
	 * Get Value From Booking Information Object By a Key
	 * @param key
	 * @return Object
	 */
	public Object getValueFromBookingInformationByKey(String key) {
		if(bookInformation.containsKey(key)) {
			return bookInformation.get(key);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Delete booking based on given Booking ID
	 * @param bookingID
	 * @return Booking
	 */
	public Booking deleteBooking(int bookingID) {
		KeywordUtil.logInfo("Deleting booking based on given Booking ID. Given Booking ID: ${bookingID}");
		
		requestObject = findTestObject('Object Repository/API/Delete Booking',['bookingID':bookingID, 'cookies':"token=${GlobalVariable.userToken}"]);
		
		response = WS.sendRequest(requestObject);
		
		return this;
	}
	
	/**
	 * Get booking based on given booking id
	 * @param bookingID
	 * @return Booking
	 */
	public Booking getBooking(int bookingID) {
		KeywordUtil.logInfo("Get booking based on given Booking ID. Given Booking ID: ${bookingID}");
		
		requestObject = findTestObject('Object Repository/API/Get Booking',['bookingID':bookingID, 'cookies':"token=${GlobalVariable.userToken}"]);
		
		response = WS.sendRequest(requestObject);
		
		return this;
	}
	
	/**
	 * Update booking based on given booking id
	 * @param bookingID
	 * @return Booking
	 */
	public Booking updateBooking(int bookingID, def bookInformationAsJson) {
		KeywordUtil.logInfo("Update booking based on given Booking ID. Given Booking ID: ${bookingID}");
		
		requestObject = findTestObject('Object Repository/API/Update Booking',['bookingID':bookingID, 'cookies':"token=${GlobalVariable.userToken}", 'updatedBody':bookInformationAsJson]);
		
		response = WS.sendRequest(requestObject);
		
		return this;
	}
	
}
