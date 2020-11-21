//###########################################################################################################
//	TITLE: 			MainAppFrame.java
//	DESCRIPTION: 	Main frame (starting view) GUI of ActivityNetworkDiagram
//	AUTHOR: 		Max Forward
//###########################################################################################################

// Imports
import javax.swing.*; // Import all the classes inside the Java Swing package (for GUI)
import java.awt.event.ActionListener; // Import the Java package for action listener
import java.awt.event.ActionEvent; // Import the Java package for events


//***********************************************************************************************************
//	MainAppFrame (starting view) GUI class
//***********************************************************************************************************
public class MainAppFrame extends JFrame // MainAppFrame is a JFrame, inherits features from JFrame class
{
	// Create a panel to place inside the frame
	JPanel mainPanel = new JPanel();
	
	// Create a text area with 25 rows and 43 columns to display output
	JTextArea listOfPaths = new JTextArea("No Activity Yet", 25, 43);
	
	// Create a scroll pane for the text area
	JScrollPane listOfPathsWithScroll = new JScrollPane(listOfPaths, // contains the JTextArea "listOfPaths"
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // vertical scroll bar is always displayed
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // horizontal scroll bar is displayed when needed
	
	// Create a label containing instructions for the user
	JLabel instructions = new JLabel("Press File and select an option to begin building network diagram");
	
	// Create new ActivityList object to store all the added activities
	ActivityList mainList = new ActivityList();
	
	
	//=======================================================================================================
	//	constructMenuBar method - Constructs the menu bar and adds action listener to each menu item
	//=======================================================================================================
	public void constructMenuBar()
	{
		// Create a menu bar to place at the top of the frame
		JMenuBar menuBar = new JMenuBar();
		
		// Create menus that will be placed on menu bar
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		// Create menu items that will be placed within the menus
		JMenuItem addActivity = new JMenuItem("Add Activity");
		JMenuItem removeActivity = new JMenuItem("Remove Activty");
		JMenuItem showCriticalPath = new JMenuItem("Display Critical Path");
		JMenuItem editDuration = new JMenuItem("Edit Duration");
		JMenuItem createReport = new JMenuItem("Create Report");
		JMenuItem restart = new JMenuItem("Restart");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem about = new JMenuItem("About");
		
		// Add the menu items to the menus
		fileMenu.add(addActivity);
		fileMenu.add(removeActivity);
		fileMenu.add(showCriticalPath);
		fileMenu.add(editDuration);
		fileMenu.add(createReport);
		fileMenu.add(restart);
		fileMenu.add(exit);
		helpMenu.add(help);
		helpMenu.add(about);
		
		// Add menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		// Add the menu bar to the frame
		this.setJMenuBar(menuBar); // Sets the JMenuBar for this JFrame to "menuBar"
		
		
		//---------------------------------------------------------------------------------------------------
		//	addActivity action listener
		//---------------------------------------------------------------------------------------------------
		addActivity.addActionListener(new ActionListener() // add ActionListener to "addActivity" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Add Activity" menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Create a new AddActivity object in order to implement its methods
				AddActivity addActivityObject = new AddActivity();
				
				// Create a variable to store whether or not an activity is added/created
				boolean activityCreated;
				
				// Display dialog box for "Add Activity" and store the return value
				activityCreated = addActivityObject.displayAddActivityDialog(mainList);
				
				// If the user added an activity...
				if (activityCreated == true)
				{
					// Get the new activity from the AddActivity object and store it
					Activity newActivity = addActivityObject.getAddedActivity();
					
					// Add the new activity to the ActivityList object
					mainList.addActivityToList(newActivity);
					
					// Create a String containing the activities in the ActivityList object
					String outputText = mainList.createOutputString();
					
					// Update the text area to display the String
					listOfPaths.setText(outputText);
				}
			}
		});
		
		
		// Add action listener to "removeActivity" menu item and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		removeActivity.addActionListener(new ActionListener() // add action listener to "removeActivity" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Create a new RemoveActivity in order to implement its methods
				RemoveActivity removeActivityObject = new RemoveActivity(); // new RemoveActivity object called "removeActivityObject"
				
