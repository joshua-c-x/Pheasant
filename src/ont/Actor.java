package ont;
import def.*;
import ecs.Entity;
import eng.*;

public class Actor 
{   
	Entity _entity;
	
	public Actor(Entity entity)
	{
		_entity = entity;
	}

	public Entity Entity() 
	{
		return _entity;
	}
	  
	public void Update(IO io, float delta) { DrawDebug(); }
  
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
