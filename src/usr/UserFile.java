package usr;

import processing.data.JSONArray;
import processing.data.JSONObject;

public class UserFile 
{
  public String UserName;
  public JSONArray EntitiesJSONArray;
  public JSONArray ActorsJSONArray;
  public JSONArray MapsJSONArray;
  public JSONObject WorldJSONObject;
  public JSONObject PlayerJSONObject;
 
  public UserFile(String userName, JSONArray mapsJSON, JSONObject worldJSON, JSONArray entitiesJSON) 
  {
    UserName             = userName;
    MapsJSONArray        = mapsJSON;
    WorldJSONObject      = worldJSON;
    EntitiesJSONArray    = entitiesJSON;
  }
}