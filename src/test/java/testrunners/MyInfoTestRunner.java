package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import setup.SetUp;

import java.io.IOException;

import static utils.Utils.getUser;
import static utils.Utils.scroll;

public class MyInfoTestRunner extends SetUp {
    LoginPage loginPage;
    PIMPage pimpage;
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        JSONArray empArray=  getUser();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String username = empObj.get("username").toString();
        String password = empObj.get("password").toString();
        loginPage = new LoginPage(driver);
        loginPage.doLogin(username,password);
        Assert.assertTrue(loginPage.btnUserDropdown.isDisplayed());
    }
    @Test(priority=1 , description = "Update Personal Info")
    public void myInfo() throws InterruptedException {
        pimpage = new PIMPage(driver);
        pimpage.leftMenuBar.get(2).click();
        Thread.sleep(3000);
        driver.findElements(By.className("oxd-radio-input")).get(0).click();
        pimpage.button.get(0).click();
        scroll(driver,900);
        //Thread.sleep(2000);
        driver.findElements(By.className("oxd-select-text--arrow")).get(2).click();
        driver.findElements(By.className("oxd-select-text-input")).get(2).sendKeys("o",Keys.ENTER);
        //driver.findElements(By.className("oxd-select-text-input")).get(2).sendKeys(Keys.ENTER);
        pimpage.button.get(1).click();

    }
    @Test(priority = 2)
    public void doLogout() throws InterruptedException {
        // Thread.sleep(3000);
        loginPage.doLogout();
        Assert.assertEquals(loginPage.loginTitle.getText(),"Login");
    }
}
