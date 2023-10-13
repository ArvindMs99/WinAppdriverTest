package tests;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CalculatorAppTest {

    WindowsDriver driver;
    String path = "C://Program Files (x86)//Windows Application Driver//WinAppDriver.exe";
    Process process;

   @BeforeClass
   public void setup() throws IOException {
       ProcessBuilder builder = new ProcessBuilder(path).inheritIO();
       process = builder.start();
       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("app","Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
       driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"),capabilities);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   }

   @AfterClass
   public void teardown(){
        driver.quit();
        process.destroy();
   }

   @AfterMethod
   public void reset() throws InterruptedException {
       driver.findElementByName("Clear").click();
        Thread.sleep(3000);
   }

    @Test(priority = 1)
    public void AdditionTest(){
        driver.findElementByName("One").click();
        driver.findElementByName("Plus").click();
        driver.findElementByName("Two").click();
        driver.findElementByName("Equals").click();
        String value = driver.findElementByName("Display is 3").getAttribute("Name");
        String actual_value = value.substring(11);
        Assert.assertEquals(actual_value,"3");
        Logger.getLogger("INFO").info("ADDITION TEST PASSED");
    }

    @Test(priority = 2)
    public void SubtractionTest(){
        driver.findElementByName("Two").click();
        driver.findElementByName("Minus").click();
        driver.findElementByName("One").click();
        driver.findElementByName("Equals").click();
        String value = driver.findElementByName("Display is 1").getAttribute("Name");
        String actual_value = value.substring(11);
        Assert.assertEquals(actual_value,"1");
        Logger.getLogger("INFO").info("SUBTRACTION TEST PASSED");
    }

    @Test(priority = 3)
    public void MultiplicationTest(){
        driver.findElementByName("Three").click();
        driver.findElementByName("Multiply by").click();
        driver.findElementByName("Two").click();
        driver.findElementByName("Equals").click();
        String value = driver.findElementByName("Display is 6").getAttribute("Name");
        String actual_value = value.substring(11);
        Assert.assertEquals(actual_value,"6");
        Logger.getLogger("INFO").info("MULTIPLICATION TEST PASSED");
    }

    @Test(priority = 4)
    public void DivisionTest(){
        driver.findElementByName("Six").click();
        driver.findElementByName("Divide by").click();
        driver.findElementByName("Two").click();
        driver.findElementByName("Equals").click();
        String value = driver.findElementByName("Display is 3").getAttribute("Name");
        String actual_value = value.substring(11);
        Assert.assertEquals(actual_value,"3");
        Logger.getLogger("INFO").info("DIVISION TEST PASSED");
    }

    @Test(priority = 4)
    public void SendKeysTest(){
        driver.findElementByName("Display is 0").sendKeys("4-1");
        driver.findElementByName("Equals").click();
        String value = driver.findElementByName("Display is 3").getAttribute("Name");
        String actual_value = value.substring(11);
        Assert.assertEquals(actual_value,"3");
        Logger.getLogger("INFO").info("SENDKEYS TEST PASSED");
    }
}
