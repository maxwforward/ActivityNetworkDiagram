//###############################################################################################################################################################################
//  List of Activities Object
//	Max Forward
//###############################################################################################################################################################################

// Imports
//===============================================================================================================================================================================
import java.util.ArrayList; // Import Package for Array Lists


// Class for ActivityList object
//===============================================================================================================================================================================
public class ActivityList 
{
	// Create an array list of activities
	public ArrayList<Activity> listOfActivities = new ArrayList<Activity>(); // new ArrayList of Activity objects called "listOfActivities"
	
	// Initialize an array list of Strings to contain the names of Activities on the path in reverse order
	public ArrayList<String> reversePath = new ArrayList<String>(); // new ArrayList of Strings called "reversePath"
	
	// Initialize an integer for the total duration of the path
	public int totalDuration; // new integer called "totalDuration"

	
	// Method that adds an Activity to the ArrayList "listOfActivities"
	//===========================================================================================================================================================================
	public void addActivityToList(Activity activityToBeAdded) // takes in Activity "activityToBeAdded" as parameter
	{
		// Add the activity to the array list
		listOfActivities.add(activityToBeAdded); // add Activity "activityToBeAdded" to ArrayList "listOfActivities"
	}
	
	
	// Method that removes an Activity from the ArrayList "listOfActivities"
	//===========================================================================================================================================================================
	public void removeActivityFromList(Activity activityToBeRemoved) // takes in Activity "activityToBeRemoved" as parameter
	{
		// Remove the activity from the array list
		listOfActivities.remove(activityToBeRemoved); // remove Activity "activityToBeRemoved" from ArrayList "listOfActivities"
	}
	
	
	// Method that returns the size of the ArrayList "listOfActivities"
	//===========================================================================================================================================================================
	public int sizeOfActivityList() // returns an integer
	{
		// Return the size of the list
		return listOfActivities.size(); // listOfActivities.size() returns the size of ArrayList "listOfActivities"
	}
	
	
	// Method that turns the array list into a string array
	//===========================================================================================================================================================================
	public String[] changeToArray() // returns a String array of Activity names
	{
		// If the ArrayList of ActivityList is empty return a String array with no Activity names
		if (listOfActivities.isEmpty() == true) // If "listOfActivities" is empty
		{
			// Create a String Array containing only "None"
			String[] emptyArray = new String[] {"None"}; // new String array called "emptyArray" containing only the String "None"
				
			// Return the String array
			return emptyArray;
		}
		// If the ArrayList of ActivityList is not empty return a String array containing names of Activities in this ActivityList
		else  // If the ArrayList of this ActivityList object is not empty
		{
			// Create a String array of Activity names
			String arrayOfActivityNames[] = new String[listOfActivities.size()]; // String array "arrayOfActivityNames" is the size of ArrayList "listOfActivities"
			
			// Fill the array with Activity names
			for (int i = 0; i < listOfActivities.size(); i++) // Use "i" to check each index in "listOfActivities"
			{
				// Save the Activity from "listOfActivities" at index "i"
				Activity activityFromList = listOfActivities.get(i); // get the Activity from "listOfActivities" at index "i" and store it in "activityFromList"

				// Store the Activity from "listOfActivities" at index "i" inside the String array at index "i"
				arrayOfActivityNames[i] = activityFromList.getActivityName(); // get the name of Activity "activityFromList" and store it in "arrayOfActivityNames" at index "i"
			}
		
			// Return the array of Activity names
			return arrayOfActivityNames; // return String array "arrayOfActivityNames"
		}
	}
	
	
	// Create a method to get an Activity from the ArrayList of "listOfActivities" at a specific index
	//===========================================================================================================================================================================
	public Activity getActivityFromActivityList(int index) // takes in an integer "index" as parameter
	{
		// Return the Activity at the specified index
		return listOfActivities.get(index); // Gets element of "listOfActivities" at index "index" and returns it
	}
	
	
	// Method that clears the ArrayList "listOfActivities"
	//===========================================================================================================================================================================
	public void clearList()
	{
		// Clear ArrayList
		listOfActivities.clear(); // clears ArrayList "listOfActivities"
	}
	
	
	// Method that creates a string of all the paths within "listOfActivities" to output in text area on the MainAppFrame
	//===========================================================================================================================================================================
	public String createOutputString() // returns a string
	{
		// Initialize an empty String
		String outputString = ""; // new String called "outputString" containing ""
		
		// Create a variable to store the last Activity in the path
		Activity lastActivity = null; // initialize it as null so our return statement works
		
		
		// Search for the last Activity in a path (Activity which no other Activities are dependent on) and create a string of the rest of the path
		for (int i = 0; i < listOfActivities.size(); i++) // Use "i" to check each index in "listOfActivities"
		{
			// Clear out the ArrayList containing the path so that we can rebuild it
			reversePath.clear(); // clears out all the elements in "reversePath"
			
			// Reset total duration so we can recalculate it
			totalDuration = 0; // value of "totalDuration" is 0
			
			// Create a boolean variable to store whether or not an Activity is last in the path
			boolean activityIsLast = true; // initialize "activityIsLast" as true until we find evidence to make it false
			
			// Save the Activity from "listOfActivities" at index "i"
			Activity activityAtIndexi = listOfActivities.get(i); // get Activity from "listOfActivities" at index "i" and save it in "activityAtIndexi"
			
			
			// Search the dependency list of each Activity to see if it contains  the Activity from "listOfActivities" at index "i"
			for (int j = 0; j < listOfActivities.size(); j++) // Use "j" to check each index in "listOfActivities"
			{
				// Save the Activity from "listOfActivities" at index "j"
				Activity activityAtIndexj = listOfActivities.get(j); // get Activity from "listOfActivities" at index "j" and save it in "activityAtIndexj"
				
				// If the Activity from "listOfActivities" at index "j" contains the Activity from "listOfActivities" at index "i" then the Activity is not last
				if (activityAtIndexj.dependencyListContains(activityAtIndexi) == true) // if "activityAtIndexj" contains "activityAtIndexi" in it's dependency list
				{
					// Activity is not last
					activityIsLast = false;
				}
			}
			
			
			// If the Activity from "listOfActivities" at index "i" is the last Activity on the path, save it
			if (activityIsLast == true) // if "activityIsLast" is true
			{	
				// Save the Activity as the last Activity
				lastActivity = activityAtIndexi; // Save "activityAtIndexi" in "lastActivity"
				
				// Create A DependencyList variable to store the DependencyList of the last Activity
				DependencyList lastActivityDependencies = lastActivity.getDependency(); // get the DependencyList from "lastActivity" and save it in "lastActivityDependencies"
				
				
				// If the last Activity has more than one direct dependency then build the path for each and add to the output string
				if (lastActivityDependencies.sizeOfDependencyList() > 1) // if the size of "lastActivityDependencies" is greater than 1
				{
					
					// Create a paths for each of the direct dependencies and add them to the output string
					for (int l = 0; l < lastActivityDependencies.sizeOfDependencyList(); l++) // Use "l" to check each index in "lastActivityDependencies"
					{
						// Clear out the ArrayList containing the path so that we can rebuild it
						reversePath.clear(); // clears out all the elements in "reversePath"
						
						// Reset total duration so we can recalculate it
						totalDuration = 0; // value of "totalDuration" is 0
						
						// Fill the reverse array with a path from each dependency of the last Activity
						this.fillReversePathArrayList(lastActivity, l);	
						
						// Add the Activity names from the "reversePath" ArrayList to the output string
						for(int k = (reversePath.size() - 1); k >= 0; k--) // Use "k" to check each index in "reversePath"
						{  
							// If we are adding the first Activity to the output string don't place " -> " in front of the Activity name
							if(k == (reversePath.size() - 1)) // if "k" is equal to the value of (reversePath.size() - 1)
							{
								// Add Activity name to the String
								outputString += reversePath.get(k); // get the Activity from "reversePath" at index "k" and add it to "outputString"
							}
							// If we are adding any other Activity besides the first one place " -> " in front of the Activity name
							else // if "k" is not equal to the value of (reversePath.size() - 1)
							{
								// Add Activity to output string with an arrow in front
								outputString += " -> " + reversePath.get(k); // get the Activity from "reversePath" at index "k", add add it to the output string with an arrow
							}
						}
							
						// Add the total duration at the end of the output string
						outputString += ": Duration = " + totalDuration; // Add the String ": Duration = " and the integer value "totalDuration" to the String "outputString"
							
						// Add a newline
						outputString += "\n"; // "\n" makes a new line
					}
				}
				
				
				// If the last Activity has only one or no direct dependency
				else // if the size of "lastActivityDependencies" is less than or equal to 1
				{
					// Fill ArrayList "reversePath" of this ActivityList object with the names of all the Activities in "listOfActivities"
					this.fillReversePathArrayList(lastActivity, 0);
						
					// Add the Activity names from the "reversePath" ArrayList to the output string
					for(int k = (reversePath.size() - 1); k >= 0; k--) // Use "k" to check each index in "reversePath"
					{   
						// If we are adding the first Activity to the output string don't place " -> " in front of the Activity name
						if(k == (reversePath.size() - 1)) // if "k" is equal to the value of (reversePath.size() - 1)
						{
							// Add Activity name to the String
							outputString += reversePath.get(k); // get the Activity from "reversePath" at index "k" and add it to "outputString"
						}
						// If we are adding any other Activity besides the first one place " -> " in front of the Activity name
						else // if "k" is not equal to the value of (reversePath.size() - 1)
						{
							// Add Activity to output string with an arrow in front
							outputString += " -> " + reversePath.get(k); // get the Activity from "reversePath" at index "k", add " - > " to the front and add it to "outputString"
						}
					}
						
					// Add the total duration at the end of the output string
					outputString += ": Duration = " + totalDuration; // Add the String ": Duration = " and the integer value "totalDuration" to the String "outputString"
						
					// Add a newline
					outputString += "\n"; // "\n" makes a new line
				}
			}
		}
		
		// Return the output string
		return outputString; // return String "outputString"
	}
	
	
	// Recursive Method that fills in the "reversePath" ArrayList with Activity names in "listOfActivities" going in reverse order from Activity in parameter
	//===========================================================================================================================================================================
	public void fillReversePathArrayList(Activity lastActivity, int index) // takes in an Activity "lastActivity" and integer "index" as parameter
	{
		// Add the name of the Activity "lastActivity" to the "reversePath" ArrayList
		reversePath.add(lastActivity.getActivityName()); // add the activity name of "lastActivity" to String ArrayList "reversePath"
					
		// increment the total duration
		totalDuration += lastActivity.getDuration(); // add the duration of "lastActivity" to "totalDuration"
		
		// if "lastActivity" has a dependency, save the dependent Activity and repeat this method for that Activity
		if (lastActivity.hasDependencies() == true) // if "lastActivity" has dependencies
		{
			// Create a new DependencyList variable to store the DependencyList of our parameter Activity
			DependencyList dependentActivities = lastActivity.getDependency(); // get the DependencyList from "lastActivity" and store it in "dependentActivities"
			
			// Create a new Activity variable to store the Activity from the DependencyList
			Activity nextActivity = dependentActivities.getActivityFromDependencyList(index); // get Activity from "dependentActivities" at "index" and save in "nextActivity"
			
			// Execute this method again with "nextActivity"
			fillReversePathArrayList(nextActivity, 0);
		}
		// if "lastActivity" has no dependencies return 
		else // if "lastActivity" has no dependencies
		{
			// Exit this method
			return;
		}
	}
}