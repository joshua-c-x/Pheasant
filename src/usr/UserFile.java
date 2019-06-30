package usr;

import processing.data.JSONArray;
import processing.data.JSONObject;

public class UserFile 
{
  public String UserName;
  public JSONArray GameObjectsJSONArray;
  public JSONArray ActorsJSONArray;
  public JSONArray MapsJSONArray;
  public JSONObject WorldJSONObject;
  public JSONObject PlayerJSONObject;
 
  public UserFile(String userName, JSONArray gameObjectsJSON, JSONArray actorsJSON, JSONArray mapsJSON, JSONObject worldJSON, JSONObject playerJSON) 
  {
    UserName             = userName;
    
    GameObjectsJSONArray = gameObjectsJSON;
    ActorsJSONArray      = actorsJSON;
    MapsJSONArray        = mapsJSON;
    WorldJSONObject      = worldJSON;
    PlayerJSONObject     = playerJSON;
  }
}