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
public class TutorToStudentConversationTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver studentDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage studChatsPage = null;
	private TutorConversationPage tutorConversationPage = null;
	private StudentConversationPage studentConversationPage = null;

	Long SystemTimeAtTutorEnd, SystemTimeAtStudentEnd;

	private static final Logger logger = Logger.getLogger(TutorToStudentConversationTest.class.getName());

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

	@Test(priority = 1, description = "Verify Tutor and Student can able to click Chats Tab " , groups ="sanity")
	@Description("Test Description:Verify Tutor and Student can able to click Chats Tab")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Tutor and Student can able to click Chats Tab")
	public void testChatsTabAtTutorAndStudentEnd() {
		logger.info("Starting of testChatsTabAtTutorAndStudentEnd method");

		try {
			// Tutor End
			super.testChatsTab(tutorChatsPage);
			// Student End
			super.testChatsTab(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Chats Tab", e);
		}

		logger.info("Ending of testChatsTabAtTutorAndStudentEnd method");
	}

	@Test(priority = 2, description = "Verify that time/date for chats is in decending order on LHS")
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

	@Test(priority = 3, description = "Verify Start a conversation in moreOptions",groups="sanity")
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

	@Test(priority = 4, description = "Verify Filters for Students",groups="sanity")
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

	@Test(priority = 5, description = "Verify Tutor can able to send message in Chat Page",groups="sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to send message in Chat Page")
	@Story("Verify Send Message By Tutor")
	public void testSendMessageByTutor() {
		logger.info("Starting of testSendMessageByTutor method");

		try {
			super.testSearchRecepientForStudent(tutorChatsPage);
			super.testSelectStudentRecepient(tutorChatsPage, tutorConversationPage);
			super.testSendMessage(tutorChatsPage,tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message By Tutor: " + e.getMessage());
			logger.error("Error occured while testing Send Message By Tutor", e);
		}

		logger.info("Ending of testSendMessageByTutor method");
	}

	@Test(priority = 6, description = "Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
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

	@Test(priority = 7, description = "Verify Student can able to send message in Chat Page",groups="sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Student can able to send message in Chat Page")
	@Story("Verify Send Message By Student")
	public void testSendMessageUsingKeyEnterByStudent() {
		logger.info("Starting of testSendMessageUsingKeyEnterByStudent method");

		try {
			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);
			super.testIsDisplayedTurnOffRepliesInfo(tutorChatsPage,studChatsPage);
			studChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			studChatsPage.clickOnEnterKeyUsingKeyBoard();

			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			super.testSmoothScrollIng(studChatsPage);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Send Message with Enter key by Student : "
							+ e.getMessage());
			logger.error("Error occured while testing Send Message with Enter key by Student", e);
		}

		logger.info("Ending of testSendMessageUsingKeyEnterByStudent method");
	}

	@Test(priority = 8, description = "Verify Add Document and File Extension")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document and File Extension")
	@Story("Verify Add Document and File Extension")
	public void testAddDocumentAndFileExtensionSentByTutor() {
				
		logger.info("Starting of testAddDocumentAndFileExtensionSentByTutor method");

		try {
			tutorChatsPage.clickOnChatsTab();
			//super.testSearchBar(tutorChatsPage);
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

	@Test(priority = 9, description = "Verify that how much time is it taking to send a message from Tutor to Student ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Tutor to Student")
	@Story("Verify that how much time is it taking to send a message from Tutor to Student")
	public void testConversationTimePeriodForTutorToStudent() {
		logger.info("Starting of testConversationTimePeriodForTutorToStudent method");

		try {
			super.testSendMessage(tutorChatsPage,tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			SystemTimeAtTutorEnd = super.getConversationTimePeriod();
			
			super.testValidateSendMessage(tutorChatsPage,  testDataProp.getProperty(SEND_MESSAGE));

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

	@Test(priority = 10, description = "Verify how long a chat can be saved at parent end")
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

	@Test(priority = 11, description = "Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
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

	@Test(priority = 12, description = "Verify that how many number of words or characters can be send at a time.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify that how many number of words or characters can be send at a time.")
	@Story("Verify that how many number of words or characters can be send at a time.")
	public void testNumberOfCharactersWhileSendingMessageByStudent() {
		logger.info("Starting of testNumberOfCharactersWhileSendingMessageByTutor method");

		try {
			// Student sent message to Tutor
			super.testSendMessage(tutorChatsPage,studChatsPage, testDataProp.getProperty(SEND_MESSAGE));
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

	@Test(priority = 13, description = "Verify that user is able to see YESTERDAY if conversation happened on the previous day in the chat list on LHS")
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

	@Test(priority = 14, description = "Verify that user is able to see the date if conversation happened long back in the chat list on LHS")
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

	@Test(priority = 15, description = "Verify Send Message With Different Data In Chat Box By Student", dataProvider = "testDataForChatBox")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Send Message With Different Data In Chat Box By Student")
	@Story("Verify Send Message With Different Data In Chat Box By Student")
	public void testSendMessageWithDifferentDataInChatBoxByStudent(String testDataForChatBox) {
		logger.info("Starting of testSendMessageWithDifferentDataInChatBoxByStudent method");

		try {
			studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);
			super.testSelectTutorRecepient(studChatsPage);
			
			super.testSendMessage(tutorChatsPage,studChatsPage, testDataForChatBox);
			super.testValidateSendMessage(studChatsPage, testDataForChatBox);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing with different data sent  By Student : " + e.getMessage());
			logger.error("Error occured while testing with different data sent By Student", e);
		}

		logger.info("Ending of testSendMessageWithDifferentDataInChatBoxByStudent method");
	}

	@Test(priority = 16, description = "Verify that user is able to set its profile pic from account profile, which is visible to other users or not.")
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

	@Parameters({ "browser" })
	@Test(priority = 17, description = "Verify Student are waiting for you in Chat Page")
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
			super.testSendMessage(tutorChatsPage,studChatsPage,  testDataProp.getProperty(SEND_MESSAGE));
			this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
					WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorToStudentConversationTest);
			this.tutorChatsPage = new ChatsPage(this.tutorDriver);
			this.tutorConversationPage = new TutorConversationPage(this.tutorDriver);

			super.testChatsTab(tutorChatsPage);

			// Assertion for Students are waiting for you header
			Assert.assertEquals(tutorConversationPage.getUnseenHeaderTxt(),
					expectedAssertionsProp.getProperty(LABEL_STUDENTS_ARE_WAITING_FOR_YOU));

		/*	Assert.assertEquals(tutorConversationPage.getWaitingStudentStartingLetterTxt(),
					expectedAssertionsProp.getProperty(LABEL_STARTING_LETTER_OF_A_NAME));*/

			tutorConversationPage.clickOnWaitingStudent();
			
			Assert.assertEquals(tutorChatsPage.getStudentHeaderNameTxt(),
					expectedAssertionsProp.getProperty(LABEL_STUDENT1_NAME_TXT));
			
			super.testSendMessage(tutorChatsPage,tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			logger.error("Error occured while testing Student are waiting for you in Chat Page ", e);
		}

		logger.info("Ending of testStudentsAreWaitingForYouAtTutorEnd method");
	}

	@Test(priority = 18, description = "Verify Download And View Document")
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

	@Test(priority = 19, description = "Verify Select With Twenty Different Images")
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

	@Test(priority = 20, description = "Verify Selecting with Single Image")
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

	@Test(priority = 21, description = "Verify Selecting with Same Image Multiple Times")
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

	@Test(priority = 22, description = "Verify Add Document and File extension")
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

	@Test(priority = 23, description = "Verify that user is able to send any emoticons or not.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that user is able to send any emoticons or not.")
	@Story("Verify that user is able to send any emoticons or not.")
	public void testSendEmoticonsByStudent() {
		logger.info("Starting of testSendEmoticonsByStudent method");

		try {
			studChatsPage.setEmoticons("😄");

		//	super.testValidateSendMessage(studChatsPage, "ðŸ˜„");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that user is able to send any emoticons or not: "
					+ e.getMessage());
			logger.error("Error occured while testing that user is able to send any emoticons or not. ", e);
		}

		logger.info("Ending of testSendEmoticonsByStudent method");
	}

	@Test(priority = 24, description = "Verify for Tutor Is With Active GreenColor Displayed in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify for Tutor Is With Active Green Color Displayed in Chat Page")
	@Story("Verify for Tutor Is With Active Green Color Displayed in Chat page")
	public void testTutorWithActiveItemGreedDotByStudent() {
		logger.info("Starting of testTutorWithActiveItemGreedDotByStudent method");

		try {
			Assert.assertTrue(
					studChatsPage.isDisplayedGreenColor(SEARCH_FOR_TUTOR_RECEPIENT));

			/*this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST);
			// every time tutor need to log out then only these case will be passed
			Assert.assertFalse(
					studChatsPage.isDisplayedGreenColor(testDataProp.getProperty(SEARCH_FOR_TUTOR_RECEPIENT)));

			this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
					WEB_DRIVER.TUTOR_DRIVER_TEST);
			this.tutorChatsPage = new ChatsPage(this.tutorDriver);*/

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Tutor With Active Item Greed Dot By Student: " + e.getMessage());
			logger.error("Error occured while testing Tutor With Active Item Greed Dot By Student", e);
		}

		logger.info("Ending of testTutorWithActiveItemGreedDotByStudent method");
	}

	@Test(priority = 25, description = "Verify that how much time is it taking to send a message from Student to Tutor ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Student to Tutor")
	@Story("Verify that how much time is it taking to send a message from Student to Tutor")
	public void testConversationTimePeriodForStudentToTutor() {
		logger.info("Starting of testConversationTimePeriodForStudentToTutor method");

		try {
		//	super.testSelectTutorRecepient(studChatsPage);
			super.testSendMessage(tutorChatsPage,studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			SystemTimeAtTutorEnd = super.getConversationTimePeriod();

			super.testValidateReceivedMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
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

	@Test(priority = 26, description = "Verify how long a chat can be saved at student end")
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

	@Test(priority = 27, description = "Verify that time/date for chats is in decending order on LHS")
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

	@Test(priority = 28, description = "Verify clicking on profile name should not navigate to profile screen ")
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

	@Test(priority = 29, description = "Verify Send Message with Timestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with Timestamp")
	@Story("Verify Send Message with Timestamp")
	public void testSentTimeStampWithLHSTimestampByStudent() {
		logger.info("Starting of testSentTimeStampWithLHSTimestampByStudent method");

		try {
			super.testSendMessage(tutorChatsPage,studChatsPage, testDataProp.getProperty(SEND_MESSAGE));
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

	@Parameters({ "browser" })
	@Test(priority = 30, description = "Verify Chat History When Application Is Closed By Student")
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

	@Test(priority = 31, description = "Verify Student Talk to Tutor from Store Tab")
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

	@Test(priority = 32, description = "Verify Add Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document")
	@Story("Verify Add Document")
	public void testAddDocumentFileByTutorAndStudent() {
		logger.info("Starting of testAddDocumentFileByTutor method");
		try {
			super.testAddDocumentFile(tutorChatsPage);
			
			studChatsPage.clickOnChatsTab();
			super.testSearchRecepientForTutorByStudent(studChatsPage);
			super.testSelectTutorRecepient(studChatsPage);
			super.testAddDocumentFile(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentFileByTutor method");
	}

	@Test(priority = 33, description = "Verify Tutor can able to Mute and UnMute Conversation for a Student Conversation", invocationCount = 2, groups="sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Mute and UnMute Conversation for a Student Conversation")
	@Story("Verify Mute and Unmute Conversation At Tutor End")
	public void testMuteOrUnmuteConversationByTutor() {
		logger.info("Starting of testMuteOrUnmuteConversationByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();

		//	super.testMuteUnmuteConversation(tutorChatsPage,tutorChatsPage, testDataProp.getProperty(STUDENT_RECEPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Tutor", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByTutor method");
	}

	@Test(priority = 34, description = "Verify Student can able to Mute Conversation for a Tutor Conversation", invocationCount = 2 ,groups="sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Student can able to Mute Conversation for a Tutor Conversation")
	@Story("Verify Mute and Unmute Conversation At Student End")
	public void testMuteOrUnmuteConversationByStudent() {
		logger.info("Starting of testMuteOrUnmuteConversationByStudent method");
				
		
		try {
			studChatsPage.clickOnMenuVerticalIcon();

		//	super.testMuteUnmuteConversation(tutorChatsPage,studChatsPage, SEARCH_FOR_TUTOR_RECEPIENT);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Student : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Student", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByStudent method");
	}

	@Test(priority = 35, description = "Verify Tutor can able to TurnOffOnReplies for a Student Conversation", invocationCount = 2,groups="sanity")
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

				super.testSendMessage(tutorChatsPage,tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			}

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Tutor", e);
		}

		logger.info("Ending of testTurnOffOnRepliesByTutor method");
	}

	@Test(priority = 36, description = "Verify Report Conversation in Chat Page",groups="sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in  Chat Page")
	@Story("Verify Report Conversation")
	public void testReportConversationByTutorAndStudent() {
		logger.info("Starting of testReportConversationByTutorAndStudent method");

		try {
			super.testReportConversation(tutorChatsPage);
			super.testReportConversation(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By Tutor and Student : " + e.getMessage());
			logger.error("Error occured while testing Repor Conversation By Tutor and Student", e);
		}

		logger.info("Ending of testReportConversationByTutorAndStudent method");
	}

			
	@Test(priority = 37, description = "Verify Clear Conversation in Chat Page",groups="sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Chat Page.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByTutorAndStudent() {
		logger.info("Starting of testClearConversationByTutorAndStudent method");

		try {
			super.testClearConversation(tutorChatsPage);
			super.testClearConversation(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By Tutor and Student : " + e.getMessage());
			logger.error("Error occured while testing Clear Conversation  By Tutor and Studen", e);
		}

		logger.info("Ending of testClearConversationByTutorAndStudent method");
	}

	@Test(priority = 38, description = "Verify Tutor can able to copy a message in a chat body",groups="sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to copy a message in a chat body")
	@Story("Verify Copy Message")
	public void testCopyMessageByTutorAndStudent() {
		logger.info("Starting of testCopyMessageByTutorAndStudent method");

		try {
			super.testCopyMessage(tutorChatsPage);
			super.testCopyMessage(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Copy Single Message By Tutor and Student : " + e.getMessage());
			logger.error("Error occured while testing Copy Message By Tutor and Student", e);
		}

		logger.info("Ending of testCopyMessageByTutorAndStudent method");
	}

	@Test(priority = 39, description = "Verify Tutor can able to delete single message in a chat body",groups="sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to delete single message in a chat body")
	@Story("Verify Delete Single Message")
	public void testDeleteSingleMessageByTutorAndStudent() {
		logger.info("Starting of testDeleteSingleMessageByTutor method");

		try {
			super.testDeleteSingleMessage(tutorChatsPage);
			super.testDeleteSingleMessage(studChatsPage);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Delete Single Message By Tutor and Student : " + e.getMessage());
			logger.error("Error occured while testing Delete Single Message  By Tutor and Student", e);
		}

		logger.info("Ending of testDeleteSingleMessageByTutorAndStudent method");
	}

	@Test(priority = 40, description = "Verify Tutor can able to navigate from Batch Chat Profile",groups="sanity")
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

			super.testSendMessage(tutorChatsPage,tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			Assert.assertEquals(tutorConversationPage.getChatsLabel(), expectedAssertionsProp.getProperty(CHATS_LABEL));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing NavigateBatchProfileChat  : " + e.getMessage());
			logger.error("Error occured while NavigateBatchProfileChat", e);
		}

		logger.info("Ending of testNavigateBatchProfileChat method");
	}

	@Test(priority = 41, description = "Verify Tutor can able to navigate from Batch Chat Profile",groups="sanity")
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
			super.testSendMessage(tutorChatsPage,studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing NavigateAssignmentsTalkToTutorByStudent : " + e.getMessage());
			logger.error("Error occured while testing NavigateAssignmentsTalkToTutorByStudent", e);
		}

		logger.info("Ending of testNavigateBatchProfileChat method");
	}

	@AfterClass(groups="sanity")
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
