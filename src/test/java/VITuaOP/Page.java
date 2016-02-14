//package VITuaOP;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.FindBy;
//
//import java.util.Random;
//
//
//public class Page {
//    WebDriver driver = new FirefoxDriver();
//
//    screenShot screen = new screenShot();
//    Random rand = new Random();
//    private String screenName = Integer.toString(rand.nextInt(10000000)) + ".png";
//    String screenPath = "C:\\Selenium\\Screenshots\\vitUa\\" + screenName;
//
//    @FindBy(id = "lst-ib") WebElement searchTable;
//    @FindBy (name = "btnG") WebElement search;
//    @FindBy (xpath = "//*[@id='resultStats']") WebElement resultTable;
//    @FindBy (xpath = "//*[@id='rso']") WebElement allText;
//    @FindBy (xpath = "//a[@id='pnnext']/span[2]") WebElement nextButton;
//    @FindBy (id = "resultStats") WebElement canNext;
//    @FindBy(xpath = ".//*[@id='nav']/tbody/tr/td[@class='cur']") WebElement pageNumber;
//
//}
