package ont;

import def.Application;
import eng.IO;
import eng.Parameters;

public class Player extends Actor 
{  
  public Player(int tileX, int tileY, int dimX, int dimY, float radius) 
  {
    super(tileX, tileY, dimX, dimY, radius);
  }
  
  public void Update(IO io, float delta) 
  {
    if(io.KeysJustPressed.get(Parameters.Keys_Up)) 
    {
      _tileY -= 1;
    }
    if(io.KeysJustPressed.get(Parameters.Keys_Down)) 
    {
      _tileY += 1;
    }
    if(io.KeysJustPressed.get(Parameters.Keys_Left)) 
    {
      _tileX -= 1;
    }
    if(io.KeysJustPressed.get(Parameters.Keys_Right)) 
    {
      _tileX += 1;
    }
    DrawDebug();
  }
  
  public void DrawDebug() 
  {
	Application.PROCESSING.noFill();
	Application.PROCESSING.stroke(255,0,255,150);
	Application.PROCESSING.rect( _tileX * Parameters.TileDimension ,_tileY * Parameters.TileDimension, Parameters.TileDimension, Parameters.TileDimension);
	Application.PROCESSING.fill(0);
	Application.PROCESSING.text('a', _tileX * Parameters.TileDimension, ( _tileY * Parameters.TileDimension) + Parameters.TileDimension);
  }
}
