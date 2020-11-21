//################################################################################################################################################################################
//  Activity Object
//	Max Forward
//################################################################################################################################################################################


// Class for Activity object
//================================================================================================================================================================================
public class Activity
{
	// Create a string to store the activity name
	String activityName;
	
	// Create an integer to store the activity duration
	int activityDuration;
	
	// Create a DependencyList to store the list of Activities this Activity is dependent on
	DependencyList dependentActivities = new DependencyList(); // new DependencyList object called "dependentActivities"
	
	
	// Constructor method for Activity object
	//============================================================================================================================================================================
	public Activity(String name, int duration, DependencyList dependencies) // takes in a String "name", an integer "duration", and an ActivityList "dependencies" as parameters
	{
		// Store parameters in the variables for this Activity object
		activityName = name; // store parameter "name" into "activityName"
		activityDuration = duration; // store parameter "duration" into "activityDuration"
		dependentActivities = dependencies; // store parameter "dependencies" into "dependentActivities"
	}
	
	
	// Getter Method for Activity name
	//=======================================================================================================================================================+++==================
	public String getActivityName() // Returns the String "activityName" of this Activity object
	{
		// Return the Activity name
		return activityName; // returns String "activityName"
	}
	
	
	// Getter Method for Activity duration
	//=========================================================================================================================================================+++================
	public int getDuration() // Returns the integer "activityDuration" of this Activity object
	{
		// Return the Activity duration
		return activityDuration; // returns integer "activityDuration"
	}
	
	
	// Getter Method for Activity dependencies
	//=========================================================================================================================================================+++================
	public DependencyList getDependency() // Returns the list of Activities that this Activity object is dependent on
	{
		// Return the Activity dependencies
		return dependentActivities; // returns DependencyList "dependentActivities"
	}
	
	
	// Method for editing Activity Duration
	//=========================================================================================================================================================+++================
	public void editDuration(int newDuration) // Takes in integer "newDuration" as parameter
	{
		// Set duration to the new integer that's being passed in
		activityDuration = newDuration;
	}
	
	
	// Method that checks if Activity has dependencies
	//=========================================================================================================================================================+++================
	public boolean hasDependencies() // returns a boolean value of whether of not this Activity has dependencies
	{
		// If this Activity has dependencies return true
		if (dependentActivities.isDependencyListEmpty() == true) // if DependencyList "dependentActivities" is empty
		{
			// Return false
			return false;
		}
		// If this Activity does not have dependencies return false
		else // if DependencyList "dependentActivities" is not empty
		{
			// Return true
			return true;
		}
	}
	
	
	// Method that checks if an Activity exists in this Activity object's dependency list
	//=========================================================================================================================================================+++================
	public boolean dependencyListContains(Activity activityToFind) // returns a boolean value whether or not "activityToFind" is found within this Activity's dependencyList
	{
		// If the Activity to find is contained in this Activity object's dependency list return true
		if (dependentActivities.doesItContain(activityToFind) == true) // if "dependentActivities" contains "activityToFind"
		{
			// Return true
			return true;
		}
		// If the Activity to find is not in this Activity object's dependency list return false
		else // if "dependentActivities" does not contain "activityToFind"
		{
			// Return false
			return false;
		}
	}
}