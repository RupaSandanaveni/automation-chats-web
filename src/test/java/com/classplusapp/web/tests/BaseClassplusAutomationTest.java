package com.classplusapp.web.tests;

import static com.classplusapp.chat.util.Constants.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.classplusapp.chat.util.ExecutionMode;
import com.classplusapp.chat.util.TestListener;
import com.classplusapp.web.pages.ChatsPage;
import com.classplusapp.web.pages.ClassplusLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(TestListener.class)
public class BaseClassplusAutomationTest {

	protected List<WebDriver> lstDriver = new ArrayList<WebDriver>();
	
	
	protected static final String BASE_DIR = System.getProperty("user.dir");
	protected static final String FILE_SEPARATOR = System.getProperty("file.separator");
	protected DevTools chromeDevTools = null;
	protected WebDriver childWebDriver = null;
	protected static String browserDriverPath = null;
	protected static WebDriver globalDriver = null;
	private static String loginURL = "https://webp-gcp.classplusapp.com";
//https://webp-gcp.classplusapp.com
	protected static ClassplusLoginPage loginPage = null;
	protected static Map<String, String> chromeDriverMap = new HashMap<String, String>();
	private static final Logger logger = Logger.getLogger(BaseClassplusAutomationTest.class.getName());
	protected static Properties testDataProp = null;
	protected static Properties expectedAssertionsProp = null;
	private static Map<WEB_DRIVER, WebDriver> webDriverPool = new Hashtable<WEB_DRIVER, WebDriver>();

	protected static final String ORG_CODE = System.getProperty("chat_org_code");
	protected static final String EMAIL_ADDRESS = System.getProperty("chat_email_address");
	protected static final String TUTOR_MOBILENUMBER = System.getProperty("chat_tutor_mobile_number");
	protected static final String STUDENTONE_MOBILE_NUMBER = System.getProperty("chat_studentOne_mobile_number");
	protected static final String STUDENTTWO_MOBILE_NUMBER = System.getProperty("chat_studentTwo_mobile_number");
	protected static final String FACULTY_ONE_MOBILE_NUMBER = System.getProperty("chat_facultyOne_mobile_number");
	protected static final String FACULTY_TWO_MOBILE_NUMBER = System.getProperty("chat_facultyTwo_mobile_number");
	protected static final String PARENT_MOBILE_NUBMER = System.getProperty("chat_parent_mobile_number");
	
//	public static final String SEARCH_FOR_TUTOR_RECEPIENT = loginPage.getProfileNameTxt();

	public enum WEB_DRIVER {

		LOGIN_DRIVER_TEST,FACULTY_ONE_DRIVER_TEST_IN_FacultyCreatedNewGroupChatTest,
		FACULTY_TWO_DRIVER_TEST_IN_FacultyCreatedNewGroupChatTest, TUTOR_DRIVER_TEST_IN_TutorToStudentConversationTest,
		STUDENT_ONE_DRIVER_TEST_IN_TutorToStudentConversationTest,
		FACULTY_ONE_DRIVER_TEST_IN_FacultyToFacultyConversationTest,
		FACULTY_TWO_DRIVER_TEST_IN_FacultyToFacultyConversationTest,
		FACULTY_ONE_DRIVER_TEST_IN_MultipleBatchAnnouncementTest, FACULTY_ONE_DRIVER_TEST_IN_StartBroadCastTest,
		STUDENT_ONE_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest,
		STUDENT_TWO_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest, TUTOR_DRIVER_TEST_IN_StudentInBatchChatGroupTest,
		STUDENT_ONE_DRIVER_TEST_IN_StudentInBatchChatGroupTest, TUTOR_DRIVER_TEST_IN_MultipleBatchAnnouncementTest,
		STUDENT_ONE_DRIVER_TEST_IN_MultipleBatchAnnouncementTest, TUTOR_DRIVER_TEST_IN_StartBroadCastTest,
		TUTOR_DRIVER_TEST_IN_StudentGroupsTest, STUDENT_ONE_DRIVER_TEST_IN_StudentGroupsTest,
		FACULTY_ONE_DRIVER_TEST_IN_StudentGroupsTest, TUTOR_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest,TUTOR_DRIVER_TEST_IN_StudentCreatedNewGroupChatTest,
		STUDENT_ONE_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest, PARENT_ONE_DRIVER_TEST_IN_TutorCreatedNewGroupChatTest,
		TUTOR_DRIVER_TEST_IN_TutorInBatchChatGroupTest, STUDENT_ONE_DRIVER_TEST_IN_TutorInBatchChatGroupTest,
		TUTOR_DRIVER_TEST_IN_TutorToFacultyConversationTest, FACULTY_ONE_DRIVER_TEST_IN_TutorToFacultyConversationTest,
		TUTOR_DRIVER_TEST_IN_TutorToParentConversationTest, PARENT_ONE_DRIVER_TEST_IN_TutorToParentConversationTest,
		TUTOR_DRIVER_TEST_IN_VerifyStoriesTest, STUDENT_ONE_DRIVER_TEST_IN_VerifyStoriesTest,TUTOR_DRIVER_TEST_IN_FacultyInBatchChatGroupTest,
		FACULTY_ONE_DRIVER_TEST_IN_FacultyInBatchChatGroupTest
	}

