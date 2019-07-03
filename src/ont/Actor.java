package ont;
import def.*;
import ecs.EntityContainer;
import ecs.IEntity;
import eng.*;

public class Actor implements IEntity
{  
	int _entity;
	EntityContainer _entityContainer;
	
	public Actor(int entity, EntityContainer entityContainer) 
	{
		_entity = entity;
		_entityContainer = entityContainer;
	}
	  
	public int Entity() 
	{
		  return _entity;
	}
	
	public EntityContainer GetEntityContainer() 
	{
		  return _entityContainer;
	}
	  
	  
	public void Update(IO io, float delta) { DrawDebug(); }
  
	public void DrawDebug() 
	{
		//int x,y;
		
//		if(_entity.HasComponent(Components.WorldPosition)) 
//		{
//			WorldPosition wp = (WorldPosition)_entity.GetComponent(Components.WorldPosition);
//		}
		
//		x = _entity.TileX();
//		y = _entity.TileY();
		
//		Application.PROCESSING.noFill();
//		Application.PROCESSING.stroke(255,0,255,150);
//		Application.PROCESSING.rect( x * Parameters.TileDimension , y * Parameters.TileDimension, Parameters.TileDimension, Parameters.TileDimension);
//		Application.PROCESSING.fill(0);
//		Application.PROCESSING.text('g', x * Parameters.TileDimension, ( y * Parameters.TileDimension) + Parameters.TileDimension);
	}
}
