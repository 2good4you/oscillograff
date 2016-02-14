package vitUa;

import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class vitUa {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();
        private screenShot screen = new screenShot();
        private Random rand = new Random();
        private String screenName = Integer.toString(rand.nextInt(10000000)) + ".png";
        private String screenPath = "C:\\Selenium\\Screenshots\\vitUa\\" + screenName;



        @Before
        public void setUp() {
                driver = new FirefoxDriver();
                baseUrl = "http://google.com/";
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }

        @Test
        public void test() {
                driver.get(baseUrl + "/?gfe_rd=cr&ei=1SmpVs7iIdbFsAGe3I-QDA&gws_rd=ssl");
                driver.findElement(By.id("lst-ib")).clear();
                driver.findElement(By.id("lst-ib")).sendKeys("осциллограф");
                driver.findElement(By.name("btnG")).click();

                try {
                        assertTrue(driver.getCurrentUrl().equals((baseUrl + "/?gfe_rd=cr&ei=1SmpVs7iIdbFsAGe3I-QDA&gws_rd=ssl")));
                } catch (AssertionFailedError e) {}

                for (int second = 0;; second++) {
                if (second >= 60) fail("timeout");
                try { if (driver.findElement(By.xpath("//*[@id='resultStats']")).getText().matches("^[\\s\\S]*Результатов: [\\s\\S]*$")) break; } catch (Exception e) {}
                }

                for (int i = 1; ;) {
                        try {
                                assertTrue(driver.findElement(By.xpath("//*[@id='rso']")).getText().matches("[\\s\\S]*vit\\.ua[\\s\\S]*"));
                                String site = driver.findElement((By.xpath(".//*[@id='rso']/div/div//div/cite[contains(text(),'vit')]"))).getText();
                                System.out.println(site);
                                driver.get(site);
                                screen.saveScreen(driver, screenPath);
                                break;
                        } catch (Error e) {
                                verificationErrors.append(e.toString());
                        } catch (Exception e) {
                                e.printStackTrace();
                        }

                        try {
                                assertTrue (driver.findElement(By.xpath("//a[@id='pnnext']/span[2]")).getText().matches("\\bСледующая\\b"));
                                driver.findElement(By.cssSelector("#pnnext>span")).click();
                                i ++;
                                for (int second = 0; ; second++) {
                                        if (second >= 60) fail("timeout");
                                        try {
                                                sleep(1000);
                                                if (driver.findElement(By.id("resultStats")).getText().matches("^[\\s\\S]*страница " + i + "[\\s\\S]*$")) break;
                                        } catch (Exception e) {
                                                e.printStackTrace();

                                        }
                                }
                        } catch (Exception e) {
                                System.out.println("Sorry, vit.ua doesn't found");
                                break;
                        }


                }
        }

        @After
        public void tearDown() throws Exception {
                driver.quit();
        }
}
