public class Session 
{
  private String _currentUserName;
  private World  _world;
  private Scene  _currentScene;
  
  Session(String userName) 
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
  ////////////    Get Player Exported As JSON    /////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public JSONObject GetPlayerExportedAsJSON() 
  {
    JSONObject playerJSON = new JSONObject();
    playerJSON.setInt("TILEX",_world.Player().TileX());
    playerJSON.setInt("TILEY",_world.Player().TileY());
    playerJSON.setInt("DIMX",_world.Player().DimensionX());
    playerJSON.setInt("DIMY",_world.Player().DimensionY());
    playerJSON.setFloat("RADIUS",_world.Player().Radius());
    
    return playerJSON;
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
  ////////////Get Game Objects Exported As JSON //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public JSONArray GetGameObjectsExportedAsJSON() 
  {
    JSONArray jsonArray = new JSONArray();
    ArrayList<GameObject> gameObjects = _world.GameObjects();  
    for(int i = 0; i < gameObjects.size(); i += 1) 
    {
      GameObject obj = gameObjects.get(i);
      JSONObject gameObjectJSON = new JSONObject();   
      gameObjectJSON.setString("LOCATION", obj.Location());
      gameObjectJSON.setString("DESCRIPTION", obj.Description());
      gameObjectJSON.setString("NAME", obj.Name());
      gameObjectJSON.setInt("X", obj.X());
      gameObjectJSON.setInt("Y", obj.Y());  
      JSONArray array = new JSONArray();    
      int[] frames = obj.Frames();   
      for(int j = 0; j < frames.length; j += 1) 
      {
        array.setInt(j,frames[j]);      
      }  
      gameObjectJSON.setJSONArray("MAPANIMATION",array);   
      jsonArray.append(gameObjectJSON);
    }
    return jsonArray;
  }

  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////    Get Actors Exported As JSON   //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public JSONArray GetActorsExportedAsJSON()
  {
    JSONArray jsonArray = new JSONArray();
    ArrayList<Actor> actors = _world.Actors();    
    for(int i = 0; i < actors.size(); i += 1) 
    {
      Actor actor = actors.get(i);
      JSONObject actorJSON = new JSONObject();

      actorJSON.setInt("TILEX", actor.TileX());
      actorJSON.setInt("TILEY", actor.TileY());
      actorJSON.setInt("DIMX", actor.DimensionX());
      actorJSON.setInt("DIMY", actor.DimensionY());
      actorJSON.setFloat("RADIUS", actor.Radius());
      
      jsonArray.append(actorJSON);
    }
    return jsonArray;
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
      for(int y = 0; y < Engine.MapY; y += 1) 
      { 
        for(int x = 0; x < Engine.MapX; x += 1) 
        {
          offset = y * Engine.MapX;
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
  ////////////Load Player Locally From JSON,    //////////////
  ////////////send to _world                    //////////////
  ////////////////////////////////////////////////////////////
  
  public void LoadPlayerFromJSON(JSONObject playerJSON) 
  {
    int tilex,tiley,dimx,dimy;
    float radius;
    
    tilex  = playerJSON.getInt("TILEX");
    tiley  = playerJSON.getInt("TILEY");
    dimx   = playerJSON.getInt("DIMX");
    dimy   = playerJSON.getInt("DIMY");
    radius = playerJSON.getFloat("RADIUS");
    
    Player player = new Player(tilex, tiley, dimx, dimy, radius);
    
    _world.SetPlayer(player);
  }

  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Load Actors Locally From JSON,    //////////////
  ////////////send to _world                    //////////////
  ////////////////////////////////////////////////////////////
  
  public void LoadActorsFromJSON(JSONArray actorsJSON) 
  {
    for(int i = 0; i < actorsJSON.size(); i += 1) 
    {
      JSONObject actorJSON = actorsJSON.getJSONObject(i);
      
      int tilex,tiley,dimx,dimy;
      float radius;
    
      tilex  = actorJSON.getInt("TILEX");
      tiley  = actorJSON.getInt("TILEY");
      dimx   = actorJSON.getInt("DIMX");
      dimy   = actorJSON.getInt("DIMY");
      radius = actorJSON.getFloat("RADIUS");
      
      Actor actor = new Actor( tilex, tiley, dimx, dimy, radius );
      
      _world.AddActor(actor);
    }
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
      
      int[][] collisions = new int[Engine.MapY][];
      int offset;
      
      for(int y = 0; y < Engine.MapY; y += 1) 
      {
        collisions[y] = new int[Engine.MapX];  
        for(int x = 0; x < Engine.MapX; x += 1) 
        {
          offset = y * Engine.MapX;
          collisions[y][x] = collisionsBuffer[offset + x];
        }
      }
      Map map = new Map(collisions, location);
      
      _world.AddMap(map);
    }
  }
  
  ////////////////////////////////////////////////////////////
  ////////////------------------------------------////////////
  ////////////Load GameObjects Locally From JSON, ////////////
  ////////////send to _world                      ////////////
  ////////////////////////////////////////////////////////////
  
  public void LoadGameObjectsFromJSON(JSONArray gameObjectsJSON) 
  {
    for(int i = 0; i < gameObjectsJSON.size(); i += 1) 
    {
      JSONObject json = gameObjectsJSON.getJSONObject(i);
      String location, description, name;
      int x,y;
      int[] frames;
      
      location = json.getString("LOCATION");
      description = json.getString("DESCRIPTION");
      name = json.getString("NAME");
      x = json.getInt("X");
      y = json.getInt("Y");
      
      JSONArray array = json.getJSONArray("MAPANIMATION");
      frames = array.getIntArray();
       
      GameObject obj = new GameObject(location,description,name,x,y,frames);
      
      _world.AddGameObject(obj);
    }
  }
}

public class SessionManager
{
  Session _activeSession;
  
  SessionManager() {}
  
  public void LoadSessionFromUserFile(FileHandler.UserFile userFile) 
  {
    _activeSession = new Session(userFile.UserName);
    
    _activeSession.LoadGameObjectsFromJSON(userFile.GameObjectsJSONArray);
    _activeSession.LoadActorsFromJSON(userFile.ActorsJSONArray);
    _activeSession.LoadMapsFromJSON(userFile.MapsJSONArray);
    _activeSession.LoadPlayerFromJSON(userFile.PlayerJSONObject);
    _activeSession.LoadWorldFromJSON(userFile.WorldJSONObject);
  }
  
  boolean ActiveSessionExists() 
  {
    return _activeSession != null;
  }
  
  Session ActiveSession() 
  {
    return _activeSession;
  }
  
  public void Close() 
  {
    _activeSession = null;
  }
}
