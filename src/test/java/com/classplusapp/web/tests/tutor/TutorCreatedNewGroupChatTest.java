package com.classplusapp.web.tests.tutor;

import static com.classplusapp.chat.util.Constants.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.classplusapp.chat.util.Constants;
import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.tutor.TutorConversationPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("New group chat")
@Feature("Tutor Created New group chat")

public class TutorCreatedNewGroupChatTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver studentDriver = null;
	private WebDriver parentDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage studChatsPage = null;
	private ChatsPage parentChatsPage = null;
	private TutorConversationPage tutorConversationPage = null;
	Long SystemTimeAtTutorEnd, SystemTimeAtFacultyEnd, SystemTimeAtParentEnd, SystemTimeAtStudentEnd;

	private static final Logger logger = Logger.getLogger(TutorCreatedNewGroupChatTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in TutorConversationTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest);
		this.studentDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest);
		this.parentDriver = super.loginClassPlusSite(browser, ORG_CODE, PARENT_MOBILE_NUBMER, EMAIL_ADDRESS,
				WEB_DRIVER.PARENT_ONE_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest);

		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.studChatsPage = new ChatsPage(this.studentDriver);
		this.parentChatsPage = new ChatsPage(this.parentDriver);
		this.tutorConversationPage = new TutorConversationPage(this.tutorDriver);

		super.testAllowStudyGroups(tutorChatsPage);

		logger.info("Ending of initClassplusSiteLogin method in TutorConversationTest");
	}

	@Test(priority = 1, description = "Verify Tutor can able to click Chats Tab", groups = "sanity")
	@Description("Test Description:Verify Tutor can able to click Chats Tab")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Tutor can able to click Chats Tab")
	public void testChatsTab() {
		logger.info("Starting of testChatsTab method");

		try {
			// Tutor End
			super.testChatsTab(tutorChatsPage);

			// Assertion for Student Groups tool tip,more options
			assertTrue(tutorChatsPage.getStudentGroupsToolTip());
			super.testMoreOptionsImage(tutorChatsPage);
			super.testSearchBarLabel(tutorChatsPage);
			super.testMessageLabel(tutorChatsPage);

			// student End
			super.testChatsTab(studChatsPage);
			super.testMoreOptionsImage(studChatsPage);
			super.testSearchBarLabelAtStudentEnd(studChatsPage);
			super.testMessageLabel(studChatsPage);

			// Parent End
			super.testChatsTab(parentChatsPage);
			super.testSearchBarLabel(parentChatsPage);
			super.testMessageLabel(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Chats Tab: " + e.getMessage());
			logger.error("Error occured while testing Chats Tab", e);
		}

		logger.info("Ending of testChatsTab method");
	}

	@Test(priority = 2, description = "Verify Search with Invalid Queries")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search with Invalid Queries")
	@Story("Verify Search with Invalid Queries")
	public void testSearchWithInvalidQueryByTutor() {
		logger.info("Starting of testSearchWithInvalidQueryByTutor method");

		try {
			// super.testChatsTab(tutorChatsPage);
			tutorChatsPage.clickOnImgMoreOptions();

			assertTrue(this.tutorChatsPage.getNewGroupChatTxt());

			tutorChatsPage.clickOnNewGroupChat();
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			String lblErrorFetchingInConveration = tutorChatsPage.getErrorFetchingConversationTxt();
			Assert.assertEquals(lblErrorFetchingInConveration,
					expectedAssertionsProp.getProperty(ERROR_IN_FETCHING_ONVERSATION_TXT));

			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_INVALID_QUERY));

			String lblItsEmptyOUtHere = tutorChatsPage.getItsEmptyOutHereTxt();
			Assert.assertEquals(lblItsEmptyOUtHere, expectedAssertionsProp.getProperty(ITS_EMPTY_OUT_HERE_TXT));

			tutorChatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing Search With Invalid Query In New group chat : " + e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In New group chat ", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryByTutor method");
	}

	@Test(priority = 3, description = "Verify selected list  green dot or not if online in New group chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify selected list  green dot or not if online in  New group chat Pagee")
	@Story("Verify selected list  green dot or not if online in New group chat Page")
	public void testGreenDotInGroupChatByTutor() {
		logger.info("Starting of testGreenDotInGroupChatByStudentOne method");

		try {
			Assert.assertFalse(tutorChatsPage.IsDisplayedNoGreenColor());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing verify selected list  green dot or not if online in New group chat Page: "
							+ e.getMessage());
			logger.error(
					"Error occured while testing verify selected list  green dot or not if online in New group chat Page",
					e);
		}

		logger.info("Ending of testGreenDotInGroupChatByStudentOne method");
	}

	@Test(priority = 4, description = "Verify Whether New Group Can Be Create Without Any Participants Or Not")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Whether New Group Can Be Created Without Any Participants Or Not")
	@Story("Verify Whether New Group Can Be Created Without Any Participants Or Not")
	public void testCreateGroupWithoutParticipantsByTutor() {
		logger.info("Starting of testCreateGroupWithoutParticipantsByTutor method");

		try {
			assertTrue(this.tutorChatsPage.isDisplayedGroupNameTxt());
			tutorChatsPage.setGroupName(testDataProp.getProperty(Constants.TUTOR_CREATED_GROUP_NAME));

			assertTrue(this.tutorChatsPage.isDisplayedCreateGroupButton());

			tutorChatsPage.clickOnCreateGroupButton();

			String lblWeNeedParticipantsTxt = tutorChatsPage.getWeNeedParticipantsTxt();
			Assert.assertEquals(lblWeNeedParticipantsTxt,
					expectedAssertionsProp.getProperty(LABEL_WE_NEED_PARTICIPANTS_TEXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing create group without participants : " + e.getMessage());
			logger.error("Error occured while testing create group without participants", e);
		}

		logger.info("Ending of testCreateGroupWithoutParticipantsByTutor method");
	}

	@Test(priority = 5, description = "Verify Create NewGroupChat By Tutor", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Create NewGroupChat By Tutors")
	@Story("Verify Create NewGroupChat By Tutor")
	public void testCreateNewGroupChatByTutor() {
		logger.info("Starting of testCreateNewGroupChatByTutor method");

		try {
			tutorChatsPage.clickOnChatsTab();
			tutorChatsPage.clickOnImgMoreOptions();
			tutorChatsPage.clickOnNewGroupChat();

			// Assertion for AddRecipients
			String lblAddRecipients = tutorChatsPage.getAddRecipientsTxt();
			Assert.assertEquals(lblAddRecipients, expectedAssertionsProp.getProperty(LABEL_ADD_RECIPIENT));

			// Using Filters To Add Participants
			// Assertion for ImgFilter
			assertTrue(this.tutorChatsPage.isDisplayedImgFilters());

			tutorChatsPage.clickOnFilters();

			// Assertion for Filters label
			String LblFiltersTxt = tutorChatsPage.getFiltersTxt();
			Assert.assertEquals(LblFiltersTxt, expectedAssertionsProp.getProperty(LABEL_FILTERS));

			// Assertion for Students label
			String LblStudentsTxt = tutorChatsPage.getStudentsTxt();
			Assert.assertEquals(LblStudentsTxt, expectedAssertionsProp.getProperty(LABEL_STUDENTS_TXT));

			tutorChatsPage.clickOnStudentRecipient();

			// Assertion for Students label
			String LblFacultyTxt = tutorChatsPage.getFacultyTxt();
			Assert.assertEquals(LblFacultyTxt, expectedAssertionsProp.getProperty(LABEL_FACULTY_TXT));

			tutorChatsPage.clickOnFacultyRecipient();

			// Assertion for Students label
			String LblParentTxt = tutorChatsPage.getParentTxt();
			Assert.assertEquals(LblParentTxt, expectedAssertionsProp.getProperty(LABEL_PARENTS_TXT));

			tutorChatsPage.clickOnParentRecipient();

			// Assertion for Button presence
			assertTrue(this.tutorChatsPage.usDisplayedApplyButton());

			tutorChatsPage.clickOnApplyButton();

			// Assertion for AddRecipients
			String lblSelectRecipients = tutorChatsPage.getAddRecipientsTxt();
			Assert.assertEquals(lblSelectRecipients, expectedAssertionsProp.getProperty(LABEL_ADD_RECIPIENT));

			// Add Participants and Asserted whether added the participants or not
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(Constants.SEARCH_FOR_STUDENT_RECEPIENT));
			tutorConversationPage.clickOnStudentRecipient(testDataProp.getProperty(Constants.STUDENT_RECEPIENT));

			assertTrue(this.tutorConversationPage.getStudentSenderNameTxt());

			// Asserting Change in Participant count
			Assert.assertEquals(tutorChatsPage.getParticipantCountTxt(),
					tutorChatsPage.getListOfParticipantsCountTxt());

			tutorChatsPage.clickOnClearQuery();

			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(Constants.SEARCH_FOR_FACULTY_RECEPIENT));
			tutorChatsPage.clickOnFacultyRecipient(testDataProp.getProperty(Constants.FACULTY_RECEPIENT));

			assertTrue(this.tutorConversationPage.getFacultySenderNameTxt());

			// Asserting Change in Participant count
			Assert.assertEquals(tutorChatsPage.getParticipantCountTxt(),
					tutorChatsPage.getListOfParticipantsCountTxt());

			tutorChatsPage.clickOnClearQuery();
			// parent
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(Constants.SEARCH_FOR_PARENT_TWO_RECEPIENT));
			tutorConversationPage.clickOnParentRecipient(testDataProp.getProperty(Constants.PARENT_TWO_RECEPIENT));

			assertTrue(this.tutorConversationPage.getParentTwoSenderNameTxt());

			// Asserting Change in Participant count
			Assert.assertEquals(tutorChatsPage.getParticipantCountTxt(),
					tutorChatsPage.getListOfParticipantsCountTxt());

			tutorChatsPage.clickOnClearQuery();

			// parent one
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(Constants.SEARCH_FOR_PARENT_ONE_RECEPIENT));
			tutorConversationPage.clickOnParentOneRecipient(testDataProp.getProperty(Constants.PARENT_ONE_RECEPIENT));

			assertTrue(this.tutorConversationPage.getParentSenderNameTxt());

			// Asserting Change in Participant count
			Assert.assertEquals(tutorChatsPage.getParticipantCountTxt(),
					tutorChatsPage.getListOfParticipantsCountTxt());

			tutorChatsPage.clickOnThreeDottedMenu();
			tutorChatsPage.clickOnRemoveParticipant();

			String removeParticipantTxt = tutorChatsPage.getRemoveConfirmationHeaderTxt();
			Assert.assertEquals(removeParticipantTxt,
					expectedAssertionsProp.getProperty(REMOVE_PARTICIPANT_HEADER_TXT));

			String removeButtonTxt = tutorChatsPage.getRemoveButtonTxt();
			Assert.assertEquals(removeButtonTxt, expectedAssertionsProp.getProperty(REMOVE_BUTTON_TXT));

			tutorChatsPage.clickOnRemoveButton();

			String msgRemoveParticipant = tutorChatsPage.getRemoveParticipantTxt();
			Assert.assertEquals(msgRemoveParticipant, expectedAssertionsProp.getProperty(REMOVE_PARTICIPANT_TXT));

			assertFalse(tutorChatsPage.getistOfSenderNameTxt());

			// Asserting Change in Participant count
			Assert.assertEquals(tutorChatsPage.getParticipantCountTxt(),
					tutorChatsPage.getListOfParticipantsCountTxt());

			tutorChatsPage.setGroupName(testDataProp.getProperty(Constants.TUTOR_CREATED_GROUP_NAME));

			assertTrue(this.tutorChatsPage.isDisplayedCreateGroupButton());

			tutorChatsPage.clickOnCreateGroupButton();

			// Assertion for Group creation toast
			String msgGroupCreated = tutorChatsPage.getGroupCreatedTxt();
			Assert.assertEquals(msgGroupCreated, expectedAssertionsProp.getProperty(GROUP_CREATED_TXT));

			// Assertion for Updated group name

			String lblgroupName = tutorChatsPage.getGroupNameTxt();
			Assert.assertEquals(lblgroupName, expectedAssertionsProp.getProperty(Constants.TUTOR_CREATED_GROUP_NAME));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing New group chat: " + e.getMessage());
			logger.error("Error occured while testing New group chat", e);
		}

		logger.info("Ending of testNewGroupChatByTutor method");
	}

	@Test(priority = 6, description = "Verify Tutor can able to send message in  GroupChat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to send message in  Page")
	@Story("Verify Send Message By Tutor")
	public void testSendMessageByTutorInGroup() {
		logger.info("Starting of testSendMessageByTutorInGroup method");

		try {
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message By Tutor: " + e.getMessage());
			logger.error("Error occured while testing Send Message By Tutor", e);
		}

		logger.info("Ending of testSendMessageByTutorInGroup method");
	}

	@Test(priority = 7, description = "Verify Select Recipient", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Select Recipient")
	@Story("Verify Select Recipient")
	public void testSearchAndSelectRecipientByStudent() {
		logger.info("Starting of testSearchAndSelectRecipientByStudent method");

		try {
			super.testSearchTutorCreatedGroupByStudent(studChatsPage);
			super.testSelectTutorCreatedGroup(studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search Recipient : " + e.getMessage());
			logger.error("Error occured while testing Search Recipient", e);
		}

		logger.info("Ending of testSearchAndSelectRecipientByStudent method");
	}

	@Test(priority = 8, description = "Verify when Tutor creates group, students are not able to check the group information")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify when Tutor creates group, students are not able to check the group information")
	@Story("Verify when Tutor creates group, students are not able to check the group information")
	public void testStudentCannotViewGroupInformation() {
		logger.info("Starting of testStudentCannotViewGroupInformation method");

		try {
			studChatsPage.clickOnMenuVerticalIcon();

			Assert.assertFalse(studChatsPage.isDisplayedGroupInformation());

			this.studChatsPage.clickOutside();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Group Information : " + e.getMessage());
			logger.error("Error occured while testing roup Information", e);
		}

		logger.info("Ending of testStudentCannotViewGroupInformation method");
	}

	// @Test(priority = 8, description = "Verify when Tutor creates group, students
	// are not able to check the group information")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify when Tutor creates group, students are not able to check the group information")
	@Story("Verify when Tutor creates group, students are not able to check the group information")
	public void testParentCannotViewGroupInformation() {
		logger.info("Starting of testParentCannotViewGroupInformation method");

		try {
			parentChatsPage.clickOnMenuVerticalIcon();

			Assert.assertFalse(parentChatsPage.isDisplayedGroupInformation());

			this.parentChatsPage.clickOutside();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Group Information : " + e.getMessage());
			logger.error("Error occured while testing roup Information", e);
		}

		logger.info("Ending of testParentCannotViewGroupInformation method");
	}

	@Test(priority = 9, description = "Verify Student can able to send message in GroupChat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Student can able to send message in GroupChat Page")
	@Story("Verify Send Message By Student")
	public void testSendMessageByStudentInGroup() {
		logger.info("Starting of testSendMessageByStudentInGroup method");
		try {
			super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message By Student : " + e.getMessage());
			logger.error("Error occured while testing Send Message By Student", e);
		}

		logger.info("Ending of testSendMessageByStudentInGroup method");
	}

	@Test(priority = 10, description = "Verify Select Recipient By Parent")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Select Recipient By Parent")
	@Story("Verify Select Recipient By Parent")
	public void testSearchAndSelectRecipientByParent() {
		logger.info("Starting of testSearchAndSelectRecipientByParent method");

		try {
			super.testSearchTutorCreatedGroup(parentChatsPage);
			super.testSelectTutorCreatedGroup(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search Recipient By Parent : " + e.getMessage());
			logger.error("Error occured while testing Search Recipient By Parent", e);
		}

		logger.info("Ending of testSearchAndSelectRecipientByParent method");
	}

	// @Test(priority = 1, description = "Verify UI of the already created batches
	// groups and the groups in which added when land on chat screen.", groups =
	// "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify UI of the already created batches groups and the groups in which added when land on chat screen.")
	@Story("Verify UI of the already created batches groups and the groups in which added when land on chat screen.")
	public void testUIOfAlreadyTutorCreatedChatGroupByParent() {
		logger.info("Starting of testUIOfAlreadyTutorCreatedChatGroupByParent method");

		try {
			assertTrue(parentChatsPage.isDisplayedMenuVerticalIcon());

			Assert.assertEquals(parentChatsPage.getGroupNameTxt(),
					expectedAssertionsProp.getProperty(Constants.TUTOR_CREATED_GROUP_NAME));

			// Assertion for Student Groups tool tip
			assertTrue(parentChatsPage.getStudentGroupsToolTip());

			// Assert for more option
			super.testMoreOptionsImage(parentChatsPage);
			// Search Bar
			super.testSearchBarLabel(parentChatsPage);

			// Assertion for Label Users
			Assert.assertEquals(parentChatsPage.getMessageTxt(), expectedAssertionsProp.getProperty(LABEL_MESSAGES));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing UI of the already created batches groups and the groups in which added when land on chat screen. : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing UI of the already created batches groups and the groups in which added when land on chat screen.",
					e);
		}

		logger.info("Ending of testUIOfAlreadyTutorCreatedChatGroupByParent method");
	}

	@Test(priority = 11, description = "Verify Parent can able to send message in Chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Parent can able to send message in Chat Page")
	@Story("Verify Send Message By Parent")
	public void testSendMessageByParentInGroup() {
		logger.info("Starting of testSendMessageByParentInGroup method");
		try {
			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message By Parent: " + e.getMessage());
			logger.error("Error occured while testing Send Message By Parent", e);
		}

		logger.info("Ending of testSendMessageByParentInGroup method");
	}

	@Test(priority = 12, description = "Verify Send Message With Blank Space By Parent")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message With Blank Space By Parent")
	@Story("Verify Send Message With Blank Space By Parent")
	public void testSendMessageWithBlankSpaceByParentInGroup() {
		logger.info("Starting of testSendMessageWithBlankSpaceByParentInGroup method");

		try {
			assertTrue(parentChatsPage.getWriteSomethingHereTxt());
			parentChatsPage.clickOnClearQueryInWriteSomethingHere();
			parentChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));
			parentChatsPage.clickOnSendButton();

			super.testBlankMessage(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message With Blank Space By Parent" + e.getMessage());
			logger.error("Error occured while testing test Send Message With Blank Space By Parent\"", e);
		}

		logger.info("Ending of testSendMessageWithBlankSpaceByParentInGroup method");
	}

	@Test(priority = 13, description = "Verify Send Message with Tinestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with Tinestamp")
	@Story("Verify Send Message with Tinestamp")
	public void testSentMessageWithTimeStampByParentEnd() {
		logger.info("Starting of testSentMessageWithTimeStampByParentEnd method");

		try {
			assertTrue(parentChatsPage.getWriteSomethingHereTxt());
			parentChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
			parentChatsPage.clickOnSendButton();

			super.testValidateSendMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			String systemTime = this.getCurrentSystemTime();
			String sentMessageTimeStamp = parentChatsPage.getMessageTimeStampTxt();
			String receivingMessageTimeStamp = parentChatsPage.getMessageTimeStampTxt();

			Assert.assertEquals(sentMessageTimeStamp, systemTime);
			Assert.assertEquals(receivingMessageTimeStamp, systemTime);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Send Message With Time Stamp", e);
		}

		logger.info("Ending of testSentMessageWithTimeStampByParentEnd method");
	}

	@Test(priority = 14, description = "Verify Send Short And Long Message")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Short And Long Message")
	@Story("Verify Send Short And Long Message")
	public void testSendShortAndLongMessageByParent() {
		logger.info("Starting of testSendShortAndLongMessageByParent method");

		try {
			assertTrue(parentChatsPage.getWriteSomethingHereTxt());

			// sending short message

			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			// sending long message
			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_LONG_MESSAGE));

			super.testValidateSendMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_LONG_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Short And Long Message\" : " + e.getMessage());
			logger.error("Error occured while testing  Send Short And Long Message", e);
		}

		logger.info("Ending of testSendShortAndLongMessageByParent method");
	}

	@Test(priority = 15, description = "Verify that how much time is it taking to send a message from Parent to Tutor in group ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Parent to Tutor in group")
	@Story("Verify that how much time is it taking to send a message from Parent to Tutor")
	public void testConversationTimePeriodForParentToTutorInGroup() {
		logger.info("Starting of testConversationTimePeriodForParentToTutorInGroup method");

		try {
			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			SystemTimeAtTutorEnd = super.getConversationTimePeriod();

			super.testValidateSendMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			String systemTime = this.getCurrentSystemTime();

			String msgTimeStamp = tutorChatsPage.getMessageTimeStampTxt();
			logger.debug(msgTimeStamp);
			// Assert.assertEquals(msgTimeStamp, systemTime);

			super.testValidateReceivedMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			SystemTimeAtParentEnd = super.getConversationTimePeriod();

			Long TimeDifference = SystemTimeAtTutorEnd - SystemTimeAtParentEnd;
			logger.debug("Time Taken To Send a Message" + TimeDifference);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from Tutor to Parent in group\" : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from Tutor to Parent in group",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForParentToTutorInGroup method");
	}

	@Test(priority = 16, description = "Verify that how much time is it taking to send a message from Tutor to Student in group ")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from Tutor to Student in group")
	@Story("Verify that how much time is it taking to send a message from Tutor to Student")
	public void testConversationTimePeriodForTutorToStudentInGroup() {
		logger.info("Starting of testConversationTimePeriodForTutorToStudentInGroup method");

		try {
			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			SystemTimeAtTutorEnd = super.getConversationTimePeriod();

			super.testValidateSendMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			String systemTime = this.getCurrentSystemTime();

			String msgTimeStamp = studChatsPage.getMessageTimeStampTxt();
			logger.debug(msgTimeStamp);
			Assert.assertEquals(msgTimeStamp, systemTime);

			super.testValidateReceivedMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			SystemTimeAtStudentEnd = super.getConversationTimePeriod();

			Long TimeDifference = SystemTimeAtTutorEnd - SystemTimeAtStudentEnd;
			logger.debug("Time Taken To Send a Message" + TimeDifference);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from Tutor to Parent in group\" : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from Tutor to Parent in group",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForTutorToStudentInGroup method");
	}

	@Test(priority = 17, description = "Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.")
	@Story("Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.")
	public void testMultipleUsersConversation() {
		logger.info("Starting of testMultipleUsersConversation method");

		try {

			super.testSendMessage(tutorChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateReceivedMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			super.testValidateReceivedMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateReceivedMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			super.testValidateReceivedMessage(parentChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			super.testSendMessage(parentChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateReceivedMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			super.testValidateReceivedMessage(studChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Multiple Users Conversation\" : " + e.getMessage());
			logger.error("Error occured while testing Multiple Users Conversation", e);
		}

		logger.info("Ending of testMultipleUsersConversation method");
	}

	@Test(priority = 18, description = "Verify Parent cannot able to delete single message in a chat body")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor cannot able to delete single message in a chat body")
	@Story("Verify Parent Cannot Delete Single Message")
	public void testCannotDeleteSingleMessageByParent() {
		logger.info("Starting of testCannotDeleteSingleMessageByParent method");

		try {
			super.testCannotDeleteConversation(parentChatsPage);
		} catch (Exception e) {
			Assert.fail("Exception occured while testing Cannot Delete Single Message By Parent : " + e.getMessage());
			logger.error("Error occured while testing Cannot Delete Single Message By Parent", e);
		}

		logger.info("Ending of testCannotDeleteSingleMessageByParent method");
	}

	@Test(priority = 19, description = "Verify Three dots Vertical Menu Options")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Three dots Vertical Menu Options")
	@Story("Verify Three dots Vertical Menu Options")
	public void testVerticalThreedotedMenuByParent() {
		logger.info("Starting of testVerticalThreedotedMenuByParent method");

		try {
			super.testSendMessage(parentChatsPage, SEND_MESSAGE);
			parentChatsPage.clickOnMenuVerticalIcon();

			super.testLabelClearConversation(parentChatsPage);
			super.testLabelReportConversation(parentChatsPage);
			super.testLabelMuteAndUnmuteConversation(parentChatsPage);

			this.parentChatsPage.clickOutside();

		} catch (Exception e) { //
			Assert.fail("Exception occured while testing vertical three doted menu : " + e.getMessage());
			logger.error("Error occured while testing vertical three doted menu ", e);
		}

		logger.info("Ending of testVerticalThreedotedMenuByParent method");
	}

	@Test(priority = 20, description = "Verify how long a chat can be saved at parent end")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify how long a chat can be saved at parent end")
	@Story("Verify how long a chat can be saved at parent end")
	public void testHowLongChatSavedAtParentEnd() {
		logger.info("Starting of testHowLongChatSavedAtParentEnd method");

		try {
			super.testHowlongChatSaved(parentChatsPage);
			logger.debug("Last chat was on :" + testHowlongChatSaved(parentChatsPage) + "Done");

		} catch (Exception e) {
			Assert.fail("Exception occured while testing how long a chat saved\" : " + e.getMessage());
			logger.error("Error occured while testing how long a chat saved", e);
		}

		logger.info("Ending of testHowLongChatSavedAtParentEnd method");
	}

	@Test(priority = 21, description = "Verify Selecting with Single Image")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Selecting with Single Image")
	@Story("Verify Selecting with Single Image")
	public void testSelectSingleImageByParent() {
		logger.info("Starting of testSelectSingleImageByParent method");

		try { // Test with Single Select
			super.testSelectImages(parentChatsPage, 1);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select Single Image: " + e.getMessage());
			logger.error("Error occured while testing Select Single Image", e);
		}

		logger.info("Ending of testSelectSingleImageByParent method");
	}

	@Test(priority = 22, description = "Verify Selecting with Same Image Multiple Times")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Selecting with Same Image Multiple Times")
	@Story("Verify Selecting with Same Image Multiple Times")
	public void testSelectWithSameImageMultipleTimesByParent() {
		logger.info("Starting of testSelectWithSameImageMultipleTimesByParent method");

		try { // Test Select with Same Image multile times
			for (int i = 0; i < 5; i++) {
				super.testSelectImages(parentChatsPage, 1);

			}

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Same Image Multiple Times : " + e.getMessage());
			logger.error("Error occured while testing Select With Same Image Multiple Times", e);
		}

		logger.info("Ending of testSelectWithSameImageMultipleTimesByParent method");
	}

	@Test(priority = 23, description = "Verify Select With Twenty Different Images")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Select With Twenty Different Images")
	@Story("Verify Select With Twenty Different Images")
	public void testSelectWithTwentyDifferentImages() {
		logger.info("Starting of testSelectWithTwentyDifferentImages method");

		try { // Test Select With Different Images
			super.testSelectImages(parentChatsPage, 20);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Twenty Different Images : " + e.getMessage());
			logger.error("Error occured while testing Select With Twenty Different Images", e);
		}

		logger.info("Ending of testSelectWithTwentyDifferentImages method");
	}

	@Test(priority = 24, description = "Verify Select With More Than Twenty Different Images")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Select With More Than Twenty Different Images")
	@Story("Verify Select With More Than Twenty Different Images")
	public void testMorethanTwentyImages() {
		logger.info("Starting of testMorethanTwentyImages method");

		try { // more than 21 images
			super.testSelectImages(parentChatsPage, 21);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select With Twenty Different Images : " + e.getMessage());
			logger.error("Error occured while testing Select With Twenty Different Images", e);
		}

		logger.info("Ending of testMorethanTwentyImages method");
	}

	@Test(priority = 25, description = "Verify Add Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document")
	@Story("Verify Add Document")
	public void testAddDocumentFileByTutor() {
		logger.info("Starting of testAddDocumentFileByTutor method");

		try {
			super.testAddDocumentFile(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentFileByTutor method");
	}

	@Test(priority = 26, description = "Verify Download And View Document")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Download And View Document")
	@Story("Verify Download And View Document")
	public void testDownloadAndViewDocumentByParent() {
		logger.info("Starting of testDownloadAndViewDocumentByParent method");

		try {

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

	@Test(priority = 27, description = "Verify Tutor can able to Mute and UnMute Conversation for a Student Conversation", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Mute and UnMute Conversation for a Student Conversation")
	@Story("Verify Mute and Unmute Conversation At Tutor End")
	public void testMuteOrUnmuteConversationByTutor() {
		logger.info("Starting of testMuteOrUnmuteConversationByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(tutorChatsPage, testDataProp.getProperty(TUTOR_CREATED_GROUP_NAME));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Tutor", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByTutor method");
	}

	@Test(priority = 28, description = "Verify Student can able to Mute Conversation for a Tutor Conversation", invocationCount = 2)
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Student can able to Mute Conversation for a Tutor Conversation")
	@Story("Verify Mute and Unmute Conversation At Student End")
	public void testMuteOrUnmuteConversationByStudent() {
		logger.info("Starting of testMuteOrUnmuteConversationByStudent method");

		try {
			studChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(studChatsPage, testDataProp.getProperty(TUTOR_CREATED_GROUP_NAME));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Student : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Student", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByStudent method");
	}

	@Test(priority = 29, description = "Verify Parent can able to Mute Conversation for a Tutor Conversation", invocationCount = 2)
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Parent can able to Mute Conversation for a Tutor Conversation")
	@Story("Verify Mute and Unmute Conversation At Parent End")
	public void testMuteOrUnmuteConversationByParent() {
		logger.info("Starting of testMuteOrUnmuteConversationByParent method");

		try {
			parentChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(parentChatsPage, testDataProp.getProperty(TUTOR_CREATED_GROUP_NAME));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Parent : " + e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Parent", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByParent method");
	}

	// @Test(priority = 30, description = "Verify Tutor can able to TurnOffOnReplies
	// for a Student Conversation", invocationCount = 2)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to TurnOffOnReplies for a Student Conversation")
	@Story("Verify TurnOffOnReplies")
	public void testTurnOffOnRepliesByTutor() {
		logger.info("Starting of testTurnOffOnRepliesByTutor method");

		try {
			// super.testTUR(tutorChatsPage,studChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing TurnOffOnReplies By Tutor: " + e.getMessage());
			logger.error("Error occured while testing TurnOffOnReplies By Tutor", e);
		}

		logger.info("Ending of testTurnOffOnRepliesByTutor method");
	}

	@Test(priority = 31, description = "Verify Report Conversation in Chat Page")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in  Chat Page")
	@Story("Verify Report Conversation")
	public void testReportConversationByTutorAndStudentAndParent() {
		logger.info("Starting of testReportConversationByTutorAndStudent method");

		try {
			super.testReportConversation(tutorChatsPage);
			super.testReportConversation(studChatsPage);
			super.testReportConversation(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By Tutor and Student : " + e.getMessage());
			logger.error("Error occured while testing Repor Conversation By Tutor and Student", e);
		}

		logger.info("Ending of testReportConversationByTutorAndStudent method");
	}

	@Test(priority = 32, description = "Verify Clear Conversation in Chat Page")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation in Chat Page.")
	@Story(" Verify Clear Conversation")
	public void testClearConversationByTutorAndStudentAndParent() {
		logger.info("Starting of testClearConversationByTutorAndStudentAndParent method");

		try {
			super.testClearConversation(tutorChatsPage);
			super.testClearConversation(studChatsPage);
			super.testClearConversation(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By Tutor Parent and Student : "
					+ e.getMessage());
			logger.error("Error occured while testing Clear Conversation  By Tutor and Student and Parent", e);
		}

		logger.info("Ending of testClearConversationByTutorAndStudentAndParent method");
	}

	@Test(priority = 33, description = "Verify Menu Options After Clear Conversation in Chat Page")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Menu Options After Clear Conversation in Chat Page")
	@Story("Verify Menu Options After Clear Conversation in Chat Page")
	public void testMenuOptionsAfterClearConversation() {
		logger.info("Starting of testMenuOptionsAfterClearConversation method");

		try {
			parentChatsPage.clickOnMenuVerticalIcon();
			super.testLabelMuteAndUnmuteConversation(parentChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Menu Options After Clear Conversation  By Parent: "
					+ e.getMessage());
			logger.error("Error occured while testing Menu Options After Clear Conversation By Parent", e);
		}

		logger.info("Ending of testMenuOptionsAfterClearConversation method");
	}

	@Test(priority = 34, description = "Verify Group Information and Edit Group Name")
	@Description("Test Description: Verify Group Information and Edit Group Name")
	@Severity(SeverityLevel.MINOR)
	@Story("Verify Group Information and Edit Group Name")
	public void testViewAndEditGroupInformationByTutor() {
		logger.info("Starting of testViewAndEditGroupInformationByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();
			tutorChatsPage.clickOnGroupInformation();

			assertTrue(this.tutorChatsPage.isDisplayedGroupNameTxt());

			tutorChatsPage.clickOnClearQueryInGroupInformation();

			tutorChatsPage.setGroupName(testDataProp.getProperty(Constants.TUTOR_CREATED_UPDATED_GROUP_NAME));

			assertTrue(this.tutorChatsPage.isDisplayedCreateGroupButton());

			tutorChatsPage.clickOnCreateGroupButton();

			String updateGroupName = tutorChatsPage.getUpdatedGroupNameTxt();
			Assert.assertEquals(updateGroupName,
					expectedAssertionsProp.getProperty("toast.message.updated.group.name"));

			// Assertion for Updated group name
			// String updatedGroupName =
			String updatedGroupName = tutorChatsPage.getGroupNameTxt();
			Assert.assertEquals(updatedGroupName, expectedAssertionsProp.getProperty("updated.group.name"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing View and Edit Group Information: " + e.getMessage());
			logger.error("Error occured while testing View and Edit Group Information:", e);
		}

		logger.info("Ending of testViewAndEditGroupInformationByTutor method");
	}

	@Test(priority = 35, description = "Verify Tutor can able to Delete conversation in New group chat")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Tutor can able to Delete conversation in New group chat")
	@Story(" Verify Delete Group.")
	public void testDeleteGroup() {
		logger.info("Starting of testDeleteGroup method");
		try {
			tutorChatsPage.clickOnMenuVerticalIcon();
			tutorChatsPage.clickOnDeleteGroup();

			String deleteGroup = tutorChatsPage.getDeleteGroupTxt();
			Assert.assertEquals(deleteGroup,
					expectedAssertionsProp.getProperty("toast.message.delete.group.conversation"));

			tutorChatsPage
					.clickOnSearchRecipients(testDataProp.getProperty(Constants.TUTOR_CREATED_UPDATED_GROUP_NAME));

			// Not working in the site in progress of assertion

			// Validating the Deleted Conversation // //
			// Assert.assertTrue(this.tutorChatsPage.getNoConversationTxt());

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Delete Group: " + e.getMessage());
			logger.error("Error occured while testing Delete Group", e);
		}

		logger.info("Ending of testDeleteGroup method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && studentDriver != null && studentDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(studentDriver);
				this.initClassplusSiteLogout(parentDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest);
				this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest);
				this.quitDriver(WEB_DRIVER.PARENT_ONE_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}
