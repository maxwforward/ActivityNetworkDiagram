//###############################################################################################################################################################################
//  Remove Activity of Activity Diagram Application
//	Max Forward
//###############################################################################################################################################################################

// Imports
//===============================================================================================================================================================================
import javax.swing.*; // the * means we are importing all the classes inside the Swing package


// Remove Activity GUI Class
//===============================================================================================================================================================================
public class RemoveActivity
{
	// Create panel to place inside the dialog box
	JPanel removeActivityPanel = new JPanel(); // new JPanel object called "removeActivityPanel"
	
	// Create a label to give user instructions
	JLabel selectActivityLabel = new JLabel("Please select activity to remove:"); // new JLabel called "selectActivityLabel"
	
	// Create a variable to store user option from confirm dialog box buttons
	int removeOption; // "removeOption" holds the value of the user clicking "SAVE" or "CANCEL"
	
	// Create a variable to store user choice from combo box
	String removeChoice; // "removeChoice" stores the name of the activity selected from the combo box
	
	// Create a variable to store the removed activity
	Activity removedActivity;
	
	
	// Method that displays the Remove Activity Dialog Box and saves the removed Activity and returns a boolean value of result
	//===========================================================================================================================================================================
	public boolean displayRemoveActivityDialog(ActivityList list) // takes in an ActivityList "list" as parameter
	{
		// Create a boolean variable to store the users decision regarding whether or not an activity was removed
		boolean activityWasRemoved;
		
		// Turn ActivityList "list" into a string array so that we can display the activities as choices in our combo box
		String[] removeableActivityChoices = list.changeToArray(); // creates a string array from from Activity names in "list" and saves it as "removeableActivityChoices"
		
		// Create a combo box for user choice
		JComboBox removableActivitiesComboBox = new JComboBox(removeableActivityChoices); // New JComboBox containing the elements of the array "removableActivityChoices"
			
		// Add components to the panel
		removeActivityPanel.add(selectActivityLabel); // add JLabel "selectActivityLabel" to JPanel "removeActivityPanel"
		removeActivityPanel.add(removableActivitiesComboBox); // add JComboBox "removableActivitiesComboBox" to JPanel "removeActivityPanel"
			
		// Display the panel in a pop up dialog box (JOptionPane)
		//***********************************************************************************************************************************************************************
		/* "showConfirmDialog" means our JOptionPane is asking for the user to confirm what is in the dialog box
		 * "null" means the dialog box is displayed with a default frame
		 * "removeActivityPanel" is the object being displayed in the dialog box
		 *  "Remove Activity" is the title of the dialog box
		 *  "JOptionPane.OK_CANCEL_OPTION" is the option type for the dialog box.  In this case we want an "OK" and "CANCEL" buttons as user options */
		removeOption = JOptionPane.showConfirmDialog(null, removeActivityPanel, "Remove Activity", JOptionPane.OK_CANCEL_OPTION); // save user option in "removeOption"
			
		
		// If User clicks "OK" save the selected Activity to remove it
		//***********************************************************************************************************************************************************************
		if (removeOption == JOptionPane.OK_OPTION) // if "removeOption" is OK_OPTION (user clicks "OK" button)
		{
			// Save choice from combo box
			removeChoice = (String) removableActivitiesComboBox.getSelectedItem(); // Get selected item from combo box, cast it as a String, and save in "removeChoice"
				
			// Activity is removed
			activityWasRemoved = true; // set "activityWasRemoved" to true
		}
		
		
		// If User clicks "CANCEL" don't remove an Activity
		//***********************************************************************************************************************************************************************
		else // if "removeOption" is CANCEL_OPTION (user clicks "CANCEL" button)
		{
			// Activity is not removed
			activityWasRemoved = false; // set "activityWasRemoved" to false
		}
			
		// Return the boolean variable representing whether of not the activity was removed
		return activityWasRemoved;
	}
	
	
	// Method that gets the removed Activity and returns it
	//===========================================================================================================================================================================
	public Activity getRemovedActivity(ActivityList list) // takes in an ActivityList "list" as parameter
	{
		// Find the Activity to be removed from "list" and save it
		for (int i = 0; i < list.sizeOfActivityList(); i++) // Use "i" to check each index in "list"
		{
			// Save the Activity from "list" at index "i"
			Activity inspectedActivity = list.getActivityFromActivityList(i); // get the Activity from "list" at index "i" and store it as an Activity in "inspectedActivity"
			
			// If the Activity from "list" at index "i" is the same Activity selected from the combo box then save it 
			if (inspectedActivity.getActivityName() == removeChoice) // if the name of "inspectedActivity" is equal to "removeChoice"
			{
				// Save the Activity from "list" at index "i"
				removedActivity =  inspectedActivity; // Save "inspectedActivity" in "removedActivity"
			}
		}
		
		// Return the removed activity
		return removedActivity;
	}
}