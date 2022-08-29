package applications.PathCareapplication.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class AutomationUserModel {
    public String username;
    public String description;
    public String location;

    public String accessProfile;
    public String password;

    public AutomationUserModel(String name, String password, String location)
    {
        this.username = name;
        this.location = location;
        this.password = password;
    }

    public AutomationUserModel()
    {

    }

    public static AutomationUserModel getExampleModel(String name) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+name+".json"), AutomationUserModel.class);
    }
}
