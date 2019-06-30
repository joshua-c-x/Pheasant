public class GameObject
{
  String _location, _description, _name;
  int _x, _y;
  int[] _frames;
  
  GameObject(String location, String description, String name, int x, int y, int[] frames)
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
    noFill();
    stroke(0,0,255,50);
    rect(_x * Engine.TileDimension,_y * Engine.TileDimension, Engine.TileDimension, Engine.TileDimension);
    fill(0);
    text('g',_x * Engine.TileDimension,(_y * Engine.TileDimension) + Engine.TileDimension);
  }
}
