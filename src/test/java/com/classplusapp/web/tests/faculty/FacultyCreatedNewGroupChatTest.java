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

import com.classplusapp.chat.util.Constants;
import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.faculty.FacultyConversationPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("New group chat")
@Feature("Faculty Created New group chat")
public class FacultyCreatedNewGroupChatTest extends CommonChatConversationTest {
	private WebDriver facultyTwoDriver = null;
	private WebDriver facultyOneDriver = null;
	private ChatsPage facultyOneChatsPage = null;
	private ChatsPage facultyTwoChatsPage = null;
	private FacultyConversationPage facultyConversationPage = null;
	Long SystemTimeAtTutorEnd, SystemTimeAtFacultyEnd, SystemTimeAtParentEnd, SystemTimeAtStudentEnd;

	private static final Logger logger = Logger.getLogger(FacultyCreatedNewGroupChatTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in FacultyCreatedNewGroupChat");

		this.facultyOneDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_ONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_FacultyCreatedNewGroupChatTest);
		this.facultyTwoDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_TWO_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_TWO_DRIVER_TEST_IN_FacultyCreatedNewGroupChatTest);

		this.facultyOneChatsPage = new ChatsPage(this.facultyOneDriver);
		this.facultyConversationPage = new FacultyConversationPage(this.facultyOneDriver);
		this.facultyTwoChatsPage = new ChatsPage(this.facultyTwoDriver);

		logger.info("Ending of initClassplusSiteLogin method in FacultyCreatedNewGroupChat");
	}

	@Test(priority = 1, description = "Verify UI of the screen should be perfect. Left side all persons lists should appear, search field should be available.")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description:Verify UI of the screen should be perfect. Left side all persons lists should appear, search field should be available.")
	@Story("Verify UI of the screen should be perfect. Left side all persons lists should appear, search field should be available.")
	public void testUINewGroupChatByFaculty() {
		logger.info("Starting of testUINewGroupChatByFaculty method");

		try {
			facultyOneChatsPage.clickOnChatsTab();
			facultyOneChatsPage.clickOnImgMoreOptions();

			assertTrue(this.facultyOneChatsPage.getNewGroupChatTxt());

			facultyOneChatsPage.clickOnNewGroupChat();

			// Assertion for AddRecipients
			Assert.assertEquals(facultyOneChatsPage.getAddRecipientsTxt(),
					expectedAssertionsProp.getProperty(LABEL_ADD_RECIPIENT));
			// Search Bar
			super.testSearchBarLabel(facultyOneChatsPage);
			// Assertion for Label Users
			Assert.assertEquals(facultyOneChatsPage.getMessageTxt(), expectedAssertionsProp.getProperty(LABEL_USERS));
			// Assertion for list of users
			assertTrue(this.facultyOneChatsPage.getListOfUsers());
			// Assertion for enter group name
			assertTrue(this.facultyOneChatsPage.isDisplayedGroupNameTxt());
			// Assertion for Create button
			assertTrue(this.facultyOneChatsPage.isDisplayedCreateGroupButton());
			// Assertion for participant count
			Assert.assertEquals(facultyOneChatsPage.getParticipantCountTxt(),
					facultyOneChatsPage.getListOfParticipantsCountTxt());
			// Assertion for admin tag
			Assert.assertEquals(facultyConversationPage.getAdminTagTxt(),
					expectedAssertionsProp.getProperty(ADMIN_TAG));
			// Color for admin text
			Assert.assertEquals(facultyConversationPage.isDisplayedGreenColor(),
					expectedAssertionsProp.getProperty(COLOR_FOR_ADMIN_TAG));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing UI of the screen should be perfect. Left side all persons lists should appear, search field should be available. : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing UI of the screen should be perfect. Left side all persons lists should appear, search field should be available.",
					e);
		}

		logger.info("Ending of testUINewGroupChatByFaculty method");
	}

	@Test(priority = 2, description = "Verify Search with Invalid Queries")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description:Verify Search with Invalid Queries")
	@Story("Verify Search with Invalid Queries")
	public void testSearchWithInvalidQueryInGroupChat() {
		logger.info("Starting of testSearchWithInvalidQueryInGroupChat method");

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

		logger.info("Ending of testSearchWithInvalidQueryInGroupChat method");
	}

	@Test(priority = 3, description = "Verify Whether New Group Can Be Create Without Any Participants Or Not")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description:Verify Whether New Group Can Be Created Without Any Participants Or Not")
	@Story("Verify Whether New Group Can Be Created Without Any Participants Or Not")
	public void testCreateGroupWithoutParticipantsByFaculty() {
		logger.info("Starting of testCreateGroupWithoutParticipantsByFaculty method");

		try {
			facultyOneChatsPage.setGroupName(testDataProp.getProperty(FACULTY_CREATED_GROUP_NAME));

			assertTrue(this.facultyOneChatsPage.isDisplayedCreateGroupButton());

			facultyOneChatsPage.clickOnCreateGroupButton();

			Assert.assertEquals(facultyOneChatsPage.getWeNeedParticipantsTxt(),
					expectedAssertionsProp.getProperty(LABEL_WE_NEED_PARTICIPANTS_TEXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing create group without participants : " + e.getMessage());
			logger.error("Error occured while testing create group without participants", e);
		}

		logger.info("Ending of testCreateGroupWithoutParticipantsByFaculty method");
	}

	@Test(priority = 4, description = "Verify With No Filters at Faculty New Group Chat")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description:Verify Verify With No Filters at Faculty New Group Chat")
	@Story("Verify With No Filters at Faculty New Group Chat")
	public void testWithNoFilterOption() {
		logger.info("Starting of testWithNoFilterOption method");

		try {
			Assert.assertFalse(facultyOneChatsPage.getImgFilter());

		} catch (Exception e) {
			Assert.fail("Exception occured while testing With No Filters at Faculty New Group Chat: " + e.getMessage());
			logger.error("Error occured while testing With No Filters at Faculty New Group Chat", e);
		}

		logger.info("Ending of testWithNoFilterOption method");
	}

	@Test(priority = 5, description = "Verify selected list green dot or not if online in New group chat Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify selected list green dot or not if online in  New group chat Pagee")
	@Story("Verify selected list green dot or not if online in New group chat Page")
	public void testGreenDotInGroupChatByFacultyOne() {
		logger.info("Starting of testGreenDotInGroupChatByFacultyOne method");

		try {
			Assert.assertFalse(facultyOneChatsPage.IsDisplayedNoGreenColor());

			facultyConversationPage.clickOnHeaderBackButton();

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testingselected list green dot or not if online in New group chat Page By FacultyOne: "
							+ e.getMessage());
			logger.error(
					"Error occured while selected list green dot or not if online in New group chat Page By FacultyOne",
					e);
		}

		logger.info("Ending of testGreenDotInGroupChatByFacultyOne method");
	}

	@Test(priority = 6, description = "Verify Create NewGroupChat By FacultyS", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Create NewGroupChat By Faculty")
	@Story("Verify Create NewGroupChat By Faculty")
	public void testCreateNewGroupChatByFaculty() {
		logger.info("Starting of testCreateNewGroupChatByFaculty method");

		try {
			facultyOneChatsPage.clickOnChatsTab();
			facultyOneChatsPage.clickOnImgMoreOptions();
			facultyOneChatsPage.clickOnNewGroupChat();
			facultyOneChatsPage.setGroupName(testDataProp.getProperty(FACULTY_CREATED_GROUP_NAME));

			// Add Participants and Asserted whether added the participants or not
			facultyOneChatsPage
					.clickOnSearchRecipients(testDataProp.getProperty(Constants.SEARCH_FOR_FACULTY_TWO_RECEPIENT));
			facultyOneChatsPage.clickOnFacultyTwoRecipient(testDataProp.getProperty(Constants.FACULTY_TWO_RECEPIENT));

			assertTrue(this.facultyConversationPage.getFacultyTwoSenderNameTxt());

			// Asserting Change in Participant count
			Assert.assertEquals(facultyOneChatsPage.getParticipantCountTxt(),
					facultyOneChatsPage.getListOfParticipantsCountTxt());

			facultyOneChatsPage.clickOnClearQuery();
			facultyOneChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_FACULTY_THREE_RECEPIENT));
			facultyOneChatsPage
					.clickOnFacultyThreeRecipient(testDataProp.getProperty(Constants.FACULTY_THREE_RECEPIENT));

			assertTrue(this.facultyConversationPage.getFacultyThreeSenderNameTxt());

			// Asserting Change in Participant count
			Assert.assertEquals(facultyOneChatsPage.getParticipantCountTxt(),
					facultyOneChatsPage.getListOfParticipantsCountTxt());

			facultyOneChatsPage.clickOnClearQuery();
			facultyOneChatsPage.clickOnCreateGroupButton();

			// Assertion for Group creation toast
			Assert.assertEquals(facultyOneChatsPage.getGroupCreatedTxt(),
					expectedAssertionsProp.getProperty(GROUP_CREATED_TXT));

			// Assertion for Updated group name
			// Assert.assertEquals(facultyOneChatsPage.getGroupName(),
			// expectedAssertionsProp.getProperty(Constants.FACULTY_CREATED_GROUP_NAME));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Create NewGroupChat : " + e.getMessage());
			logger.error("Error occured while testing Create NewGroupChat", e);
		}

		logger.info("Ending of testCreateNewGroupChatByFaculty method");
	}

	@Test(priority = 7, description = "Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.")
	@Story("Verify that multiple users can do chat or discussion in their group and every chat should be visible to every member of the group.")
	public void testMultipleUsersConversation() {
		logger.info("Starting of testMultipleUsersConversation method");

		try {
			super.testSendMessage(facultyOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			super.testValidateSendMessage(facultyOneChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));
			
			super.testChatsTab(facultyTwoChatsPage);
			super.testSearchFacultyCreatedGroup(facultyTwoChatsPage);
			super.testSelectFacultyCreatedGroup(facultyTwoChatsPage);
		
			super.testSendMessage(facultyTwoChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			super.testValidateSendMessage(facultyTwoChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Multiple Users Conversation\" : " + e.getMessage());
			logger.error("Error occured while testing Multiple Users Conversation", e);
		}

		logger.info("Ending of testMultipleUsersConversation method");
	}

	@Test(priority = 8, description = "Verify Send Short And Long Message")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description:Verify Send Short And Long Message")
	@Story("Verify Send Short And Long Message")
	public void testSendShortAndLongMessageByFacultyOne() {
		logger.info("Starting of testSendShortAndLongMessageByFacultyOne method");

		try {
			assertTrue(facultyOneChatsPage.getWriteSomethingHereTxt());

			// sending short message
			super.testSendMessage(facultyOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			super.testValidateSendMessage(facultyOneChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			// sending long message
			super.testSendMessage(facultyOneChatsPage, testDataProp.getProperty(SEND_LONG_MESSAGE));

			super.testValidateSendMessage(facultyOneChatsPage, expectedAssertionsProp.getProperty(SEND_LONG_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Short And Long Message\" : " + e.getMessage());
			logger.error("Error occured while testing  Send Short And Long Message", e);
		}

		logger.info("Ending of testSendShortAndLongMessageByFacultyOne method");
	}

	@Test(priority = 9, description = "Verify Send Message with Tinestamp")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Send Message with Tinestamp")
	@Story("Verify Send Message with Tinestamp")
	public void testSentMessageWithTimeStampByFacultyOne() {
		logger.info("Starting of testSentMessageWithTimeStampByFacultyOne method");

		try {
			super.testSendMessage(facultyOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			// asserting with timestamp with current systemTime
			Assert.assertEquals(facultyOneChatsPage.getMessageTimeStampTxt(), this.getCurrentSystemTime());

			super.testValidateSendMessage(facultyOneChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message With Time Stamp\" : " + e.getMessage());
			logger.error("Error occured while testing Send Message With Time Stamp", e);
		}

		logger.info("Ending of testSentMessageWithTimeStampByFacultyOne method");
	}

	@Test(priority = 10, description = "Verify that how much time is it taking to send a message from FacultyOne to FacultyTwo in group ")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description:Verify that how much time is it taking to send a message from FacultyOne to FacultyTwo in group")
	@Story("Verify that how much time is it taking to send a message from FacultyOne to FacultyTwo")
	public void testConversationTimePeriodForFacultyOnetoFacultyTwoInGroup() {
		logger.info("Starting of testConversationTimePeriodForFacultyOnetoFacultyTwoInGroup method");

		try {
			super.testSendMessage(facultyOneChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			SystemTimeAtTutorEnd = super.getConversationTimePeriod();

			super.testValidateSendMessage(facultyOneChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			//super.testValidateReceivedMessage(facultyTwoChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

			SystemTimeAtParentEnd = super.getConversationTimePeriod();
			Long TimeDifference = SystemTimeAtTutorEnd - SystemTimeAtParentEnd;

			logger.debug("Time Taken To Send a Message" + TimeDifference);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing how much time is it taking to send a message from FacultyOne to FacultyTwo in group\" : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing Verify that how much time is it taking to send a message from FacultyOne to FacultyTwo in group",
					e);
		}

		logger.info("Ending of testConversationTimePeriodForFacultyOnetoFacultyTwoInGroup method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (facultyOneDriver != null && facultyTwoDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(facultyOneDriver);
				this.initClassplusSiteLogout(facultyTwoDriver);
				this.quitDriver(WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_FacultyCreatedNewGroupChatTest);
				this.quitDriver(WEB_DRIVER.FACULTY_TWO_DRIVER_TEST_IN_FacultyCreatedNewGroupChatTest);

				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}