import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonBuilder
import internal.GlobalVariable

Booking bookingInstance = new Booking()
Map<String, Object> userData;
Map<String, Object> responseBody;
Map<String, Object> bookingInformation;
int bookingID = 155;
boolean checker;
ErrorHandling errorHandlingInstance = new ErrorHandling()


//Get Token
int responseStatusCode = bookingInstance
    .authenticateRequest('admin', 'password123')
    .checkResponseStatus()

if(responseStatusCode != 200) {
    errorHandlingInstance.handleFailure(responseStatusCode)
}

//Store Token
bookingInstance
	.storeUserToken()

//Get Booking
responseStatusCode = bookingInstance
	.getBooking(bookingID)
	.checkResponseStatus()

if(responseStatusCode != 200) {
	errorHandlingInstance.handleFailure(responseStatusCode)
}

responseBody = bookingInstance.parseAndGetResponseBodyAsMap()

println responseBody.toMapString()

bookingInstance.setBookInformationWithRealData(responseBody)

userData = bookingInstance.getBookingInformationAsMap()

userData['lastname'] = new RandomStrings().generateRandomName(10)

Map<String, Object> patchedData = ['lastname': userData['lastname']]

JsonBuilder patchedDataAsJson = new JsonBuilder(patchedData)

//Update Booking
responseStatusCode = bookingInstance
	.setBookInformationWithRealData(userData)
	.convertBookInformationToJson()
    .partiallyUpdateBooking(bookingID, patchedDataAsJson)
    .checkResponseStatus()

if(responseStatusCode != 200) {
    errorHandlingInstance.handleFailure(responseStatusCode)
}

responseBody = bookingInstance.parseAndGetResponseBodyAsMap()

bookingInformation = bookingInstance.getBookingInformationAsMap()

checker = new Maps().compareMaps(bookingInformation, responseBody)

if(checker) {
    KeywordUtil.logInfo("Booking has been updated")
}

//Get Booking
responseStatusCode = bookingInstance
	.getBooking(bookingID)
	.checkResponseStatus()

if(responseStatusCode != 200) {
	errorHandlingInstance.handleFailure(responseStatusCode)
}

responseBody = bookingInstance.parseAndGetResponseBodyAsMap()

checker = new Maps().compareMaps(bookingInformation, responseBody)

if(checker) {
	KeywordUtil.markPassed("Booking has been updated")
}
	