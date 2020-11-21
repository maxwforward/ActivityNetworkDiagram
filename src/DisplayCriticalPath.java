//################################################################################################################################################################################
//  Display the Critical Path(s) of the Activity Diagram Application
// 	Max Forward
//################################################################################################################################################################################

// Imports
//================================================================================================================================================================================
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


// Display Critical Path Class
//================================================================================================================================================================================
public class DisplayCriticalPath 
{
	// Create a panel to place inside the dialog box
	JPanel criticalPathPanel = new JPanel(new BorderLayout()); // new JPanel object called "criticalPathPanel" that has a BorderLayout
	
	// Create label to give user instructions
	JLabel criticalPathLabel = new JLabel("The critical path:"); // new JLabel called "criticalPathLabel"
	
	// Create a text area to display the critical path
	JTextArea criticalPathTextArea = new JTextArea("", 5, 30); // new JTextArea called "criticalPathTextArea" with 5 rows and 30 columns
	
	// Initialize an array list of Strings to contain the critical path
	public ArrayList<String> criticalPath = new ArrayList<String>(); // new ArrayList of Strings called "criticalPath"
		
	// Initialize an integer for the duration of the critical path
	public int criticalPathDuration; // new integer called "criticalPathDuration"
	

	// Method that displays the Display Critical Path window
	//============================================================================================================================================================================
	public void displayCriticalPath(ActivityList list) // takes in an ActivityList "list" as parameter
	{
		String outputString = "";
		
		// Add components to the panel
		criticalPathPanel.add(criticalPathLabel, BorderLayout.NORTH); // add "criticalPathLabel" to "criticalPathPanel" in the north position of the BorderLayout
		criticalPathPanel.add(criticalPathTextArea, BorderLayout.SOUTH); // add "criticalPathTextArea" to "criticalPathPanel" in the south position of the BorderLayout
		
		Activity longestActivity = null;
		
		// Search for the last Activity in the path (Activity which no other Activities are dependent on)
		for (int i = 0; i < list.sizeOfActivityList(); i++) // Use "i" to check each index in "list"
		{	
			// Create a boolean variable to store whether or not an Activity is last in the path
			boolean activityIsLast = true; // initialize "activityIsLast" as true until we find evidence to make it false
			
			// Save the Activity from "list" at index "i"
			Activity activityAtIndexi = list.getActivityFromActivityList(i); // get Activity from "list" at index "i" and save it in "activityAtIndexi"
				
			// Search the dependency list of each Activity to see if it contains the Activity from "list" at index "i"
			for (int j = 0; j < list.sizeOfActivityList(); j++) // Use "j" to check each index in "list"
			{
				// Save the Activity from "list" at index "j"
				Activity activityAtIndexj = list.getActivityFromActivityList(j); // get Activity from "list" at index "j" and save it in "activityAtIndexj"
				
				// If the Activity from "list" at index "j" contains the Activity from "list" at index "i" then the Activity is not last
				if (activityAtIndexj.dependencyListContains(activityAtIndexi) == true) // if "activityAtIndexj" contains "activityAtIndexi" in it's dependency list
				{
					// Activity is not last
					activityIsLast = false;
				}
			}
			
			if (activityIsLast == true)
			{
				if (longestActivity == null)
				{
					longestActivity = activityAtIndexi;
				}
				if (activityAtIndexi.getDuration() > longestActivity.getDuration())
				{
					longestActivity = activityAtIndexi;
				}
			}
		}
		
	
		calculateCriticalPath(longestActivity);
		
		for(int k = (criticalPath.size() - 1); k >= 0; k--)
		{   
			if(k == (criticalPath.size() - 1)) 
			{
				outputString += criticalPath.get(k); 
			}
			else
			{
				outputString += " -> " + criticalPath.get(k);
			}
		}
			
		outputString += ": Duration = " + criticalPathDuration;
		
		criticalPathTextArea.append(outputString + "\n");
				
		// Display the panel in a pop up dialog box (JOptionPane)
		//************************************************************************************************************************************************************************
		/* "showMessageDialog" means our JOptionPane is telling the user about something
		 * "null" means the dialog box is displayed with a default frame
		 * "criticalPathPanel" is the object being displayed in the dialog box
		 *  "Critical Path" is the title of the dialog box
		 *  "JOptionPane.INFORMATION_MESSAGE" is the option type for the dialog box.  In this case we want to show the user a message to inform them */
		JOptionPane.showMessageDialog(null, criticalPathPanel, "Critical Path", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void calculateCriticalPath(Activity lastActivity)
	{
		// Add the name of the Activity "lastActivity" to the "criticalPath" ArrayList
		criticalPath.add(lastActivity.getActivityName()); // add the activity name of "lastActivity" to String ArrayList "criticalPath"
							
		// increment the total duration
		criticalPathDuration += lastActivity.getDuration(); // add the duration of "lastActivity" to "criticalPathDuration"
				
		// if "lastActivity" has a dependency, save the dependent Activity and repeat this method for that Activity
		if (lastActivity.hasDependencies() == true) // if "lastActivity" has dependencies
		{
			// Create a new DependencyList variable to store the DependencyList of our parameter Activity
			DependencyList dependentActivities = lastActivity.getDependency(); // get the DependencyList from "lastActivity" and store it in "dependencActivities"
				
			Activity activityA = dependentActivities.getActivityFromDependencyList(0);
			
			Activity longestActivity = activityA;
				
			for (int j = 1; j < dependentActivities.sizeOfDependencyList(); j++)
			{
				Activity activityB = dependentActivities.getActivityFromDependencyList(j);
					
				if (activityB.getDuration() > activityA.getDuration())
				{
					longestActivity = activityB;
				}
			}
			
			calculateCriticalPath(longestActivity);
		}
		else
		{
			return;
		}
	}
}