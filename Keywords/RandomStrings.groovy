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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.apache.commons.lang3.RandomStringUtils
import java.util.Random
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.format.DateTimeFormatter
import internal.GlobalVariable

public class RandomStrings {

	// Function to generate a random string with certain characters
	def generateRandomString(int length, String chars) {
		return RandomStringUtils.random(length, chars)
	}

	// Function to generate a random name
	def generateRandomName(int length) {
		// Generate a random name of length 5 (you can change this)
		return generateRandomString(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz")
	}

	// Function to generate a random password
	def generateRandomPassword(int length) {
		// Generate a random password of length 12 with specified characters
		return generateRandomString(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@&#?!_")
	}

	// Function to generate a random email
	def generateRandomEmail(int length, String domainName) {
		// Generate a random email with a length of 5 for the local-part (you can change this)
		String localPart = generateRandomString(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")
		return localPart + domainName
	}

	// Function to generate a random number within a range
	def generateRandomNumber(int min=0, int max=8539) {
		Random random = new Random()
		return random.nextInt(max - min + 1) + min
	}
	
	def generateRandomDates(String format = "yyyy-MM-dd", int howOldStartDateShouldBeInMonths=10, int howNewEndDateShouldBeInMonths=5) {
		// Define the date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
	
		// Generate a random start date in the last 10 years
		LocalDate startDate = LocalDate.now().minus((long)(Math.random() * 365* howOldStartDateShouldBeInMonths), ChronoUnit.DAYS)
	
		// Generate a random end date in the next 365 days after the start date
		LocalDate endDate = startDate.plus((long)(Math.random() * 365*howNewEndDateShouldBeInMonths), ChronoUnit.DAYS)
	
		// Return the start and end dates, formatted as 'yyyy/MM/dd'
		return [startDate.format(formatter), endDate.format(formatter)]
	}
}
