package demoApiAutomation.JiraAutomation;

import demoApiAutomation.files.JiraPayload;
import demoApiAutomation.files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class JiraRequest
{
    public SessionFilter session;
    public String addIssueResponse;
    public String sessionResponse;


    @BeforeClass
    public void createSession()
    {
        session=new SessionFilter();
        RestAssured.baseURI="http://localhost:8080";
        sessionResponse=given().log().all().header("Content-Type","application/json")
                .body(JiraPayload.userDetails()).log().all().filter(session)
                .when().post("/rest/auth/1/session")
                .then().log().all().extract().response().asString();
    }

    @Test(priority = 1)
    public void addIssue()
    {


         addIssueResponse=given().log().all().header("Content-Type","application/json")
                .body(JiraPayload.addIssuePayload())
                .log().all().filter(session)
                .when().post("/rest/api/2/issue")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();

    }

    @Test(priority = 2)
    public void addComment()
    {
        JsonPath js= ReusableMethod.rawToJson(addIssueResponse);

        int id=js.getInt("id");
        //System.out.println("The id is "+id);
        given().pathParam("key",id).log().all().header("Content-Type","application/json")
                .body(JiraPayload.addComment())
                .filter(session)
                .when().post("/rest/api/2/issue/{key}/comment")
                .then().log().all().assertThat().statusCode(201);
    }
}