package VITuaOP;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class Result {
    WebDriver driver;
    public Result(WebDriver driver ) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@id='pnnext']/span[2]") WebElement nextButton;
    @FindBy(xpath = ".//*[@id='nav']/tbody/tr/td[@class='cur']") WebElement pageNumber;


    screenShot screen = new screenShot();
    Random rand = new Random();
    private String screenName = Integer.toString(rand.nextInt(10000000)) + ".png";
    String screenPath = "C:\\Selenium\\Screenshots\\vitUa\\" + screenName;


    public void pageScreenAndPrint() {
        try {
//            assertTrue(nextButton.getText().matches("\\bСледующая\\b"));
            System.out.println("the Google page is " + pageNumber.getText());
            screen.saveScreen(driver, screenPath);
        } catch (Exception e) {
        }
    }


}
