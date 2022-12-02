package com.classplusapp.web.pages.parent;

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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.classplusapp.web.pages.BaseClassplusAutomationPage;

public class ParentConversationPage extends BaseClassplusAutomationPage {

	@FindBys({ @FindBy(xpath = "//div[@class='DateTime_time__2LwRn List_marginZero__39KOC']") })
	private List<WebElement> lblDateOrTimeTxt;

	@FindBy(xpath = "(//img[@class='Item_img__2HaKx'])[last()]")
	private WebElement imgProfile;

	@FindBy(xpath = "//div[text()='Raghuna']")
	private WebElement lblFacultyTwoSenderName;

	@FindBy(xpath = "//div[@class='List_unseenHeader__RnECG']//following-sibling::div")
	private List<WebElement> lblListOfSenderNames;

	public ParentConversationPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of ParentConversationPage method");

		PageFactory.initElements(driver, this);

		logger.info("Starting of ParentConversationPage method");
	}

	private static final Logger logger = Logger.getLogger(ParentConversationPage.class.getName());

	public void clickOnFacultyTwoRecipient(String userName) {
		logger.info("Starting of clickOnFacultyTwoRecipient method");

		this.pickFromWebElemetList(lblListOfSenderNames, userName);
		try {
			this.lblFacultyTwoSenderName.click();
		} catch (Exception e) {
			this.clickOnWebElement(lblFacultyTwoSenderName);
		}

		logger.info("Ending of clickOnFacultyTwoRecipient method");
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

	public List getDescendingOrderDayStatusOfLHSTxt() {
		logger.info("Starting of getDescendingOrderDayStatusOfLHSTxt method");

		List<String> listOfLHSDayStatus = new ArrayList<String>();
		for (WebElement str : lblDateOrTimeTxt) {
			listOfLHSDayStatus.add(str.getText());

		}

		logger.info("Ending of getDescendingOrderDayStatusOfLHSTxt method");

		return listOfLHSDayStatus;
	}

}
