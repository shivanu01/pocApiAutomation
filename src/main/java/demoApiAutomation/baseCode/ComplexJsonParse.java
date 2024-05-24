package demoApiAutomation.baseCode;

import demoApiAutomation.files.ReusableMethod;
import io.restassured.path.json.JsonPath;
import demoApiAutomation.files.Payload;

public class ComplexJsonParse {
    public static void main(String args[])
    {
        JsonPath js = ReusableMethod.rawToJson(Payload.coursePrice());


        //getting total courses
        int courseSize=js.getInt("courses.size()");
        System.out.println(courseSize);

        //print purchase amount
        System.out.println(js.getInt("dashboard.purchaseAmount"));

        //Print title of the first course
        System.out.println(js.getString("courses[0].title"));

        for(int i=0;i<courseSize;i++)
        {
            System.out.println("The Course title is ="+js.getString("courses["+i+"].title"));
            System.out.println("the course price is ="+js.get("courses["+i+"].price").toString());
        }

        //getting number of copies bases on title

        for(int i=0;i<courseSize;i++)
        {
            if(js.getString("courses["+i+"].title").equals("RPA"))
            {
                System.out.println(js.get("courses["+i+"].copies").toString());
                break;
            }
        }

        int totalCalculatedSum=0;
        int totalSum=js.getInt("dashboard.purchaseAmount");

        for(int i=0;i<courseSize;i++)
        {
           totalCalculatedSum+=js.getInt("courses["+i+"].copies")*(js.getInt("courses["+i+"].price"));
        }

        if(totalCalculatedSum==totalSum)
        {
            System.out.println("The amount is same"+totalCalculatedSum);
        }
        else
        {
            System.out.println("The amount is different");
        }



    }
}
