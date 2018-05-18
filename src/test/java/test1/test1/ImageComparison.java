package test1.test1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import junit.framework.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ImageComparison {

  public static void main(String [] args) throws InterruptedException, IOException{
   //System.setProperty("webdriver.chrome.driver",path of executable file "Chromedriver.exe")
         System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
         //WebDriver driver = new ChromeDriver();
         WebElement webElement = null;
         //Setting Browser related configurations
         ChromeOptions options = new ChromeOptions();
     	 options.addArguments("--disable-web-security");
     	 options.addArguments("--no-proxy-server");
     	 options.addArguments("--disable-extensions");
         options.addArguments("--disable-notifications");
         options.addArguments("disable-popup-blocking");
         options.addArguments("--ignore-certificate-errors");
                 WebDriver  driver = new ChromeDriver(options);
         //Open the QUARC QA Environment
         driver.get("https://qa.medarchon.com");
                  
         Thread.sleep(10000);
         //Enter the Username
         driver.findElement(By.id("userName")).sendKeys("EMcfad");
         //Enter the Password
         driver.findElement(By.id("password")).sendKeys("Qa4MED@");
         //Click on OK Button
         driver.findElement(By.name("btn_submit")).click();
         Thread.sleep(10000);
         webElement = driver.findElement(By.xpath("//*[@class='quarc-menu-header']"));
         Thread.sleep(5000);
         Screenshot screenshot =new AShot().takeScreenshot(driver, webElement);
        // ImageIO.write(screenshot.getImage(), "PNG" , new  File (System.getProperty("user.dir") +"/Images/LogoImage.png"));
         BufferedImage actualImage  = screenshot.getImage();
         BufferedImage expectedImage = ImageIO.read(new File((System.getProperty("user.dir") +"/Images/expected.png")));
         //Use ImageDiffer Class
         ImageDiffer imgDiff = new ImageDiffer();
         ImageDiff  diff = imgDiff.makeDiff(expectedImage, actualImage);
                  
         if(diff.hasDiff())
         {
        	 Assert.assertFalse("Images are not same", diff.hasDiff());
             
         }
         else 
         {
        	 Assert.assertTrue("Images are same", diff.hasDiff());
         }
         

         //driver.close();
           }
}