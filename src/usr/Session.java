package usr;
import java.util.ArrayList;

import ecs.Entity;
import ecs.IComponent;
import eng.IO;
import eng.Parameters;
import ont.*;
import processing.data.JSONArray;
import processing.data.JSONObject;
import res.Animation;

public class Session 
{
  private String _currentUserName;
  private World  _world;
  //private Scene  _currentScene;
  
  public Session(String userName) 
  {
     _world = new World();
     _currentUserName = userName;
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////    Update                        //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public void Update(IO io, float delta) 
  {
    _world.Update(io,delta);
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////    Properties                    //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public World World() 
  {
    return _world;
  }
  
  public String CurrentUserName() 
  {
    return _currentUserName;
  }
 
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////    Get World Exported As JSON    //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public JSONObject GetWorldExportedAsJSON() 
  {
    JSONObject worldJSON = new JSONObject();
    worldJSON.setString("TEMP",_world.Temp);
    return worldJSON;
  }  

  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////    Get Maps Exported As JSON     //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public JSONArray GetMapsExportedAsJSON() 
  {
    JSONArray mapArray = new JSONArray();
    ArrayList<Map> maps = _world.Maps();   
    for(int i = 0; i < maps.size(); i += 1) 
    {
      Map map = maps.get(i);     
      String location = map.Location();
      JSONObject mapJSON = new JSONObject();      
      JSONArray collisionArray = new JSONArray();
      
      int[][] collisions = map.Collisions();
      int offset;     
      for(int y = 0; y < Parameters.MapY; y += 1) 
      { 
        for(int x = 0; x < Parameters.MapX; x += 1) 
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
  ////////////Get Entities Exported As JSON //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public JSONArray GetEntitiesExportedAsJSON() 
  {
    JSONArray entitiesJSONArray = new JSONArray();
    
    // game objects
    for(GameObject obj : _world.GameObjects()) 
    {
    	JSONObject jsonEntity = obj.Entity().ToJSON();
    	entitiesJSONArray.append(jsonEntity);
    }	
    
    // actors
    for(Actor actor : _world.Actors()) 
    {
    	JSONObject jsonEntity = actor.Entity().ToJSON();
    	entitiesJSONArray.append(jsonEntity);
    }
    
    // player
	entitiesJSONArray.append(_world.Player().Entity().ToJSON());
	
    return entitiesJSONArray;
  }

  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////      Load World From JSON        //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public void LoadWorldFromJSON(JSONObject worldJSON) 
  {
    String temp = worldJSON.getString("TEMP");
    _world.SetTemp(temp);
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Load Maps Locally From JSON,      //////////////
  ////////////send to _world                    //////////////
  ////////////////////////////////////////////////////////////
  
  public void LoadMapsFromJSON(JSONArray mapsJSON) 
  {
	  for(int i = 0; i < mapsJSON.size(); i += 1) 
	  {
		  JSONObject json = mapsJSON.getJSONObject(i);
		  String location;
		  int[] collisionsBuffer;
			  
		  location = json.getString("LOCATION");
			  
		  JSONArray array = json.getJSONArray("COLLISIONS");
		  collisionsBuffer = array.getIntArray();
		
		  int[][] collisions = new int[Parameters.MapY][];
		  int offset;
			  
		  for(int y = 0; y < Parameters.MapY; y += 1) 
		  {
			  collisions[y] = new int[Parameters.MapX];  
			  for(int x = 0; x < Parameters.MapX; x += 1) 
			  {
				  offset = y * Parameters.MapX;
				  collisions[y][x] = collisionsBuffer[offset + x];
			  }
		  }
		  
		  Map map = new Map(collisions, location);
		  
		  _world.AddMap(map);
		}
	}
  
  	////////////////////////////////////////////////////////////
  	////////////------------------------------------////////////
  	////////////Load Entities from User JSON file.  ////////////
  	////////////send to _world                      ////////////
  	////////////////////////////////////////////////////////////

	public void LoadEntitiesFromJSON(JSONArray entities) 
	{
		for(int i = 0; i < entities.size(); i += 1) 
		{
			JSONObject json = entities.getJSONObject(i);
			
			String location, name;
			int tag, tileX, tileY, tilesX, tilesY, imageWidth, imageHeight;
			float radius;
			boolean render;
		
			tag         = json.getInt("TAG");
			location    = json.getString("LOCATION");
			name        = json.getString("NAME");
			tileX       = json.getInt("TILEX");
			tileY       = json.getInt("TILEY");
			tilesX      = json.getInt("TILESX");
			tilesY      = json.getInt("TILESY");
			imageWidth  = json.getInt("IMAGEWIDTH");
			imageHeight = json.getInt("IMAGEHEIGHT");
			radius      = json.getFloat("RADIUS");
			render      = json.getBoolean("RENDER");
			
			Entity e = new Entity(tag, location, name, render);
			e.SetDimensions(tileX, tileY, tilesX, tilesY, imageWidth, imageHeight, radius);
			
			int j;
			
			JSONArray animations = json.getJSONArray("ANIMATIONS");			
			for( j = 0; j < animations.size(); j += 1) 
			{
				String animationName;
				int numFrames;
				int[] animationFrames;
				
				JSONObject jsonAnimation = animations.getJSONObject(j);
				JSONArray jsonArray = jsonAnimation.getJSONArray("FRAMES");
				numFrames = jsonArray.size();
				animationName = jsonAnimation.getString("NAME");
				animationFrames = new int[numFrames];
				
				for(int f = 0; f < numFrames; f += 1) 
				{
					animationFrames[f] = jsonArray.getInt(f);
				}
				
				Animation animation = new Animation(animationName, animationFrames);
				
				e.AddAnimation(animation);
			}
			
			JSONArray components = json.getJSONArray("COMPONENTS");
			for( j = 0; j < components.size(); j += 1) 
			{
				JSONObject jsonComponent = components.getJSONObject(j);
				// todo parse jsonComponent?
			}
			
			switch(e.Tag()) 
			{
				case 0:
					_world.SetPlayer(e);
					break;
				case 1:
					_world.AddGameObject(e);
					break;
				case 2:
					_world.AddActor(e);
					break;
			}
		}
	}
}


