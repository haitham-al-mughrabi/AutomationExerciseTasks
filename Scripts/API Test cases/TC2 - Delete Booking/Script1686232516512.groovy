import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable

Booking bookingInstance = new Booking()

try {
    int responseStatusCode = bookingInstance
        .setupRandomTestData()
        .convertBookInformationToJson()
        .authenticateRequest('admin', 'password123')
        .checkResponseStatus()

    if(responseStatusCode != 200) {
        handleFailure(responseStatusCode)
        return
    }

    responseStatusCode = bookingInstance
        .storeUserToken()
        .createBooking(GlobalVariable.bookInformationAsJson)
        .checkResponseStatus()

    if(responseStatusCode != 200) {
        handleFailure(responseStatusCode)
        return
    }

    Map<String, Object> responseBody = getResponseBodyAsMap(bookingInstance)

    int bookingID = responseBody.get('bookingid')

    responseStatusCode = bookingInstance
        .deleteBooking(bookingID)
        .checkResponseStatus()

    if(responseStatusCode != 201) {
        handleFailure(responseStatusCode)
        return
    }

    KeywordUtil.markPassed("Booking has been deleted")
} catch (Exception e) {
    KeywordUtil.markFailedAndStop("Exception occurred: ${e.getMessage()}")
}

void handleFailure(int responseStatusCode) {
    KeywordUtil.markFailedAndStop("Something went wrong. Status code: ${responseStatusCode}")
}

Map<String, Object> getResponseBodyAsMap(Booking bookingInstance) {
    return bookingInstance
        .parseResponseBodyAsMap()
        .getResponseBodyAsMap()
}
