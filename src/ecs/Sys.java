package ecs;

import def.Application;

public class Sys 
{
	int _pairedComponentID;
	
	public Sys(int pairedComponentID) 
	{
		_pairedComponentID = pairedComponentID;
	}
	
	public void UpdateComponent(IComponent component)
	{
		Application.println("Error, UpdateComponent has not been Overidden");
		Application.PROCESSING.exit();
		return;
	}
	
	public boolean IsValidComponent(IComponent component) 
	{
		Application.println("Error, IsValidComponent has not been Overidden");
		Application.PROCESSING.exit();
		return false;
	}
	
	public int PairedComponentID() 
	{
		return _pairedComponentID;
	}
}