package applications.PathCareapplication.models;

import java.io.*;
import java.util.*;

public class SuperSetTesCSF {

   public HashMap<String, List<String>> value = new HashMap<>();


    public SuperSetTesCSF() {
        value.put("CSF Macroscopy",splitArryfiler(new String[]{"Xanthochromic","Xanthochromic"}));
        value.put("CSF Microscopy",splitArryfiler(new String[]{"Organisms seen","Scanty"}));
        value.put("CSF Cell Count",splitArryfiler(new String[]{"14","0","1710"}));
        value.put("Cryptococcal Antigen LFA",splitArryfiler(new String[]{"Negative","Present"}));

    }

    public List<String> splitArryfiler(String[] value){

        return Arrays.asList(value);
    }
}
