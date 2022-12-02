package com.classplusapp.web.tests.faculty;

import static com.classplusapp.chat.util.Constants.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.classplusapp.chat.util.Constants;
import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.tutor.StartBroadCastPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Start a broadcast - Faculty End")
@Feature("Start a broadcast")
public class StartBraodCastTest extends CommonChatConversationTest {
	private WebDriver facultyOneDriver = null;
	private ChatsPage chatsPage = null;
	private StartBroadCastPage startBroadCastPage = null;

	private static final Logger logger = Logger.getLogger(StartBraodCastTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in FacultyToFacultyConversationTest");

		this.facultyOneDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_ONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_StartBroadCastTest);
		this.chatsPage = new ChatsPage(this.facultyOneDriver);
		this.startBroadCastPage = new StartBroadCastPage(this.facultyOneDriver);

		logger.info("Ending of initClassplusSiteLogin method in FacultyToFacultyConversationTest");
	}

	@Test(priority = 1, description = "Verify Start a broadcast in moreOptions", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Start a broadcast in moreOptions")
	@Story("Verify Start a broadcast in moreOptions")
	public void testStartBroadCastByFaculty() {
		logger.info("Starting of testStartBroadCastByFaculty method");

		try {
			chatsPage.clickOnChatsTab();
			chatsPage.clickOnImgMoreOptions();
			startBroadCastPage.clickOnStartBroadCast();

			// Assertion for SelectRecipients
			String lblSelectRecipients = chatsPage.getSelectRecipientsTxt();
			Assert.assertEquals(lblSelectRecipients, expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Start a broad cast: " + e.getMessage());
			logger.error("Error occured while testing Start a broad cast", e);
		}

		logger.info("Ending of testStartBroadCastByFaculty method");
	}

	@Test(priority = 2, description = "Verify Search with Invalid Queries")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search with Invalid Queries")
	@Story("Verify Search with Invalid Queries")
	public void testSearchWithInvalidQueryByFaculty() {
		logger.info("Starting of testSearchWithInvalidQueryByFaculty method");

		try {
			chatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			String lblErrorFetchingInConveration = chatsPage.getErrorFetchingConversationTxt();
			Assert.assertEquals(lblErrorFetchingInConveration,
					expectedAssertionsProp.getProperty(ERROR_IN_FETCHING_ONVERSATION_TXT));

			chatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_INVALID_QUERY));

			String lblItsEmptyOUtHere = chatsPage.getItsEmptyOutHereTxt();
			Assert.assertEquals(lblItsEmptyOUtHere, expectedAssertionsProp.getProperty(ITS_EMPTY_OUT_HERE_TXT));

			chatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search With Invalid Query In Start a broadcast: "
					+ e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In Start a broadcast", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryByFaculty method");
	}

	@Test(priority = 3, description = "Verify With No Filters In Start a broadcasst")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Verify With No Filters In Start a broadcasst")
	@Story("Verify With No Filters In Start a broadcasst")
	public void testWithNoFilterOptionInStartBroadCast() {
		logger.info("Starting of testWithNoFilterOptionInStartBroadCast method");

		try {
			super.testWithNoFilters(chatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing With No Filters In Start a broadcasst : " + e.getMessage());
			logger.error("Error occured while testing With No Filters In Start a broadcasst ", e);
		}

		logger.info("Ending of testWithNoFilterOptionInStartBroadCast method");
	}

	@Test(priority = 4, description = "Verify Select Recipients in Start a broadcast", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Select Recipients in Start a broadcast")
	@Story("Verify Select Recipients in Start a broadcast")
	public void testSelectRecipientsStartBroadCast() {
		logger.info("Starting of testSelectRecipientsStartBroadCast method");

		try {
			chatsPage.clickOnSearchRecipients(
					testDataProp.getProperty(Constants.SEARCH_FOR_FACULTY_RECEPIENT_IN_START_BROAD_CAST));
			startBroadCastPage.clickOnRecipients();
			startBroadCastPage.clickOnSelectLabel();

			// Assertion for SelectRecipients
			String lblSelectRecipients = chatsPage.getSelectRecipientsTxt();
			Assert.assertEquals(lblSelectRecipients, expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select Batches in filter recipients: " + e.getMessage());
			logger.error("Error occured while testing Select Batches in filter recipients", e);
		}

		logger.info("Ending of testSelectRecipientsStartBroadCast method");
	}

	@Test(priority = 5, description = "Verify selected list  green dot or not if online in Start a broadcast Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify selected list  green dot or not if online in Start a broadcast Pagee")
	@Story("Verify selected list  green dot or not if online in Start a broadcast Page")
	public void testGreenDotInStartBroadCastByFaculty() {
		logger.info("Starting of testGreenDotInStartBroadCastByFaculty method");

		try {
			Assert.assertFalse(chatsPage.IsDisplayedNoGreenColor());

		} catch (Exception e) {
			Assert.fail("Exception occured while testing selected list With Active Item Greed Dot By Faculty: "
					+ e.getMessage());
			logger.error("Error occured while testing selected list With Active Item Greed Dot By Faculty", e);
		}

		logger.info("Ending of testGreenDotInStartBroadCastByFaculty method");
	}

	// @Test(priority = 6, description = "Verify Add Document in Multiple batch
	// announcement")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document in Multiple batch announcement")
	@Story("Verify Add Document in Multiple batch announcement")
	public void testAddDocumentFile() {
		logger.info("Starting of testAddDocumentFile method");

		try {
			chatsPage.clickOnImgAttachment();
			chatsPage.clickOnDocumentLabel(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_DOC_FILE));

			// Assertion for BroadCast Message Title
			String lblBroadCastMessage = startBroadCastPage.getBroadCastMessageTxt();
			Assert.assertEquals(lblBroadCastMessage, expectedAssertionsProp.getProperty(TITLE_BROADCAST_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentFile method");
	}

	// @Test(priority = 8, description = "Verify Add Image in Multiple batch
	// announcement")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Image in Multiple batch announcement")
	@Story("Verify Add Image in Multiple batch announcement")
	public void testAddImageFile() {
		logger.info("Starting of testAddImageFile method");

		try {
			// Test with Single Select
			super.testSelectImages(chatsPage, 1);

			// Test with Same Image multile times
			for (int i = 0; i < 5; i++) {
				super.testSelectImages(chatsPage, 1);
			}

			// Test With Different Images
			super.testSelectImages(chatsPage, 20);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Image File  : " + e.getMessage());
			logger.error("Error occured while testing Add Image File ", e);
		}

		logger.info("Ending of testAddImageFile method");
	}

	@Test(priority = 6, description = "Send Broadcast Message", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Send Broadcast Message")
	@Story("Send Broadcast Message")
	public void testSendBroadCastMessage() {
		logger.info("Starting of testSendBroadCastMessage method");

		try {
			chatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(Constants.SEND_MESSAGE));
			chatsPage.clickOnSendButton();

			// Assertion for Send BroadCast Message Sent
			String lblBroadCastMessageSent = startBroadCastPage.getSendBroadCastMessageSentTxt();
			Assert.assertEquals(lblBroadCastMessageSent,
					expectedAssertionsProp.getProperty(LABEL_BROADCAST_MESSAGE_SENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send BroadCast Message: " + e.getMessage());
			logger.error("Error occured while testing Send BroadCast Message", e);
		}

		logger.info("Ending of testSendBroadCastMessage method");
	}
	
	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (facultyOneDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(facultyOneDriver);
				this.quitDriver(WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_StartBroadCastTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}

}
