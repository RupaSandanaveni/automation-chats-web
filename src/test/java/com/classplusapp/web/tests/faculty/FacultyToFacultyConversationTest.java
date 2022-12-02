package com.classplusapp.web.tests.faculty;

import static com.classplusapp.chat.util.Constants.*;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Start a conversation")
@Feature("Faculty to Faculty Conversation")
public class FacultyToFacultyConversationTest extends CommonChatConversationTest {
	private WebDriver facultyOneDriver = null;
	private WebDriver facultyTwoDriver = null;
	private ChatsPage facultyOneChatsPage = null;
	private ChatsPage facultyTwoChatsPage = null;

	private static final Logger logger = Logger.getLogger(FacultyToFacultyConversationTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in FacultyToFacultyConversationTest");

		this.facultyOneDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_ONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_FacultyToFacultyConversationTest);
		this.facultyTwoDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_TWO_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_TWO_DRIVER_TEST_IN_FacultyToFacultyConversationTest);

		this.facultyTwoChatsPage = new ChatsPage(this.facultyTwoDriver);
		this.facultyOneChatsPage = new ChatsPage(this.facultyOneDriver);

		logger.info("Ending of initClassplusSiteLogin method in FacultyToFacultyConversationTest");
	}

	@Test(priority = 1, description = "Verify Faculty_One and Faculty_Two can able to click Chats Tab ", groups = "sanity")
	@Description("Test Description:Verify Faculty_One and Faculty_Two can able to click Chats Tab")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Faculty_One and Faculty_Two can able to click Chats Tab")
	public void testChatsTabAtFacultyOneAndFacultyTwoEnd() {
		logger.info("Starting of testChatsTabAtFacultyOneAndFacultyTwoEnd method");

		try {
			// Tutor End
			super.testChatsTab(facultyOneChatsPage);
			// Faculty End
			super.testChatsTab(facultyTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Chats Tab", e);
		}

		logger.info("Ending of testChatsTabAtFacultyOneAndFacultyTwoEnd method");
	}

	@Test(priority = 2, description = "Verify in chat search field by name or number but by putting space at the start")
	@Description("Test Description:Verify in chat search field by name or number but by putting space at the start")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify in chat search field by name or number but by putting space at the start")
	public void testSearchInChatByPuttingSpaceAtStart() {
		logger.info("Starting of testSearchInChatByPuttingSpaceAtStart method");

		try {
			facultyOneChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(facultyOneChatsPage.getNoConversationTxt(),
					expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));

			facultyOneChatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search Chats Tab:" + e.getMessage());
			logger.error("Error occured while testing Search Chats Tab", e);
		}

		logger.info("Ending of testSearchInChatByPuttingSpaceAtStart method");
	}

	@Test(priority = 3, description = "Verify Start a conversation in moreOptions", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Start a conversation in moreOptions")
	@Story("Verify Start a conversation in moreOptions")
	public void testStartConversationByFacultyOneToFacultyTwo() {
		logger.info("Starting of testStartConversationByFacultyOneToFacultyTwo method");

		try {
			// Faculty_One Initiated Chat With Faculty_Two
			super.testMoreOptions(facultyOneChatsPage);
			super.testStartConversation(facultyOneChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Start a conversation :" + e.getMessage());
			logger.error("Error occured while testing Start a conversation", e);
		}

		logger.info("Ending of testStartConversationByFacultyOneToFacultyTwo method");
	}

	@Test(priority = 4, description = "Verify Search with Invalid Queries")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search with Invalid Queries")
	@Story("Verify Search with Invalid Queries")
	public void testSearchWithInvalidQueryInStartConversation() {
		logger.info("Starting of testSearchWithInvalidQueryInStartConversation method");

		try {
			facultyOneChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(facultyOneChatsPage.getErrorFetchingConversationTxt(),
					expectedAssertionsProp.getProperty(ERROR_IN_FETCHING_ONVERSATION_TXT));

			facultyOneChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_INVALID_QUERY));

			Assert.assertEquals(facultyOneChatsPage.getItsEmptyOutHereTxt(),
					expectedAssertionsProp.getProperty(ITS_EMPTY_OUT_HERE_TXT));

			facultyOneChatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Search With Invalid Query In New group chat : " + e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In New group chat ", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryInStartConversation method");
	}

	@Test(priority = 5, description = "Verify Search and Select Faculty Recipient", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search and Select Faculty Recipient")
	@Story("Verify Search and Select Faculty Recipient")
	public void testSearchAndSelectRecipientByFacultyOne() {
		logger.info("Starting of testSearchAndSelectRecipientByFacultyOne method");

		try {
			super.testSearchRecepientForFacultyTwo(facultyOneChatsPage);
			super.testSelectFacultyTwoRecepient(facultyOneChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search and Select Recipient : " + e.getMessage());
			logger.error("Error occured while testing Search and Select Recipient", e);
		}

		logger.info("Ending of testSearchAndSelectRecipientByFacultyOne method");
	}

	@Test(priority = 6, description = "Verify Parent can able to send message in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Parent can able to send message in Chat Page")
	@Story("Verify Send Message By Parent")
	public void testSendMessageWithScrollingByFacultyOne() {
		logger.info("Starting of testSendMessageWithScrollingByFacultyOne method");
		try {

			super.testSendMessage(facultyOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			super.testSmoothScrollIng(facultyOneChatsPage);

		} catch (Exception e) {
			logger.error("Error occured while testing Send Message By Tutor", e);
		}

		logger.info("Ending of testSendMessageWithScrollingByFacultyOne method");
	}

	@Test(priority = 7, description = "Verify Send Message With Blank Space By Parent")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message With Blank Space By Parent")
	@Story("Verify Send Message With Blank Space By Parent")
	public void testSendMessageWithBlankSpaceByFacultyOne() {
		logger.info("Starting of testSendMessageWithBlankSpaceByFacultyOne method");

		try {
			assertTrue(facultyOneChatsPage.getWriteSomethingHereTxt());
			facultyOneChatsPage.clickOnClearQueryInWriteSomethingHere();
			facultyOneChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));
			facultyOneChatsPage.clickOnSendButton();

			super.testBlankMessage(facultyOneChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message With Blank Space \" : " + e.getMessage());
			logger.error("Error occured while testing test Send Message With Blank Space", e);
		}

		logger.info("Ending of testSendMessageWithBlankSpaceByFacultyOne method");
	}

	@Test(priority = 8, description = "Verify Send Message while clicking on enter key of keyboard")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message while clicking on enter key of keyboard")
	@Story("Verify Send Message while clicking on enter key of keyboard")
	public void testSendMessageWithEnterKey() {
		logger.info("Starting of testSendMessageWithEnterKey method");

		try {
			assertTrue(facultyOneChatsPage.getWriteSomethingHereTxt());
			facultyOneChatsPage.clickOnClearQueryInWriteSomethingHere();
			facultyOneChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			facultyOneChatsPage.clickOnEnterKeyUsingKeyBoard();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message by clicking on enter key of keyboard \" : "
					+ e.getMessage());
			logger.error("Error occured while testing test Send Message by clicking on enter key of keyboard ", e);
		}

		logger.info("Ending of testSendMessageWithEnterKey method");
	}

	@Test(priority = 8, description = "Verify Faculty_One can able to send message to Faculty_Two in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Faculty_One can able to send message to Faculty_Two in Chat Page")
	@Story("Verify Send Message By Faculty_One to Faculty_Two")
	public void testSendMessageByFacultyOneToFacultyTwo() {
		logger.info("Starting of testSendMessageByFacultyOneToFacultyTwo method");

		try {
			// Tutor sent message to Faculty
			super.testSendMessage(facultyOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing  Send Message By Faculty_One to Faculty_Two: " + e.getMessage());
			logger.error("Error occured while testing Send Message By Faculty_One to Faculty_Two", e);
		}

		logger.info("Ending of testSendMessageByFacultyOneToFacultyTwo method");
	}

	@Test(priority = 10, description = "Verify Select Recipient", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Select Recipient")
	@Story("Verify Select Recipient")
	public void testSearchAndSelectRecipientByFacultyTwo() {
		logger.info("Starting of testSearchAndSelectRecipientByFacultyTwo method");

		try {
			super.testSearchRecepientForFaculty(facultyTwoChatsPage);
			super.testSelectFacultyRecepient(facultyTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search Recipient : " + e.getMessage());
			logger.error("Error occured while testing Search Recipient", e);
		}

		logger.info("Ending of testSearchAndSelectRecipientByFacultyTwo method");
	}

	@Test(priority = 11, description = "Verify Faculty_Two can able to send message to Faculty_One in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Faculty_Two can able to send message to Faculty_One in Chat Page")
	@Story("Verify Send Message By Faculty_Two to Faculty_One")
	public void testSendMessageByFacultyTwoToFacultyOne() {
		logger.info("Starting of testSendMessageByFacultyTwoToFacultyOne method");

		try {
			// Tutor sent message to Faculty
			super.testSendMessage(facultyTwoChatsPage, testDataProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing  Send Message By Faculty_Two to Faculty_One: " + e.getMessage());
			logger.error("Error occured while testing Send Message By Faculty_Two to Faculty_One", e);
		}

		logger.info("Ending of testSendMessageByFacultyTwoToFacultyOne method");
	}

	@Test(priority = 12, description = "Verify Add Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document")
	@Story("Verify Add Document")
	public void testAddDocumentFileByFaculty_OneAndFaculty_Two() {
		logger.info("Starting of testAddDocumentFileByFaculty_OneAndFaculty_Two method");

		try {
			super.testAddDocumentFile(facultyOneChatsPage);
			super.testAddDocumentFile(facultyTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentFileByFaculty_OneAndFaculty_Two method");
	}

	@Test(priority = 13, description = "Verify Add Image")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Image")
	@Story("Verify Add Image")
	public void testAddImageFileByFaculty_OneAndFaculty_Two() {
		logger.info("Starting of testAddImageFileByFaculty_OneAndFaculty_Two method");

		try {
			super.testAddImageFile(facultyOneChatsPage);
			super.testAddImageFile(facultyTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Image File  : " + e.getMessage());
			logger.error("Error occured while testing Add Image File ", e);
		}

		logger.info("Ending of testAddImageFileByFaculty_OneAndFaculty_Two method");
	}

	@Test(priority = 14, description = "Verify Faculty_One can able to Mute and UnMute Conversation for a Faculty_Two Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Faculty_One can able to Mute and UnMute Conversation for a Faculty_Two Conversation")
	@Story("Verify Mute and Unmute Conversation At Faculty_One End")
	public void testMuteOrUnmuteConversationByFaculty_One() {
		logger.info("Starting of testMuteOrUnmuteConversationByFaculty_One method");

		try {
			facultyOneChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(facultyOneChatsPage, testDataProp.getProperty(FACULTY_TWO_RECEPIENT),testDataProp.getProperty(SEARCH_FOR_FACULTY_TWO_RECEPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute Or UnMute Conversation at Faculty_One End : "
					+ e.getMessage());
			logger.error("Error occured while testing Mute Or UnMute Conversation at Faculty_One End", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByFaculty_One method");
	}

	@Test(priority = 15, description = "Verify Faculty_Two can able to Mute Conversation for a Faculty_One Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Faculty_Two can able to Mute Conversation for a Faculty_One Conversation")
	@Story("Verify Mute and Unmute Conversation At Faculty_Two End")
	public void testMuteOrUnmuteConversationByFaculty_Two() {
		logger.info("Starting of testMuteOrUnmuteConversationByFaculty_Two method");

		try {
			facultyTwoChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(facultyTwoChatsPage, testDataProp.getProperty(FACULTY_RECEPIENT),testDataProp.getProperty(SEARCH_FOR_FACULTY_RECEPIENT));
			
		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute Or UnMute Conversation at Faculty_Two End : "
					+ e.getMessage());
			logger.error("Error occured while testing Mute Or UnMute Conversation at Faculty_Two End", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByFaculty_Two method");
	}

	@Test(priority = 16, description = "Verify Report Conversation in  Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in  Chat Page")
	@Story("Verify Report Conversation")
	public void testReportConversationByFaculty_OneAndFaculty_Two() {
		logger.info("Starting of testReportConversationByFaculty_OneAndFaculty_Two method");

		try {
			super.testReportConversation(facultyOneChatsPage);
			super.testReportConversation(facultyTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation at Faculty_One and Faculty_Two End : "
					+ e.getMessage());
			logger.error("Error occured while testing Report Conversation at Faculty_One and Faculty_Two End ", e);
		}

		logger.info("Ending of testReportConversationByFaculty_OneAndFaculty_Two method");
	}

	@Test(priority = 17, description = "Verify Clear Conversation in  Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Chat Page.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByFaculty_OneAndFaculty_Two() {
		logger.info("Starting of testClearConversationByTutorAndFaculty method");

		try {
			super.testClearConversation(facultyOneChatsPage);
			super.testClearConversation(facultyTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation at Faculty_One and Faculty_Two End : "
					+ e.getMessage());
			logger.error("Error occured while testing Clear Conversation at Faculty_One and Faculty_Two End ", e);
		}

		logger.info("Ending of testClearConversationByTutorAndFaculty method");
	}

	@Test(priority = 18, description = "Verify Faculty_One and Faculty_Two can able to copy a message in a chat body", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Faculty_One and Faculty_Two can able to copy a message in a chat body")
	@Story("Verify Copy Message")
	public void testCopyMessageByFaculty_OneAndFaculty_Two() {
		logger.info("Starting of testCopyMessageByTutor method");

		try {
			super.testCopyMessage(facultyOneChatsPage);
			super.testCopyMessage(facultyTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Copy Message By Faculty_One and Faculty_Two End : "
					+ e.getMessage());
			logger.error("Error occured while testing Copy Message By Faculty_One and Faculty_Two End", e);
		}

		logger.info("Ending of testCopyMessageByTutor method");
	}

	@Test(priority = 19, description = "Verify Faculty_One And Faculty_Two can able to delete single message in a chat body", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Faculty_One And Faculty_Two can able to delete single message in a chat body")
	@Story("Verify Delete Single Message")
	public void testDeleteSingleMessageByFaculty_OneAndFaculty_Two() {
		logger.info("Starting of testDeleteSingleMessageByFaculty_OneAndFaculty_Two method");

		try {
			super.testDeleteSingleMessage(facultyOneChatsPage);
			super.testDeleteSingleMessage(facultyTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Delete Single Message By Faculty_One And Faculty_Two : "
					+ e.getMessage());
			logger.error("Error occured while testing Delete Single Message By Faculty_One And Faculty_Two", e);
		}

		logger.info("Ending of testDeleteSingleMessageByFaculty_OneAndFaculty_Two method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (facultyOneDriver != null && facultyTwoDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(facultyOneDriver);
				this.initClassplusSiteLogout(facultyTwoDriver);
				this.quitDriver(WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_FacultyToFacultyConversationTest);
				this.quitDriver(WEB_DRIVER.FACULTY_TWO_DRIVER_TEST_IN_FacultyToFacultyConversationTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}

}
