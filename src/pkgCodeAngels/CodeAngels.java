/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgCodeAngels;

/**
 *
 * @author jimko
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class CodeAngels 
{
    Vector<Angel> angels; 	// ADT = Vector
    Vector<Angel> newangels; 	// ADT = Vector
    Vector<Employer> employers;
    Vector<Employer> newemployers;
    Vector<Job> jobs;
    Vector<Job> newjobs;    
    Vector<Message> messages;
    Vector<Message> newmessages;  
    Queue<Message> liumessages;
    
    int liu;    //index of logged in user
    int newagls;
    int newemps;
    int newjbs;
    int newmess;
    Employer lie = new Employer();
    //Angel user; //Angel object of current user 
    
    //Database objects
    Connection connection;
    Statement statement;
    ResultSet resultSet;  
     
    public CodeAngels() 
    {
        angels = new Vector<Angel>(); //From DB
        newangels = new Vector<Angel>(); //new AGLs
        newemployers = new Vector<Employer>(); //new EMPs
        employers = new Vector<Employer>();
        jobs = new Vector<Job>();
        newjobs = new Vector<Job>();        
        messages = new Vector<Message>();
        newmessages = new Vector<Message>(); 
        liumessages = new LinkedList<Message>();
 
        liu = -1;		//start at -1 because 0 is the first record
        newagls = 0; //start at 0 and update count when new Angel is added
        newemps = 0; //start at 0 and update count when new Employer is added
        newjbs = 0; //start 0 and update count when new Job is added
        newmess = 0; //start at 0 and update count when new Message is added
        
        connection = null;
        statement = null;
        resultSet = null;
    }

    public void loadData() throws IOException, SQLException
    {
        loadAngels();
        loadEmployers();
        loadJobs();
        loadMessages();
    }

    public void welcome() throws IOException, SQLException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        int width = 140;
        int height = 50;
        //Create a program logo
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 23));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("Code", 5, 20);
        graphics.drawString("Angels", 5, 40);

        for (int y = 0; y < height; y++) 
        {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) 
            {
                sb.append(image.getRGB(x, y) == -16777216 ? "*" : " ");
            }
            if (sb.toString().trim().isEmpty()) 
            {
                continue;
            }
            System.out.println(sb);
        }
        displayMainMenu();
    }

    public void displayMainMenu() throws IOException, SQLException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        //Display the Main Program menu
        //Display the Main Menu
        System.out.println("-------------------------------------------");
        System.out.println("1.  Login");
        System.out.println("2.  Sign Up");
        System.out.println("3.  Retrieve Password");
        System.out.println("4.  Exit");
        System.out.println("-------------------------------------------");
        
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"))
        {
            System.out.print("Enter your choice: ");
            choice = br.readLine().trim();
        }
        switch (Integer.parseInt(choice))
        {
            case (1):
            {
                login();
                break;
            }
            case (2):
            {
                signup();
                break;
            }
            case (3):
            {
                retpass();
                break;
            }
            case (4):
            {
                exitCASystem();
            }
            default: 
            {
                System.out.println("Invalid Choice!");
                exitCASystem();
            }
        } // end of switch statement
    }

    public void login() throws IOException, SQLException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numatts = 0;
        boolean loginvalid = false;
        String id = "", pwd = "", firstname = "";
        //Search the vector by ID and password - 3 attempts
        do 
        {
            System.out.print("Enter your user ID: ");
            id = br.readLine().trim();
            System.out.print("Enter your password: ");
            pwd = br.readLine();

            switch (id.charAt(0)) 
            {
                //Possible Angel ID detected, search Angel vector
                case 'A' -> 
                {
                    for (int i = 0; i < angels.size(); i++) 
                    {
                        if (id.equals(angels.get(i).getAngelID()) && pwd.equals(angels.get(i).getAngelPass()))
                        {
                            firstname = angels.get(i).getAngelFirst();
                            loginvalid = true;
                            liu = i;
                            System.out.println("Login successful.\n\nWelcome back, " + firstname + "!");
                            confirmationMessage(id);
                            displayAngelMenu();
                        }
                    }
                }
                //Possible Employer ID detected, search Employer vector
                case 'E' -> 
                {
                    for (int i = 0; i < employers.size(); i++)
                    {
                        if (id.equals(employers.get(i).getEmpID()) && pwd.equals(employers.get(i).getEmpPass()))
                        {
                            firstname = employers.get(i).getEmpContactName();
                            loginvalid = true;
                            liu = i;
                            System.out.println("Login successful.\n\nWelcome back, " + firstname + "!");
                            lie = employers.get(i);
                            
                            for (int in = 0; in < messages.size(); in++)
                            {
                                if (messages.get(in).getEmpID().equals(lie.getEmpID()))
                                {  
                                    liumessages.add(messages.get(in));
                                    //System.out.println(liumessages.remove().getMessageContent()); //test
                                }
                            }
                            displayEmployerMenu();
                        }
                    }
                }
                default -> 
                    System.out.println("Invalid login, please try again. \n");
            }
            numatts++;
        } while (!loginvalid);
        //while (!loginvalid && numatts < 3);
        
        
        //System.out.print("Number of attempts exceeded, program terminated!");
        //exitCASystem();     
    }

    public void displayAngelMenu() throws IOException, SQLException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        String choice1 = "";
        
        displaybyCounty(angels.get(liu).getAngelCounty());
        
        System.out.println("\nOptions: ");
        System.out.println("1.   Send Message Regarding A Listed Job");
        System.out.println("2.   Display Jobs");
        System.out.println("3.   Exit Program");
        System.out.println("-------------------------------------------\n");

        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"))
        {
            System.out.print("Enter your choice: ");
            choice = br.readLine().trim();
        }
        switch (Integer.parseInt(choice))
        {
            case (1):
            {  
                System.out.print("Enter job ID for a position that interests you: ");
                choice1 = br.readLine();
                sendMessage(choice1); 
                break;
            }
            case (2): 
            {
                displayAngelDisplayJobs();
                break;                
            }
            case (3): 
            {
                exitCASystem();
            }
            default: 
            {
                System.out.println("Invalid Choice! Try again. ");
                displayAngelMenu();
            }
        }
    }    

    public void displayAngelDisplayJobs() throws IOException, SQLException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        System.out.println("-------------------------------------------");
        System.out.println("                JOBS MENU                  ");
        System.out.println("-------------------------------------------");
        System.out.println("1.   Jobs By County");
        System.out.println("2.   All Jobs");
        System.out.println("3.   Return");
        System.out.println("4.   Exit Program");
        System.out.println("-------------------------------------------\n");        
        
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"))
        {
            System.out.print("Enter your choice: ");
            choice = br.readLine().trim();
        }
        
        switch (Integer.parseInt(choice))
        {
            case (1): 
            {
                String countyChoice = "";
                System.out.println("What county are you searching for (Enter '1' for Suffolk, '2' for Brooklyn, '3' for Nassau, '4' for Queens, or '5' for Manhattan: ");
                countyChoice = br.readLine();
                if (countyChoice.equals("1")){countyChoice = "Suffolk";}
                else if (countyChoice.equals("2")){countyChoice = "Brooklyn";}
                else if (countyChoice.equals("3")){countyChoice = "Nassau";}
                else if (countyChoice.equals("4")){countyChoice = "Queens";}
                else if (countyChoice.equals("5")){countyChoice = "Manhattan";}
                else{
                    System.out.println("Error detected with input, attempting to run search: ");
                }
                displaybyCounty(countyChoice);
                displayAngelDisplayJobs();
                break;
            }
            case (2): 
            {
                displayallJobs();
                break;
            }
            case (3): 
            {
                displayAngelMenu();
                break;
            }           
            case (4): 
            {
                exitCASystem();
            }
            default: 
            {
                System.out.println("Invalid Choice! Try again");
                displayAngelDisplayJobs();
            }
        }
    }    

    public void displayEmployerMenu() throws IOException, SQLException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        System.out.println("-------------------------------------------");
        System.out.println("             EMPLOYER MENU                 ");
        System.out.println("-------------------------------------------");
        System.out.println("1.   Create Jobs");
        System.out.println("2.   Remove Jobs");
        System.out.println("3.   View Next Unread Message");
        System.out.println("4.   View All Read Messages");
        System.out.println("5.   Exit Program");
        System.out.println("-------------------------------------------\n");
        
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5"))
        {
            System.out.print("Enter your choice: ");
            choice = br.readLine().trim();
        }
        
        switch (Integer.parseInt(choice))
        {
            case (1): 
            {
                empNewJob(employers.elementAt(liu));
                break;
            }
            case (2): 
            {
                removeJobs();
                break;
            }
            case (3): 
            {
                displaynewMessages();
                break;
            }
            case (4): 
            {
                displayallMessages();
                break;
            }            
            case (5): 
            {
                exitCASystem();
            }
            default: 
            {
                System.out.println("Invalid Choice! Try again");
                displayEmployerMenu();
            }
        }
    }    

    public void empNewJob(Employer e) throws IOException, SQLException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lastidnum = 0;
        String jobTitle, jobDescription, jobAddress, jobCounty, jobPostTime, jobPay, newid, lastdigits, lastid;
        boolean jobOpen;

        System.out.print("What is the Job Title? ");
        jobTitle = br.readLine();
        System.out.print("What is the Job Description? ");
        jobDescription = br.readLine();
        System.out.print("What is the Job Address? ");
        jobAddress = br.readLine();
        System.out.print("What County is the Address located in? ");
        jobCounty = br.readLine().trim();
        System.out.print("What is the Pay for the Job? ");
        jobPay = br.readLine().trim();

        lastid = jobs.get(jobs.size() - 1).getJobID();
        lastdigits = lastid.substring(1, 4);  //last 3 digits
        lastidnum = Integer.parseInt(lastdigits);
        newid = "J" + ++lastidnum;
        //Display the new user id and add to the vector
        Job newjob = new Job(newid, e.getEmpID(), jobTitle, jobDescription, jobAddress, jobCounty, getSystemDate(), jobPay, true);
        jobs.add(newjob);
        System.out.println("Thank you " + e.getEmpContactName() + ", " + "your Job ID is: " + newid);
        newjobs.add(newjob);
        updateJobs();
        displayEmployerMenu(); 
    }

    void removeJobs() throws IOException, SQLException 
    {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String jobid;
        boolean empty = true;
        System.out.println("-------------------------------------------");
        System.out.println("           REMOVE JOBS");
        System.out.println("-------------------------------------------");
        ResultSet rs = statement.executeQuery("SELECT * FROM jobs WHERE jobOpen = yes");
        while(rs.next()) 
        {
            if (rs.getString("empID").equals(lie.getEmpID()))
            {
                System.out.println("Job ID:" + rs.getString("jobID") + " " + rs.getString("jobTitle")); 
                empty = false;
            }
        }
        if (empty == true) {
            System.out.println("You have no active jobs to remove. ");
            displayEmployerMenu();
        }
        System.out.print("Please input a Job ID to remove: ");
        jobid = br.readLine();

        Statement stmt = connection.createStatement();
        String SQL = "UPDATE  jobs set jobOpen = no WHERE jobID = '"+jobid+"'";
        stmt.executeUpdate(SQL);
        System.out.println("Jobs Updated, " + jobid + " has been removed from the database.");
        displayEmployerMenu(); 
        
    }
    
    public void retpass() throws IOException, SQLException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numatts = 0;
        boolean passRet = false;
        String id = "", pet = "", pwd = "", firstname = "";

        System.out.println("-------------------------------------------");
        System.out.println("             RETRIEVE PASSWORD  ");
        System.out.println("-------------------------------------------");
        
        do
        {
            System.out.print("Enter your user ID: ");
            id = br.readLine().trim();
            System.out.print("Enter your pet's name: ");
            pet = br.readLine();
            
            if (id.charAt(0) == 'A') 
            {
                for (int i = 0; i < angels.size(); i++) 
                {
                    if (id.equals(angels.get(i).getAngelID()) && pet.equals(angels.get(i).getAngelSecAns())) 
                    {
                        pwd = angels.get(i).getAngelPass();
                        firstname = angels.get(i).getAngelFirst();
                        passRet = true;
                    }
                }
            }
            if (id.charAt(0) == 'E') 
            {
                for (int i = 0; i < employers.size(); i++) 
                {
                    if (id.equals(employers.get(i).getEmpID()) && pet.equals(employers.get(i).getEmpSecAns()))
                    {
                        System.out.println("Hello");
                        pwd = employers.get(i).getEmpPass();
                        firstname = employers.get(i).getEmpContactName();
                        passRet = true;
                    }
                }
            }         
            if (!passRet)
            {
                System.out.println("Invalid ID or Pet name!");
            }
            numatts++;
        }   while (!passRet && numatts < 3);
        if  (passRet)
        {
            System.out.println("Hello " + firstname + ", " + "your password is: " + pwd);
        }
        else 
        {
            System.out.println("Number of attempts exceeded, program terminated!");
            exitCASystem();
        }
        displayMainMenu();
    }
       
    public void signup() throws IOException, SQLException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sizeA = angels.size();
        int sizeE = employers.size();
        int lastidnum = 0;
        String lastdigits = "", lastid = "", newid = "", pet = "", pwd = "", fname = "", lname = "", email = "", phone = "", county = "", 
                  bname = "", cname = "", address = "", letter = "", choice = "";
        String joindate = getSystemDate();
        System.out.println("-------------------------------------------");
        System.out.println("                  SIGN-UP                  ");
        System.out.println("-------------------------------------------");

        System.out.println("Enter the letter 'A' to signup as an Angel or 'E' to signup as an Employer");
        letter = br.readLine().trim();
        
        if (letter.charAt(0) == 'A' || letter.charAt(0) == 'a') 
        {   
            System.out.print("Enter your First name: ");
            fname = br.readLine().trim();
            System.out.print("Enter your Last name: ");
            lname = br.readLine().trim();
            System.out.print("Enter your Phone number: ");
            phone = br.readLine();
            System.out.print("Enter your Email: ");
            email = br.readLine();
            
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5")) 
            {
                System.out.print("What is your County (Enter '1' for Suffolk, '2' for Brooklyn, '3' for Nassau, '4' for Queens, or '5' for Manhattan: ");
                choice = br.readLine();
            }
            
            switch (Integer.parseInt(choice)) 
            {
                case (1): 
                {
                    county = "Suffolk";
                    break;
                }
                case (2): 
                {
                    county = "Brooklyn";
                    break;
                }
                case (3): 
                {
                    county = "Nassau";
                    break;
                }
                case (4):
                {
                    county = "Queens";
                    break;
                }
                case (5):
                {
                    county = "Manhattan";
                    break;
                }
                default: 
                {
                    System.out.println("Invalid Choice! Try again");
                    signup();
                }
            }
            System.out.print("Create a Password: ");
            pwd = br.readLine();
            System.out.print("Enter your Pet's name: ");
            pet = br.readLine();
            lastid = angels.get(sizeA - 1).getAngelID();
            lastdigits = lastid.substring(1,6);  
            lastidnum = Integer.parseInt(lastdigits);
            newid = "A" + ++lastidnum;
            //Display the new user id and add to the vector
            Angel agl = new Angel(newid, fname, lname, phone, email, county, pwd, pet, joindate);
            angels.add(agl);
            System.out.println("Hello " + agl.getAngelFirst() + ", " + "your Angel ID is: " + newid);
            newangels.add(agl);
            newagls++;
            updateAngels();
        }
        
        if (letter.charAt(0) == 'E' || letter.charAt(0) == 'e')
        {
            System.out.print("Create a Password: ");
            pwd = br.readLine();
            System.out.print("Enter your Business name: ");
            bname = br.readLine();
            System.out.print("Enter your Name: ");
            cname = br.readLine();
            System.out.print("Enter your Phone number: ");
            phone = br.readLine();
            System.out.print("Enter your Email: ");
            email = br.readLine();
            System.out.print("Enter your Address for the business: ");
            address = br.readLine();
            System.out.print("Enter your Pet's name: ");
            pet = br.readLine();
            lastid = employers.get(sizeE - 1).getEmpID();
            lastdigits = lastid.substring(1,4);  //last 3 digits
            lastidnum = Integer.parseInt(lastdigits);
            newid = "E" + ++lastidnum;
            //Display the new user id and add to the vector
            Employer emp = new Employer(newid, pwd, bname, cname, phone, email, address, pet, joindate);
            employers.add(emp);
            System.out.println("Hello " + emp.getEmpContactName() + ", " + "your Employer ID is: " + newid);
            newemployers.add(emp);
            newemps++;
            updateEmployers();
        }      
        //Write new users to text file at end of program or update database immediately
        displayMainMenu();
    }
    
    void loadAngels() throws IOException, SQLException
    {
        //create the variables for each field in the file
        String id = "";
        String first = "";
        String last = "";
        String phone ="";
        String email = "";
        String county = "";
        String password = "";
        String secAns = "";
        String joinDate = "";
        int totalrows = 0, index = 0;
        
        //Get the total rows in the table to loop through the result set
        resultSet = statement.executeQuery("SELECT * FROM ANGELS"); 
        //resultSet.first();
         
        totalrows = resultSet.getRow();
        while (resultSet.next()) //tests for the eof
        {   
            totalrows = resultSet.getRow();
            id = resultSet.getString("angelID");
            first = resultSet.getString("angelFirst");
            last = resultSet.getString("angelLast");
            phone = resultSet.getString("angelPhone");
            email = resultSet.getString("angelEmail");
            county = resultSet.getString("angelCounty");
            password = resultSet.getString("angelPass");
            secAns = resultSet.getString("angelSecAns");
            joinDate = resultSet.getString("angelJoinDate");
            angels.add(new Angel(id,first,last,phone,email,county,password,secAns,joinDate));
            index++;
            
            //to test Angel load
            //System.out.println("ID: " + id + "\nFirst: " + first + "\nLast: " + last + "" + "\nPhone: " + phone + "\nemail: " + email + "\ncounty: " + county
            //+ "\npassword: " + password + "\nsecAns: " + secAns + "\njoinDate: " + joinDate);
        }//end of loading to array
        System.out.println("Angels Loaded");
    }
   
    void loadEmployers() throws IOException, SQLException
    {
        //create the variables for each field in the file
        String id = "";
        String password = "";
        String busName = "";
        String contactName ="";
        String phone = "";
        String email = "";
        String address = "";
        String secAns = "";
        String joinDate = "";
        int totalrows = 0, index = 0;

        //Get the total rows in the table to loop through the result set
        resultSet = statement.executeQuery("SELECT * FROM EMPLOYERS");      
        //resultSet.first();
        
        totalrows = resultSet.getRow();
        while (resultSet.next()) //tests for the eof
            {
                totalrows = resultSet.getRow();
                secAns = resultSet.getString("empSecAns");
                joinDate = resultSet.getString("empJoinDate");
                id = resultSet.getString("empID");
                password = resultSet.getString("empPassword");
                busName = resultSet.getString("empBusinessName");
                contactName = resultSet.getString("empContactName");
                phone = resultSet.getString("empPhone");
                email = resultSet.getString("empEmail");
                address = resultSet.getString("empAddress");                

                employers.add(new Employer(id,password,busName,contactName,phone,email, address, secAns,joinDate));
                index++;
                
                //test employer load
                //     System.out.println("ID: " + id + "\npassword: " + password + "\nbusName" + busName + "" + "\ncontactName: " + contactName + "\nphone: "  + phone
                //     + "\nemail: " + email + "\naddress: " + address + "\nsecAns: " + secAns + "\njoindate" + joinDate);
            }//end of loading to array			
            System.out.println("Employers Loaded");
    }

    void loadJobs()  throws IOException, SQLException
    {	
        //create the variables for each field in the file
        String jobID = "";
        String empID = "";
        String jobTitle = "";
        String jobDescription ="";
        String jobAddress = "";
        String jobCounty = "";
        String jobPostTime = "";
        String pay = "";
        boolean jobOpen = false;
        int totalrows = 0, index = 0;
						
        //Get the total rows in the table to loop through the result set
        resultSet = statement.executeQuery("SELECT * FROM JOBS"); 
        //resultSet.last();
        
        totalrows = resultSet.getRow();
        while (resultSet.next()) //tests for the eof
        {   
            totalrows = resultSet.getRow();
            jobID = resultSet.getString("jobID");
            empID = resultSet.getString("empID");
            jobTitle = resultSet.getString("jobTitle");
            jobDescription = resultSet.getString("jobDescription");
            jobAddress = resultSet.getString("jobAddress");
            jobCounty = resultSet.getString("jobCounty");
            jobPostTime = resultSet.getString("jobPostTime");
            pay = resultSet.getString("pay");
            jobOpen = resultSet.getBoolean("jobOpen");
            
            jobs.add(new Job(jobID,empID,jobTitle,jobDescription,jobAddress,jobCounty,jobPostTime,pay,jobOpen));
            index++;
            
            //test load jobs
            //System.out.println("jobID: " + jobID + "\nempID: " + empID + "\njobTitle: " + jobTitle + "" + "\njobDescription: " + jobDescription + "\njobAddress: "  + jobAddress
            //+ "\njobCounty: " + jobCounty + "\njobPostTime: " + jobPostTime + "\npay: " + pay + "\njobOpen: " + jobOpen);        
        
        }//end of loading to array
        System.out.println("Jobs Loaded");
    }
        
    void loadMessages()  throws IOException, SQLException
    {	
        //create the variables for each field in the file
        String messageID = "", jobID = "", angelID = "", empID = "", messageContent ="",messagePostTime = "";        
        boolean unreadMessage = false;
        int totalrows = 0, index = 0;
						
        //Get the total rows in the table to loop through the result set
        resultSet = statement.executeQuery("SELECT * FROM MESSAGE"); 
        // WHERE unreadMessage = TRUE");
        totalrows = resultSet.getRow();
        
        while (resultSet.next()) //tests for the eof
        {
            totalrows = resultSet.getRow();
            messageID = resultSet.getString("messageID");
            jobID = resultSet.getString("jobID");
            empID = resultSet.getString("empID");
            angelID = resultSet.getString("angelID");
            messageContent = resultSet.getString("messageContent");
            messagePostTime = resultSet.getString("messagePostTime");
            unreadMessage = resultSet.getBoolean("unreadMessage");

            messages.add(new Message(messageID,empID,jobID,angelID,messageContent, messagePostTime ,unreadMessage));
            index++;

            //test messages load
            //System.out.println("messageID: " + messageID + "\njobID: " + jobID + "\nangelID: " + angelID + "" + "\nmessageContent: " + messageContent 
            //+ "\nmessagePostTime: " + messagePostTime + "\nunreadMessage: " + unreadMessage);

        }//end of loading to array		
        System.out.println("Messages Loaded");
    }
        
    String getSystemDate() 
    {
        String timestamp = ""; //Create a string to hold the date
        String pattern = "MM/dd/yyyy HH:mm:ss"; //Determine the pattern for the date and time fields
        SimpleDateFormat formatter = new SimpleDateFormat(pattern); //Set your date and time pattern
        Date date = new Date(); //Capture the system datetime in milliseconds
        timestamp = formatter.format(date); //Format the date based on the pattern
        return timestamp + " PM";
    }
    
    void exitCASystem() throws IOException, SQLException
    {
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("Thank you for using Code Angels, Program Ended!");
        System.out.println("-------------------------------------------------------------");
        System.exit(0);  
    }
    
    void connectDB() throws ClassNotFoundException, SQLException
    {
        // Database variables
        // Step 1: Loading or registering JDBC driver class 
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 		 
        // Step 2: Opening database connection
        String msAccDB = "CA.accdb";
        String dbURL = "jdbc:ucanaccess://" + msAccDB; 
        // Step 3: Create and get connection using DriverManager class
        connection = DriverManager.getConnection(dbURL); 
        // Step 4: Creating JDBC Statement 
        // It is scrollable so we can use next() and last() & It is updatable so we can enter new records
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
        System.out.println("Database Connected!");
    }
    
    void updateAngels() throws IOException, SQLException
    {	
        //create the variables for each field in the file
        String id = "";
        String first = "";
        String last = "";
        String phone ="";
        String email = "";
        String county = "";
        String password = "";
        String secAns = "";
        String joinDate = "";
        
        int newagls = newangels.size();
              				
        for (int ind = 0; ind < newagls; ind++)
        {   // insert the data
            Angel agl = new Angel();
            agl = newangels.get(ind);
						
            id = agl.getAngelID();
            first = agl.getAngelFirst();
            last = agl.getAngelLast();
            phone = agl.getAngelPhone();
            email = agl.getAngelEmail();
            county = agl.getAngelCounty();
            password = agl.getAngelPass();
            secAns = agl.getAngelSecAns();
            joinDate = agl.getAngelJoinDate();
            
            statement.executeUpdate("INSERT INTO ANGELS VALUES ("
                + "'"+ id +"',"
                + "'"+ first +"', "
                + "'"+ last +"', "
                + "'"+ phone +"', "
                + "'"+ email +"', "
                + "'"+ county +"', "
                + "'"+ password +"', "
                + "'"+ secAns +"', "
                + "'"+ joinDate +"')");				
        }				
        System.out.println("Angels Updated, " + newagls + " new accounts added.");
    }    

    void updateEmployers() throws IOException, SQLException
    {	
        //create the variables for each field in the file
        String id = "";
        String password = "";
        String busName = "";
        String contactName ="";
        String phone = "";
        String email = "";
        String address = "";
        String secAns = "";
        String joinDate = "";
        
        int newemps = newemployers.size();     				
        for (int ind = 0; ind < newemps; ind++)
        {   // insert the data
            Employer emp = new Employer();
            emp = newemployers.get(ind);
						
            id = emp.getEmpID();
            password = emp.getEmpPass();
            busName = emp.getEmpBusinessName();
            contactName = emp.getEmpContactName();
            phone = emp.getEmpPhone();
            email = emp.getEmpEmail();
            address = emp.getEmpAddress();
            secAns = emp.getEmpSecAns();
            joinDate = emp.getEmpJoinDate();
            
            statement.executeUpdate("INSERT INTO EMPLOYERS VALUES ("
                + "'"+ id +"',"
                + "'"+ password +"', "
                + "'"+ busName +"', "
                + "'"+ contactName +"', "
                + "'"+ phone +"', "
                + "'"+ email +"', "
                + "'"+ address +"', "
                + "'"+ secAns +"', "
                + "'"+ joinDate +"')");
					
        }				
        System.out.println("Employers Updated, " + newemps + " new accounts added.");
    }     

    void updateJobs() throws IOException, SQLException 
    {
        String jobID;
        String empID;
        String jobTitle;
        String jobDescription;
        String jobAddress;
        String jobCounty;
        String jobPostTime;
        String jobPay;
        boolean jobOpen;

        int newjbs = newjobs.size();
        for (int ind = 0; ind < newjbs; ind++) 
        {   // insert the data
            Job job = new Job();
            job = newjobs.get(ind);

            jobID = job.getJobID();
            empID = job.getEmpID();
            jobTitle = job.getJobTitle();
            jobDescription = job.getJobDescription();
            jobAddress = job.getJobAddress();
            jobCounty = job.getJobCounty();
            jobPostTime = job.getJobPostTime();
            jobPay = job.getJobPay();
            jobOpen = job.isJobOpen();

            statement.executeUpdate("INSERT INTO JOBS VALUES ("
                    + "'" + jobID + "',"
                    + "'" + empID + "', "
                    + "'" + jobTitle + "', "
                    + "'" + jobDescription + "', "
                    + "'" + jobAddress + "', "
                    + "'" + jobCounty + "', "
                    + "'" + jobPostTime + "', "
                    + "'" + jobPay + "', "
                    + "'" + jobOpen + "')");
        }
        newjobs.removeAll(jobs);
        System.out.println("Jobs Updated, " + newjbs + " New Jobs added.");
    }  

    void updateMessages() throws IOException, SQLException 
    {
        String messageID;
        String empID;
        String jobID;
        String angelID;
        String messageContent;
        String messagePostTime;
        boolean unreadMessage;

        int newmess = newmessages.size();
        
        for (int ind = 0; ind < newmess; ind++) 
        {   // insert the data
            Message message = new Message();
            message = newmessages.get(ind);

            messageID = message.getMessageID();
            empID = message.getEmpID();
            jobID = message.getJobID();
            angelID = message.getAngelID();
            messageContent = message.getMessageContent();
            messagePostTime = message.getMessagePostTime();
            unreadMessage = message.isUnreadMessage();

            statement.executeUpdate("INSERT INTO MESSAGE VALUES ("
                    + "'" + messageID + "',"
                    + "'" + empID + "', "
                    + "'" + jobID + "', "
                    + "'" + angelID + "', "
                    + "'" + messageContent + "', "
                    + "'" + messagePostTime + "', "
                    + "'" + unreadMessage + "')");

        }
        System.out.println("Messages Updated, " + newmess + " New Messages read.");
    }  
    
    void dbAddMessages() throws IOException, SQLException 
    {
        //create the variables for each field in the file
        String messageID;
        String empID;
        String jobID;
        String angelID;
        String messageContents;
        String messagePostTime;
        boolean unreadMessage = true;

        int newmsgs = newmessages.size();

        for (int ind = 0; ind < newagls; ind++) 
        {   // insert the data
            Message msg = new Message();
            msg = newmessages.get(ind);

            messageID = msg.getMessageID();
            empID = msg.getEmpID();
            jobID = msg.getJobID();
            angelID = msg.getAngelID();
            messageContents = msg.getMessageContent();
            messagePostTime = msg.getMessagePostTime();
            unreadMessage = msg.isUnreadMessage();
            statement.executeUpdate("INSERT INTO MESSAGE VALUES ("
                    + "'" + messageID + "',"
                    + "'" + empID + "', "        
                    + "'" + jobID + "', "
                    + "'" + angelID + "', "
                    + "'" + messageContents + "', "
                    + "'" + messagePostTime + "', "
                    + "'" + unreadMessage + "')");
        }
        System.out.println("Messages, " + newmsgs + " new messages added.");
        newmessages.removeAllElements();
    }  
        
    void displaybyCounty(String county) throws IOException, SQLException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Employer emp = new Employer();
        emp.setEmpBusinessName("Employer not found");
        ResultSet rs = statement.executeQuery("SELECT * FROM jobs WHERE jobCounty = '"+county+"' ");
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("AVAILABLE POSITIONS IN : " + county);
        System.out.println("-----------------------------------");

        while(rs.next()) 
        {
            if (rs.getBoolean("jobOpen") == true) 
            {
                System.out.println("Job ID: " + rs.getString("jobID"));
                System.out.println("Job Title: " + rs.getString("jobTitle"));
                for (int i = 0; i < employers.size(); i++) 
                {
                    if (employers.get(i).getEmpID().equals(rs.getString("empID"))) 
                    {
                        emp = employers.get(i);
                    }
                }
                System.out.println("Employer: " + emp.getEmpBusinessName());
                System.out.println("Description: " + rs.getString("jobDescription") + "\n");                
            }
        }  
    }
    
    void displayallJobs() throws IOException, SQLException 
    {    
        System.out.println("-------------------------------------------");
        System.out.println("           ALL AVAILABLE JOBS");
        System.out.println("-------------------------------------------");
        
        ResultSet rs = statement.executeQuery("SELECT * FROM jobs WHERE jobOpen = yes");
        while(rs.next()) 
        {
            System.out.println("JobID: " + rs.getString("jobID") + " Job Title: " + rs.getString("jobTitle"));
        }
        displayAngelDisplayJobs();
    }
    
    void displaynewMessages() throws IOException, SQLException
    {
        System.out.println("-------------------------------------------");
        System.out.println("           NEW MESSAGES");
        System.out.println("-------------------------------------------");
        boolean cont = true;
        if (liumessages.isEmpty()) 
        {
            cont = false;
        }
        if (!cont)
        {
            System.out.println("There are no new messages to display.");
        }
        else 
        {
            Message mess1 = new Message();
            mess1 = liumessages.peek();
            //System.out.println(mess1.getMessageContent());
            mess1 = liumessages.remove();
            Angel tempAngel = new Angel();
            if (mess1.isUnreadMessage() == true)
            {
                for (int in = 0; in < angels.size(); in ++)
                {
                    if (angels.get(in).getAngelID().equals(mess1.getAngelID()))
                    {
                        tempAngel = angels.get(in);
                        String angelid = angels.get(in).getAngelID();
                        Statement stmt = connection.createStatement();
                        String SQL = "UPDATE message set unreadMessage = no WHERE angelID = '"+angelid+"' AND jobID = '"+mess1.getJobID()+"'";
                        stmt.executeUpdate(SQL); 
                    }
                }
                System.out.println("From: " + tempAngel.getAngelFirst() + " " + tempAngel.getAngelLast());
                System.out.println("Email: " + tempAngel.getAngelEmail() + "\n" + "Phone: " + tempAngel.getAngelPhone());
                System.out.println("Post Time: " + mess1.getMessagePostTime());
                System.out.println("Message: " + mess1.getMessageContent());
            }
        }
        displayEmployerMenu();        
    }
    
    void displayallMessages() throws IOException, SQLException 
    {    
        System.out.println("-------------------------------------------");
        System.out.println("           READ MESSAGES");
        System.out.println("-------------------------------------------");
        ResultSet rs = statement.executeQuery("SELECT * FROM message ORDER BY messagePostTime");
        //for loop to display only messages for that employer thats logged in
        //selects messages from newest to oldest
        
        String angelName = "";
        String position = "";
        while(rs.next()) 
        {
            
            for (int i = 0; i < angels.size(); i++){
                if (rs.getString("angelID").equals(angels.get(i).getAngelID())){
                    Angel a = angels.get(i);
                    angelName = a.getAngelFirst() + " " + a.getAngelLast();
                }          
            }
            
            for (int i = 0; i < jobs.size(); i++){
                if (rs.getString("jobID").equals(jobs.get(i).getJobID())){
                    Job j = jobs.get(i);
                    position = j.getJobTitle();
                }          
            }
            
            if (rs.getString("empID").equals(lie.getEmpID()) && !rs.getBoolean("unreadMessage"))
            {
                System.out.println("From: " + angelName + "\nPosition: " + position + "\nMessage: " + rs.getString("messageContent") + "\n");
            }
        }
        displayEmployerMenu();
    }
    
    void confirmationMessage(String id) throws IOException, SQLException
    {   
        ResultSet rs = statement.executeQuery("SELECT message.messageID, message.empID, message.jobID, message.angelID, "
                + "message.messageContent, message.messagePostTime, message.unreadMessage, employers.empBusinessName\n" +
                                                                    "FROM employers INNER JOIN message ON employers.[empID] = message.[empID];");
        while (rs.next())
        {
            if (id.equals(rs.getString("angelID")) && rs.getBoolean("unreadMessage") == false)
            {
                System.out.println(rs.getString("empBusinessName") + " has read your message.");
            }
            else if (id.equals(rs.getString("angelID")) && rs.getBoolean("unreadMessage") == true)
            {
                System.out.println(rs.getString("empBusinessName") + " has not yet read your message.");
            }
        }
    }
    
    public void sendMessage(String newJobID) throws IOException, SQLException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String messageID;
        String jobID;
        String empID = "";
        String angelID;
        String messageContent;
        String messagePostTime;
        String lastid, newid, lastdigits;
        int lastidnum;
        String truestring = "True";

        for (int i = 0; i < jobs.size(); i++)
        {   
            if (jobs.get(i).getJobID().equals(newJobID))
            {
                empID = jobs.get(i).getEmpID();
            }
            //System.out.println("Invalid input");
            //displayAngelMenu();
        }
        int sizeM = messages.size();
        boolean unreadMessage;
        System.out.println("\n-------------------------------------------");
        System.out.println("                  SEND MESSAGE                  ");
        System.out.println("-------------------------------------------");

        // we need to ask the user if they are an angel or an employer and split below into 2 cases
        System.out.print("Please enter the contents of your message: ");
        messageContent = br.readLine();

        lastid = messages.get(sizeM - 1).getMessageID();
        lastdigits = lastid.substring(1, 6);
        lastidnum = Integer.parseInt(lastdigits);
        newid = "M" + ++lastidnum;

        Message mes = new Message(newid, empID, newJobID, angels.get(liu).getAngelID(), messageContent);
        messages.add(mes);
        angelID = angels.get(liu).getAngelID();
        String postTime = mes.getMessagePostTime();
        
        statement.executeUpdate("INSERT INTO MESSAGE VALUES ("
                + "'" + newid + "',"
                + "'" + empID + "',"
                + "'" + newJobID + "', "
                + "'" + angelID + "', "
                + "'" + messageContent + "', "
                + "'" + mes.getMessagePostTime()+ "', "
                + "'" + truestring + "')");
        System.out.println("\nYour message has been sent to the Employer and we will let you know when they have seen it. ");
        displayAngelMenu();        
    }    
}
