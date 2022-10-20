/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgCodeAngels;

import java.io.IOException;
import java.sql.SQLException;

public class CASystem {


	public static void main(String[] args) {
		
		
		CodeAngels ca = new CodeAngels();
		
		try
		{	
			ca.connectDB();
			ca.loadData();		//load data from text files
			ca.welcome();		//Welcome Method calls login, sign up, retrieve password methods
								//Login Method calls order, delivery and calorie count methods
	
		}
		catch (IOException e)
		{	System.out.println("Unable to load data, error: " + e.getMessage());}
		catch (ClassNotFoundException e)
		{	System.out.println("Unable to locate driver, error: " + e.getMessage());}
		catch (SQLException e)
		{	System.out.println("Unable to access database: " + e.getMessage());}
		
	}
	
}