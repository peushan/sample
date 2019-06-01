package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.KiwiSaverPage;
import pageobjects.KiwiSaverRetirementCalculator;
import pageobjects.WestPacHomePage;

public class WestpacTest extends BaseTest {


    @Test(description = "Test User Story 1", priority = 1)
    public void useCase1_Scenario1() {
        sAssert = new SoftAssert();
        WestPacHomePage westPacHomePage = new WestPacHomePage(driver);
        KiwiSaverPage kiwiSaverPage = westPacHomePage.navigateKiwisaver();
        KiwiSaverRetirementCalculator kiwiSaverRetirementCalculator = kiwiSaverPage.navigatetoRetirementCal();
        sAssert.assertTrue(kiwiSaverRetirementCalculator.verifyAllIconsDisplayed(),"All the Info Icons are not displayed");
        sAssert.assertTrue(kiwiSaverRetirementCalculator.verifyMessage(TestDataScenarios.US1_Scenario1()),"Expected Info Message is not displayed");
        sAssert.assertAll();
    }

    @Test(description = "Test User Story 2", priority = 2)
    public void useCase2_Scenario1() {
        sAssert = new SoftAssert();
        WestPacHomePage westPacHomePage = new WestPacHomePage(driver);
        KiwiSaverPage kiwiSaverPage = westPacHomePage.navigateKiwisaver();
        KiwiSaverRetirementCalculator kiwiSaverRetirementCalculator = kiwiSaverPage.navigatetoRetirementCal();
        sAssert.assertTrue(kiwiSaverRetirementCalculator.calculateKiwiSaverBalance(TestDataScenarios.US2_Scenario1()));
        sAssert.assertAll();
    }

    @Test(description = "Test User Story 2", priority = 3)
    public void useCase2_Scenario2() {
        sAssert = new SoftAssert();
        WestPacHomePage westPacHomePage = new WestPacHomePage(driver);
        KiwiSaverPage kiwiSaverPage = westPacHomePage.navigateKiwisaver();
        KiwiSaverRetirementCalculator kiwiSaverRetirementCalculator = kiwiSaverPage.navigatetoRetirementCal();
        sAssert.assertTrue(kiwiSaverRetirementCalculator.calculateKiwiSaverBalance(TestDataScenarios.US2_Scenario2()));
        sAssert.assertAll();
    }

    @Test(description = "Test User Story 2", priority = 4)
    public void useCase2_Scenario3() {
        sAssert = new SoftAssert();
        WestPacHomePage westPacHomePage = new WestPacHomePage(driver);
        KiwiSaverPage kiwiSaverPage = westPacHomePage.navigateKiwisaver();
        KiwiSaverRetirementCalculator kiwiSaverRetirementCalculator = kiwiSaverPage.navigatetoRetirementCal();
        sAssert.assertTrue(kiwiSaverRetirementCalculator.calculateKiwiSaverBalance(TestDataScenarios.US2_Scenario3()));
        sAssert.assertAll();
    }

}
