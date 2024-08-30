package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import setup.SetUp;

import java.io.IOException;

import static utils.Utils.getUser;
import static utils.Utils.scroll;

public class UserLoginTestRunner extends SetUp {
    LoginPage loginPage;
    PIMPage pimpage;
    @Test(priority = 1 , description = "User Employee can't login with invalid creds")
    public void doLoginWithInvalidCreds() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("nonexistent","nonexistent");
        WebElement alertMsgElem = driver.findElement(By.className("oxd-alert-content-text"));
        String alertMsg = alertMsgElem.getText();
        String alertMsgExpected = "Invalid credentials";
        Assert.assertEquals(alertMsg,alertMsgExpected);
    }

    @Test(priority = 2, description = "Successfull user Employee Login")
    public void doLogin() throws IOException, ParseException {
        JSONArray empArray=  getUser();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String username = empObj.get("username").toString();
        String password = empObj.get("password").toString();
        loginPage = new LoginPage(driver);
        loginPage.doLogin(username,password);
        Assert.assertTrue(loginPage.btnUserDropdown.isDisplayed());
    }
    @Test(priority = 3, description = "Verify Full Name next to profile icon")
    public void showFullName() throws IOException, ParseException {
        JSONArray empArray=  getUser();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String firstname = empObj.get("firstname").toString();
        String lastname = empObj.get("lastname").toString();
        String fullName = driver.findElement(By.className("oxd-userdropdown-name")).getText();
        String fullNameExpected = firstname +" " + lastname;
        //System.out.println(fullNameExpected);
        Assert.assertTrue(fullName.contains(fullNameExpected));
    }

    //@Test(priority=5 , description = "Update Personal Info")
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
    @Test(priority = 4)
    public void doLogout() throws InterruptedException {
        // Thread.sleep(3000);
        loginPage.doLogout();
        Assert.assertEquals(loginPage.loginTitle.getText(),"Login");
    }
}
