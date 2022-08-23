package applications.PathCareapplication.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class TestSetResults {
    public String testresult;
    public String testresult2;

    public TestSetResults(String testresult, String testresult2)
    {
        this.testresult = testresult;
        this.testresult2 = testresult2;
    }

    public TestSetResults()
    {

    }

    public static TestSetResults getExampleModel(String name) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+name+".json"), TestSetResults.class);
    }
}
