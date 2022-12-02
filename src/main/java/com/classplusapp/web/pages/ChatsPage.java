package com.classplusapp.web.pages;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ChatsPage extends BaseClassplusAutomationPage {

	@FindBy(xpath = "//img[@alt='close']")
	private WebElement imgClose;

	@FindBy(xpath = "//li[text()='Start a conversation']")
	private WebElement lblStartConversation;

	@FindBy(xpath = "//p[contains(text(),'Batches')]")
	private WebElement dashBoard;

	@FindBy(xpath = "//p[contains(text(),'Chats')]")
	private WebElement lblChatsTab;
	
	@FindBy(xpath = "//i[@class='dropdown icon']")
	private WebElement lblDropdown;
	
	@FindBy(xpath = "//p[contains(text(),'Settings')]")
	private WebElement btnSettings;
	
	@FindBy(xpath = "//div[text()='General Settings']")
	private WebElement btnGeneralSettings;
	
	@FindBy(xpath = "//div[text()='Group Study']/../div/div/child::div/following-sibling::div")
	private WebElement chkGroupStudyButton;

	@FindBys({
			@FindBy(xpath = "//div[@class='List_singleDigitUnreadCount__3IOvP']/parent::div/preceding-sibling::div") })
	private List<WebElement> lblListOfUsersUnreadCount;

	@FindBys({ @FindBy(xpath = "//div[@class='List_singleDigitUnreadCount__3IOvP']") })
	private List<WebElement> lblListUnreadCount;

	@FindBy(xpath = "//span[text()='Chats']")
	private WebElement lblChats;

	@FindBy(xpath = "//p[text()='Batches']")
	private WebElement lblBatchesTab;

	@FindBy(xpath = "//p[@class='batch-heading']")
	private WebElement lblBatch;

	@FindBy(xpath = "//input[@placeholder='Search for Batches']")
	private WebElement txtSearchBarInBatches;

	@FindBy(xpath = "//div[@class='tooltip']")
	private WebElement imgStudentGroupsToolTip;

	@FindBy(xpath = "//div[@class='List_unseenHeader__RnECG']")
	private WebElement lblMessages;

	@FindBy(xpath = "//div[@class='Dropdown_ChatDropdown__34tKP ChatDropdown undefined']")
	private WebElement imgMoreOptions;

	@FindBy(xpath = "//li[text()='New group chat']")
	private WebElement lblNewGroupChat;

	@FindBy(xpath = "//span[text()='Filters']")
	private WebElement lblFiltersTxt;

	@FindBy(xpath = "//lable[text()='Students']")
	private WebElement lblStudentsTxt;

	@FindBy(xpath = "//lable[text()='Faculty']")
	private WebElement lblFacultyTxt;

	@FindBy(xpath = "//lable[text()='Parents']")
	private WebElement lblParentTxt;

	@FindBy(xpath = "//div[@class='Dropdown_Btn__3FGw8 ']")
	private WebElement imgFilters;

	@FindBy(xpath = "//span[contains(text(),'Apply')]")
	private WebElement btnApply;

	@FindBy(xpath = "//input[@placeholder='Search by name or number']")
	private WebElement txtSearchBar;
	
	@FindBy(xpath = "//input[@placeholder='Search by name']")
	private WebElement txtSearchBarAtStudentEnd;

	@FindBy(xpath = "//span[text()='Gunjan']")
	private WebElement lblSenderName;

	@FindBy(xpath = "//span[text()='TutorCreatedGroup']")
	private WebElement lblTutorCreatedGroup;

	@FindBy(xpath = "//span[text()='FacultyCreatedGroup']")
	private WebElement lblFacultyCreatedGroup;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']")
	private List<WebElement> lblListOfSenderNames;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu Item_hasUnreadMsg__jKiST item undefined ']")
	private List<WebElement> lblListOfUnreadMessages;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']")
	private List<WebElement> lblListOfUsers;

	@FindBy(xpath = "//span[text()='Rupa Batch']")
	private WebElement lblBatchName;

	@FindBys({ @FindBy(xpath = "//div[@class='Dropdown_Btn__3FGw8 ']") })
	private List<WebElement> mnuThreeDottedVerticalIcon;

	@FindBys({ @FindBy(xpath = "//div[@class='Dropdown_Items__IR149  ']") })
	private List<WebElement> lblIndividualTurnOfRepllies;

	@FindBys({ @FindBy(xpath = "//li[text()='Turn' and contains(., 'off') and contains(., 'replies')]") })
	private List<WebElement> lblIndividualTurnOffrepllies;

	@FindBys({ @FindBy(xpath = "//li[text()='Turn' and contains(., 'on') and contains(., 'replies')]") })
	private List<WebElement> lblIndividualTurnOnrepllies;

	@FindBy(xpath = "//div[@class='CreateGroup_participants__23gXR']")
	private WebElement lblParticipantsInfo;

	@FindBys({ @FindBy(xpath = "//div[@class='Dropdown_Btn__3FGw8 ']") })
	private List<WebElement> lblListOfParticipantsCount;

	@FindBys({ @FindBy(xpath = "//div[@class='CreateGroup_senderName__anM05']") })
	private List<WebElement> lblListOfParticipantst;

	@FindBys({ @FindBy(xpath = "(//div[@class='MsgItem_alignSelfRight__2ff7z']/div)[last()]") })
	private List<WebElement> lblListMessageContainer;

	@FindBy(xpath = "//*[text()='Vijaya']")
	private WebElement lblFacultySenderName;

	@FindBy(xpath = "//div[contains(text(),'Raghuna ')]")
	private WebElement txtFacultyTwoSenderName;

	@FindBy(xpath = "//div[contains(text(),'Raghuna')]")
	private WebElement lblFacultyTwoSenderName;

	@FindBy(xpath = "//div[contains(text(),'Sathish')]")
	private WebElement lblFacultyThreeSenderName;

	@FindBy(xpath = "//div[text()='Vijaya']") // div[contains(text(),'Sathish')]
	private WebElement lblFacultyName;

	@FindBy(xpath = "//span[text()='Vijaya']")
	private WebElement lblFacultyNameInChatPage;

	@FindBy(xpath = "//*[contains(text(),'Gunjan')]")
	private WebElement lblTutorName;

	@FindBy(xpath = "//span[text()='Parent']")
	private WebElement lblParentName;

	@FindBy(xpath = "//*[text()='Rupa']")
	private WebElement lblStudentName;

	@FindBy(xpath = "//span[text()='TutorCreatedGroup']")
	private WebElement lblTutorCreatedGroupName;

	@FindBy(xpath = "//span[text()='FacultyCreatedGroup']")
	private WebElement lblFacultyCreatedGroupName;

	@FindBy(xpath = "(//span[text()='Vijaya'])[2]")
	private WebElement lblFacultyHeaderName;

	@FindBy(xpath = "//span[text()='Rupa']")
	private WebElement lblStudentHeaderName;

	@FindBy(xpath = "//span[text()='Rupa Batch']")
	private WebElement lblBatchHeaderName;

	@FindBy(xpath = "//input[@placeholder='Enter group name (required)']")
	private WebElement txtGroupName;

	@FindBy(xpath = "//div[@class='profile-name-container']")
	private WebElement lblHeaderNameInInfo;

	@FindBy(xpath = "//div[@class='Header_nameContainer__3MWIq']/span/span")
	private WebElement lblHeaderName;

	@FindBy(xpath = "//span/span[text()='parent']")
	private WebElement lblProfileHeaderName;
	
	@FindBy(xpath = "//div[@class='input_SearchForm__2jrd5']")
	private WebElement ele;
	
	@FindBy(xpath = "//li[text()='Leave Group']")
	private WebElement lblLeaveGroup;

	@FindBy(xpath = "//button[text()='Leave']")
	private WebElement btnLeave;

	@FindBy(xpath = "//div[text()='Successfully left the group']")
	private WebElement msgLeftGroup;

	@FindBy(xpath = "//div[@class='Header_deleteConfirmation__2zeIV']")
	private WebElement lblHeaderConfirmationTxt;

	@FindBy(xpath = "//button[@class='CreateGroup_done__2AIZr']")
	private WebElement btnCreateGroup;

	@FindBy(xpath = "//lable[text()='Students']/parent::div/child::input")
	private WebElement chkStudentRecipient;

	@FindBy(xpath = "//lable[text()='Faculty']/parent::div/child::input")
	private WebElement chkFacultyRecipient;

	@FindBy(xpath = "//lable[text()='Parents']/parent::div/child::input")
	private WebElement chkParentRecipient;

	@FindBy(xpath = "//span[text()='Select recipient(s)']")
	private WebElement lblSelectRecipients;

	@FindBy(xpath = "//span[text()='Add recipient(s)']")
	private WebElement lblAddRecipients;

	@FindBy(xpath = "//img[@class='input_left__2FOuN input_attachmentImg__3M88i']")
	private WebElement imgAttachment;

	@FindBy(xpath = "//input[@type='file']")
	private WebElement etInputFile;

	@FindBys({ @FindBy(xpath = "//div[@class='DateTime_time__2LwRn List_marginZero__39KOC']") })
	private List<WebElement> lblDateOrTimeTxt;

	@FindBy(xpath = "//span[@class='Attachment_attachmentNumb__32aOs']")
	private WebElement lblAttachmentNumber;

	@FindBy(xpath = "//div[@class='MsgItem_msg__3H9D2 MsgItem_sent__2L--y']/div/a")
	private WebElement lblImages;

	@FindBy(xpath = "(//li[text()='Remove participant'])[last()]")
	private WebElement lblRemoveParticipant;

	@FindBy(xpath = "//div[@class='CreateGroup_deleteConfirmation__1jIf4']")
	private WebElement lblRemoveParticipantHeader;

	@FindBy(xpath = "//button[text()='Remove']")
	private WebElement btnRemoveParticipant;

	@FindBy(xpath = "(//div[@class='Dropdown_Btn__3FGw8 '])[last()]")
	private WebElement imgThreeDottedVerticalIcon;

	@FindBy(xpath = "//div[text()='Removed the participant']")
	private WebElement msgRemoveParticipant;

	@FindBys({ @FindBy(xpath = "//div[@class='CreateGroup_senderName__anM05']") })
	private List<WebElement> lblListOfSenderName;

	@FindBy(xpath = "//li[text()='Documents']")
	private WebElement lblDocument;

	@FindBy(xpath = "//li[text()='Images']")
	private WebElement lblImage;

	@FindBy(xpath = "//span[@class='Attachment_attachmentNumb__32aOs']/following-sibling::img")
	private WebElement imgSendButton;

	@FindBy(xpath = "//textarea[@type='text']")
	private WebElement txtWriteSomethingHere;

	@FindBy(xpath = "//div[@class='tooltip']")
	private WebElement imgStudentGroups;

	@FindBy(xpath = "//img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAYAAABWdVznAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAABySURBVHgBlZHRDYAgDERPXYARHI1NdAUnQifQDZjDLzwIKAFD6DUv/blLmxZIclBkIYakOolGJYeZ2MxYlg2eLNAy5yE1xZE/YyspciPu2VtmCE2gEUL5wC7wXz5wCAJbeljfWV+JH/cFdXFmQ9awRdQDI96DZkVQ9CYAAAAASUVORK5CYII=']")
	private WebElement imgGreenColor;

	@FindBy(xpath = "//div[@class='Header_alertMsg__1XXpq']")
	private WebElement lblHeaderAlertMsg;

	@FindBys({ @FindBy(xpath = "//span[@class='Item_activeDot__2lLDe']") })
	private List<WebElement> lblItemActiveDot;

	@FindBy(xpath = "(//img[contains(@src,'/static/media/attachmentDownload.968d66c8.svg')])[last()]")
	private WebElement btnDownload;

	@FindBy(xpath = "//img[@class='input_right__2Z46_']")
	private WebElement btnSend;

	@FindBy(xpath = "//span[@class='Header_verticalText__2JxzK']")
	private WebElement mnuThreeDotVerticalIcon;

	@FindBy(xpath = "//li[text()='Turn' and contains(., 'On') and contains(., 'replies')]")
	private WebElement lblTurnOnReplies;

	@FindBy(xpath = "//li[text()='Turn' ]")
	private WebElement lblTurnOfReplies;

	// li[text()='Turn' and contains(., 'Off') and contains(., 'replies')]

	@FindBy(xpath = "//img[@src='/newApp/static/media/turnOffReplies.954f5a78.svg']")
	private WebElement imgTurnOfReplies;

	// img[@src='/static/media/turnOffReplies.954f5a78.svg'] without newapp

	@FindBy(xpath = "//li[text()='Mute' and contains(., 'Conversation')]")
	private WebElement lblMuteConversation;

	@FindBy(xpath = "//li[text()='Unmute' and contains(., 'Conversation')]")
	private WebElement lblUnmuteConversation;

	@FindBy(xpath = "//li[text()='Batch' and contains(., 'Information')]")
	private WebElement imgBatchInformation;

	@FindBy(xpath = "//li[text()='Mute' or text()='Unmute']")
	private WebElement imgMuteConversation;

	// img[@src='/static/media/mute.ceb7bca9.svg'](without newapp)

	@FindBy(xpath = "//img[@src='/static/media/unmute.1911ac56.svg']")
	private WebElement imgUnMuteConversation;

	// @FindBy(xpath = "(//li[@class='Header_listItems__8Wkki'])[2]")
	@FindBy(xpath = "//li[text()='Clear conversation']")
	private WebElement lblClearConversation;

	@FindBy(xpath = "//li[text()='Report Conversation']")
	private WebElement lblReportConversation;

	@FindBy(xpath = "//li[text()='Delete']")
	private WebElement lblDeleteConversation;

	@FindBy(xpath = "//div[text()='Conversation cleared successfully']")
	private WebElement msgClearConversation;

	@FindBy(xpath = "//span[text()='addDocument.pdf']")
	private WebElement lblAddDocument;

	@FindBy(xpath = "//span[text()='addImage.png']")
	private WebElement lblAddImage;

	@FindBy(xpath = "(//div[@class='MsgItem_msg__3H9D2 MsgItem_sent__2L--y']/span[not(contains(@class,'MsgItem_timeStamp__2Wifz'))])[last()]")
	private WebElement lblSentMessage;

	@FindBy(xpath = "(//div[@class='MsgItem_msg__3H9D2 MsgItem_received__2LcB0']/span[not(contains(@class,'MsgItem_timeStamp__2Wifz'))])[last()]")
	private WebElement lblRecieveddMessage;

	@FindBy(xpath = "//div[@class='Header_header__1V2oq']/following-sibling::img")
	private WebElement imgHello;

	@FindBy(xpath = "//div[@class='Report-Modal-Header']")
	private WebElement lblRepportHeaderName;

	@FindBy(xpath = "(//div[contains(@class,'ui fitted')]/following-sibling::div)[1]")
	private WebElement chkAbusiveContent;

	@FindBy(xpath = "(//div[contains(@class,'ui fitted')]/following-sibling::div)[2]")
	private WebElement chkIncludeHarassment;

	@FindBy(xpath = "(//div[contains(@class,'ui fitted')]/following-sibling::div)[3]")
	private WebElement chkIrrelevantConversation;

	@FindBy(xpath = "(//div[contains(@class,'ui fitted')]/following-sibling::div)[4]")
	private WebElement chkIrrelevantInfo;

	@FindBy(xpath = "(//div[contains(@class,'ui fitted')]/following-sibling::div)[5]")
	private WebElement chkInappropraiteBehaviour;

	@FindBy(xpath = "//button[contains(@class,'ui large')]")
	private WebElement btnSubmit;

	@FindBy(xpath = "//div[text()='Report submitted successfully. Relevant action will be taken soon!']")
	private WebElement msgReportConversation;

	@FindBy(xpath = "//div[@class='Header_alertMsg__1XXpq']")
	private WebElement txtTurnedOffReplies;

	@FindBy(xpath = "//div[text()='More than 20 files cannot be uploaded .']")
	private WebElement lblCannotUpload;

	@FindBy(xpath = "//div[text()='Replies turned  Off']")
	private WebElement msgTurnedOffReplies;

	@FindBy(xpath = "//div[text()='success']")
	private WebElement msgTurnedOffRepliesInBatchGroup;

	@FindBy(xpath = "//div[text()='Replies turned  On']")
	private WebElement msgTurnedOnReplies;

	@FindBy(xpath = "//div[text()='Conversation unmuted']")
	private WebElement msgUnmuteConversation;

	@FindBy(xpath = "//div[text()='Conversation muted']")
	private WebElement msgMuteConversation;

	@FindBy(xpath = "//div[text()='Successfully deleted the conversation']")
	private WebElement msgDeleteConversation;

	@FindBy(xpath = "//img[@alt='mute']")
	private WebElement imgMuteIcon;
	// div[@class='List_senderName__UvVhm']/span[text()='Rupa']/parent::div/following-sibling::div/img

	/*
	 * @FindBy(xpath = "//img[@class='List_muteIcon__1y1rn']") private
	 * List<WebElement> imgMuteIconInList;
	 */

	@FindBys({ @FindBy(xpath = "//div[@class='List_senderName__UvVhm']") })
	private List<WebElement> imgMuteIconInList;

	@FindBy(xpath = "//div[text()='No conversations found']")
	private WebElement msgNoConversation;

	@FindBy(xpath = "//div[text()='error in fetching conversation list']")
	private WebElement msgErrorFetchingInConversation;

	@FindBy(xpath = "//div[text()='Clear filters or add more users to see recipients here']")
	private WebElement msgitsEmptyOutHere;

	@FindBy(xpath = "//div[text()='No matches Found']")
	private WebElement msgNoMatchesFound;

	@FindBy(xpath = "//div[text()='No conversations found']")
	private WebElement msgNoConversationFouond;

	@FindBy(xpath = "(//div[@class='Dropdown_ChatDropdown__34tKP ChatDropdown undefined'])[3]  ")
	private WebElement btnDropDown;

	@FindBys({ @FindBy(xpath = "//li[text()='Delete message']") })
	private List<WebElement> lblDeleteMessage;

	@FindBys({ @FindBy(xpath = "//div[@class='MsgItem_msg__3H9D2 MsgItem_sent__2L--y']") })
	private List<WebElement> lblDeletedMessage;

	@FindBy(xpath = "(//li[text()='Copy message '])[last()]")
	private WebElement lblCopyMessage;

	@FindBy(xpath = "//div[text()='Message copied successfully']")
	private WebElement txtCopyMessage;

	@FindBy(xpath = "//div[text()='Message deleted successfully']")
	private WebElement txtDeleteMessage;

	@FindBy(xpath = "//div[text()='A group is created !']")
	private WebElement msgGroupCreated;

	@FindBy(xpath = "//li[text()='Group' and contains(., 'Information')]")
	private WebElement imgGroupInformation;

	@FindBy(xpath = "//div[text()='Successfully updated conversation']")
	private WebElement msgUpdatedGroupName;

	@FindBy(xpath = "(//span[contains(text(),'added Java Full Course 2022 | Java Tutorial For Beginners | Core Java Full Course | Simplilearn in the batch. Watch the video now')])[last()]")
	private WebElement lblVideoAddedInBatches;

	@FindBy(xpath = "//span[@class='Header_backButton__2L_EF']")
	private WebElement btnBackInGroupInformation;

	@FindBy(xpath = "//li[text()='Delete' and contains(., 'Group')]")
	private WebElement lblDeleteGroup;

	@FindBy(xpath = "//div[text()='Successfully deleted the group']")
	private WebElement msgDeleteGroup;

	@FindBy(xpath = "//div[@class='Header_deleteConfirmation__2zeIV']")
	private WebElement lblHeaderDeleteConfirmation;

	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement btnDelete;

	@FindBy(xpath = "(//span[@class='MsgItem_timeStamp__2Wifz'])[last()]")
	private WebElement lblMsgUpdatedTime;

	@FindBy(xpath = "//div[@class='DateTime_time__2LwRn undefined']")
	private WebElement lblDay;

	@FindBy(xpath = "//div[text()='We need atleast two participants in this group!']")
	private WebElement lblWeNeedParticipantsTxt;

	@FindBys({ @FindBy(xpath = "//div[@class='List_senderName__UvVhm']") })
	private List<WebElement> lblActiveUserName;

	@FindBys({ @FindBy(xpath = "//div[@class='DateTime_time__2LwRn List_marginZero__39KOC']") })
	private List<WebElement> lblDate;

	@FindBys({
			@FindBy(xpath = "//div[@class='List_senderName__UvVhm']/child::span/following-sibling::span/child::div") })
	private List<WebElement> lblLHSTimeStamp;

	@FindBys({ @FindBy(xpath = "//div[@class='DateTime_time__2LwRn undefined']") })
	private List<WebElement> lblDateAndDay;

	@FindBy(xpath = "(//li[text()='Copy message '])[last()]")
	private WebElement lblCopyMessageInParent;

	@FindBy(xpath = "(//i[@class='dropdown icon'])[last()]")
	private WebElement lblUerProfile;

	@FindBy(xpath = "//p[text()='Profile']")
	private WebElement lblProfile;

	@FindBy(xpath = "//img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAHcSURBVHgBtZVPTsJQEMa/KfgnUSMmJkY3lo1r0QPAAUzkBsJCjSvwBJQbsDPiAk5g8ATAAVTWbigbXaE1EhP593zTgqC02Er9raDvzff1debNEKZRqIfQVVKAiEFAlU/UwYoBQg19cYM+SjgL604SZPv0oq4iSAUpGoMrqIheP2tnpEzszTdSCNC9e3FGJMyYy3p6wvrbv6tGBkJomAUiDcfb2UkDfnOIHPxAiHOchnMjA/7mfEQgBH8w0BMRzomVg4CS8VEcplZAFgn4BNbb1+GRxM4SKo8f0Ftd501zYk2RdRSHR2KbiyhE11E+2IC6HHTe2EZaGtAhPJDZCyG6tYDs7SvUlSB21+edNxNFg7Led+ESFtf2V83f2p2ByPUTas32tBCVk+wquePiTOOt95v4l4Fn8WS1ieJDy02o2Sr0/xKXGFMNuBRnEOdLUFPkta46rY+XoGdxRrZzMnt+h17s1rneY5sLqD13UNLf4ZmeCFu9KK+XWQ++ImfEyXZSGTglwQ3KPwxzAGE4cHgSCZGFX7DWYLqN7gH3bz9MWGMwC5jJmcxjj+gv7dv4KW5vwHALV6BJoyO4gVBBV+bRZujT1EDLKG52XKspDk+ly0hd1nkV88ghGXYskE/Bh7Jkw6pdFAAAAABJRU5ErkJggg==']")
	private WebElement btnEdit;

	@FindBy(xpath = "//div[@class='menu-item']")
	private WebElement lblUploadPhoto;

	@FindBy(xpath = "(//img[@class='Item_img__2HaKx'])[1]")
	private WebElement imgProfile;

	@FindBy(xpath = "//span[text()='Announcements']")
	private WebElement lblAnnouncemets;

	@FindBy(xpath = "//span[@class='Item_activeDot__2lLDe']")
	private WebElement greenColor;
	

	@FindBy(xpath = "//div[@class='List_senderAndMsg__PTfMO']")
	private WebElement cardInListSendMsg;
	
	

	public ChatsPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of ChatsPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of ChatsPage method");
	}

	public boolean getProfileHeaderNameTxt() {
		logger.info("Starting of getProfileHeaderNameTxt method");
		logger.info("Ending of getProfileHeaderNameTxt method");

		try {
			return lblHeaderName.isSelected();
		} catch (Exception e) {
			return false;
		}

	}

	public void clickOutside() {
		logger.info("Starting of clickOutside method");

		//WebElement ele= driver.findElement(By.xpath("//div[@class='Details_msgsContainer__nQ3R5']"));
		Point point = ele.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		
		Actions action = new Actions(driver);
		action.moveByOffset(xcord, ycord).click().build().perform();

		logger.info("Ending of clickOutside method");
	}
	
	public void clickOnProfileHeaderNameTxt() {
		logger.info("Starting of clickOnProfileHeaderNameTxt method");

		this.lblProfileHeaderName.click();

		logger.info("Ending of clickOnProfileHeaderNameTxt method");
	}

	public String getHeaderNameInInfoTxt() {
		logger.info("Starting of getHeaderNameInInfoTxt method");
		
		hardWait(5);
		
		logger.info("Ending of getHeaderNameInInfoTxt method");

		return lblHeaderNameInInfo.getText();
	}

	public void clickOnLeaveGroup() {
		logger.info("Starting of clickOnLeaveGroup method");

		this.lblLeaveGroup.click();

		logger.info("Ending of clickOnLeaveGroup method");
	}

	public String getHeaderConfirmationTxt() {
		logger.info("Starting of getHeaderConfirmationTxt method");

		this.explicitWait(lblHeaderConfirmationTxt);

		logger.info("Ending of getHeaderConfirmationTxt method");

		return lblHeaderConfirmationTxt.getText();
	}

	public void clickOnLeaveButton() {
		logger.info("Starting of clickOnLeaveButton method");

		this.btnLeave.click();

		logger.info("Ending of clickOnLeaveButton method");
	}

	public String getLeftGroupTxt() {
		logger.info("Starting of getLeftGroupTxt method");

		this.explicitWait(msgLeftGroup);

		logger.info("Ending of getLeftGroupTxt method");

		return msgLeftGroup.getText();
	}

	public String getCopyMessageInParent() {
		logger.info("Starting of getCopyMessageInParent method");
		logger.info("Ending of getCopyMessageInParent method");

		return lblCopyMessageInParent.getText();
	}

	public void clickOnChatsTab() {
		logger.info("Starting of clickOnChatsTab method");

		try {
			this.lblChatsTab.click();

		} catch (StaleElementReferenceException e) {
			this.lblChatsTab.click();
		}

		logger.info("Ending of clickOnChatsTab method");
	}
	
	public void clickOnDropdown() {
		logger.info("Starting of clickOnSendMessage method");

		hardWait(3);
		try {
			this.lblDropdown.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblDropdown);
		}

		logger.info("Ending of clickOnSendMessage method");
	}
	
	public void clickOnSettings() {
		logger.info("Starting of clickOnSettings method");

		hardWait(3);
		try {
			this.btnSettings.click();
		} catch (Exception e) {
			this.clickOnWebElement(btnSettings);
		}

		logger.info("Ending of clickOnSettings method");
	}
	

	public void clickOnGeneralSettings() {
		logger.info("Starting of clickOnGeneralSettings method");

		try {
			this.btnGeneralSettings.click();
		} catch (Exception e) {
			this.clickOnWebElement(btnGeneralSettings);
		}
		
		logger.info("Ending of clickOnGeneralSettings method");
	}
	
	public void groupStudyCheckboxOff() {
		logger.info("Starting of groupStudyCheckboxOff method");
		hardWait(3);
		if(!isGroupStudyCheckbox())
			chkGroupStudyButton.click();
		
		logger.info("Ending of groupStudyCheckboxOff method");
	}
	
	public boolean isGroupStudyCheckbox() {
		logger.info("Starting of isGroupStudyCheckbox method");
		
		hardWait(2);
		System.out.println(chkGroupStudyButton.getAttribute("class").contains("checked"));
		
		logger.info("Ending of isGroupStudyCheckbox method");
		return chkGroupStudyButton.getAttribute("class").contains("checked");
	}

	public void clickOnBatchsTab() {
		logger.info("Starting of clickOnBatchsTab method");

		try {
			this.lblBatchesTab.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblBatchesTab);
		}

		logger.info("Ending of clickOnBatchsTab method");
	}

	public void getUnreadMessageCount() {
		logger.info("Starting of getUnreadMessageCount method");

		for (int i = 0; i < lblListOfUsersUnreadCount.size(); i++) {
			logger.debug(lblListOfUsersUnreadCount.get(i).getText());
			logger.debug(lblListUnreadCount.get(i).getText());
		}

		logger.info("Ending of getUnreadMessageCount method");
	}

	public void clickOnBatch() {
		logger.info("Starting of clickOnBatch method");

		this.lblBatch.click();

		logger.info("Ending of clickOnBatch method");
	}

	public void setSearchBar(String strBatchName) {
		logger.info("Starting setSearchBar method");

		this.explicitWait(txtSearchBarInBatches);
		this.txtSearchBarInBatches.click();
		this.txtSearchBarInBatches.sendKeys(strBatchName);

		logger.info("Ending setSearchBar method");
	}

	public void clickOnImgMoreOptions() {
		logger.info("Starting of clickOnImgMoreOptions method");

		this.clickOnWebElement(imgMoreOptions);

		logger.info("Ending of clickOnImgMoreOptions method");
	}

	public void clickOnStartConversation() {
		logger.info("Starting of clickOnStartConversation method");

		this.clickOnWebElement(lblStartConversation);

		logger.info("Ending of clickOnStartConversation method");
	}

	public void clickOnNewGroupChat() {
		logger.info("Starting of clickOnNewGroupChat method");

		this.clickOnWebElement(lblNewGroupChat);

		logger.info("Ending of clickOnNewGroupChat method");
	}

	public void clickOnRemoveParticipant() {
		logger.info("Starting of clickOnRemoveParticipant method");

		this.lblRemoveParticipant.click();

		logger.info("Ending of clickOnRemoveParticipant method");
	}

	public void clickOnRemoveButton() {
		logger.info("Starting of clickOnRemoveButton method");

		this.btnRemoveParticipant.click();

		logger.info("Ending of clickOnRemoveButton method");
	}

	public String getRemoveButtonTxt() {
		logger.info("Starting of getRemoveButtonTxt method");
		logger.info("Ending of getRemoveButtonTxt method");

		return btnRemoveParticipant.getText();
	}

	public String getRemoveParticipantTxt() {
		logger.info("Starting of getRemoveParticipantTxt method");

		this.explicitWait(msgRemoveParticipant);

		logger.info("Ending of getRemoveParticipantTxt method");

		return msgRemoveParticipant.getText();
	}

	public String getRemoveConfirmationHeaderTxt() {
		logger.info("Starting of getRemoveParticipantHeaderTxt method");

		this.explicitWait(lblRemoveParticipantHeader);

		logger.info("Ending of getRemoveParticipantHeaderTxt method");

		return lblRemoveParticipantHeader.getText();
	}

	public boolean getistOfSenderNameTxt() {
		logger.info("Starting of getistOfSenderNameTxt method");

		for (WebElement senderName : lblListOfSenderName) {
			if (senderName.getText().equals("Kumar"))
				return true;
		}

		logger.info("Ending of getistOfSenderNameTxt method");
		return false;
	}

	public void clickOnThreeDottedMenu() {
		logger.info("Starting of clickOnThreeDottedMenu method");

		this.imgThreeDottedVerticalIcon.click();

		logger.info("Ending of clickOnThreeDottedMenu method");
	}

	public void clickOnFilters() {
		logger.info("Starting of clickOnFilters method");

		try {
			this.imgFilters.click();
		} catch (Exception e) {
			this.clickOnWebElement(imgFilters);
		}

		logger.info("Ending of clickOnFilters method");
	}

	public boolean getImgFilter() {
		logger.info("Starting of getImgFilter method");
		logger.info("Ending of getImgFilter method");

		try {
			return imgFilters.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnStudentRecipient() {
		logger.info("Starting of clickOnStudentRecipient method");

		this.explicitWait(chkStudentRecipient);
		this.clickOnWebElement(chkStudentRecipient);

		logger.info("Ending of clickOnStudentRecipient method");
	}

	public void clickOnFacultyRecipient() {
		logger.info("Starting of clickOnFacultyRecipient method");

		this.explicitWait(chkFacultyRecipient);
		this.clickOnWebElement(chkFacultyRecipient);

		logger.info("Ending of clickOnFacultyRecipient method");
	}

	public void clickOnParentRecipient() {
		logger.info("Starting of clickOnParentRecipient method");

		this.explicitWait(chkParentRecipient);
		this.clickOnWebElement(chkParentRecipient);

		logger.info("Ending of clickOnParentRecipient method");
	}

	public void clickOnApplyButton() {
		logger.info("Starting of clickOnApplyButton method");

		this.btnApply.click();

		logger.info("Ending of clickOnApplyButton method");
	}

	public void clickOnSearchRecipients(String userName) {
		logger.info("Starting of clickOnSearchRecipients method");

		this.txtSearchBar.click();
		this.txtSearchBar.clear();
		this.txtSearchBar.sendKeys(userName);
		this.txtSearchBar.sendKeys(Keys.ENTER);
		hardWait(5);

		logger.info("Ending of clickOnSearchRecipients method");
	}

	public void clickOnSearchRecipientsForStudent(String userName) {
		logger.info("Starting of clickOnSearchRecipientsForStudent method");

		this.txtSearchBarAtStudentEnd.click();
		this.txtSearchBarAtStudentEnd.clear();
		this.txtSearchBarAtStudentEnd.sendKeys(userName);
		this.txtSearchBarAtStudentEnd.sendKeys(Keys.ENTER);
		hardWait(5);

		logger.info("Ending of clickOnSearchRecipientsForStudent method");
	}

	
	public void clickOnRecipients() {
		logger.info("Starting of clickOnRecipients method");

		/*
		 * try { this.pickFromWebElemetList(lblListOfUnreadMessages, userName);
		 * 
		 * }catch(Exception e) { this.pickFromWebElemetList(lblListOfSenderNames,
		 * userName); }
		 */
		this.fluentWait(lblTutorName);

		logger.info("Ending of clickOnRecipients method");
	}

	public void clickOnTutorCreatedGroup(String userName) {
		logger.info("Starting of clickOnTutorCreatedGroup method");

		this.pickFromWebElemetList(lblListOfSenderNames, userName);
		this.explicitWait(lblTutorCreatedGroup);
		this.lblTutorCreatedGroup.click();

		logger.info("Ending of clickOnTutorCreatedGroup method");
	}

	public void clickOnFacultyCreatedGroup(String userName) {
		logger.info("Starting of clickOnFacultyCreatedGroup method");

		this.pickFromWebElemetList(lblListOfSenderNames, userName);
		this.explicitWait(lblFacultyCreatedGroup);
		this.lblFacultyCreatedGroup.click();

		logger.info("Ending of clickOnFacultyCreatedGroup method");
	}

	public void setGroupName(String groupName) {
		logger.info("Starting of setGroupName method");

		this.txtGroupName.click();
		this.txtGroupName.clear();
		this.txtGroupName.sendKeys(groupName);

		logger.info("Ending of setGroupName method");
	}

	public void clickOnCreateGroupButton() {
		logger.info("Starting of clickOnCreateGroupButton method");

		this.btnCreateGroup.click();

		logger.info("Ending of clickOnCreateGroupButton method");
	}
	
	public String getHeaderNameTxt() {
		logger.info("Starting of getHeaderNameTxt method");
		logger.info("Ending of getHeaderNameTxt method");

		this.explicitWait(lblHeaderName);

		return lblHeaderName.getText();
	}

	public String getGroupNameTxt() {
		logger.info("Starting of getGroupNameTxt method");
		logger.info("Ending of getGroupNameTxt method");

		hardWait(5);

		return lblHeaderName.getText();
	}

	public boolean isDisplayedGroupNameTxt() {
		logger.info("Starting of isDisplayedGroupNameTxt method");
		logger.info("Ending of isDisplayedGroupNameTxt method");

		try {
			return txtGroupName.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnClearQueryInGroupInformation() {
		logger.info("Starting of clickOnClearQueryInGroupInformation method");

		this.txtGroupName.clear();

		logger.info("Ending of clickOnClearQueryInGroupInformation method");
	}

	public void clickOnClearQuery() {
		logger.info("Starting of clickOnClearQuery method");

		this.txtSearchBar.clear();
		this.implicitWait(Duration.ofSeconds(5L));

		logger.info("Ending of clickOnClearQuery method");
	}
	
	public void clickOnClearQueryAtStudentEnd() {
		logger.info("Starting of clickOnClearQuery method");

		this.txtSearchBarAtStudentEnd.clear();
		this.implicitWait(Duration.ofSeconds(5L));

		logger.info("Ending of clickOnClearQuery method");
	}
	
	
	
	public void clickOnImgAttachment() {
		logger.info("Starting of clickOnImgAttachment method");

		this.clickOnWebElement(imgAttachment);

		logger.info("Ending of clickOnImgAttachment method");
	}

	public void clickOnDocumentLabel(String filePath) {
		logger.info("Starting of clickOnDocumentLabel method");

		lblDocument.click();
		etInputFile.sendKeys(filePath);
		this.closeOSWindow();
		hardWait(5);
		
		logger.info("Ending of clickOnDocumentLabel method");
	}

	public void cllickOnImageLabel() {
		logger.info("Starting of cllickOnImageLabel method");

		lblImage.click();

		logger.info("Ending of cllickOnImageLabel method");
	}

	public void cllickOnSelectImages(String filePath) {
		logger.info("Starting of cllickOnSelectImages method");

		etInputFile.sendKeys(filePath);

		logger.info("Ending of cllickOnSelectImages method");
	}

	public void clickOnSendAttachments() {
		logger.info("Starting of clickOnSendAttachments method");

		imgSendButton.click();
		this.closeOSWindow();

		logger.info("Ending of clickOnSendAttachments method");
	}

	public void clickOnWriteSomeThingHereLabel(String sendMessage) {
		logger.info("Starting of clickOnWriteSomeThingHereLabel method");
		
		this.txtWriteSomethingHere.click();
		this.txtWriteSomethingHere.clear();
		this.txtWriteSomethingHere.sendKeys(sendMessage);

		logger.info("Ending of clickOnWriteSomeThingHereLabel method");
	}

	public void setCopiedTextToWriteSomethingHereLabel() {
		logger.info("Starting of setCopiedTextToWriteSomethingHereLabel method");

		Actions act = new Actions(driver);
		this.txtWriteSomethingHere.click();
		this.txtWriteSomethingHere.clear();
		act.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
		act.sendKeys(Keys.ENTER).perform();

		logger.info("Ending of setCopiedTextToWriteSomethingHereLabel method");
	}

	public void setEmoticons(String text) {
		logger.info("Starting of setEmoticons method");

		String script = "arguments[0].value=' " + text + "';";
		((JavascriptExecutor) driver).executeScript(script, txtWriteSomethingHere);
		txtWriteSomethingHere.sendKeys(Keys.ENTER);
		//this.btnSend.click();

		logger.info("Ending of setEmoticons method");
	}

	public int getNoOfCharacters(String sendMessage) {
		logger.info("Starting of getNoOfCharacters method");

		int noOfCharacters = sendMessage.length();

		logger.info("Ending of getNoOfCharacters method");
		return noOfCharacters;
	}

	public void clickOnEnterKeyUsingKeyBoard() {
		logger.info("Starting of clickOnEnterKeyUsingKeyBoard method");

		this.txtWriteSomethingHere.sendKeys(Keys.ENTER);

		logger.info("Ending of clickOnEnterKeyUsingKeyBoard method");
	}

	public void clickOnStudentGroups() {
		logger.info("Starting of clickOnStudentGroups method");

		hardWait(5);
		this.imgStudentGroups.click();

		logger.info("Ending of clickOnStudentGroups method");
	}

	public String getGroupInformationTxt() {
		logger.info("Starting of getGroupInformationTxt method");
		logger.info("Ending of getGroupInformationTxt method");

		return imgGroupInformation.getText();
	}

	public boolean isDisplayedGroupInformation() {
		logger.info("Starting of isDisplayedGroupInformation method");
		logger.info("Ending of isDisplayedGroupInformation method");

		try {
			return imgGroupInformation.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void clickOnSendButton() {
		logger.info("Starting of clickOnSendButton method");

		this.btnSend.click();
		hardWait(3);

		logger.info("Ending of clickOnSendButton method");
	}

	public void scrollup() {
		logger.info("Starting of scrollup method");

		this.scrollDown(500);

		logger.info("Ending of scrollup method");
	}

	public void scrolldown() {
		logger.info("Starting of scrolldown method");

		this.scrollDown(-200);

		logger.info("Ending of scrolldown method");
	}

	public void clickOnMenuVerticalIcon() {
		logger.info("Starting of clickOnMenuVerticalIcon method");
		
		try {
			hardWait(5);
			//this.explicitWait(mnuThreeDotVerticalIcon);
			this.mnuThreeDotVerticalIcon.click();
		}catch(Exception e) {
			hardWait(5);
			this.clickOnWebElement(mnuThreeDotVerticalIcon);		
			}
		
		logger.info("Ending of clickOnMenuVerticalIcon method");
	}
	
	public boolean isDisplayedMenuVerticalIcon() {
		logger.info("Starting of isDisplayedMenuVerticalIcon method");
		logger.info("Ending of isDisplayedMenuVerticalIcon method");
		
		return mnuThreeDotVerticalIcon.isDisplayed();
	}

	public void clickOutsideInUserPage() {
		logger.info("Starting of clickOutsideInUserPage method");

		// Actions action = new Actions(driver);
		// action.moveByOffset(400, 600).click().build().perform();
		this.lblChats.click();

		logger.info("Ending of clickOutsideInUserPage method");
	}

	public void clickOnTurnOnReplies() {
		logger.info("Starting of clickOnTurnOnReplies method");

		try {
			this.lblTurnOnReplies.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblTurnOnReplies);

		}

		logger.info("Ending of clickOnTurnOnReplies method");
	}

	public void clickOnTurnOffReplies() {
		logger.info("Starting of clickOnTurnOfReplies method");

	/*	try {
			
			this.lblTurnOfReplies.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblTurnOfReplies);

		}
*/
		this.explicitWait(lblTurnOfReplies);
		this.lblTurnOfReplies.click();
		
		logger.info("Ending of clickOnTurnOfReplies method");
	}

	public void clickOnMuteConversation() {
		logger.info("Starting of clickOnMuteConversation method");

		this.lblMuteConversation.click();

		logger.info("Ending of clickOnMuteConversation method");
	}

	public void clickOnUnmuteConversation() {
		logger.info("Starting of clickOnMuteOrUnmuteConversation method");

		this.explicitWait(lblUnmuteConversation);
		this.lblUnmuteConversation.click();

		logger.info("Ending of clickOnMuteOrUnmuteConversation method");
	}

	public void clickOnClearConversation() {
		logger.info("Starting of clickOnClearConversation method");

		try {
			this.lblClearConversation.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblClearConversation);
		}

		logger.info("Ending of clickOnClearConversation method");
	}

	public void clickOnReportConversation() {
		logger.info("Starting of clickOnReportConversation method");

		this.lblReportConversation.click();

		logger.info("Ending of clickOnReportConversation method");
	}

	public String getReportConversationLabel() {
		logger.info("Starting of getReportConversationLabel method");
		
		hardWait(3);
		
		logger.info("Ending of getReportConversationLabel method");

		return lblReportConversation.getText();
	}

	public String getClearConversationLabel() {
		logger.info("Starting of getClearConversationLabel method");

		hardWait(3);
		logger.debug(lblClearConversation.getText());

		logger.info("Ending of getClearConversationLabel method");

		return lblClearConversation.getText();
	}

	public String getMuteConversationLabel() {
		logger.info("Starting of getMuteConversationLabel method");
	
		hardWait(3);
		logger.info("Ending of getMuteConversationLabel method");

		return lblMuteConversation.getText();
	}

	public String getTurnOnRepliesLabel() {
		logger.info("Starting of getTurnOnRepliesLabel method");
		logger.info("Ending of getTurnOnRepliesLabel method");

		return lblTurnOnReplies.getText();
	}

	public String getTurnOffRepliesLabel() {
		logger.info("Starting of getTurnOffRepliesLabel method");
		logger.info("Ending of getTurnOffRepliesLabel method");

		return lblTurnOfReplies.getText();
	}

	public String getUnmuteConversationLabel() {
		logger.info("Starting of getUnmuteConversationLabel method");
		
		hardWait(3);
		
		logger.info("Ending of getUnmuteConversationLabel method");

		return lblUnmuteConversation.getText();
	}

	public String getBatchInformationTxt() {
		logger.info("Starting of getBatchInformationTxt method");
		logger.info("Ending of getBatchInformationTxt method");

		return imgBatchInformation.getText();
	}

	public void clickOnAbusiveContentCheckBox() {
		logger.info("Starting of clickOnAbusiveContentCheckBox method");

		this.chkAbusiveContent.click();

		logger.info("Ending of clickOnAbusiveContentCheckBox method");
	}

	public void clickOnSubmitButton() {
		logger.info("Starting of clickOnSubmitButton method");

		this.btnSubmit.click();

		logger.info("Ending of clickOnSubmitButton method");
	}

	public void clickOnDropDownButton() {
		logger.info("Starting of clickOnDropDownButton method");

		WebElement lastElement = lblListMessageContainer.get(lblListMessageContainer.size() - 1);
		lastElement.click();

		logger.info("Ending of clickOnDropDownButton method");
	}

	public void clickOnDeleteMessage() {
		logger.info("Starting of clickOnDeleteMessage method");

		WebElement lastElement = lblDeleteMessage.get(lblDeleteMessage.size() - 1);

		try {
			lastElement.click();
		} catch (Exception e) {
			this.clickOnWebElement(lastElement);
		}

		logger.info("Ending of clickOnDeleteMessage method");
	}

	public String getLastElementText() {
		logger.info("Starting of getLastElementText method");

		WebElement lastElement = lblDeletedMessage.get(lblDeletedMessage.size() - 1);
		System.out.println(lastElement.getText() + "Last element text");
		logger.info("Starting of getLastElementText method");

		return lastElement.getText();
	}

	public void clickOnCopyMessage() {
		logger.info("Starting of clickOnCopyMessage method");

		try {
			lblCopyMessage.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblCopyMessage);
		}

		logger.info("Ending of clickOnCopyMessage method");
	}

	public void clickOnFacultyRecipient(String userName) {
		logger.info("Starting of clickOnFacultyRecipient method");

		try {
			this.lblFacultySenderName.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblFacultySenderName);
		}

		logger.info("Ending of clickOnFacultyRecipient method");
	}
	
	public void clickOnMessageCard() {
		logger.info("Starting of clickOnFacultyRecipient method");

		hardWait(5);
		fluentWait(cardInListSendMsg);

		logger.info("Ending of clickOnFacultyRecipient method");
	}

	public void clickOnFacultyTwoRecipient(String userName) {
		logger.info("Starting of clickOnFacultyTwoRecipient method");

		this.fluentWait(lblFacultyTwoSenderName);

		logger.info("Ending of clickOnFacultyTwoRecipient method");
	}

	public void clickOnFacultyThreeRecipient(String userName) {
		logger.info("Starting of clickOnFacultyThreeRecipient method");

		this.fluentWait(lblFacultyThreeSenderName);

		logger.info("Ending of clickOnFacultyThreeRecipient method");
	}

	public void clickOnGroupInformation() {
		logger.info("Starting of clickOnGroupInformation method");

		this.imgGroupInformation.click();

		logger.info("Ending of clickOnGroupInformation method");
	}

	public String getUpdatedGroupNameTxt() {
		logger.info("Starting of getUpdatedGroupNameTxt method");
		logger.info("Ending of getUpdatedGroupNameTxt method");

		return msgUpdatedGroupName.getText();
	}

	public String getChatsTabTxt() {
		logger.info("Starting of getChatsTabTxt method");

		hardWait(2);

		logger.info("Ending of getChatsTabTxt method");

		return lblChatsTab.getText();
	}

	public boolean getStudentGroupsToolTip() {
		logger.info("Starting of getStudentGroupsToolTip method");
		logger.info("Ending of getStudentGroupsToolTip method");

		try {
			return imgStudentGroupsToolTip.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getMessageTxt() {
		logger.info("Starting of getMessageTxt method");
		logger.info("Ending of getMessageTxt method");

		return lblMessages.getText();
	}

	public boolean getStartConversationTxt() {
		logger.info("Starting of getStartConversationTxt method");
		logger.info("Ending of getStartConversationTxt method");

		try {
			return lblStartConversation.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public BufferedImage getProfileImage() {
		logger.info("Starting of getProfileImage method");

		BufferedImage img = null;
		URL url;
		try {
			url = new URL(imgProfile.getAttribute("src"));
			img = ImageIO.read(url);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		logger.info("Ending of getProfileImage method");
		return img;
	}

	public String getChatsTxt() {
		logger.info("Starting of getChatsTxt method");

		this.fluentWait(lblChats);

		logger.info("Ending of getChatsTxt method");

		return lblChats.getText();
	}

	public boolean getSearchBarTxt() {
		logger.info("Starting of getSearchBarTxt method");
		logger.info("Ending of getSearchBarTxt method");

		try {
			return txtSearchBar.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	

	public boolean getSearchBarStudentEndTxt() {
		logger.info("Starting of getSearchBarStudentEndTxt method");
		logger.info("Ending of getSearchBarStudentEndTxt method");

		try {
			return txtSearchBarAtStudentEnd.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getTutorNameTxt() {
		logger.info("Starting of getTutorNameTxt method");
		logger.info("Ending of getTutorNameTxt method");

		return lblTutorName.getText();
	}

	public String getStudentNameTxt(String userName) {
		logger.info("Starting of getStudentNameTxt method");

		// this.implicitWait(Duration.ofSeconds(10000));

		logger.info("Ending of getStudentNameTxt method");

		return lblStudentName.getText();
	}

	public String getTutorCreatedGroupNameTxt(String userName) {
		logger.info("Starting of getTutorCreatedGroupNameTxt method");

		this.pickFromWebElemetList(lblListOfSenderNames, userName);

		logger.info("Ending of getTutorCreatedGroupNameTxt method");

		return lblTutorCreatedGroupName.getText();
	}

	public String getFacultyCreatedGroupName(String userName) {
		logger.info("Starting of getFacultyCreatedGroupName method");

		this.pickFromWebElemetList(lblListOfSenderNames, userName);

		logger.info("Ending of getFacultyCreatedGroupName method");

		return lblFacultyCreatedGroupName.getText();
	}

	public String getStudentHeaderNameTxt() {
		logger.info("Starting of getStudentHeaderNameTxt method");

		hardWait(3);

		logger.info("Ending of getStudentHeaderNameTxt method");

		return lblStudentHeaderName.getText();
	}

	public String getBatchGroupHeaderNameTxt() {
		logger.info("Starting of getBatchGroupHeaderNameTxt method");
		logger.info("Ending of getBatchGroupHeaderNameTxt method");

		return lblBatchHeaderName.getText();
	}

	public boolean getImgMoreOptions() {
		logger.info("Starting of getImgMoreOptions method");
		logger.info("Ending of getImgMoreOptions method");

		try {
			return imgMoreOptions.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean getNewGroupChatTxt() {
		logger.info("Starting of getNewGroupChatTxt method");
		logger.info("Ending of getNewGroupChatTxt method");

		try {
			return lblNewGroupChat.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getFiltersTxt() {
		logger.info("Starting of getFiltersTxt method");
		logger.info("Ending of getFiltersTxt method");

		return lblFiltersTxt.getText();
	}

	public String getStudentsTxt() {
		logger.info("Starting of getStudentsTxt method");
		logger.info("Ending of getStudentsTxt method");

		this.explicitWait(lblStudentsTxt);

		return lblStudentsTxt.getText();
	}

	public String getFacultyTxt() {
		logger.info("Starting of getFacultyTxt method");
		logger.info("Ending of getFacultyTxt method");

		this.explicitWait(lblFacultyTxt);

		return lblFacultyTxt.getText();
	}

	public String getParentTxt() {
		logger.info("Starting of getParentTxt method");
		logger.info("Ending of getParentTxt method");

		this.explicitWait(lblParentTxt);

		return lblParentTxt.getText();
	}

	public String getSelectRecipientsTxt() {
		logger.info("Starting of getSelectRecipientsTxt method");
		logger.info("Ending of getSelectRecipientsTxt method");

		return lblSelectRecipients.getText();
	}

	public boolean isDisplayedImgFilters() {
		logger.info("Starting of isDisplayedImgFilters method");
		logger.info("Ending of isDisplayedImgFilters method");

		try {
			return imgFilters.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean usDisplayedApplyButton() {
		logger.info("Starting of getBtnApply method");
		logger.info("Ending of getBtnApply method");

		try {
			return btnApply.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean getWriteSomethingHereTxt() {
		logger.info("Starting of getWriteSomethingHereTxt method");
		logger.info("Ending of getWriteSomethingHereTxt method");

		try {
			hardWait(2);
			return txtWriteSomethingHere.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public void clickOnClearQueryInWriteSomethingHere() {
		logger.info("Starting of clickOnClearQueryInWriteSomethingHere method");

		this.txtWriteSomethingHere.clear();

		logger.info("Ending of clickOnClearQueryInWriteSomethingHere method");
	}

	public String getAddDocumentTxt() {
		logger.info("Starting of getAddDocumentTxt method");

		this.explicitWait(lblAddDocument);

		logger.info("Ending of getAddDocumentTxt method");

		return lblAddDocument.getText();
	}

	public boolean isDisplayedAddImage() {
		logger.info("Starting of isDisplayedAddImage method");
		logger.info("Ending of isDisplayedAddImage method");

		try {
			return lblAddImage.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public String getSendMessageTxt() {
		logger.info("Starting of getSendMessageTxt method");

		this.explicitWait(lblSentMessage);
		logger.info("Ending of getSendMessageTxt method");

		return lblSentMessage.getText();
	}

	public String getReceivedMessageTxt() {
		logger.info("Starting of getReceivedMessageTxt method");

		hardWait(5);

		logger.info("Ending of getReceivedMessageTxt method");

		return lblRecieveddMessage.getText();
	}

	public String getClearConversationTxt() {
		logger.info("Starting of getClearConversationTxt method");
		logger.info("Ending of getClearConversationTxt method");

		this.explicitWait(msgClearConversation);

		return msgClearConversation.getText();
	}

	public boolean isDisplayedImgHello() {
		logger.info("Starting of isDisplayedImgHello method");
		logger.info("Ending of isDisplayedImgHello method");

		try {
			return imgHello.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean getReportHeaderNameTxt() {
		logger.info("Starting of getReportHeaderNameTxt method");
		logger.info("Ending of getReportHeaderNameTxt method");

		try {
			return lblRepportHeaderName.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public String getReportConversationTxt() {
		logger.info("Starting of getReportConversationTxt method");
		logger.info("Ending of getReportConversationTxt method");

		this.waitForElementVisibilty(msgReportConversation);

		return msgReportConversation.getText();
	}

	public String getTurnedOffRepliesInfoTxt() {
		logger.info("Starting of getTurnedOffRepliesInfoTxt method");
		logger.info("Ending of getTurnedOffRepliesInfoTxt method");

		this.explicitWait(txtTurnedOffReplies);

		return txtTurnedOffReplies.getText();
	}
	
	public boolean isDisplayedTurnedOffRepliesInfoTxt() {
		
		try {
			
			 txtTurnedOffReplies.isDisplayed();
		}catch(Exception e) {
			return false;
		}
		return true;
		
		
	}

	public String getCannotUploadMoreImagesTxt() {
		logger.info("Starting of getCannotUploadMoreImagesTxt method");
		logger.info("Ending of getCannotUploadMoreImagesTxt method");

		return lblCannotUpload.getText();
	}

	public String getTurnedOffRepliesTxt() {
		logger.info("Starting of getTurnedOffRepliesTxt method");
		logger.info("Ending of getTurnedOffRepliesTxt method");

		this.explicitWait(msgTurnedOffReplies);

		return msgTurnedOffReplies.getText();
	}

	public String getIndividualTurnedOnOffRepliesTxt() {
		logger.info("Starting of getIndividualTurnedOnOffRepliesTxt method");

		this.explicitWait(msgTurnedOffRepliesInBatchGroup);

		logger.info("Ending of getIndividualTurnedOnOffRepliesTxt method");

		return msgTurnedOffRepliesInBatchGroup.getText();
	}

	public String getTurnedOnRepliesTxt() {
		logger.info("Starting of getTurnedOnRepliesTxt method");
		logger.info("Ending of getTurnedOnRepliesTxt method");

		this.explicitWait(msgTurnedOnReplies);

		return msgTurnedOnReplies.getText();
	}

	public String getUnmuteConversationTxt() {
		logger.info("Starting of getUnmuteConversationTxt method");
		logger.info("Ending of getUnmuteConversationTxt method");

		this.explicitWait(msgUnmuteConversation);

		return msgUnmuteConversation.getText();
	}

	public String getMuteConversationTxt() {
		logger.info("Starting of getMuteConversationTxt method");

		this.explicitWait(msgMuteConversation);

		logger.info("Ending of getMuteConversationTxt method");

		return msgMuteConversation.getText();
	}

	public String getNoConversationTxt() {
		logger.info("Starting of getNoConversationTxt method");
		logger.info("Ending of getNoConversationTxt method");

		return msgNoConversation.getText();
	}

	public String getErrorFetchingConversationTxt() {
		logger.info("Starting of getErrorFetchingConversationTxt method");

		this.explicitWait(msgErrorFetchingInConversation);
		
		logger.info("Ending of getErrorFetchingConversationTxt method");

		return msgErrorFetchingInConversation.getText();
	}

	public String getItsEmptyOutHereTxt() {
		logger.info("Starting of getItsEmptyOutHereTxt method");
		logger.info("Ending of getItsEmptyOutHereTxt method");

		return msgitsEmptyOutHere.getText();
	}

	public String getNoConversationFoundTxt() {
		logger.info("Starting of getNoConversationFoundTxt method");
		logger.info("Ending of getNoConversationFoundTxt method");

		return msgNoConversationFouond.getText();
	}

	public String getNoMatchesFoundTxt() {
		logger.info("Starting of getNoMatchesFoundTxt method");
		logger.info("Ending of getNoMatchesFoundTxt method");

		return msgNoMatchesFound.getText();
	}

	public boolean isDisplayedImgMuteIcon(String containsText) {
		logger.info("Starting of isDisplayedImgMuteIcon method");

		logger.debug(imgMuteIconInList.size());
		for (WebElement webElement : imgMuteIconInList) {
			logger.debug(webElement.getText());
			logger.debug(containsText);
			if (webElement.getText().contains(containsText)) {
				System.out.println(imgMuteIcon.isDisplayed());
				return imgMuteIcon.isDisplayed();
			}
		}
		logger.info("Ending of isDisplayedImgMuteIcon method");

		
		return false;
	}

	public boolean isDisplayedImageMute() {
		logger.info("Starting of isDisplayedImageMute method");
		logger.info("Ending of isDisplayedImageMute method");

		logger.debug(imgMuteConversation.getText());
		if (imgMuteConversation.getText().trim().equals("Mute Conversation"))
			return true;
		else
			return false;

	}

	public boolean isDisplayedImgTurnOffRepliesTxt() {
		logger.info("Starting of isDisplayedImgTurnOffRepliesTxt method");
		logger.info("Ending of isDisplayedImgTurnOffRepliesTxt method");

		logger.debug(lblTurnOfReplies.getText().trim());
		if (lblTurnOfReplies.getText().trim().equals("Turn Off replies"))
			return true;
		else
			return false;

		// return imgTurnOfReplies.isDisplayed();
	}

	public String getCopyMessageTxt() {
		logger.info("Starting of getCopyMessageTxt method");

		this.explicitWait(txtCopyMessage);

		logger.info("Ending of getCopyMessageTxt method");

		return txtCopyMessage.getText();
	}

	public String getAddRecipientsTxt() {
		logger.info("Starting of getAddRecipientsTxt method");
		logger.info("Ending of getAddRecipientsTxt method");

		this.explicitWait(lblAddRecipients);

		return lblAddRecipients.getText();
	}

	public String getDeleteMessageTxt() {
		logger.info("Starting of getDeleteMessageTxt method");

		this.explicitWait(txtDeleteMessage);
		logger.info("Ending of getDeleteMessageTxt method");

		return txtDeleteMessage.getText();
	}

	public boolean isDisplayedCreateGroupButton() {
		logger.info("Starting of isDisplayedCreateGroupButton method");
		logger.info("Ending of isDisplayedCreateGroupButton method");

		try {
			return btnCreateGroup.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getGroupCreatedTxt() {
		logger.info("Starting of getGroupCreatedTxt method");

		this.explicitWait(msgGroupCreated);

		logger.info("Ending of getGroupCreatedTxt method");

		return msgGroupCreated.getText();
	}

	public void clickOnBatchChatGroup(String userName) {
		logger.info("Starting of clickOnBatchChatGroup method");

		this.fluentWait(lblBatchName);

		logger.info("Ending of clickOnBatchChatGroup method");
	}

	public void clickOnAnnouncementTab() {
		logger.info("Starting of clickOnAnnouncementTab method");

		hardWait(5);
		this.lblAnnouncemets.click();

		logger.info("Ending of clickOnAnnouncementTab method");
	}

	public void clickOnIndividualMenuVerticalIcon(String userName) {
		logger.info("Starting of clickOnIndividualMenuVerticalIcon method");

		WebElement lastElement = lblListOfParticipantsCount.get(lblListOfParticipantsCount.size() - 1);
		lastElement.click();

		logger.info("Ending of clickOnIndividualMenuVerticalIcon method");
	}

	public void clickOnIndividualTurnOnOffReplies() {
		logger.info("Starting of clickOnIndividualTurnOnOffReplies method");

		WebElement lastElement = lblIndividualTurnOfRepllies.get(lblIndividualTurnOfRepllies.size() - 1);
		System.out.println(lastElement.getText() + "returned text");
		lastElement.click();

		logger.info("Ending of clickOnIndividualTurnOnOffReplies method");
	}

	public String getTurnOffRepliesTxt() {
		logger.info("Starting of getTurnOffRepliesTxt method");

		WebElement lastElement = lblIndividualTurnOfRepllies.get(lblIndividualTurnOfRepllies.size() - 1);
		System.out.println(lastElement.getText() + "returned text");
		logger.info("Ending of getTurnOffRepliesTxt method");
		return lastElement.getText();
	}

	public int getParticipantCountTxt() {
		logger.info("Starting of getParticipantCount method");

		int participantsInfo = this.getCountText(lblParticipantsInfo);

		logger.info("Ending of getParticipantCount method");

		return participantsInfo;
	}

	public int getListOfParticipantsCountTxt() {
		logger.info("Starting of getListOfParticipantsCount method");
		logger.info("Ending of getListOfParticipantsCount method");

		return this.lblListOfParticipantst.size() - 1;
		// return this.lblListOfParticipantst.size();
	}

	public int getListOfParticipantsCountText() {
		logger.info("Starting of getListOfParticipantsCount method");
		logger.info("Ending of getListOfParticipantsCount method");

		return this.lblListOfParticipantst.size();
	}

	public String getVideoAddedInBatchesTxt() {
		logger.info("Starting of getVideoAddedInBatchesTxt method");
	
		hardWait(5);
		String str[] = lblVideoAddedInBatches.getText().split("[.]",0);
		for (String s : str)
			logger.debug(s);
		logger.info("Ending of getVideoAddedInBatchesTxt method");

		return str[1];
	}

	public void clickOnBackInGroupInformation() {
		logger.info("Starting of clickOnBackInGroupInformation method");

		this.clickOnWebElement(btnBackInGroupInformation);

		logger.info("Ending of clickOnBackInGroupInformation method");
	}

	public void clickOnDeleteGroup() {
		logger.info("Starting of clickOnDeleteGroup method");

		try {
			this.lblDeleteGroup.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblDeleteGroup);
		}

		logger.info("Ending of clickOnDeleteGroup method");
	}

	public String getDeleteGroupLabel() {
		logger.info("Starting of getDeleteGroupLabel method");
		logger.info("Ending of getDeleteGroupLabel method");

		return lblDeleteGroup.getText();
	}

	public String getDeleteGroupTxt() {
		logger.info("Starting of getDeleteGroupTxt method");

		this.explicitWait(msgDeleteGroup);

		logger.info("Ending of getDeleteGroupTxt method");

		return msgDeleteGroup.getText();
	}

	public String getHeaderDeleteConfirmationTxt() {
		logger.info("Starting of getHeaderDeleteConfirmationTxt method");
		logger.info("Ending of getHeaderDeleteConfirmationTxt method");

		this.explicitWait(lblHeaderDeleteConfirmation);

		return lblHeaderDeleteConfirmation.getText();
	}

	public void clickOnDeleteButton() {
		logger.info("Starting of clickOnDeleteButton method");

		this.btnDelete.click();

		logger.info("Ending of clickOnDeleteButton method");
	}

	public String getMessageTimeStampTxt() {
		logger.info("Starting of getMessageTimeStampTxt method");

		hardWait(5);
		logger.info("Ending of getMessageTimeStampTxt method");

		return lblMsgUpdatedTime.getText().toUpperCase();
	}

	public String getDayTxt() {
		logger.info("Starting of getDayTxt method");
		logger.info("Ending of getDayTxt method");
		
		try {
			return lblDay.getText();
		} catch (Exception e) {
			logger.debug("There are no long days back chat conversations");;
		}
		return lblDay.getText();
	}

	public boolean getChatHistoryTxt() {
		logger.info("Starting of getChatHistoryTxt method");
		logger.info("Ending of getChatHistoryTxt method");

		try {
			return lblDay.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void getYesterdayTxt() {
		logger.info("Starting of getYesterdayTxt method");

		this.implicitWait(Duration.ofSeconds(5L));
		for (int i = 0; i < (lblActiveUserName.size()); i++) {
			System.out.println(lblActiveUserName.get(i).getText());
			if (lblActiveUserName.get(i).getText().contains("YESTERDAY")) {
				lblActiveUserName.get(i).click();
				break;
			} else {
				logger.debug("There are no yesterday conversation");
			}
		}
		logger.info("Ending of getYesterdayTxt method");
	}

	public void clickOnRecepientLHSDateTxt() {
		logger.info("Starting of clickOnRecepientLHSDateTxt method");

		//this.implicitWait(Duration.ofMinutes(5L));
		for (int i = 0; i < (lblDate.size()); i++) {
			System.out.println(lblDate.get(i).getText());
			if (lblDate.get(i).getText().matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
				lblDate.get(i).click();
				break;

			} else {
				logger.debug("There are no long days back chat");
			}
		}
		logger.info("Ending of clickOnRecepientLHSDateTxt method");
	}

	public String getLHSTimestamp(String userName) {
		logger.info("Starting of getLHSTimestamp method");

		System.out.println(userName);
		System.out.println("size" + lblActiveUserName.size());
		String lhsTimeStamp = null;
		for (int i = 0; i < (lblActiveUserName.size()); i++) {
			System.out.println(lblActiveUserName.get(i).getText());
			if (lblActiveUserName.get(i).getText().contains(userName)) {
				// this.clickOnWebElement(webElement);
				System.out.println("in if------------" + lblActiveUserName.get(i).getText());
				logger.debug("in if------------" + lblActiveUserName.get(i).getText());

				System.out.println(lblLHSTimeStamp.get(i).getText());
				lhsTimeStamp = lblLHSTimeStamp.get(i).getText();
				break;

			}
		}

		logger.info("Ending of getLHSTimestamp method");
		return lhsTimeStamp.toUpperCase();
	}

	public BufferedImage getImgGreenColor() {
		logger.info("Starting of getImgGreenColor method");

		BufferedImage img = null;
		String data = imgGreenColor.getAttribute("src");
		String base64Image = data.split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		try {
			img = ImageIO.read(new ByteArrayInputStream(imageBytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Ending of getImgGreenColor method");
		return img;
	}

	public boolean getListOfUsers() {
		logger.info("Starting of getListOfUsers method");

		boolean flag = true;
		for (WebElement user : lblListOfUsers) {

			if (user.isDisplayed() != true)
				flag = false;
		}

		logger.info("Ending of getListOfUsers method");

		return flag;
	}

	public boolean isDisplayedGreenColor(String activeUserName) {
		logger.info("Starting of isDisplayedGreenColor method");

		System.out.println("size" + lblActiveUserName.size());
		for (int i = 0; i < (lblActiveUserName.size()); i++) {

			System.out.println(lblActiveUserName.get(i).getText());
			if (lblActiveUserName.get(i).getText().contains(activeUserName)) {

				System.out.println("in if------------" + lblActiveUserName.get(i).getText());
				logger.debug("in if------------" + lblActiveUserName.get(i).getText());
				if ((lblActiveUserName.get(i) + "/../..//span//span[@class='Item_activeDot__2lLDe']") != null) {
					System.out.println("Green appear");
					return true;
				}
			}
			System.out.println("No Data found");
		}

		logger.info("Ending of isDisplayedGreenColor method");

		return false;
	}

	public boolean IsDisplayedNoGreenColor() {
		logger.info("Starting of IsDisplayedNoGreenColor method");

		for (int i = 0; i < (lblActiveUserName.size()); i++) {

			/*
			 * if ((lblActiveUserName.get(i) +greenColor)!= null) {
			 * 
			 * return true; }
			 */
			try {
				greenColor.isDisplayed();
			} catch (NoSuchElementException e) {
				// e.printStackTrace();
				return false;
			}
		}

		logger.info("Ending of IsDisplayedNoGreenColor method");

		return false;
	}

	public void clickOnDownloadButton() {
		logger.info("Starting of clickOnDownloadButton method");

		this.btnDownload.click();

		logger.info("Ending of clickOnDownloadButton method");
	}

	public boolean isDisplayedDownloadButton() {
		logger.info("Starting of isDisplayedDownloadButton method");
		logger.info("Ending of isDisplayedDownloadButton method");

		try {
			return btnDownload.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public String getWeNeedParticipantsTxt() {
		logger.info("Starting of getWeNeedParticipantsTxt method");

		this.explicitWait(lblWeNeedParticipantsTxt);

		logger.info("Ending of getWeNeedParticipantsTxt method");

		return lblWeNeedParticipantsTxt.getText();
	}

	public void closeTab() {
		logger.info("Starting of closeTab method");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		if (tabs.size() > 1) {
			driver.switchTo().window(tabs.get(1));
			driver.close();
			driver.switchTo().window(tabs.get(0));
		}

		logger.info("Ending of closeTab method");
	}

	public int geAttachmentNumberTxt() {
		logger.info("Starting of geAttachmentNumberTxt method");

		int attachmentCount = Integer.parseInt(lblAttachmentNumber.getText());

		logger.info("Ending of geAttachmentNumberTxt method");
		return attachmentCount;
	}

	public String getLongChatDayTxt() {
		logger.info("Starting of getLongChatDayTxt method");

		String longChatDay = lblDateAndDay.get(0).getText();

		logger.info("Ending of getLongChatDayTxt method");
		return longChatDay;
	}

	public void clickOnProfile() {
		logger.info("Starting of clickOnProfile method");

		this.clickOnWebElement(lblUerProfile);
		this.lblProfile.click();

		logger.info("Ending of clickOnProfile method");
	}

	public void clickOnEditButton() {
		logger.info("Starting of clickOnEditButton method");

		this.clickOnWebElement(btnEdit);

		logger.info("Ending of clickOnEditButton method");
	}

	public void setProfilePhoto(String filePath) {
		logger.info("Starting of setProfilePhoto method");

		this.lblUploadPhoto.click();
		this.etInputFile.sendKeys(filePath);
		this.closeOSWindow();

		logger.info("Ending of setProfilePhoto method");

	}

	public List getDescendingOrder() {
		logger.info("Starting of getDescendingOrder method");

		List<String> listOfLHSDayStatus = new ArrayList<String>();
		List<String> strListTimeam = new ArrayList<String>();
		List<String> strListTimepm = new ArrayList<String>();
		List<String> strListYesterday = new ArrayList<String>();
		List<String> strListDate = new ArrayList<String>();
		// Condition for Different set of status of day chats
		for (int i = 1; i < lblDateOrTimeTxt.size(); i++) {
			if (lblDateOrTimeTxt.get(i).getText().endsWith("am"))
				strListTimeam.add(lblDateOrTimeTxt.get(i).getText());
			else if (lblDateOrTimeTxt.get(i).getText().endsWith("pm"))
				strListTimepm.add(lblDateOrTimeTxt.get(i).getText());
			else if (lblDateOrTimeTxt.get(i).getText().endsWith("DAY"))
				strListYesterday.add(lblDateOrTimeTxt.get(i).getText());
			else {
				strListDate.add(lblDateOrTimeTxt.get(i).getText().replace("/", "-"));
			}
		}
		// date formate which is in string
		LocalDate[] date = new LocalDate[strListDate.size()];
		Date[] dat = new Date[strListDate.size()];
		for (int i = 0; i < strListDate.size(); i++) {
			try {
				date[i] = LocalDate.parse(strListDate.get(i));
				dat[i] = Date.from(date[i].atStartOfDay(ZoneId.systemDefault()).toInstant());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Descending order using sorting
		Collections.sort(strListTimeam, Collections.reverseOrder());
		Collections.sort(strListTimepm, Collections.reverseOrder());
		Collections.sort(strListYesterday);
		Arrays.sort(date, Collections.reverseOrder());
		// Adding to the list foor asserting the value
		for (String str : strListTimepm) {
			listOfLHSDayStatus.add(str);
		}
		for (String str : strListTimeam) {
			listOfLHSDayStatus.add(str);
		}
	
		for (String str : strListYesterday) {
			listOfLHSDayStatus.add(str);
		}
		for (Date str : dat) {
			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("YYYY/MM/dd");
			dateTimeFormatter.format(str);
			listOfLHSDayStatus.add(dateTimeFormatter.format(str));
		}
		logger.info("Ending of getDescendingOrder method");

		return listOfLHSDayStatus;

	}

	public List getDescendingOrderDayStatusOfLHSTxt() {
		logger.info("Starting of getDescendingOrderDayStatusOfLHS method");

		List<String> listOfLHSDayStatus = new ArrayList<String>();
		for (int i = 1; i < lblDateOrTimeTxt.size(); i++) {
			listOfLHSDayStatus.add(lblDateOrTimeTxt.get(i).getText());
		}

		logger.info("Ending of getDescendingOrderDayStatusOfLHS method");

		return listOfLHSDayStatus;
	}
}
