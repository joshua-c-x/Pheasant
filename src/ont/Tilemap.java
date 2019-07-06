package ont;

import processing.core.PImage;

public class Tilemap 
{
	private String _name;
	private long   _location;
	private PImage _background, _foreground;
	private int[]  _collisions;
	private int    _tilesX, _tilesY;
	private float  _renderOffsetX, _renderOffsetY; 
	
	public Tilemap(String name, long location, PImage background, PImage foreground, int tilesX, int tilesY, int[] collisions) 
	{
		_name       = name;
		_location   = location;
		_background = background;
		_foreground = foreground;
		_tilesX     = tilesX;
		_tilesY     = tilesY;
		_collisions = collisions;
	}
	
	public long Location() 
	{
		return _location;
	}
	
	public PImage Background() 
	{
		return _background;
	}
	
	public PImage Foreground() 
	{
		return _foreground;
	}
	
	public int TilesX() 
	{
		return _tilesX;
	}
	
	public int TilesY() 
	{
		return _tilesY;
	}
	
	public float RenderOffsetX() 
	{
		return _renderOffsetX;
	}
	
	public float RenderOffsetY() 
	{
		return _renderOffsetY;
	}
	
	public int[] Collisions() 
	{
		return _collisions;
	}
	// once i have what i need
	// then i can focus on what i want (programming style?)
	// but what you need, is a function of what you want?
	
	public void SetOffset(float renderOffsetX, float renderOffsetY) 
	{
		_renderOffsetX = renderOffsetX;
		_renderOffsetY = renderOffsetY;
	}
}
