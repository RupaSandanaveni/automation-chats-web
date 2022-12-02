package com.classplusapp.web.pages.faculty;

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

import com.classplusapp.web.pages.ChatsPage;

public class FacultyConversationPage extends ChatsPage {

	@FindBys({ @FindBy(xpath = "//div[@class='DateTime_time__2LwRn List_marginZero__39KOC']") })
	private List<WebElement> lblDateOrTimeTxt;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='Raghuna']")
	private WebElement txtFacultyTwoSenderName;

	@FindBy(xpath = "//div[@class='Item_item__1PDSu  item undefined ']/div/div/span[text()='Sathish']")
	private WebElement txtFacultyThreeSenderName;

	@FindBy(xpath = "//span[@class='CreateGroup_adminLable__38zVL']")
	private WebElement lblAdminTag;

	@FindBy(xpath = "//span[@class='Header_backButton__2L_EF']")
	private WebElement imgHeaderBackButton;

	public FacultyConversationPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of FacultyConversationPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of FacultyConversationPage method");
	}

	private static final Logger logger = Logger.getLogger(FacultyConversationPage.class.getName());

	public List getDescendingOrderDayStatusOfLHSTxt() {
		logger.info("Starting of getDescendingOrderDayStatusOfLHSTxt method");

		List<String> listOfLHSDayStatus = new ArrayList<String>();
		for (WebElement str : lblDateOrTimeTxt) {
			listOfLHSDayStatus.add(str.getText());

		}

		logger.info("Ending of getDescendingOrderDayStatusOfLHSTxt method");

		return listOfLHSDayStatus;
	}

	public List getDescendingOrderOfLHSDayStatusTxt() {
		logger.info("Starting of getDescendingOrderOfLHSDayStatusTxt method");

		List<String> listOfLHSDayStatus = new ArrayList<String>();
		List<String> strListTimeam = new ArrayList<String>();
		List<String> strListTimepm = new ArrayList<String>();
		List<String> strListYesterday = new ArrayList<String>();
		List<String> strListDate = new ArrayList<String>();

		// Condition for Different set of status of day chats
		for (WebElement str : lblDateOrTimeTxt) {
			if (str.getText().endsWith("am"))
				strListTimeam.add(str.getText());
			else if (str.getText().endsWith("pm"))
				strListTimepm.add(str.getText());
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
		logger.info("Ending of getDescendingOrderOfLHSDayStatusTxt method");

		return listOfLHSDayStatus;

	}

	public String getAdminTagTxt() {
		logger.info("Starting of getAdminTagTxt method");
		logger.info("Ending of getAdminTagTxt method");

		return lblAdminTag.getText();
	}

	public void clickOnHeaderBackButton() {
		logger.info("Starting of clickOnHeaderBackButton method");

		this.imgHeaderBackButton.click();

		logger.info("Ending of clickOnHeaderBackButton method");

	}

	public String isDisplayedGreenColor() {
		logger.info("Starting of isDisplayedGreenColor method");

		String colorString = this.lblAdminTag.getCssValue("background-color");

		String hex = Color.fromString(colorString).asHex();

		System.out.println("**********************************************" + hex);

		logger.info("Ending of isDisplayedGreenColor method");
		return hex;
	}

	public boolean getFacultyTwoSenderNameTxt() {
		logger.info("Starting of getFacultyTwoSenderNameTxt method");
		logger.info("Ending of getFacultyTwoSenderNameTxt method");

		return txtFacultyTwoSenderName.isDisplayed();
	}

	public boolean getFacultyThreeSenderNameTxt() {
		logger.info("Starting of getFacultyThreeSenderNameTxt method");
		logger.info("Ending of getFacultyThreeSenderNameTxt method");

		return txtFacultyThreeSenderName.isDisplayed();
	}

}
