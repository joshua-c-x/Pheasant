package ont;

import java.util.ArrayList;

import def.Application;
import ecs.EntityContainer;
import ecs.IEntity;
import eng.IO;
import eng.Parameters;

public class GameObject implements IEntity
{
  int _entity;
  EntityContainer _entityContainer;
  
  public GameObject(int entity, EntityContainer entityContainer) 
  {
	  _entity = entity;
	  _entityContainer = entityContainer;
  } 
  
  public int Entity() 
  {
	  // TODO Auto-generated method stub
	  return _entity;
  }
  
  public EntityContainer GetEntityContainer() 
  {
	  return _entityContainer;
  }
	
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
	int x,y;
//	x = _entity.TileX();
//	y = _entity.TileY();
	
//	Application.PROCESSING.noFill();
//	Application.PROCESSING.stroke(255,0,255,150);
//	Application.PROCESSING.rect( x * Parameters.TileDimension , y * Parameters.TileDimension, Parameters.TileDimension, Parameters.TileDimension);
//	Application.PROCESSING.fill(0);
//	Application.PROCESSING.text('g', x * Parameters.TileDimension, ( y * Parameters.TileDimension) + Parameters.TileDimension);
  }


}
