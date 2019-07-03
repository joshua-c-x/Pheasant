package components;

import ecs.Components;

import processing.data.JSONObject;

public class Tag extends Components
{
	public String _type;
	
	public Tag() 
	{
		super(Components.Tag);
	}
	
	public String Type() 
	{
		return _type;
	}
	
	@Override
	public JSONObject ToJSON() 
	{
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.setString("TYPE", _type);
		
		json.setString("NAME", Components.Name_Tag);
		json.setLong(Components.ComponentIDKey, ComponentID());
		json.setJSONObject("DATA", data);
		
		return json;
	}

	@Override
	public void FromJSON(JSONObject data) 
	{
		_type = data.getString("TYPE");
	}	
}
