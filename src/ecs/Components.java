package ecs;
import com.fasterxml.jackson.databind.JsonNode;

import processing.data.JSONObject;

public abstract class Components 
{	
	public final static long Tag           = 1 << 0, // 1
							 Position      = 1 << 1, // 2 
							 Controller    = 1 << 2, // 4
	 				         Sprite        = 1 << 3; // 8
	
	private final long _id;
	private final String _name;
	
	public Components(long id, String name) 
	{
		_id = id;
		_name = name;
	}
	
	public long ComponentID() 
	{
		return _id;
	}
	
	public String ComponentName() 
	{
		return _name;
	}
	
	public abstract void FromJsonNode(JsonNode jsonNode);
	public abstract JsonNode ToJsonNode();
}
