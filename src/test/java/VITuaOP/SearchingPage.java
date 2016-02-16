package VITuaOP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SearchingPage{
    private final Logger logger= LogManager.getLogger(SearchingPage.class);

    WebDriver driver;

    public SearchingPage(WebDriver driver) {
       this.driver = driver;
    }

    private @FindBy (id = "lst-ib") WebElement searchTable;
    private @FindBy (name = "btnG") WebElement search;
    private @FindBy (xpath = "//*[@id='resultStats']") WebElement resultTable;
    private @FindBy (xpath = "//*[@id='rso']") WebElement allText;
    private @FindBy(xpath = "//a[@id='pnnext']/span[2]") WebElement nextButton;
    private @FindBy (id = "resultStats") WebElement canNext;

    public void searchWord(String text) {
        logger.info("Enter the text in search bar");
        searchTable.clear();
        searchTable.sendKeys(text);
        search.click();

        for (int second = 0;; second++) {
            if (second >= 10) break;

            try { if (driver.findElement(By.xpath("//*[@id='resultStats']")).getText().matches("^[\\s\\S]*Результатов: [\\s\\S]*$"))
                sleep(1000);
                break;
            } catch (Exception e) {
                logger.error("strange error with searching request");
            }
        }
    }

    public void searchSite(String text) {
        logger.info("starts for searcing vit.ua");
        for (int i = 1; ;) {
            try {
                assertTrue(allText.getText().matches("[\\s\\S]*" + text + "[\\s\\S]*"));
                logger.info("vit.ua has founds");
                String site = driver.findElement((By.xpath(".//*[@id='rso']//cite[contains(text(),'" + text + "')]"))).getText();
//                System.out.println(site);
//                driver.get(site);
//                System.out.println("the Google page is " + i);
                break;
            } catch (Error e) {
                logger.info("Error was catching in searching site");
            } catch (Exception e) {
                logger.info("Exeption was catching in searching site");
            }

            try {
                logger.info("locking for <next button>");
                assertTrue(nextButton.getText().matches("\\bСледующая\\b"));
                logger.info("<next button> was founded");
                nextButton.click();
                i ++;
                logger.info("waiting for page loading after switching");
                for (int second = 0; ; second++) {
                    if (second >= 60) fail("timeout");
                    try {
                        sleep(1000);
                        if (canNext.getText().matches("^[\\s\\S]*страница " + i + "[\\s\\S]*$")) break;
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                logger.info("vit.ua was not founded");
                System.out.println("Sorry, vit.ua wasn't founded at page" + (i-1));
                break;
            }
        }
    }
}
