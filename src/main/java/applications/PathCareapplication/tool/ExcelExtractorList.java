package applications.PathCareapplication.tool;

import applications.PathCareapplication.models.*;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;

public class ExcelExtractorList {

    static String path ="Registration and Result entry Template With DTF 27Jan2023.xlsx";

    public static List<PatientModel> patientData(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("Registration").addListDelimiter(",")
                .build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), PatientModel.class, options);

    }

    public static List<TestSetCodeEntity> testSetCode(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("TestSetCode").addListDelimiter(",")
                .build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), TestSetCodeEntity.class, options);
    }

    public static List<UserProfileEntity> userProfile(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("UserProfile").addListDelimiter(",")
                .build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), UserProfileEntity.class, options);
    }

    static public  List<TestSetDetailsEntity> testSetDetails(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("TestSetDetails").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), TestSetDetailsEntity.class, options);
    }
    static public List<SpecimensEntity> specimenDetails(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("Specimens").addListDelimiter(",")
                .build();
        return  Poiji.fromExcel(new File("src/main/resources/".concat(path)), SpecimensEntity.class, options);

    }

    static public List<EditTestSetEntity> editTestSets(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("EditTestSet").build();
        return  Poiji.fromExcel(new File("src/main/resources/".concat(path)), EditTestSetEntity.class, options);

    }

    public static List<SpecimenReceiveEntity> specimenReceives(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(3).sheetName("Specimen receive").build();
        return  Poiji.fromExcel(new File("src/main/resources/".concat(path)), SpecimenReceiveEntity.class, options);
    }

    static public List<WorkAreaReceiveEntity> workAreaReceives(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("Work Area Receive").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), WorkAreaReceiveEntity.class,options);
    }

    static public List<ResultsGenerator_AbbottAlinityc> resultsGenerator_abbottAlinitycs(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_AbbottAlinityc").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)),ResultsGenerator_AbbottAlinityc.class,options);
    }

    static public List<ResultsGenerator_RocheSysmexXN1> resultsGeneratorRocheSysmexXN1s(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_RocheSysmexXN1").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)),ResultsGenerator_RocheSysmexXN1.class,options);
    }

    static public List<ResultsGenerator_SysmexCS2500> resultsGenerator_sysmexCS2500s(){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_SysmexCS2500").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)),ResultsGenerator_SysmexCS2500.class,options);
    }

     static public List<ResultsGenerator_PCPBioFireFilm> resultsGenerator_pcpBioFireFilms() {
         PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_PCPBioFireFilm").build();
         return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsGenerator_PCPBioFireFilm.class, options);
     }

        static public List<ResultsEntry> resultsEntries(){
            PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("ResultEntry").build();
            return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsEntry.class,options);
         }

    static public List<TestSetValuesEntity> testSetValues(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("TestSetValues").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), TestSetValuesEntity.class,options);
    }

}
