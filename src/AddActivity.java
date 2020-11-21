//###############################################################################################################################################################################
//  Add Activity for Activity Diagram Application
//	Max Forward
//###############################################################################################################################################################################

// Imports
//===============================================================================================================================================================================
// Import Java package for GUI
import javax.swing.*; // the * means we are importing all the classes inside the Swing package 
//Import Java packages for action listener and events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Add Activity Class
//===============================================================================================================================================================================
public class AddActivity
{
	// Create panel to place inside the dialog box
	JPanel addActivityPanel = new JPanel(); // new JPanel object called "addActivityPanel"
	
	// Create labels to specify user input for each text field and content of text area
	JLabel activityNameLabel = new JLabel("Activity Name:"); // new JLabel called "activityNameLabel" containing the the message "Activity Name:"
	JLabel durationLabel = new JLabel("Duration:"); // new JLabel called "durationLabel" containing the the message "Duration:"
	JLabel directDependencyLabel = new JLabel("Direct Dependency:"); // new JLabel called "directDependencyLabel" containing the the message "Direct Dependency:"
	JLabel currentDependenciesLabel = new JLabel("This activity is dependent on:"); // new JLabel called "currentDependenciesLabel" containing the the message in quotes
	
	// Create text fields for user input
	JTextField activityNameInput = new JTextField(10); // Creates a new JTextField called "activityNameInput" with 10 columns
	JTextField durationInput = new JTextField(10); // Creates a new JTextField called "durationInput" with 10 columns
	
	// Create a JButton for adding direct dependencies
	JButton plusButton = new JButton("+"); // Creates a new JButton object called "plusButton" containing the message "+" on it
	
	// Create a text area to display added dependencies
	JTextArea addedDependencies = new JTextArea("", 5, 20); // new JTextArea called "addedDependencies" with 5 rows and 20 columns
	
