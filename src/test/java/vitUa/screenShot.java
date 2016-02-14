package vitUa;


import com.sun.javafx.geom.Rectangle;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class screenShot {
    public void saveScreen (WebDriver driver, String Path) throws Exception{
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(Path));
    }

    public static void captureElementBitmap(WebDriver driver, WebElement element, String Path) throws Exception {
        // Делаем скриншот страницы
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Создаем экземпляр BufferedImage для работы с изображением
        BufferedImage img = ImageIO.read(screen);
        // Получаем размеры элемента
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
        // Создаем прямоуголник (Rectangle) с размерами элемента
        Rectangle rect = new Rectangle(width, height);
        // Получаем координаты элемента
        Point p = element.getLocation();
        // Вырезаем изображенеи элемента из общего изображения
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
        // Перезаписываем File screen
        ImageIO.write(dest, "png", screen);
        // Возвращаем File c изображением элемента
        FileUtils.copyFile(screen, new File(Path));
    }

}
