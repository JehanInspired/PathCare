package applications.PathCareapplication.tool;

import applications.PathCareapplication.models.*;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;

public class ExcelExtractorList {

    static String path = "QC template 18 April 2023.xlsx";

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
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(",").sheetName("Work Area Receive").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), WorkAreaReceiveEntity.class,options);
    }

    //Results Generator
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

    static public List<ResultsGenerator_PCP> resultsGenerator_PCP() {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_PCP").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsGenerator_PCP.class, options);
    }

     //Change sheet name from ResultsGenerator_AAGeorge to ResultsGenerator_ George
    static public List<ResultsGenerator_AAGeorge> resultsGenerator_AAGeorge(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_ George").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsGenerator_AAGeorge.class,options);
    }

    static public List<ResultsGenerator_RocheSysmexXGE> resultsGenerator_RocheSysmexXGE(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_RocheSysmexXGE").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsGenerator_RocheSysmexXGE.class,options);
    }

    static public List<ResultsGenerator_Sysmexca620Geo> resultsGenerator_Sysmexca620Geo(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_RocheSysmexXGE").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsGenerator_Sysmexca620Geo.class,options);
    }

    static public List<ResultsGenerator_Aquios1> resultsGenerator_Aquios1(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_Aquios1").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsGenerator_Aquios1.class,options);
    }
    static public List<ResultsGenerator_Abbott> resultsGenerator_Abbott(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("ResultsGenerator_").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsGenerator_Abbott.class,options);
    }
    //End

    //Result Entries
    static public List<ResultsEntry> resultsEntries(){
            PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("ResultEntry").build();
            return Poiji.fromExcel(new File("src/main/resources/".concat(path)), ResultsEntry.class,options);
    }

    static public List<TestSetValuesEntity> testSetValues(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("TestSetValues").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), TestSetValuesEntity.class,options);
    }

    //Work Sheet
    static public List<WorkSheetResultEntry> workSheetResultEntries(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("Worksheet result entry").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), WorkSheetResultEntry.class,options);
    }

    static public List<WorkSheetResultValues> workSheetResultValues(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("WorkSheet result Values").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), WorkSheetResultValues.class,options);
    }
    //End Work Sheet

    //Queue
    static public List<LabQueueEntity> labQueues(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().headerCount(2).sheetName("Queues").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), LabQueueEntity.class,options);
    }

    static public List<LabQueueValuesEntity> labQueueValues(){
        PoijiOptions options  = PoijiOptions.PoijiOptionsBuilder.settings().sheetName("QueuesValues").build();
        return Poiji.fromExcel(new File("src/main/resources/".concat(path)), LabQueueValuesEntity.class,options);
    }
    //End Queue

}
