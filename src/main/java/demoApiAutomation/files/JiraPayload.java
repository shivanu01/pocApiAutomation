package demoApiAutomation.files;

public class JiraPayload
{
    public static String addIssuePayload()
    {
        return "{\n" +
                "    \"fields\":{\n" +
                "        \"project\":\n" +
                "        {\n" +
                "            \"key\":\"POC\"\n" +
                "        },\n" +
                "        \"summary\": \"comment jira  api\",\n" +
                "        \"description\": \"Vreatinh an issue to do poc automation of api\",\n" +
                "        \"issuetype\":{\n" +
                "            \"name\":\"Bug\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }
    public static String userDetails()
    {
        return "{ \"username\": \"shivanshub01\", \"password\": \"Bisht@123\" }";

    }
    public static String addComment()
    {
        return "{\n" +
                "    \"body\": \" checking the comment\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}";
    }
}
