package com.classplusapp.web.tests.parent;

import static com.classplusapp.chat.util.Constants.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.parent.ParentConversationPage;
import com.classplusapp.web.pages.tutor.TutorConversationPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

@Epic("Start a conversation")
@Feature("Tutor to Parent Conversation")
public class ParentConversationTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver parentDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage parentChatsPage = null;
	private TutorConversationPage tutorConversationPage = null;
	private ParentConversationPage parentConversationPage = null;
	Long SystemTimeAtTutorEnd, SystemTimeAtParentEnd;

	private static final Logger logger = Logger.getLogger(ParentConversationTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in TutorConversationTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToParentConversationTest);
		this.parentDriver = super.loginClassPlusSite(browser, ORG_CODE, PARENT_MOBILE_NUBMER, EMAIL_ADDRESS,
				WEB_DRIVER.PARENT_ONE_DRIVER_TEST_IN_TutorToParentConversationTest);

		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.parentChatsPage = new ChatsPage(this.parentDriver);
		this.tutorConversationPage = new TutorConversationPage(this.tutorDriver);
		this.parentConversationPage = new ParentConversationPage(this.parentDriver);

		logger.info("Ending of initClassplusSiteLogin method in TutorConversationTest");
	}

	@Test(priority = 1, description = "Verify Tutor and Parent can able to click Chats Tab ", groups = "sanity")
	@Description("Test Description:Verify Tutor and Parent can able to click Chats Tab")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Tutor and Parent can able to click Chats Tab")
	public void testChatsTab() {
		logger.info("Starting of testChatsTabAtTutorAndParentEnd method");

		try {
			super.testChatsTab(parentChatsPage);
			super.testSearchBarLabel(parentChatsPage);
			super.testMessageLabel(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Chats Tab", e);
		}

		logger.info("Ending of testChatsTabAtTutorAndParentEnd method");
	}

	@Test(priority = 2, description = "Verify that time/date for chats is in decending order on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that time/date for chats is in decending order on LHS")
	@Story("Verify that time/date for chats is in decending order on LHS")
	public void testDayStatusInDescendingOrderOnLHS() {
		logger.info("Starting of testDayStatusInDescendingOrderOnLHS method");

		try {
			Assert.assertTrue(parentConversationPage.getDescendingOrderDayStatusOfLHSTxt()
					.equals(parentConversationPage.getDescendingOrderOfLHSDayStatusTxt()));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that time/date for chats is in decending order on LHS\" : "
					+ e.getMessage());
			logger.error("Error occured while testing that time/date for chats is in decending order on LHS", e);
		}

		logger.info("Ending of testDayStatusInDescendingOrderOnLHS method");

	}

	@Test(priority = 3, description = "Verify in chat search field by name or number but by putting space at the start by Parent")
	@Description("Test Description:Verify in chat search field by name or number but by putting space at the start by Parent")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify in chat search field by name or number but by putting space at the start  by Parent")
	public void testSearchInChatByPuttingSpaceAtStartByParent() {
		logger.info("Starting of testSearchInChatByPuttingSpaceAtStartByParent method");

		try {
			parentChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(parentChatsPage.getNoConversationTxt(),
					expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));

			parentChatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Search Chats Tab", e);
		}

		logger.info("Ending of testSearchInChatByPuttingSpaceAtStartByParent method");
	}

	@Test(priority = 4, description = "Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	@Story("Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	public void testSetProfileByParent() {
		logger.info("Starting of testProfileNameAtParentEnd method");

		try {
			tutorChatsPage.clickOnChatsTab();
			parentChatsPage.clickOnProfile();
			parentChatsPage.clickOnEditButton();
			parentChatsPage
					.setProfilePhoto(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH));

			BufferedImage expectedImage = ImageIO
					.read(new File(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH)));
			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(tutorChatsPage.getProfileImage(), expectedImage);
			Assert.assertFalse(diff.hasDiff());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that user is able to set its profile pic from account profile, which is visible to other users or not. : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that user is able to set its profile pic from account profile, which is visible to other users or not. ",
					e);
		}

		logger.info("Ending of testProfileNameAtParentEnd method");
	}

	@Test(priority = 5, description = "Verify Parent can able to send message in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Parent can able to send message in Chat Page")
	@Story("Verify Send Message By Parent")
	public void testSendMessageWithScrollingByParent() {
		logger.info("Starting of testSendMessageWithScrollingByParent method");
		try {

			super.testMoreOptions(tutorChatsPage);
			super.testStartConversation(tutorChatsPage);
			super.testFilters(tutorChatsPage);
			super.testSelectParentInFilters(tutorChatsPage);
			super.testApplyButtonInFilters(tutorChatsPage);

			// Tutor Search and select the Parent Recepient
			super.testSearchRecepientForParent(tutorChatsPage);
			super.testSelectParentRecepient(tutorChatsPage, tutorConversationPage);
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			parentChatsPage.clickOnChatsTab();
			super.testSearchRecepientForTutor(parentChatsPage);
			super.testSelectTutorRecepient(parentChatsPage);
			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			super.testSmoothScrollIng(parentChatsPage);

			super.testValidateSendMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that Parent can able to send message in Chat Page: "
					+ e.getMessage());
			logger.error("Error occured while testing Parent can able to send message in Chat Page", e);
		}

		logger.info("Ending of testSendMessageWithScrollingByParent method");
	}

	@Test(priority = 6, description = "Verify Send Message With Blank Space By Parent")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message With Blank Space By Parent")
	@Story("Verify Send Message With Blank Space By Parent")
	public void testSendMessageWithBlankSpaceByParent() {
		logger.info("Starting of testSendMessageWithBlankSpaceByParent method");

		try {
			parentChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));
			parentChatsPage.clickOnEnterKeyUsingKeyBoard();

			Assert.assertNotEquals(parentChatsPage.getSendMessageTxt(),
					testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message With Blank Space \" : " + e.getMessage());
			logger.error("Error occured while testing test Send Message With Blank Space", e);
		}

		logger.info("Ending of testSendMessageWithBlankSpaceByParent method");
	}

	@DataProvider(name = "testDataForChatBox")
	public Object[] getDataFromDataprovider() {

		return new Object[] { "Hai1234Everyone", "123456789",
				"https://webp-gcp.classplusapp.com/messages/details?convId=626f62e42cf76e001256a30f",
				"someone@gmail.com", "@$%$##&*", "Good  to  see        you     HJHG",
				"à¦•à§�à¦²à¦¾à¦¸à¦ªà§�à¦²à¦¿à¦¸à§‡ à¦¸à§�à¦¬à¦¾à¦—à¦¤à¦®" };

	}

	@Test(priority = 7, description = "Verify Parent Can Send HyperLink URL Or Not", dataProvider = "testDataForChatBox")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Parent Can Send HyperLink URL Or Not")
	@Story("Verify Parent Can Send HyperLink URL Or Not,  ")
	public void testSendMessageWithDifferentDataInChatBoxByParent(String testDataForChatBox) {
		logger.info("Starting of testSendMessageWithDifferentDataInChatBoxByParent method");

		try {
			super.testSendMessage(parentChatsPage, testDataForChatBox);

			super.testValidateSendMessage(parentChatsPage, testDataForChatBox);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing with different data sent  By Parent : " + e.getMessage());
			logger.error("Error occured while testing with different data sent By Parent", e);
		}

		logger.info("Ending of testSendMessageWithDifferentDataInChatBoxByParent method");
	}

	@Test(priority = 8, description = "Verify Send Message while clicking on enter key of keyboard")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message while clicking on enter key of keyboard")
	@Story("Verify Send Message while clicking on enter key of keyboard")
	public void testSendMessageWithEnterKey() {
		logger.info("Starting of testSendMessageWithEnterKey method");

		try {
			parentChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			parentChatsPage.clickOnEnterKeyUsingKeyBoard();

			super.testValidateSendMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message by clicking on enter key of keyboard \" : "
					+ e.getMessage());
			logger.error("Error occured while testing test Send Message by clicking on enter key of keyboard ", e);
		}

		logger.info("Ending of testSendMessageWithEnterKey method");
	}

	@Test(priority = 9, description = "Verify that user is able to send any emoticons or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to send any emoticons or not.")
	@Story("Verify that user is able to send any emoticons or not.")
	public void testSendEmoticonsByParent() {
		logger.info("Starting of testSendEmoticonsByParent method");

		try {
			parentChatsPage.setEmoticons("ðŸ˜„");

			// super.testValidateSendMessage(parentChatsPage, "ðŸ˜„");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that user is able to send any emoticons or not. \" : "
					+ e.getMessage());
			logger.error("Error occured while testing that user is able to send any emoticons or not. ", e);
		}

		logger.info("Ending of testSendEmoticonsByParent method");
	}

	@Test(priority = 10, description = "Verify Send Message with Timestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with Timestamp")
	@Story("Verify Send Message with Timestamp")
	public void testSentTimeStampWithLHSTimestampByParent() {
		logger.info("Starting of testSentTimeStampWithLHSTimestampByParent method");

		try {
			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			// Asserting status of day
			Assert.assertTrue(parentChatsPage.getChatHistoryTxt());

			super.testValidateSendMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			// asserting the lhs time w.r.to rhs
			Assert.assertEquals(parentChatsPage.getMessageTimeStampTxt(),
					parentChatsPage.getLHSTimestamp(SEARCH_FOR_TUTOR_RECEPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Validate Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Validate Send Message With Time Stamp", e);
		}

		logger.info("Ending of testSentTimeStampWithLHSTimestampByParent method");
	}

	@Test(priority = 11, description = "Verify that how many number of words or characters can be send at a time.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify that how many number of words or characters can be send at a time.")
	@Story("Verify that how many number of words or characters can be send at a time.")
	public void testNumberOfCharactersWhileSendingMessageByParent() {
		logger.info("Starting of testNumberOfCharactersWhileSendingMessageByParent method");

		try {
			// Student sent message to Tutor
			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			// Assert for text
			Assert.assertEquals(parentChatsPage.getSendMessageTxt(), testDataProp.getProperty(SEND_MESSAGE));
			// Assert for no of characters
			Assert.assertEquals(parentChatsPage.getSendMessageTxt().length(),
					parentChatsPage.getNoOfCharacters(testDataProp.getProperty(SEND_MESSAGE)));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that how many number of words or characters can be send at a time.: "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how many number of words or characters can be send at a time.",
					e);
		}

		logger.info("Ending of testNumberOfCharactersWhileSendingMessageByParent method");

	}

	@Test(priority = 12, description = "Verify that how much time is it taking to send a message from Parent to Tutor ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Parent to Tutor")
	@Story("Verify that how much time is it taking to send a message from Parent to Tutor")
	public void testConversationTimePeriodForParentToTutor() {
		logger.info("Starting of testConversationTimePeriodForParentToTutor method");

		try {
			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			SystemTimeAtTutorEnd = super.getConversationTimePeriod();

			super.testValidateSendMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			SystemTimeAtParentEnd = super.getConversationTimePeriod();

			Long TimeDifference = SystemTimeAtTutorEnd - SystemTimeAtParentEnd;
			logger.debug(TimeDifference);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from Parent to Tutor\" : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from Parent to Tutor",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForParentToTutor method");
	}

	@Test(priority = 13, description = "Verify clicking on profile name should not navigate to profile screen ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify clicking on  profile name should not navigate to profile screen ")
	@Story("Verify clicking on  profile name should not navigate to profile screen ")
	public void testProfileNameAtParentEnd() {
		logger.info("Starting of testProfileNameAtParentEnd method");

		try {
			Assert.assertFalse(tutorChatsPage.getProfileHeaderNameTxt());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing clicking on  profile name should not navigate to profile screen  : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing clicking on  profile name should not navigate to profile screen  ", e);
		}

		logger.info("Ending of testProfileNameAtParentEnd method");
	}

	@Test(priority = 14, description = "Verify for Tutor Is With Active GreenColor Displayed in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify for Tutor Is With Active Green Color Displayed in Chat Page")
	@Story("Verify for Tutor Is With Active Green Color Displayed in Chat page")
	public void testTutorWithActiveItemGreedDotByParent() {
		logger.info("Starting of testTutorWithActiveItemGreedDotByParent method");

		try {
			Assert.assertTrue(parentChatsPage.isDisplayedGreenColor(SEARCH_FOR_TUTOR_RECEPIENT));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Tutor With Active Item Greed Dot By Parent: " + e.getMessage());
			logger.error("Error occured while testing Tutor With Active Item Greed Dot By Parent", e);
		}

		logger.info("Ending of testTutorWithActiveItemGreedDotByParent method");
	}

	@Test(priority = 15, description = "Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	@Story("Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	public void testStatusOfChatWithYesterday() {
		logger.info("Starting of testStatusOfChatWithYesterday method");

		try {
			parentChatsPage.getYesterdayTxt();

			// Asserting status of day
			if (parentChatsPage.getDayTxt().equals("YESTERDAY")) {
				String yesterdayTxt = parentChatsPage.getDayTxt();
				Assert.assertEquals(yesterdayTxt, expectedAssertionsProp.getProperty(LABEL_YESTERDAY_TXT));
			} else {
				logger.debug("There are no Yesterday Conversations");
			}

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS",
					e);
		}

		logger.info("Ending of testStatusOfChatWithYesterday method");
	}

	@Test(priority = 16, description = "Verify that user is able to see the dateif conversation happened long back in the chat list on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	@Story("Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	public void testStatusOfChatWithLHSDateAtParentEnd() {
		logger.info("Starting of testStatusOfChatWithLHSDateAtParentEnd method");

		try {
			parentChatsPage.clickOnRecepientLHSDateTxt();

			// Asserting status of day
			logger.debug("Long Back Date in the chat list" + parentChatsPage.getDayTxt());

			if (parentChatsPage.getDayTxt().matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
				logger.debug("Long Back Date in the chat list" + parentChatsPage.getDayTxt());
			} else {

				logger.debug("There are no long days back chat conversations");
			}

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that user is able to see the date if conversation happened long back in the chat list on LHS : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that user is able to see the date if conversation happened long back in the chat list on LHS",
					e);
		}

		logger.info("Ending of testStatusOfChatWithLHSDateAtParentEnd method");
	}

	@Test(priority = 17, description = "Verify Add Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document")
	@Story("Verify Add Document")
	public void testAddDocumentAndFileExtensionSentByParent() {
		logger.info("Starting of testAddDocumentFileByParent method");

		try {
			super.testSearchRecepientForTutor(parentChatsPage);
			super.testSelectTutorRecepient(parentChatsPage);

			super.testAddDocumentFile(parentChatsPage);

			String expectedFileName = expectedAssertionsProp.getProperty(DOCUMENT_FILE_TXT);
			String expectedResult = expectedFileName.substring(expectedFileName.length() - 3,
					expectedFileName.length());

			String fineNameTxt = parentChatsPage.getAddDocumentTxt();
			String actualResult = fineNameTxt.substring(fineNameTxt.length() - 3, fineNameTxt.length());

			Assert.assertEquals(actualResult, expectedResult);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentAndFileExtensionSentByParent method");
	}

	@Test(priority = 18, description = "Verify Download And View Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Download And View Document")
	@Story("Verify Download And View Document")
	public void testDownloadAndViewDocumentByParent() {
		logger.info("Starting of testDownloadAndViewDocumentByParent method");

		try {
			super.testAddDocumentFile(tutorChatsPage);
			Thread.sleep(5000);
			Assert.assertEquals(parentChatsPage.getAddDocumentTxt(),
					expectedAssertionsProp.getProperty(SELECT_DOC_FILE));
			Assert.assertTrue(parentChatsPage.isDisplayedDownloadButton());

			parentChatsPage.clickOnDownloadButton();
			parentChatsPage.closeTab();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Download And View Document File : " + e.getMessage());
			logger.error("Error occured while testing Download And View Document File", e);
		}

		logger.info("Ending of testDownloadAndViewDocumentByParent method");
	}

	@Test(priority = 19, description = "Verify Parent can able to Mute Conversation for a Tutor Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Parent can able to Mute Conversation for a Tutor Conversation")
	@Story("Verify Mute and Unmute Conversation At Parent End")
	public void testMuteOrUnmuteConversationByParent() {
		logger.info("Starting of testMuteOrUnmuteConversationByParent method");

		try {
			parentChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(parentChatsPage, SEARCH_FOR_TUTOR_RECEPIENT);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Parent : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Parent", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByParent method");
	}

	@Test(priority = 20, description = "Verify Report Conversation in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in  Chat Page")
	@Story("Verify Report Conversation")
	public void testReportConversationByParent() {
		logger.info("Starting of testReportConversationByParent method");

		try {
			// super.testReportConversation(tutorChatsPage);
			super.testReportConversation(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By Parent : " + e.getMessage());
			logger.error("Error occured while testing Repor Conversation By Parent", e);
		}

		logger.info("Ending of testReportConversationByParent method");
	}

	@Test(priority = 21, description = "Verify Tutor can able to copy a message in a chat body", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to copy a message in a chat body")
	@Story("Verify Copy Message")
	public void testCopyMessageByParent() {
		logger.info("Starting of testCopyMessageByParent method");

		try {
			super.testCopyMessage(parentChatsPage);
			// super.testCopyMessage(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Copy Single Message By Parent : " + e.getMessage());
			logger.error("Error occured while testing Copy Message By Parent", e);
		}

		logger.info("Ending of testCopyMessageByParent method");
	}

	@Test(priority = 22, description = "Verify Clear Conversation in  Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Chat Page.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByParent() {
		logger.info("Starting of testClearConversationByTutorAndParent method");

		try {
			// super.testClearConversation(tutorChatsPage);
			super.testClearConversation(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By Tutor and Parent : " + e.getMessage());
			logger.error("Error occured while testing Clear Conversation  By Tutor and Parent", e);
		}

		logger.info("Ending of testClearConversationByTutorAndParent method");
	}

	@Parameters({ "browser" })
	@Test(priority = 23, description = "Verify Chat History When Application Is Closed By Parent")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Chat History When Application Is Closed By Parent")
	@Story("Verify Chat History When Application Is Closed By Parent")
	public void testChatHistoryWhenApplicationIsClosedByParent(String browser) {
		logger.info("Starting of testChatHistoryWhenApplicationIsClosedByParent method");

		try {
			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			Assert.assertTrue(parentChatsPage.getChatHistoryTxt());

			this.quitDriver(WEB_DRIVER.PARENT_ONE_DRIVER_TEST_IN_TutorToParentConversationTest);
			this.parentDriver = super.loginClassPlusSite(browser, ORG_CODE, PARENT_MOBILE_NUBMER, EMAIL_ADDRESS,
					WEB_DRIVER.PARENT_ONE_DRIVER_TEST_IN_TutorToParentConversationTest);
			this.parentChatsPage = new ChatsPage(this.parentDriver);

			super.testChatsTab(parentChatsPage);
			super.testSearchRecepientForTutor(parentChatsPage);
			super.testSelectTutorRecepient(parentChatsPage);

			logger.debug(parentChatsPage.getDayTxt());
			Assert.assertTrue(parentChatsPage.getChatHistoryTxt());

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Validate Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Validate Send Message With Time Stamp", e);
		}

		logger.info("Ending of testSentTimeStampWithLHSTimestampByParent method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && parentDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(parentDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToParentConversationTest);
				this.quitDriver(WEB_DRIVER.PARENT_ONE_DRIVER_TEST_IN_TutorToParentConversationTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}

}