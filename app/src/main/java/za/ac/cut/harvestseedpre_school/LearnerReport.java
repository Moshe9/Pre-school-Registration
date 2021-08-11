package za.ac.cut.harvestseedpre_school;

public class LearnerReport {

    private String LearnerName;
    private String LearnerReportRecommendation;
    private String ReportTitle;
    private String ReportInformation;
    private String InsertDate;

    public LearnerReport() {
        this.LearnerName = null;
        this.LearnerReportRecommendation = null;
        this.ReportTitle = null;
        this.ReportInformation = null;
        this.InsertDate = null;
    }

    public String getLearnerName() {
        return LearnerName;
    }

    public void setLearnerName(String learnerName) {
        LearnerName = learnerName;
    }

    public String getLearnerReportRecommendation() {
        return LearnerReportRecommendation;
    }

    public void setLearnerReportRecommendation(String learnerReportRecommendation) {
        LearnerReportRecommendation = learnerReportRecommendation;
    }

    public String getReportTitle() {
        return ReportTitle;
    }

    public void setReportTitle(String reportTitle) {
        ReportTitle = reportTitle;
    }

    public String getReportInformation() {
        return ReportInformation;
    }

    public void setReportInformation(String reportInformation) {
        ReportInformation = reportInformation;
    }

    public String getInsertDate() {
        return InsertDate;
    }

    public void setInsertDate(String insertDate) {
        InsertDate = insertDate;
    }
}
