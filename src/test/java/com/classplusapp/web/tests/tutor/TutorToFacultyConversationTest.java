package com.classplusapp.web.tests.tutor;

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
import com.classplusapp.web.pages.faculty.FacultyConversationPage;
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
@Feature("Tutor to Faculty Conversation")
public class TutorToFacultyConversationTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver facultyDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage facultyChatsPage = null;
	private FacultyConversationPage facultyConversationPage = null;
	Long SystemTimeAtTutorEnd, SystemTimeAtFacultyEnd;

	private static final Logger logger = Logger.getLogger(TutorToFacultyConversationTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {

		logger.info("Starting of initClassplusSiteLogin method in TutorConversationTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToFacultyConversationTest);
		this.facultyDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_ONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_TutorToFacultyConversationTest);

		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.facultyChatsPage = new ChatsPage(this.facultyDriver);
		this.facultyConversationPage = new FacultyConversationPage(this.facultyDriver);

		logger.info("Ending of initClassplusSiteLogin method in TutorConversationTest");
	}

	@Test(priority = 1, description = "Verify Tutor and Faculty can able to click Chats Tab ", groups = "sanity")
	@Description("Test Description:Verify Tutor and Faculty can able to click Chats Tab")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Tutor and Faculty can able to click Chats Tab")
	public void testChatsTabAtTutorAndFacultyEnd() {
		logger.info("Starting of testChatsTabAtTutorAndFacultyEnd method");

		try {
			// Tutor End
			super.testChatsTab(tutorChatsPage);
			// Faculty End
			super.testChatsTab(facultyChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Chats Tab", e);
		}

		logger.info("Ending of testChatsTabAtTutorAndFacultyEnd method");
	}

	@Test(priority = 2, description = "Verify Start a conversation in moreOptions", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Start a conversation in moreOptions")
	@Story("Verify Start a conversation in moreOptions")
	public void testStartConversationByTutor() {
		logger.info("Starting of testStartConversationByTutor method");

		try {
			// Tutor Initiated Chat With Faculty
			super.testMoreOptionsImage(tutorChatsPage);
			super.testSearchBarLabel(tutorChatsPage);
			super.testMessageLabel(tutorChatsPage);
			super.testMoreOptions(tutorChatsPage);
			super.testStartConversation(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Start a conversation : " + e.getMessage());
			logger.error("Error occured while testing Start a conversation", e);
		}

		logger.info("Ending of testStartConversationByTutor method");
	}

	@Test(priority = 3, description = "Verify Filters for Faculty", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Filters for Faculty")
	@Story("Verify Filters for Faculty")
	public void testFiltersForFaculty() {
		logger.info("Starting of testFiltersForFaculty method");

		try {
			// Filter and Select the Faculty list
			super.testFilters(tutorChatsPage);
			super.testSelectFacultyInFilters(tutorChatsPage);
			super.testApplyButtonInFilters(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Filters: " + e.getMessage());
			logger.error("Error occured while testing Filters", e);
		}

		logger.info("Ending of testFiltersForFaculty method");
	}

	@Test(priority = 4, description = "Verify Search With Invalid Query")
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

	@Test(priority = 5, description = "Verify Tutor can able to send message to Faculty in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to send message to Faculty in Chat Page")
	@Story("Verify Send Message By Tutor to Faculty")
	public void testSendMessageByTutorToFaculty() {
		logger.info("Starting of testSendMessageByTutorToFaculty method");

		try {
			// Search and select the faculty recepient
			super.testSearchRecepientForFaculty(tutorChatsPage);
			super.testSelectFacultyRecepient(tutorChatsPage);
			// Tutor sent message to Faculty
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			super.testSmoothScrollIng(tutorChatsPage);
			// search and select
			super.testSearchRecepientForTutor(facultyChatsPage);
			super.testSelectTutorRecepient(facultyChatsPage);
			// validation on other end
			super.testValidateReceivedMessage(facultyChatsPage, testDataProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing  Send Message By Tutor to Faculty: " + e.getMessage());
			logger.error("Error occured while testing Send Message By Tutor to Faculty", e);
		}

		logger.info("Ending of testSendMessageByTutorToFaculty method");
	}

	@Test(priority = 6, description = "Verify Faculty can able to send message to Tutor in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Faculty can able to send message to Tutor in Chat Page")
	@Story("Verify Send Message By Faculty to Tutors")
	public void testSendMesageByFacultyToTutor() {
		logger.info("Starting of testSendMesageByFacultyToTutor method");

		try {
			// Tutor sent message to Faculty
			super.testSendMessage(facultyChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateReceivedMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing  Send Message By Faculty to Tutor: " + e.getMessage());
			logger.error("Error occured while testing Send Message By Facultys to Tutor", e);
		}

		logger.info("Ending of testSendMesageByFacultyToTutor method");
	}

	@Test(priority = 7, description = "Verify that how many number of words or characters can be send at a time by Tutor.")
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

	@Test(priority = 8, description = "Verify that how many number of words or characters can be send at a time by Faculty.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify that how many number of words or characters can be send at a time by Faculty.")
	@Story("Verify that how many number of words or characters can be send at a time by Faculty.")
	public void testNumberOfCharactersWhileSendingMessageByFaculty() {
		logger.info("Starting of testNumberOfCharactersWhileSendingMessageByFaculty method");

		try { // Tutor sent message to Faculty
			super.testSendMessage(facultyChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			int expectedNoOfCharaters = facultyChatsPage.getNoOfCharacters(testDataProp.getProperty(SEND_MESSAGE));
			int actualNoOfCharaters = facultyChatsPage.getSendMessageTxt().length();

			// Assert for text
			Assert.assertEquals(facultyChatsPage.getSendMessageTxt(), testDataProp.getProperty(SEND_MESSAGE));

			// Assert for no of characters
			Assert.assertEquals(actualNoOfCharaters, expectedNoOfCharaters);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that how many number of words or characters can be send at a time by Faculty.: "
							+ e.getMessage());
			logger.error(
					"Error occured while testingVerify that how many number of words or characters can be send at a time by Faculty.",
					e);
		}

		logger.info("Ending of testNumberOfCharactersWhileSendingMessageByFaculty method");
	}

	@DataProvider(name = "testDataForChatBox")
	public Object[] getDataFromDataprovider() {

		return new Object[] { "Hai1234Everyone", "123456789",
				"https://webp-gcp.classplusapp.com/messages/details?convId=626f62e42cf76e001256a30f",
				"someone@gmail.com", "@$%$##&*", "Good  to  see     you" };

	}

	@Test(priority = 9, description = "Verify Send Message With Different Data In Chat Box By Student", dataProvider = "testDataForChatBox")
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

	@Test(priority = 10, description = "Verify Send Message with Timestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with Timestamp")
	@Story("Verify Send Message with Timestamp")
	public void testSentTimeStampWithLHSTimestampByTutor() {
		logger.info("Starting of testSentTimeStampWithLHSTimestampByTutor method");

		try {
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			
			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			Assert.assertEquals(tutorChatsPage.getMessageTimeStampTxt(),
					tutorChatsPage.getLHSTimestamp(testDataProp.getProperty(FACULTY_RECEPIENT)));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Validate Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Validate Send Message With Time Stamp", e);
		}

		logger.info("Ending of testSentTimeStampWithLHSTimestampByTutor method");
	}

	@Test(priority = 11, description = "Verify that how much time is it taking to send a message from Tutor to Faculty")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Tutor to Faculty")
	@Story("Verify that how much time is it taking to send a message from Tutor to Faculty")
	public void testConversationTimePeriodForTutorToFaculty() {
		logger.info("Starting of testConversationTimePeriodForTutorToFaculty method");

		try {
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			SystemTimeAtTutorEnd = super.getConversationTimePeriod();
			Thread.sleep(2000);
			// Validation for received text and timestamp
			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			// Assert.assertEquals(tutorChatsPage.getMessageTimeStampTxt(),
			// this.getCurrentSystemTime());

			SystemTimeAtFacultyEnd = super.getConversationTimePeriod();
			Long TimeDifference = SystemTimeAtTutorEnd - SystemTimeAtFacultyEnd;
			logger.debug(TimeDifference);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from Tutor to Faculty\" : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from Tutor to Faculty",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForTutorToFaculty method");
	}

	@Test(priority = 12, description = "Verify how long a chat can be saved")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify how long a chat can be saved")
	@Story("Verify how long a chat can be saved")
	public void testHowLongChatSavedByTutorAndFaculty() {
		logger.info("Starting of testHowLongChatSaved method");

		try {
			super.testHowlongChatSaved(tutorChatsPage);
			logger.debug("Last chat was on :" + testHowlongChatSaved(tutorChatsPage) + "Done");

			super.testHowlongChatSaved(facultyChatsPage);
			logger.debug("Last chat was on :" + testHowlongChatSaved(facultyChatsPage) + "Done");

		} catch (Exception e) {
			Assert.fail("Exception occured while testifng how long a chat saved\" : " + e.getMessage());
			logger.error("Error occured while testing how long a chat saved", e);
		}

		logger.info("Ending of testHowLongChatSaved method");
	}

	@Test(priority = 13, description = "Verify for Faculty Is With Active Green Color Displayed in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify for Faculty Is With Active Green Color Displayed in Chat Page")
	@Story("Verify for Faculty Is With Active Green Color Displayed in Chat page")
	public void testFacultyWithActiveItemGreedDotByTutor() {
		logger.info("Starting of testFacultyWithActiveItemGreedDotByTutor method");

		try {
			Thread.sleep(3000);
			Assert.assertTrue(tutorChatsPage.isDisplayedGreenColor(testDataProp.getProperty(FACULTY_RECEPIENT)));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing for Faculty Is With Active Green Color Displayed: "
					+ e.getMessage());
			logger.error("Error occured while testing for Faculty Is With Active Green Color Displayed", e);
		}

		logger.info("Ending of testFacultyWithActiveItemGreedDotByTutor method");
	}

	@Test(priority = 14, description = "Verify for Tutor Is With Active Green Color Displayed in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify for Tutor Is With Active Green Color Displayed in Chat Page")
	@Story("Verify for Tutor Is With Active Green Color Displayed in Chat page")
	public void testTutorWithActiveItemGreedDotByFacultty() {
		logger.info("Starting of testTutorWithActiveItemGreedDotByFacultty method");

		try {
			Assert.assertTrue(
					facultyChatsPage.isDisplayedGreenColor(SEARCH_FOR_TUTOR_RECEPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing  for Tutor Is With Active Green Color Displayed: "
					+ e.getMessage());
			logger.error("Error occured while testing for Tutor Is With Active Green Color Displayed", e);
		}

		logger.info("Ending of testTutorWithActiveItemGreedDot method");
	}

	@Test(priority = 15, description = "Verify Selecting with Single Image")
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

	@Test(priority = 16, description = "Verify Selecting with Same Image Multiple Times")
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

	@Test(priority = 17, description = "Verify Select With Twenty Different Images")
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

	@Test(priority = 18, description = "Verify that user is able to send any emoticons or not.")
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

	@Test(priority = 19, description = "Verify Add Document and File Extension")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document and File Extension")
	@Story("Verify Add Document and File Extension")
	public void testAddDocumentAndFileExtensionSentByFaculty() {
		logger.info("Starting of testAddDocumentAndFileExtensionSentByFaculty method");

		try {
			super.testAddDocumentFile(tutorChatsPage);
			super.testAddDocumentFile(facultyChatsPage);
			// expected file extension
			String expectedFileName = expectedAssertionsProp.getProperty(DOCUMENT_FILE_TXT);
			String expectedResult = expectedFileName.substring(expectedFileName.length() - 3,
					expectedFileName.length());
			// actual file extension
			String fiLeNameTxt = facultyChatsPage.getAddDocumentTxt();
			String actualResult = fiLeNameTxt.substring(fiLeNameTxt.length() - 3, fiLeNameTxt.length());

			Assert.assertEquals(actualResult, expectedResult);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentAndFileExtensionSentByFaculty method");
	}

	@Test(priority = 20, description = "Verify Download And View Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Download And View Document")
	@Story("Verify Download And View Document")
	public void testDownloadAndViewDocumentByTutorAndFaculty() {
		logger.info("Starting of testDownloadAndViewDocumentByTutor method");

		try {
			// tutor end testcase
			Assert.assertTrue(tutorChatsPage.isDisplayedDownloadButton());

			tutorChatsPage.clickOnDownloadButton();
			tutorChatsPage.closeTab();

			// faculty end testcase
			Assert.assertTrue(facultyChatsPage.isDisplayedDownloadButton());

			facultyChatsPage.clickOnDownloadButton();
			facultyChatsPage.closeTab();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Download And View Document File : " + e.getMessage());
			logger.error("Error occured while testing Download And View Document File", e);
		}

		logger.info("Ending of testDownloadAndViewDocumentByTutor method");
	}

	@Test(priority = 21, description = "Verify Selecting with Single Image")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Selecting with Single Image")
	@Story("Verify Selecting with Single Image")
	public void testSelectSingleImageByFaculty() {
		logger.info("Starting of testSelectSingleImageByFaculty method");

		try {
			// Test with Single Select
			super.testSelectImages(facultyChatsPage, 1);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select Single Image: " + e.getMessage());
			logger.error("Error occured while testing Select Single Image", e);
		}

		logger.info("Ending of testSelectSingleImageByFaculty method");
	}

	@Test(priority = 22, description = "Verify Selecting with Same Image Multiple Times")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Selecting with Same Image Multiple Times")
	@Story("Verify Selecting with Same Image Multiple Times")
	public void testSelectWithSameImageMultipleTimesByFaculty() {
		logger.info("Starting of testSelectWithSameImageMultipleTimesByFaculty method");

		try {
			// Test Select with Same Image multile times
			for (int i = 0; i < 5; i++) {
				super.testSelectImages(facultyChatsPage, 1);
			}

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Same Image Multiple Times : " + e.getMessage());
			logger.error("Error occured while testing Select With Same Image Multiple Times", e);
		}

		logger.info("Ending of testSelectWithSameImageMultipleTimesByFaculty method");
	}

	@Test(priority = 23, description = "Verify Select With Twenty Different Images")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Select With Twenty Different Images")
	@Story("Verify Select With Twenty Different Images")
	public void testSelectWithTwentyDifferentImagesByFaculty() {
		logger.info("Starting of testSelectWithTwentyDifferentImagesByFaculty method");

		try {
			// Test Select With Different Images
			super.testSelectImages(facultyChatsPage, 20);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Twenty Different Images : " + e.getMessage());
			logger.error("Error occured while testing Select With Twenty Different Images", e);
		}

		logger.info("Ending of testSelectWithTwentyDifferentImagesByFaculty method");
	}

	@Test(priority = 24, description = "Verify clicking on  profile name should not navigate to profile screen ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify clicking on  profile name should not navigate to profile screen ")
	@Story("Verify clicking on  profile name should not navigate to profile screen ")
	public void testProfileNameAtFacultyEnd() {
		logger.info("Starting of testProfileNameAtFacultyEnd method");

		try {
			Assert.assertFalse(facultyChatsPage.getProfileHeaderNameTxt());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing clicking on  profile name should not navigate to profile screen  : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing clicking on  profile name should not navigate to profile screen  ", e);
		}

		logger.info("Ending of testProfileNameAtFacultyEnd method");
	}

	@Test(priority = 25, description = "Verify that how much time is it taking to send a message from Faculty to Tutor ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Faculty to Tutor")
	@Story("Verify that how much time is it taking to send a message from Parent to Tutor")
	public void testConversationTimePeriodForFacultyToTutor() {
		logger.info("Starting of testConversationTimePeriodForFacultyToTutor method");

		try {
			super.testSendMessage(facultyChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			SystemTimeAtTutorEnd = super.getConversationTimePeriod();
			Thread.sleep(2000);
			// Validation for received text
			super.testValidateSendMessage(facultyChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			Assert.assertEquals(facultyChatsPage.getMessageTimeStampTxt(), this.getCurrentSystemTime());
			// time difference
			SystemTimeAtFacultyEnd = super.getConversationTimePeriod();
			Long TimeDifference = SystemTimeAtTutorEnd - SystemTimeAtFacultyEnd;
			logger.debug(TimeDifference);

		} catch (Exception e) {

			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from Parent to Tutor\" : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from Parent to Tutor",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForFacultyToTutor method");
	}

	@DataProvider(name = "testDataForChat")
	public Object[] getDataFromDataproviderByFaculty() {

		return new Object[] { "Hai1234Everyone", "123456789",
				"https://webp-gcp.classplusapp.com/messages/details?convId=626f62e42cf76e001256a30f",
				"someone@gmail.com", "@$%$##&*", "Good  to  see                you" };

	}

	@Test(priority = 26, description = "Verify Send Message With Different Data In Chat Box By Faculty", dataProvider = "testDataForChat")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Send Message With Different Data In Chat Box By Faculty")
	@Story("Verify Send Message With Different Data In Chat Box By Student")
	public void testSendMessageWithDifferentDataInChatBoxByFaculty(String testDataForChat) {
		logger.info("Starting of testSendMessageWithDifferentDataInChatBoxByFaculty method");

		try {
			super.testSendMessage(facultyChatsPage, testDataForChat);
			// Validation
			super.testValidateSendMessage(facultyChatsPage, testDataForChat);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing with different data sent  By Faculty : " + e.getMessage());
			logger.error("Error occured while testing with different data sent By Faculty", e);
		}

		logger.info("Ending of testSendMessageWithDifferentDataInChatBoxByFaculty method");
	}

	@Test(priority = 27, description = "Verify that user is able to send any emoticons or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to send any emoticons or not.")
	@Story("Verify that user is able to send any emoticons or not.")
	public void testSendEmoticonsByFaculty() {
		logger.info("Starting of testSendEmoticonsByFaculty method");

		try {
			facultyChatsPage.setEmoticons("😄");
			facultyChatsPage.clickOnSendButton();

			super.testValidateSendMessage(facultyChatsPage, "😄");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that user is able to send any emoticons or not. \" : "
					+ e.getMessage());
			logger.error("Error occured while testing that user is able to send any emoticons or not. ", e);
		}

		logger.info("Ending of testSendEmoticonsByFaculty method");
	}

	@Test(priority = 28, description = "Verify Send Message with Timestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with Timestamp")
	@Story("Verify Send Message with Timestamp")
	public void testSentTimeStampWithLHSTimestampByFaculty() {
		logger.info("Starting of testSentTimeStampWithLHSTimestampByFaculty method");

		try {
			super.testSendMessage(facultyChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			// Asserting text and status of day
			super.testValidateSendMessage(facultyChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			// asserting the lhs time w.r.to rhs
			Assert.assertEquals(facultyChatsPage.getMessageTimeStampTxt(),
					facultyChatsPage.getLHSTimestamp(SEARCH_FOR_TUTOR_RECEPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Validate Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Validate Send Message With Time Stamp", e);
		}

		logger.info("Ending of testSentTimeStampWithLHSTimestampByFaculty method");
	}

	@Test(priority = 29, description = "Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	@Story("Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	public void testStatusOfChatWithYesterday() {
		logger.info("Starting of testStatusOfChatWithYesterday method");

		try {
			facultyChatsPage.getYesterdayTxt();
			// Asserting status of day
			if (facultyChatsPage.getDayTxt().equals("YESTERDAY")) {
				String yesterdayTxt = facultyChatsPage.getDayTxt();
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

	@Test(priority = 30, description = "Verify Tutor can able to Mute and UnMute Conversation for a Faculty Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Mute and UnMute Conversation for a Faculty Conversation")
	@Story("Verify Mute and Unmute Conversation At Tutor End")
	public void testMuteOrUnmuteConversationByTutor() {
		logger.info("Starting of testMuteOrUnmuteConversationByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(tutorChatsPage,
					expectedAssertionsProp.getProperty(LABEL_FACULTY_NAME_TXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute Or UnMute Conversation at Tutor End : " + e.getMessage());
			logger.error("Error occured while testing Mute Or UnMute Conversation at Tutor End", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByTutor method");
	}

	@Test(priority = 31, description = "Verify Faculty can able to Mute Conversation for a Tutor Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Faculty can able to Mute Conversation for a Tutor Conversation")
	@Story("Verify Mute and Unmute Conversation At Student End")
	public void testMuteOrUnmuteConversationByFaculty() {
		logger.info("Starting of testMuteOrUnmuteConversationByFaculty method");

		try {
			facultyChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(facultyChatsPage,
					SEARCH_FOR_TUTOR_RECEPIENT);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Mute Or UnMute Conversation at Student End : " + e.getMessage());
			logger.error("Error occured while testing Mute Or UnMute Conversation at Student End", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByFaculty method");
	}

	@Test(priority = 32, description = "Verify Report Conversation in  Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in  Chat Page")
	@Story("Verify Report Conversation")
	public void testReportConvers6tionByTutorAndFaculty() {
		logger.info("Starting of testReportConversationByTutorAndFaculty method");

		try {
			super.testReportConversation(tutorChatsPage);
			super.testReportConversation(facultyChatsPage);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Report Conversation at Tutor and Faculty End : " + e.getMessage());
			logger.error("Error occured while testing Report Conversation at Tutor and Faculty End ", e);
		}

		logger.info("Ending of testReportConversationByTutorAndFaculty method");
	}

	@Test(priority = 33, description = "Verify Clear Conversation in  Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Chat Page.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByTutorAndFaculty() {
		logger.info("Starting of testClearConversationByTutorAndFaculty method");

		try {
			super.testClearConversation(tutorChatsPage);
			super.testClearConversation(facultyChatsPage);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Clear Conversation at Tutor and Faculty End : " + e.getMessage());
			logger.error("Error occured while testing Clear Conversation at Tutor and Faculty End ", e);
		}

		logger.info("Ending of testClearConversationByTutorAndFaculty method");
	}

	@Test(priority = 34, description = "Verify Tutor can able to copy a message in a chat body", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to copy a message in a chat body")
	@Story("Verify Copy Message")
	public void testCopyMessageByTutorAndFaculty() {
		logger.info("Starting of testCopyMessageByTutor method");

		try {
			super.testCopyMessage(tutorChatsPage);
			super.testCopyMessage(facultyChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Copy Message By Tutor and Faculty End : " + e.getMessage());
			logger.error("Error occured while testing Copy Message By Tutor and Faculty End", e);
		}

		logger.info("Ending of testCopyMessageByTutor method");
	}

	@Test(priority = 35, description = "Verify Delete Single Message", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Delete Single Message")
	@Story("Verify Delete Single Message")
	public void testDeleteSingleMessageByTutorAndFaculty() {
		logger.info("Starting of testDeleteSingleMessageByTutorAndFaculty method");

		try {
			super.testDeleteSingleMessage(tutorChatsPage);
			super.testDeleteSingleMessage(facultyChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Delete Single Message By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Delete Single Message By Tutor", e);
		}

		logger.info("Ending of testDeleteSingleMessageByTutorAndFaculty method");
	}

	@Test(priority = 36, description = "Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	@Story("Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	public void testStatusOfChatWithLHSDate() {
		logger.info("Starting of testStatusOfChatWithLHSDate method");

		try {
			facultyChatsPage.clickOnRecepientLHSDateTxt();
			// Asserting status of day
			logger.debug("Long Back Date in the chat list" + facultyChatsPage.getDayTxt());
			// Logging the date conversation
			if (facultyChatsPage.getDayTxt().matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
				logger.debug("Long Back Date in the chat list" + facultyChatsPage.getDayTxt());
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

		logger.info("Ending of testStatusOfChatWithLHSDate method");
	}

	@Test(priority = 37, description = "Verify that time/date for chats is in decending order on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that time/date for chats is in decending order on LHS")
	@Story("Verify that time/date for chats is in decending order on LHS")
	public void testDayStatusInDescendingOrderOnLHS() {
		logger.info("Starting of testDayStatusInDescendingOrderOnLHS method");

		try {
			logger.debug(facultyConversationPage.getDescendingOrderDayStatusOfLHSTxt());
			logger.debug(facultyConversationPage.getDescendingOrderOfLHSDayStatusTxt());
			Assert.assertTrue(facultyConversationPage.getDescendingOrderDayStatusOfLHSTxt()
					.equals(facultyConversationPage.getDescendingOrderOfLHSDayStatusTxt()));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that time/date for chats is in decending order on LHS\" : "
					+ e.getMessage());
			logger.error("Error occured while testing that time/date for chats is in decending order on LHS", e);
		}

		logger.info("Ending of testDayStatusInDescendingOrderOnLHS method");
	}

	@Test(priority = 38, description = "Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	@Story("Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	public void testSetProfileByFaculty() {
		logger.info("Starting of testSetProfileByTutor method");

		try {
			facultyChatsPage.clickOnProfile();
			facultyChatsPage.clickOnEditButton();
			facultyChatsPage
					.setProfilePhoto(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH));

			BufferedImage expectedImage = ImageIO
					.read(new File(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH)));

			logger.debug(tutorChatsPage.getProfileImage());
			logger.debug(expectedImage);

			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(tutorChatsPage.getProfileImage(), expectedImage);

			logger.debug(diff);

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
	
	@Parameters({ "browser" })
	@Test(priority = 39, description = "Verify Chat History When Application Is Closed By Faculty")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Chat History When Application Is Closed By Faculty")
	@Story("Verify Chat History When Application Is Closed By Faculty")
	public void testChatHistoryWhenApplicationIsClosedByFaculty(String browser) {
		logger.info("Starting of testChatHistoryWhenApplicationIsClosedByFaculty method");

		try {
			// Asserting status of day
			Assert.assertTrue(facultyChatsPage.getChatHistoryTxt());

			// quiting the driver
			this.quitDriver(WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_TutorToFacultyConversationTest);
			// initialising again the page objects
			this.facultyDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_ONE_MOBILE_NUMBER, EMAIL_ADDRESS,
					WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_TutorToFacultyConversationTest);
			this.facultyChatsPage = new ChatsPage(this.facultyDriver);
			this.facultyConversationPage = new FacultyConversationPage(this.facultyDriver);

			// validation of chat history
			super.testChatsTab(facultyChatsPage);
			super.testSearchRecepientForTutor(facultyChatsPage);
			super.testSelectTutorRecepient(facultyChatsPage);

			// Asserting status of day
			String chatHistoryTxt = facultyChatsPage.getDayTxt();
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
			if (tutorDriver != null && facultyDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(facultyDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToFacultyConversationTest);
				this.quitDriver(WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_TutorToFacultyConversationTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}

}
