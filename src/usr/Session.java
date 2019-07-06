package usr;

import com.fasterxml.jackson.databind.JsonNode;

import eng.IO;
import ont.*;
import processing.data.JSONArray;

public class Session 
{
  private World  _world;
  
  public Session(JsonNode entityContainerJsonNode) 
  {     
     _world = new World();     
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


