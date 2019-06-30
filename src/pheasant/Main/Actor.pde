public class Actor
{ 
  int _tileX, 
      _tileY, 
      _dimX, 
      _dimY;
      
  float _radius;
  
  Actor(int tilex, int tiley, int dimX, int dimY, float radius)
  {
    _tileX = tilex;
    _tileY = tiley;
    _dimX = dimX;
    _dimY = dimY;
    _radius = radius;
  }
  
  public int TileX() 
  {
    return _tileX;
  }
  
  public int TileY() 
  {
    return _tileY;
  }
  
  public int DimensionX() 
  {
    return _dimX;
  }
  
  public int DimensionY() 
  {
    return _dimY;
  }
  
  public float Radius() 
  {
    return _radius;
  }

  public void Update(IO io, float delta) { DrawDebug(); }
  
  public void DrawDebug() 
  {
    noFill();
    stroke(255,0,255,150);
    rect(_tileX * Engine.TileDimension,_tileY * Engine.TileDimension, Engine.TileDimension, Engine.TileDimension);
    fill(0);
    text('a', _tileX * Engine.TileDimension, (_tileY * Engine.TileDimension) + Engine.TileDimension);
  }
}
