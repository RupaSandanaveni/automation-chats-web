package com.classplusapp.web.tests.tutor;

import static com.classplusapp.chat.util.Constants.*;
import static org.testng.Assert.assertTrue;

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
import com.classplusapp.web.pages.faculty.FacultyConversationPage;
import com.classplusapp.web.pages.student.StudentConversationPage;
import com.classplusapp.web.pages.tutor.TutorConversationPage;
import com.classplusapp.web.tests.CommonChatConversationTest;
import com.classplusapp.web.tests.BaseClassplusAutomationTest.WEB_DRIVER;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

@Epic("Start a conversation")
@Feature("Tutor to Student Conversation")
public class TutorConversationTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver studentDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage studChatsPage = null;
	private TutorConversationPage tutorConversationPage = null;
	private StudentConversationPage studentConversationPage = null;

	Long SystemTimeAtTutorEnd, SystemTimeAtStudentEnd;

	private static final Logger logger = Logger.getLogger(TutorConversationTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in TutorConversationTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToStudentConversationTest);
		this.studentDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_TutorToStudentConversationTest);

		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.studChatsPage = new ChatsPage(this.studentDriver);
		this.tutorConversationPage = new TutorConversationPage(this.tutorDriver);
		this.studentConversationPage = new StudentConversationPage(this.studentDriver);

		logger.info("Ending of initClassplusSiteLogin method in TutorConversationTest");
	}

	@Test(priority = 1, description = "Verify Tutor and Student can able to click Chats Tab ", groups = "sanity")
	@Description("Test Description:Verify Tutor and Student can able to click Chats Tab")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Tutor and Student can able to click Chats Tab")
	public void testChatsTab() {
		logger.info("Starting of testChatsTab method");

		try {
			// Tutor End
			super.testChatsTab(tutorChatsPage);
			// Student End
			super.testChatsTab(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Chats Tab", e);
		}

		logger.info("Ending of testChatsTab method");
	}
	
	@Test(priority = 2, description = "Verify in chat search field by name or number but by putting space at the start")
	@Description("Test Description:Verify in chat search field by name or number but by putting space at the start")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify in chat search field by name or number but by putting space at the start")
	public void testSearchInChatByPuttingSpaceAtStart() {
		logger.info("Starting of testSearchInChatByPuttingSpaceAtStart method");

		try {
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(tutorChatsPage.getNoConversationTxt(),
					expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));

			tutorChatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search Chats Tab:" + e.getMessage());
			logger.error("Error occured while testing Search Chats Tab", e);
		}

		logger.info("Ending of testSearchInChatByPuttingSpaceAtStart method");
	}

	@Test(priority = 3, description = "Verify that time/date for chats is in decending order on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that time/date for chats is in decending order on LHS")
	@Story("Verify that time/date for chats is in decending order on LHS")
	public void testDayStatusInDescendingOrderOnLHSInTutorEnd() {
		logger.info("Starting of testDayStatusInDescendingOrderOnLHSInTutorEnd method");

		try {
			System.out.println(tutorChatsPage.getDescendingOrderDayStatusOfLHSTxt());
			System.out.println(tutorChatsPage.getDescendingOrder());
			Assert.assertTrue(
					tutorChatsPage.getDescendingOrderDayStatusOfLHSTxt().equals(tutorChatsPage.getDescendingOrder()));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that time/date for chats is in decending order on LHS\" : "
					+ e.getMessage());
			logger.error("Error occured while testing that time/date for chats is in decending order on LHS", e);
		}

		logger.info("Ending of testDayStatusInDescendingOrderOnLHSInTutorEnd method");
	}

	@Test(priority = 4, description = "Verify Start a conversation in moreOptions", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Start a conversation in moreOptions")
	@Story("Verify Start a conversation in moreOptions")
	public void testStartConversationByTutor() {
		logger.info("Starting of testStartConversationByTutor method");

		try {
			// Tutor initiated Conversation
			super.testMoreOptionsImage(tutorChatsPage);
			super.testSearchBarLabel(tutorChatsPage);
			super.testMessageLabel(tutorChatsPage);
			super.testMoreOptions(tutorChatsPage);
			super.testStartConversation(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Start a conversation in moreOptions\": " + e.getMessage());
			logger.error("Error occured while testing Start a conversation in moreOptions\"", e);
		}

		logger.info("Ending of testStartConversationByTutor method");
	}

	@Test(priority = 5, description = "Verify Filters for Students", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Filters for Students")
	@Story("Verify Filters for Students")
	public void testFiltersForStudents() {
		logger.info("Starting of testFiltersForStudents method");

		try {
			// Filter and select the students list
			super.testFilters(tutorChatsPage);
			super.testSelectStudentInFilters(tutorChatsPage);
			super.testApplyButtonInFilters(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Filters: " + e.getMessage());
			logger.error("Error occured while testing Filters", e);
		}

		logger.info("Ending of testFiltersForStudents method");
	}

	@Test(priority = 6, description = "Verify Search With Invalid Query")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search With Invalid Query")
	@Story("Verify Search With Invalid Query")
	public void testSearchWithInvalidQueryByTutor() {
		logger.info("Starting of testSearchWithInvalidQueryByTutor method");

		try {
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(tutorChatsPage.getErrorFetchingConversationTxt(),
					expectedAssertionsProp.getProperty(ERROR_IN_FETCHING_ONVERSATION_TXT));

			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_INVALID_QUERY));

			Assert.assertEquals(tutorChatsPage.getItsEmptyOutHereTxt(),
					expectedAssertionsProp.getProperty(ITS_EMPTY_OUT_HERE_TXT));

			tutorChatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search With Invalid Query In Tutor To Faculty Conversation : "
					+ e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In Tutor To Faculty Conversation ", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryByTutor method");
	}

	@Test(priority = 7, description = "Verify Tutor can able to send message in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to send message in Chat Page")
	@Story("Verify Send Message By Tutor")
	public void testSendMessageByTutor() {
		logger.info("Starting of testSendMessageByTutor method");

		try {
			super.testSearchRecepientForStudent(tutorChatsPage);
			super.testSelectStudentRecepient(tutorChatsPage, tutorConversationPage);
			super.testSendMessage(tutorChatsPage, tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message By Tutor: " + e.getMessage());
			logger.error("Error occured while testing Send Message By Tutor", e);
		}

		logger.info("Ending of testSendMessageByTutor method");
	}
	

	@Test(priority = 8, description = "Verify clicking on profile name should not navigate to profile screen ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify clicking on  profile name should not navigate to profile screen ")
	@Story("Verify clicking on  profile name should not navigate to profile screen ")
	public void testProfileNameAtTutorEnd() {
		logger.info("Starting of testProfileNameAtTutorEnd method");

		try {
			tutorChatsPage.clickOnProfileHeaderNameTxt();

			// Assertion for header name in profile info page
			Assert.assertEquals(tutorChatsPage.getHeaderNameInInfoTxt(),
					expectedAssertionsProp.getProperty(LABEL_STUDENT1_NAME_TXT));

			tutorConversationPage.clickOnProfileChatOption();

			// Assert for header name in chat page
			Assert.assertEquals(tutorChatsPage.getHeaderNameTxt(),
					expectedAssertionsProp.getProperty(LABEL_STUDENT1_NAME_TXT));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing clicking on  profile name should not navigate to profile screen  : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing clicking on  profile name should not navigate to profile screen  ", e);
		}

		logger.info("Ending of testProfileNameAtTutorEnd method");
	}


	@Test(priority = 9, description = "Verify that user is able to send any emoticons or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to send any emoticons or not.")
	@Story("Verify that user is able to send any emoticons or not.")
	public void testSendEmoticonsByTutor() {
		logger.info("Starting of testSendEmoticonsByTutor method");

		try {
			tutorChatsPage.setEmoticons("😄");

			// super.testValidateSendMessage(tutorChatsPage, "😄");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that user is able to send any emoticons or not. \" : "
					+ e.getMessage());
			logger.error("Error occured while testing that user is able to send any emoticons or not. ", e);
		}

		logger.info("Ending of testSendEmoticonsByTutor method");
	}
	
	@DataProvider(name = "testDataForChatBox")
	public Object[] getDataFromDataprovider() {

		return new Object[] { "Hai1234Everyone", "123456789",
				"https://webp-gcp.classplusapp.com/messages/details?convId=626f62e42cf76e001256a30f",
				"someone@gmail.com", "@$%$##&*", "Good  to  see     you" };

	}

	@Test(priority = 10, description = "Verify Send Message With Different Data In Chat Box By Student", dataProvider = "testDataForChatBox")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Send Message With Different Data In Chat Box By Student")
	@Story("Verify Send Message With Different Data In Chat Box By Student")
	public void testSendMessageWithDifferentDataInChatBoxByTutor(String testDataForChatBox) {
		logger.info("Starting of testSendHyperLinkUrlAndEmailsTextInChatBoxByTutor method");

		try {
			super.testSendMessage(tutorChatsPage, testDataForChatBox);

			Assert.assertEquals(tutorChatsPage.getSendMessageTxt(), testDataForChatBox);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing with different data sent  By Tutor : " + e.getMessage());
			logger.error("Error occured while testing with different data sent By Tutor", e);
		}

		logger.info("Ending of testSendHyperLinkUrlAndEmailsTextInChatBoxByTutor method");
	}

	@Test(priority = 11, description = "Verify Selecting with Single Image")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Selecting with Single Image")
	@Story("Verify Selecting with Single Image")
	public void testSelectSingleImage() {
		logger.info("Starting of testSelectSingleImage method");

		try {
			// Test with Select Single Image
			super.testSelectImages(tutorChatsPage, 1);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select Single Image: " + e.getMessage());
			logger.error("Error occured while testing Select Single Image", e);
		}

		logger.info("Ending of testSelectSingleImage method");
	}

	@Test(priority = 12, description = "Verify Selecting with Same Image Multiple Times")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Selecting with Same Image Multiple Times")
	@Story("Verify Selecting with Same Image Multiple Times")
	public void testSelectWithSameImageMultipleTimes() {
		logger.info("Starting of testSelectWithSameImageMultipleTimes method");

		try {
			// Test Select with Same Image multile times
			for (int i = 0; i < 5; i++) {
				super.testSelectImages(tutorChatsPage, 1);
			}

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Same Image Multiple Times : " + e.getMessage());
			logger.error("Error occured while testing Select With Same Image Multiple Times", e);
		}

		logger.info("Ending of testSelectWithSameImageMultipleTimes method");
	}

	@Test(priority = 13, description = "Verify Select With Twenty Different Images")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Select With Twenty Different Images")
	@Story("Verify Select With Twenty Different Images")
	public void testSelectWithTwentyDifferentImages() {
		logger.info("Starting of testSelectWithTwentyDifferentImages method");

		try { // Test Select With Different Images
			super.testSelectImages(tutorChatsPage, 20);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Twenty Different Images : " + e.getMessage());
			logger.error("Error occured while testing Select With Twenty Different Images", e);
		}

		logger.info("Ending of testSelectWithTwentyDifferentImages method");
	}

	@Test(priority = 14, description = "Verify for Faculty Is With Active Green Color Displayed in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify for Faculty Is With Active Green Color Displayed in Chat Page")
	@Story("Verify for Faculty Is With Active Green Color Displayed in Chat page")
	public void testStudentWithActiveItemGreedDotByTutor() {
		logger.info("Starting of testStudentWithActiveItemGreedDotByTutor method");

		try {
			Thread.sleep(3000);
			Assert.assertTrue(tutorChatsPage.isDisplayedGreenColor(testDataProp.getProperty(STUDENT_RECEPIENT)));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing for Student Is With Active Green Color Displayed: "
					+ e.getMessage());
			logger.error("Error occured while testing for Student Is With Active Green Color Displayed", e);
		}

		logger.info("Ending of testStudentWithActiveItemGreedDotByTutor method");
	}

	@Test(priority = 15, description = "Verify Download And View Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Download And View Document")
	@Story("Verify Download And View Document")
	public void testDownloadAndViewDocumentByTutor() {
		logger.info("Starting of testDownloadAndViewDocumentByTutor method");

		try {
			super.testAddDocumentFile(tutorChatsPage);
			// tutor end testcase
			Assert.assertTrue(tutorChatsPage.isDisplayedDownloadButton());

			tutorChatsPage.clickOnDownloadButton();
			tutorChatsPage.closeTab();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Download And View Document File : " + e.getMessage());
			logger.error("Error occured while testing Download And View Document File", e);
		}

		logger.info("Ending of testDownloadAndViewDocumentByTutor method");
	}

	@Test(priority = 16, description = "Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	@Story("Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	public void testSetProfileByTutor() {
		logger.info("Starting of testSetProfileByTutor method");

		try {
			tutorChatsPage.clickOnProfile();
			tutorChatsPage.clickOnEditButton();
			tutorChatsPage
					.setProfilePhoto(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH));

			BufferedImage expectedImage = ImageIO
					.read(new File(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH)));

			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(studChatsPage.getProfileImage(), expectedImage);
			Assert.assertFalse(diff.hasDiff());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that user is able to set its profile pic from account profile, which is visible to other users or not. : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that user is able to set its profile pic from account profile, which is visible to other users or not. ",
					e);
		}

		logger.info("Ending of testSetProfileByTutor method");
	}

	//@Test(priority = 17, description = "Verify Student can able to send message in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Student can able to send message in Chat Page")
	@Story("Verify Send Message By Student")
	public void testSendMessageUsingKeyEnterByStudent() {
		logger.info("Starting of testSendMessageUsingKeyEnterByStudent method");

		try {
			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);
			super.testIsDisplayedTurnOffRepliesInfo(tutorChatsPage, studChatsPage);
			studChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			studChatsPage.clickOnEnterKeyUsingKeyBoard();

			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			super.testSmoothScrollIng(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message with Enter key by Student : " + e.getMessage());
			logger.error("Error occured while testing Send Message with Enter key by Student", e);
		}

		logger.info("Ending of testSendMessageUsingKeyEnterByStudent method");
	}

	@Test(priority = 18, description = "Verify Add Document and File Extension")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document and File Extension")
	@Story("Verify Add Document and File Extension")
	public void testAddDocumentAndFileExtensionSentByTutor() {

		logger.info("Starting of testAddDocumentAndFileExtensionSentByTutor method");

		try {
			tutorChatsPage.clickOnChatsTab();
			// super.testSearchBar(tutorChatsPage);
			super.testSearchRecepientForStudent(tutorChatsPage);
			super.testSelectStudentRecepient(tutorChatsPage, tutorConversationPage);
			super.testAddDocumentFile(tutorChatsPage);

			String expectedFileName = expectedAssertionsProp.getProperty(DOCUMENT_FILE_TXT);
			String expectedResult = expectedFileName.substring(expectedFileName.length() - 3,
					expectedFileName.length());
			String fileNameTxt = tutorChatsPage.getAddDocumentTxt();
			String actualResult = fileNameTxt.substring(fileNameTxt.length() - 3, fileNameTxt.length());
			Assert.assertEquals(actualResult, expectedResult);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document and File Extension  :" + e.getMessage());
			logger.error("Error occured while testing Add Document ", e);
		}

		logger.info("Ending of testAddDocumentAndFileExtensionSentByTutor method");
	}

	@Test(priority = 19, description = "Verify that how many number of words or characters can be send at a time by Tutor.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify that how many number of words or characters can be send at a time by Tutor.")
	@Story("Verify that how many number of words or characters can be send at a time by Tutor.")
	public void testNumberOfCharactersWhileSendingMessageByTutor() {
		logger.info("Starting of testNumberOfCharactersWhileSendingMessageByTutor method");

		try {
			// Tutor sent message to Faculty
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			// Assert for text
			Assert.assertEquals(tutorChatsPage.getSendMessageTxt(), testDataProp.getProperty(SEND_MESSAGE));
			// Assert for no of characters
			Assert.assertEquals(tutorChatsPage.getSendMessageTxt().length(),
					tutorChatsPage.getNoOfCharacters(testDataProp.getProperty(SEND_MESSAGE)));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that how many number of words or characters can be send at a time by Tutor.: "
							+ e.getMessage());
			logger.error(
					"Error occured while testingVerify that how many number of words or characters can be send at a time by Tutor.",
					e);
		}

		logger.info("Ending of testNumberOfCharactersWhileSendingMessageByTutor method");
	}

	@Test(priority = 20, description = "Verify Send Message with Timestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with Timestamp")
	@Story("Verify Send Message with Timestamp")
	public void testSentTimeStampWithLHSTimestampByTutor() {
		logger.info("Starting of testSentTimeStampWithLHSTimestampByTutor method");

		try {
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			// asserting the lhs time w.r.to rhs
			logger.debug(tutorChatsPage.getMessageTimeStampTxt());
			logger.debug(tutorChatsPage.getLHSTimestamp(testDataProp.getProperty(STUDENT_RECEPIENT)));
			Assert.assertEquals(tutorChatsPage.getMessageTimeStampTxt(),
					tutorChatsPage.getLHSTimestamp(testDataProp.getProperty(STUDENT_RECEPIENT)));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Validate Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Validate Send Message With Time Stamp", e);
		}

		logger.info("Ending of testSentTimeStampWithLHSTimestampByTutor method");
	}

	@Test(priority = 21, description = "Verify that how much time is it taking to send a message from Tutor to Student ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Tutor to Student")
	@Story("Verify that how much time is it taking to send a message from Tutor to Student")
	public void testConversationTimePeriodForTutorToStudent() {
		logger.info("Starting of testConversationTimePeriodForTutorToStudent method");

		try {
			super.testSendMessage(tutorChatsPage, tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			SystemTimeAtTutorEnd = super.getConversationTimePeriod();

			super.testValidateSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			SystemTimeAtStudentEnd = super.getConversationTimePeriod();
			Long TimeDifference = SystemTimeAtTutorEnd - SystemTimeAtStudentEnd;
			logger.debug(TimeDifference);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from Tutor to Student\" : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from Tutor to Student",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForTutorToStudent method");
	}

	@Test(priority = 22, description = "Verify how long a chat can be saved at parent end")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify how long a chat can be saved at parent end")
	@Story("Verify how long a chat can be saved at parent end")
	public void testHowLongChatSavedAtTutorEnd() {
		logger.info("Starting of testHowLongChatSavedAtTutorEnd method");

		try {
			super.testHowlongChatSaved(tutorChatsPage);
			logger.debug("Last chat was on :" + testHowlongChatSaved(tutorChatsPage) + "Done");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing how long a chat saved\" : " + e.getMessage());
			logger.error("Error occured while testing how long a chat saved", e);
		}

		logger.info("Ending of testHowLongChatSavedAtTutorEnd method");

	}

	@Test(priority = 23, description = "Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	@Story("Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	public void testStatusOfChatWithYesterdayAtTutorEnd() {
		logger.info("Starting of testStatusOfChatWithYesterdayAtTutorEnd method");

		try {
			tutorChatsPage.getYesterdayTxt();
			// Asserting status of day
			if (tutorChatsPage.getDayTxt().equals("YESTERDAY")) {
				String yesterdayTxt = tutorChatsPage.getDayTxt();
				Assert.assertEquals(yesterdayTxt, expectedAssertionsProp.getProperty(LABEL_YESTERDAY_TXT));
			} else {
				logger.debug("There are no Yesterday Conversations");
			}
			// Tutor Search and select the Student Recepient
			super.testSearchRecepientForStudent(tutorChatsPage);
			super.testSelectStudentRecepient(tutorChatsPage, tutorConversationPage);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS",
					e);
		}

		logger.info("Ending of testStatusOfChatWithYesterdayAtTutorEnd method");
	}

	@Test(priority = 24, description = "Verify Tutor can able to Mute and UnMute Conversation for a Student Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Mute and UnMute Conversation for a Student Conversation")
	@Story("Verify Mute and Unmute Conversation At Tutor End")
	public void testMuteOrUnmuteConversationByTutor() {
		logger.info("Starting of testMuteOrUnmuteConversationByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();

			super.testMuteUnmuteConversation(tutorChatsPage,
					testDataProp.getProperty(STUDENT_RECEPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Tutor", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByTutor method");
	}

	@Test(priority = 25, description = "Verify Tutor can able to TurnOffOnReplies for a Student Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to TurnOffOnReplies for a Student Conversation")
	@Story("Verify TurnOffOnReplies")
	public void testTurnOffOnRepliesByTutor() {
		logger.info("Starting of testTurnOffOnRepliesByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();
			try {
				if (tutorChatsPage.isDisplayedImgTurnOffRepliesTxt()) {
					tutorChatsPage.clickOnTurnOffReplies();

					String turnOffReplies = tutorChatsPage.getTurnedOffRepliesTxt();
					Assert.assertEquals(turnOffReplies,
							expectedAssertionsProp.getProperty("toast.message.replies.turnedOff"));

					String lblInfoTutorEnd = tutorChatsPage.getTurnedOffRepliesInfoTxt();
					Assert.assertEquals(lblInfoTutorEnd, expectedAssertionsProp.getProperty(TURN_OFF_REPLIES_INFO_TXT));

					String lblInfoStudentEnd = tutorChatsPage.getTurnedOffRepliesInfoTxt();
					Assert.assertEquals(lblInfoStudentEnd,
							expectedAssertionsProp.getProperty(TURN_OFF_REPLIES_INFO_TXT));
				}
			} catch (Exception e) {
				tutorChatsPage.clickOnTurnOnReplies();

				String turnOnReplies = tutorChatsPage.getTurnedOnRepliesTxt();
				Assert.assertEquals(turnOnReplies,
						expectedAssertionsProp.getProperty("toast.message.replies.turnedOn"));

				super.testSendMessage(tutorChatsPage, tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			}

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Tutor", e);
		}

		logger.info("Ending of testTurnOffOnRepliesByTutor method");
	}

	@Test(priority = 26, description = "Verify Report Conversation in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in  Chat Page")
	@Story("Verify Report Conversation")
	public void testReportConversationByTutor() {
		logger.info("Starting of testReportConversationByTutor method");

		try {
			super.testReportConversation(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Repor Conversation By Tutor", e);
		}

		logger.info("Ending of testReportConversationByTutor method");
	}

	@Test(priority = 27, description = "Verify Clear Conversation in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Chat Page.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByTutor() {
		logger.info("Starting of testClearConversationByTutor method");

		try {
			super.testClearConversation(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Clear Conversation  By Tutor", e);
		}

		logger.info("Ending of testClearConversationByTutor method");
	}

	@Test(priority = 28, description = "Verify Tutor can able to copy a message in a chat body", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to copy a message in a chat body")
	@Story("Verify Copy Message")
	public void testCopyMessageByTutor() {
		logger.info("Starting of testCopyMessageByTutor method");

		try {
			super.testCopyMessage(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Copy Single Message By TutoR : " + e.getMessage());
			logger.error("Error occured while testing Copy Message By Tutor", e);
		}

		logger.info("Ending of testCopyMessageByTutor method");
	}

	@Test(priority = 29, description = "Verify Tutor can able to delete single message in a chat body", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to delete single message in a chat body")
	@Story("Verify Delete Single Message")
	public void testDeleteSingleMessageByTutor() {
		logger.info("Starting of testDeleteSingleMessageByTutor method");

		try {
			super.testDeleteSingleMessage(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Delete Single Message By Tutor and Student : " + e.getMessage());
			logger.error("Error occured while testing Delete Single Message  By Tutor and Student", e);
		}

		logger.info("Ending of testDeleteSingleMessageByTutor method");
	}

	@Test(priority = 30, description = "Verify Tutor can able to navigate from Batch Chat Profile", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Tutor can able to navigate from Batch Chat Profile ")
	@Story("Verify Tutor can able to navigate from Batch Chat Profile")
	public void testNavigateBatchProfileChatByTutor() {
		logger.info("Starting of testNavigateBatchProfileChat method");

		try {
			tutorChatsPage.clickOnBatchsTab();

			assertTrue(tutorConversationPage.getIconSearch());

			tutorChatsPage.setSearchBar(testDataProp.getProperty(SEARCH_FOR_BATCH));

			Assert.assertEquals(tutorConversationPage.getSearchBatchNameLabel(),
					expectedAssertionsProp.getProperty(SEARCH_BATCH_NAME));

			tutorChatsPage.clickOnBatch();

			Assert.assertEquals(tutorConversationPage.getStudentslabel(),
					expectedAssertionsProp.getProperty(STUDENTS_LABEL));

			tutorConversationPage.clickOnStudentsTab();

			Assert.assertEquals(tutorConversationPage.getStudentsLabelInStudentsTab(),
					expectedAssertionsProp.getProperty(STUDENTS_LABEL_IN_STUDENT_TAB));

			tutorConversationPage.setSearchBarInStudentsTab(testDataProp.getProperty(STUDENT_RECEPIENT));

			Assert.assertEquals(tutorConversationPage.getSearchAddStudentsLabel(),
					expectedAssertionsProp.getProperty(STUDENT_RECEPIENT));

			tutorConversationPage.clickOnStudent();

			Assert.assertEquals(tutorConversationPage.getChatLabel(), expectedAssertionsProp.getProperty(CHAT_LABEL));

			tutorConversationPage.clickOnProfileChatOption();

			super.testSendMessage(tutorChatsPage, tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			Assert.assertEquals(tutorConversationPage.getChatsLabel(), expectedAssertionsProp.getProperty(CHATS_LABEL));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing NavigateBatchProfileChat  : " + e.getMessage());
			logger.error("Error occured while NavigateBatchProfileChat", e);
		}

		logger.info("Ending of testNavigateBatchProfileChat method");
	}

	@Test(priority = 31, description = "Verify that user is able to see the dateif conversation happened long back in the chat list on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	@Story("Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	public void testStatusOfChatWithLHSDateAtTutorEnd() {
		logger.info("Starting of testStatusOfChatWithLHSDateAtTutorEnd method");

		try {
			tutorChatsPage.clickOnRecepientLHSDateTxt();

			// Asserting status of day
			logger.debug("Long Back Date in the chat list" + tutorChatsPage.getDayTxt());
			if (tutorChatsPage.getDayTxt().matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
				logger.debug("Long Back Date in the chat list" + tutorChatsPage.getDayTxt());
			} else {

				logger.debug("There are no long days back chat conversations");
			}
			super.testSearchRecepientForParent(tutorChatsPage);
			super.testSelectParentRecepient(tutorChatsPage, tutorConversationPage);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that user is able to see the date if conversation happened long back in the chat list on LHS : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that user is able to see the date if conversation happened long back in the chat list on LHS",
					e);
		}

		logger.info("Ending of testStatusOfChatWithLHSDateAtTutorEnd method");
	}

	@Parameters({ "browser" })
	@Test(priority = 32, description = "Verify Student are waiting for you in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Student are waiting for you in Chat Page")
	@Story("Verify Student are waiting for you in Chat Page")
	public void testStudentsAreWaitingForYouAtTutorEnd(String browser) {
		logger.info("Starting of testStudentsAreWaitingForYouAtTutorEnd method");
		try {
			this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToStudentConversationTest);
			studChatsPage.clickOnChatsTab();
			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);
			super.testSendMessage(tutorChatsPage, studChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
					WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToStudentConversationTest);
			this.tutorChatsPage = new ChatsPage(this.tutorDriver);
			this.tutorConversationPage = new TutorConversationPage(this.tutorDriver);

			super.testChatsTab(tutorChatsPage);

			// Assertion for Students are waiting for you header
			Assert.assertEquals(tutorConversationPage.getUnseenHeaderTxt(),
					expectedAssertionsProp.getProperty(LABEL_STUDENTS_ARE_WAITING_FOR_YOU));

			/*
			 * Assert.assertEquals(tutorConversationPage.getWaitingStudentStartingLetterTxt(
			 * ), expectedAssertionsProp.getProperty(LABEL_STARTING_LETTER_OF_A_NAME));
			 */

			tutorConversationPage.clickOnWaitingStudent();

			Assert.assertEquals(tutorChatsPage.getStudentHeaderNameTxt(),
					expectedAssertionsProp.getProperty(LABEL_STUDENT1_NAME_TXT));

			super.testSendMessage(tutorChatsPage, tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			logger.error("Error occured while testing Student are waiting for you in Chat Page ", e);
		}

		logger.info("Ending of testStudentsAreWaitingForYouAtTutorEnd method");
	}

	@Parameters({ "browser" })
	@Test(priority = 33, description = "Verify Chat History When Application Is Closed By Faculty")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Chat History When Application Is Closed By Faculty")
	@Story("Verify Chat History When Application Is Closed By Faculty")
	public void testChatHistoryWhenApplicationIsClosedByFaculty(String browser) {
		logger.info("Starting of testChatHistoryWhenApplicationIsClosedByFaculty method");

		try {
			// Asserting status of day
			Assert.assertTrue(tutorChatsPage.getChatHistoryTxt());

			// quiting the driver
			this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToStudentConversationTest);
			// initialising again the page objects
			this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
					WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToStudentConversationTest);
			this.tutorChatsPage = new ChatsPage(this.tutorDriver);

			// validation of chat history
			super.testChatsTab(tutorChatsPage);
			super.testSearchRecepientForStudent(tutorChatsPage);
			super.testSelectStudentRecepient(tutorChatsPage,tutorConversationPage);

			// Asserting status of day
			String chatHistoryTxt = tutorChatsPage.getDayTxt();
			Assert.assertEquals(chatHistoryTxt, expectedAssertionsProp.getProperty(LABEL_TODAY_TXT));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Chat History When application is closed \" : " + e.getMessage());
			logger.error("Error occured while testing Chat History When application is closed ", e);
		}

		logger.info("Ending of testChatHistoryWhenApplicationIsClosedByFaculty method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && studentDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(studentDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToStudentConversationTest);
				this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_TutorToStudentConversationTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}
