package applications.PathCareapplication.models;

public class PatientModel {

    String userprofile ="";
    String Name = "";
    String Surname = "";
    String sex = "";
    String DateOfBirth = "";
    String referringDoctor = "";
    String patientLocation = "";


    public String getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(String userprofile) {
        this.userprofile = userprofile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getReferringDoctor() {
        return referringDoctor;
    }

    public void setReferringDoctor(String referringDoctor) {
        this.referringDoctor = referringDoctor;
    }

    public String getPatientLocation() {
        return patientLocation;
    }

    public void setPatientLocation(String patientLocation) {
        this.patientLocation = patientLocation;
    }

    public String getCollectionDateAndTime() {
        return collectionDateAndTime;
    }

    public void setCollectionDateAndTime(String collectionDateAndTime) {
        this.collectionDateAndTime = collectionDateAndTime;
    }

    public String[] getTestSet() {
        return testSet;
    }

    public void setTestSet(String[] testSet) {
        this.testSet = testSet;
    }

    String collectionDateAndTime = "";
    String[] testSet =null;

}
