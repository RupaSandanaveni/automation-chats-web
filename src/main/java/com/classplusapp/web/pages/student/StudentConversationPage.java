package com.classplusapp.web.pages.student;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.classplusapp.web.pages.BaseClassplusAutomationPage;

public class StudentConversationPage extends BaseClassplusAutomationPage {

	@FindBy(xpath = "//span[text()='Assignments']")
	private WebElement lblAssignmentslabel;

	@FindBy(xpath = "//span[@class='headerValue']")
	private WebElement lblAssignmentsHeadinglabel;

	@FindBy(xpath = "//span[text()='Gunjan Preprod']")
	private WebElement lblSenderName;

	@FindBy(xpath = "//div[@class='Header_nameContainer__3MWIq']")
	private WebElement lblHeaderName;

	@FindBy(xpath = "//div[@class='List_unseenHeader__RnECG']//following-sibling::div")
	private List<WebElement> lblListOfSenderNames;

	@FindBy(xpath = "//div[text()='Rajitha'] ")
	private WebElement lblStudentSenderName;

	@FindBy(xpath = "//div[text()='prassana'] ")
	private WebElement lblStudent2SenderName;

	@FindBy(xpath = "//div[text()='Jyosthna'] ")
	private WebElement lblStudent3SenderName;

	@FindBy(xpath = "//li[text()='Leave Group']")
	private WebElement lblLeaveGroup;

