package com.classplusapp.web.tests;

import static com.classplusapp.chat.util.Constants.*;
import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.classplusapp.chat.util.Constants;
import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.student.StudentConversationPage;
import com.classplusapp.web.pages.tutor.TutorConversationPage;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonChatConversationTest extends BaseClassplusAutomationTest {
	private static final Logger logger = Logger.getLogger(CommonChatConversationTest.class.getName());

	public void testChatsTab(ChatsPage chatsPage) {
		logger.info("Starting of testChatsTab method");

		try {
			// Checking for whether user has any Unread messages with active green dot
			checkActiveWithGreenDot(chatsPage);

			// Assertion for Chats Tab
			Assert.assertEquals(chatsPage.getChatsTabTxt(), expectedAssertionsProp.getProperty(LABEL_CHATS));

			chatsPage.clickOnChatsTab();
			// Assertion for Chats Txt
			Assert.assertEquals(chatsPage.getChatsTxt(), expectedAssertionsProp.getProperty(LABEL_CHATS));

			chatsPage.getUnreadMessageCount();

		} catch (Exception e) {
			// Assertion for Chats Tab
			Assert.assertEquals(chatsPage.getChatsTabTxt(), expectedAssertionsProp.getProperty(LABEL_CHATS));

			chatsPage.clickOnChatsTab();
			// Assertion for Chats Txt
			Assert.assertEquals(chatsPage.getChatsTxt(), expectedAssertionsProp.getProperty(LABEL_CHATS));

			chatsPage.getUnreadMessageCount();
		}

		logger.info("Ending of testChatsTab method");
	}

	public void testMoreOptionsImage(ChatsPage chatsPage) {
		logger.info("Starting of testMoreOptionsImage method");
		// Assertion for ImgMoreOption
		assertTrue(chatsPage.getImgMoreOptions());

		logger.info("Ending of testMoreOptionsImage method");
	}

	public void testMoreOptions(ChatsPage chatsPage) {
		logger.info("Starting of testMoreOptions method");

		chatsPage.clickOnImgMoreOptions();

		logger.info("Ending of testMoreOptions method");
	}

	public void testSearchBarLabel(ChatsPage chatsPage) {
		logger.info("Starting of testSearchBarLabel method");

		assertTrue(chatsPage.getSearchBarTxt());

		logger.info("Ending of testSearchBarLabel method");
	}

	public void testSearchBarLabelAtStudentEnd(ChatsPage chatsPage) {
		logger.info("Starting of testSearchBarLabelAtStudentEnd method");

		assertTrue(chatsPage.getSearchBarStudentEndTxt());

		logger.info("Ending of testSearchBarLabelAtStudentEnd method");
	}

	public void testMessageLabel(ChatsPage chatsPage) {
		logger.info("Starting of testMessageLabel method");

		Assert.assertEquals(chatsPage.getMessageTxt(), expectedAssertionsProp.getProperty(LABEL_MESSAGES));

		logger.info("Ending of testMessageLabel method");
	}

	public void checkActiveWithGreenDot(ChatsPage chatsPage) {
		logger.info("Starting of checkActiveWithGreenDot method");

		BufferedImage expectedImage = null;
		try {
			expectedImage = ImageIO
					.read(new File(System.getProperty("user.dir") + "/src/main/resources/testdata/greenDot.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(chatsPage.getImgGreenColor(), expectedImage);
		Assert.assertFalse(diff.hasDiff());

		logger.info("Ending  of checkActiveWithGreenDot method");
	}

	public String getCurrentSystemTime() {
		logger.info("Starting of getCurrentSystemTime method");

		Date time = new Date();
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm a");
		String systemTime = formatTime.format(time);

		logger.info("Ending of getCurrentSystemTime method");

		return systemTime.toUpperCase();
	}

	public Long getConversationTimePeriod() {
		logger.info("Starting of getConversationTimePeriod method");

		Date date = new Date();
		// This method returns the time in millis
		long timeMilli = date.getTime();
		logger.debug("Time in milliseconds using Date class:" + timeMilli);

		logger.info("Ending of getConversationTimePeriod method");

		return timeMilli;
	}

	public String getCurrentSystemDate() {
		logger.info("Starting of getCurrentSystemDate method");

		Date date = new Date();
		SimpleDateFormat formatTime = new SimpleDateFormat("dd:mm:yyyy");
		String systemDate = formatTime.format(date);

		logger.info("Ending of getCurrentSystemDate method");

		return systemDate;
	}

	public void testStartConversation(ChatsPage chatsPage) {
		logger.info("Starting of testStartConversation method");
		// Assertion for Start a convdersation label
		assertTrue(chatsPage.getStartConversationTxt());

		chatsPage.clickOnStartConversation();

		// Assertion for SelectRecipients
		Assert.assertEquals(chatsPage.getSelectRecipientsTxt(),
				expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		logger.info("Ending of testStartConversation method");
	}

	public void testFilters(ChatsPage chatsPage) {
		logger.info("Starting of testFilters method");
		// Assertion for ImgFilter
		assertTrue(chatsPage.isDisplayedImgFilters());

		chatsPage.clickOnFilters();

		// Assertion for Filters label
		Assert.assertEquals(chatsPage.getFiltersTxt(), expectedAssertionsProp.getProperty(LABEL_FILTERS));

		logger.info("Ending of testFilters method");
	}

	public void testSelectStudentInFilters(ChatsPage chatsPage) {
		logger.info("Starting of testSelectStudentInFilters method");

		// Assertion for Students label
		Assert.assertEquals(chatsPage.getStudentsTxt(), expectedAssertionsProp.getProperty(LABEL_STUDENTS_TXT));

		chatsPage.clickOnStudentRecipient();

		logger.info("Ending of testSelectStudentInFilters method");
	}

	public void testSelectFacultyInFilters(ChatsPage chatsPage) {
		logger.info("Starting of testSelectFacultyInFilters method");

		// Assertion for Students label
		Assert.assertEquals(chatsPage.getFacultyTxt(), expectedAssertionsProp.getProperty(LABEL_FACULTY_TXT));

		chatsPage.clickOnFacultyRecipient();

		logger.info("Ending of testSelectFacultyInFilters method");
	}

	public void testApplyButtonInFilters(ChatsPage chatsPage) {
		logger.info("Starting of testApplyButtonInFilters method");

		// Assertion for Button presence
		assertTrue(chatsPage.usDisplayedApplyButton());

		chatsPage.clickOnApplyButton();

		// Assertion for SelectRecipients
		Assert.assertEquals(chatsPage.getSelectRecipientsTxt(),
				expectedAssertionsProp.getProperty(LABEL_SELECT_RECIPIENT));

		logger.info("Ending of testApplyButtonInFilters method");
	}

	public void testSelectParentInFilters(ChatsPage chatsPage) {
		logger.info("Starting of testSelectParentInFilters method");

		// Assertion for Students label
		Assert.assertEquals(chatsPage.getParentTxt(), expectedAssertionsProp.getProperty(LABEL_PARENTS_TXT));

		chatsPage.clickOnParentRecipient();

		logger.info("Ending of testSelectParentInFilters method");
	}

	public void testSearchBar(ChatsPage chatsPage) {
		logger.info("Starting of testSearchBar method");

		// Assertion for SenderName
		assertTrue(chatsPage.getSearchBarStudentEndTxt());

		logger.info("Ending of testSearchBar method");
	}

	public void testSearchRecepientForStudent(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSearchRecepientForStudent method");

		tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_STUDENT_RECEPIENT));
		// Assertion for Students label
		Assert.assertEquals(
				tutorChatsPage.getStudentNameTxt(expectedAssertionsProp.getProperty(LABEL_STUDENT1_NAME_TXT)),
				expectedAssertionsProp.getProperty(LABEL_STUDENT1_NAME_TXT));

		logger.info("Ending of testSearchRecepientForStudent method");
	}

	public void testSearchTutorCreatedGroup(ChatsPage chatsPage) {
		logger.info("Starting of testSearchTutorCreatedGroup method");

		chatsPage.clickOnSearchRecipients(testDataProp.getProperty(TUTOR_CREATED_GROUP_NAME));
		// Assertion for TutorCreated group label
		Assert.assertEquals(
				chatsPage.getTutorCreatedGroupNameTxt(expectedAssertionsProp.getProperty(TUTOR_CREATED_GROUP_NAME)),
				expectedAssertionsProp.getProperty(TUTOR_CREATED_GROUP_NAME));

		logger.info("Ending of testSearchTutorCreatedGroup method");
	}

	public void testSearchTutorCreatedGroupByStudent(ChatsPage chatsPage) {
		logger.info("Starting of testSearchTutorCreatedGroup method");

		chatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(TUTOR_CREATED_GROUP_NAME));
		// Assertion for TutorCreated group label
		Assert.assertEquals(
				chatsPage.getTutorCreatedGroupNameTxt(expectedAssertionsProp.getProperty(TUTOR_CREATED_GROUP_NAME)),
				expectedAssertionsProp.getProperty(TUTOR_CREATED_GROUP_NAME));

		logger.info("Ending of testSearchTutorCreatedGroup method");
	}

	public void testSearchFacultyCreatedGroup(ChatsPage chatsPage) {
		logger.info("Starting of testSearchFacultyCreatedGroup method");

		chatsPage.clickOnSearchRecipients(testDataProp.getProperty(FACULTY_CREATED_GROUP_NAME));
		// Assertion for TutorCreated group label
		Assert.assertEquals(
				chatsPage.getFacultyCreatedGroupName(expectedAssertionsProp.getProperty(FACULTY_CREATED_GROUP_NAME)),
				expectedAssertionsProp.getProperty(FACULTY_CREATED_GROUP_NAME));

		logger.info("Ending of testSearchFacultyCreatedGroup method");
	}

	public void testSelectTutorCreatedGroup(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSelectTutorCreatedGroup method");

		tutorChatsPage.clickOnTutorCreatedGroup(testDataProp.getProperty(TUTOR_CREATED_GROUP_NAME));
		// Assertion for Students label
		Assert.assertEquals(tutorChatsPage.getHeaderNameTxt(),
				expectedAssertionsProp.getProperty(TUTOR_CREATED_GROUP_NAME));

		logger.info("Ending of testSelectTutorCreatedGroup method");
	}

	public void testSelectFacultyCreatedGroup(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSelectFacultyCreatedGroup method");

		tutorChatsPage.clickOnFacultyCreatedGroup(testDataProp.getProperty(FACULTY_CREATED_GROUP_NAME));
		// Assertion for Students label
		Assert.assertEquals(tutorChatsPage.getHeaderNameTxt(),
				expectedAssertionsProp.getProperty(FACULTY_CREATED_GROUP_NAME));

		logger.info("Ending of testSelectFacultyCreatedGroup method");
	}

	public void testSearchRecepientForFaculty(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSearchRecepientForFaculty method");

		tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_FACULTY_RECEPIENT));
		// Assertion for Students label
		// Assert.assertEquals(tutorChatsPage.getFacultyName(),
		// expectedAssertionsProp.getProperty(LABEL_FACULTY_NAME_TXT));

		logger.info("Ending of testSearchRecepientForFaculty method");
	}

	public void testSearchRecepientForFacultyTwo(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSearchRecepientForFacultyTwo method");

		tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_FACULTY_TWO_RECEPIENT));

		logger.info("Ending of testSearchRecepientForFacultyTwo method");
	}

	public void testSearchRecepientForTutor(ChatsPage chatsPage) {
		logger.info("Starting of testSearchRecepientForTutor method");

		chatsPage.clickOnSearchRecipients(SEARCH_FOR_TUTOR_RECEPIENT);

		// Assertion for Students label
		Assert.assertEquals(chatsPage.getTutorNameTxt(),
				SEARCH_FOR_TUTOR_RECEPIENT);

		logger.info("Ending of testSearchRecepientForTutor method");
	}
	

	public void testSearchRecepientForTutorByStudent(ChatsPage chatsPage) {
		logger.info("Starting of testSearchRecepientForTutor method");

		chatsPage.clickOnSearchRecipientsForStudent(SEARCH_FOR_TUTOR_RECEPIENT);

		// Assertion for Students label
		Assert.assertEquals(chatsPage.getTutorNameTxt(),
				SEARCH_FOR_TUTOR_RECEPIENT);

		logger.info("Ending of testSearchRecepientForTutor method");
	}

	public void testSearchRecepientForParent(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSearchRecepientForParent method");

		tutorChatsPage.clickOnSearchRecipients(testDataProp.getProperty(SEARCH_FOR_PARENT_TWO_RECEPIENT));

		logger.info("Ending of testSearchRecepientForParent method");
	}

	public void testSelectStudentRecepient(ChatsPage tutorChatsPage, TutorConversationPage tutorConversationPage) {
		logger.info("Starting of testSelectStudentRecepient method");

		tutorConversationPage.clickOnStudentRecipient(testDataProp.getProperty(STUDENT_RECEPIENT));
		// Assertion for Students label
		Assert.assertEquals(tutorChatsPage.getStudentHeaderNameTxt(),
				expectedAssertionsProp.getProperty(LABEL_STUDENT1_NAME_TXT));

		logger.info("Ending of testSelectStudentRecepient method");
	}

	public void testSelectFacultyRecepient(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSelectFacultyRecepient method");

		tutorChatsPage.clickOnFacultyRecipient(testDataProp.getProperty(FACULTY_RECEPIENT));

		Assert.assertEquals(tutorChatsPage.getHeaderNameTxt(),
				expectedAssertionsProp.getProperty(LABEL_FACULTY_NAME_TXT));

		logger.info("Ending of testSelectFacultyRecepient method");
	}

	public void testSelectFacultyTwoRecepient(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSelectFacultyTwoRecepient method");

		tutorChatsPage.clickOnFacultyTwoRecipient(testDataProp.getProperty(FACULTY_TWO_RECEPIENT));
		// Assertion for Faculty label
		Assert.assertEquals(tutorChatsPage.getHeaderNameTxt(),
				expectedAssertionsProp.getProperty(LABEL_FACULTY_TWO_TXT));

		logger.info("Ending of testSelectFacultyTwoRecepient method");
	}

	public void testSelectTutorRecepient(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSelectTutorRecepient method");

		tutorChatsPage.clickOnRecipients();

		Assert.assertEquals(tutorChatsPage.getHeaderNameTxt(),
				SEARCH_FOR_TUTOR_RECEPIENT);

		logger.info("Ending of testSelectTutorRecepient method");
	}

	public void testSelectParentRecepient(ChatsPage tutorChatsPage, TutorConversationPage tutorConversationPage) {
		logger.info("Starting of testSelectParentRecepient method");

		tutorConversationPage.clickOnParentRecipient(testDataProp.getProperty(SEARCH_FOR_PARENT_ONE_RECEPIENT));

		Assert.assertEquals(tutorChatsPage.getHeaderNameTxt(),
				expectedAssertionsProp.getProperty(LABEL_PARENT_NAME_TXT));

		logger.info("Ending of testSelectParentRecepient method");
	}

	public void checkEnableModeForChatBox(ChatsPage chatsPage, String message) {

	}

	public void testSendMessage(ChatsPage tutorChatsPage ,ChatsPage chatsPage ,String message) {
		logger.info("Starting of testSendMessage method");

		// Assertipon for chat box
		assertTrue(chatsPage.getWriteSomethingHereTxt());
		testIsDisplayedTurnOffRepliesInfo(tutorChatsPage,chatsPage);
		
		chatsPage.clickOnWriteSomeThingHereLabel(message);
		chatsPage.clickOnSendButton();

		logger.info("Ending of testSendMessage method");
	}
	
	public void testSendMessage(ChatsPage chatsPage ,String message) {
		logger.info("Starting of testSendMessage method");

		// Assertipon for chat box
		assertTrue(chatsPage.getWriteSomethingHereTxt());
		
		chatsPage.clickOnWriteSomeThingHereLabel(message);
		chatsPage.clickOnSendButton();

		logger.info("Ending of testSendMessage method");
	}
	
	public void testIsDisplayedTurnOffRepliesInfo(ChatsPage tutorChatsPage ,ChatsPage chatsPage) {
		if(chatsPage.isDisplayedTurnedOffRepliesInfoTxt()) {
			tutorChatsPage.clickOnMenuVerticalIcon();
			tutorChatsPage.clickOnTurnOnReplies();
			chatsPage.refresh();
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				
				e.getMessage();
			}
			
		}
	}

	public void testWithNoFilters(ChatsPage chatsPage) {
		logger.info("Starting of testWithNoFilters method");

		if (chatsPage.getImgFilter() == true) {
			assertTrue(chatsPage.getImgFilter());
		} else {
			Assert.assertFalse(chatsPage.getImgFilter());
			logger.debug(chatsPage.getImgFilter());
		}

		logger.info("Ending of testWithNoFilters method");
	}

	public void testSendMessageWithDifferentValues(ChatsPage chatsPage, String dataWithDifferentValues) {
		logger.info("Starting of testSendHyperLinkURL method");

		// Assertipon for chat box
		assertTrue(chatsPage.getWriteSomethingHereTxt());

		chatsPage.clickOnWriteSomeThingHereLabel(dataWithDifferentValues);
		chatsPage.clickOnSendButton();

		logger.info("Ending of testSendHyperLinkURL method");
	}

	public void testSmoothScrollIng(ChatsPage chatsPage) {
		logger.info("Starting of testSmoothScrollIng method");

		chatsPage.scrollup();
		chatsPage.scrolldown();

		logger.info("Ending of testSmoothScrollIng method");
	}

	public void testValidateSendMessage(ChatsPage chatsPage, String expectedValue) {
		logger.info("Starting of testValidateSendMessage method");

		Assert.assertEquals(chatsPage.getSendMessageTxt(), expectedValue);

		logger.info("Ending of testValidateSendMessage method");
	}

	public void testValidateReceivedMessage(ChatsPage chatsPage, String expectetdValue) {
		logger.info("Starting of testValidateReceivedMessage method");

		Assert.assertEquals(chatsPage.getReceivedMessageTxt(), expectetdValue);

		logger.info("Ending of testValidateReceivedMessage method");
	}

	public void testValidateLongMessage(ChatsPage chatsPage) {
		logger.info("Starting of testValidateLongMessage method");

		Assert.assertEquals(chatsPage.getSendMessageTxt().substring(0, chatsPage.getSendMessageTxt().length() - 9),
				expectedAssertionsProp.getProperty(SEND_MESSAGE));

		logger.info("Ending of testValidateSendMessage method");
	}

	// not usage this method
	public void testBlankMessage(ChatsPage chatsPage) {
		logger.info("Starting of testBlankMessage method");

		Assert.assertNotEquals(chatsPage.getSendMessageTxt().substring(0, chatsPage.getSendMessageTxt().length() - 9),
				expectedAssertionsProp.getProperty(SEND_MESSAGE_WITH_BLANK_SPACE));

		logger.info("Ending of testBlankMessage method");
	}

	public void testAddDocumentFile(ChatsPage chatsPage) {
		logger.info("Starting of testAddDocumentFile method");

		chatsPage.clickOnImgAttachment();
		chatsPage.clickOnDocumentLabel(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_DOC_FILE));

		// Assertion for uploaded doc for tutor end
	//	Assert.assertEquals(chatsPage.getAddDocumentTxt(), expectedAssertionsProp.getProperty(DOCUMENT_FILE_TXT));

		logger.info("Ending of testAddDocumentFile method");
	}

	public void testSelectImages(ChatsPage chatsPage, int noOfImages) {
		logger.info("Starting of testSelectImages method");

		chatsPage.clickOnImgAttachment();
		chatsPage.cllickOnImageLabel();
		int i;

		for (i = 1; i <= noOfImages; i++) {
			chatsPage.cllickOnSelectImages(
					BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE) + i + ".png");

			if (i >= 21) {
				int lblInfo = chatsPage.geAttachmentNumberTxt();
				Assert.assertNotEquals(lblInfo, i);
			} else {
				int lblInfoStudentEnd = chatsPage.geAttachmentNumberTxt();
				Assert.assertEquals(lblInfoStudentEnd, i);
			}

			if (i > 21) {

				String LblCannotUpload = chatsPage.getCannotUploadMoreImagesTxt();
				Assert.assertEquals(LblCannotUpload,
						expectedAssertionsProp.getProperty(CANNOT_UPLOAD_MORE_THAN_TWENTY_IMAGES_TXT));
			}

		}
				
		chatsPage.clickOnSendAttachments();

		logger.info("Starting of testSelectImages method");
	}

	public void testAddImageFile(ChatsPage tutorChatsPage) {
		logger.info("Starting of testAddImageFile method");

		tutorChatsPage.clickOnImgAttachment();
		tutorChatsPage.cllickOnImageLabel();

		tutorChatsPage
				.cllickOnSelectImages(BASE_DIR + FILE_SEPARATOR + testDataProp.getProperty(SELECT_IMAGE_FILE_PATH));
		tutorChatsPage.clickOnSendAttachments();

		// Assertion for uploaded img for tutor end
		assertTrue(tutorChatsPage.isDisplayedAddImage());

		logger.info("Ending of testAddImageFile method");
	}

	public void validateStudentEndTurnedOffReplieInfos(ChatsPage chatsPage) {
		logger.info("Ending of validateStudentEndTurnedOffReplieInfos method");
		// Assertion for label Chats in Home Page
		Assert.assertEquals(chatsPage.getTurnedOffRepliesInfoTxt(),
				expectedAssertionsProp.getProperty(VALIDATE_TURN_OFF_REPLIES));

		logger.info("Ending of validateStudentEndTurnedOffReplieInfos method");
	}

	public void testReportConversation(ChatsPage tutorChatsPage) {
		logger.info("Starting of testReportConversation method");
		
		tutorChatsPage.clickOnMenuVerticalIcon();
		tutorChatsPage.clickOnReportConversation();

		assertTrue(tutorChatsPage.getReportHeaderNameTxt());

		tutorChatsPage.clickOnAbusiveContentCheckBox();
		tutorChatsPage.clickOnSubmitButton();

		Assert.assertEquals(tutorChatsPage.getReportConversationTxt(),
				expectedAssertionsProp.getProperty(TOAST_FOR_REPORT_CONVERSATION_TXT));

		logger.info("Ending of testReportConversation method");
	}

	public void testClearConversation(ChatsPage chatsPage) {
		logger.info("Starting of testClearConversation method");

		chatsPage.clickOnMenuVerticalIcon();
		chatsPage.clickOnClearConversation();

		Assert.assertEquals(chatsPage.getClearConversationTxt(),
				expectedAssertionsProp.getProperty(TOAST_FOR_CLEAR_MESSAGE_TXT));

		assertTrue(chatsPage.isDisplayedImgHello());

		logger.info("Ending of testClearConversation method");
	}

	public void testCopyMessage(ChatsPage chatsPage) {
		logger.info("Starting of testCopyMessage method");

		chatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
		chatsPage.clickOnSendButton();

		chatsPage.clickOnDropDownButton();
		chatsPage.clickOnCopyMessage();

		Assert.assertEquals(chatsPage.getCopyMessageTxt(),
				expectedAssertionsProp.getProperty(TOAST_FOR_COPY_MESSAGE_TXT));

		chatsPage.setCopiedTextToWriteSomethingHereLabel();

		Assert.assertEquals(chatsPage.getSendMessageTxt(), expectedAssertionsProp.getProperty(SEND_MESSAGE));
	}

	public void testDeleteSingleMessage(ChatsPage chatsPage) {
		logger.info("Starting of testDeleteSingleMessage method");

		chatsPage.clickOnWriteSomeThingHereLabel(testDataProp.getProperty(SEND_MESSAGE));
		chatsPage.clickOnSendButton();
		chatsPage.clickOnDropDownButton();
		chatsPage.clickOnDeleteMessage();

		Assert.assertEquals(chatsPage.getDeleteMessageTxt(),
				expectedAssertionsProp.getProperty(TOAST_FOR_DELETE_MESSAGE_TXT));

		logger.debug("Validating Deleted Message " + chatsPage.getLastElementText());

		logger.info("Ending of testDeleteSingleMessage method");
	}

	public void testCannotDeleteConversation(ChatsPage chatsPage) {
		logger.info("Starting of testCannotDeleteConversation method");

		chatsPage.clickOnMenuVerticalIcon();

		Assert.assertNotEquals(chatsPage.getCopyMessageInParent(),
				expectedAssertionsProp.getProperty(DELETE_MESSAGE_TXT));

		logger.info("Ending of testCannotDeleteConversations method");
	}

	public void testSearchRecepient(ChatsPage chatsPage) {
		logger.info("Starting of testSearchRecepient method");

		chatsPage.clickOnSearchRecipients(testDataProp.getProperty(Constants.SEARCH_FOR_FACULTY_RECEPIENT));

		logger.info("Ending of testSearchRecepient method");
	}
	
	public void testAllowStudyGroups(ChatsPage tutorChatsPage) {
		logger.info("Starting of testSearchRecepient method");
		
		tutorChatsPage.clickOnDropdown();
		tutorChatsPage.clickOnSettings();
		tutorChatsPage.clickOnGeneralSettings();
		tutorChatsPage.groupStudyCheckboxOff();
		
		logger.info("Ending of testSearchRecepient method");
	}

	public void testCreateNewGroupChatByStudent(ChatsPage stuChatsPage, StudentConversationPage studentConversationPage,
			String groupNames) {
		logger.info("Starting of testCreateNewGroupChatByStudent method");

		stuChatsPage.setGroupName(groupNames);

		// Add Participants and Asserted whether added the participants or not
		stuChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(Constants.STUDENT_ONE__RECEPIENT));
		studentConversationPage
				.clickOnStudentRecipient(testDataProp.getProperty(Constants.STUDENT_ONE__RECEPIENT));

		// Assertion for AddRecipientsS
		Assert.assertEquals(studentConversationPage.getStudentOneSenderNameTxt(),
				expectedAssertionsProp.getProperty(LABEL_STUDENT2_NAME_TXT));

		// Asserting Change in Participant count
		Assert.assertEquals(stuChatsPage.getParticipantCountTxt(), stuChatsPage.getListOfParticipantsCountTxt());

		stuChatsPage.clickOnClearQueryAtStudentEnd();

		stuChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(Constants.STUDENT_TWO__RECEPIENT));
		studentConversationPage
				.clickOnStudentTwoRecipient(testDataProp.getProperty(Constants.STUDENT_TWO__RECEPIENT));

		// Assertion for AddRecipients
		Assert.assertEquals(studentConversationPage.getStudentTwoSenderNameTxt(),
				expectedAssertionsProp.getProperty(LABEL_STUDENT3_NAME_TXT));

		// Asserting Change in Participant count
		Assert.assertEquals(stuChatsPage.getParticipantCountTxt(), stuChatsPage.getListOfParticipantsCountTxt());

		stuChatsPage.clickOnClearQueryAtStudentEnd();

		stuChatsPage.clickOnSearchRecipientsForStudent(testDataProp.getProperty(Constants.STUDENT_THREE__RECEPIENT));
		studentConversationPage.clickOnStudentThreeRecipient(testDataProp.getProperty(Constants.STUDENT_THREE__RECEPIENT));

		// Assertion for AddRecipients
		Assert.assertEquals(studentConversationPage.getStudentThreeSenderNameTxt(),
				expectedAssertionsProp.getProperty(LABEL_STUDENT4_NAME_TXT));

		// Asserting Change in Participant count
		Assert.assertEquals(stuChatsPage.getParticipantCountTxt(), stuChatsPage.getListOfParticipantsCountTxt());

		stuChatsPage.clickOnClearQueryAtStudentEnd();

		stuChatsPage.clickOnThreeDottedMenu();
		stuChatsPage.clickOnRemoveParticipant();

		Assert.assertEquals(stuChatsPage.getRemoveConfirmationHeaderTxt(),
				expectedAssertionsProp.getProperty(REMOVE_PARTICIPANT_HEADER_TXT));

		Assert.assertEquals(stuChatsPage.getRemoveButtonTxt(), expectedAssertionsProp.getProperty(REMOVE_BUTTON_TXT));

		stuChatsPage.clickOnRemoveButton();

		Assert.assertEquals(stuChatsPage.getRemoveParticipantTxt(),
				expectedAssertionsProp.getProperty(REMOVE_PARTICIPANT_TXT));

		// Asserting Change in Participant count
		Assert.assertEquals(stuChatsPage.getParticipantCountTxt(), stuChatsPage.getListOfParticipantsCountTxt());

		assertTrue(stuChatsPage.isDisplayedCreateGroupButton());

		stuChatsPage.clickOnCreateGroupButton();

		// Assertion for Group creation toast;
		Assert.assertEquals(stuChatsPage.getGroupCreatedTxt(), expectedAssertionsProp.getProperty(GROUP_CREATED_TXT));

		logger.info("Ending of testCreateNewGroupChatByStudent method");
	}

	public void testLabelClearConversation(ChatsPage chatsPage) {
		logger.info("Starting of testLabelClearConversation method");

		Assert.assertEquals(chatsPage.getClearConversationLabel(),
				expectedAssertionsProp.getProperty(LABEL_CLEAR_CONVERSATION));

		logger.info("Ending of testLabelClearConversation method");
	}

	public void testLabelReportConversation(ChatsPage chatsPage) {
		logger.info("Starting of testLabelReportConversation method");

		Assert.assertEquals(chatsPage.getReportConversationLabel(),
				expectedAssertionsProp.getProperty(LABEL_REPORT_CONVERSATION));

		logger.info("Ending of testLabelReportConversation method");
	}

	public void testLabelDeleteGroup(ChatsPage chatsPage) {
		logger.info("Starting of testLabelDeleteGroup method");

		Assert.assertEquals(chatsPage.getDeleteGroupLabel(), expectedAssertionsProp.getProperty(LABEL_DELETE_GROUP));

		logger.info("Ending of testLabelDeleteGroup method");
	}

	public void testLabelLeftGroup(ChatsPage chatsPage, TutorConversationPage tutoConversationPage) {
		logger.info("Starting of testLabelLeftGroup method");

		Assert.assertEquals(tutoConversationPage.getLeaveGroupLabel(),
				expectedAssertionsProp.getProperty(LABEL_LEFT_GROUP));

		logger.info("Ending of testLabelLeftGroup method");
	}

	public void testLabelLeftGroup(ChatsPage chatsPage, StudentConversationPage studentConversationPage) {
		logger.info("Starting of testLabelLeftGroup method");

		Assert.assertEquals(studentConversationPage.getLeaveGroupLabel(),
				expectedAssertionsProp.getProperty(LABEL_LEFT_GROUP));

		logger.info("Ending of testLabelLeftGroup method");
	}

	public void testLabelBatchInformation(ChatsPage chatsPage) {
		logger.info("Starting of testLabelBatchInformation method");

		Assert.assertEquals(chatsPage.getBatchInformationTxt(),
				expectedAssertionsProp.getProperty(LABEL_BATCH_INFORMATION));

		logger.info("Ending of testLabelBatchInformation method");
	}

	public void testLabelGroupInformation(ChatsPage chatsPage) {
		logger.info("Starting of testLabelGroupInformation method");

		Assert.assertEquals(chatsPage.getGroupInformationTxt(),
				expectedAssertionsProp.getProperty(LABEL_GROUP_INFORMATION));

		logger.info("Ending of testLabelGroupInformation method");
	}

	public void testLabelTurnOnOffReplies(ChatsPage chatsPage) {
		logger.info("Starting of testLabelTurnOnOffReplies method");

		if ((expectedAssertionsProp.getProperty(LABEL_TURN_OFF_REPLIES)).equals(chatsPage.getTurnOffRepliesLabel())) {
			Assert.assertEquals(chatsPage.getTurnOffRepliesLabel(),
					expectedAssertionsProp.getProperty(LABEL_TURN_OFF_REPLIES));
		} else {
			Assert.assertEquals(chatsPage.getTurnOnRepliesLabel(),
					expectedAssertionsProp.getProperty(LABEL_TURN_ON_REPLIES));
		}
		logger.info("Ending of testLabelTurnOnOffReplies method");
	}

	public void testLabelMuteAndUnmuteConversation(ChatsPage chatsPage) {
		logger.info("Starting of testLabelMuteAndUnmuteConversation method");

		if (chatsPage.isDisplayedImageMute()) {
			Assert.assertEquals(chatsPage.getMuteConversationLabel(),
					expectedAssertionsProp.getProperty(LABEL_MUTE_CONVERSATION));

		} else {
			Assert.assertEquals(chatsPage.getUnmuteConversationLabel(),
					expectedAssertionsProp.getProperty(LABEL_UNMUTE_CONVERSATION));
		}
		logger.info("Starting of testLabelMuteAndUnmuteConversation method");
	}

	public void testMuteUnmuteConversation(ChatsPage chatsPage, String userName) {
		logger.info("Starting of testMuteUnmuteConversation method");

		if (chatsPage.isDisplayedImageMute()) {
			chatsPage.clickOnMuteConversation();

			Assert.assertEquals(chatsPage.getMuteConversationTxt(),
					expectedAssertionsProp.getProperty(TOAST_FOR_MUTE_CONVERSATION_TXT));

			testSendMessage(chatsPage, testDataProp.getProperty(SEND_MESSAGE));

			// Validating the mute conversation
			assertTrue(chatsPage.isDisplayedImgMuteIcon(userName));
		} else {
			chatsPage.clickOnUnmuteConversation();

			Assert.assertEquals(chatsPage.getUnmuteConversationTxt(),
					expectedAssertionsProp.getProperty(TOAST_FOR_UNMUTE_CONVERSATION_TXT));
		}

		logger.info("Ending of testMuteUnmuteConversation method");
	}
	
	//Faculty end
	public void testMuteUnmuteConversation(ChatsPage chatsPage, String userName,String userNumber) {
		logger.info("Starting of testMuteUnmuteConversation method");

		if (chatsPage.isDisplayedImageMute()) {
			chatsPage.clickOnMuteConversation();

			Assert.assertEquals(chatsPage.getMuteConversationTxt(),
					expectedAssertionsProp.getProperty(TOAST_FOR_MUTE_CONVERSATION_TXT));

			if(userName.equals("Raghuna")||userName.equals("Vijaya")) {
				chatsPage.clickOnSearchRecipients(userNumber);
				chatsPage.clickOnMessageCard();
				
			}else {
				chatsPage.clickOnSearchRecipients(userName);
				chatsPage.clickOnBatchChatGroup(userName);
			}

			testSendMessage(chatsPage, testDataProp.getProperty(SEND_MESSAGE));

			// Validating the mute conversation
			assertTrue(chatsPage.isDisplayedImgMuteIcon(userName));
		} else {
			chatsPage.clickOnUnmuteConversation();

			Assert.assertEquals(chatsPage.getUnmuteConversationTxt(),
					expectedAssertionsProp.getProperty(TOAST_FOR_UNMUTE_CONVERSATION_TXT));
		}

		logger.info("Ending of testMuteUnmuteConversation method");
	}
	
	/*public void testMuteUnmuteConversation(ChatsPage chatsPage, String userName) {
		logger.info("Starting of testMuteUnmuteConversation method");

		if (chatsPage.isDisplayedImageMute()) {
			chatsPage.clickOnMuteConversation();

			Assert.assertEquals(chatsPage.getMuteConversationTxt(),
					expectedAssertionsProp.getProperty(TOAST_FOR_MUTE_CONVERSATION_TXT));

			
				chatsPage.clickOnSearchRecipients(userName);
				chatsPage.clickOnMessageCard();
			
			testSendMessage(chatsPage, testDataProp.getProperty(SEND_MESSAGE));

			// Validating the mute conversation
			assertTrue(chatsPage.isDisplayedImgMuteIcon(userName));
		} else {
			chatsPage.clickOnUnmuteConversation();

			Assert.assertEquals(chatsPage.getUnmuteConversationTxt(),
					expectedAssertionsProp.getProperty(TOAST_FOR_UNMUTE_CONVERSATION_TXT));
		}

		logger.info("Ending of testMuteUnmuteConversation method");
	}
*/
	public String testHowlongChatSaved(ChatsPage chatsPage) {
		logger.info("Starting of testHowlongChatSaved method");

		String longChatDay = chatsPage.getLongChatDayTxt();

		logger.info("Ending of testHowlongChatSaved method");
		return longChatDay;
	}
}
