package co.verisoft;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();// -ב השימוש את להשבית GPU
        options.addArguments("--window-size=1920,1080");// חלון גודל
// יצירת WebDriverעם האפשרויות שהוגדרו
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        System.out.println(driver.getTitle());
        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        WebElement button =  driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        Thread.sleep(1000);
        button.click();
        Thread.sleep(1000);
        WebElement titleNextPage = driver.findElement(By.xpath("//span[@class='title']"));
        if(titleNextPage.getText().equals("Products")){
            System.out.println("The test is OK");
        }
        driver.quit();
    }
}
