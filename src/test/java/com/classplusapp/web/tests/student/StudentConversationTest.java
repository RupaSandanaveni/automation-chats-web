package com.classplusapp.web.tests.student;

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
import com.classplusapp.web.pages.student.StudentConversationPage;
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
@Feature("Tutor to Student Conversation")
public class StudentConversationTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver studentDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage studChatsPage = null;
	private TutorConversationPage tutorConversationPage = null;
	private StudentConversationPage studentConversationPage = null;

	Long SystemTimeAtTutorEnd, SystemTimeAtStudentEnd;

	private static final Logger logger = Logger.getLogger(StudentConversationTest.class.getName());

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
		logger.info("Starting of testChatsTabAtTutorAndStudentEnd method");

		try {
			super.testChatsTab(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Chats Tab", e);
		}

		logger.info("Ending of testChatsTabAtTutorAndStudentEnd method");
	}
	
	@Test(priority = 2, description = "Verify in chat search field by name or number but by putting space at the start")
	@Description("Test Description:Verify in chat search field by name or number but by putting space at the start")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify in chat search field by name or number but by putting space at the start")
	public void testSearchInChatByPuttingSpaceAtStart() {
		logger.info("Starting of testSearchInChatByPuttingSpaceAtStart method");

		try {
			studChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(studChatsPage.getNoConversationTxt(),
					expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));

			studChatsPage.clickOnClearQueryAtStudentEnd();

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
	public void testDayStatusInDescendingOrderOnLHS() {
		logger.info("Starting of testDayStatusInDescendingOrderOnLHS method");

		try {

			Assert.assertTrue(studentConversationPage.getDescendingOrderDayStatusOfLHS()
					.equals(studentConversationPage.getDescendingOrderOfLHSDayStatus()));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that time/date for chats is in decending order on LHS\" : "
					+ e.getMessage());
			logger.error("Error occured while testing that time/date for chats is in decending order on LHS", e);
		}

		logger.info("Ending of testDayStatusInDescendingOrderOnLHS method");
	}

	@Test(priority = 4, description = "Verify Student can able to send message in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Student can able to send message in Chat Page")
	@Story("Verify Send Message By Student")
	public void testSendMessageUsingKeyEnterByStudent() {
		logger.info("Starting of testSendMessageUsingKeyEnterByStudent method");

		try {
			// pre condition steps
			super.testChatsTab(tutorChatsPage);
			super.testMoreOptionsImage(tutorChatsPage);
			super.testSearchBarLabel(tutorChatsPage);
			super.testMessageLabel(tutorChatsPage);
			super.testMoreOptions(tutorChatsPage);
			super.testStartConversation(tutorChatsPage);
			super.testFilters(tutorChatsPage);
			super.testSelectStudentInFilters(tutorChatsPage);
			super.testApplyButtonInFilters(tutorChatsPage);
			super.testSearchRecepientForStudent(tutorChatsPage);
			super.testSelectStudentRecepient(tutorChatsPage, tutorConversationPage);
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);
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

	@Test(priority = 5, description = "Verify that how many number of words or characters can be send at a time.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify that how many number of words or characters can be send at a time.")
	@Story("Verify that how many number of words or characters can be send at a time.")
	public void testNumberOfCharactersWhileSendingMessageByStudent() {
		logger.info("Starting of testNumberOfCharactersWhileSendingMessageByTutor method");

		try {
			// Student sent message to Tutor
			super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			// Assert for text
			Assert.assertEquals(studChatsPage.getSendMessageTxt(), testDataProp.getProperty(SEND_MESSAGE));
			// Assert for no of characters
			Assert.assertEquals(studChatsPage.getSendMessageTxt().length(),
					studChatsPage.getNoOfCharacters(testDataProp.getProperty(SEND_MESSAGE)));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that how many number of words or characters can be send at a time.: "
							+ e.getMessage());
			logger.error(
					"Error occured while testingVerify that how many number of words or characters can be send at a time.",
					e);
		}

		logger.info("Ending of testNumberOfCharactersWhileSendingMessageByTutor method");

	}

	@Test(priority = 6, description = "Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	@Story("Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
	public void testStatusOfChatWithLHSDate() {
		logger.info("Starting of testStatusOfChatWithLHSDate method");

		try {
			studChatsPage.clickOnRecepientLHSDateTxt();

			if (studChatsPage.getDayTxt().matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
				logger.debug("Long Back Date in the chat list" + studChatsPage.getDayTxt());
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

	@DataProvider(name = "testDataForChatBox")
	public Object[] getDataFromDataprovider() {

		return new Object[] { "Hai1234Everyone", "123456789",
				"https://webp-gcp.classplusapp.com/messages/details?convId=626f62e42cf76e001256a30f",
				"someone@gmail.com", "@$%$##&*", "Good  to  see     you" };

	}

	@Test(priority = 7, description = "Verify Send Message With Different Data In Chat Box By Student", dataProvider = "testDataForChatBox")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Send Message With Different Data In Chat Box By Student")
	@Story("Verify Send Message With Different Data In Chat Box By Student")
	public void testSendMessageWithDifferentDataInChatBoxByStudent(String testDataForChatBox) {
		logger.info("Starting of testSendMessageWithDifferentDataInChatBoxByStudent method");

		try {
			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);

			super.testSendMessage(studChatsPage, testDataForChatBox);
			super.testValidateSendMessage(studChatsPage, testDataForChatBox);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing with different data sent  By Student : " + e.getMessage());
			logger.error("Error occured while testing with different data sent By Student", e);
		}

		logger.info("Ending of testSendMessageWithDifferentDataInChatBoxByStudent method");
	}

	@Test(priority = 8, description = "Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	@Story("Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
	public void testSetProfileByStudent() {
		logger.info("Starting of testSetProfileByStudent method");

		try {
			studChatsPage.clickOnProfile();
			studChatsPage.clickOnEditButton();
			studChatsPage.setProfilePhoto(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH));

			BufferedImage expectedImage = ImageIO
					.read(new File(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH)));
			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(tutorConversationPage.getProfileImage(), expectedImage);
			System.out.println(diff.hasDiff());
			Assert.assertFalse(diff.hasDiff());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that user is able to set its profile pic from account profile, which is visible to other users or not. : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that user is able to set its profile pic from account profile, which is visible to other users or not. ",
					e);
		}

		logger.info("Ending of testSetProfileByStudent method");
	}

	@Test(priority = 9, description = "Verify Download And View Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Download And View Document")
	@Story("Verify Download And View Document")
	public void testDownloadAndViewDocumentByStudent() {
		logger.info("Starting of testDownloadAndViewDocumentByStudent method");

		try {
			studChatsPage.clickOnChatsTab();
			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);

			super.testAddDocumentFile(studChatsPage);

			// faculty end testcase
			Assert.assertTrue(studChatsPage.isDisplayedDownloadButton());

			studChatsPage.clickOnDownloadButton();
			studChatsPage.closeTab();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Download And View Document File : " + e.getMessage());
			logger.error("Error occured while testing Download And View Document File", e);
		}

		logger.info("Ending of testDownloadAndViewDocumentByStudent method");
	}

	@Test(priority = 10, description = "Verify Select With Twenty Different Images")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Select With Twenty Different Images")
	@Story("Verify Select With Twenty Different Images")
	public void testSelectWithTwentyDifferentImagesByStudent() {
		logger.info("Starting of testSelectWithTwentyDifferentImagesByStudent method");

		try {
			// Test Select With Different Images
			super.testSelectImages(studChatsPage, 20);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Twenty Different Images : " + e.getMessage());
			logger.error("Error occured while testing Select With Twenty Different Images", e);
		}

		logger.info("Ending of testSelectWithTwentyDifferentImagesByStudent method");
	}

	@Test(priority = 11, description = "Verify Selecting with Single Image")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Selecting with Single Image")
	@Story("Verify Selecting with Single Image")
	public void testSelectSingleImageByStudent() {
		logger.info("Starting of testSelectSingleImageByParent method");

		try {
			// Test with Single Select

			super.testSelectImages(studChatsPage, 1);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select Single Image: " + e.getMessage());
			logger.error("Error occured while testing Select Single Image", e);
		}

		logger.info("Ending of testSelectSingleImageByParent method");
	}

	@Test(priority = 12, description = "Verify Selecting with Same Image Multiple Times")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Selecting with Same Image Multiple Times")
	@Story("Verify Selecting with Same Image Multiple Times")
	public void testSelectWithSameImageMultipleTimesByStudent() {
		logger.info("Starting of testSelectWithSameImageMultipleTimesByStudent method");

		try { // Test Select with Same Image multile times
			for (int i = 0; i < 5; i++) {
				super.testSelectImages(studChatsPage, 1);

			}

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Same Image Multiple Times : " + e.getMessage());
			logger.error("Error occured while testing Select With Same Image Multiple Times", e);
		}

		logger.info("Ending of testSelectWithSameImageMultipleTimesByStudent method");
	}

	@Test(priority = 13, description = "Verify Add Document and File extension")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document  and File extension")
	@Story("Verify Add Document  and File extension")
	public void testAddDocumentAndFileExtensionSentByStudent() {
		logger.info("Starting of testAddDocumentAndFileExtensionSentByStudent method");

		try {
			super.testAddDocumentFile(studChatsPage);

			String expectedFileName = expectedAssertionsProp.getProperty(DOCUMENT_FILE_TXT);
			String expectedResult = expectedFileName.substring(expectedFileName.length() - 3,
					expectedFileName.length());
			String actualFileName = studChatsPage.getAddDocumentTxt();
			String actualResult = actualFileName.substring(actualFileName.length() - 3, actualFileName.length());
			Assert.assertEquals(actualResult, expectedResult);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentAndFileExtensionSentByStudent method");
	}

	@Test(priority = 14, description = "Verify that user is able to send any emoticons or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to send any emoticons or not.")
	@Story("Verify that user is able to send any emoticons or not.")
	public void testSendEmoticonsByStudent() {
		logger.info("Starting of testSendEmoticonsByStudent method");

		try {
			studChatsPage.setEmoticons("😄");

			// super.testValidateSendMessage(studChatsPage, "ðŸ˜„");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that user is able to send any emoticons or not: "
					+ e.getMessage());
			logger.error("Error occured while testing that user is able to send any emoticons or not. ", e);
		}

		logger.info("Ending of testSendEmoticonsByStudent method");
	}

	@Test(priority = 15, description = "Verify for Tutor Is With Active GreenColor Displayed in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify for Tutor Is With Active Green Color Displayed in Chat Page")
	@Story("Verify for Tutor Is With Active Green Color Displayed in Chat page")
	public void testTutorWithActiveItemGreedDotByStudent() {
		logger.info("Starting of testTutorWithActiveItemGreedDotByStudent method");

		try {
			Assert.assertTrue(studChatsPage.isDisplayedGreenColor(SEARCH_FOR_TUTOR_RECEPIENT));

			/*
			 * this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST); // every time tutor need to
			 * log out then only these case will be passed Assert.assertFalse(
			 * studChatsPage.isDisplayedGreenColor(testDataProp.getProperty(
			 * SEARCH_FOR_TUTOR_RECEPIENT)));
			 * 
			 * this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE,
			 * TUTOR_MOBILENUMBER, EMAIL_ADDRESS, WEB_DRIVER.TUTOR_DRIVER_TEST);
			 * this.tutorChatsPage = new ChatsPage(this.tutorDriver);
			 */

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Tutor With Active Item Greed Dot By Student: " + e.getMessage());
			logger.error("Error occured while testing Tutor With Active Item Greed Dot By Student", e);
		}

		logger.info("Ending of testTutorWithActiveItemGreedDotByStudent method");
	}

	@Test(priority = 16, description = "Verify that how much time is it taking to send a message from Student to Tutor ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Student to Tutor")
	@Story("Verify that how much time is it taking to send a message from Student to Tutor")
	public void testConversationTimePeriodForStudentToTutor() {
		logger.info("Starting of testConversationTimePeriodForStudentToTutor method");

		try {
			// super.testSelectTutorRecepient(studChatsPage);
			super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			SystemTimeAtTutorEnd = super.getConversationTimePeriod();

			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			SystemTimeAtStudentEnd = super.getConversationTimePeriod();

			Long TimeDifference = SystemTimeAtTutorEnd - SystemTimeAtStudentEnd;
			logger.debug(TimeDifference);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from Student to Tutor\" : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from Student to Tutor",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForStudentToTutor method");
	}

	@Test(priority = 17, description = "Verify how long a chat can be saved at student end")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify how long a chat can be saved at student end")
	@Story("Verify how long a chat can be saved at student end")
	public void testHowLongChatSavedAtStudentEnd() {
		logger.info("Starting of testHowLongChatSavedAtStudentEnd method");

		try {
			super.testHowlongChatSaved(studChatsPage);
			logger.debug("Last chat was on :" + testHowlongChatSaved(studChatsPage) + "Done");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing how long a chat saved\" : " + e.getMessage());
			logger.error("Error occured while testing how long a chat saved", e);
		}

		logger.info("Ending of testHowLongChatSavedAtStudentEnd method");
	}

	@Test(priority = 18, description = "Verify clicking on profile name should not navigate to profile screen ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify clicking on  profile name should not navigate to profile screen ")
	@Story("Verify clicking on  profile name should not navigate to profile screen ")
	public void testProfileNameAtStudentEnd() {
		logger.info("Starting of testProfileNameAtStudentEnd method");

		try {
			Assert.assertFalse(studChatsPage.getProfileHeaderNameTxt());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing clicking on  profile name should not navigate to profile screen  : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing clicking on  profile name should not navigate to profile screen  ", e);
		}

		logger.info("Ending of testProfileNameAtStudentEnd method");
	}

	@Test(priority = 19, description = "Verify Send Message with LHS Timestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with LHS Timestamp")
	@Story("Verify Send Message with Timestamp")
	public void testSentTimeStampWithLHSTimestampByStudent() {
		logger.info("Starting of testSentTimeStampWithLHSTimestampByStudent method");

		try {
			super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			// Asserting status of day
			Assert.assertTrue(studChatsPage.getChatHistoryTxt());

			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			// asserting rhs time with system time
			Assert.assertEquals(studChatsPage.getMessageTimeStampTxt(),
					studChatsPage.getLHSTimestamp(SEARCH_FOR_TUTOR_RECEPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Validate Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Validate Send Message With Time Stamp", e);
		}

		logger.info("Ending of testSentTimeStampWithLHSTimestampByStudent method");
	}

	@Test(priority = 20, description = "Verify Student Talk to Tutor from Store Tab")
	@Description("Test Description:Verify Student Talk to Tutor from Store Tab")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify Student Talk to Tutor from Store Tab")
	public void testTalkToTutorFromStoreAndBackNavigationByStudent() {
		logger.info("Starting of testTalkToTutorFromStoreAndBackNavigationByStudent method");

		try {
			studentConversationPage.clickOnStoreButton();
			studentConversationPage.setSearchInStore(testDataProp.getProperty(STUDENT_SEARCH_COURSE));
			studentConversationPage.clickOnCourse();

			Assert.assertEquals(studentConversationPage.getOverviewTxt(),
					expectedAssertionsProp.getProperty(LABEL_OVERVIEW));
			Assert.assertEquals(studentConversationPage.getContentTxt(),

					expectedAssertionsProp.getProperty(LABEL_CONTENT));
			Assert.assertEquals(studentConversationPage.getTalkToTutorTxt(),
					expectedAssertionsProp.getProperty(LABEL_TALK_TO_TUTOR));

			studentConversationPage.clickOnTalkToTutorButton();

			// Assertion for Chats Txt and chat recepient
			Assert.assertEquals(studChatsPage.getChatsTxt(), expectedAssertionsProp.getProperty(LABEL_CHATS));
			Assert.assertEquals(studChatsPage.getHeaderNameTxt(), expectedAssertionsProp.getProperty(CHAT_RECEPIENT));

			studentConversationPage.clickOnBackNavigationToStore();

			Assert.assertEquals(studentConversationPage.getCoureTxt(),
					expectedAssertionsProp.getProperty(LABEL_COURSE_NAME));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing  Student Talk to Tutor from store module: " + e.getMessage());
			logger.error("Error occured while testing Student Talk to Tutor from store module", e);
		}

		logger.info("Ending of testTalkToTutorFromStoreAndBackNavigationByStudent method");
	}

	@Test(priority = 21, description = "Verify Student can able to Mute Conversation for a Tutor Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Student can able to Mute Conversation for a Tutor Conversation")
	@Story("Verify Mute and Unmute Conversation At Student End")
	public void testMuteOrUnmuteConversationByStudent() {
		logger.info("Starting of testMuteOrUnmuteConversationByStudent method");

		try {
			studChatsPage.clickOnChatsTab();
			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);
			studChatsPage.clickOnMenuVerticalIcon();

			super.testMuteUnmuteConversation(studChatsPage, SEARCH_FOR_TUTOR_RECEPIENT);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Student : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Student", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByStudent method");
	}

	@Test(priority = 22, description = "Verify Report Conversation in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in  Chat Page")
	@Story("Verify Report Conversation")
	public void testReportConversationByStudent() {
		logger.info("Starting of testReportConversationByStudent method");

		try {
			super.testReportConversation(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By Student : " + e.getMessage());
			logger.error("Error occured while testing Repor Conversation By Student", e);
		}

		logger.info("Ending of testReportConversationByStudent method");
	}

	@Test(priority = 23, description = "Verify Clear Conversation in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Chat Page.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByStudent() {
		logger.info("Starting of testClearConversationByStudent method");

		try {
			super.testClearConversation(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By Student : " + e.getMessage());
			logger.error("Error occured while testing Clear Conversation  By Studen", e);
		}

		logger.info("Ending of testClearConversationByStudent method");
	}

	@Test(priority = 24, description = "Verify Student can able to copy a message in a chat body", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Student can able to copy a message in a chat body")
	@Story("Verify Copy Message")
	public void testCopyMessageByStudent() {
		logger.info("Starting of testCopyMessageByStudent method");

		try {
			super.testCopyMessage(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Copy Single Message By Student : " + e.getMessage());
			logger.error("Error occured while testing Copy Message By Student", e);
		}

		logger.info("Ending of testCopyMessageByStudent method");
	}

	@Test(priority = 25, description = "Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	@Story("Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
	public void testStatusOfChatWithYesterdayAtStudentEnd() {
		logger.info("Starting of testStatusOfChatWithYesterdayAtStudentEnd method");

		try {
			studChatsPage.getYesterdayTxt();
			// Asserting status of day
			if (studChatsPage.getDayTxt().equals("YESTERDAY")) {
				String yesterdayTxt = studChatsPage.getDayTxt();
				Assert.assertEquals(yesterdayTxt, expectedAssertionsProp.getProperty(LABEL_YESTERDAY_TXT));
			} else {
				logger.debug("There are no Yesterday Conversations");
			}

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while tesing that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS",
					e);
		}

		logger.info("Ending of testStatusOfChatWithYesterdayAtStudentEnd method");
	}

	@Test(priority = 26, description = "Verify Tutor can able to navigate from Batch Chat Profile", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Tutor can able to navigate from Batch Chat Profile ")
	@Story("Verify Tutor can able to navigate from Batch Chat Profile")
	public void testNavigateAssignmentsTalkToTutorByStudent() {
		logger.info("Starting of testNavigateAssignmentsTalkToTutorByStudent method");

		try {
			studChatsPage.clickOnBatchsTab();

			assertTrue(studentConversationPage.getIconSearch());

			studChatsPage.setSearchBar(testDataProp.getProperty(SEARCH_FOR_BATCH));

			Assert.assertEquals(studentConversationPage.getSearchBatchNameLabel(),
					expectedAssertionsProp.getProperty(SEARCH_BATCH_NAME));

			studChatsPage.clickOnBatch();

			Assert.assertEquals(studentConversationPage.getAssignmentslabel(),
					expectedAssertionsProp.getProperty(ASSIGNMENTS_LABEL));

			studentConversationPage.clickOnAssignmentsTab();

			Assert.assertEquals(studentConversationPage.getAssignmentsHeadinglabel(),
					expectedAssertionsProp.getProperty(ASSIGNMENTS_LABEL));

			assertTrue(studentConversationPage.getIconSearch());

			Assert.assertEquals(studentConversationPage.getAssignmentHeading(),
					expectedAssertionsProp.getProperty(ASSIGNMENTS_HEADING_LABEL));

			studentConversationPage.clickOnAssignmentsHeading();

			Assert.assertEquals(studentConversationPage.getTalkToTutorlabel(),
					expectedAssertionsProp.getProperty(TALK_TO_TUTOR));

			studentConversationPage.clickOnTalkToTutor();
			super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing NavigateAssignmentsTalkToTutorByStudent : " + e.getMessage());
			logger.error("Error occured while testing NavigateAssignmentsTalkToTutorByStudent", e);
		}

		logger.info("Ending of testNavigateBatchProfileChat method");
	}

	@Parameters({ "browser" })
	@Test(priority = 27, description = "Verify Chat History When Application Is Closed By Student")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Chat History When Application Is Closed By Student")
	@Story("Verify Chat History When Application Is Closed By Parent")
	public void testChatHistoryWhenApplicationIsClosedByStudent(String browser) {
		logger.info("Starting of testChatHistoryWhenApplicationIsClosedByStudent method");

		try {
			Assert.assertTrue(studChatsPage.getChatHistoryTxt());

			this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_TutorToStudentConversationTest);
			this.studentDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
					WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_TutorToStudentConversationTest);
			this.studChatsPage = new ChatsPage(this.studentDriver);
			this.studentConversationPage = new StudentConversationPage(this.studentDriver);

			super.testChatsTab(studChatsPage);
			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);

			// Asserting status of day
			logger.info(studChatsPage.getDayTxt());
			Assert.assertTrue(studChatsPage.getChatHistoryTxt());

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Validate Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Validate Send Message With Time Stamp", e);
		}

		logger.info("Ending of testChatHistoryWhenApplicationIsClosedByStudent method");
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
