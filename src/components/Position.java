package components;

import ecs.Components;
import processing.data.JSONObject;

public class Position extends Components 
{
	public int TileX, TileY;
	
	public Position() 
	{
		super(Components.Position);
	}
	
	@Override
	public JSONObject ToJSON() 
	{
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.setInt("X", TileX);
		data.setInt("Y", TileY);
		
		json.setString("NAME", Components.Name_Position);
		json.setLong(Components.ComponentIDKey, ComponentID());
		json.setJSONObject("DATA", data);
		
		return json;
	}

	@Override
	public void FromJSON(JSONObject data) 
	{
		TileX = data.getInt("X");
		TileY = data.getInt("Y");
	}
}
