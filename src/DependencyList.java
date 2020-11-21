import java.util.ArrayList;

public class DependencyList 
{	
	public ArrayList<Activity> listOfDependencies = new ArrayList<Activity>();
	
	// Method that that checks if DependencyList is empty
	//===========================================================================================================================================================================
	public boolean isDependencyListEmpty()
	{
		// If the Activity list is empty
		if (listOfDependencies.isEmpty() == true)
		{
			// Return true
			return true;
		}
		// If the Activity list is not empty
		else
		{
			// Return false
			return false;
		}
	}
	
	public void addActivityToDependencyList(Activity activityToBeAdded) // takes in Activity "activityToBeAdded"
	{
		// Add the activity to the array list
		listOfDependencies.add(activityToBeAdded); // add "activityToBeAdded" to "listOfActivities"
	}
	
	
	public Activity getActivityFromDependencyList(int index)
	{
		return listOfDependencies.get(index);
	}
	
	public int sizeOfDependencyList()
	{
		// Return the size of the list
		return listOfDependencies.size();
	}
	
	public boolean doesItContain(Activity a)
	{
		if (listOfDependencies.contains(a) == true)
		{
			return true;
		}
		
		return false;
	}
}