				// Create a boolean variable to save whether or not an activity was removed by the RemoveActivity object
				boolean activityRemoved; // "activityRemoved" is a boolean that will be true if an Activity is removed/deleted and false if not
				
				// Display the Remove Activity dialog box and save the truth value of whether or not an activity was removed in "activityRemoved"
				activityRemoved = removeActivityObject.displayRemoveActivityDialog(mainList);// displays remove activity dialog box and returns true if Activity was removed
				
				// If the user removed an activity...
				if (activityRemoved == true) // if "activityRemoverd" is true 
				{
					// Get the Removed activity from the RemoveActivity object and save it.  Pass in the Activity List to identify the activity to remove
					Activity activityToRemove = removeActivityObject.getRemovedActivity(mainList); // Save the activity to remove from "mainList" in "activityToRemove
					
					// Remove the activity from the Activity List
					mainList.removeActivityFromList(activityToRemove); // removes "activityToRemove" from the Activity List "mainList"
					
					// Create a string containing the list of Activities in the Activity List to output in the text area
					String outputText = mainList.createOutputString(); // create an output string from the main list and save it in "outputText"
					
					// Update the text area to display new activity output
					listOfPaths.setText(outputText); // Set the text of the text area "listOfPaths" to the string "outputText"
				}
			}
		});
		
		
		// Add action listener to "showCriticalPath" menu item and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		showCriticalPath.addActionListener(new ActionListener() // add action listener to "showCriticalPath" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Create a new DisplayCriticalPath object in order to implement its methods
				DisplayCriticalPath displayCriticalPathObject = new DisplayCriticalPath(); // new DisplayCriticalPath object called "displayCriticalPathObject"
				
				// Display the Critical Path dialog box
				displayCriticalPathObject.displayCriticalPath(mainList); // displays critical path dialog box
			}
		});
		

		// Add action listener to "editDuration" menu item and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		editDuration.addActionListener(new ActionListener() // add action listener to "editDuration" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Create a new EditDuration object in order to implement its methods
				EditDuration editDurationObject = new EditDuration(); // new EditDuration object called "editDurationObject"
				
				// Create a boolean variable to save whether or not an activity duration was edited by the EditDuration object
				boolean durationEdited; // "durationEdited" is a boolean that will be true if an Activity's duration was edited and false if not
				
				// Display the Edit Duration dialog box and save the truth value of whether or not an activity's duration was edited in "durationEdited"
				durationEdited = editDurationObject.displayEditDurationDialog(mainList); // displays edit duration dialog box and returns true if Activity's duration was edited
				
				// If the user edited an activity duration...
				if (durationEdited == true) // if "durationEdited" is true
				{
					// Get edited Activity from the EditDuration object and save it
					Activity activityToEdit = editDurationObject.getEditedActivity(mainList); // get the edited activity from "editDurationObject" and save in "activityToEdit"
					
					// Get the duration input from the EditDuration object and save it
					int durationEdit = editDurationObject.getEditDurationInput(); // get the user input from "editDurationObject" and save it in "durationEdit"
					
					// Remove the edited activity from the Activity List
					mainList.removeActivityFromList(activityToEdit); // removes "activityToEdit" from the Activity List "mainList"
					
					// Edit the duration of the edited Activity
					activityToEdit.editDuration(durationEdit); // Edit the duration of "activityToEdit" to be the value of "durationEdit"
					
					// Add the edited activity back to the main Activity list
					mainList.addActivityToList(activityToEdit); // add activity "activityToEdit" to the array list "mainList"
					
					// Create a string containing the list of Activities in the Activity List to output in the text area
					String outputText = mainList.createOutputString(); // create an output string from the main list and save it in "outputText"
					
					// Update the text area to display new activity output
					listOfPaths.setText(outputText); // Set the text of the text area "listOfPaths" to the string "outputText"
				}
			}
		});
		
		
		// Add action listener to "createReport" menu item and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		createReport.addActionListener(new ActionListener() // add action listener to "createReport" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Create a new CreateReport object in order to implement its methods
				CreateReport createReportObject = new CreateReport(); // new CreateReport object called "CreateReportObject"
				
				// Create a boolean variable to save whether or not a report was created by the CreateReport object
				boolean reportWasCreated; // "reportWasCreated" is a boolean that will be true if a report was created and false if not
				
				// Display the Create Report dialog box and save the truth value of whether or not a report was created in "reportWasCreated"
				reportWasCreated = createReportObject.reportCreated(mainList); // displays create report dialog box and returns true if report was created
			}
		});		
		
		
		// Add action listener to "restart" menu item and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		restart.addActionListener(new ActionListener() // add action listener to "restart" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Restart Application
				listOfPaths.setText("No Activity Yet"); // reset the text area to "No Activity Yet"
				mainList.clearList(); // Delete every Activity from the activity list "mainList"
			}
		});
		
		
		// Add action listener to "exit" menu item and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		exit.addActionListener(new ActionListener() // add action listener to "exit" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Exit the application
				System.exit(0); // Ends the program
			}
		});
		
		
		// Add action listener to "help" menu item and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		help.addActionListener(new ActionListener() // add action listener to "help" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Display Help
				//---------------------------------------------------------------------------------------------------------------------------------------------------------------
				/* "showMessageDialog" means our JOptionPane is displaying a message to the user
				 * "null" means the dialog box is displayed with a default frame
				 * "Please refer to the user guide for help" is the message being displayed in the JOptionPane
				 * "Help" is the title of the JOptionPane
				 * "JOptionPane.INFORMATION_MESSAGE" is the type of message for the dialog box.  In this case we want an information message */
				JOptionPane.showMessageDialog(null, "Please refer to the user guide for help", "Help", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		// Add action listener to "about" menu item and specify action performed when clicked
		//***********************************************************************************************************************************************************************
		about.addActionListener(new ActionListener() // add action listener to "about" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on menu item)
			public void actionPerformed(ActionEvent event)
			{
				// Display About
				//---------------------------------------------------------------------------------------------------------------------------------------------------------------
				/* "showMessageDialog" means our JOptionPane is displaying a message to the user
				 * "null" means the dialog box is displayed with a default frame
				 * "Authors: Max Forward, Evan Shoup, Daniel Yllescas" is the message being displayed in the JOptionPane
				 * "About" is the title of the JOptionPane
				 * "JOptionPane.INFORMATION_MESSAGE" is the type of message for the dialog box.  In this case we want an information message */
				JOptionPane.showMessageDialog(null, "Author: Max Forward", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	
	// Constructor method for MainAppFrame JFrame object
	//===========================================================================================================================================================================
	public MainAppFrame() 
	{
		// Call constructor method of JFrame
		super("Network Diagram"); // "Network Diagram" is name of the frame
		
		// Set size of the frame
		setSize(500, 500); // 500 is the width and 500 is the height of the JFrame
		setResizable(false); // Prevents user from resizing the frame
		
		// Specify the action of the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits the program
		
		// Create and add menu bar to this frame
		this.constructMenuBar(); // calls the constructMenuBar() method from this class to give the frame a menu bar
		
		// Add components to the panel
		mainPanel.add(listOfPathsWithScroll); // add JScrollPane "listOfPathsWithScroll" with JTextArea "listOfPaths"
		mainPanel.add(instructions); // add JLabel "instructions" to JPanel
		
		// Add the panel to this frame
		this.add(mainPanel);
		
		// Prevent the user from editing the text area containing the Activity List paths
		listOfPaths.setEditable(false); // Set the JTextArea "listOfPaths" to not be editable
		
		// Make frame visible
		setVisible(true); // setVisible() controls whether a component and its children are displayed on the screen
	}
}