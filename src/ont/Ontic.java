package ont;

import def.Application;
import ecs.Components;
import ecs.EntityContainer;

public class Ontic 
{
	  int _entity;
	  EntityContainer _entityContainer;
	  
	  public Ontic(int entity, EntityContainer entityContainer) 
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
	  
	  @SuppressWarnings("unchecked")
	  public <T extends Components> T GetComponent(long componentID) 
	  {
		T t = null;
		
		if(_entityContainer.HasComponent(_entity, componentID)) 
	  	{
			t = (T)_entityContainer.GetComponent(_entity, componentID);
	  	}
		
		if(t == null) 
		{
			System.err.println("Error getting component");
			Application.PROCESSING.exit();
		}
		
		return t;
	  }
	  
	  public boolean HasComponents(long componentFlag) 
	  {
		  return ((_entityContainer.Flags()[_entity] & componentFlag) == componentFlag);
	  }
	  
	  public boolean HasComponent(long componentID) 
	  {
		  return _entityContainer.HasComponent(_entity, componentID);
	  }
}
