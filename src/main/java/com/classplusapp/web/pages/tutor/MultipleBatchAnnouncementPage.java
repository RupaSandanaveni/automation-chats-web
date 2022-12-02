package com.classplusapp.web.pages.tutor;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.classplusapp.web.pages.BaseClassplusAutomationPage;

public class MultipleBatchAnnouncementPage extends BaseClassplusAutomationPage {

	@FindBy(xpath = "//li[text()=' Multiple batch announcement']")
	private WebElement txtMultipleBatchAnnouncement;

	@FindBy(xpath = "//li[text()='Multiple batch announcement']")
	private WebElement lblMultipleBatchAnnouncement;

	@FindBy(xpath = "//span[text()='Select recipient(s)']")
	private WebElement lblSelectRecipients;

	@FindBy(xpath = "//input[@placeholder='Search by name or number']")
	private WebElement txtSearchBar;

	@FindBy(xpath = "//span[contains(@class, 'List_lableAndCheckbox')]//input")
	private WebElement lblSelectBatch;

	@FindBy(xpath = "//input[@class='input_sendSMSInput__ViNsw']")
	private WebElement chkSendSMS;

	@FindBy(xpath = "//p[text()='Send SMS']")
	private WebElement lblSendSMS;

	@FindBy(xpath = "//div[text()='Multiple Batch Announcement']")
	private WebElement msgMultipleBatchAnnouncement;

	@FindBy(xpath = "//div[text()='Multibatch announcement sent']")
	private WebElement lblMultiBatchAnnouncementSent;

	@FindBy(xpath = "(//span[text()='Testdata for sending message'])[1]")
	private WebElement lblReceivedAnnouncement;

	static final Logger logger = Logger.getLogger(MultipleBatchAnnouncementPage.class.getName());

	public MultipleBatchAnnouncementPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of MultiBatchAnnouncementPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of MultiBatchAnnouncementPage method");
	}

	public void clickOnMultipleBatchAnnocument() {
		logger.info("Starting of clickOnMultipleBatchAnnocument method");

		try {
			this.lblMultipleBatchAnnouncement.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblMultipleBatchAnnouncement);
		}

		logger.info("Ending of clickOnMultipleBatchAnnocument method");
	}

	public void clickOnSearchRecipients(String batchName, String batchName2) {
		logger.info("Starting of clickOnSearchRecipients method");

		this.txtSearchBar.click();
		this.txtSearchBar.sendKeys(batchName);
		this.lblSelectBatch.click();
		this.txtSearchBar.clear();
		this.txtSearchBar.sendKeys(batchName2);
		this.lblSelectBatch.click();

		logger.info("Ending of clickOnSearchRecipients method");
	}

	public void clickOnSendSMS() {
		logger.info("Starting of clickOnSendSMS method");

		this.clickOnWebElement(chkSendSMS);

		logger.info("Ending of clickOnSendSMS method");
	}

	public String getMultipleBatchAnnouncementTxt() {
		logger.info("Starting of getMultipleBatchAnnouncementTxt method");
		logger.info("Ending of getMultipleBatchAnnouncementTxt method");

		return txtMultipleBatchAnnouncement.getText();
	}

	public String getSendSMSTxt() {
		logger.info("Starting of getSendSMSTxt method");

		this.explicitWait(lblSendSMS);
		logger.info("Ending of getSendSMSTxt method");

		return lblSendSMS.getText();
	}

	public String getTitleMultipleBatchAnnouncementTxt() {
		logger.info("Starting of getTitleMultipleBatchAnnouncementTxt method");
		logger.info("Ending of getTitleMultipleBatchAnnouncementTxt method");

		return msgMultipleBatchAnnouncement.getText();
	}

	public String getMultiBatchAnnouncementSentTxt() {
		logger.info("Starting of getMultiBatchAnnouncementSentTxt method");

		this.explicitWait(lblMultiBatchAnnouncementSent);

		logger.info("Ending of getMultiBatchAnnouncementSentTxt method");

		return lblMultiBatchAnnouncementSent.getText();
	}

	public String getReceivedAnnouncementTxt() {
		logger.info("Starting of getReceivedAnnouncementTxt method");

		this.explicitWait(lblReceivedAnnouncement);

		logger.info("Ending of getReceivedAnnouncementTxt method");

		return lblReceivedAnnouncement.getText();
	}
}
