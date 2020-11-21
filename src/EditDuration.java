//################################################################################################################################################################################
//  Edit the Duration of Activities on the Activity Diagram Application
//	Max Forward
//################################################################################################################################################################################

// Imports
//================================================================================================================================================================================
// Import Java package for GUI
import javax.swing.*; // the * means we are importing all the classes inside the Swing package
// Import Java package for BorderLayout
import java.awt.BorderLayout;


// Edit Duration Class
//================================================================================================================================================================================
public class EditDuration 
{
	// Create a main panel to place inside the dialog box
	JPanel mainEditDurationPanel = new JPanel(new BorderLayout()); // new JPanel object called "mainEditDurationPanel" that has a BorderLayout
	
	// Create panels to place inside of main panel
	JPanel selectActivityPanel = new JPanel(); // new JPanel object called "selectActivityPanel"
	JPanel enterDurationPanel = new JPanel(); // new JPanel object called "enterDurationPanel"
	
	// Create labels to give user instructions
	JLabel selectActvityLabel = new JLabel("Please select activity to edit duration:"); // new JLabel called "selectActvityLabel"
	JLabel enterDurationLabel = new JLabel("Enter duration: "); // new JLabel called "enterDurationLabel"
	
	// Create text field for user input
	JTextField editDurationInputTextField = new JTextField(5); // Creates a new JTextField called "editDurationInputTextField" with 5 columns
	
	// Create String to store user input from text field
	String editDurationInput; // new String called "editDurationInput"
	
	// Create a variable to store user option from confirm dialog box buttons
	int editOption; // "editOption" holds the value of the user clicking "SAVE" or "CANCEL"
		
	// Create a variable to store user choice from combo box
	String editChoice; // "editChoice" stores the name of the activity selected from the combo box
		
	// Create a variable to store the ActivityList with duration edited
	Activity editedActivity;
	
	
	// Method that displays the Edit Duration Dialog Box and saves the edited Activity and returns a boolean value of result
	//============================================================================================================================================================================
	public boolean displayEditDurationDialog(ActivityList list) // takes in an ActivityList "list" as parameter
	{
		// Create a boolean variable to store the users decision regarding whether or not duration of Activity was edited
		boolean durationWasEdited;
		
		// Turn ActivityList "list" into a string array so that we can display the activities as choices in our combo box
		String[] editDurationChoices = list.changeToArray(); // creates a string array from from Activity names in "list" and saves it as "editDurationChoices"
				
		// Create a combo box for user choice
		JComboBox editDurationComboBox = new JComboBox(editDurationChoices); // New JComboBox containing the elements of the array "editDurationChoices"
		
		// Add components to the panels
		selectActivityPanel.add(selectActvityLabel); // add JLabel "editDurationLabel" to JPanel "selectActivityPanel"
		selectActivityPanel.add(editDurationComboBox); // add JComboBox "editDurationComboBox" to JPanel "selectActivityPanel"
		enterDurationPanel.add(enterDurationLabel); // add JLabel "enterDurationLabel" to JPanel "enterDurationPanel"
		enterDurationPanel.add(editDurationInputTextField); // add JTextField "editDurationInputTextField" to JPanel "enterDurationPanel"
		
		// Add the panels to the main panel
		mainEditDurationPanel.add(selectActivityPanel, BorderLayout.NORTH); // add "selectActivityPanel" to "mainEditDurationPanel" in the north position of the BorderLayout
		mainEditDurationPanel.add(enterDurationPanel, BorderLayout.SOUTH); // add "enterDurationPanel" to "mainEditDurationPanel" in the south position of the BorderLayout
		
		// Display the panel in a pop up dialog box (JOptionPane)
		//************************************************************************************************************************************************************************
		/* "showConfirmDialog" means our JOptionPane is asking for the user to confirm what is in the dialog box
		 * "null" means the dialog box is displayed with a default frame
		 * "editDurationPanel" is the object being displayed in the dialog box
		 *  "Edit Duration" is the title of the dialog box
		 *  "JOptionPane.OK_CANCEL_OPTION" is the option type for the dialog box.  In this case we want an "OK" and "CANCEL" buttons as user options */
		editOption = JOptionPane.showConfirmDialog(null, mainEditDurationPanel, "Edit Duration", JOptionPane.OK_CANCEL_OPTION); // save user option in "editOption"
		
		
		// If User clicks "OK" save the edited Activity and input
		//************************************************************************************************************************************************************************
		if (editOption == JOptionPane.OK_OPTION) // if "editOption" is OK_OPTION (user clicks "OK" button)
		{
			// Save choice from combo box
			editChoice = (String) editDurationComboBox.getSelectedItem(); // Get selected item from combo box, cast it as a String, and save in "editChoice"
			
			// Save user input from text field
			editDurationInput = editDurationInputTextField.getText(); // Get text from "editDurationInputTextField" JTextField and save as string in "editDurationInput"
			
			// If user does not enter an integer for duration display an error message
			//*******************************************************************************************************************************************************************
			// Try to find an integer for duration
			try // Try this...
			{
				// Analyze the String "editDurationInput" and look for an integer
				Integer.parseInt(editDurationInput); // parse "editDurationInput" as an integer
			}
			// Catch if we cannot find an integer in "editDurationInput"
			catch(NumberFormatException durationNotInteger) // Catch a NumberFormatException (duration cannot be parsed as an integer)
			{
				// Show a message dialog box to display an error message
				JOptionPane.showMessageDialog(null, "Please enter an integer for duration", "ERROR", JOptionPane.ERROR_MESSAGE); // The type of JOptionPane is an "ERROR_MESSAGE"
				
				// Return to the main frame
				return false; // return false if error occurs (activity is not created)
			} 
			
			// Duration is edited
			durationWasEdited = true; // set "durationWasEdited" to true
		}
		
		
		// If User clicks "CANCEL" don't edit the duration of an Activity
		//************************************************************************************************************************************************************************
		else // if "editOption" is CANCEL_OPTION (user clicks "CANCEL" button)
		{
			// Duration is not edited
			durationWasEdited = false; // set "durationWasEdited" to false
		}
		
		// Return the boolean variable representing whether of not the duration of an Activity was edited
		return durationWasEdited;
	}
	
	
	// Method that gets the edited Activity and returns it
	//===========================================================================================================================================================================
	public Activity getEditedActivity(ActivityList list) // takes in an ActivityList "list" as parameter
	{
		// Find the Activity to be removed from "list" and save it
		for (int i = 0; i < list.sizeOfActivityList(); i++) // Use "i" to check each index in "list"
		{
			// Save the Activity from "list" at index "i"
			Activity inspectedActivity = list.getActivityFromActivityList(i); // get the Activity from "list" at index "i" and store it as an Activity in "inspectedActivity"
				
			// If the Activity from "list" at index "i" is the same Activity selected from the combo box then save it 
			if (inspectedActivity.getActivityName() == editChoice) // if the name of "inspectedActivity" is equal to "editChoice"
			{
				// Save the Activity from "list" at index "i"
				editedActivity =  inspectedActivity; // Save "inspectedActivity" in "editedActivity"
			}
		}
			
		// Return the edited activity
		return editedActivity;
	}
	
	
	// Method that gets the users input for edit duration and returns it
	//===========================================================================================================================================================================
	public int getEditDurationInput()
	{
		// Turn input into an integer and return it
		return Integer.parseInt(editDurationInput); // parse "editDurationInput" as an integer and return it
	}
}