/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgCodeAngels;

/**
 *
 * @author jimko
 */
public class Angel {
    private String angelID;
    private String angelFirst;
    private String angelLast;
    private String angelPhone;
    private String angelEmail;
    private String angelCounty;
    private String angelPass;
    private String angelJoinDate;
    private String angelSecAns;

    public Angel() {
        super();
        this.angelID = "";
        this.angelFirst = "";
        this.angelLast = "";
        this.angelPhone = "";
        this.angelEmail = "";
        this.angelCounty = "";
        this.angelPass = "";
        this.angelJoinDate = "";
        this.angelSecAns = "";
    }

    public Angel(String angelID, String angelFirst, String angelLast, String angelPhone, String angelEmail, String angelCounty, String angelPass, String angelSecAns,String angelJoinDate) {
        super();
        this.angelID = angelID;
        this.angelFirst = angelFirst;
        this.angelLast = angelLast;
        this.angelPhone = angelPhone;
        this.angelEmail = angelEmail;
        this.angelCounty = angelCounty;
        this.angelPass = angelPass;
        this.angelJoinDate = angelJoinDate;
        this.angelSecAns = angelSecAns;
    }
    

    public String getAngelID() {
        return angelID;
    }

    public void setAngelID(String angelID) {
        this.angelID = angelID;
    }

    public String getAngelFirst() {
        return angelFirst;
    }

    public void setAngelFirst(String angelFirst) {
        this.angelFirst = angelFirst;
    }

    public String getAngelLast() {
        return angelLast;
    }

    public void setAngelLast(String angelLast) {
        this.angelLast = angelLast;
    }

    public String getAngelPhone() {
        return angelPhone;
    }

    public void setAngelPhone(String angelPhone) {
        this.angelPhone = angelPhone;
    }

    public String getAngelEmail() {
        return angelEmail;
    }

    public void setAngelEmail(String angelEmail) {
        this.angelEmail = angelEmail;
    }

    public String getAngelCounty() {
        return angelCounty;
    }

    public void setAngelCounty(String angelCounty) {
        this.angelCounty = angelCounty;
    }

    public String getAngelPass() {
        return angelPass;
    }

    public void setAngelPass(String angelPass) {
        this.angelPass = angelPass;
    }

    public String getAngelJoinDate() {
        return angelJoinDate;
    }

    public void setAngelJoinDate(String angelJoinDate) {
        this.angelJoinDate = angelJoinDate;
    }

    public String getAngelSecAns() {
        return angelSecAns;
    }

    public void setAngelSecAns(String angelSecAns) {
        this.angelSecAns = angelSecAns;
    }

    
    
    
}
