package com.classplusapp.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClassplusLoginPage extends BaseClassplusAutomationPage {

	@FindBy(xpath = "//p[@class='accountLogin-heading']")
	private WebElement lblLoginHeading;

	@FindBy(xpath = "//input[@class='accountLogin-input']")
	private WebElement txtOrgCode;

	@FindBy(xpath = "//input[@class='accountLogin-input accountLogin-input-mobile']")
	private WebElement txtMobileNumber;

	@FindBy(xpath = "//button[contains(text(),'Proceed Securely')]") // button[contains(text(),'Proceed Securely')]
	private WebElement btnProceedSecurely;

	@FindBy(xpath = "//p[@class='accountLogin-type-text']")
	private WebElement txtAlternativeLogin;

	@FindBy(xpath = "//input[@data-cy='login_email']")
	private WebElement txtEmailInput;

	@FindBy(xpath = "//div[@class='accountLogin-org-error-text']")
	private WebElement txtInvalidOrgCode;

	@FindBy(xpath = "//label[@class='accountLogin-error-message']")
	private WebElement msgLoginError;

	@FindBy(xpath = "//button[@class='otp-back-button']")
	private WebElement btnBackToLogin;

	@FindBy(xpath = "//input[@id='otp_input']")
	private WebElement txtOTPInput;// div[@class=\"otp-container\"]/input

	@FindBy(xpath = "//div[contains(text(), 'Send OTP again')]")
	private WebElement btnResendOTP;

	@FindBy(xpath = "//button[@data-cy='login_verify']")
	private WebElement btnVerifyOTP;

	@FindBy(xpath = "//button[@class='rrt-button rrt-ok-btn']")
	private WebElement btnOk;

	@FindBy(xpath = "//i[@class='dropdown icon']")
	private WebElement lblListBox;

	@FindBy(xpath = "//p[contains(text(),'Logout')]")
	private WebElement btnLogOut;

	@FindBy(xpath = "//div[contains(text(),'OTP Sent Successfully')]")
	private WebElement msgOTPSent;

	@FindBy(xpath = "//div[contains(text(),'OTP Verified Successfully')]")
	private WebElement msgOTPVerified;

	@FindBy(xpath = "//p[contains(text(),'Enter OTP here')]")
	private WebElement txtOTPHeading;

	@FindBy(xpath = "//div[contains(text(),'Something went wrong')]")
	private WebElement msgError;

	@FindBy(xpath = "//p[contains(text(),'Login to your account')]")
	private WebElement lblLoginToAccount;

	@FindBy(xpath = "//p[contains(text(),'Batches')]")
	private WebElement lblDashBoard;

	@FindBy(xpath = "//div[contains(text(),'Are you sure about logout?')]")
	private WebElement msgLogoutConfirmation;

	@FindBy(xpath = "(//i[@class='dropdown icon'])[last()]")
	private WebElement lblUerProfile;
	
	@FindBy(xpath = "//span[@class='profile-thumbnail']")
	private WebElement lblProfileName;

	private static final Logger logger = Logger.getLogger(ClassplusLoginPage.class.getName());

	public ClassplusLoginPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of ClassplusLoginPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of ClassplusLoginPage method");
	}

	public String getLoginHeadingText() {
		logger.info("Starting of getLoginHeadingText method");
		logger.info("Ending of getLoginHeadingText method");

		return lblLoginHeading.getText();
	}

	public void setOrgCode(String strOrgCode) {
		logger.info("Starting of setOrgCode method");

		// fluentWait(txtOrgCode);
		hardWait(5);
		this.txtOrgCode.clear();
		this.txtOrgCode.sendKeys(strOrgCode);

		logger.info("Ending of setOrgCode method");
	}

	public void setMobileNumber(String strMobileNumber) {
		logger.info("Starting of setMobileNumber method");

		// this.explicitWait(txtMobileNumber);

		hardWait(2);
		this.txtMobileNumber.clear();
		this.txtMobileNumber.sendKeys(strMobileNumber);

		logger.info("Ending of setMobileNumber method");
	}

	public void setEmailAddress(String strEmailAddress) {
		logger.info("Starting of setEmailAddress method");

		this.explicitWait(txtEmailInput);
		this.txtEmailInput.clear();
		this.txtEmailInput.sendKeys(strEmailAddress);

		logger.info("Ending of strEmailAddress method");
	}

	public void clickOnProceedSecurelyButton() {
		logger.info("Starting of clickOnProceedSecurelyButton method");

		try {
			hardWait(2);
			this.btnProceedSecurely.click();
		} catch (Exception e) {
			hardWait(2);
			this.clickOnWebElement(btnProceedSecurely);
		}

		logger.info("Ending of clickOnProceedSecurelyButton method");
	}

	public void clickForAlterantiveLogin() {
		logger.info("Starting of clickForAlterantiveLogin method");

		this.explicitWait(txtAlternativeLogin);
		this.clickOnWebElement(txtAlternativeLogin);

		logger.info("Ending of clickForAlterantiveLogin method");
	}

	public void loginToClassplusUsingMobileNumber(String strOrgCode, String strMobileNumber, String strEmailAddress) {
		logger.info("Starting of LoginToClassplusUsingMobileNumber method");

		this.setOrgCode(strOrgCode);
		try {
			this.setMobileNumber(strMobileNumber);
		} catch (Exception e) {
			this.setEmailAddress(strEmailAddress);
			this.clickForAlterantiveLogin();
			try {
				this.setMobileNumber(strMobileNumber);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		this.clickOnProceedSecurelyButton();

		logger.info("Ending of LoginToClassplusUsingMobileNumber method");
	}

	public void loginToClassplusUsingEmailAddress(String strOrgCode, String strEmailAddress) {
		logger.info("Starting of loginToClassPlusUsingEmailAddress method");

		this.setOrgCode(strOrgCode);
		this.clickForAlterantiveLogin();
		this.setEmailAddress(strEmailAddress);
		this.clickOnProceedSecurelyButton();

		logger.info("Ending of loginToClassPlusUsingEmailAddress method");
	}

	public void clickOnBackToLogin() {
		logger.info("Starting of clickOnBackToLogin method");

		this.btnBackToLogin.click();

		logger.info("Ending of clickOnBackToLogin method");
	}

	public void setOTP(String strOTP) {
		logger.info("Starting of setOTP  method");

		// this.explicitWait(txtOTPInput);
		hardWait(2);
		this.txtOTPInput.sendKeys(strOTP);

		logger.info("Ending of setOTP method");
	}

	public void clickOnResendOTP() {
		logger.info("Starting of clickOnResendOTP methond");

		this.btnResendOTP.click();

		logger.info("Ending of clickOnResendOTP methond");
	}

	public void clickOnVerifyOTP() {
		logger.info("Starting of clickOnVerifyOTP method");

		// this.explicitWait(btnVerifyOTP);
		hardWait(2);
		this.btnVerifyOTP.click();
		hardWait(2);

		logger.info("Ending of clickOnVerifyOTP method");
	}

	public void clickOnListBox() {
		logger.info("Starting of clickOnListBox method");

		this.clickOnWebElement(lblListBox);

		logger.info("Ending of clickOnListBox method");
	}

	public void clickOnLogOut() {
		logger.info("Starting of clickOnLogOut method ");

		this.clickOnWebElement(lblUerProfile);
		this.explicitWait(btnLogOut);
		this.clickOnWebElement(btnLogOut);
		this.clickOnOk();

		logger.info("Ending of clickOnLogOut method");
	}

	public void clickOnOk() {
		logger.info("Starting of clickOnOK method");

		this.btnOk.click();

		logger.info("Ending of clickOnOk method");
	}

	public String getInvalidOrgCodeErrorMessageTxt() {
		logger.info("Starting of getInvalidOrgCodeErrorMessageTxt method");
		logger.info("Ending of getInvalidOrgCodeErrorMessageTxt method");

		return txtInvalidOrgCode.getText();
	}

	public String getInvalidMobileNumberErrorMessageTxt() {
		logger.info("Starting of getInvalidMobileNumberErrorMessageTxt method");
		logger.info("Ending of getInvalidMobileNumberErrorMessageTxt method");

		return msgError.getText();
	}

	public String getInvalidEmailAddressErrorMessageTxt() {
		logger.info("Starting of getInvalidEmailAddressErrorMessageTxt method");
		logger.info("Ending of getInvalidEmailAddressErrorMessageTxt method");

		return msgError.getText();
	}

	public String getPageTitleTxt(WebDriver driver) {
		logger.info("Starting of getPageTitleTxt method");
		logger.info("Ending of getPageTitleTxt method");

		return driver.getTitle();
	}

	public String getOTPSentMessageTxt() {
		logger.info("Starting of getOTPSentMessageTxt method");

		String otpMessageText = this.msgOTPSent.getText();

		logger.info("Ending of getOTPSentMessageTxt method");

		return otpMessageText;
	}

	public String getOTPVerifiedMessageTxt() {
		logger.info("Starting of getOTPVerifiedMessageTxt method");

		this.explicitWait(msgOTPVerified);
		String otpVerifiedMessageText = this.msgOTPVerified.getText();
		logger.info("Ending of getOTPVerifiedMessageTxt method");

		return otpVerifiedMessageText;
	}

	public String getOTPHeadingTxt() {
		logger.info("Starting of getOTPHeadingTxt method");

		this.explicitWait(txtOTPHeading);
		String otpHeadingText = this.txtOTPHeading.getText();

		logger.info("Ending of getOTPHeadingTxt method");

		return otpHeadingText;
	}

	public String getErrorMessageTxt() {
		logger.info("Starting of getErrorMessageTxt method");

		this.explicitWait(msgError);
		String errorMessageText = this.msgError.getText();

		logger.info("Ending of getErrorMessageTxt method");

		return errorMessageText;
	}

	public String getLoginToAccountTxt() {
		logger.info("Starting of getLoginToAccountTxt method");

		this.explicitWait(lblLoginToAccount);
		String loginToAccountText = this.lblLoginToAccount.getText();

		logger.info("Ending of getLoginToAccountTxt method");

		return loginToAccountText;
	}

	public String getDashBoardTxt() {
		logger.info("Starting of getDashBoardTxt method");

		this.explicitWait(lblDashBoard);
		String dashBoardText = this.lblDashBoard.getText();

		logger.info("Ending of getDashBoardTxt method");

		return dashBoardText;
	}
	
	public String getProfileNameTxt() {
		logger.info("Starting of getProfileNameTxt method");
		logger.info("Ending of getProfileNameTxt method");
	
		return lblProfileName.getText();

	}

	public String getLogoutConfirmMessageTxt() {
		logger.info("Starting of getLogoutConfirmMessageTxt method");

		this.explicitWait(msgLogoutConfirmation);
		String logoutConfirmMessageText = this.msgLogoutConfirmation.getText();

		logger.info("Ending of getLogoutConfirmMessageTxt method");

		return logoutConfirmMessageText;
	}
}
