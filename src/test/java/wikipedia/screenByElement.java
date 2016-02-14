package wikipedia;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import vitUa.screenShot;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class screenByElement {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private screenShot screen = new screenShot();
    private WebElement element;
    private Random rand = new Random();
    private String screenName = Integer.toString(rand.nextInt(10000000)) + ".png";
    private String screenPath = "C:\\Selenium\\Screenshots\\wiki\\" + screenName;



    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://en.wikipedia.org/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test() {
        driver.get(baseUrl + "wiki/Main_Page");

        element = driver.findElement(By.cssSelector("#mp-tfp>table>tbody>tr>td"));

        try {
            screenShot.captureElementBitmap(driver, element, screenPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void close() {
        driver.close();
    }


}
