package res;

import def.Application;
import eng.Assets;
import processing.core.PImage;

public class Animation 
{
	String  _name;
	int[]   _frames;
	int     _index;
	
	public Animation (String name, int[] frames) 
	{
		_name = name;
		_frames = frames;
		_index = 0;
	}
	
	public String Name() 
	{
		return _name;
	}
	
	public int[] Frames() 
	{
		return _frames;
	}
	
	public void Step() 
	{
		_index += 1;
		_index = _index < _frames.length ? _index : 0;
	}
	
	public void Draw(float x, float y) 
	{
		PImage[] imageCache = Assets.Images.get(_name);
		Application.PROCESSING.image(imageCache[_frames[_index]], x, y);
	}
}
