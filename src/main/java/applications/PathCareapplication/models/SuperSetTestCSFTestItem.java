package applications.PathCareapplication.models;

import java.io.IOException;
import java.util.HashMap;


public class SuperSetTestCSFTestItem {

   public HashMap<String, String> value = new HashMap<>();


    public SuperSetTestCSFTestItem() throws IOException {
        value.put("CSF Protein","4.63");
        value.put("CSF Chloride","118");
        value.put("CSF Glucose","0.60");

    }
}
