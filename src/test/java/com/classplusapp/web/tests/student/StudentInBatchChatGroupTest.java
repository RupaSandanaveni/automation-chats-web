package com.classplusapp.web.tests.student;

import static com.classplusapp.chat.util.Constants.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.ClassplusLoginPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Student Conversation in Batch-Chat-Group")
@Feature("Batch Group Chat")
public class StudentInBatchChatGroupTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver studentDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage studChatsPage = null;
	private ClassplusLoginPage loginPage = null;

	private static final Logger logger = Logger.getLogger(StudentInBatchChatGroupTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in BatchChatGroupTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_StudentInBatchChatGroupTest);
		this.studentDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_StudentInBatchChatGroupTest);

		this.loginPage = new ClassplusLoginPage(tutorDriver);
		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.studChatsPage = new ChatsPage(this.studentDriver);

		logger.info("Ending of initClassplusSiteLogin method in BatchChatGroupTest");
	}

	@Test(priority = 1, description = "Verify UI of the already created batches groups and the groups in which added when land on chat screen.", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify UI of the already created batches groups and the groups in which added when land on chat screen.")
	@Story("Verify UI of the already created batches groups and the groups in which added when land on chat screen.")
	public void testUIOfTutorCreatedBatchGroupByStudent() {
		logger.info("Starting of testUIOfTutorCreatedBatchGroupByStudent method");

		try {
			// Student End
			super.testChatsTab(studChatsPage);
			studChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(SEARCH_FOR_BATCH));
			studChatsPage.clickOnBatchChatGroup(testDataProp.getProperty(SEARCH_FOR_BATCH));

			// Assertion for Batch label String LblStudentEndBatchGroupHeaderName =
			Assert.assertEquals(studChatsPage.getBatchGroupHeaderNameTxt(),
					expectedAssertionsProp.getProperty(BATCH_NAME_TXT));

			// Assert for more option
			super.testMoreOptionsImage(studChatsPage);
			// Search Bar
			super.testSearchBarLabelAtStudentEnd(studChatsPage);

			// Assertion for Label Users
			Assert.assertEquals(studChatsPage.getMessageTxt(), expectedAssertionsProp.getProperty(LABEL_MESSAGES));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing UI of the already created batches groups and the groups in which added when land on chat screen. : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing UI of the already created batches groups and the groups in which added when land on chat screen.",
					e);
		}

		logger.info("Ending of testUIOfTutorCreatedBatchGroupByStudent method");
	}

	//@Test(priority = 2, description = "Verify in chat search field by name or number but by putting space at the start by Student")
	@Description("Test Description:Verify in chat search field by name or number but by putting space at the start by Student")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify in chat search field by name or number but by putting space at the start by Student")
	public void testSearchInChatByPuttingSpaceAtStart() {
		logger.info("Starting of testSearchInChatByPuttingSpaceAtStart method");

		try {
			studChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(studChatsPage.getNoConversationTxt(),
					expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));

			studChatsPage.clickOnClearQueryAtStudentEnd();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search in Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Search in Chats Tab", e);
		}

		logger.info("Ending of testSearchInChatByPuttingSpaceAtStart method");
	}

	@Test(priority = 2, description = "Verify Send Message With Blank Space By Student")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message With Blank Space By Student")
	@Story("Verify Send Message With Blank Space")
	public void testSendMessageWithBlankSpaceByStudent() {
		logger.info("Starting of testSendMessageWithBlankSpaceByStudent method");

		try {
			// Student End
			studChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));
			studChatsPage.clickOnSendButton();

			Assert.assertNotEquals(studChatsPage.getMessageTimeStampTxt(), this.getCurrentSystemTime());

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message With Blank Space \" : " + e.getMessage());
			logger.error("Error occured while testing test Send Message With Blank Space", e);
		}

		logger.info("Ending of testSendMessageWithBlankSpaceByStudent method");
	}

	@Test(priority = 3, description = "Verify Student can able to send message in Batch Group Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Student can able to send message in Batch Group Chat Page")
	@Story("Verify Send Message By Student in Batch Group")
	public void testSendMessageAndTimeStampByStudentInBatchGroup() {
		logger.info("Starting of testSendMessageAndTimeStampByStudentInBatchGroup method");

		try {
			super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			Assert.assertEquals(studChatsPage.getMessageTimeStampTxt(), this.getCurrentSystemTime());

			// Student End
			super.testChatsTab(tutorChatsPage);
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_BATCH));
			tutorChatsPage.clickOnBatchChatGroup(testDataProp.getProperty(SEARCH_FOR_BATCH));

			super.testValidateReceivedMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message in Batch Group  By Student " + e.getMessage());
			logger.error("Error occured while testing Send Message in Batch Group  By Student", e);
		}

		logger.info("Ending of testSendMessageAndTimeStampByStudentInBatchGroup method");
	}

	@Test(priority = 4, description = "Verify Send Short And Long Message")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Short And Long Message")
	@Story("Verify Send Short And Long Message")
	public void testSendShortAndLongMessageByStudent() {
		logger.info("Starting of testSendShortAndLongMessageByStudent method");

		try {
			// sending short message
			studChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			studChatsPage.clickOnSendButton();

			// Assert for text
			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			// sending long message
			studChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_LONG_MESSAGE));
			studChatsPage.clickOnSendButton();

			// Assert for text
			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_LONG_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Short And Long Message\" : " + e.getMessage());
			logger.error("Error occured while testing  Send Short And Long Message", e);
		}

		logger.info("Ending of testSendShortAndLongMessageByStudent method");
	}
	
	@Test(priority = 5, description = "Verify Student can able to Mute and UnMute Conversation for a BatchChatGroup", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Student can able to Mute and UnMute Conversation for a BatchChatGroup")
	@Story("Verify Mute and Unmute Conversation for a BatchChatGroup")
	public void testMuteOrUnmuteConversationByStudent() {
		logger.info("Starting of testMuteOrUnmuteConversationByStudent method");

		try {
			studChatsPage.clickOnMenuVerticalIcon();

			super.testMuteUnmuteConversation(studChatsPage, testDataProp.getProperty(SEARCH_FOR_BATCH));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Student for a Batch Group : "
					+ e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Student for a Batch Group", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByStudent method");
	}
	
	@Test(priority = 6, description = "Verify Report Conversation in Batch Group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in Batch Group")
	@Story("Verify Report Conversation")
	public void testReportConversationByStudent() {
		logger.info("Starting of testReportConversationByStudent method");

		try {
			super.testReportConversation(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By Student : "
					+ e.getMessage());
			logger.error("Error occured while testing Repor Conversation By Student", e);
		}

		logger.info("Ending of testReportConversationByStudent method");
	}
	
	@Test(priority = 7, description = "Verify Student can able to copy a message in a batch group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Student can able to copy a message in a batch group")
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

	@Test(priority = 8, description = "Verify Clear Conversation in Batch Group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Batch Group.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByStudent() {
		logger.info("Starting of testClearConversationByStudent method");

		try {
			super.testClearConversation(studChatsPage);
		
		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By Student: "
					+ e.getMessage());
			logger.error("Error occured while testing Clear Conversation  By Student", e);
		}

		logger.info("Ending of testClearConversationByStudent method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && studentDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(studentDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_StudentInBatchChatGroupTest);
				this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_StudentInBatchChatGroupTest);

				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}