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
import com.classplusapp.web.pages.tutor.TutorConversationPage;
import com.classplusapp.web.tests.CommonChatConversationTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Stories")
@Feature("Verify Stories")
public class VerifyStoriesTest extends CommonChatConversationTest {
	private WebDriver tutorDriver = null;
	private WebDriver studentDriver = null;
	private ChatsPage tutorChatsPage = null;
	private ChatsPage studChatsPage = null;
	private TutorConversationPage tutorConversationPage = null;

	private static final Logger logger = Logger.getLogger(TutorToStudentConversationTest.class.getName());

	@BeforeClass(groups = "sanity")
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin method in TutorConversationTest");

		this.tutorDriver = super.loginClassPlusSite(browser, ORG_CODE, TUTOR_MOBILENUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.TUTOR_DRIVER_TEST_IN_VerifyStoriesTest);
		this.studentDriver = super.loginClassPlusSite(browser, ORG_CODE, STUDENTONE_MOBILE_NUMBER, EMAIL_ADDRESS,
				WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_VerifyStoriesTest);

		this.tutorChatsPage = new ChatsPage(this.tutorDriver);
		this.studChatsPage = new ChatsPage(this.studentDriver);
		this.tutorConversationPage = new TutorConversationPage(this.tutorDriver);

		logger.info("Ending of initClassplusSiteLogin method in TutorConversationTest");
	}

	@Test(priority = 1, description = "Verify Tutor can able to add a video which should be display in the chat page", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Tutor can able to add a video which should be display in the chat page")
	@Story("Verify Tutor can able to add a video which should be display in the chat page")
	public void testAddVideo() {
		logger.info("Starting of testAddVideo method");

		try {
			tutorChatsPage.clickOnBatchsTab();

			assertTrue(tutorConversationPage.getIconSearch());
			tutorChatsPage.setSearchBar(testDataProp.getProperty(SEARCH_FOR_BATCH));

			Assert.assertEquals(tutorConversationPage.getSearchBatchNameLabel(),
					expectedAssertionsProp.getProperty(SEARCH_BATCH_NAME));

			tutorChatsPage.clickOnBatch();

			Assert.assertEquals(tutorConversationPage.getVideosTabTxt(),
					expectedAssertionsProp.getProperty(VIDEO_TAB_TXT));

			tutorConversationPage.clickOnVideosTab();

			Assert.assertEquals(tutorConversationPage.getVideosTxt(), expectedAssertionsProp.getProperty(VIDEOS_TXT));

			Assert.assertEquals(tutorConversationPage.getAddButtonTxt(),
					expectedAssertionsProp.getProperty(BUTTON_ADD_VIDEO_TXT));

			tutorConversationPage.clickOnAddButton();

			Assert.assertEquals(tutorConversationPage.getNewVideoTxt(),
					expectedAssertionsProp.getProperty(NEW_VIDEO_TXT));

			tutorConversationPage.clickOnNewVideo();

			Assert.assertEquals(tutorConversationPage.getHeaderAddVideoTxt(),
					expectedAssertionsProp.getProperty(HEADER_ADD_VIDEO_TXT));

			Assert.assertEquals(tutorConversationPage.getPasteLinkTxt(),
					expectedAssertionsProp.getProperty(PASTE_LINK__TXT));

			tutorConversationPage.clickOnPasteLink();

			Assert.assertEquals(tutorConversationPage.getCheckVideoButtonTxt(),
					expectedAssertionsProp.getProperty(CHECK_VIDEO_BUTTON_TXT));

			tutorConversationPage.clickOnCheckVideo();

			Assert.assertEquals(tutorConversationPage.getHeaderYoutubeLinkTxt(),
					expectedAssertionsProp.getProperty(LABEL_HEADER_YOUTUBE_LINK));

			tutorConversationPage.clickOnCheckVideo();

			Assert.assertEquals(tutorConversationPage.getSuccessTxt(), expectedAssertionsProp.getProperty(SUCCESS_TXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Add Video" + e.getMessage());
			logger.error("Error occured while testing Add Video", e);
		}

		logger.info("Ending of testAddVideo method");
	}

	@Test(priority = 2, description = "Verify Tutor can able to view the video in batch chat group", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Tutor can able to view the video in batch chat group")
	@Story("Verify Tutor can able to view the video in batch chat group")
	public void testViewVideoMessageInBatchAtTutorEnd() {
		logger.info("Starting of testViewVideoMessageInBatchAtTutorEnd method");

		try {
			// Tutor end
			// super.testChatsTab(tutorChatsPage);
			tutorChatsPage.clickOnChatsTab();
			tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_BATCH));
			tutorChatsPage.clickOnBatchChatGroup(testDataProp.getProperty(SEARCH_FOR_BATCH));

			// Assertion for Batch label
			Assert.assertEquals(tutorChatsPage.getBatchGroupHeaderNameTxt(),
					expectedAssertionsProp.getProperty(BATCH_NAME_TXT));

			Assert.assertEquals(tutorChatsPage.getVideoAddedInBatchesTxt(),
					expectedAssertionsProp.getProperty(LABEL_VIDEO_ADDED));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing View Video by Tutor In Batch" + e.getMessage());
			logger.error("Error occured while testing View Video by Tutor In Batch", e);
		}

		logger.info("Ending of testViewVideoMessageInBatchAtTutorEnd method");
	}

	@Test(priority = 3, description = "Verify Tutor can able to view the video in batch chat group", groups = "sanity")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Tutor can able to view the video in batch chat group")
	@Story("Verify Tutor can able to view the video in batch chat group")
	public void testViewVideoMessageInBatchAtStudentEnd() {
		logger.info("Starting of testViewVideoMessageInBatchAtStudentEnd method");

		try {
			// studentEnd
			studChatsPage.clickOnChatsTab();
			studChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(SEARCH_FOR_BATCH));
			studChatsPage.clickOnBatchChatGroup(testDataProp.getProperty(SEARCH_FOR_BATCH));

			// Assertion for Batch label
			Assert.assertEquals(studChatsPage.getBatchGroupHeaderNameTxt(),
					expectedAssertionsProp.getProperty(BATCH_NAME_TXT));

			Assert.assertEquals(studChatsPage.getVideoAddedInBatchesTxt(),
					expectedAssertionsProp.getProperty(LABEL_VIDEO_ADDED));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing View Video by Student In Batch" + e.getMessage());
			logger.error("Error occured while testing View Video by Student In Batch", e);
		}

		logger.info("Ending of testViewVideoMessageInBatchAtStudentEnd method");
	}

	@Test(priority = 4, description = "Verify Tutor can able to delete a video")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description:Verify Tutor can able to delete a video")
	@Story("Verify Tutor can able to delete a video")
	public void testDeleteVideo() {
		logger.info("Starting of testDeleteVideo method");

		try {
			tutorChatsPage.clickOnBatchsTab();

			assertTrue(tutorConversationPage.getIconSearch());

			tutorChatsPage.setSearchBar(testDataProp.getProperty(SEARCH_FOR_BATCH));

			Assert.assertEquals(tutorConversationPage.getSearchBatchNameLabel(),
					expectedAssertionsProp.getProperty(SEARCH_BATCH_NAME));

			tutorChatsPage.clickOnBatch();

			Assert.assertEquals(tutorConversationPage.getVideosTabTxt(),
					expectedAssertionsProp.getProperty(VIDEO_TAB_TXT));

			tutorConversationPage.clickOnVideosTab();

			Assert.assertEquals(tutorConversationPage.getFolderNameTxt(),
					expectedAssertionsProp.getProperty(LABEL_VIDEO_FOLDER_NAME));

			tutorConversationPage.clickOnThreeDottedVerticalIcon();

			Assert.assertEquals(tutorConversationPage.getDeleteVideoTxt(),
					expectedAssertionsProp.getProperty(LABEL_DELETE_VIDEO));

			tutorConversationPage.clickOnDeleteVideo();

			Assert.assertEquals(tutorConversationPage.getDeleteInfoTxt(),
					expectedAssertionsProp.getProperty(LABEL_DELETE_INFO));

			Assert.assertEquals(tutorConversationPage.getDeleteVideoButtonTxt(),
					expectedAssertionsProp.getProperty(LABEL_DELETE_VIDOE_BUTTON));

			tutorConversationPage.clickOnDeleteVideoButton();

			Assert.assertEquals(tutorConversationPage.getSuccessTxt(), expectedAssertionsProp.getProperty(SUCCESS_TXT));

			// validating whether batch deleted or not
			tutorConversationPage.clickOnSearchVideos();

			Assert.assertEquals(tutorConversationPage.getEmptyHeadingTxt(),
					expectedAssertionsProp.getProperty(EMPTY_HEADING_TXT));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing Delete Video" + e.getMessage());
			logger.error("Error occured while testing Delete Video", e);
		}

		logger.info("Ending of testDeleteVideo method");
	}

	@AfterClass(groups = "sanity")
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			if (tutorDriver != null && studentDriver != null) {
				Thread.sleep(5000);
				this.initClassplusSiteLogout(tutorDriver);
				this.initClassplusSiteLogout(studentDriver);
				this.quitDriver(WEB_DRIVER.TUTOR_DRIVER_TEST_IN_VerifyStoriesTest);
				this.quitDriver(WEB_DRIVER.STUDENT_ONE_DRIVER_TEST_IN_VerifyStoriesTest);
				logger.info("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}
