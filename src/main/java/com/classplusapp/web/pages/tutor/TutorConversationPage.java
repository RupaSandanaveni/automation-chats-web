package com.classplusapp.web.pages.tutor;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.classplusapp.web.pages.BaseClassplusAutomationPage;

public class TutorConversationPage extends BaseClassplusAutomationPage {

	@FindBy(xpath = "//div[@class='ui transparent left icon input']")
	private WebElement icnSearch;

	@FindBy(xpath = "(//p[text()='Rupa Batch'])[1]")
	private WebElement lblSearchBatchName;

	@FindBy(xpath = "//span[text()='Students']")
	private WebElement lblStudentslabel;

	@FindBy(xpath = "//div[text()='Rupa']")
	private WebElement lblStudentSenderName;

	@FindBy(xpath = "//li[text()='Batch' and contains(., 'Information')]")
	private WebElement imgBatchInformation;

	@FindBy(xpath = "//input[@value='Rupa Batch']")
	private WebElement lblBatchGroupName;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='Rupa']")
	private WebElement txtStudentSenderName;

	@FindBy(xpath = "//button[text()='Add Students']")
	private WebElement lblAddStudentsLabel;

	@FindBy(xpath = "(//span[@class='SingleStudent_StudentLabel__33sIf'])[1]")
	private WebElement lblSearchAddStudentsLabel;

	@FindBy(xpath = "//p[text()='Chat']")
	private WebElement lblChatLabel;

	@FindBy(xpath = "//span[text()='Chats']")
	private WebElement lblChatsLabel;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='Vijaya']")
	private WebElement txtFacultySenderName;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='parent']")
	private WebElement txtParentSenderName;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='parent']")
	private WebElement txtParentTwoSenderName;

	@FindBy(xpath = "//*[text()='parent']")
	private WebElement lblParentSenderName;

	@FindBy(xpath = "//*[text()='parent']")
	private WebElement lblParent;

	@FindBy(xpath = "//div[text()='RajeshwariParent']")
	private WebElement lblParentOneSenderName;

	@FindBys({ @FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']") })
	private List<WebElement> lblListOfSenderNames;

	@FindBy(xpath = "//div[@class='List_listContainer__1Rz3n']")
	private List<WebElement> lblListStudentGroupContainer;

	@FindBy(xpath = "//div[@class='CreateGroup_participants__23gXR']")
	private WebElement lblParticipantsInfo;

	@FindBy(xpath = "//span[@class='Header_backButton__2L_EF']")
	private WebElement btnHeaderBack;

	@FindBy(xpath = "//span[contains(text(),'Vijaya')]/parent::div/parent::div/parent::div/descendant::div[@class=\"Dropdown_Btn__3FGw8 \"]/descendant::img")
	private WebElement mnuVerticalIconInGroupInformation;

	@FindBy(xpath = "//div[@class='Header_alertMsg__1XXpq']")
	private WebElement studentGroupsHeaderInfo;

	@FindBy(xpath = "//span[text()='StudentCreatedGroup']")
	private WebElement lblStudentGroupName;

	@FindBy(xpath = "//span[text()='leaveGrp']")
	private WebElement lblLeaveStudentGroupName;

	@FindBy(xpath = "//span[text()='StudentCreatedGroup']")
	private WebElement lblGroupHeaderName;

	@FindBy(xpath = "//span[@class='Header_studentGroups__1KRsz']")
	private WebElement lblStudentGroups;

	@FindBy(xpath = "//div[@class='List_studentviewingWarning__ulCGX']")
	private WebElement lblStudentGroupsInfo;

	@FindBy(xpath = "//input[@placeholder='Search for Batches']")
	private WebElement txtSearchBar;

	@FindBy(xpath = "//input[@placeholder='Search students']")
	private WebElement txtSearchBarInStudentTab;

	@FindBy(xpath = "//p[text()='Batches']")
	private WebElement lblBatchesTab;

	@FindBy(xpath = "//p[@class='batch-heading']")
	private WebElement lblBatch;

	@FindBy(xpath = "//span[text()='Students']")
	private WebElement lblStudentsTab;

	// student need to select
	@FindBy(xpath = "//*[text()='Rupa']")
	private WebElement lblStudent;

	@FindBy(xpath = "//div[@class='profile-option']")
	private WebElement imgProfileChatOption;

	@FindBy(xpath = "//h1[text()='Students']")
	private WebElement lblStudentHeader;

	@FindBy(xpath = "//span[text()='Videos']")
	private WebElement lblVideosTab;

	@FindBy(xpath = "//div[text()='Videos']")
	private WebElement lblVideos;

	@FindBy(xpath = "//div[@class='ui item dropdown study-material-add-button']")
	private WebElement btnAdd;

	@FindBy(xpath = "//span[text()='New Video']")
	private WebElement lblNewVideo;

	@FindBy(xpath = "//div[text()='Add Video']")
	private WebElement lblHeaderAddVideo;

	@FindBy(xpath = "//label[text()='Enter or paste link'] ")
	private WebElement lblHearderPasteLink;

	@FindBy(xpath = "//input[@name='category']")
	private WebElement lblpasteLink;

	@FindBy(xpath = "//button[@class='ui large fluid primary button add-video-button']")
	private WebElement btnCheckVideo;

	@FindBy(xpath = "//div[text()='Youtube link']")
	private WebElement headerYoutubeLink;

	@FindBy(xpath = "//span[@class='folder-name']")
	private WebElement lblFolderName;

	@FindBy(xpath = "//i[@class='ellipsis vertical float-right icon']")
	private WebElement mnuThreeDottedVerticalIcon;

	@FindBy(xpath = "//div[text()='Delete Video']")
	private WebElement lblDeleteVideo;

	@FindBy(xpath = "//p[text()='Are you sure about deleting this video?']")
	private WebElement lblDeleteInfo;

	@FindBy(xpath = "//button[@class='ui primary button']")
	private WebElement btnDeleteVideo;

	@FindBy(xpath = "//div[text()='Success']")
	private WebElement msgSuccess;

	@FindBy(xpath = "//input[@placeholder='Search videos']")
	private WebElement lblSearchVideos;

	@FindBy(xpath = "//p[@class='empty-subheading']")
	private WebElement lblEmptyHeading;

	@FindBy(xpath = "//li[text()='Leave Group']")
	private WebElement lblLeaveGroup;

	@FindBy(xpath = "//span[@class='Header_activeDot__1BFC5']")
	private WebElement lblStudentGroupsActiveColor;

	@FindBys({
			@FindBy(xpath = "//div[contains(text(), 'Participants')]/following-sibling::div/div[@class='Item_item__1PDSu  item undefined ']") })
	private List<WebElement> participantCount;

	@FindBy(xpath = "//div[@class='UnseenConversation_unseenHeader__3RZgG']")
	private WebElement lblUnseenHeader;

	@FindBys({
			@FindBy(xpath = "//div[@class='UnseenConversation_unseenHeader__3RZgG']//following-sibling::div//child::div/child::div") })
	private List<WebElement> listOfWaitingStudents;

	@FindBy(xpath = "(//img[@class='Item_img__2HaKx'])[last()]")
	private WebElement imgProfile;

	public TutorConversationPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of TutorConversationPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of TutorConversationPage method");
	}

	private static final Logger logger = Logger.getLogger(TutorConversationPage.class.getName());

	public BufferedImage getProfileImage() {
		logger.info("Starting of getProfileImage method");

		BufferedImage img = null;
		URL url;
		try {
			url = new URL(imgProfile.getAttribute("src"));
			System.out.println("src file " + url);
			img = ImageIO.read(url);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		logger.info("Ending of getProfileImage method");
		return img;
	}

	public boolean getIconSearch() {
		logger.info("Starting of getIconSearch method");
		logger.info("Ending of getIconSearch method");

		return icnSearch.isDisplayed();
	}

	public String getSearchBatchNameLabel() {
		logger.info("Starting of getSearchBatchNameLabel method");
		logger.info("Ending of getSearchBatchNameLabel method");

		return lblSearchBatchName.getText();
	}

	public String getStudentslabel() {
		logger.info("Starting of getStudentslabel method");
		logger.info("Ending of getStudentslabel method");

		return lblStudentslabel.getText();
	}

	public String getAddStudentsLabel() {
		logger.info("Starting of getAddStudentsLabel method");
		logger.info("Ending of getAddStudentsLabel method");

		return lblAddStudentsLabel.getText();
	}

	public String getSearchAddStudentsLabel() {
		logger.info("Starting of getSearchAddStudentsLabel method");
		logger.info("Ending of getSearchAddStudentsLabel method");

		return lblSearchAddStudentsLabel.getText();
	}

	public String getChatLabel() {
		logger.info("Starting of getChatLabel method");
		logger.info("Ending of getChatLabel method");
		
		this.explicitWait(lblChatLabel);

		return lblChatLabel.getText();
	}

	public String getChatsLabel() {
		logger.info("Starting of getChatsLabel method");
		logger.info("Ending of getChatsLabel method");

		return lblChatsLabel.getText();
	}

	public void clickOnStudentRecipient(String userName) {
		logger.info("Starting of clickOnStudentRecipient method");

		// this.pickFromWebElemetList(lblListOfSenderNames, userName);
		this.fluentWait(lblStudent);

		logger.info("Ending of clickOnStudentRecipient method");
	}

	public void clickOnParentRecipient(String userName) {
		logger.info("Starting of clickOnParentRecipient method");

		// this.pickFromWebElemetList(lblListOfSenderNames, userName);
		/*
		 * try { this.lblParent.click(); } catch (Exception e) {
		 * this.clickOnWebElement(lblParent); }
		 */
		hardWait(2);
		this.fluentWait(lblParent);

		logger.info("Ending of clickOnParentRecipient method");
	}

	public void clickOnParentOneRecipient(String userName) {
		logger.info("Starting of clickOnParentOneRecipient method");

		/*
		 * this.pickFromWebElemetList(lblListOfSenderNames, userName); try {
		 * this.lblParentOneSenderName.click(); } catch (Exception e) {
		 * this.clickOnWebElement(lblParentOneSenderName); }
		 */

		fluentWait(lblParentOneSenderName);

		logger.info("Ending of clickOnParentOneRecipient method");
	}

	public void clickOnHeaderBackButton() {
		logger.info("Starting of clickOnHeaderBackButton method");

		this.btnHeaderBack.click();

		logger.info("Ending of clickOnHeaderBackButton method");
	}

	public int getParticipantsInfoTxt() {
		logger.info("Starting of getParticipantsInfoTxt method");

		int participantsCount = this.getCountText(lblParticipantsInfo);

		logger.info("Ending of getParticipantsInfoTxt method");

		return participantsCount;
	}

	public int getListParticipantCountTxt() {
		logger.info("Starting of getParticipantCountTxt method");
		logger.info("Ending of getParticipantCountTxt method");

		return participantCount.size();
	}

	public boolean getStudentGroupsTxt() {
		logger.info("Starting of getStudentGroupsTxt method");
		logger.info("Ending of getStudentGroupsTxt method");

		return lblStudentGroups.isDisplayed();
	}

	public String getStudentGroupsInfoTxt() {
		logger.info("Starting of getStudentGroupsInfoTxt method");
		logger.info("Ending of getStudentGroupsInfoTxt method");

		return lblStudentGroupsInfo.getText();
	}

	public void clickOnStudentGroup(String groupName) {
		logger.info("Starting of clickOnStudentGroup method");

		this.explicitWait(lblListStudentGroupContainer);
		this.pickFromWebElemetList(lblListStudentGroupContainer, groupName);
		this.explicitWait(lblStudentGroupName);
		this.clickOnWebElement(lblStudentGroupName);

		logger.info("Ending of clickOnStudentGroup method");
	}

	public void clickOnLeaveStudentGroup(String groupName) {
		logger.info("Starting of clickOnLeaveStudentGroup method");

		this.explicitWait(lblListStudentGroupContainer);
		this.pickFromWebElemetList(lblListStudentGroupContainer, groupName);
		this.explicitWait(lblLeaveStudentGroupName);
		this.lblLeaveStudentGroupName.click();

		logger.info("Ending of clickOnLeaveStudentGroup method");
	}

	public void setSearchBarInStudentsTab(String strStudentName) {
		logger.info("Starting setSearchBarInStudentsTab method");

		this.explicitWait(txtSearchBarInStudentTab);
		this.txtSearchBarInStudentTab.click();
		this.txtSearchBarInStudentTab.sendKeys(strStudentName);

		logger.info("Ending setSearchBarInStudentsTab method");
	}

	public void clickOnStudentsTab() {
		logger.info("Starting of clickOnStudentsTab method");

		this.lblStudentsTab.click();

		logger.info("Ending of clickOnStudentsTab method");
	}

	public void clickOnStudent() {
		logger.info("Starting of clickOnStudent method");

		this.lblStudent.click();

		logger.info("Ending of clickOnStudent method");
	}

	public void clickOnProfileChatOption() {
		logger.info("Starting of clickOnProfileChatOption method");

		this.imgProfileChatOption.click();

		logger.info("Ending of clickOnProfileChatOption method");
	}

	public String getStudentGroupNameTxt() {
		logger.info("Starting of getStudentGroupNameTxt method");
		logger.info("Ending of getStudentGroupNameTxt method");

		return lblStudentGroupName.getText();
	}

	public String getStudentGroupsHeaderInfoTxt() {
		logger.info("Starting of getStudentGroupsHeaderInfoTxt method");

		this.explicitWait(studentGroupsHeaderInfo);

		logger.info("Ending of getStudentGroupsHeaderInfoTxt method");

		return studentGroupsHeaderInfo.getText();
	}

	public String getStudentGroupHeaderNameTxt() {
		logger.info("Starting of getStudentGroupHeaderNameTxt method");

		this.explicitWait(lblStudentGroupName);

		logger.info("Ending of getStudentGroupHeaderNameTxt method");

		return lblStudentGroupName.getText();
	}

	public boolean getStudentSenderNameTxt() {
		logger.info("Starting of getStudentSenderNameTxt method");
		logger.info("Ending of getStudentSenderNameTxt method");

		return txtStudentSenderName.isDisplayed();
	}

	public boolean getFacultySenderNameTxt() {
		logger.info("Starting of getFacultySenderNameTxt method");
		logger.info("Ending of getFacultySenderNameTxt method");

		return txtFacultySenderName.isDisplayed();
	}

	public boolean getParentTwoSenderNameTxt() {
		logger.info("Starting of getParentTwoSenderNameTxt method");
		logger.info("Ending of getParentTwoSenderNameTxt method");

		return txtParentTwoSenderName.isDisplayed();
	}

	public boolean getParentSenderNameTxt() {
		logger.info("Starting of getParentSenderNameTxt method");
		logger.info("Ending of getParentSenderNameTxt method");

		return txtParentSenderName.isDisplayed();
	}

	public void clickOnBatchInformation() {
		logger.info("Starting of clickOnBatchInformation method");

		this.imgBatchInformation.click();

		logger.info("Ending of clickOnBatchInformation method");
	}

	public String getGroupNameInBatchInformation() {
		logger.info("Starting of getGroupNameInBatchInformation method");

		this.explicitWait(lblBatchGroupName);
		System.out.println(lblBatchGroupName.getText());

		logger.info("Ending of getGroupNameInBatchInformation method");

		return lblBatchGroupName.getAttribute("value");
	}

	public String getStudentsLabelInStudentsTab() {
		logger.info("Starting of getStudentsLabelInStudentsTab method");
		logger.info("Ending of getStudentsLabelInStudentsTab method");

		return lblStudentHeader.getText();
	}

	public String getVideosTabTxt() {
		logger.info("Starting of getVideosTabTxt method");
		logger.info("Ending of getVideosTabTxt method");

		return lblVideosTab.getText();
	}

	public void clickOnVideosTab() {
		logger.info("Starting of clickOnVideosTab method");

		this.lblVideosTab.click();

		logger.info("Ending of clickOnVideosTab method");
	}

	public String getVideosTxt() {
		logger.info("Starting of getVideosTxt method");
		logger.info("Ending of getVideosTxt method");

		return lblVideos.getText();
	}

	public String getAddButtonTxt() {
		logger.info("Starting of getAddButtonTxt method");
		logger.info("Ending of getAddButtonTxt method");

		return btnAdd.getText();
	}

	public void clickOnAddButton() {
		logger.info("Starting of clickOnAddButton method");

		this.btnAdd.click();

		logger.info("Ending of clickOnAddButton method");
	}

	public String getNewVideoTxt() {
		logger.info("Starting of getNewVideoTxt method");
		logger.info("Ending of getNewVideoTxt method");

		return lblNewVideo.getText();
	}

	public void clickOnNewVideo() {
		logger.info("Starting of clickOnNewVideo method");

		this.lblNewVideo.click();

		logger.info("Ending of clickOnNewVideo method");

	}

	public String getHeaderAddVideoTxt() {
		logger.info("Starting of getHeaderAddVideoTxt method");
		logger.info("Ending of getHeaderAddVideoTxt method");

		return lblHeaderAddVideo.getText();
	}

	public String getPasteLinkTxt() {
		logger.info("Starting of getPasteLinkTxt method");
		logger.info("Ending of getPasteLinkTxt method");

		return lblHearderPasteLink.getText();
	}

	public void clickOnPasteLink() {
		logger.info("Starting of clickOnPasteLink method");

		this.lblpasteLink.sendKeys("https://youtu.be/CFD9EFcNZTQ");

		logger.info("Ending of clickOnPasteLink method");
	}

	public String getCheckVideoButtonTxt() {
		logger.info("Starting of getCheckVideoButtonTxt method");
		logger.info("Ending of getCheckVideoButtonTxt method");

		return btnCheckVideo.getText();
	}

	public void clickOnCheckVideo() {
		logger.info("Starting of clickOnCheckVideo method");

		this.btnCheckVideo.click();

		logger.info("Ending of clickOnCheckVideo method");

	}

	public String getHeaderYoutubeLinkTxt() {
		logger.info("Starting of getHeaderYoutubeLinkTxt method");
		logger.info("Ending of getHeaderYoutubeLinkTxt method");

		return headerYoutubeLink.getText();
	}

	public String getFolderNameTxt() {
		logger.info("Starting of getFolderNameTxt method");
		logger.info("Ending of getFolderNameTxt method");

		return lblFolderName.getText();
	}

	public void clickOnThreeDottedVerticalIcon() {
		logger.info("Starting of clickOnThreeDottedVerticalIcon method");

		this.mnuThreeDottedVerticalIcon.click();

		logger.info("Ending of clickOnThreeDottedVerticalIcon method");
	}

	public String getDeleteVideoTxt() {
		logger.info("Starting of getDeleteVideoTxt method");
		logger.info("Ending of getDeleteVideoTxt method");

		return lblDeleteVideo.getText();
	}

	public void clickOnDeleteVideo() {
		logger.info("Starting of clickOnDeleteVideo method");

		this.lblDeleteVideo.click();

		logger.info("Ending of clickOnDeleteVideo method");
	}

	public String getDeleteInfoTxt() {
		logger.info("Starting of getDeleteInfoTxt method");
		logger.info("Ending of getDeleteInfoTxt method");

		return lblDeleteInfo.getText();
	}

	public String getDeleteVideoButtonTxt() {
		logger.info("Starting of getDeleteVideoButtonTxt method");
		logger.info("Ending of getDeleteVideoButtonTxt method");

		return btnDeleteVideo.getText();
	}

	public void clickOnDeleteVideoButton() {
		logger.info("Starting of clickOnDeleteVideoButton method");

		this.btnDeleteVideo.click();

		logger.info("Ending of clickOnDeleteVideoButton method");
	}

	public String getSuccessTxt() {
		logger.info("Starting of getSuccessTxt method");

		this.explicitWait(msgSuccess);

		logger.info("Ending of getSuccessTxt method");

		return msgSuccess.getText();
	}

	public void clickOnSearchVideos() {
		logger.info("Starting of clickOnSearchVideos method");

		this.lblSearchVideos.sendKeys("Java");

		logger.info("Ending of clickOnSearchVideos method");

	}

	public String getEmptyHeadingTxt() {
		logger.info("Starting of getEmptyHeadingTxt method");
		logger.info("Ending of getEmptyHeadingTxt method");

		return lblEmptyHeading.getText();
	}

	public String getLeaveGroupLabel() {
		logger.info("Starting of getLeaveGroupLabel method");
		logger.info("Ending of getLeaveGroupLabel method");

		return lblLeaveGroup.getText();
	}

	public String isActiveColorDisplayed() {
		logger.info("Starting of isActiveColorDisplayed method");

		String colorString = this.lblStudentGroupsActiveColor.getCssValue("background-color");

		String hex = Color.fromString(colorString).asHex();

		System.out.println("**********************************************" + hex);

		logger.info("Ending of isActiveColorDisplayed method");
		return hex;
	}

	public String getUnseenHeaderTxt() {
		logger.info("Starting of getUnseenHeaderTxt method");
		logger.info("Ending of getUnseenHeaderTxt method");

		return lblUnseenHeader.getText();
	}

	public void clickOnWaitingStudent() {
		logger.info("Starting of clickOnWaitingStudent method");

		WebElement element = listOfWaitingStudents.get(0);

		try {
			element.click();
		} catch (Exception e) {
			this.clickOnWebElement(element);
		}

		logger.info("Ending of clickOnWaitingStudent method");
	}
}