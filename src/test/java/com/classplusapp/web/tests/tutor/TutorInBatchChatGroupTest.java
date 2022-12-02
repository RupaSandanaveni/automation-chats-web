package com.classplusapp.web.tests.tutor;

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
import com.classplusapp.web.pages.ClassplusLoginPage;
import com.classplusapp.web.pages.tutor.TutorConversationPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Tutor Conversation in Batch-Chat-Group")
@Feature("Batch Group Convewrsation")
public class TutorInBatchChatGroupTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver studentDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage studChatsPage = null;
	private ClassplusLoginPage loginPage = null;
	private TutorConversationPage tutorConversationPage = null;

	private static final Logger logger = Logger.getLogger(TutorInBatchChatGroupTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in BatchChatGroupTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorInBatchChatGroupTest);
		this.studentDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_TutorInBatchChatGroupTest);

		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.studChatsPage = new ChatsPage(this.studentDriver);
		this.loginPage = new ClassplusLoginPage(tutorDriver);
		this.tutorConversationPage = new TutorConversationPage(this.tutorDriver);
		
		super.testAllowStudyGroups(tutorChatsPage);

		logger.info("Ending of initClassplusSiteLogin method in BatchChatGroupTest");
	}

	@Test(priority = 1, description = "Verify UI of the already created batches groups and the groups in which added when land on chat screen.", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify UI of the already created batches groups and the groups in which added when land on chat screen.")
	@Story("Verify UI of the already created batches groups and the groups in which added when land on chat screen.")
	public void testUIOfCreatedBatchGroupByTutor() {
		logger.info("Starting of testUIOfAlreadyCreatedBatchGroupByFaculty method");

		try {
			// Student End
			tutorChatsPage.clickOnChatsTab();
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_BATCH));
			tutorChatsPage.clickOnBatchChatGroup(testDataProp.getProperty(SEARCH_FOR_BATCH));

			// Assertion for Batch label String LblStudentEndBatchGroupHeaderName =
			Assert.assertEquals(tutorChatsPage.getBatchGroupHeaderNameTxt(),
					expectedAssertionsProp.getProperty(BATCH_NAME_TXT));

			// Assertion for Student Groups tool tip
			assertTrue(tutorChatsPage.getStudentGroupsToolTip());

			// Assert for more option
			super.testMoreOptionsImage(tutorChatsPage);
			// Search Bar
			super.testSearchBarLabel(tutorChatsPage);

			// Assertion for Label Users
			Assert.assertEquals(tutorChatsPage.getMessageTxt(), expectedAssertionsProp.getProperty(LABEL_MESSAGES));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing UI of the already created batches groups and the groups in which added when land on chat screen. : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing UI of the already created batches groups and the groups in which added when land on chat screen.",
					e);
		}

		logger.info("Ending of testUIOfAlreadyCreatedBatchGroupByFaculty method");
	}

	@Test(priority = 2, description = "Verify Three dots Vertical Menu Options in Batch Group")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Three dots Vertical Menu Options in Batch Group")
	@Story("Verify Three dots Vertical Menu Options in Batch Group")
	public void testVerticalThreedotedMenu() {
		logger.info("Starting of testVerticalThreedotedMenu method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();

			// Asserting for all the labels of Menu Options
			super.testLabelClearConversation(tutorChatsPage);
			super.testLabelReportConversation(tutorChatsPage);
			super.testLabelBatchInformation(tutorChatsPage);
			super.testLabelTurnOnOffReplies(tutorChatsPage);
			super.testLabelMuteAndUnmuteConversation(tutorChatsPage);

			// this.loginPage.clickOutside();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing vertical three doted menu  : " + e.getMessage());
			logger.error("Error occured while testing vertical three doted menu ", e);
		}

		logger.info("Ending of testVerticalThreedotedMenu method");
	}

	@Test(priority = 3, description = "Verify Send Message With Blank Space By Tutor")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message With Blank Space By Tutor")
	@Story("Verify Send Message With Blank Space By Tutor")
	public void testSendMessageWithBlankSpaceByTutor() {
		logger.info("Starting of testSendMessageWithBlankSpaceByTutor method");

		try {
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertNotEquals(tutorChatsPage.getMessageTimeStampTxt(), this.getCurrentSystemTime());

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message With Blank Space \" : " + e.getMessage());
			logger.error("Error occured while testing test Send Message With Blank Space", e);
		}

		logger.info("Ending of testSendMessageWithBlankSpaceByTutor method");
	}

	@Test(priority = 4, description = "Verify Send Message using Enter Keyboard Button")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message using Enter Keyboard Button")
	@Story("Verify Send Message using Enter Keyboard Button")
	public void testSendMessageWithEnterKeyByTutor() {
		logger.info("Starting of testSendMessageWithEnterKeyByTutor method");

		try {
			tutorChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			tutorChatsPage.clickOnEnterKeyUsingKeyBoard();

		} catch (Exception e) {
			Assert.fail("Exception occured while Send Message With Enter Key\" : " + e.getMessage());
			logger.error("Error occured while Send Message With Enter Key", e);
		}

		logger.info("Ending of testSendMessageWithEnterKeyByTutor method");
	}

	@Test(priority = 5, description = "Verify Send Message with Tinestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with Tinestamp")
	@Story("Verify Send Message with Tinestamp")
	public void testValidateSendMessageWithTimeStamp() {
		logger.info("Starting of testValidateSendMessageWithTimeStamp method");

		try {
			tutorChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			tutorChatsPage.clickOnEnterKeyUsingKeyBoard();
			
			Assert.assertEquals(tutorChatsPage.getMessageTimeStampTxt(), this.getCurrentSystemTime());

			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Validate Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Validate Send Message With Time Stamp", e);
		}

		logger.info("Ending of testValidateSendMessageWithTimeStamp method");
	}

	@Test(priority = 6, description = "Verify Send Short And Long Message")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Short And Long Message")
	@Story("Verify Send Short And Long Message")
	public void testSendShortAndLongMessageByTutor() {
		logger.info("Starting of testSendShortAndLongMessageByTutor method");

		try {
			// sending long message
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			// Assert for text
			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			// sending long message
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_LONG_MESSAGE));
			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_LONG_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Short And Long Message\" : " + e.getMessage());
			logger.error("Error occured while testing  Send Short And Long Message", e);
		}

		logger.info("Ending of testSendShortAndLongMessageByTutor method");
	}

	@Test(priority = 7, description = "Verify Tutor can able to TurnOffOnReplies for a BatchChatGroup", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to TurnOffOnReplies for a BatchChatGroup")
	@Story("Verify TurnOffOnReplies for Batch-Chat-Group")
	public void testTurnOffOnRepliesByTutor() {
		logger.info("Starting of testTurnOffOnRepliesByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();
			if ((expectedAssertionsProp.getProperty(LABEL_TURN_OFF_REPLIES))
					.equals(tutorChatsPage.getTurnOffRepliesLabel())) {
				tutorChatsPage.clickOnTurnOffReplies();

				Assert.assertEquals(tutorChatsPage.getTurnedOffRepliesTxt(),
						expectedAssertionsProp.getProperty(TOAST_FOR_TURNED_OFF_TXT));

				Assert.assertEquals(tutorChatsPage.getTurnedOffRepliesInfoTxt(),
						expectedAssertionsProp.getProperty(TURN_OFF_REPLIES_IN_BATCH_CHAT_GROUP_INFO_TXT));

				studChatsPage.clickOnChatsTab();
				studChatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_BATCH);
				studChatsPage.clickOnBatchChatGroup(testDataProp.getProperty(SEARCH_FOR_BATCH));

				Assert.assertEquals(studChatsPage.getTurnedOffRepliesInfoTxt(),
						expectedAssertionsProp.getProperty(TURN_OFF_REPLIES_IN_BATCH_CHAT_GROUP_INFO_TXT));
			} else {
				tutorChatsPage.clickOnTurnOnReplies();

				Assert.assertEquals(tutorChatsPage.getTurnedOnRepliesTxt(),
						expectedAssertionsProp.getProperty(TOAST_FOR_TURNED_ON_TXT));

				super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

				super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			}

		} catch (Exception e) {
			Assert.fail("Exception occured while testing TurnOffOnReplies By Tutor : " + e.getMessage());
			logger.error("Error occured while testing TurnOffOnReplies By Tutor ", e);
		}

		logger.info("Ending of testTurnOffOnRepliesByTutor method");
	}

	@Test(priority = 8, description = "Verify Tutor can able to View Batch Information", groups = "sanity")
	@Description("Test Description:Verify Tutor can able to View Batch Information")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Tutor can able to View Batch Information")
	public void testBatchInformation() {
		logger.info("Starting of testBatchInformation method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();
			tutorConversationPage.clickOnBatchInformation();

			// Assertion for Batch label
			Assert.assertEquals(tutorConversationPage.getGroupNameInBatchInformation(),
					expectedAssertionsProp.getProperty(BATCH_NAME_TXT));

			// Assertion for Participant count label
			Assert.assertEquals(tutorChatsPage.getParticipantCountTxt(),
					tutorChatsPage.getListOfParticipantsCountText());

			tutorChatsPage.clickOnBackInGroupInformation();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing View Batch Group Info: " + e.getMessage());
			logger.error("Error occured while testing View Batch Group Info", e);
		}

		logger.info("Ending of testBatchInformation method");
	}

	@Test(priority = 9, description = "Verify Tutor can able to Mute and UnMute Conversation for a BatchChatGroup", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Mute and UnMute Conversation for a BatchChatGroup")
	@Story("Verify Mute and Unmute Conversation for a BatchChatGroup")
	public void testMuteOrUnmuteConversationByTutor() {
		logger.info("Starting of testMuteOrUnmuteConversationByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();

			super.testMuteUnmuteConversation(tutorChatsPage, testDataProp.getProperty(SEARCH_FOR_BATCH));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Tutor for a Batch Group : "
					+ e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Tutor for a Batch Group", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByTutor method");
	}

	@Test(priority = 10, description = "Verify Tutor can able to send message in Batch Group Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to send message in Batch Group Chat Page")
	@Story("Verify Send Message By Tutor in Batch Group")
	public void testSendMessageByTutorInBatchGroup() {
		logger.info("Starting of testSendMessageByTutorInBatchGroup method");

		try {
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			logger.error("Error occured while testing Send Message in Batch Group  By Tutor", e);
		}

		logger.info("Ending of testSendMessageByTutorInBatchGroup method");
	}

	@Test(priority = 11, description = "Verify Report Conversation in Batch Group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in Batch Group")
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

	@Test(priority = 12, description = "Verify Clear Conversation in BatchGroup Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in BatchGroup Page.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByTutor() {
		logger.info("Starting of testClearConversationByTutorAndStudent method");

		try {
			super.testClearConversation(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By Tutor and Student : " + e.getMessage());
			logger.error("Error occured while testing Clear Conversation  By Tutor and Studen", e);
		}

		logger.info("Ending of testClearConversationByTutorAndStudent method");
	}

	// @Test(priority = 7, description = "Verify User is in Online or Active")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify User is in Online or Active")
	@Story("Verify User is in Online or Active")
	public void testChatTabWithGreenDot() {
		logger.info("Starting of testChatTabWithGreenDot method");

		try {
			tutorChatsPage.clickOnBatchsTab();
			// Student sends messages to make active status of tutor
			studChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			studChatsPage.clickOnSendButton();

			// Assert for text
			Assert.assertEquals(studChatsPage.getSendMessageTxt(), testDataProp.getProperty(SEND_MESSAGE));

			tutorChatsPage.clickOnChatsTab();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Video Added In Batch\" : " + e.getMessage());
			logger.error("Error occured while testing Video Added In Batch", e);
		}

		logger.info("Ending of testChatTabWithGreenDot method");
	}

	// @Test(priority = 11, description = "Verify Tutor can able to TurnOnOffReplies
	// for a participant in Batch Information", invocationCount = 2)
	@Description("Test Description:Verify Tutor can able to TurnOnOffReplies for a participant in Batch Information")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Tutor can able to TurnOnOffReplies for a participant in Batch Information")
	public void testIndividualTurnOnOffRepliesInBatchGroup() {
		logger.info("Starting of testIndividualTurnOnOffRepliesInBatchGroup method");

		try {
			tutorChatsPage.clickOnIndividualMenuVerticalIcon(testDataProp.getProperty(STUDENT_RECEPIENT));

			if (tutorChatsPage.getTurnOffRepliesTxt().contains(" Turn off replies")) {
				tutorChatsPage.clickOnIndividualTurnOnOffReplies();

				String turnOffReplies = tutorChatsPage.getIndividualTurnedOnOffRepliesTxt();
				Assert.assertEquals(turnOffReplies,
						expectedAssertionsProp.getProperty(TOAST_FOR_INDIVIDUAL_TURNED_OFF_TXT));

				this.studChatsPage.refresh();

				String lblInfoStudentEnd = studChatsPage.getTurnedOffRepliesInfoTxt();
				Assert.assertEquals(lblInfoStudentEnd, expectedAssertionsProp.getProperty(VALIDATE_TURN_OFF_REPLIES));
			} else {
				tutorChatsPage.clickOnIndividualTurnOnOffReplies();

				String turnOnReplies = tutorChatsPage.getIndividualTurnedOnOffRepliesTxt();
				Assert.assertEquals(turnOnReplies,
						expectedAssertionsProp.getProperty(TOAST_FOR_INDIVIDUAL_TURNED_OFF_TXT));

				this.studChatsPage.refresh();

				super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			}

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Individual TurnOnOffReplies In Batch Group: " + e.getMessage());
			logger.error("Error occured while testing Individual TurnOnOffReplies In Batch Group", e);
		}

		logger.info("Ending of testIndividualTurnOnOffRepliesInBatchGroup method");
	}

	// @Test(priority = 19, description = "Verify Add Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document")
	@Story("Verify Add Document")
	public void testAddDocumentFileByTutorAndStudent() {
		logger.info("Starting of testAddDocumentFileByTutor method");

		try {
			super.testAddDocumentFile(tutorChatsPage);
			super.testAddDocumentFile(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentFileByTutor method");
	}

	// @Test(priority = 20, description = "Verify Add Image in Batch Group")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Image in Batch Group")
	@Story("Verify Add Image")
	public void testAddImageFileByTutorAndStudent() {
		logger.info("Starting of testAddImageFileByTutor method");

		try {
			super.testAddImageFile(tutorChatsPage);
			super.testAddImageFile(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Image File  : " + e.getMessage());
			logger.error("Error occured while testing Add Image File ", e);
		}

		logger.info("Ending of testAddImageFileByTutor method");
	}

	@Test(priority = 13, description = "Verify Tutor a can able to copy a message in a batch chat group ", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to copy a message in a batch chat group ")
	@Story("Verify Copy Message in Batch Chat Group")
	public void testCopyMessageByTutor() {
		logger.info("Starting of testCopyMessageByTutor method");

		try {
			super.testCopyMessage(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Copy Single Message By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Copy Message By Tutor", e);
		}

		logger.info("Ending of testCopyMessageByTutor method");
	}

	@Test(priority = 14, description = "Verify Tutor can able to delete single message in a batch chat group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to delete single message in a batch chat group")
	@Story("Verify Delete Single Message in Batch Chat Group")
	public void testDeleteSingleMessageByTutor() {
		logger.info("Starting of testDeleteSingleMessageByTutor method");

		try {
			super.testDeleteSingleMessage(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Delete Single Message By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Delete Single Message  By Tutor", e);
		}

		logger.info("Ending of testDeleteSingleMessageByTutor method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && studentDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(studentDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorInBatchChatGroupTest);
				this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_TutorInBatchChatGroupTest);

				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}
