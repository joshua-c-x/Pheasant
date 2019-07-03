package components;

import ecs.Components;
import processing.data.JSONObject;

public class Sprite extends Components
{
	private static final String SpriteIDKey = "SPRITEID".toUpperCase();
	
	public int SpriteID;
	public int Index;
	public float SuperDuration;
	public boolean OverrideDuration;
	
	public Sprite() 
	{
		super(Components.Sprite);
	}
	
	@Override
	public JSONObject ToJSON() 
	{
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.setInt(SpriteIDKey, SpriteID);
		
		json.setString("NAME", Components.Name_Sprite);
		json.setLong(Components.ComponentIDKey, ComponentID());
		json.setJSONObject("DATA", data);
		
		return json;
	}

	@Override
	public void FromJSON(JSONObject data) 
	{
		SpriteID = data.getInt(SpriteIDKey);
		Index = 0;
		SuperDuration = 0.0f;
		OverrideDuration = false;
	}
}
