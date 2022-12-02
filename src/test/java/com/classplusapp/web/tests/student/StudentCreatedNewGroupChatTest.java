package com.classplusapp.web.tests.student;

import static com.classplusapp.chat.util.Constants.*;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.classplusapp.chat.util.Constants;
import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.student.StudentConversationPage;
import com.classplusapp.web.tests.CommonChatConversationTest;
import com.classplusapp.web.tests.BaseClassplusAutomationTest.WEB_DRIVER;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Student Created New group chat")
@Feature("New group chat ")
public class StudentCreatedNewGroupChatTest extends CommonChatConversationTest {
	private WebDriver studentOneDriver = null;
	private WebDriver studentTwoDriver = null;
	private WebDriver tutorDriver = null;
	private ChatsPage studentOneChatsPage = null;
	private ChatsPage studentTwoChatsPage = null;
	private ChatsPage tutChatsPage = null;
	private StudentConversationPage studentOneConversationPage = null;
	private StudentConversationPage studentTwoConversationPage = null;
	Long SystemTimeAtStudentOneEnd, SystemTimeAtStudentTwoEnd;

	private static final Logger logger = Logger.getLogger(StudentCreatedNewGroupChatTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in StudentCreatedNewGroupChatTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest);
		this.tutChatsPage = new ChatsPage(this.tutorDriver);
		this.studentOneDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest);
		this.studentOneChatsPage = new ChatsPage(this.studentOneDriver);
		this.studentTwoDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTTWO_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_TWO_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest);

		this.studentTwoChatsPage = new ChatsPage(this.studentTwoDriver);
		this.studentOneConversationPage = new StudentConversationPage(this.studentOneDriver);
		this.studentTwoConversationPage = new StudentConversationPage(this.studentTwoDriver);
		
		super.testAllowStudyGroups(tutChatsPage);

		logger.info("Ending of initClassplusSiteLogin method in StudentCreatedNewGroupChatTest");
	}
	
	@Test(priority = 1, description = "Verify NewGroupChat By StudentOne", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify NewGroupChat By StudentOne")
	@Story("Verify NewGroupChat By StudentOne")
	public void testNewGroupChatByStudentOne() {
		logger.info("Starting of testNewGroupChatByStudentOne method");

		try {
			studentOneChatsPage.clickOnChatsTab();
			studentOneChatsPage.clickOnImgMoreOptions();

			assertTrue(this.studentOneChatsPage.getNewGroupChatTxt());

			studentOneChatsPage.clickOnNewGroupChat();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing New group chat:" + e.getMessage());
			logger.error("Error occured while testing New group chat", e);
		}

		logger.info("Ending of testNewGroupChatByStudentOne method");
	}

	@Test(priority = 2, description = "Verify Search with Invalid Queries")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search with Invalid Queries")
	@Story("Verify Search with Invalid Queries")
	public void testSearchWithInvalidQuery() {
		logger.info("Starting of testSearchWithInvalidQuery method");

		try {
			studentOneChatsPage
					.clickOnSearchRecipientsForStudent(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			String lblErrorFetchingInConveration = studentOneChatsPage.getErrorFetchingConversationTxt();
			Assert.assertEquals(lblErrorFetchingInConveration,
					expectedAssertionsProp.getProperty(ERROR_IN_FETCHING_ONVERSATION_TXT));

			studentOneChatsPage
					.clickOnSearchRecipientsForStudent(testDataProp.getProperty(SEND_MESSAGE_WITH_INVALID_QUERY));

			String lblItsEmptyOUtHere = studentOneChatsPage.getItsEmptyOutHereTxt();
			Assert.assertEquals(lblItsEmptyOUtHere, expectedAssertionsProp.getProperty(ITS_EMPTY_OUT_HERE_TXT));

			// student1ChatsPage.clickOnClearQueryAtStudentEnd();

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Search With Invalid Query In New group chat : " + e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In New group chat ", e);
		}

		logger.info("Ending of testSearchWithInvalidQuery method");
	}

	@Test(priority = 3, description = "Verify Whether New Group Can Be Create Without Any Participants Or Not")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Whether New Group Can Be Created Without Any Participants Or Not")
	@Story("Verify Whether New Group Can Be Created Without Any Participants Or Not")
	public void testCreateGroupWithoutParticipantsByStudent() {
		logger.info("Starting of testCreateGroupWithoutParticipantsByStudent method");

		try {
			assertTrue(this.studentOneChatsPage.isDisplayedGroupNameTxt());
			studentOneChatsPage.setGroupName(testDataProp.getProperty(Constants.STUDENT_CREATED_GROUP_NAME));

			assertTrue(this.studentOneChatsPage.isDisplayedCreateGroupButton());

			studentOneChatsPage.clickOnCreateGroupButton();

			String lblWeNeedParticipantsTxt = studentOneChatsPage.getWeNeedParticipantsTxt();// change method name
			Assert.assertEquals(lblWeNeedParticipantsTxt,
					expectedAssertionsProp.getProperty(LABEL_WE_NEED_PARTICIPANTS_TEXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing create group without participants : " + e.getMessage());
			logger.error("Error occured while testing create group without participants", e);
		}

		logger.info("Ending of testCreateGroupWithoutParticipantsByStudent method");
	}

	@Test(priority = 4, description = "Verify With No Filters")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Verify With No Filters")
	@Story("Verify With No Filters")
	public void testWithNoFilterOption() {
		logger.info("Starting of testWithNoFilterOption method");

		try {
			Assert.assertFalse(studentOneChatsPage.getImgFilter());

		} catch (Exception e) {
			Assert.fail("Exception occured while testing With No Filters : " + e.getMessage());
			logger.error("Error occured while testing With No Filters ", e);
		}

		logger.info("Ending of testWithNoFilterOption method");
	}

	@Test(priority = 5, description = "Verify selected list green dot or not if online in New group chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify selected list  green dot or not if online in  New group chat Pagee")
	@Story("Verify selected list  green dot or not if online in New group chat Page")
	public void testGreenDotInGroupChatByStudentOne() {
		logger.info("Starting of testGreenDotInGroupChatByStudentOne method");

		try {
			Assert.assertFalse(studentOneChatsPage.IsDisplayedNoGreenColor());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Tutor With Active Item Greed Dot By Parent: " + e.getMessage());
			logger.error("Error occured while testing Tutor With Active Item Greed Dot By Parent", e);
		}

		logger.info("Ending of testGreenDotInGroupChatByStudentOne method");
	}

	@Test(priority = 6, description = "Verify Create NewGroupChat By StudentOne", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Create NewGroupChat By StudentOne")
	@Story("Verify Create NewGroupChat By StudentOne")
	public void testCreateNewGroupChatByStudentOne() {
		logger.info("Starting of testCreateNewGroupChatByStudentOne method");

		try {
			// student1ChatsPage.clickOnChatsTab();
			// student1ChatsPage.clickOnImgMoreOptions();
			// student1ChatsPage.clickOnNewGroupChat();

			super.testCreateNewGroupChatByStudent(studentOneChatsPage, studentOneConversationPage,
					"StudentCreatedGroup");

			// student1ChatsPage.clickOnSearchRecipients(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));
			// studentOneConversationPage
			// .clickOnStudentCreatedGroupName(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing New group chat: " + e.getMessage());
			logger.error("Error occured while testing New group chat", e);
		}

		logger.info("Ending of testCreateNewGroupChatByStudentOne method");
	}

	@Test(priority = 8, description = "Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.")
	@Story("Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.")
	public void testMultipleUsersConversation() {
		logger.info("Starting of testMultipleUsersConversation method");

		try {
			//testSearchGroupNameByStudentTwo
			studentTwoChatsPage.clickOnChatsTab();
			studentTwoChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));
			studentTwoConversationPage
					.clickOnStudentCreatedGroupName(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			
			super.testSendMessage(studentOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateReceivedMessage(studentTwoChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			super.testSendMessage(studentTwoChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateReceivedMessage(studentOneChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Multiple Users Conversation\" : " + e.getMessage());
			logger.error("Error occured while testing Multiple Users Conversation", e);
		}

		logger.info("Ending of testMultipleUsersConversation method");
	}

	@Test(priority = 9, description = "Verify Three dots Vertical Menu Options")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Three dots Vertical Menu Options.")
	@Story("Verify Three dots Vertical Menu Options")
	public void testVerticalThreedotedMenuByStudentOne() {
		logger.info("Starting of testVerticalThreedotedMenuByStudentOne method");

		try {
			super.testSendMessage(studentTwoChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			studentOneChatsPage.clickOnMenuVerticalIcon();

			super.testLabelClearConversation(studentOneChatsPage);
			super.testLabelReportConversation(studentOneChatsPage);
			super.testLabelGroupInformation(studentOneChatsPage);
			super.testLabelMuteAndUnmuteConversation(studentOneChatsPage);
			super.testLabelDeleteGroup(studentOneChatsPage);
			super.testLabelTurnOnOffReplies(studentOneChatsPage);

			this.studentOneChatsPage.clickOutsideInUserPage();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Vertical Three doted Menu By StudentOne" + e.getMessage());
			logger.error("Error occured while testing Vertical Three doted Menu By StudentOne", e);
		}

		logger.info("Ending of testVerticalThreedotedMenuByStudentOne method");
	}

	@Test(priority = 10, description = "Verify whether 'Admin' in green colour is mentioned or not for the one who created the group")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify whether 'Admin' in green colour is mentioned or not for the one who created the group.")
	@Story("Verify whether 'Admin' in green colour is mentioned or not for the one who created the group")
	public void testAdminTagForCreatedGroupByStudentOne() {
		logger.info("Starting of testAdminTagForCreatedGroupByStudentOne method");

		try {
			studentOneChatsPage.clickOnMenuVerticalIcon();
			studentOneChatsPage.clickOnGroupInformation();

			Assert.assertEquals(studentOneConversationPage.getAdminTag(),
					expectedAssertionsProp.getProperty(ADMIN_TAG));
			Assert.assertEquals(studentOneConversationPage.isGreenColorDisplayed(),
					expectedAssertionsProp.getProperty(COLOR_FOR_ADMIN_TAG));

			studentOneChatsPage.clickOnBackInGroupInformation();

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing 'Admin' in green colour is mentioned or not for the one who created the group"
							+ e.getMessage());
			logger.error(
					"Error occured while testing 'Admin' in green colour is mentioned or not for the one who created the group",
					e);
		}

		logger.info("Ending of testAdminTagForCreatedGroupByStudentOne method");
	}

	@Test(priority = 11, description = "Verify that how much time is it taking to send a message from StudentOne to StudentTwo")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from StudentOne to StudentTwor")
	@Story("Verify that how much time is it taking to send a message from StudentOne to StudentTwo")
	public void testConversationTimePeriodForStudentOneToStudentTwo() {
		logger.info("Starting of testConversationTimePeriodForStudentOneToStudentTwo method");

		try {
			super.testSendMessage(studentOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			SystemTimeAtStudentOneEnd = super.getConversationTimePeriod();

			super.testValidateReceivedMessage(studentTwoChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			String systemTime = this.getCurrentSystemTime();

			String msgTimeStamp = studentOneChatsPage.getMessageTimeStampTxt();
			logger.debug(msgTimeStamp);
			Assert.assertEquals(msgTimeStamp, systemTime);

			SystemTimeAtStudentTwoEnd = super.getConversationTimePeriod();

			Long TimeDifference = SystemTimeAtStudentOneEnd - SystemTimeAtStudentTwoEnd;
			logger.debug(TimeDifference);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from StudentOne to StudentTwo"
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from StudentOne to StudentTwo",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForStudentOneToStudentTwo method");
	}

	@Test(priority = 12, description = "Verify how long a chat can be saved")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify how long a chat can be saved")
	@Story("Verify how long a chat can be saved")
	public void testHowLongChatSavedAtStudentOneEnd() {
		logger.info("Starting of testHowLongChatSavedAtStudentOneEnd method");

		try {
			super.testHowlongChatSaved(studentOneChatsPage);
			logger.debug("Last chat was on :" + testHowlongChatSaved(studentOneChatsPage) + "Done");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing how long a chat saved" + e.getMessage());
			logger.error("Error occured while testing how long a chat saved", e);
		}

		logger.info("Ending of testHowLongChatSavedAtStudentOneEnd method");
	}

	@Test(priority = 13, description = "Verify Student_One can able to TurnOffOnReplies for a Student created group", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Student_One can able to TurnOffOnReplies for a Student created group")
	@Story("Verify Student_One can able to TurnOffOnReplies for a Student created group")
	public void testTurnOffOnRepliesByStudentOne() {
		logger.info("Starting of testTurnOffOnRepliesByStudentOne method");

		try {
			studentOneChatsPage.clickOnMenuVerticalIcon();

			if (studentOneChatsPage.isDisplayedImgTurnOffRepliesTxt()) {
				studentOneChatsPage.clickOnTurnOffReplies();

				String turnOffReplies = studentOneChatsPage.getTurnedOffRepliesTxt();
				Assert.assertEquals(turnOffReplies, expectedAssertionsProp.getProperty(TOAST_FOR_TURNED_OFF_TXT));

				String lblInfoStudentOneEnd = studentOneChatsPage.getTurnedOffRepliesInfoTxt();
				Assert.assertEquals(lblInfoStudentOneEnd,
						expectedAssertionsProp.getProperty(TURN_OFF_REPLIES_IN_GROUP_CHAT_INFO_TXT));

				String lblInfoStudentTwoEnd = studentTwoChatsPage.getTurnedOffRepliesInfoTxt();
				Assert.assertEquals(lblInfoStudentTwoEnd,
						expectedAssertionsProp.getProperty(TURN_OFF_REPLIES_IN_GROUP_CHAT_INFO_TXT));

			} else {
				studentOneChatsPage.clickOnTurnOffReplies();

				String turnOnReplies = studentOneChatsPage.getTurnedOnRepliesTxt();
				Assert.assertEquals(turnOnReplies, expectedAssertionsProp.getProperty(TOAST_FOR_TURNED_ON_TXT));

				super.testSendMessage(studentTwoChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			}

		} catch (Exception e) {
			Assert.fail("Exception occured while testing TurnOffOnReplies By StudentOne :" + e.getMessage());
			logger.error("Error occured while testing TurnOffOnReplies By StudentOne", e);
		}

		logger.info("Ending of testTurnOffOnRepliesByStudentOne method");
	}

	@Test(priority = 14, description = "Verify StudentOne can able to View Group Information", groups = "sanity")
	@Description("Test Description:Verify StudentOne can able to View Group Information")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify StudentOne can able to View Group Information")
	public void testGroupInformationByStudentOne() {
		logger.info("Starting of testGroupInformation method");

		try {
			studentOneChatsPage.clickOnMenuVerticalIcon();
			studentOneChatsPage.clickOnGroupInformation();

			// Assertion for Batch label
			String LblBatchGroupHeaderName = studentOneConversationPage.getGroupNameInGroupInformation();
			Assert.assertEquals(LblBatchGroupHeaderName,
					expectedAssertionsProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			// Assertion for Batch label
			Assert.assertEquals(studentOneChatsPage.getParticipantCountTxt(),
					studentOneChatsPage.getListOfParticipantsCountText());

			studentOneChatsPage.clickOnBackInGroupInformation();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Group Information : " + e.getMessage());
			logger.error("Error occured while testing Group Information", e);
		}

		logger.info("Ending of testGroupInformation method");
	}

	@Test(priority = 15, description = "Verify StudentOne can able to Mute and UnMute Conversation for a Group Chat", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify StudentOne can able to Mute and UnMute Conversation for a Group Chat")
	@Story("Verify StudentOne can able to Mute and UnMute Conversation for a Group Chat")
	public void testMuteOrUnmuteConversationByStudentOne() {
		logger.info("Starting of testMuteOrUnmuteConversationByStudentOne method");

		try {
			studentOneChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(studentOneChatsPage, testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By StudentOne for a Group Chat : "
					+ e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By StudentOne for a Group Chat", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByStudentOne method");
	}


	@Test(priority = 16, description = "Verify StudentTwo can able to Mute and UnMute Conversation for a Group Chat", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify StudentTwo can able to Mute and UnMute Conversation for a Group Chat")
	@Story("Verify StudentTwo can able to Mute and UnMute Conversation for a Group Chat")
	public void testMuteOrUnmuteConversationByStudentTwo() {
		logger.info("Starting of testMuteOrUnmuteConversationByStudentTwo method");

		try {
			/*
			 * if(!student2ChatsPage.isSelectedWriteSomethingHereLabel()) {
			 * student1ChatsPage.clickOnMenuVerticalIcon();
			 * student1ChatsPage.clickOnTurnOnReplies(); }
			 */
			studentTwoChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(studentTwoChatsPage, testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Mute and Unmute Conversation By StudentTwo for a Group Chat  : "
							+ e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By StudentTwo for a Group Chat", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByStudentTwo method");
	}

	@Test(priority = 17, description = "Verify StudentOne can able to send message in Group Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify StudentOne can able to send message in Group Chat Page")
	@Story("Verify Send Message By StudentOne in Group Chat")
	public void testSendMessageByStudentOne() {
		logger.info("Starting of testSendMessageByStudentOne method");

		try {
			super.testSendMessage(studentOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(studentTwoChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message in Group By StudentOne : " + e.getMessage());
			logger.error("Error occured while testing Send Message in Group By StudentOne", e);
		}

		logger.info("Ending of testSendMessageByStudentOne method");
	}

	@Test(priority = 18, description = "Verify StudentTwo can able to send message in Group Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify StudentTwo can able to send message in Group Chat Page")
	@Story("Verify Send Message By StudentTwo in Group Chat")
	public void testSendMessageByStudentTwo() {
		logger.info("Starting of testSendMessageByStudentTwo method");

		try {
			/*
			 * if(!student2ChatsPage.isSelectedWriteSomethingHereLabel()) {
			 * student1ChatsPage.clickOnMenuVerticalIcon();
			 * student1ChatsPage.clickOnTurnOnReplies(); }
			 */
			super.testSendMessage(studentTwoChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(studentOneChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message in Group By StudentTwo : " + e.getMessage());
			logger.error("Error occured while testing Send Message in Group  By StudentTwo", e);
		}

		logger.info("Ending of testSendMessageByStudentTwo method");
	}

	@Test(priority = 19, description = "Verify Report Conversation in Group Chat", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in Group Chat")
	@Story("Verify Report Conversation")
	public void testReportConversationByStudentOneAndStudentTwo() {
		logger.info("Starting of testReportConversationByStudentOneAndStudentTwo method");

		try {
			super.testReportConversation(studentOneChatsPage);
			super.testReportConversation(studentTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By StudentOne and StudentTwo : "
					+ e.getMessage());
			logger.error("Error occured while testing Repor Conversation By StudentOne and StudentTwo", e);
		}

		logger.info("Ending of testReportConversationByStudentOneAndStudentTwo method");
	}

	@Test(priority = 20, description = "Verify Clear Conversation in Group Chat", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Group Chat.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByStudentOneAndStudentTwo() {
		logger.info("Starting of testClearConversationByTutorAndStudent method");

		try {
			super.testClearConversation(studentOneChatsPage);
			super.testClearConversation(studentTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By StudentOne and StudentTwo : "
					+ e.getMessage());
			logger.error("Error occured while testing Clear Conversation  By StudentOne and StudentTwo", e);
		}

		logger.info("Ending of testClearConversationByTutorAndStudent method");
	}

	@Test(priority = 12, description = "Verify Add Document and File extension")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document  and File extension")
	@Story("Verify Add Document and File extension")
	public void testAddDocumentAndFileExtensionSentByStudent() {
		logger.info("Starting of testAddDocumentAndFileExtensionSentByStudent method");

		try {
			super.testAddDocumentFile(studentOneChatsPage);

			String expectedFileName = expectedAssertionsProp.getProperty(DOCUMENT_FILE_TXT);
			String expectedResult = expectedFileName.substring(expectedFileName.length() - 3,
					expectedFileName.length());
			String actualFileName = studentOneChatsPage.getAddDocumentTxt();
			String actualResult = actualFileName.substring(actualFileName.length() - 3, actualFileName.length());
			Assert.assertEquals(actualResult, expectedResult);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentAndFileExtensionSentByStudent method");
	}

	//extra testcase
	@Test(priority = 22, description = "Verify Add Image")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Image")
	@Story("Verify Add Image")
	public void testAddImageFileByStudentOneAndStudentTwo() {
		logger.info("Starting of testAddImageFileByStudentOneAndStudentTwo method");

		try {
			super.testAddImageFile(studentOneChatsPage);
			super.testAddImageFile(studentTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Image File  : " + e.getMessage());
			logger.error("Error occured while testing Add Image File ", e);
		}

		logger.info("Ending of testAddImageFileByStudentOneAndStudentTwo method");
	}

	@Test(priority = 23, description = "Verify Tutor a can able to copy a message in a group chat ", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to copy a message in a group chat ")
	@Story("Verify Copy Message in Group Chat")
	public void testCopyMessageByStudentOneAndStudentTwo() {
		logger.info("Starting of testCopyMessageByStudentOneAndStudentTwo method");

		try {
			super.testCopyMessage(studentOneChatsPage);
			super.testCopyMessage(studentTwoChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Copy Single Message By StudentOne And StudentTwo : "
					+ e.getMessage());
			logger.error("Error occured while testing Copy Message By StudentOne And StudentTwo", e);
		}

		logger.info("Ending of testCopyMessageByStudentOneAndStudentTwo method");
	}

	@Test(priority = 24, description = "Verify Tutor can able to delete single message in a batch chat group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to delete single message in a batch chat group")
	@Story("Verify Delete Single Message in Batch Chat Group")
	public void testDeleteSingleMessageByStudentOne() {
		logger.info("Starting of testDeleteSingleMessageByStudentOne method");

		try {
			super.testDeleteSingleMessage(studentOneChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Delete Single Message By StudentOne : " + e.getMessage());
			logger.error("Error occured while testing Delete Single Message  By StudentOne", e);
		}

		logger.info("Ending of testDeleteSingleMessageByStudentOne method");
	}

	@Test(priority = 26, description = "Verify Tutor can able to Delete Student Created Group ", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Delete Student Created Group")
	@Story("Verify Delete Group")
	public void testDeleteGroupByStudentOne() {
		logger.info("Starting of testDeleteGroupByTutor method");
		try {
			studentOneChatsPage.clickOnMenuVerticalIcon();
			studentOneChatsPage.clickOnDeleteGroup();

			String deleteConfirmationTxt = studentOneChatsPage.getHeaderDeleteConfirmationTxt();
			Assert.assertEquals(deleteConfirmationTxt, expectedAssertionsProp.getProperty(DELETE_CONFIRMATION_TXT));

			studentOneChatsPage.clickOnDeleteButton();

			String deleteGroup = studentOneChatsPage.getDeleteGroupTxt();
			Assert.assertEquals(deleteGroup,
					expectedAssertionsProp.getProperty("toast.message.delete.group.conversation"));

			studentOneChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			// Not working in the site in progress of assertion

			// Validating the Deleted Conversation //
			/*Assert.assertEquals(studentOneChatsPage.getNoConversationFoundTxt(),
					expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));*/

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Delete Group by StudentOne : " + e.getMessage());
			logger.error("Error occured while testing Delete Group by StudentOne", e);
		}

		logger.info("Ending of testDeleteGroupByTutor method");
	}

	@Test(priority = 25, description = "Verify StudentTwo can able to Leave Group from StudentOne Created Group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify StudentTwo can able to Leave Group from StudentOne Created Group")
	@Story("Verify Leave Group")
	public void testLeaveGroupByStudentTwo() {
		logger.info("Starting of testLeaveGroupByStudentTwo method");

		try {
			studentTwoChatsPage.clickOnMenuVerticalIcon();
			studentTwoChatsPage.clickOnLeaveGroup();

			String deleteConfirmationTxt = studentTwoChatsPage.getHeaderConfirmationTxt();
			Assert.assertEquals(deleteConfirmationTxt, expectedAssertionsProp.getProperty(LEAVE_CONFIRMATION_TXT));

			studentTwoChatsPage.clickOnLeaveButton();

			String leaveGroup = studentTwoChatsPage.getLeftGroupTxt();
			Assert.assertEquals(leaveGroup, expectedAssertionsProp.getProperty(TOAST_FOR_LEAVE_GROUP_TXT));

		/*	studentTwoChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			Assert.assertEquals(studentTwoChatsPage.getNoConversationFoundTxt(),
					expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));*/

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Leave Group by StudentTwo : " + e.getMessage());
			logger.error("Error occured while testing Leave Group by StudentTwo", e);
		}

		logger.info("Ending of testLeaveGroupByStudentTwo method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (studentOneDriver != null && studentTwoChatsPage != null && tutorDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(studentOneDriver);
				this.initClassplusSiteLogout(studentTwoDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest);
				this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest);
				this.quitDriver(WEB_DRIVER.STUDENT_TWO_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}

}
