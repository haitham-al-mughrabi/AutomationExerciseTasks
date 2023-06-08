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

public class Maps {
	/**
	 * Compare values in two different maps
	 * @param map1
	 * @param map2
	 * @return boolean 
	 */
	def compareMaps(Map map1, Map map2) {
		boolean checker = true
		map1.each { key, value ->
			if (map2.containsKey(key)) {
				if (value instanceof Map && map2[key] instanceof Map) {
					compareMaps(value, map2[key])
				} else if (value != map2[key]) {
					KeywordUtil.logInfo("The key ${key} does not have the same value in both maps.")
					KeywordUtil.markFailedAndStop("Values don't match")
					checker = false
				}
			} else {
				KeywordUtil.logInfo("The key ${key} does not exist in the second map.")
				KeywordUtil.markFailedAndStop("Values don't match")
				checker = false
			}
		}
		return checker
	}
}
