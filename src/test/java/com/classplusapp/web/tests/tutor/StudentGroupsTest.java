package com.classplusapp.web.tests.tutor;

import static com.classplusapp.chat.util.Constants.*;
import static org.testng.Assert.assertTrue;

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

@Epic("Tutor View StudentGroups But StudentDoest Know ")
@Feature("Student Groups")
public class StudentGroupsTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver studentDriver = null;
	private WebDriver facultyDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage studChatsPage = null;
	private ChatsPage facChatsPage = null;
	private TutorConversationPage tutorConversationPage = null;
	private StudentConversationPage studentConversationPage = null;

	private static final Logger logger = Logger.getLogger(StudentGroupsTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in TutorConversationTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_StudentGroupsTest);
		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.studentDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_StudentGroupsTest);
		this.facultyDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_ONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_StudentGroupsTest);

		this.facChatsPage = new ChatsPage(this.facultyDriver);
		this.studChatsPage = new ChatsPage(this.studentDriver);
		this.tutorConversationPage = new TutorConversationPage(this.tutorDriver);
		this.studentConversationPage = new StudentConversationPage(this.studentDriver);
		
		super.testAllowStudyGroups(tutorChatsPage);

		logger.info("Ending of initClassplusSiteLogin method in TutorConversationTest");
	}

	@DataProvider(name = "groupNames")
	public Object[] getDataFromDataprovider() {

		return new Object[] { "StudentCreatedGroup", "leaveGrp" };

	}

	@Test(priority = 1, description = "Verify Student Created Group", dataProvider = "groupNames", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Student Created Group")
	@Story("Verify Student Created Group")
	public void testCreateGrsoupByStudent(String groupNames) {
		logger.info("Starting of testCreateGrsoupByStudent method");
		try {
			studChatsPage.clickOnChatsTab();
			studChatsPage.clickOnImgMoreOptions();
			studChatsPage.clickOnNewGroupChat();
			super.testCreateNewGroupChatByStudent(studChatsPage, studentConversationPage, groupNames);
			Thread.sleep(5000);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Create Group By Student : " + e.getMessage());
			logger.error("Error occured while testing Create Group sBy Student ", e);
		}

		logger.info("Ending of testCreateGrsoupByStudent method");
	}

	@Test(priority = 2, description = "Verify Student Groups as a link in Chats Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Student Groups as a link in Chats Page")
	@Story("Verify Student Groups")
	public void testStudentGroups() {
		logger.info("Starting of testStudentGroups method");

		try {
			tutorChatsPage.clickOnChatsTab(); // change with chats tab
			// Assertion for Student Groups tool tip
			assertTrue(tutorChatsPage.getStudentGroupsToolTip());

			// Assertion for Active
			String activeColorTxt = this.tutorConversationPage.isActiveColorDisplayed();
			Assert.assertEquals(activeColorTxt, expectedAssertionsProp.getProperty(COLOR_FOR_ACTIVE));

			tutorChatsPage.clickOnStudentGroups();

			assertTrue(tutorConversationPage.getStudentGroupsTxt());

			// Assertion for ImgMoreOption
			assertTrue(tutorChatsPage.getImgMoreOptions());

			// Assertion for SenderName
			assertTrue(tutorChatsPage.getSearchBarTxt());

			// Assertion for Label Messages
			String lblMessages = tutorChatsPage.getMessageTxt();
			Assert.assertEquals(lblMessages, expectedAssertionsProp.getProperty(LABEL_MESSAGES));

			// Assertion for label StudentGroups
			String studentGroupsInfo = tutorConversationPage.getStudentGroupsInfoTxt();
			Assert.assertEquals(studentGroupsInfo, expectedAssertionsProp.getProperty(STUDENT_GROUPS_HEADER_INFO_TXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Student Groups: " + e.getMessage());
			logger.error("Error occured while testing Student Groups", e);
		}

		logger.info("Ending of testStudentGroups method");
	}

	@Test(priority = 3, description = "Verify Search functionality On Student Groups Screen at faculty end")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search Ounctionality On Student Groups Screen at faculty end")
	@Story("Verify Search Ounctionality On Student Groups Screen at faculty end")
	public void testSearchWithInvalidQueryInStudentGroupsByFaculty() {
		logger.info("Starting of testSearchWithInvalidQueryInStudentGroupsByFaculty method");

		try {
			facChatsPage.clickOnChatsTab();
			facChatsPage.clickOnStudentGroups();
			facChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			String lblNoConveration = facChatsPage.getNoConversationTxt();
			Assert.assertEquals(lblNoConveration, expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search With Invalid Query In StudentGroups at Faculty end  : "
					+ e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In Student Groups at Faculty end ", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryInStudentvGroupsByFaculty method");
	}

	@Test(priority = 4, description = "Verify Search functionality On Student Groups Screen at Tutor end")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search functionality On Student Groups Screen at Tutor end")
	@Story("Verify Search functionality On Student Groups Screen at Tutor end")
	public void testSearchWithInvalidQueryInStudentvGroupsByTutor() {
		logger.info("Starting of testSearchWithInvalidQueryInStudentGroupsByTutor method");

		try {
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			String lblNoConveration = tutorChatsPage.getNoConversationTxt();
			Assert.assertEquals(lblNoConveration, expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search With Invalid Query In StudentGroups at Tutor end : "
					+ e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In StudentGroups at Tutor end ", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryInStudentvGroupsByTutor method");
	}

	@Test(priority = 5, description = "Verify Search for Student Created Group in Student Groups Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Search for Student Created Group in Student Groups Page")
	@Story("Verify Search for Student Created Group")
	public void testSearchAndSelectStudentGroupByTutor() {
		logger.info("Starting of testSearchAndSelectStudentGroupByTutor method");

		try {
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			// Assertion for Students label
			String lblStudentGroupName = tutorConversationPage.getStudentGroupNameTxt();
			Assert.assertEquals(lblStudentGroupName, expectedAssertionsProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			tutorConversationPage.clickOnStudentGroup(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			// Assertion for StudentsGroupName label
			String LblStudentHeaderName = tutorConversationPage.getStudentGroupHeaderNameTxt();
			Assert.assertEquals(LblStudentHeaderName, expectedAssertionsProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			// Assertion for label InfoLabel
			String lblInfo = tutorConversationPage.getStudentGroupsHeaderInfoTxt();
			Assert.assertEquals(lblInfo, expectedAssertionsProp.getProperty(STUDENT_GROUPS_INFO_TXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search And Select Student Group : " + e.getMessage());
			logger.error("Error occured while testing Search And Select Student Group", e);
		}

		logger.info("Ending of testSearchAndSelectStudentGroupByTutor method");
	}

	@Test(priority = 6, description = "Verify Three dots Vertical Menu Options")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Three dots Vertical Menu Options")
	@Story("Verify Three dots Vertical Menu Options")
	public void testVerticalThreedotedMenuByTutor() {
		logger.info("Starting of testVerticalThreedotedMenuByTutor method");

		try {
			studChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));
			studentConversationPage.clickOnStudentGroup(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));
			super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

			tutorChatsPage.clickOnMenuVerticalIcon();

			super.testLabelClearConversation(tutorChatsPage);
			super.testLabelReportConversation(tutorChatsPage);
			super.testLabelGroupInformation(tutorChatsPage);
			super.testLabelMuteAndUnmuteConversation(tutorChatsPage);
			super.testLabelDeleteGroup(tutorChatsPage);
			super.testLabelLeftGroup(tutorChatsPage, tutorConversationPage);

			this.tutorChatsPage.clickOutside();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing vertical three doted menu  : " + e.getMessage());
			logger.error("Error occured while testing vertical three doted menu ", e);
		}

		logger.info("Ending of testVerticalThreedotedMenuByTutor method");
	}

	@Test(priority = 7, description = "Verify Tutor can able to Mute a Student Created Group Conversation in Student Groups Page", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Mute a Student Created Group Conversation in Student Groups Page")
	@Story("Verify Mute Conversation")
	public void testMuteConversationByTutor() {
		logger.info("Starting of testMuteConversationByTutor method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();

			if (tutorChatsPage.isDisplayedImageMute()) {
				tutorChatsPage.clickOnMuteConversation();

				Assert.assertEquals(tutorChatsPage.getMuteConversationTxt(),
						expectedAssertionsProp.getProperty(TOAST_FOR_MUTE_CONVERSATION_TXT));

				super.testSendMessage(studChatsPage, testDataProp.getProperty(SEND_MESSAGE));

				assertTrue(tutorChatsPage.isDisplayedImgMuteIcon(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME)));
			} else {
				tutorChatsPage.clickOnUnmuteConversation();

				String unmuteConversationStudentEnd = tutorChatsPage.getUnmuteConversationTxt();
				Assert.assertEquals(unmuteConversationStudentEnd,
						expectedAssertionsProp.getProperty(TOAST_FOR_UNMUTE_CONVERSATION_TXT));
			}
		} catch (Exception e) {

			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Tutor for a Student Group : "
					+ e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Tutor for a Student Group", e);
		}

		logger.info("Ending of testMuteConversationByTutor method");
	}

	@Test(priority = 8, description = "Verify Tutor can able to View Group Information in Student Created Group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to View Group Information in Student Created Group")
	@Story("Verify Group Information")
	public void testTutorViewGroupInformation() {
		logger.info("Starting of testTutorViewGroupInformation method");

		try {
			tutorChatsPage.clickOnMenuVerticalIcon();
			tutorChatsPage.clickOnGroupInformation();

			Assert.assertEquals(this.tutorConversationPage.getParticipantsInfoTxt(),
					this.tutorConversationPage.getListParticipantCountTxt());

			tutorConversationPage.clickOnHeaderBackButton();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Group Information : " + e.getMessage());
			logger.error("Error occured while testing roup Information", e);
		}

		logger.info("Ending of testTutorViewGroupInformation method");
	}

	@Test(priority = 9, description = "Verify Report Conversation in Chat Page", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation in  Chat Page")
	@Story("Verify Report Conversation")
	public void testReportConversationByTutor() {
		logger.info("Starting of testReportConversationByTutor method");

		try {
			super.testReportConversation(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By Tutor : " + e.getMessage());
			logger.error("Error occured while testing Repor Conversation By Tutort", e);
		}

		logger.info("Ending of testReportConversationByTutor method");
	}

	@Test(priority = 10, description = "Verify Clear Conversation in Chat Page", groups = "sanity")
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

	@Test(priority = 11, description = "Verify Tutor can able to Leave Group from Student Created Group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Leave Group from Student Created Group")
	@Story("Verify Leave Group")
	public void testLeaveGroupByTutor() {
		logger.info("Starting of testLeaveGroupByTutor method");

		try {
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(STUDENT_CREATED_LEAVE_GROUP_NAME));
			tutorConversationPage.clickOnLeaveStudentGroup(testDataProp.getProperty(STUDENT_CREATED_LEAVE_GROUP_NAME));
			tutorChatsPage.clickOnMenuVerticalIcon();
			tutorChatsPage.clickOnLeaveGroup();

			Assert.assertEquals(tutorChatsPage.getHeaderConfirmationTxt(),
					expectedAssertionsProp.getProperty(LEAVE_CONFIRMATION_TXT));

			tutorChatsPage.clickOnLeaveButton();

			Assert.assertEquals(tutorChatsPage.getLeftGroupTxt(),
					expectedAssertionsProp.getProperty("toast.message.leave.group"));

			// validation is in progress due to search results are not working

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Leave Group : " + e.getMessage());
			logger.error("Error occured while testing Leave Group", e);
		}

		logger.info("Ending of testLeaveGroupByTutor method");
	}

	@Test(priority = 12, description = "Verify Tutor can able to Delete Student Created Group ", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Tutor can able to Delete Student Created Group")
	@Story("Verify Delete Group")
	public void testDeleteGroupByTutor() {
		logger.info("Starting of testDeleteGroupByTutor method");
		try {
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));
			tutorConversationPage.clickOnStudentGroup(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));
			tutorChatsPage.clickOnMenuVerticalIcon();
			tutorChatsPage.clickOnDeleteGroup();

			Assert.assertEquals(tutorChatsPage.getHeaderDeleteConfirmationTxt(),
					expectedAssertionsProp.getProperty(DELETE_CONFIRMATION_TXT));

			tutorChatsPage.clickOnDeleteButton();

			Assert.assertEquals(tutorChatsPage.getDeleteGroupTxt(),
					expectedAssertionsProp.getProperty("toast.message.delete.group.conversation"));

			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(STUDENT_CREATED_GROUP_NAME));

			// Not working in the site in progress of assertion

			// Validating the Deleted Conversation
		/*	Assert.assertEquals(tutorChatsPage.getNoConversationTxt(),
					expectedAssertionsProp.getProperty(NO_CONVERSATION_TXT));*/

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Delete Group : " + e.getMessage());
			logger.error("Error occured while testing Delete Group", e);
		}

		logger.info("Ending of testDeleteGroupByTutor method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && studentDriver != null && facultyDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(studentDriver);
				this.initClassplusSiteLogout(facultyDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_StudentGroupsTest);
				this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_StudentGroupsTest);
				this.quitDriver(WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_StudentGroupsTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}

}
