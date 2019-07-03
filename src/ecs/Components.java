package ecs;

import processing.data.JSONObject;

public abstract class Components 
{
	// 0 = no components
	
	public final static long 
							 Empty         = 0,
				             Tag           = 1 << 0,
							 WorldPosition = 1 << 1,
							 SpriteID      = 1 << 2,
							 Player        = 1 << 3,
							 GameObject    = 1 << 4,
							 Actor         = 1 << 5;
	private final long _id;
	
	public Components(long id) 
	{
		_id = id;
	}
	
	
	public long ID() 
	{
		return _id;
	}
	
	public abstract void FromJSON(JSONObject json);
	public abstract JSONObject ToJSON();
}
