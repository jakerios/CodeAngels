/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgCodeAngels;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/**
 *
 * @author jimko
 */
public class Message {
    private String messageID;
    private String empID;
    private String jobID;
    private String angelID;
    private String messageContent;
    private String messagePostTime;
    private boolean unreadMessage;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    LocalDateTime now = LocalDateTime.now();  
    
    public Message() {
        this.messageID = "";
        this.empID = "";
        this.jobID = "";
        this.angelID = "";
        this.messageContent = "";
        this.messagePostTime = "";
        this.unreadMessage = true;
    }

    public Message(String messageID, String empID, String jobID, String angelID, String messageContent) {
        this.messageID = messageID;
        this.empID = empID;
        this.jobID = jobID;
        this.angelID = angelID;
        this.messageContent = messageContent;
        this.messagePostTime = LocalDateTime.now().toString();
        this.unreadMessage = true;        
    }
    
    public Message(String messageID, String empID, String jobID, String angelID, String messageContent, String messagePostTime, boolean unreadMessage) {
        this.messageID = messageID;
        this.empID = empID;
        this.jobID = jobID;
        this.angelID = angelID;
        this.messageContent = messageContent;
        this.messagePostTime = messagePostTime;
        this.unreadMessage = unreadMessage;      
    }
    
    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getAngelID() {
        return angelID;
    }

    public void setAngelID(String angelID) {
        this.angelID = angelID;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessagePostTime() {
        return messagePostTime;
    }

    public void setMessagePostTime(String messagePostTime) {
        this.messagePostTime = messagePostTime;
    }

    public boolean isUnreadMessage() {
        return unreadMessage;
    }

    public void setUnreadMessage(boolean unreadMessage) {
        this.unreadMessage = unreadMessage;
    }    
    
}
