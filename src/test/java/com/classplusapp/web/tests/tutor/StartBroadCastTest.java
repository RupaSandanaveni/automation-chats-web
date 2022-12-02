package com.classplusapp.web.tests.tutor;

import static com.classplusapp.chat.util.Constants.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.classplusapp.chat.util.Constants;
import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.tutor.StartBroadCastPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Tutor Send Broadcast Message")
@Feature("Start a broadcast")
public class StartBroadCastTest extends CommonChatConversationTest {
	private WebDriver driver = null;
	private StartBroadCastPage startBroadCastPage = null;
	private ChatsPage chatsPage = null;
	private static final Logger logger = Logger.getLogger(StartBroadCastTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin methond");

		this.driver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_StartBroadCastTest);

		this.chatsPage = new ChatsPage(this.driver);
		this.startBroadCastPage = new StartBroadCastPage(this.driver);

		logger.info("Ending of initClassplusSiteLogin method");
	}

	@Test(priority = 1, description = "Verify Start a broadcast in moreOptions", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Start a broadcast in moreOptions")
	@Story("Verify Start a broadcast in moreOptions")
	public void testStartBroadCastByTutor() {
		logger.info("Starting of testStartBroadCastByTutor method");

		try {
			chatsPage.clickOnChatsTab();
			chatsPage.clickOnImgMoreOptions();
			startBroadCastPage.clickOnStartBroadCast();

			Assert.assertEquals(chatsPage.getSelectRecipientsTxt(),
					expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Start a broad cast: " + e.getMessage());
			logger.error("Error occured while testing Start a broad cast", e);
		}

		logger.info("Ending of testStartBroadCastByTutor method");
	}

	@Test(priority = 2, description = "Verify Search with Invalid Queries")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Search with Invalid Queries")
	@Story("Verify Search with Invalid Queries")
	public void testSearchWithInvalidQueryByTutor() {
		logger.info("Starting of testSearchWithInvalidQueryByTutor method");

		try {
			chatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

			Assert.assertEquals(chatsPage.getErrorFetchingConversationTxt(),
					expectedAssertionsProp.getProperty(ERROR_IN_FETCHING_ONVERSATION_TXT));

			chatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEND_MESSAGE_WITH_INVALID_QUERY));

			Assert.assertEquals(chatsPage.getItsEmptyOutHereTxt(),
					expectedAssertionsProp.getProperty(ITS_EMPTY_OUT_HERE_TXT));

			// chatsPage.clickOnClearQuery();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Search With Invalid Query In Start a broadcast  : "
					+ e.getMessage());
			logger.error("Error occured while testing Search With Invalid Query In Start a broadcast ", e);
		}

		logger.info("Ending of testSearchWithInvalidQueryByTutor method");
	}

	@Test(priority = 3, description = "Verify Filters in Start a broadcast Select Recipients", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Filters in Start a broadcast Select Recipients")
	@Story("Verify Filters in Start a broadcast Select Recipients")
	public void testFiltersByTutor() {
		logger.info("Starting of testFiltersByTutor method");

		try {
			chatsPage.clickOnFilters();

			Assert.assertEquals(startBroadCastPage.getFiltersTxt(), expectedAssertionsProp.getProperty(LABEL_FILTERS));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Filters: " + e.getMessage());
			logger.error("Error occured while testing Filters", e);
		}

		logger.info("Ending of testFiltersByTutor method");
	}

	@Test(priority = 4, description = "Verify Select Batches in Filter recipients", groups = "sanity")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description:Verify Select Batches in Filter recipients")
	@Story("Verify Select Batches in Filter recipients")
	public void testSelectBatchesInFilters() {
		logger.info("Starting of testSelectBatchesInFilters method");

		try {
			startBroadCastPage.clickOnViewMore();
			startBroadCastPage.clickOnSearchRecipientsInFilters(testDataProp.getProperty(Constants.SEARCH_FOR_BATCH));
			startBroadCastPage.clickOnApplyFiltersButton();

			Assert.assertEquals(chatsPage.getSelectRecipientsTxt(),
					expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select Batches in filter recipients: " + e.getMessage());
			logger.error("Error occured while testing Select Batches in filter recipients", e);
		}

		logger.info("Ending of testSelectBatchesInFilters method");
	}

	@Test(priority = 5, description = "Verify Select Recipients in Start a broadcast", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Select Recipients in Start a broadcast")
	@Story("Verify Select Recipients in Start a broadcast")
	public void testSelectRecipientsStartBroadCast() {
		logger.info("Starting of testSelectRecipientsStartBroadCast method");

		try {
			startBroadCastPage.clickOnSelectLabel();

			Assert.assertEquals(chatsPage.getSelectRecipientsTxt(),
					expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Select Batches in filter recipients: " + e.getMessage());
			logger.error("Error occured while testing Select Batches in filter recipients", e);
		}

		logger.info("Ending of testSelectRecipientsStartBroadCast method");
	}

	@Parameters({ "browser" })
	@Test(priority = 6, description = "Verify selected list  green dot or not if online in Start a broadcast Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify selected list  green dot or not if online in Start a broadcast Pagee")
	@Story("Verify selected list  green dot or not if online in Start a broadcast Page")
	public void testGreenDotInStartBroadCastByTutor(String browser) {
		logger.info("Starting of testGreenDotInStartBroadCastByTutor method");

		try {
			Assert.assertFalse(chatsPage.IsDisplayedNoGreenColor());

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing selected list  green dot or not if online in Start a broadcast Page: "
							+ e.getMessage());
			logger.error(
					"Error occured while testing selected list  green dot or not if online in Start a broadcast Page",
					e);
		}

		logger.info("Ending of testGreenDotInStartBroadCastByTutor method");
	}

	// @Test(priority = 8, description = "Verify Add Document in Multiple batch
	// announcement")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Document in Multiple batch announcement")
	@Story("Verify Add Document in Multiple batch announcement")
	public void testAddDocumentFile() {
		logger.info("Starting of testAddDocumentFile method");

		try {
			chatsPage.clickOnImgAttachment();
			chatsPage.clickOnDocumentLabel(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_DOC_FILE));

			Assert.assertEquals(startBroadCastPage.getBroadCastMessageTxt(),
					expectedAssertionsProp.getProperty(TITLE_BROADCAST_MESSAGE));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Document File : " + e.getMessage());
			logger.error("Error occured while testing Add Document File", e);
		}

		logger.info("Ending of testAddDocumentFile method");
	}

	// @Test(priority = 9, description = "Verify Add Image in Multiple batch
	// announcement")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Add Image in Multiple batch announcement")
	@Story("Verify Add Image in Multiple batch announcement")
	public void testAddImageFile() {
		logger.info("Starting of testAddImageFile method");

		try {
			// Test with Single Select
			super.testSelectImages(chatsPage, 1);

			// Test with Same Image multile times
			for (int i = 0; i < 5; i++) {
				super.testSelectImages(chatsPage, 1);
			}

			// Test With Different Images
			super.testSelectImages(chatsPage, 20);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Image File : " + e.getMessage());
			logger.error("Error occured while testing Add Image File", e);
		}

		logger.info("Ending of testAddImageFile method");
	}

	@Test(priority = 7, description = "Send Broadcast Message", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Send Broadcast Message")
	@Story("Send Broadcast Message")
	public void testSendBroadCastMessage() {
		logger.info("Starting of testSendBroadCastMessage method");

		try {
			chatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(Constants.SEND_MESSAGE));
			chatsPage.clickOnSendButton();

			Assert.assertEquals(startBroadCastPage.getSendBroadCastMessageSentTxt(),
					expectedAssertionsProp.getProperty(LABEL_BROADCAST_MESSAGE_SENT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Send BroadCast Message: " + e.getMessage());
			logger.error("Error occured while testing Send BroadCast Message", e);
		}

		logger.info("Ending of testSendBroadCastMessage method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (driver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(driver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_StartBroadCastTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}