	// Create a scroll pane for text area "addedDependencies"
	JScrollPane addedDependenciesWithScroll = new JScrollPane(addedDependencies, // new JScrollPane that contains the JTextArea "addedDependencies"
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // "JScrollPane.VERTICAL_SCROLLBAR_ALWAYS" means the vertical scroll bar is always displayed
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // "JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED" means the horizontal scroll bar is displayed when needed
	
	// Create a variable to store user choice from confirm dialog box buttons
	int addOption; // "addOption" holds the value of the user clicking "SAVE" or "CANCEL"
	
	// Create a variable to store user input for Activity Name
	String name;
	
	// Create a variable to store user input for duration
	String duration;
	
	// Create a new DependencyList to store the dependent activities of the added activity
	DependencyList listOfDependencies = new DependencyList(); // new DependencyList called "listOfDependencies"
	
	// Create a variable to store the added activity
	Activity addedActivity;
	
	
	// Method that builds the layout of the Add Activity panel then displays the dialog box and processes user input and returns a boolean value of result
	//===========================================================================================================================================================================
	public boolean displayAddActivityDialog(ActivityList list) // takes in an ActivityList "list" as parameter
	{	
		// Turn ActivityList "list" into a string array so that we can display the activities as choices in our combo box
		String[] directDependencyChoices = list.changeToArray(); // creates a string array from Activity names in "list" and saves it as "directDependencyChoices"
						
		// Create a combo box for user choice
		JComboBox dependencyComboBox = new JComboBox(directDependencyChoices); // Creates a new JComboBox containing the elements of the array "directDependencyChoices"
		
		// Create a new layout for the panel
		GroupLayout addActivityLayout = new GroupLayout(addActivityPanel); // Creates a new group layout called "addActivityLayout" associated with "addActivtyPanel"
		
		
		// Create horizontal group for layout "addActivityLayout" and add components
		//***********************************************************************************************************************************************************************
		addActivityLayout.setHorizontalGroup // Set the group that positions components along the horizontal axis
		(
			addActivityLayout.createParallelGroup()// Create a group of components that are placed vertically parallel
				// 1st component in parallel group along the vertical axis
				.addGroup(addActivityLayout.createSequentialGroup() // Create a group of components that are placed sequentially (one after the other)
						// 1st component in sequential group along the horizontal axis 
						.addGroup(addActivityLayout.createParallelGroup() // Create and add a group of vertically parallel components
								.addComponent(activityNameLabel) // add "activityNameLabel" to the parallel group within the sequential group along the horizontal axis
								.addComponent(durationLabel) // add "durationLabel" to the parallel group within the sequential group along the horizontal axis
								.addComponent(directDependencyLabel)) // add "directDependencyLabel" to the parallel group within the sequential group along horizontal axis
						// 2nd Component in sequential group along the horizontal axis
						.addGroup(addActivityLayout.createParallelGroup() // Create and add a group of vertically parallel components
								.addComponent(activityNameInput) // add "activityNameInput" to the parallel group within the sequential group along the horizontal axis
								.addComponent(durationInput) // add "durationInput" to the parallel group within the sequential group along the horizontal axis
								.addComponent(dependencyComboBox)) // add "dependencyComboBox" to the parallel group within the sequential group along the horizontal axis
						// 3rd Component in sequential group along the horizontal axis
						.addComponent(plusButton)) // add "plusButton" to the sequential group along the horizontal axis
				// 2nd component in parallel group along the vertical axis
				.addComponent(currentDependenciesLabel) // add "currentDependenciesLabel" to the parallel group along the vertical axis
				.addComponent(addedDependenciesWithScroll) // add "addedDependenciesWithScroll" to the parallel group along the vertical axis
		);
		
		
		// Create horizontal group for layout "addActivityLayout" and add components
		//***********************************************************************************************************************************************************************
		addActivityLayout.setVerticalGroup // Sets the group that positions components along the vertical axis
		(
			addActivityLayout.createSequentialGroup() // Create a group of components that are placed sequentially (one after the other)
				// 1st component in sequential group along the vertical axis 
				.addGroup(addActivityLayout.createParallelGroup() // Create and add a group of horizontally parallel components
						.addComponent(activityNameLabel) // add "activityNameLabel" to the parallel group within the sequential group along the vertical axis
						.addComponent(activityNameInput)) // add "activityNameInput" to the parallel group within the sequential group along the vertical axis
				// 2nd component in sequential group along the vertical axis 
				.addGroup(addActivityLayout.createParallelGroup() // Create and add a group of horizontally parallel components
						.addComponent(durationLabel) // add "durationLabel" to the parallel group within the sequential group along the vertical axis
						.addComponent(durationInput)) // add "durationInput" to the parallel group within the sequential group along the vertical axis
				// 3rd component in sequential group along the vertical axis
				.addGroup(addActivityLayout.createParallelGroup() // Create and add a group of horizontally parallel components
						.addComponent(directDependencyLabel) // add "directDependencyLabel" to the parallel group within the sequential group along the vertical axis
						.addComponent(dependencyComboBox) // add "dependencyComboBox" to the parallel group within the sequential group along the vertical axis	
						.addComponent(plusButton)) // add "plusButton" to the parallel group within the sequential group along the vertical axis
				// 4th component in sequential group along the vertical axis 
				.addComponent(currentDependenciesLabel) // add "currentDependenciesLabel" to the sequential group along the vertical axis
				// 5th component in sequential group along the vertical axis 
				.addComponent(addedDependenciesWithScroll) // add "addedDependenciesWithScroll" to the sequential group along the vertical axis
		);
		
		// Set automatic gap creation between the components of the layout
		addActivityLayout.setAutoCreateGaps(true); // When true automatically adds gaps between components
		addActivityLayout.setAutoCreateContainerGaps(true); // When true automatically adds gaps between the edges of the container and the components
		
		// Set layout to the panel
		addActivityPanel.setLayout(addActivityLayout); // set the layout of "addActivityPanel" to the group layout "addActivityLayout"
		
		// Prevent the user from editing the text area
		addedDependencies.setEditable(false); // Set the JTextArea "addedDependencies" to not be editable
		
		
		// Add action listener to "plusButton" JButton and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		plusButton.addActionListener(new ActionListener() // add action listener to "plusButton" JButton
		{
			// This method is performed when the event is triggered (clicking "plusButton" button)
			public void actionPerformed(ActionEvent event)
			{
				// Get the selected item from the combo box, cast it as a string, and store it as a String variable
				String dependencyAddedToTextArea = (String) dependencyComboBox.getSelectedItem(); // store selected item from Combo Box in "dependencyAddedToTextArea"
				
				// Update the text area to show the added dependency
				addedDependencies.append(dependencyAddedToTextArea + "\n"); // Append String "dependencyAddedToTextArea" followed by a newline to "addedDependencies" JTextArea
				
				// Add the selected Activity from the Combo Box as a dependency of added activity
				for(int i = 0; i < list.sizeOfActivityList(); i++) // Use "i" to check each index in "list"
				{
					// Store the Activity from "list" at index "i"
					Activity activityFromList = list.getActivityFromActivityList(i); // get the Activity from "list" at index "i" and store it in "activityFromList"
					
					// If the Activity from "list" at index "i" is the same Activity appended to the text area then add it to the list of dependencies for the added Activity 
					if (dependencyAddedToTextArea == activityFromList.getActivityName()) // If "dependencyAddedToTextArea" is equal to the Name of Activity "activityFromList"
					{
						// Add the Activity from "list" at index "i" to the list of dependencies for newly added activity
						listOfDependencies.addActivityToDependencyList(activityFromList); // Add "activityFromList" to "listOfDependencies"
					}
				}
			}	
		});
		
		// Create a boolean variable to store the users decision regarding whether or not an activity was created
		boolean activityWasCreated;
		
		// Display the Add Activity panel in a pop up dialog box (JOptionPane)
		//***********************************************************************************************************************************************************************
		/* "showConfirmDialog" means our JOptionPane is asking for the user to confirm what is in the dialog box
		 * "null" means the dialog box is displayed with a default frame
		 * "addActivityPanel" is the object being displayed in the dialog box
		 * "Add Activity" is the title of the dialog box
		 * "JOptionPane.OK_CANCEL_OPTION" is the option type for the dialog box. In this case we want an "OK" and "CANCEL" buttons as user options */
		addOption = JOptionPane.showConfirmDialog(null, addActivityPanel, "Add Activity", JOptionPane.OK_CANCEL_OPTION); // save user option ("OK" or "CANCEL") in "addOption"
		
		
		// If User clicks "OK" Create and add an Activity from user input
		//***********************************************************************************************************************************************************************
		if (addOption == JOptionPane.OK_OPTION) // if "addOption" is OK_OPTION (user clicks "OK" button)
		{
			// Save user input from text fields into variables
			name = activityNameInput.getText(); // Get text from "activityNameInput" JTextField and save as string in "name"
			duration = durationInput.getText(); // Get text from "durationInput" JTextField and save as string in "duration"
			
			// If user does not enter an integer for duration display an error message
			//*******************************************************************************************************************************************************************
			// Try to find an integer for duration
			try // Try this...
			{
				// Analyze the String "duration" and look for an integer
				Integer.parseInt(duration); // parse "duration" as an integer
			}
			// Catch if we cannot find an integer in "duration"
			catch(NumberFormatException durationNotInteger) // Catch a NumberFormatException (duration cannot be parsed as an integer)
			{
				// Show a message dialog box to display an error message
				JOptionPane.showMessageDialog(null, "Please enter an integer for duration", "ERROR", JOptionPane.ERROR_MESSAGE); // The type of JOptionPane is an "ERROR_MESSAGE"
				
				// Return to the main frame
				return false; // return false if error occurs (activity is not created)
			} 
			
			// Create new Activity object from user input ("name", "duration", and "listOfDependencies")
			addedActivity = new Activity(name, Integer.parseInt(duration), listOfDependencies); // parse "duration" in as an integer (convert "duration" to an integer)
			
			// Activity is created
			activityWasCreated = true; // set "activityWasCreated" to true
		}
		
		
		// If User clicks "CANCEL" don't create an Activity and return to main frame
		//***********************************************************************************************************************************************************************
		else // if "addOption" is CANCEL_OPTION (user clicks "CANCEL" button)
		{
			// Activity is not created
			activityWasCreated = false; // set "activityWasCreated" to false
		}
		
		// Return the boolean variable representing whether of not the activity was created
		return activityWasCreated;
	}
	
	
	// Method that gets the added Activity and returns it
	//===========================================================================================================================================================================	
	public Activity getAddedActivity() // Returns an Activity object
	{
		// Return the added activity
		return addedActivity; // return Activity "addedActivity"
	}
}