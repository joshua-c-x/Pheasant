package components;

import ecs.Components;
import processing.data.JSONObject;

public class WorldPosition extends Components 
{
	public int TileX, TileY;
	
	public WorldPosition() 
	{
		super(Components.WorldPosition);
	}
	
	@Override
	public JSONObject ToJSON() 
	{
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.setInt("X", TileX);
		data.setInt("Y", TileY);
		
		json.setLong("ID", ID());
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
