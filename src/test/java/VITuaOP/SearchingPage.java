package VITuaOP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SearchingPage{

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
        searchTable.clear();
        searchTable.sendKeys(text);
        search.click();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (resultTable.getText().matches("^[\\s\\S]*Результатов: [\\s\\S]*$")) break; } catch (Exception e) {}
        }
    }

    public void searchSite(String text) {
        for (int i = 1; ;) {
            try {
                assertTrue(allText.getText().matches("[\\s\\S]*" + text + "[\\s\\S]*"));
//                String site = driver.findElement((By.xpath(".//*[@id='rso']/div/div//div/cite[contains(text(),'"+ text +"')]"))).getText();
//                System.out.println(site);
//                driver.get(site);
//                System.out.println("the Google page is " + i);
                break;
            } catch (Error e) {

            } catch (Exception e) {}

            try {
                assertTrue(nextButton.getText().matches("\\bСледующая\\b"));
                nextButton.click();
                i ++;
                for (int second = 0; ; second++) {
                    if (second >= 60) fail("timeout");
                    try {
                        sleep(1000);
                        if (canNext.getText().matches("^[\\s\\S]*страница " + i + "[\\s\\S]*$")) break;
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
            } catch (Exception e) {
                break;
            }
        }
    }


}
