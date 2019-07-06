package ont;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;

import components.Tag;
import ecs.Components;
import ecs.EntityContainer;
import ecs.Systems;
import eng.IO;
import eng.Parameters;
import processing.data.JSONArray;
import processing.data.JSONObject;
import systems.SpriteSystem;

public class World 
{
  private EntityContainer _entities;
  private SpriteSystem _spriteSystem;
  private Player _player;
  private ArrayList<GameObject> _gameObjects;
  private ArrayList<Actor> _actors;
  
  
  public World() 
  {
	// Systems
	_spriteSystem = new SpriteSystem();
	
	// Entitys
	_entities     = new EntityContainer();
    _gameObjects  = new ArrayList<GameObject>();
    _actors       = new ArrayList<Actor>();
	
    // Maps
  }
  
  
  public void Update(IO io, float delta) 
  {
	  _player.Update(io, delta);

	  _spriteSystem.Update(_entities, this, io, delta);
	  
	  //    for(Actor actor : _actors) 
	  //    {
	  //      actor.Update(io, delta);
	  //    }
	  //    
	  //    for(GameObject obj : _gameObjects) 
	  //    {
	  //      obj.Update(io, delta);
	  //    }
  } 
  
  
	////////////////////////////////////////////////////////////
	////////////------------------------------------////////////
	////////////Load Entities from User JSON file.  ////////////
	////////////send to _world                      ////////////
	////////////////////////////////////////////////////////////
	
	public void LoadEntityContainer(JsonNode entityContainerNode) 
	{
		// build EntityContainer on world object
		_entities.LoadEntityContainerJsonNode(entityContainerNode);
	
		// get all entity flags
		long[] entityFlags = _entities.Flags();
	
		for(int entity = 0; entity < entityFlags.length; entity += 1) 
		{
			
			// by design, entity is just an index within _entities
			// flag is the components mask
			
			if((entityFlags[entity] & Components.Tag) > 0)
			{
				Tag tag = (Tag)_entities.GetComponent(entity, Components.Tag);
				
				String type = tag.Type;
				
				if(type.equals(Parameters.TagType_Player)) 
				{		
					
					_player = new Player(entity, _entities);
				}
				else if(type.equals(Parameters.TagType_GameObject)) 
				{
					GameObject gameObject = new GameObject(entity, _entities);
					_gameObjects.add(gameObject);
				}
				else if(type.equals(Parameters.TagType_Actor)) 
				{
					Actor actor = new Actor(entity, _entities);
					_actors.add(actor);
				}
			}
		}
	}
	
	  public JsonNode GetEntityContainerJsonNode() 
	  {
		  return _entities.ExportEntityContainerJsonNode();
	  }
}
