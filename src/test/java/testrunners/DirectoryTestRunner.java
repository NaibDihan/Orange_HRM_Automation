package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import setup.SetUp;

import java.io.IOException;
import java.time.Duration;

import static utils.Utils.getUser;

public class DirectoryTestRunner extends SetUp {
    LoginPage loginPage;
    PIMPage pimpage;
    @BeforeTest
    public void doLogin(){
        loginPage =new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
    }

    @Test(priority = 1, description = "Search Employee By non-existent full name")
    public void searchEmployeeByWrongName() throws IOException, ParseException, InterruptedException {
        pimpage = new PIMPage(driver);
        pimpage.leftMenuBar.get(8).click();

        driver.findElement(By.xpath("//input[@placeholder=\"Type for hints...\"]")).sendKeys("Non existent");

        pimpage.button.get(1).click();

        WebElement errorMsgElem = driver.findElement(By.className("oxd-input-field-error-message"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); //Explicit wait
        wait.until(ExpectedConditions.visibilityOf(errorMsgElem));
        String errorMsg = errorMsgElem.getText();

        Assert.assertTrue(errorMsg.contains("Invalid"));
        System.out.println(errorMsg);

    }

    @Test(priority = 2, description = "Search Employee using the full name")
    public void searchEmployeeByName() throws IOException, ParseException, InterruptedException {
        pimpage = new PIMPage(driver);
        JSONArray empArray=  getUser();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String firstname = empObj.get("firstname").toString();
        pimpage.leftMenuBar.get(8).click();

        driver.findElement(By.xpath("//input[@placeholder=\"Type for hints...\"]")).sendKeys(firstname);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@placeholder=\"Type for hints...\"]")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
        Thread.sleep(2000);
        pimpage.button.get(1).click();

        Thread.sleep(2000);
        WebElement recordMsgElem = driver.findElements(By.className("oxd-text--span")).get(12);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); //Explicit wait
        wait.until(ExpectedConditions.visibilityOf(recordMsgElem));
        String recordMsg = recordMsgElem.getText();

        Assert.assertTrue(recordMsg.contains("Record Found"));
        System.out.println(recordMsg);

    }
    @Test(priority = 3)
    public void doLogout() throws InterruptedException {
        // Thread.sleep(3000);
        loginPage.doLogout();
        Assert.assertEquals(loginPage.loginTitle.getText(),"Login");
    }

}
