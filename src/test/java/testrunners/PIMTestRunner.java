package testrunners;

import com.beust.ah.A;
import com.github.javafaker.Faker;
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
import setup.EmployeeModel;
import setup.SetUp;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static utils.Utils.getUser;

public class PIMTestRunner extends SetUp {
    LoginPage loginPage;
    PIMPage pimpage;
    @BeforeTest
    public void doLogin(){
        loginPage =new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
    }
    @Test(priority = 1, description = "Employee can't be created without filling the required fields")
    public void createEmployeeWithoutRequiredField() throws IOException, ParseException {
        pimpage = new PIMPage(driver);
        pimpage.leftMenuBar.get(1).click();
        pimpage.button.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();
        pimpage.button.get(1).click();

        String requiredFieldError = driver.findElements(By.className("oxd-input-field-error-message")).get(0).getText();
        Assert.assertTrue(requiredFieldError.contains("Required"));
    }

    @Test(priority = 2, description = "Successful Employee creation")
    public void createEmployee() throws IOException, ParseException {
        pimpage = new PIMPage(driver);
        //pimpage.leftMenuBar.get(1).click();
        //pimpage.button.get(2).click();
        //driver.findElement(By.className("oxd-switch-input")).click();
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String username = faker.name().username();
        int ID = (int)(Math.random() * 9000) + 1000; //4 digit randomly generated
        String IDString = Integer.toString(ID);
        //String password = faker.regexify("[a-zA-Z0-9!@#$%^&*()_+]{"+ 12 +"}"); //random password
        // also tried fake.internet().password(length,length,true,true,true) but didn't work
        String digits = faker.numerify("##########").substring(0, 12 / 4); // Generate random digits
        String specialChars = faker.bothify("!@#$%^&*()_+????").substring(0, 12 / 4); // Generate random special characters
        String password = firstname +digits+specialChars;
        //System.out.println(password);

        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstname);
        model.setLastname(lastname);
        model.setUsername(username);
        model.setPassword(password);
        model.setID(IDString);

        pimpage.addEmployeeField.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);

        pimpage.createEmployee(model);

        pimpage.button.get(1).click();

        WebElement txtTitleElem = driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); //Explicit wait
        wait.until(ExpectedConditions.visibilityOf(txtTitleElem));
        String txtTitle = txtTitleElem.getText();
        Assert.assertTrue(txtTitle.contains("Personal Details"));
        Utils.saveUsers(model);

    }

    @Test(priority = 3, description = "Search Employee by a wrong ID and it will appear as no record found")
    public void searchEmployeeByWrongID() throws IOException, ParseException, InterruptedException {
        pimpage.leftMenuBar.get(1).click();
        pimpage.addEmployeeField.get(1).sendKeys("123456");
        pimpage.button.get(1).click();

        Thread.sleep(2000);
        WebElement recordMsgElem = driver.findElements(By.className("oxd-text--span")).get(12);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); //Explicit wait
        wait.until(ExpectedConditions.visibilityOf(recordMsgElem));
        String recordMsg = recordMsgElem.getText();

        Assert.assertTrue(recordMsg.contains("No Records Found"));
        System.out.println(recordMsg);

    }

    @Test(priority = 4, description = "Search Employee by a valid ID and it will appear as record found")
    public void searchEmployeeByID() throws IOException, ParseException, InterruptedException {
        JSONArray empArray=  getUser();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size()-1);
        String ID = empObj.get("ID").toString();
        pimpage.leftMenuBar.get(1).click();
        //pimpage.addEmployeeField.get(1).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);

        pimpage.addEmployeeField.get(1).sendKeys(ID);
        pimpage.button.get(1).click();

        Thread.sleep(2000);
        WebElement recordMsgElem = driver.findElements(By.className("oxd-text--span")).get(12);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); //Explicit wait
        wait.until(ExpectedConditions.visibilityOf(recordMsgElem));
        String recordMsg = recordMsgElem.getText();

        Assert.assertTrue(recordMsg.contains("Record Found"));
        System.out.println(recordMsg);

    }


}
