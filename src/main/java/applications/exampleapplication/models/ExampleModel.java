package applications.exampleapplication.models;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

public class ExampleModel {
    public String Name;
    public String EmailAddress;

    public ExampleModel(String name, String email)
    {
        this.Name = name;
        this.EmailAddress =email;
    }

    public ExampleModel()
    {

    }

    public static ExampleModel getExampleModel() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\exampleData.json"), ExampleModel.class);
    }
}
