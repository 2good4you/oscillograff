package VITuaOP;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class TestRunner {

        WebDriver driver = new FirefoxDriver();

        SearchingPage search = PageFactory.initElements(driver, SearchingPage.class);
        Result result = PageFactory.initElements(driver, Result.class);

        @Test
        public void test() {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                driver.get("https://www.google.com.ua");
                search.searchWord("осциллограф");
                search.searchSite("vit.ua");
                result.pageScreenAndPrint();
                driver.quit();

        }
}
