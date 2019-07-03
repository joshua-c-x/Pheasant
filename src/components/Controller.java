package components;

import ecs.Components;
import processing.data.JSONObject;

public class Controller extends Components
{
	private static final String FacingKey = "FACING".toUpperCase();
	
	public static final long Up    = 1 << 0, 
							 Down  = 1 << 1, 
							 Left  = 1 << 2, 
							 Right = 1 << 3;
	Long Facing;
	
	public Controller() 
	{
		super(Components.Controller);
	}

	@Override
	public void FromJSON(JSONObject data) 
	{
		Facing = data.getLong(FacingKey);
	}

	@Override
	public JSONObject ToJSON() 
	{
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.setLong(FacingKey, Facing);
		
		json.setString("NAME", Components.Name_Sprite);
		json.setLong(Components.ComponentIDKey, ComponentID());
		json.setJSONObject("DATA", data);
		
		return json;
	}

}
