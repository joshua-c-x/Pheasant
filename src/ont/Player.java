package ont;

import def.Application;
import ecs.Entity;
import eng.IO;
import eng.Parameters;

public class Player extends Actor 
{	
  public Player(Entity entity) 
  {
    super(entity);
  }
  
  public void Update(IO io, float delta) 
  {
	  if(io.KeysJustPressed.get(Parameters.Keys_Up)) 
	  {
		  _entity.SetTileY(_entity.TileY() - 1);
	  }
	  if(io.KeysJustPressed.get(Parameters.Keys_Down)) 
	  {
		  _entity.SetTileY(_entity.TileY() + 1);
	  }
	  if(io.KeysJustPressed.get(Parameters.Keys_Left)) 
	  {
		  _entity.SetTileX(_entity.TileX() - 1);
	  }
	  if(io.KeysJustPressed.get(Parameters.Keys_Right)) 
	  {
		  _entity.SetTileX(_entity.TileX() + 1);
	  }
	  DrawDebug();
    }
  
  	public void DrawDebug() 
  	{
  		int x,y;
		x = _entity.TileX();
		y = _entity.TileY();
	
		Application.PROCESSING.noFill();
		Application.PROCESSING.stroke(255,0,255,150);
		Application.PROCESSING.rect( x * Parameters.TileDimension , y * Parameters.TileDimension, Parameters.TileDimension, Parameters.TileDimension);
		Application.PROCESSING.fill(0);
		Application.PROCESSING.text('g', x * Parameters.TileDimension, ( y * Parameters.TileDimension) + Parameters.TileDimension);
  	}
}
