import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable

Booking bookingInstance = new Booking()

ErrorHandling errorHandlingInstance = new ErrorHandling()

try {
    int responseStatusCode = bookingInstance
        .setupRandomTestData()
        .convertBookInformationToJson()
        .authenticateRequest('admin', 'password123')
        .checkResponseStatus()

    if(responseStatusCode != 200) {
       errorHandlingInstance.handleFailure(responseStatusCode)
    }

    responseStatusCode = bookingInstance
        .storeUserToken()
        .createBooking(GlobalVariable.bookInformationAsJson)
        .checkResponseStatus()

    if(responseStatusCode != 200) {
        errorHandlingInstance.handleFailure(responseStatusCode)
    }

    Map<String, Object> responseBody = bookingInstance.parseAndGetResponseBodyAsMap()

    int bookingID = responseBody.get('bookingid')

    responseStatusCode = bookingInstance
        .getBooking(bookingID)
        .checkResponseStatus()

    if(responseStatusCode != 200) {
        errorHandlingInstance.handleFailure(responseStatusCode)
    }

    responseBody = bookingInstance.parseAndGetResponseBodyAsMap()

    Map<String, Object> bookingInformation = bookingInstance.getBookingInformationAsMap()

    boolean checker = new Maps().compareMaps(bookingInformation, responseBody)

    if(checker) {
        KeywordUtil.markPassed("Booking has been deleted")
    }
} catch (Exception e) {
    KeywordUtil.markFailedAndStop("Exception occurred")
	KeywordUtil.logInfo("Error message: ${e.getMessage()}")}

