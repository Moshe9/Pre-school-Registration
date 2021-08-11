package za.ac.cut.harvestseedpre_school;


import java.util.Date;

public class ReportMaintenance {

    private String reportTitle;
    private String reportType;
    private String ReportInformation;
    private String InserDate;
    private String Status;



    public ReportMaintenance() {
    }

    public ReportMaintenance(String reportTitle, String reportType, String reportInformation, String inserDate, String status) {
        this.reportTitle = reportTitle;
        this.reportType = reportType;
        ReportInformation = reportInformation;
        InserDate = inserDate;
        Status = status;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportInformation() {
        return ReportInformation;
    }

    public void setReportInformation(String reportInformation) {
        ReportInformation = reportInformation;
    }

    public String getInserDate() {
        return InserDate;
    }

    public void setInserDate(String inserDate) {
        InserDate = inserDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}
