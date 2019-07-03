package ecs;
import processing.data.JSONObject;

public abstract class Components 
{	
	public final static String ComponentIDKey = "CIDL";
	
	public final static long Tag           = 1 << 0, // 1
							 Position      = 1 << 1, // 2 
							 Controller    = 1 << 2, // 4
	 				         Sprite        = 1 << 3; // 8
	
	public final static String Name_Tag        = "TAG",
							   Name_Position   = "POSITION",
							   Name_Controller = "CONTROLLER",
							   Name_Sprite     = "SPRITE";
	
	private final long _id;
	
	public Components(long id) 
	{
		_id = id;
	}
	
	public long ComponentID() 
	{
		return _id;
	}
	
	public abstract void FromJSON(JSONObject json);
	public abstract JSONObject ToJSON();
}
