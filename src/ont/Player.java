package ont;

import components.WorldPosition;
import def.Application;
import ecs.Components;
import ecs.EntityContainer;
import ecs.IEntity;
import eng.IO;
import eng.Parameters;

public class Player extends Actor implements IEntity
{	
  public Player(int entity, EntityContainer entityContainer) 
  {
	  super(entity, entityContainer);
  }
  
  public void Update(IO io, float delta) 
  {
  int entity = this.Entity();
  
  if(_entityContainer.HasComponent(entity, Components.WorldPosition)) 
  {	
	  WorldPosition wp = (WorldPosition)_entityContainer.GetComponent(entity, Components.WorldPosition);

	  if(io.KeysJustPressed.get(Parameters.Keys_Up)) 
	  {
		  wp.TileY -= 1;
	  }
	  if(io.KeysJustPressed.get(Parameters.Keys_Down)) 
	  {
		  wp.TileY += 1;
	  }
	  if(io.KeysJustPressed.get(Parameters.Keys_Left)) 
	  {
		  wp.TileX -= 1;
	  }
	  if(io.KeysJustPressed.get(Parameters.Keys_Right)) 
	  {
		  wp.TileX += 1;
	  }
  }
  
  DrawDebug();
}
  
  	public void DrawDebug() 
  	{
  		int x,y,entity;
  		
  		entity = this.Entity();
  		
  		if(_entityContainer.HasComponent(entity, Components.WorldPosition)) 
  		{
  			WorldPosition wp = (WorldPosition)_entityContainer.GetComponent(entity, Components.WorldPosition);
  			x = wp.TileX;
  			y = wp.TileY;
  			Application.PROCESSING.noFill();
  			Application.PROCESSING.stroke(255,0,255,150);
  			Application.PROCESSING.rect( x * Parameters.TileDimension , y * Parameters.TileDimension, Parameters.TileDimension, Parameters.TileDimension);
  			Application.PROCESSING.fill(0);
  			Application.PROCESSING.text('p', x * Parameters.TileDimension, ( y * Parameters.TileDimension) + Parameters.TileDimension);
  		}

  	}
}
