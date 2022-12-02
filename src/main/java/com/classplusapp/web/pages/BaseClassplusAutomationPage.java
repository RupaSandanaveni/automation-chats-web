package com.classplusapp.web.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClassplusAutomationPage {
	protected WebDriver driver = null;
	public static String TEST_FILE_PATH = null;

	protected static final Logger logger = Logger.getLogger(BaseClassplusAutomationPage.class.getName());

	public BaseClassplusAutomationPage(WebDriver driver) {
		logger.info("Starting of BaseClassplusAutomationPage method");

		this.driver = driver;
		if (TEST_FILE_PATH == null) {
			TEST_FILE_PATH = getTestFilePath();

			logger.debug("In Constructor " + TEST_FILE_PATH);
		}
		PageFactory.initElements(driver, this);

		logger.info("Ending of BaseClassplusAutomationPage method");

	}

	public WebDriver getWebDriver() {
		logger.info("Starting of getWebDriver method");
		logger.info("Ending of getWebDriver method");

		return this.driver;
	}

	public String getTestFilePath() {
		logger.info("Starting of getTestFilePath method");

		String path = "src/main/resources";
		File file = new File(path);

		logger.info("Endong of getTestFilePath method");

		return file.getAbsolutePath();
	}

	protected void selectDropdown(String id, String value) {
		logger.info("Starting of selectDropdown method");

		Select conditions = new Select(driver.findElement(By.id(id)));
		conditions.selectByValue(value);

		logger.info("Ending of selectDropdown method");
	}

	public void clickOnWebElement(WebElement webelement) {
		logger.info("Starting of clickOnWebElement method");

		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		jsExec.executeScript("arguments[0].click();", webelement);

		logger.info("Ending of clickOnWebElement method");
	}

	public void scrollDown(int scroll) {
		logger.info("Starting of scrollDown method");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, " + scroll + ")");

		logger.info("Ending of scrollDown method");
	}

	public void refresh() {
		logger.info("Starting of refresh method");

		driver.navigate().refresh();

		logger.info("Ending of refresh method");
	}

	public void impicitWait() {
		logger.info("Starting of impicitWait method");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		logger.info("Ending of impicitWait method");
	}

	public void explicitWait(List<WebElement> categoryOptions) {
		logger.info("Startng of explicitWait method");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfAllElements(categoryOptions));

		logger.info("Ending of explicitWait method");
	}

	public void explicitWait(WebElement categoryOptions) {
		logger.info("Starting of explicitWait method");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
		wait.until(ExpectedConditions.visibilityOf(categoryOptions));

		logger.info("Ending of explicitWait method");
	}

	public void pickFromWebElemetList(List<WebElement> webElements, String containsText) {
		logger.info("Starting of pickFromWebElemetList method");

		logger.debug(webElements.size());
		for (WebElement webElement : webElements) {
			if (webElement.getText().contains(containsText)) {
				this.clickOnWebElement(webElement);
				break;
			}
		}

		logger.info("Ending of pickFromWebElemetList method");
	}

	public void hardWait(int seconds) {
		logger.info("Staritng of hardWait method");

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		logger.info("Ending of hardWait method");
	}

	public void pickFromWebElemetList(List<WebElement> webElements, List<WebElement> textWebElements,
			String containsText) {
		logger.info("Staritng of pickFromWebElemetList method");

		WebElement webElement = null;
		WebElement textWebElement = null;
		Object[] webElementsArray = webElements.toArray();
		Object[] xPathArray = textWebElements.toArray();

		for (int i = 0; i < webElements.size(); i++) {
			webElement = (WebElement) webElementsArray[i];
			textWebElement = (WebElement) xPathArray[i];
			if (textWebElement.getText().contains(containsText)) {
				this.clickOnWebElement(webElement);
				break;
			}
		}

		logger.info("Ending of pickFromWebElemetList method");
	}

	public void scrollIntoView(WebElement element) {
		logger.info("Starting of scrollIntoView method");

		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		jsExec.executeScript("arguments[0].scrollIntoView(true);", element);

		logger.info("Ending of scrollIntoView method");
	}

	public void switchToNewWindow() {
		logger.info("Starting of switchToNewWindow method");

		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));

		logger.info("Ending of switchToNewWindow method");
	}

	public void closeWindow() {
		logger.info("Starting of closeWindow method");

		driver.close();
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));

		logger.info("Ending of closeWindow method");
	}

	public void waitForElementVisibilty(WebElement element) {
		logger.info("Starting of waitForElementVisibilty method");

		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));

		logger.info("Ending of waitForElementVisibilty method");
	}

	public void dragAndSort(List<WebElement> webElementList, Integer targetIndex, Integer destIndex) {
		logger.info("Starting of dragAndSort method");

		Actions action = new Actions(driver);
		WebElement target = webElementList.get(targetIndex);
		WebElement dest = webElementList.get(destIndex);
		action.click(target).clickAndHold().moveToElement(dest).moveByOffset(0, 50).release().build().perform();

		logger.info("Ending of dragAndSort method");
	}

	protected void closeOSWindow() {
		logger.info("Starting of closeOSWindow method");

		Robot robot = null;
		try {
			if (System.getProperty("os.name").contains("Windows 10")) {
				robot = new Robot();
				for (int i = 0; i < 3; i++) {
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);

				}
				robot.keyPress(KeyEvent.VK_ENTER);

				robot.delay(500);

				robot.keyRelease(KeyEvent.VK_ENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Ending of closeOSWindow method");
	}

	public Integer getCountText(WebElement lblCount) {
		logger.info("Starting of getCountText method");

		String participantsCount = lblCount.getText();
		String returnCount = "";
		System.out.println("Participants count is   " + participantsCount);
		String str[] = participantsCount.split("\\D");
		for (String s : str)
			if (s != "")
				returnCount = s;
		int countValue = 0;
		if (!returnCount.equals(""))
			countValue = Integer.parseInt(returnCount);
		System.out.println("Count" + countValue);

		logger.info("Ending of getCountText method");
		return countValue;
	}

	protected void fluentWait(WebElement element) {
		logger.info("Starting of fluentWait method");

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50L))
				.pollingEvery(Duration.ofSeconds(5L)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		jsExec.executeScript("arguments[0].click();", element);

		logger.info("Ending of fluentWait method");
	}

	public void implicitWait(Duration duration) {
		logger.info("Starting of implicitWait Method");

		driver.manage().timeouts().implicitlyWait(duration);

		logger.info("Ending of implicitWait Method");
	}

}
