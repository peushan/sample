package pageobjects;

import base.Base;
import base.BasePage;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class KiwiSaverRetirementCalculator extends BasePage {

    private Logger logger = Logger.getLogger(KiwiSaverRetirementCalculator.class);

    @FindBy(xpath = "//div[@model='ctrl.data.CurrentAge']//input")
    private WebElement txtAge;

    @FindBy(xpath = "//div[@ng-model='ctrl.data.EmploymentStatus']")
    private WebElement drpEmployeeStatus;

    @FindBy(xpath = "//div[@ng-model='ctrl.data.EmploymentStatus']//ul//li[@value='employed']")
    private WebElement drpEmployeeStatusSelectEmployed;

    @FindBy(xpath = "//div[@model='ctrl.data.AnnualIncome']//input")
    private WebElement txtAnnualIncome;

    @FindBy(xpath = "//div[@ng-model='ctrl.data.PIRRate']")
    private WebElement drpPIRRate;

    @FindBy(xpath = "//div[@model='ctrl.data.KiwiSaverBalance']//input")
    private WebElement txtKiwiSavaerBalance;

    @FindBy(xpath = "//div[@model='ctrl.data.VoluntaryContributions']//input")
    private WebElement txtVoluntaryContributions;

    @FindBy(xpath = "//div[@model='ctrl.data.SavingsGoal']//input")
    private WebElement txtSavingsGoal;

    @FindBy(xpath = "//div[@ng-model='$parent.period']//div[@ng-bind-html='selectedContent']")
    private WebElement drpFrequency;

    @FindBy(css = "button[ng-click='ctrl.showResultsPanel()")
    private WebElement btnCalculate;

    @FindBy(xpath = "//span[@class='result-value result-currency ng-binding']")
    private WebElement txtValue;

    //identifying the info icons

    @FindBy(xpath = "//div[@help-id='CurrentAge']//button")
    private WebElement lnkAgeInfo;

    @FindBy(xpath = "//div[@help-id='EmploymentStatus']//button")
    private WebElement lnkEmploymentStatusInfo;

    @FindBy(xpath = "//div[@help-id='KiwiSaverMemberContribution']//button")
    private WebElement lnkKiwiSaverMemberContributionInfo;

    @FindBy(xpath = "//div[@help-id='AnnualIncome']//button")
    private WebElement lnkAnnualIncomeInfo;

    @FindBy(xpath = "//div[@help-id='PIRRate']//button")
    private WebElement lnkPIRRateInfo;

    @FindBy(xpath = "//div[@help-id='KiwiSaverBalance']//button")
    private WebElement lnkKiwiSaverBalanceInfo;

    @FindBy(xpath = "//div[@help-id='VoluntaryContributions']//button")
    private WebElement lnkVoluntaryContributionsInfo;

    @FindBy(xpath = "//div[@help-id='RiskProfile']//button")
    private WebElement lnkRiskProfileInfo;

    @FindBy(xpath = "//div[@help-id='SavingsGoal']//button")
    private WebElement lnkSavingsGoalInfo;

    @FindBy(xpath = "//div[@class='field-message message-info ng-binding']//p")
    private WebElement textEmployementStatusInfo;

    boolean verifyAllIcons = false;

    public KiwiSaverRetirementCalculator(WebDriver driver) {
        Base.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    /**
     * This method calculating the kiwi saver balance according to the given condition
     * @param Scenario
     * @return
     */
    public boolean calculateKiwiSaverBalance(JSONObject Scenario) {

        navigateIframe();
        if (Scenario.get("EmployeeStatus").equals("employed")) {
            sendKeys(txtAge, (String) Scenario.get("Age"));
            click(drpEmployeeStatus);
            driver.findElement(By.xpath(String.format("//div[@ng-model='ctrl.data.EmploymentStatus']//ul//li[@value='%s']", Scenario.get("EmployeeStatus")))).click();
            sendKeys(txtAnnualIncome, (String) Scenario.get("Salary"));
            driver.findElement(By.xpath(String.format("//span[@class='input-holder']//input[@value='%s']", Scenario.get("KiwiSaveContribution")))).click();
            click(drpPIRRate);
            driver.findElement(By.xpath(String.format("//div[@ng-model='ctrl.data.PIRRate']//ul//li[@value='%s']", Scenario.get("PIRRate")))).click();
            driver.findElement(By.xpath(String.format("//span[@class='input-holder']//input[@value='%s']", Scenario.get("RiskProfile")))).click();
        } else {
            sendKeys(txtAge, (String) Scenario.get("Age"));
            click(drpEmployeeStatus);
            driver.findElement(By.xpath(String.format("//div[@ng-model='ctrl.data.EmploymentStatus']//ul//li[@value='%s']", Scenario.get("EmployeeStatus")))).click();
            click(drpPIRRate);
            driver.findElement(By.xpath(String.format("//div[@ng-model='ctrl.data.PIRRate']//ul//li[@value='%s']", Scenario.get("PIRRate")))).click();
            sendKeys(txtKiwiSavaerBalance, (String) Scenario.get("KiwiSavaerBalance"));
            sendKeys(txtVoluntaryContributions, (String) Scenario.get("VoluntaryContributions"));
            click(drpFrequency);
            driver.findElement(By.xpath(String.format("//div[@ng-model='$parent.period']//div[@class='dropdown']//ul//li[@value='%s']", Scenario.get("Frequency")))).click();
            driver.findElement(By.xpath(String.format("//span[@class='input-holder']//input[@value='%s']", Scenario.get("RiskProfile")))).click();
            sendKeys(txtSavingsGoal, (String) Scenario.get("KiwiSavaerBalance"));
        }
        click(btnCalculate);
        return txtValue.getText().replaceAll("[^a-zA-Z0-9 ]", "").equals(Scenario.get("ExpectedResult"));
    }

    /**
     * This method verify all the information icons presence
     * @return
     */
    public boolean verifyAllIconsDisplayed() {

        navigateIframe();
        click(drpEmployeeStatus);
        click(drpEmployeeStatusSelectEmployed);
        boolean ageInfo = verifyAvailability(lnkAgeInfo, "lnkAgeInfo");
        boolean employmentStatusInfo = verifyAvailability(lnkEmploymentStatusInfo, "lnkEmploymentStatusInfo");
        boolean kiwiSaverMemberContributionInfo = verifyAvailability(lnkKiwiSaverMemberContributionInfo, "lnkKiwiSaverMemberContributionInfo");
        boolean annualIncomeInfo = verifyAvailability(lnkAnnualIncomeInfo, "lnkAnnualIncomeInfo");
        boolean pIRRateInfo = verifyAvailability(lnkPIRRateInfo, "lnkPIRRateInfo");
        boolean kiwiSaverBalanceInfo = verifyAvailability(lnkKiwiSaverBalanceInfo, "lnkKiwiSaverBalanceInfo");
        boolean voluntaryContributionsInfo = verifyAvailability(lnkVoluntaryContributionsInfo, "lnkVoluntaryContributionsInfo");
        boolean riskProfileInfo = verifyAvailability(lnkRiskProfileInfo, "lnkRiskProfileInfo");
        boolean savingsGoalInfo = verifyAvailability(lnkSavingsGoalInfo, "lnkSavingsGoalInfo");

        if (ageInfo && employmentStatusInfo && kiwiSaverMemberContributionInfo
                && annualIncomeInfo && pIRRateInfo && kiwiSaverBalanceInfo &&
                voluntaryContributionsInfo && riskProfileInfo && savingsGoalInfo) {
            return verifyAllIcons = true;
        } else {
            logger.info("All the info Icons are not displayed");
            return verifyAllIcons = false;
        }
    }

    /**
     * This method return true if the expected message found
     * @param expectedMessage
     * @return
     */
    public boolean verifyMessage(String expectedMessage) {
        click(lnkEmploymentStatusInfo);
        boolean isMessageDisplayed = false;
        isMessageDisplayed = textEmployementStatusInfo.getText().equals(expectedMessage);
        return isMessageDisplayed;
    }
}
