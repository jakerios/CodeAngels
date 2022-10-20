/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgCodeAngels;

/**
 *
 * @author jimko
 */
public class Employer {
    private String empID;
    private String empEmail;
    private String empPass;
    private String empBusinessName;
    private String empContactName;
    private String empPhone;
    private String empAddress;
    private String empJoinDate;
    private String empSecAns;

    public Employer() {
        super();
        this.empID = "";
        this.empEmail = "";
        this.empPass = "";
        this.empBusinessName = "";
        this.empContactName = "";
        this.empPhone = "";   
        this.empAddress = "";
        this.empJoinDate = "";
        this.empSecAns = "";
    }

    public Employer(String empID, String empPass,String empBusinessName,String empContactName, String empPhone, String empEmail, String empAddress, String empSecAns, String empJoinDate) {
        super();
        this.empID = empID;
        this.empEmail = empEmail;
        this.empPass = empPass;
        this.empBusinessName = empBusinessName;
        this.empContactName = empContactName;
        this.empPhone = empPhone;
        this.empAddress = empAddress;
        this.empJoinDate = empJoinDate;
        this.empSecAns = empSecAns;
    }
    
    

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    public String getEmpBusinessName() {
        return empBusinessName;
    }

    public void setEmpBusinessName(String empBusinessName) {
        this.empBusinessName = empBusinessName;
    }

    public String getEmpContactName() {
        return empContactName;
    }

    public void setEmpContactName(String empContactName) {
        this.empContactName = empContactName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }
    
    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }    

    public String getEmpJoinDate() {
        return empJoinDate;
    }

    public void setEmpJoinDate(String empJoinDate) {
        this.empJoinDate = empJoinDate;
    }

    public String getEmpSecAns() {
        return empSecAns;
    }

    public void setEmpSecAns(String empSecAns) {
        this.empSecAns = empSecAns;
    }


}
