package ont;

import components.Position;
import components.Sprite;
import def.Application;
import ecs.Components;
import ecs.EntityContainer;
import eng.IO;
import eng.Parameters;
import vis.Assets;
import vis.SpriteSheet;

public class Player extends Actor
{	
	  public Player(int entity, EntityContainer entityContainer) 
	  {
		  super(entity, entityContainer);
	  }
	  
	  public void Update(IO io, float delta) 
	  {
		  Position p = GetComponent(Components.Position);
		  
		  if(io.KeysJustPressed.get(Parameters.Keys_Up)) 
		  {
			  p.TileY -= 1;
		  }
		  if(io.KeysJustPressed.get(Parameters.Keys_Down)) 
		  {
			  p.TileY += 1;
		  }
		  if(io.KeysJustPressed.get(Parameters.Keys_Left)) 
		  {
			  p.TileX -= 1;
		  }
		  if(io.KeysJustPressed.get(Parameters.Keys_Right)) 
		  {
			  p.TileX += 1;
		  }
		  DrawDebug();
	  }
  
	  public void DrawDebug() 
	  { 		 		
		  Position p = GetComponent(Components.Position);
		  Sprite s = GetComponent(Components.Sprite);
	

		 // sprite.Play(p.TileX * Parameters.TileDimension, p.TileY * Parameters.TileDimension);
	  }
}
