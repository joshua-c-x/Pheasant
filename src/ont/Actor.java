package ont;
import def.*;
import eng.*;

public class Actor
{ 
  int _tileX, 
      _tileY, 
      _dimX, 
      _dimY;
      
  float _radius;
  
  public Actor(int tilex, int tiley, int dimX, int dimY, float radius)
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
    Application.PROCESSING.noFill();
    Application.PROCESSING.stroke(255,0,255,150);
    Application.PROCESSING.rect( _tileX * Parameters.TileDimension ,_tileY * Parameters.TileDimension, Parameters.TileDimension, Parameters.TileDimension);
    Application.PROCESSING.fill(0);
    Application.PROCESSING.text('a', _tileX * Parameters.TileDimension, ( _tileY * Parameters.TileDimension) + Parameters.TileDimension);
  }
}
