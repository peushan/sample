package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class BasePage extends Base {

    private Logger logger = Logger.getLogger(BasePage.class);

    /**
     * This is wrapper method to send keys
     * @param element
     * @param text
     */
    protected void sendKeys(WebElement element, String text) {
        try {
            element.click();
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            logger.error("sendKeys(element, " + text + ") failed with error : " + e.getMessage());
        }
    }

    /**
     *  This is a wrapper method to click on the given element
     * @param element
     */

    protected void click(WebElement element) {
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
            } else {
                sleep(1);
                element.click();
            }
        } catch (NoSuchElementException f) {
            logger.error("Element not visible in screen : " + f.getMessage());
        }
    }

    /**
     *  This is method to set hard wait
     * @param seconds
     */
    protected void sleep(double seconds) {
        try {
            logger.info("Sleeping for " + seconds + " seconds...");
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    /**
     *  This method use for navigate to a iframe
     */
    public void navigateIframe() {
        sleep(3);
        WebElement iFrame = driver.findElement(By.xpath("//div[@id='calculator-embed']//iframe"));
        driver.switchTo().frame(iFrame);
    }

    /**
     * This method will return true if the give element is displayed
     *
     * @param element
     * @return boolean
     */
    boolean isDisplayed = false;
    protected boolean verifyAvailability(WebElement element, String elemnetName) {

        if (element.isDisplayed()) {
            logger.info("Element : " + elemnetName + " is Displayed " + element);
            return isDisplayed=true;
        } else {
            logger.info("Element : " + elemnetName + " is not Displayed " + element);
            return isDisplayed=false;
        }
    }
}