	/*
	 * @FindBy(xpath =
	 * "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='Rupa']"
	 * ) private WebElement txtStudentSenderName;
	 */

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='Rajitha']")
	private WebElement txtStudent1SenderName;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='prassana']")
	private WebElement txtStudent2SenderName;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='Jyosthna']")
	private WebElement txtStudent3SenderName;

	@FindBy(xpath = "//div[@class='List_listContainer__1Rz3n']")
	private List<WebElement> lblListStudentGroupContainer;

	@FindBy(xpath = "//span[text()='StudentCreatedGroup']")
	private WebElement lblStudentGroupName;

	@FindBy(xpath = "//span[text()='Assignments']")
	private WebElement lblAssignmentsTab;

	@FindBy(xpath = "//div[@class='twelve wide computer column assignmentHeading ml-3 pl-0']")
	private WebElement lblAssignmentHeading;

	@FindBy(xpath = "//div[text()='Talk to tutor']")
	private WebElement lblTalkToTutor;

	@FindBy(xpath = "//div[@class='ui transparent left icon input']")
	private WebElement icnSearch;

	@FindBy(xpath = "//p[text()='Rupa Batch']")
	private WebElement lblSearchBatchName;

	@FindBy(xpath = "//input[@value='StudentCreatedGroup']")
	private WebElement lblGroupName;

	@FindBys({ @FindBy(xpath = "//div[@class='DateTime_time__2LwRn List_marginZero__39KOC']") })
	private List<WebElement> lblDateOrTimeTxt;

	@FindBy(xpath = "//span[@class='CreateGroup_adminLable__38zVL']")
	private WebElement lblAdminTag;

	@FindBy(xpath = "//p[text()='Store']")
	private WebElement btnStore;

	@FindBy(xpath = "//span[contains(text(),'Hello NestJS')]")
	private WebElement lblCourse;

	@FindBy(xpath = "//span[text()='Overview']")
	private WebElement lblOverview;

	@FindBy(xpath = "//span[text()='Content']")
	private WebElement lblContent;

	@FindBy(xpath = "//p[contains(text(),'Talk to Tutor')]")
	private WebElement btnTalkToTutor;

	@FindBy(xpath = "//input[@placeholder='Search for a course']")
	private WebElement txtSearch;

	@FindBy(xpath = "//div[@class='publishPopupTitle']")
	private WebElement lblCoureTitle;

	@FindBy(xpath = "//p[text()='Store']")
	private WebElement lblStore;

	public StudentConversationPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of StudentConversationPage method");

		PageFactory.initElements(driver, this);

		logger.info("Starting of StudentConversationPage method");

	}

	private static final Logger logger = Logger.getLogger(StudentConversationPage.class.getName());

	public String getAssignmentslabel() {
		logger.info("Starting of getAssignmentslabel method");
		logger.info("Ending of getAssignmentslabel method");
		
		this.explicitWait(lblAssignmentslabel);

		return lblAssignmentslabel.getText();
	}

	public String getAssignmentsHeadinglabel() {
		logger.info("Starting of getAssignmentsHeadinglabel method");
		logger.info("Ending of getAssignmentsHeadinglabel method");
		
		this.explicitWait(lblAssignmentsHeadinglabel);

		return lblAssignmentsHeadinglabel.getText();
	}

	public String getTalkToTutorlabel() {
		logger.info("Starting of getTalkToTutorlabel method");
		logger.info("Ending of getTalkToTutorlabel method");

		return lblTalkToTutor.getText();
	}

	public void clickOnStudentRecipient(String userName) {
		logger.info("Starting of clickOnStudentRecipient method");

//		this.pickFromWebElemetList(lblListOfSenderNames, userName);
		// this.lblStudentSenderName.click();
		this.fluentWait(lblStudentSenderName);

		logger.info("Ending of clickOnStudentRecipient method");
	}

	public void clickOnStudentTwoRecipient(String userName) {
		logger.info("Starting of clickOnStudentTwoRecipient method");

		// this.explicitWait(lblListOfSenderNames);
		//this.pickFromWebElemetList(lblListOfSenderNames, userName);
		// this.explicitWait(lblStudent2SenderName);
		// this.lblStudent2SenderName.click();
		this.fluentWait(lblStudent2SenderName);

		logger.info("Ending of clickOnStudentTwoRecipient method");
	}

	public void clickOnStudentThreeRecipient(String userName) {
		logger.info("Starting of clickOnStudentThreeRecipient method");

		// this.explicitWait(lblListOfSenderNames);
		//this.pickFromWebElemetList(lblListOfSenderNames, userName);
		// this.explicitWait(lblStudent3SenderName);
		// this.lblStudent3SenderName.click();
		
		this.fluentWait(lblStudent3SenderName);

		logger.info("Ending of clickOnStudentThreeRecipient method");
	}

	public void clickOnStudentGroup(String groupName) {
		logger.info("Starting of clickOnStudentGroup method");

	/*.explicitWait(lblListStudentGroupContainer);
		this.pickFromWebElemetList(lblListStudentGroupContainer, groupName);
		this.explicitWait(lblStudentGroupName);
		this.clickOnWebElement(lblStudentGroupName);
		// this.lblStudentGroupName.click();*/
		
		this.fluentWait(lblStudentGroupName);

		logger.info("Ending of clickOnStudentGroup method");
	}

	public String getLeaveGroupLabel() {
		logger.info("Starting of getDeleteGroupLabel method");
		logger.info("Ending of getDeleteGroupLabel method");

		return lblLeaveGroup.getText();
	}

	public void clickOnStudentCreatedGroupName(String userName) {
		logger.info("Starting of clickOnStudentCreatedGroupName method");

		/*this.explicitWait(lblListOfSenderNames);
		this.pickFromWebElemetList(lblListOfSenderNames, userName);
		this.explicitWait(lblStudentGroupName);
		this.lblStudentGroupName.click();*/
		
		this.fluentWait(lblStudentGroupName);

		logger.info("Ending of clickOnStudentCreatedGroupName method");
	}

	public void clickOnAssignmentsTab() {
		logger.info("Starting of clickOnAssignmentsTab method");

		this.lblAssignmentsTab.click();

		logger.info("Ending of clickOnAssignmentsTab method");
	}

	public void clickOnAssignmentsHeading() {
		logger.info("Starting of clickOnAssignmentsHeading method");

		this.lblAssignmentHeading.click();

		logger.info("Ending of clickOnAssignmentsHeading method");
	}

	public String getAssignmentHeading() {
		logger.info("Starting of getAssignmentHeading method");
		logger.info("Ending of getAssignmentHeading method");

		return lblAssignmentHeading.getText();
	}

	public void clickOnTalkToTutor() {
		logger.info("Starting of clickOnTalkToTutor method");

		this.lblTalkToTutor.click();

		logger.info("Ending of clickOnTalkToTutor method");
	}

	public String getStudentOneSenderNameTxt() {
		logger.info("Starting of getStudentOneSenderNameTxt method");
		logger.info("Ending of getStudentOneSenderNameTxt method");

		return txtStudent1SenderName.getText();
	}

	public String getStudentTwoSenderNameTxt() {
		logger.info("Starting of getStudentTwoSenderNameTxt method");
		logger.info("Ending of getStudentTwoSenderNameTxt method");

		return txtStudent2SenderName.getText();
	}

	public String getStudentThreeSenderNameTxt() {
		logger.info("Starting of getStudentThreeSenderNameTxt method");
		logger.info("Ending of getStudentThreeSenderNameTxt method");

		return txtStudent3SenderName.getText();
	}

	public String getAdminTag() {
		logger.info("Starting of getAdminTag method");
		logger.info("Starting of getAdminTag method");

		return lblAdminTag.getText();
	}

	public void clickOnStoreButton() {
		logger.info("starting of clickOnStoreButton method");

		try {
			this.btnStore.click();
		} catch (Exception e) {
			this.clickOnWebElement(btnStore);
		}

		logger.info("Ending of clickOnStoreButton method");
	}

	public void setSearchInStore(String strSearch) {
		logger.info("Starting of setSearchInStore method");

		this.txtSearch.sendKeys(strSearch);

		logger.info("Ending of setSearchInStore method");
	}

	public void clickOnCourse() {
		logger.info("starting of clickOnCourse method");

		this.lblCourse.click();

		logger.info("Ending clickOnCourse method");
	}

	public String getOverviewTxt() {
		logger.info("Starting of getOverviewTxt method");
		logger.info("Ending of getOverviewTxt method");

		return lblOverview.getText();
	}

	public String getContentTxt() {
		logger.info("Starting of getContentTxt method");
		logger.info("Ending of getContentTxt method");

		return lblContent.getText();
	}

	public void clickOnTalkToTutorButton() {
		logger.info("starting of clickOnTalkToTutorButton  method");

		try {
			hardWait(3);
			this.btnTalkToTutor.click();
		}catch(Exception e) {
			hardWait(3);
			this.clickOnWebElement(btnTalkToTutor);
		}

		logger.info("Ending of clickOnTalkToTutorButton method");
	}

	public String getTalkToTutorTxt() {
		logger.info("starting of getTalkToTutorTxt  method");
		logger.info("Ending of getTalkToTutorTxt method");

		return btnTalkToTutor.getText();
	}

	public void clickOnBackNavigationToStore() {
		logger.info("starting of clickOnBackNavigationToStore  method");

		this.driver.navigate().back();

		logger.info("Ending of clickOnBackNavigationToStore method");
	}

	public String getCoureTxt() {
		logger.info("Starting of getCoureTxt method");
		logger.info("Ending of getCoureTxt method");

		return lblCoureTitle.getText();
	}

	public String getStoreTxt() {
		logger.info("Starting of getStoreTxt method");
		logger.info("Ending of getStoreTxt method");

		return lblCoureTitle.getText();
	}

	public String isGreenColorDisplayed() {
		logger.info("Starting of isGreenColorDisplayed method");

		String colorString = this.lblAdminTag.getCssValue("background-color");

		String hex = Color.fromString(colorString).asHex();

		System.out.println("**********************************************" + hex);

		logger.info("Ending of isGreenColorDisplayed method");
		return hex;
	}

	public boolean getSenderNameTxt() {
		logger.info("Starting of getSenderNameTxt method");
		logger.info("Ending of getSenderNameTxt method");

		return lblSenderName.isDisplayed();
	}

	public boolean getIconSearch() {
		logger.info("Starting of getIconSearch method");
		logger.info("Ending of getIconSearch method");

		return icnSearch.isDisplayed();
	}

	public String getSearchBatchNameLabel() {
		logger.info("Starting of getSearchBatchNameLabel method");
		logger.info("Ending of getSearchBatchNameLabel method");
		
		this.explicitWait(lblSearchBatchName);

		return lblSearchBatchName.getText();
	}

	public String getGroupNameInGroupInformation() {
		logger.info("Starting of getGroupNameInGroupInformation method");

		this.explicitWait(lblGroupName);
		System.out.println(lblGroupName.getText());

		logger.info("Ending of getGroupNameInGroupInformation method");

		return lblGroupName.getAttribute("value");
	}

	public List getDescendingOrderOfLHSDayStatus() {
		logger.info("Starting of getDescendingOrderOfLHSDayStatus method");

		List<String> listOfLHSDayStatus = new ArrayList<String>();
		List<String> strListTimeam = new ArrayList<String>();
		List<String> strListTimetwelve = new ArrayList<String>();
		List<String> strListTimepm = new ArrayList<String>();
		List<String> strListYesterday = new ArrayList<String>();
		List<String> strListDate = new ArrayList<String>();

		// Condition for Different set of status of day chats
		for (WebElement str : lblDateOrTimeTxt) {
			if (str.getText().endsWith("am")) 
				strListTimeam.add(str.getText());
			else if (str.getText().endsWith("pm")) {
				if(str.getText().contains("12:"))
				strListTimetwelve.add(str.getText());
					else
				strListTimepm.add(str.getText());
			}
			else if (str.getText().endsWith("DAY"))
				strListYesterday.add(str.getText());
			else {
				strListDate.add(str.getText().replace("/", "-"));

			}
		}

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

		Collections.sort(strListTimeam, Collections.reverseOrder());
		Collections.sort(strListTimepm, Collections.reverseOrder());
		Collections.sort(strListYesterday);
		Arrays.sort(date, Collections.reverseOrder());

		for (String str : strListTimepm) {
			listOfLHSDayStatus.add(str);
		}
		
		for (String str : strListTimetwelve) {
			listOfLHSDayStatus.add(str);
		}
		
		for (String str : strListTimeam) {
			listOfLHSDayStatus.add(str);
		}	

		for (String str : strListYesterday) {
			listOfLHSDayStatus.add(str);
		}

		for (Date str : dat) {
			// System.out.println(str.getYear()+"/"+str.getMonthValue()+"/"+str.getDayOfMonth());
			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("YYYY/MM/dd");
			dateTimeFormatter.format(str);
			logger.debug(dateTimeFormatter.format(str));
			listOfLHSDayStatus.add(dateTimeFormatter.format(str));
		}
		logger.info("Ending of getDescendingOrderOfLHSDayStatus method");

		return listOfLHSDayStatus;

	}

	public List getDescendingOrderDayStatusOfLHS() {
		logger.info("Starting of getDescendingOrderDayStatusOfLHS method");

		List<String> listOfLHSDayStatus = new ArrayList<String>();
		for (WebElement str : lblDateOrTimeTxt) {
			listOfLHSDayStatus.add(str.getText());

		}

		logger.info("Ending of getDescendingOrderDayStatusOfLHS method");

		return listOfLHSDayStatus;
	}

}