	@BeforeSuite(groups = "sanity")
	public void initTestData() {

		if (testDataProp == null) {
			FileReader testDataReader = null;
			FileReader assertionsReader = null;

			try {
				testDataReader = new FileReader("src/main/resources/testdata.properties");
				assertionsReader = new FileReader("src/main/resources/expectedassertions.properties");

				testDataProp = new Properties();
				testDataProp.load(testDataReader);

				expectedAssertionsProp = new Properties();
				expectedAssertionsProp.load(assertionsReader);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {

					testDataReader.close();
					assertionsReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		logger.debug("Site URL : " + this.loginURL);
	}

	protected synchronized void quitDriver(WEB_DRIVER webDriver) {
		logger.info("Starting of method quitDriver in BaseClassplusAutomationTest ");

		WebDriver driver = webDriverPool.get(webDriver);
		try {
			if (driver != null) {
				driver.quit();
				driver = null;
				webDriverPool.remove(webDriver);
				logger.debug(webDriver + " Web driver quit successfully in BaseClassplusAutomationTest ");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			driver = null;
		}

		logger.info("Ending of method quitDriver in BaseClassplusAutomationTest");
	}

	// @AfterSuite
	/*
	 * protected synchronized void quitAllDrivers() { logger.
	 * info("Starting of method quitAllDrivers in BaseClassplusAutomationTest ");
	 * 
	 * WebDriver driver = null;
	 * 
	 * synchronized (webDriverPool) { if (!webDriverPool.isEmpty()) { for
	 * (WEB_DRIVER driverKey : webDriverPool.keySet()) {
	 * logger.debug("Quitting driver key: " + driverKey); synchronized
	 * (webDriverPool) { driver = webDriverPool.get(driverKey); try {
	 * 
	 * if (driver != null) { Thread.sleep(3000); driver.quit(); driver = null;
	 * webDriverPool.remove(driverKey); Thread.sleep(3000);
	 * logger.debug("Driver quit successfully in BaseClassplusAutomationTest "); } }
	 * catch (Exception ex) { logger.error(ex.getMessage()); driver = null; } }
	 * 
	 * }
	 * 
	 * } }
	 * logger.info("Ending of method quitAllDrivers in BaseClassplusAutomationTest"
	 * ); }
	 */

	/**
	 * This method is used for get driver
	 * 
	 * @param webDriver
	 * @return
	 */
	protected synchronized WebDriver getWebDriver(String browser, WEB_DRIVER webDriver) {
		logger.info("Starting of method getWebDriver");

		WebDriver driver = webDriverPool.get(webDriver);

		String osPath = System.getProperty("os.name");

		// Use existing driver
		if (driver != null) {
			logger.debug("Using existing web driver " + webDriver);
			return driver;

		}

		if (osPath.contains("Linux")) {

			if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				options.addArguments("--no-sandbox");
				driver = new FirefoxDriver(options);
			} else if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");
				options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				options.setHeadless(true);
				options.addArguments("--headless"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
				options.addArguments("--window-size=1920,1080");
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);// del
				options.addArguments("--disable-browser-side-navigation"); // del
				options.addArguments("--disable-dev-shm-usage"); // del
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");

				// options.setBinary("/opt/google/chrome/google-chrome");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.popups", 0);
				options.setExperimentalOption("prefs", prefs);

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("CHROME");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
				capabilities.setCapability("applicationCacheEnabled", "true");

				driver = new ChromeDriver(options);

			}
		} else if (osPath.contains("Mac OS X")) {
			if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");

				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				driver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			}
		} else {

			if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

				// options.setHeadless(true); options.addArguments("--no-sandbox");

				driver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("Chromium")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();

			} else if (browser.equalsIgnoreCase("IEDriverServer")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		logger.info("**************** Driver Successfully Created *************** " + driver.getTitle());

		logger.info("End of method getWebDriver");

		webDriverPool.put(webDriver, driver);
		lstDriver.add(driver);
		return driver;
	}

	public void siteLogin(String strOrgCode, String strMobileNumber, String strEmailAddress, WebDriver driver) {
		logger.info("Starting of siteLogin method");

		driver.get(loginURL);
		this.childWebDriver = driver;

		// String xPath = "//input[@class='accountLogin-input
		// accountLogin-input-mobile']";
		// this.fluentWaitForElement(driver, xPath);

		// this.loginPage.clickOutside();
		this.loginPage.loginToClassplusUsingMobileNumber(strOrgCode, strMobileNumber, strEmailAddress);

		logger.info("Ending of siteLogin method");
	}

	public void goToSite(WebDriver driver) throws Exception {

		logger.debug("Login URL" + loginURL);

		driver.get(loginURL);
	}

	public void logBrowserConsoleErrors(WebDriver driver) {
		LogEntries logentries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry logentrey : logentries) {
			logger.error("===========================");
			logger.error(logentrey);
			logger.error("===========================");
		}
	}

	public WebDriver getChildWebDriver() {
		return this.childWebDriver;
	}

	public WebDriver getDriver() {
		return globalDriver = webDriverPool.get(WEB_DRIVER.LOGIN_DRIVER_TEST);

	}

	protected WebDriver loginClassPlusSite(String browser, String orgCode, String mobileNumber, String emailAddress,
			WEB_DRIVER driverKey) throws Exception {

		WebDriver childDriver = this.getWebDriver(browser, driverKey);
		this.initClassplusSiteLogin(browser, childDriver);
		this.testLoginSiteUI(orgCode, mobileNumber, emailAddress, childDriver);
		this.testVerifyOTP(childDriver);
		
		if(mobileNumber.equals("9711153079")) {
			SEARCH_FOR_TUTOR_RECEPIENT = loginPage.getProfileNameTxt();
			
			logger.debug("Tutor Name :" +SEARCH_FOR_TUTOR_RECEPIENT);
		}

		return childDriver;
	}

	public void initClassplusSiteLogin(String browser, WebDriver childDriver) throws Exception {
		logger.info("Starting of initClassplusSiteLogin methond");

		this.goToSite(childDriver);
		this.loginPage = new ClassplusLoginPage(childDriver);

		logger.info("Ending of initClassplusSiteLogin method");
	}
	public void initClassplusSiteLogout(WebDriver driver) throws Exception {
		logger.info("Starting of initClassplusSiteLogin methond");

		this.loginPage = new ClassplusLoginPage(driver);
		loginPage.clickOnLogOut();
		
		logger.info("Ending of initClassplusSiteLogin method");
	}

	public void testLoginSiteUI(String orgCode, String mobileNumber, String emailAddress, WebDriver childDriver) {
		logger.info("Starting of LoginToSite method");
		try {
			this.siteLogin(orgCode, mobileNumber, emailAddress, childDriver);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing LoginTosite: " + e.getMessage());
			logger.error("Error occured while testing the LoginTosite   ", e);
		}

		logger.info("Ending of LoginTo Site method");
	}

	public void fluentWaitForElement(WebDriver childDriver, String xPath) {

		try {

			// Reference : https://www.guru99.com/implicit-explicit-waits-selenium.html
			Wait<WebDriver> wait = new FluentWait<WebDriver>(childDriver).withTimeout(Duration.ofSeconds(3))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);

			WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(xPath));
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void testVerifyOTP(WebDriver childDriver) {
		logger.info("Starting of testVerifyOTP method");
		try {
			this.loginPage.setOTP(testDataProp.getProperty(LOGIN_OTP));
			this.loginPage.clickOnVerifyOTP();
			/*
			 * String getOTPVerifiedText = this.loginPage.getOTPVerifiedMessageTxt();
			 * Assert.assertEquals(getOTPVerifiedText,
			 * expectedAssertionsProp.getProperty(LOGIN_OTP_SUCCESS_MESSAGE));
			 * 
			 * 
			 */

		} catch (Exception e) {
			Assert.fail("Exception occured while testing VerifyOTP: " + e.getMessage());
			logger.error("Error occured while testing the VerifyOTP  ", e);
		}
		logger.info("Ending of testVerifyOTP method");
	}
	
	


	public List<WebDriver> getDriversList() {
			return lstDriver;
		}

	protected boolean isHeadless() {

		if (ExecutionMode.HEADLESS.getMode())
			return true;
		else
			return false;

	}

}
