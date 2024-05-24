package demoApiAutomation.baseCode;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import demoApiAutomation.files.Payload;
import demoApiAutomation.files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class Basics
{
    public static void main(String[] args)
    {



        RestAssured.baseURI="https://rahulshettyacademy.com";

        //Posting the place

        String response=
                given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                        .body(Payload.addPlace())
                        .when().post("maps/api/place/add/json")
                        .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                        .header("Server","Apache/2.4.52 (Ubuntu)").extract().asString();

        JsonPath js= ReusableMethod.rawToJson(response);
        String place_id=js.getString("place_id");
        System.out.println(place_id);

        //Updating a place
        String newAddress="new colony Dehradun1";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(Payload.updatePlace(place_id, newAddress))
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


        //Getting the place

        String validatingResponse=
                given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_id)
                        .when().get("maps/api/place/get/json")
                        .then().log().all().assertThat().statusCode(200).extract().asString();

        JsonPath js1= ReusableMethod.rawToJson(validatingResponse);
        String actualAddress=js1.getString("address");
        //System.out.println("validatinng resonse"+validatingResponse);

        Assert.assertEquals(actualAddress, "hi");


    }


}
