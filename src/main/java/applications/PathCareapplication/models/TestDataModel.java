package applications.PathCareapplication.models;


public class TestDataModel {
    public Object labespode;
    public String specimennumber;
    public String testset;
    public String department;
    public String workArea;
    public String location;


    public TestDataModel(Object labespodespecimen, String testsets, String workArea, String department)
    {
        this.labespode = labespodespecimen;
        this.testset = testsets;
        this.department = department;
        this.workArea = workArea;

    }

    public TestDataModel(String labespodespecimen, String testset, String workArea, String department,String location)
    {
        this.specimennumber = labespodespecimen;
        this.testset = testset;
        this.department = department;
        this.workArea = workArea;
        this.location = location;
    }

    public TestDataModel(Object labespodespecimen, String workArea, String department)
    {
        this.labespode = labespodespecimen;
        this.department = department;
        this.workArea = workArea;
    }

}
