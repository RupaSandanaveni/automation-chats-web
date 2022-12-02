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

import com.classplusapp.chat.util.Constants;
import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.tutor.MultipleBatchAnnouncementPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Tutor Send Announcement for Multiple Batches")
@Feature("Make Announcement")
public class MultipleBatchAnnouncementTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver stuDriver = null;
	private MultipleBatchAnnouncementPage tutorMultiBatchAnnouncementPage = null;
	private MultipleBatchAnnouncementPage studentMultiBatchAnnouncementPage = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage stuChatsPage = null;

	private static final Logger logger = Logger.getLogger(MultipleBatchAnnouncementTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in MultipleBatchAnnouncementTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_MultipleBatchAnnouncementTest);
		this.stuDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_MultipleBatchAnnouncementTest);

		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.stuChatsPage = new ChatsPage(this.stuDriver);
		this.tutorMultiBatchAnnouncementPage = new MultipleBatchAnnouncementPage(this.tutorDriver);
		this.studentMultiBatchAnnouncementPage = new MultipleBatchAnnouncementPage(this.stuDriver);

		logger.info("Ending of initClassplusSiteLogin method in MultipleBatchAnnouncementTest");
	}

	@Test(priority = 1, description = "Verify Multiple batch announcement in moreOptions", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Multiple batch announcement in moreOptions")
	@Story("Verify Multiple batch announcement in moreOptions")
	public void testMultipleBatchAnnocumentByTutor() {
		logger.info("Starting of testMultipleBatchAnnocumentByTutor method");

		try {
			tutorChatsPage.clickOnChatsTab();
			tutorChatsPage.clickOnImgMoreOptions();
			tutorMultiBatchAnnouncementPage.clickOnMultipleBatchAnnocument();

			Assert.assertEquals(tutorChatsPage.getSelectRecipientsTxt(),
					expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

			Assert.assertEquals(tutorMultiBatchAnnouncementPage.getTitleMultipleBatchAnnouncementTxt(),
					expectedAssertionsProp.getProperty(TITLE_MULTIPLE_BATCH_ANNOUNCEMENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Multiple batch announcement : " + e.getMessage());
			logger.error("Error occured while testing  Multiple batch announcement", e);
		}

		logger.info("Ending of testMultipleBatchAnnocumentByTutor method");
	}

	@Test(priority = 2, description = "Verify Search with Invalid Queries")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search with Invalid Queries")
	@Story("Verify Search with Invalid Queries")
	public void testSearchWithInvalidQueryByTutor() {
		logger.info("Starting of testSearchWithInvalidQueryByTutor method");

		try {
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(tutorChatsPage.getNoMatchesFoundTxt(),
					expectedAssertionsProp.getProperty(NO_MATCHES_FOUND));

			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_INVALID_QUERY));

			Assert.assertEquals(tutorChatsPage.getNoMatchesFoundTxt(),
					expectedAssertionsProp.getProperty(NO_MATCHES_FOUND));

			tutorChatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search With Invalid Query In Multi-batch-Announcement : "
					+ e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In Multi-batch-Announcement ", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryByTutor method");
	}

	@Test(priority = 3, description = "Verify With No Filters In Multi-batch-Announcement")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Verify With No Filters In Multi-batch-Announcement")
	@Story("Verify With No Filters In Multi-batch-Announcement")
	public void testWithNoFilterOptionInMultiBatchAnnouncement() {
		logger.info("Starting of testWithNoFilterOptionInMultiBatchAnnouncement method");

		try {
			super.testWithNoFilters(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing With No Filters In Multi-batch-Announcement : " + e.getMessage());
			logger.error("Error occured while testing With No Filters In Multi-batch-Announcement ", e);
		}

		logger.info("Ending of testWithNoFilterOptionInMultiBatchAnnouncement method");
	}

	@Test(priority = 4, description = "Verify Select Recipient in Multiple batch announcement", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Select Recipient in Multiple batch announcement")
	@Story("Verify Select Recipient in Multiple batch announcementv")
	public void testSelectBatchRecipients() {
		logger.info("Starting of testSelectBatchRecipients method");

		try {
			// Assertion for SenderName
			assertTrue(tutorChatsPage.getSearchBarTxt());

			tutorMultiBatchAnnouncementPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_BATCH),
					testDataProp.getProperty(SEARCH_FOR_BATCH2));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search Recipient : " + e.getMessage());
			logger.error("Error occured while testing Search Recipient", e);
		}

		logger.info("Ending of testSelectBatchRecipients method");
	}

	// @Test(priority = 5, description = "Verify Add Document in Multiple batch
	// announcement")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document in Multiple batch announcement")
	@Story("Verify Add Document in Multiple batch announcement")
	public void testAddDocumentFile() {
		logger.info("Starting of testAddDocumentFile method");

		try {
			super.testAddDocumentFile(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentFile method");
	}

	// @Test(priority = 6, description = "Verify Add Image in Multiple batch
	// announcement")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Image in Multiple batch announcement")
	@Story("Verify Add Image in Multiple batch announcement")
	public void testAddImageFile() {
		logger.info("Starting of testAddImageFile method");

		try {
			super.testAddImageFile(tutorChatsPage);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Image File  : " + e.getMessage());
			logger.error("Error occured while testing Add Image File ", e);
		}

		logger.info("Ending of testAddImageFile method");
	}

	@Test(priority = 5, description = "Make Announcement", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Make Announcement")
	@Story("Make Announcement")
	public void testMakeAnnouncement() {
		logger.info("Starting of testMakeAnnouncement method");

		try {
			tutorChatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(Constants.SEND_MESSAGE));
			tutorMultiBatchAnnouncementPage.clickOnSendSMS();
			tutorChatsPage.clickOnSendButton();

			Assert.assertEquals(tutorMultiBatchAnnouncementPage.getMultiBatchAnnouncementSentTxt(),
					expectedAssertionsProp.getProperty(LABEL_MULTIPLE_BATCH_ANNOUNCEMENT_SENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Make Annocument : " + e.getMessage());
			logger.error("Error occured while testing Make Annocument", e);
		}

		logger.info("Ending of testMakeAnnouncement method");
	}

	@Test(priority = 6, description = "whether announcement which is made is displayed in the Announcement tab or not at Tutor end")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify swhether announcement which is made is displayed in the Announcement tab or not at Tutor end")
	@Story("Verify swhether announcement which is made is displayed in the Announcement tab or not at Tutor end")
	public void testAnnouncementInBatchGroupAtTutorEnd() {
		logger.info("Starting of testAnnouncementInBatchGroupAtTutorEnd method");

		try {
			tutorChatsPage.clickOnBatchsTab();
			tutorChatsPage.setSearchBar(testDataProp.getProperty(SEARCH_FOR_BATCH));
			tutorChatsPage.clickOnBatch();
			tutorChatsPage.clickOnAnnouncementTab();

			Assert.assertEquals(tutorMultiBatchAnnouncementPage.getReceivedAnnouncementTxt(),
					expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing  announcement which is made is displayed in the Announcement tab or not at Tutor end : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing  announcement which is made is displayed in the Announcement tab or not at Tutor end",
					e);
		}

		logger.info("Ending of testAnnouncementInBatchGroupAtTutorEnd method");
	}

	@Test(priority = 7, description = "whether announcement which is made is displayed in the Announcement tab or not at Student end")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Make Announcement with attachments like Document,Image and Text at Student end")
	@Story("whether announcement which is made is displayed in the Announcement tab or not at Student end")
	public void testAnnouncementInBatchGroupAtStudentEnd() {
		logger.info("Starting of testAnnouncementInBatchGroupAtStudentEnd method");

		try {
			stuChatsPage.clickOnBatchsTab();
			stuChatsPage.setSearchBar(testDataProp.getProperty(SEARCH_FOR_BATCH));
			stuChatsPage.clickOnBatch();
			stuChatsPage.clickOnAnnouncementTab();

			Assert.assertEquals(studentMultiBatchAnnouncementPage.getReceivedAnnouncementTxt(),
					expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing announcement which is made is displayed in the Announcement tab or not at Student end : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing  announcement which is made is displayed in the Announcement tab or not at Student end",
					e);
		}

		logger.info("Ending of testAnnouncementInBatchGroupAtStudentEnd method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && stuDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(stuDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_MultipleBatchAnnouncementTest);
				this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_MultipleBatchAnnouncementTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}