import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\puzig\\Documents\\JavaAppiumAuto\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
 /*   @Test
    public void firstTest()
    {
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementByXpathAndSendKeys(
                "//*[contains(@text,'Search…')]",
                "Java",
                "Cannot find search input",
                15
        );
        waitForElementPresentByXPath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );

    //       System.out.println("First Test run");
    }*/

    @Test
    public void testCancelSearch()
    {
        waitForElementByIdAndClick(
                "org.wikipedia:id/search_container",
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementByIdAndClick(
                "org.wikipedia:id/search_close_btn",
                "Cannot find X to cancel search",
                5
        );
        waitForElementNotPresent(
                "org.wikipedia:id/search_close_btn",
                "X is still present on the page",
                5
        );
    }

    private WebElement waitForElementPresentByXPath(String xpath, String error_massage, long timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_massage + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXPath(String xpath, String error_massage)
    {
        return waitForElementPresentByXPath(xpath, error_massage, 5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_massage, long timeoutInSecond)
    {
        WebElement element = waitForElementPresentByXPath(xpath, error_massage,timeoutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_massage, long timeoutInSecond)
    {
        WebElement element = waitForElementPresentByXPath(xpath, error_massage,timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementPresentById(String id, String error_massage, long timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_massage + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementByIdAndClick(String id, String error_massage, long timeoutInSecond)
    {
        WebElement element = waitForElementPresentById(id, error_massage, timeoutInSecond);
        element.click();
        return element;
    }

    private boolean waitForElementNotPresent(String id, String error_massage, long timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_massage + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
                );
    }




}
