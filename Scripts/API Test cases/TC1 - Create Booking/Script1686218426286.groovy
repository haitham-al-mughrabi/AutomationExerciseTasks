import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable

Booking bookingInstance = new Booking()

ErrorHandling errorHandlingInstance = new ErrorHandling()

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

KeywordUtil.markPassed("Booking has been created")
	