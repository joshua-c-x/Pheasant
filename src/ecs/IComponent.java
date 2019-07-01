package ecs;

import def.Application;
import eng.IO;
import processing.data.JSONObject;

public interface IComponent 
{	
	public JSONObject ToJSON();
	public void RunComponentUpdate(IO io, float delta);
}
