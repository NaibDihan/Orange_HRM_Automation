package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.EmployeeModel;

import java.util.List;

public class PIMPage {
    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> leftMenuBar;

    @FindBy(className = "oxd-button")
    public List<WebElement> button;

    @FindBy(className = "oxd-input")
    public List<WebElement> addEmployeeField;



    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void createEmployee(EmployeeModel model){
        addEmployeeField.get(1).sendKeys(model.getFirstname());
        addEmployeeField.get(3).sendKeys(model.getLastname());
        addEmployeeField.get(4).sendKeys(model.getID());
        addEmployeeField.get(5).sendKeys(model.getUsername());
        addEmployeeField.get(6).sendKeys(model.getPassword());
        addEmployeeField.get(7).sendKeys(model.getPassword());

    }

}
