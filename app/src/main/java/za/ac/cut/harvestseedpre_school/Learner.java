package za.ac.cut.harvestseedpre_school;

import java.util.Date;


public class Learner {

    private String name;
    private String surname;
    private String className;
    private String gender;
    private String race;
    private String birthdate;
    private String languageInstruction;
    private String idNumber;
    private String dateEnrolled;
    private String address;
    private String type_student;


    private String motherName;
    private String fatherName;
    private String motherEmailAddress;
    private String fatherEmailAddress;
    private String motherContactNumber;
    private String fatherContactNumber;

    private String doctorName;
    private String doctorContactNumber;
    private String medicalAidName;
    private String medicalAidPlan;
    private String medicalAidNumber;
    private String alergiesOfLearner;
    private String tuckBalance;


    private String objectId;
    private Date created;
    private Date updated;

    public Learner() {
        this.name = null;
        this.className = null;
        this.tuckBalance = "0";
    }

    public Learner(String name, String surname, String className, String gender, String race, String birthdate, String languageInstruction, String idNumber, String dateEnrolled, String address, String type_student, String motherName, String fatherName, String motherEmailAddress, String fatherEmailAddress, String motherContactNumber, String fatherContactNumber, String doctorName, String doctorContactNumber, String medicalAidName, String medicalAidPlan, String medicalAidNumber, String alergiesOfLearner, String tuckBalance, String objectId, Date created, Date updated) {
        this.name = name;
        this.surname = surname;
        this.className = className;
        this.gender = gender;
        this.race = race;
        this.birthdate = birthdate;
        this.languageInstruction = languageInstruction;
        this.idNumber = idNumber;
        this.dateEnrolled = dateEnrolled;
        this.address = address;
        this.type_student = type_student;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.motherEmailAddress = motherEmailAddress;
        this.fatherEmailAddress = fatherEmailAddress;
        this.motherContactNumber = motherContactNumber;
        this.fatherContactNumber = fatherContactNumber;
        this.doctorName = doctorName;
        this.doctorContactNumber = doctorContactNumber;
        this.medicalAidName = medicalAidName;
        this.medicalAidPlan = medicalAidPlan;
        this.medicalAidNumber = medicalAidNumber;
        this.alergiesOfLearner = alergiesOfLearner;
        this.tuckBalance = tuckBalance;
        this.objectId = objectId;
        this.created = created;
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getLanguageInstruction() {
        return languageInstruction;
    }

    public void setLanguageInstruction(String languageInstruction) {
        this.languageInstruction = languageInstruction;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(String dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType_student() {
        return type_student;
    }

    public void setType_student(String type_student) {
        this.type_student = type_student;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherEmailAddress() {
        return motherEmailAddress;
    }

    public void setMotherEmailAddress(String motherEmailAddress) {
        this.motherEmailAddress = motherEmailAddress;
    }

    public String getFatherEmailAddress() {
        return fatherEmailAddress;
    }

    public void setFatherEmailAddress(String fatherEmailAddress) {
        this.fatherEmailAddress = fatherEmailAddress;
    }

    public String getMotherContactNumber() {
        return motherContactNumber;
    }

    public void setMotherContactNumber(String motherContactNumber) {
        this.motherContactNumber = motherContactNumber;
    }

    public String getFatherContactNumber() {
        return fatherContactNumber;
    }

    public void setFatherContactNumber(String fatherContactNumber) {
        this.fatherContactNumber = fatherContactNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorContactNumber() {
        return doctorContactNumber;
    }

    public void setDoctorContactNumber(String doctorContactNumber) {
        this.doctorContactNumber = doctorContactNumber;
    }

    public String getMedicalAidName() {
        return medicalAidName;
    }

    public void setMedicalAidName(String medicalAidName) {
        this.medicalAidName = medicalAidName;
    }

    public String getMedicalAidPlan() {
        return medicalAidPlan;
    }

    public void setMedicalAidPlan(String medicalAidPlan) {
        this.medicalAidPlan = medicalAidPlan;
    }

    public String getMedicalAidNumber() {
        return medicalAidNumber;
    }

    public void setMedicalAidNumber(String medicalAidNumber) {
        this.medicalAidNumber = medicalAidNumber;
    }

    public String getAlergiesOfLearner() {
        return alergiesOfLearner;
    }

    public void setAlergiesOfLearner(String alergiesOfLearner) {
        this.alergiesOfLearner = alergiesOfLearner;
    }

    public String getTuckBalance() {
        return tuckBalance;
    }

    public void setTuckBalance(String tuckBalance) {
        this.tuckBalance = tuckBalance;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
