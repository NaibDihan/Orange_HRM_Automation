package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name="username")
    WebElement txtUsername;
    @FindBy(name="password")
    WebElement txtPassword;
    @FindBy(css="[type=submit]")
    WebElement btnSubmit;
    @FindBy(className="oxd-userdropdown-img")
    public WebElement btnUserDropdown;
    @FindBy(className= "oxd-userdropdown-link")
    List<WebElement> btnLogout;
    @FindBy(className="orangehrm-login-title")
    public WebElement loginTitle;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doLogin(String username, String password){
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnSubmit.click();
    }
    public void doLogout(){
        btnUserDropdown.click();
        btnLogout.get(3).click();
    }
}
