package components;

import ecs.Components;
import processing.data.JSONObject;

public class SpriteID extends Components
{
	public int Value;
	
	public SpriteID() 
	{
		super(Components.SpriteID);
	}

	@Override
	public JSONObject ToJSON() 
	{
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.setInt("VALUE", Value);
		
		json.setLong("ID", ID());
		json.setJSONObject("DATA", data);
		
		return json;
	}

	@Override
	public void FromJSON(JSONObject data) 
	{
		Value = data.getInt("VALUE");
	}
}
