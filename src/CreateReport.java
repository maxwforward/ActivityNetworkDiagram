//################################################################################################################################################################################
//  Create Report of the Activity Diagram Application
// 	Max Forward
//################################################################################################################################################################################

// Imports
//================================================================================================================================================================================
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


// Create Report Class
//================================================================================================================================================================================
public class CreateReport 
{
	ArrayList<Activity> reportList = new ArrayList<Activity>();
	
	String reportName = JOptionPane.showInputDialog(null, "Enter the name for the report", "Create Report",JOptionPane.INFORMATION_MESSAGE);
		
	public String timeDate() 
	{
		Date d = new Date ();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss"); //creating date format 
		String s = sdf.format(d);//creation of string with our intended date format
		return s; //will print to file	   
	}
		   
	public boolean reportCreated(ActivityList list) 
	{
		try 
		{
			PrintWriter outputStream = new PrintWriter(reportName);//the text file will be named fileName
			outputStream.println(timeDate());
			outputStream.println("Name of the report: " + reportName); //call timeDate and write it to file
			// outputStream.println(timeDate());
			
			String listofActivities = "";
			
			String[] listOfActs = list.changeToArray();
			
			outputStream.println("List Of Activities: ");
			
			for (int i = 0; i < list.sizeOfActivityList(); i++)
			{
				outputStream.println(listOfActs[i] + ": Duration = " + (list.getActivityFromActivityList(i)).getDuration());
			}
			
			
			
		
			outputStream.println("Paths: ");
			
			outputStream.println(list.createOutputString());
			
			//must add alphabetical printer here 
				   	  
			outputStream.close();
		} 
			
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();  	  
		}	
		   
		//need to change to boolean 
			  
		return true;
	}	
}