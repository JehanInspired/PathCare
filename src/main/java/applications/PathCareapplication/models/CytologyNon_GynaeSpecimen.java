package applications.PathCareapplication.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CytologyNon_GynaeSpecimen {

   public HashMap<String, List<String>> value = new HashMap<>();

    public CytologyNon_GynaeSpecimen() {
        value.put("1",splitArryfiler(new String[]{"Breast FNA", "Right First Site", "02 FNA Slides"}));
        value.put("2",splitArryfiler(new String[]{"Breast FNA", "Right First Site", "02 FNA Slides"}));
        value.put("2.1",splitArryfiler(new String[]{"Breast FNA", "Left First Site", "02 FNA Slides"}));
        value.put("3.1",splitArryfiler(new String[]{"Breast FNA", "Right First Site", "02 FNA Slides"}));
        value.put("3.2",splitArryfiler(new String[]{"Breast FNA", "Right Second Site", "02 FNA Slides"}));
    }

    public List<String> splitArryfiler(String[] value){

        return Arrays.asList(value);
    }
}
