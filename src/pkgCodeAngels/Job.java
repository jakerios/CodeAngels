/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgCodeAngels;

/**
 *
 * @author jimko
 */
public class Job {
    private String jobID;
    private String empID;
    private String jobTitle;
    private String jobDescription;
    private String jobAddress;
    private String jobCounty;
    private String jobPostTime;
    private String jobPay;
    private boolean jobOpen;

    public Job() {
        this.jobID = "";
        this.empID = "";
        this.jobTitle = "";
        this.jobDescription = "";
        this.jobAddress = "";
        this.jobCounty = "";
        this.jobPostTime = "";
        this.jobPay = "";
        this.jobOpen = true;
    }
    
    public Job(String jobID, String empID, String jobTitle, String jobDescription, String jobAddress, String jobCounty, String jobPostTime, String jobPay, boolean jobOpen) {
        this.jobID = jobID;
        this.empID = empID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobAddress = jobAddress;
        this.jobCounty = jobCounty;
        this.jobPostTime = jobPostTime;
        this.jobPay = jobPay;
        this.jobOpen = jobOpen;
            
    }
    
    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public String getJobCounty() {
        return jobCounty;
    }

    public void setJobCounty(String jobCounty) {
        this.jobCounty = jobCounty;
    }

    public String getJobPostTime() {
        return jobPostTime;
    }

    public void setJobPostTime(String jobPostTime) {
        this.jobPostTime = jobPostTime;
    }

    public String getJobPay() {
        return jobPay;
    }

    public void setJobPay(String jobPay) {
        this.jobPay = jobPay;
    }

    public boolean isJobOpen() {
        return jobOpen;
    }

    public void setJobOpen(boolean jobOpen) {
        this.jobOpen = jobOpen;
    }

    
}
