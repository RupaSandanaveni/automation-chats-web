package com.classplusapp.web.tests;

import static com.classplusapp.chat.util.Constants.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.classplusapp.web.pages.ClassplusLoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Tutor Login")
@Feature("Tutor Login")
public class ClassplusLoginTest extends BaseClassplusAutomationTest {
	private WebDriver driver = null;
	private static final Logger logger = Logger.getLogger(ClassplusLoginTest.class.getName());

	@BeforeClass
	@Parameters({ "browser" })
	public void initClassplusSiteLogin(String browser) throws Exception {
		logger.info("Starting of initClassplusSiteLogin methond");

		this.driver = this.getWebDriver(browser, WEB_DRIVER.LOGIN_DRIVER_TEST);
		this.loginPage = new ClassplusLoginPage(driver);
		logger.info("Ending of initClassplusSiteLogin method");
	}

	@Test(priority = 1, description = "site login")
	@Parameters({ "orgCode", "mobileNumber", "emailAddress" })
	@Description("Test Description:Verify Tutor can login the page")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify Tutor can login the page")
	public void testLoginSiteUI(String orgCode, String mobileNumber, String emailAddress) {
		logger.info("Startitng of LoginToSite method");

		try {
			this.siteLogin(orgCode, mobileNumber, emailAddress, this.driver);

			String lblLoginHeading = this.loginPage.getLoginHeadingText();
			Assert.assertEquals(lblLoginHeading, expectedAssertionsProp.getProperty(LABEL_LOGIN_HEADING));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing LoginTosite: " + e.getMessage());
			logger.error("Error occured while testing the LoginTosite   ", e);
		}

		logger.info("Ending of LoginTo Site method");
	}

	@Test(priority = 2, description = "verify OTP")
	@Description("Test Description:Verify OTP")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify OTP")
	public void testVerifyOTP() {
		logger.info("Starting of testVerifyOTP method");

		try {
			String xPath = "//p[contains(text(),'Enter OTP here')]";
			this.fluentWaitForElement(this.driver, xPath);

			String getOTPHeadingText = this.loginPage.getOTPHeadingTxt();
			Assert.assertEquals(getOTPHeadingText, expectedAssertionsProp.getProperty(LABEL_OTP_HEADING));

			this.loginPage.setOTP(testDataProp.getProperty(LOGIN_OTP));
			this.loginPage.clickOnVerifyOTP();

			/*
			 * String getOTPVerifiedText = this.loginPage.getOTPVerifiedMessageText();
			 * Assert.assertEquals(getOTPVerifiedText,
			 * expectedAssertionsProp.getProperty(LOGIN_OTP_SUCCESS_MESSAGE));
			 */

		} catch (Exception e) {
			Assert.fail("Exception occured while testing VerifyOTP: " + e.getMessage());
			logger.error("Error occured while testing the VerifyOTP  ", e);
		}

		logger.info("Ending of testVerifyOTP method");
	}

	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			Thread.sleep(5000);
			this.loginPage.clickOnLogOut();
			if (driver != null) {
				this.quitDriver(WEB_DRIVER.LOGIN_DRIVER_TEST);
				logger.debug("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Ending of quitDriver method");
	}
}
