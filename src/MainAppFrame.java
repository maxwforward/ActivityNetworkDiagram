//###################################################################################################################
//	TITLE: 			MainAppFrame.java
//	DESCRIPTION: 	Main frame (starting view) GUI of ActivityNetworkDiagram
//	AUTHOR: 		Max Forward
//###################################################################################################################

// Imports
import javax.swing.*; // Import all the classes inside the Java Swing package (for GUI)
import java.awt.event.ActionListener; // Import the Java package for action listener
import java.awt.event.ActionEvent; // Import the Java package for events


//*******************************************************************************************************************
//	MainAppFrame (starting view) GUI class
//*******************************************************************************************************************
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
	
	
	//===============================================================================================================
	//	constructMenuBar method - Constructs the menu bar and adds action listener to each menu item
	//===============================================================================================================
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
		
		//-----------------------------------------------------------------------------------------------------------
		//	addActivity action listener
		//-----------------------------------------------------------------------------------------------------------
		addActivity.addActionListener(new ActionListener() // add ActionListener to "addActivity" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Add Activity")
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
		
		//-----------------------------------------------------------------------------------------------------------
		//	removeActivity action listener
		//-----------------------------------------------------------------------------------------------------------
		removeActivity.addActionListener(new ActionListener() // add ActionListener to "removeActivity" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Remove Activity")
			public void actionPerformed(ActionEvent event)
			{
				// Create a new RemoveActivity object in order to implement its methods
				RemoveActivity removeActivityObject = new RemoveActivity();
				
				// Create a variable to store whether or not an activity is removed/deleted
				boolean activityRemoved;
				
				// Display dialog box for "Remove Activity" and store the return value
				activityRemoved = removeActivityObject.displayRemoveActivityDialog(mainList);
				
				// If the user removed an activity...
				if (activityRemoved == true)
				{
					// Get the removed activity from the RemoveActivity object and store it  
					Activity activityToRemove = removeActivityObject.getRemovedActivity(mainList);
					
					// Remove the activity from the ActivityList object
					mainList.removeActivityFromList(activityToRemove);
					
					// Create a String containing the activities in the ActivityList object
					String outputText = mainList.createOutputString();
					
					// Update the text area to display the String
					listOfPaths.setText(outputText);
				}
			}
		});
		
		//-----------------------------------------------------------------------------------------------------------
		//	showCriticalPath action listener
		//-----------------------------------------------------------------------------------------------------------
		showCriticalPath.addActionListener(new ActionListener() // add ActionListener to "showCriticalPath" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Display Critical Path")
			public void actionPerformed(ActionEvent event)
			{
				// Create a new DisplayCriticalPath object in order to implement its methods
				DisplayCriticalPath displayCriticalPathObject = new DisplayCriticalPath();
				
				// Display dialog box for "Display Critical Path"
				displayCriticalPathObject.displayCriticalPath(mainList);
			}
		});
		
		//-----------------------------------------------------------------------------------------------------------
		//	editDuration action listener
		//-----------------------------------------------------------------------------------------------------------
		editDuration.addActionListener(new ActionListener() // add ActionListener to "editDuration" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Edit Duration")
			public void actionPerformed(ActionEvent event)
			{
				// Create a new EditDuration object in order to implement its methods
				EditDuration editDurationObject = new EditDuration();
				
				// Create a variable to store whether or not an activity duration is edited/changed
				boolean durationEdited;
				
				// Display dialog box for "Edit Duration" and store the return value
				durationEdited = editDurationObject.displayEditDurationDialog(mainList);
				
				// If the user edited an activity duration...
				if (durationEdited == true)
				{
					// Get the edited activity from the EditDuration object and store it
					Activity activityToEdit = editDurationObject.getEditedActivity(mainList);
					
					// Get the new duration from the EditDuration object and store it
					int durationEdit = editDurationObject.getEditDurationInput();
					
					// Remove the edited activity from the ActivityList object
					mainList.removeActivityFromList(activityToEdit);
					
					// Change the duration of the edited activity
					activityToEdit.editDuration(durationEdit);
					
					// Add the edited activity back to the ActivityList object
					mainList.addActivityToList(activityToEdit);
					
					// Create a String containing the activities in the ActivityList object
					String outputText = mainList.createOutputString();
					
					// Update the text area to display the String
					listOfPaths.setText(outputText);
				}
			}
		});
		
		//-----------------------------------------------------------------------------------------------------------
		//	createReport action listener
		//-----------------------------------------------------------------------------------------------------------
		createReport.addActionListener(new ActionListener() // add ActionListener to "createReport" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Create Report")
			public void actionPerformed(ActionEvent event)
			{
				// Create a new CreateReport object in order to implement its methods
				CreateReport createReportObject = new CreateReport();
				
				// Create a variable to store whether or not a report is created
				boolean reportWasCreated;
				
				// Display dialog box for "Create Report" and store the return value
				reportWasCreated = createReportObject.reportCreated(mainList);
			}
		});
		
		//-----------------------------------------------------------------------------------------------------------
		//	restart action listener
		//-----------------------------------------------------------------------------------------------------------
		restart.addActionListener(new ActionListener() // add ActionListener to "restart" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Restart")
			public void actionPerformed(ActionEvent event)
			{
				// Reset the text area
				listOfPaths.setText("No Activity Yet");
				
				// Remove every activity from the ActivityList object
				mainList.clearList();
			}
		});
		
		//-----------------------------------------------------------------------------------------------------------
		//	exit action listener
		//-----------------------------------------------------------------------------------------------------------
		exit.addActionListener(new ActionListener() // add ActionListener to "exit" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Exit")
			public void actionPerformed(ActionEvent event)
			{
				// End program
				System.exit(0);
			}
		});
		
		//-----------------------------------------------------------------------------------------------------------
		//	help action listener
		//-----------------------------------------------------------------------------------------------------------
		help.addActionListener(new ActionListener() // add ActionListener to "help" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "Help")
			public void actionPerformed(ActionEvent event)
			{
				// Display information message dialog box for "Help"
				JOptionPane.showMessageDialog(null, "Please refer to the user guide for help", "Help", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//-----------------------------------------------------------------------------------------------------------
		//	about action listener
		//-----------------------------------------------------------------------------------------------------------
		about.addActionListener(new ActionListener() // add ActionListener to "about" JMenuItem
		{
			// This method is performed when the event is triggered (clicking on "About")
			public void actionPerformed(ActionEvent event)
			{
				// Display information message dialog box for "About"
				JOptionPane.showMessageDialog(null, "Author: Max Forward", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	
	//===============================================================================================================
	//	MainAppFrame method - Constructor for MainAppFrame JFrame object
	//===============================================================================================================
	public MainAppFrame()
	{
		// Call constructor method of JFrame
		super("Activity Network Diagram"); // "Activity Network Diagram" is the title of the frame
		
		// Set the frame size
		setSize(500, 500);
		
		// Prevent user from resizing the frame
		setResizable(false);
		
		// Specify the action of the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits the program
		
		// Construct menu bar and add it to this frame
		this.constructMenuBar();
		
		// Add components to the panel
		mainPanel.add(listOfPathsWithScroll);
		mainPanel.add(instructions);
		
		// Add the panel to this frame
		this.add(mainPanel);
		
		// Prevent the user from editing the text area
		listOfPaths.setEditable(false);
		
		// Make frame visible
		setVisible(true);
	}
}