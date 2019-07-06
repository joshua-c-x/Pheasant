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
  private ArrayList<Map> _maps;
  
  
  public World() 
  {
	// Systems
	_spriteSystem = new SpriteSystem();
	
	// Entitys
	_entities     = new EntityContainer();
    _gameObjects  = new ArrayList<GameObject>();
    _actors       = new ArrayList<Actor>();
	
    // Maps
    _maps         = new ArrayList<Map>();
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
	
	 ////////////////////////////////////////////////////////////
	 ////////////----------------------------------//////////////
	 ////////////Load Maps Locally From JSON,      //////////////
	 ////////////send to _world                    //////////////
	 ////////////////////////////////////////////////////////////
	
	 public void LoadMapsFromJSONArray(JSONArray jsonArray)
	 {
	     for (int i = 0; i < jsonArray.size(); i += 1) 
	     {
	         JSONObject json = jsonArray.getJSONObject(i);
	         String location;
	         int[] collisionsBuffer;
	
	         location = json.getString("LOCATION");
	
	         JSONArray array = json.getJSONArray("COLLISIONS");
	         collisionsBuffer = array.getIntArray();
	
	         int[][] collisions = new int[Parameters.MapY][];
	         int offset;
	
	         for (int y = 0; y < Parameters.MapY; y += 1) 
	         {
	             collisions[y] = new int[Parameters.MapX];
	             for (int x = 0; x < Parameters.MapX; x += 1) 
	             {
	                 offset = y * Parameters.MapX;
	                 collisions[y][x] = collisionsBuffer[offset + x];
	             }
	         }
	
	         Map map = new Map(collisions, location);
	
	         _maps.add(map);
	     }
	 }
	 
	  ////////////////////////////////////////////////////////////
	  ////////////----------------------------------//////////////
	  ////////////    Get Maps Exported As JSON     //////////////
	  ////////////__________________________________//////////////
	  ////////////////////////////////////////////////////////////
	
	  public JSONArray GetMapsToJSONArray() 
	  {
	      JSONArray mapArray = new JSONArray();
	
	      for (int i = 0; i < _maps.size(); i += 1) 
	      {
	          Map map = _maps.get(i);
	          String location = map.Location();
	          JSONObject mapJSON = new JSONObject();
	          JSONArray collisionArray = new JSONArray();
	          int[][] collisions = map.Collisions();
	          int offset;
	          for (int y = 0; y < Parameters.MapY; y += 1) 
	          {
	              for (int x = 0; x < Parameters.MapX; x += 1) 
	              {
	                  offset = y * Parameters.MapX;
	                  collisionArray.setInt((offset + x), collisions[y][x]);
	              }
	          }
	          mapJSON.setString("LOCATION", location);
	          mapJSON.setJSONArray("COLLISIONS", collisionArray);
	          mapArray.append(mapJSON);
	      }
	      return mapArray;
	  }
	  
	  ////////////////////////////////////////////////////////////
	  ////////////----------------------------------//////////////
	  ////////////    Get Maps Exported As JSON     //////////////
	  ////////////__________________________________//////////////
	  ////////////////////////////////////////////////////////////
	  
	  public JsonNode GetEntityContainerJsonNode() 
	  {
		  return _entities.ExportEntityContainerJsonNode();
	  }
}
