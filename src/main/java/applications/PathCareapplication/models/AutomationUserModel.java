package applications.PathCareapplication.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class AutomationUserModel {
    public String username;
    public String Description;
    public String password;

    public AutomationUserModel(String name, String password)
    {
        this.username = name;
        this.password = password;
    }

    public AutomationUserModel()
    {

    }

    public static AutomationUserModel getExampleModel() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\UserData.json"), AutomationUserModel.class);
    }
}
