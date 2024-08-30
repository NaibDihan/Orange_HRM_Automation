package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import setup.EmployeeModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void saveUsers(EmployeeModel model) throws IOException, ParseException {
        String filelocation = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray empArray = (JSONArray) parser.parse(new FileReader(filelocation));
        JSONObject empObj = new JSONObject();
        empObj.put("firstname",model.getFirstname());
        empObj.put("lastname",model.getLastname());
        empObj.put("username",model.getUsername());
        empObj.put("password",model.getPassword());
        empObj.put("ID",model.getID());

        empArray.add(empObj);

        FileWriter writer = new FileWriter(filelocation);
        writer.write(empArray.toJSONString());
        writer.flush();
        writer.close();

    }

    public static JSONArray getUser() throws IOException, ParseException {
        String filelocation = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray empArray = (JSONArray) parser.parse(new FileReader(filelocation));
        return empArray;

    }

    public static void scroll(WebDriver driver, int height){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")");
        //System.out.println("scroll");
    }
}
