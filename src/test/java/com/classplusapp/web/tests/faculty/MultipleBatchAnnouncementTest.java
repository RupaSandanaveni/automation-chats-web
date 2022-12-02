package com.classplusapp.web.tests.faculty;

import static com.classplusapp.chat.util.Constants.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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

@Epic("Multiple-Batch-Announcement")
@Feature("Make Announcement")
public class MultipleBatchAnnouncementTest extends CommonChatConversationTest {
	private WebDriver driver = null;
	private MultipleBatchAnnouncementPage multipleBatchAnnouncementPage = null;
	private ChatsPage chatsPage = null;

	private static final Logger logger = Logger.getLogger(MultipleBatchAnnouncementTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin methond");

		this.driver = super.loginClassPlusSite(browser, ORG_CODE, FACULTY_ONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_MultipleBatchAnnouncementTest);

		this.chatsPage = new ChatsPage(this.driver);
		this.multipleBatchAnnouncementPage = new MultipleBatchAnnouncementPage(this.driver);

		logger.info("Ending of initClassplusSiteLogin method");
	}

	@Test(priority = 1, description = "Verify Multiple batch announcement in moreOptions", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Multiple batch announcement in moreOptions")
	@Story("Verify Multiple batch announcement in moreOptions")
	public void testMultipleBatchAnnocument() {
		logger.info("Starting of testMultipleBatchAnnocument method");

		try {
			chatsPage.clickOnChatsTab();
			chatsPage.clickOnImgMoreOptions();
			multipleBatchAnnouncementPage.clickOnMultipleBatchAnnocument();

			// Assertion for SelectRecipients
			String lblSelectRecipients = chatsPage.getSelectRecipientsTxt();
			Assert.assertEquals(lblSelectRecipients, expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Multiple batch announcement : " + e.getMessage());
			logger.error("Error occured while testing  Multiple batch announcement", e);
		}

		logger.info("Ending of testMultipleBatchAnnocument method");
	}

	@DataProvider(name = "testDataForSearchQuery")
	public Object[][] getDataFromDataprovider() {

		return new Object[][] {
				{ testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE),
						expectedAssertionsProp.getProperty(NO_MATCHES_FOUND) },
				{ testDataProp.getProperty(SEND_MESSAGE_WITH_INVALID_QUERY),
						expectedAssertionsProp.getProperty(NO_MATCHES_FOUND) } };

	}

	@Test(priority = 2, description = "Verify Search with Invalid Queries", dataProvider = "testDataForSearchQuery")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search with Invalid Queries")
	@Story("Verify Search with Invalid Queries")
	public void testSearchWithInvalidQueryByFaculty(String invalidQuries, String noMatchesFound) {
		logger.info("Starting of testSearchWithInvalidQueryByFaculty method");

		try {
			chatsPage.clickOnSearchRecipients(invalidQuries);

			Assert.assertEquals(chatsPage.getNoMatchesFoundTxt(), noMatchesFound);

			chatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search With Invalid Query In Multi-batch-Announcement : "
					+ e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In Multi-batch-Announcement ", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryByFaculty method");
	}

	@Test(priority = 3, description = "Verify With No Filters In Multi-batch-Announcement")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Verify With No Filters In Multi-batch-Announcement")
	@Story("Verify With No Filters In Multi-batch-Announcement")
	public void testWithNoFilterOptionInMultiBatchAnnouncement() {
		logger.info("Starting of testWithNoFilterOptionInMultiBatchAnnouncement method");

		try {
			super.testWithNoFilters(chatsPage);

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
	public void testSearchAndSelectRecipientByFaculty() {
		logger.info("Starting of testSearchAndSelectRecipientByFaculty method");

		try {
			multipleBatchAnnouncementPage.clickOnSearchRecipients(testDataProp.getProperty(Constants.SEARCH_FOR_BATCH),
					testDataProp.getProperty(SEARCH_FOR_BATCH2));

			// Assertion for SelectRecipients
			String lblSelectRecipients = chatsPage.getSelectRecipientsTxt();
			Assert.assertEquals(lblSelectRecipients, expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search Recipient : " + e.getMessage());
			logger.error("Error occured while testing Search Recipient", e);
		}

		logger.info("Ending of testSearchAndSelectRecipientByFaculty method");
	}

	//@Test(priority = 5, description = "Verify Add Document in Multiple batch announcement")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document in Multiple batch announcement")
	@Story("Verify Add Document in Multiple batch announcement")
	public void testAddDocumentFile() {
		logger.info("Starting of testAddDocumentFile method");

		try {
			chatsPage.clickOnImgAttachment();
			chatsPage.clickOnDocumentLabel(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_DOC_FILE));

			// Assertion for MultipleBatchAnnouncement Title
			String lblMultipleBatchAnnouncement = multipleBatchAnnouncementPage.getTitleMultipleBatchAnnouncementTxt();
			Assert.assertEquals(lblMultipleBatchAnnouncement,
					expectedAssertionsProp.getProperty(TITLE_MULTIPLE_BATCH_ANNOUNCEMENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentFile method");
	}

	//@Test(priority = 6, description = "Verify Add Image in Multiple batch announcement")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Image in Multiple batch announcement")
	@Story("Verify Add Image in Multiple batch announcement")
	public void testAddImageFile() {
		logger.info("Starting of testAddImageFile method");

		try {
			// Test with Single Select
			super.testSelectImages(chatsPage, 'A');

			// Test with Same Image multile times
			for (int i = 0; i < 5; i++) {
				super.testSelectImages(chatsPage, 'A');
			}

			// Test With Different Images
			super.testSelectImages(chatsPage, 'E');

			// Assertion for MultipleBatchAnnouncement Title
			String lblMultipleBatchAnnouncement = multipleBatchAnnouncementPage.getTitleMultipleBatchAnnouncementTxt();
			Assert.assertEquals(lblMultipleBatchAnnouncement,
					expectedAssertionsProp.getProperty(TITLE_MULTIPLE_BATCH_ANNOUNCEMENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Image File  : " + e.getMessage());
			logger.error("Error occured while testing Add Image File ", e);
		}

		logger.info("Ending of testAddImageFile method");
	}

	@Test(priority = 7, description = "Make Announcement with attachments like Document,Image and Text", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Make Announcement with attachments like Document,Image and Text")
	@Story("Make Announcement with Attachments.")
	public void testMakeAnnouncementByFaculty() {
		logger.info("Starting of testMakeAnnouncementByFaculty method");

		try {
			chatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(Constants.SEND_MESSAGE));
			multipleBatchAnnouncementPage.clickOnSendSMS();
			chatsPage.clickOnSendButton();

			// Assertion for MultipleBatchAnnouncement Sent
			String LblMultiBatchAnnouncementSent = multipleBatchAnnouncementPage.getMultiBatchAnnouncementSentTxt();
			Assert.assertEquals(LblMultiBatchAnnouncementSent,
					expectedAssertionsProp.getProperty(LABEL_MULTIPLE_BATCH_ANNOUNCEMENT_SENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Make Annocument : " + e.getMessage());
			logger.error("Error occured while testing Make Annocument", e);
		}

		logger.info("Ending of testMakeAnnouncementByFaculty method");
	}

	@Test(priority = 8, description = "whether announcement which is made is displayed in the Announcement tab or not")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Make Announcement with attachments like Document,Image and Text")
	@Story("whether announcement which is made is displayed in the Announcement tab or not")
	public void testAnnouncementInBatchGroupAtFacultyEnd() {
		logger.info("Starting of testAnnouncementInBatchGroupAtFacultyEnd method");

		try {
			chatsPage.clickOnBatchsTab();
			chatsPage.setSearchBar(testDataProp.getProperty(SEARCH_FOR_BATCH));
			chatsPage.clickOnBatch();
			chatsPage.clickOnAnnouncementTab();

			String announcementInBatch = multipleBatchAnnouncementPage.getReceivedAnnouncementTxt();
			Assert.assertEquals(announcementInBatch, expectedAssertionsProp.getProperty(SEND_MESSAGE));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing  announcement which is made is displayed in the Announcement tab or not : "
							+ e.getMessage());
			logger.error(
					"Error occured while testing  announcement which is made is displayed in the Announcement tab or not",
					e);
		}

		logger.info("Ending of testAnnouncementInBatchGroupAtFacultyEnd method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (driver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(driver);
				this.quitDriver(WEB_DRIVER.FACULTY_ONE_DRIVER_TEST_IN_MultipleBatchAnnouncementTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}