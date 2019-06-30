package ont;

import def.Application;
import eng.IO;
import eng.Parameters;

public class GameObject
{
  String _location, _description, _name;
  int _x, _y;
  int[] _frames;
  
  public GameObject(String location, String description, String name, int x, int y, int[] frames)
  {
    _location    = location;
    _description = description;
    _name        = name;
    _x           = x;
    _y           = y;
    _frames      = frames;
  } 
  
  public String Location()    { return _location; }
  public String Description() { return _description; }
  public String Name()        { return _name; }
  public int X()              { return _x; }
  public int Y()              { return _y; }
  public int[] Frames()       { return _frames; }

  public void Draw() 
  {
    DrawDebug();
  }
  
  public void Update(IO io, float delta) 
  {
    Draw();
  }
  
  public void DrawDebug() 
  {
	Application.PROCESSING.noFill();
	Application.PROCESSING.stroke(255,0,255,150);
	Application.PROCESSING.rect( _x * Parameters.TileDimension , _y * Parameters.TileDimension, Parameters.TileDimension, Parameters.TileDimension);
	Application.PROCESSING.fill(0);
	Application.PROCESSING.text('g', _x * Parameters.TileDimension, ( _y * Parameters.TileDimension) + Parameters.TileDimension);
  }
}
