package com.classplusapp.web.pages.tutor;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.classplusapp.web.pages.BaseClassplusAutomationPage;

public class StartBroadCastPage extends BaseClassplusAutomationPage {

	@FindBy(xpath = "//li[text()='Start a broadcast']")
	private WebElement lblStartBroadCast;

	@FindBy(xpath = "//span[text()='Filters']")
	private WebElement lblFilters;

	@FindBy(xpath = "//div[contains(@class, 'FilterRecipient_ModalBody')]/div/*/input[@placeholder='Search by name or number']")
	private WebElement etSearchBarInFilters;

	@FindBy(xpath = "//span[contains(text(), 'Filter recipients by')]/ancestor::div/div/*/*/following-sibling::div/*/*/following-sibling::div/*/*/div/input")
	private WebElement lblAddParticipant1;

	@FindBy(xpath = "//div[text()='Broadcast Message']")
	private WebElement lblBroadCastMessage;

	@FindBy(xpath = "//div[text()='View more']")
	private WebElement lblViewMore;

	@FindBy(xpath = "//lable[text()='Faculty']/parent::div/child::input")
	private WebElement chkFacultyRecipient;

	@FindBy(xpath = "//lable[text()='Parents']/parent::div/child::input")
	private WebElement chkParentRecipient;

	@FindBy(xpath = "//button[text()='Apply Filters']")
	private WebElement btnApplyFilters;

	@FindBy(xpath = "//span[text()='Select']")
	private WebElement lblSelect;

	@FindBy(xpath = "//img[@class='input_left__2FOuN input_attachmentImg__3M88i']")
	private WebElement imgAttachment;

	@FindBy(xpath = "//input[@type='file']")
	private WebElement inputFile;

	@FindBy(xpath = "//li[text()=' Documents']")
	private WebElement lblDocument;

	@FindBy(xpath = "//li[text()=' Documents']")
	private WebElement lblImage;

	@FindBy(xpath = "//textarea[@type='text']")
	private WebElement txtWriteSomethingHere;

	@FindBy(xpath = "//img[@class='input_right__2Z46_']")
	private WebElement btnSend;

	@FindBy(xpath = "//div[text()='Message broadcasted succesfully']")
	private WebElement lblBroadCastMessageSent;

	@FindBy(xpath = "//div[text()='Raghuna']")
	private WebElement lblSelectRecepient;

	private static final Logger logger = Logger.getLogger(StartBroadCastPage.class.getName());
	Robot robot = null;

	public StartBroadCastPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of StartBroadCastPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of StartBroadCastPage method");
	}

	public void clickOnStartBroadCast() {
		logger.info("Starting of clickOnStartBroadCast method");

		this.explicitWait(lblStartBroadCast);
		this.clickOnWebElement(lblStartBroadCast);

		logger.info("Ending of clickOnStartBroadCast method");
	}

	public void clickOnFacultyRecipient() {
		logger.info("Starting of clickOnFacultyRecipient method");

		this.explicitWait(chkFacultyRecipient);
		this.clickOnWebElement(chkFacultyRecipient);

		logger.info("Ending of clickOnFacultyRecipient method");
	}

	public void clickOnViewMore() {
		logger.info("Starting of clickOnViewMore method");

		this.explicitWait(lblViewMore);
		this.clickOnWebElement(lblViewMore);

		logger.info("Ending of clickOnViewMore method");
	}

	public void clickOnSearchRecipientsInFilters(String batchName) throws InterruptedException {
		logger.info("Starting of clickOnSearchRecipientsInFilters method");

		this.explicitWait(etSearchBarInFilters);
		this.etSearchBarInFilters.click();
		this.etSearchBarInFilters.clear();
		try {

			robot = new Robot();
			StringSelection stringSelection = new StringSelection(batchName);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			this.etSearchBarInFilters.sendKeys(Keys.ENTER);
			this.etSearchBarInFilters.sendKeys(Keys.CONTROL + "v");
			Thread.sleep(500);
			this.etSearchBarInFilters.sendKeys(Keys.ENTER);
			this.explicitWait(lblAddParticipant1);
			this.clickOnWebElement(lblAddParticipant1);
		} catch (Exception e) {

		}

		logger.info("Ending of clickOnSearchRecipientsInFilters method");
	}

	public void clickOnRecipients() {
		logger.info("Starting of clickOnRecipients method");

		this.fluentWait(lblSelectRecepient);

		logger.info("Ending of clickOnRecipients method");
	}

	public void clickOnApplyFiltersButton() {
		logger.info("Starting of clickOnApplyFiltersButton method");

		this.explicitWait(btnApplyFilters);
		this.clickOnWebElement(btnApplyFilters);

		logger.info("Ending of clickOnApplyFiltersButton method");
	}

	public void clickOnSelectLabel() {
		logger.info("Starting of clickOnSelectLabel method");

		this.explicitWait(lblSelect);
		this.clickOnWebElement(lblSelect);

		logger.info("Ending of clickOnSelectLabel method");
	}

	public String getBroadCastMessageTxt() {
		logger.info("Starting of getBroadCastMessageTxt method");
		logger.info("Ending of getBroadCastMessageTxt method");

		return lblBroadCastMessage.getText();
	}

	public String getSendBroadCastMessageSentTxt() {
		logger.info("Starting of getSendBroadCastMessageSentTxt method");
		logger.info("Ending of getSendBroadCastMessageSentTxt method");

		System.out.println(lblBroadCastMessageSent.getText());

		return lblBroadCastMessageSent.getText();
	}

	public String getFiltersTxt() {
		logger.info("Starting of getFiltersTxt method");
		logger.info("Ending of getFiltersTxt method");

		return lblFilters.getText();
	}
}
