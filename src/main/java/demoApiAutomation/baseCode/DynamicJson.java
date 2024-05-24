package demoApiAutomation.baseCode;

import demoApiAutomation.files.Payload;
import demoApiAutomation.files.ReusableMethod;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicJson
{

    /*
    @Test(dataProvider ="booksData" )
    public void addBook(String isbn,String aisle)
    {
        RestAssured.baseURI="http://216.10.245.166";
        String addBookResponse=given().log().all().header("Content-Type","application/json")
                .body(Payload.AddBook(isbn,aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js= ReusableMethod.rawToJson(addBookResponse);
        String id=js.get("ID");
        Assert.assertEquals(js.get("Msg"),"successfully added");
    }*/


    // to convert the file to string-> convert into byte->byte data to string
    @Test
    public void addBookThroughFile() throws IOException {
        RestAssured.baseURI="http://216.10.245.166";
        String addBookResponse=given().log().all().header("Content-Type","application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Shivanshu Bisht\\addBook.json"))))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println("Hello"+addBookResponse);
        JsonPath js= ReusableMethod.rawToJson(addBookResponse);
        Assert.assertEquals(js.get("Msg"),"successfully added");
    }

   /*
   @DataProvider(name="booksData")
    public Object[][] getData()
    {
        return new Object[][]
                {
                        {"abc1223wqs","23231212"},
                        {"tewq121332sa1", "5322334121"},
                        {"swqs21sass","109132098"}
                };
    }

    */
}
