package testrunners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.SetUp;

public class LoginTestRunner extends SetUp {
    LoginPage loginPage;
    @Test (priority = 1, description = "Admin cannot login with invalid creds")
    public void doLoginWrongCreds(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("WrongUser","Wrongpass");
        WebElement alertMsgElem = driver.findElement(By.className("oxd-alert-content-text"));
        String alertMsg = alertMsgElem.getText();
        String alertMsgExpected = "Invalid credentials";
        Assert.assertEquals(alertMsg,alertMsgExpected);
    }
    @Test (priority = 2, description = "Admin can login with valid creds")
    public void doLogin(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
        Assert.assertTrue(loginPage.btnUserDropdown.isDisplayed());
    }
    @Test (priority = 3, description = "logout from system")
    public void doLogout(){
        loginPage.doLogout();
        Assert.assertEquals(loginPage.loginTitle.getText(),"Login");
    }

}
