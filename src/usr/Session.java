package usr;

import com.fasterxml.jackson.databind.JsonNode;

import eng.IO;
import ont.*;
import processing.data.JSONArray;

public class Session 
{
  private String _currentUserName;
  private World  _world;
  
  public Session(String userName, JSONArray maps, JsonNode entityContainerJsonNode) 
  {
     _currentUserName = userName;
     
     _world = new World();     
     _world.LoadMapsFromJSONArray(maps);
     _world.LoadEntityContainer(entityContainerJsonNode);
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


  public String CurrentUserName() 
  {
	return _currentUserName;  
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Get Maps as JSON Array            //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public JSONArray MapsToJSONArray() 
  {
    return _world.GetMapsToJSONArray();
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Get Entities as JSON Array        //////////////
  ////////////__________________________________//////////////
  ////////////////////////////////////////////////////////////
  
  public JsonNode EntitiesToJSONArray() 
  {
    return _world.GetEntityContainerJsonNode();
  }
  
}


