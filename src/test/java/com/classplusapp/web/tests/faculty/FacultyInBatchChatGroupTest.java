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

import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.ClassplusLoginPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Faculty in Batch-Chat-Group")
@Feature("Batch Chat Group Conversation")
public class FacultyInBatchChatGroupTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver facultyDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage facultyChatsPage = null;
	private ClassplusLoginPage loginPage = null;

	private static final Logger logger = Logger.getLogger(FacultyInBatchChatGroupTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in BatchChatGroupTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_FacultyInBatchChatGroupTest);
		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.facultyDriver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_ONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_FacultyInBatchChatGroupTest);

		this.facultyChatsPage = new ChatsPage(this.facultyDriver);
		this.loginPage = new ClassplusLoginPage(tutorDriver);
		
		//super.testAllowStudyGroups(tutorChatsPage);

		logger.info("Ending of initClassplusSiteLogin method in BatchChatGroupTest");
	}

	@Test(priority = 1, description = "Verify UI of the already created batches groups and the groups in which added when land on chat screen.", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify UI of the already created batches groups and the groups in which added when land on chat screen.")
	@Story("Verify UI of the already created batches groups and the groups in which added when land on chat screen.")
	public void testUIOfAlreadyCreatedBatchGroupByFaculty() {
		logger.info("Starting of testUIOfAlreadyCreatedBatchGroupByFaculty method");

		try {
			super.testChatsTab(facultyChatsPage);
			facultyChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_BATCH));
			facultyChatsPage.clickOnBatchChatGroup(testDataProp.getProperty(SEARCH_FOR_BATCH));

			// Assertion for Batch label String LblStudentEndBatchGroupHeaderName =
			Assert.assertEquals(facultyChatsPage.getBatchGroupHeaderNameTxt(),
					expectedAssertionsProp.getProperty(BATCH_NAME_TXT));

			// Assertion for Student Groups tool tip
			assertTrue(facultyChatsPage.getStudentGroupsToolTip());

			// Assert for more option
			super.testMoreOptionsImage(facultyChatsPage);
			// Search Bar
			super.testSearchBarLabel(facultyChatsPage);

			// Assertion for Label Users
			Assert.assertEquals(facultyChatsPage.getMessageTxt(), expectedAssertionsProp.getProperty(LABEL_MESSAGES));

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

	@Test(priority = 2, description = "Verify three dots feature on batches created group by Faculty")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify three dots feature on batches created group by Faculty")
	@Story("Verify three dots feature on batches created group by Faculty")
	public void testVerticalThreedotedMenu() {
		logger.info("Starting of testVerticalThreedotedMenu method");

		try {
			facultyChatsPage.clickOnMenuVerticalIcon();
			// Assert for Mute or UnMute Label
			super.testLabelMuteAndUnmuteConversation(facultyChatsPage);
			this.facultyChatsPage.clickOutside();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing vertical three doted menu  : " + e.getMessage());
			logger.error("Error occured while testing vertical three doted menu ", e);
		}

		logger.info("Ending of testVerticalThreedotedMenu method");
	}

	@Test(priority = 3, description = "Verify Faculty can able to send message in Batch Group Chat Page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify Faculty can able to send message in Batch Group Chat Page")
	@Story("Verify Send Message By Faculty in Batch Group")
	public void testSendMessageByFacultyInBatchGroup() {
		logger.info("Starting of testSendMessageByFacultyInBatchGroup method");

		try {
			super.testSendMessage(facultyChatsPage, testDataProp.getProperty(SEND_MESSAGE));
			// Tutor End
			super.testChatsTab(tutorChatsPage);
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_BATCH));
			tutorChatsPage.clickOnBatchChatGroup(testDataProp.getProperty(SEARCH_FOR_BATCH));

			super.testValidateReceivedMessage(tutorChatsPage, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send Message in Batch Group By Faculty : " + e.getMessage());
			logger.error("Error occured while testing Send Message in Batch Group By Faculty", e);
		}

		logger.info("Ending of testSendMessageByFacultyInBatchGroup method");
	}

	@Test(priority = 4, description = "Verify Faculty can able to Mute and UnMute Conversation for a BatchChatGroup", invocationCount = 2, groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Faculty can able to Mute and UnMute Conversation for a BatchChatGroup")
	@Story("Verify Faculty can able to Mute and UnMute Conversation for a BatchChatGroup")
	public void testMuteOrUnmuteConversationByFaculty() {
		logger.info("Starting of testMuteOrUnmuteConversationByFaculty method");

		try {
			facultyChatsPage.clickOnMenuVerticalIcon();
			super.testMuteUnmuteConversation(facultyChatsPage, testDataProp.getProperty(BATCH_NAME_TXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Mute and Unmute Conversation By Faculty for a Batch Group : "
					+ e.getMessage());
			logger.error("Error occured while testing Mute and Unmute Conversation By Faculty for a Batch Group", e);
		}

		logger.info("Ending of testMuteOrUnmuteConversationByFaculty method");
	}

	@Test(priority = 5, description = "Verify Report Conversation By Faculty in Batch Group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Report Conversation By Faculty in Batch Group")
	@Story("Verify Report Conversation By Faculty in Batch Group")
	public void testReportConversationByFacultyInBatchGroup() {
		logger.info("Starting of testReportConversationByFacultyInBatchGroup method");

		try {
			super.testReportConversation(facultyChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Report Conversation By Faculty: " + e.getMessage());
			logger.error("Error occured while testing Repor Conversation By Faculty", e);
		}

		logger.info("Ending of testReportConversationByFacultyInBatchGroup method");
	}

	@Test(priority = 6, description = "Verify Clear Conversation By Faculty in Batch Group", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Verify Clear Conversation By Faculty in Batch Group")
	@Story("Verify Clear Conversation By Faculty in Batch Group")
	public void testClearConversationByFacultyInBatchGroup() {
		logger.info("Starting of testClearConversationByFacultyInBatchGroup method");

		try {
			super.testClearConversation(facultyChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Clear Conversation By Faculty : " + e.getMessage());
			logger.error("Error occured while testing Clear Conversation By Faculty", e);
		}

		logger.info("Ending of testClearConversationByFacultyInBatchGroup method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && facultyDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(facultyDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_FacultyInBatchChatGroupTest);
				this.quitDriver(WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_FacultyInBatchChatGroupTest);

				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}
