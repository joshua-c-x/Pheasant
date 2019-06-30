import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import processing.data.*;
import processing.core.*;
import processing.core.PApplet;

public class FileHandler 
{
  ///
  /// This class uses a singleton pattern, see "Main.pde" for its initialization
  ///
  
  private static FileHandler _instance;
  private static PApplet _app;
  
  public static final String FILENAME_GAMEOBJECTS = "gameobjects.json";
  public static final String FILENAME_ACTORS      = "actors.json";
  public static final String FILENAME_MAPS        = "maps.json";
  public static final String FILENAME_WORLD       = "world.json";
  public static final String FILENAME_PLAYER      = "player.json";
   
  public static final String CONFIG_JSON_PATH = "\\data\\config\\json\\";
  public static final String CONFIG_PATH = "\\data\\config\\cfg.json";
  public static final String USER_DATA_FILES = "\\data\\users\\";
  
  private FileHandler(){}
  
  public static FileHandler Global(PApplet app)
  {
    if(_instance == null) 
    {
      _instance = new FileHandler();
      _app = app;
    }
    return _instance;
  }
  
  public class UserFile 
  {
    String UserName;
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
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Adds UserName to cfg.json         //////////////
  ////////////                                  //////////////
  ////////////////////////////////////////////////////////////
  
  private static void AddUserToConfigFile(String name) 
  {
    name = name.toLowerCase();
    
    JSONObject usersObject = _app.loadJSONObject(CONFIG_PATH);
    JSONArray userNames = usersObject.getJSONArray("USERS");
    JSONArray array = new JSONArray();
    
    int index = -1;
    while((index+=1) < (userNames.size())) 
    {
      array.setString(index,userNames.getString(index));
    }
    array.setString(index,name);  
    
    usersObject.setJSONArray("USERS", array);
    _app.saveJSONObject(usersObject,CONFIG_PATH);
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Confirms UserName does not        //////////////
  ////////////already point to local folder     //////////////
  ////////////////////////////////////////////////////////////
  
  public boolean UserFolderExists(String name) 
  {
    String userPath = _app.sketchPath() + USER_DATA_FILES + name.toLowerCase() + "\\";
    Path path = Paths.get(userPath);
    
    if(Files.exists(path)) 
    {
      return true;
    }
    return false;
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Confirms UserName does not        //////////////
  ////////////already point to local files      //////////////
  ////////////////////////////////////////////////////////////
  
  private int UserFilesExist(String userName) 
  {
    int filesExist = 1;
    String[] userPaths = UserPaths(userName.toLowerCase());
    String localPath = _app.sketchPath();
    
    for(int i = 0; i < userPaths.length; i += 1) 
    {
      Path pobj = Paths.get(localPath + userPaths[i]);
      if(Files.exists(pobj) != true) 
      { 
        filesExist = 0;
        break;
      }
    }
    return filesExist;
  } 
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Generate User Paths               //////////////
  ////////////                                  //////////////
  ////////////////////////////////////////////////////////////
  
  private String[] UserPaths(String name) 
  {  
    return new String[] 
    {
      USER_DATA_FILES + name + "\\" + FILENAME_GAMEOBJECTS,
      USER_DATA_FILES + name + "\\" + FILENAME_ACTORS,
      USER_DATA_FILES + name + "\\" + FILENAME_MAPS,
      USER_DATA_FILES + name + "\\" + FILENAME_WORLD,
      USER_DATA_FILES + name + "\\" + FILENAME_PLAYER
    };
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Generate User Filenames           //////////////
  ////////////                                  //////////////
  ////////////////////////////////////////////////////////////
  
  private String[] UserFiles() 
  {
    return new String[] 
    {
      FILENAME_GAMEOBJECTS,
      FILENAME_ACTORS,
      FILENAME_MAPS,
      FILENAME_WORLD,
      FILENAME_PLAYER
    };
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Write all relevant session data   //////////////
  ////////////to json files                     //////////////
  ////////////////////////////////////////////////////////////
  
  public void WriteSessionToJSON(Main.Session session) 
  {
    String userName = session.CurrentUserName();
    
    // validate username
    if(UserFilesExist(userName) == 0) 
    {
      System.err.println("something is wrong with a user file!");
      _app.exit();
    }
    
    String[] paths = UserPaths(userName);
    String[] files = UserFiles();
    
    JSONArray  objectsJSON = session.GetGameObjectsExportedAsJSON(); 
    JSONArray  actorsJSON  = session.GetActorsExportedAsJSON();
    JSONArray  mapsJSON    = session.GetMapsExportedAsJSON();
    JSONObject worldJSON   = session.GetWorldExportedAsJSON(); 
    JSONObject playerJSON  = session.GetPlayerExportedAsJSON();
    
    for(int i = 0; i < paths.length; i += 1) 
    {     
      String path = paths[i]; 
      switch(files[i]) 
      {
        case FILENAME_GAMEOBJECTS:
          _app.saveJSONArray(objectsJSON, path);
        break;
        case FILENAME_ACTORS:
          _app.saveJSONArray(actorsJSON,path);
        break;
        case FILENAME_MAPS:
          _app.saveJSONArray(mapsJSON, path);
        break;
        case FILENAME_WORLD:
          _app.saveJSONObject(worldJSON, path);
        break;
        case FILENAME_PLAYER:
          _app.saveJSONObject(playerJSON, path);
        break;
      }     
    }    
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Read User JSON Files              //////////////
  ////////////                                  //////////////
  ////////////////////////////////////////////////////////////
  
  public UserFile ReadSessionFiles(String userName) 
  {
    // validate username
    if(UserFilesExist(userName) == 0) 
    {
      System.err.println("something is wrong with a user file!");
      _app.exit();
    }
    
    UserFile userFile;
    
    String[] paths = UserPaths(userName);
    String[] files = UserFiles();
    
    JSONArray objectsJSON = new JSONArray(); 
    JSONArray actorsJSON  = new JSONArray();
    JSONArray mapsJSON    = new JSONArray();
    JSONObject worldJSON  = new JSONObject();
    JSONObject playerJSON = new JSONObject();
      
    for(int i = 0; i < paths.length; i += 1) 
    {     
      String path = paths[i]; 
      switch(files[i]) 
      {
        case FILENAME_GAMEOBJECTS:
          objectsJSON = _app.loadJSONArray(path);
        break;
        case FILENAME_ACTORS:
          actorsJSON = _app.loadJSONArray(path);
        break;
        case FILENAME_MAPS:
          mapsJSON = _app.loadJSONArray(path);
        break;
        case FILENAME_WORLD:
          worldJSON = _app.loadJSONObject(path);
        break;
        case FILENAME_PLAYER:
          playerJSON = _app.loadJSONObject(path);
        break;
      }     
    }
    
    userFile = new UserFile(userName, objectsJSON, actorsJSON, mapsJSON, worldJSON, playerJSON);
    
    return userFile;
  }
 
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Create files for new User         //////////////
  ////////////from default files stored locally //////////////
  ////////////////////////////////////////////////////////////
  
  public int CreateUserFiles(String name) 
  { 
    name = name.toLowerCase();
    
    int success = 0;
    
    if(UserFolderExists(name) == false) 
    {
      success = 1;
      JSONArray  gameObjects = _app.loadJSONArray(CONFIG_JSON_PATH + FILENAME_GAMEOBJECTS);
      JSONArray  actors      = _app.loadJSONArray(CONFIG_JSON_PATH + FILENAME_ACTORS);
      JSONArray  maps        = _app.loadJSONArray(CONFIG_JSON_PATH+ FILENAME_MAPS);
      JSONObject world       = _app.loadJSONObject(CONFIG_JSON_PATH + FILENAME_WORLD);
      JSONObject player      = _app.loadJSONObject(CONFIG_JSON_PATH + FILENAME_PLAYER);
    
      _app.saveJSONArray(gameObjects, USER_DATA_FILES + name + "\\" + FILENAME_GAMEOBJECTS);
      _app.saveJSONArray(actors,      USER_DATA_FILES + name + "\\" + FILENAME_ACTORS);
      _app.saveJSONArray(maps,        USER_DATA_FILES + name + "\\" + FILENAME_MAPS);
      _app.saveJSONObject(world,      USER_DATA_FILES + name + "\\" + FILENAME_WORLD);
      _app.saveJSONObject(player,     USER_DATA_FILES + name + "\\" + FILENAME_PLAYER);
      
      AddUserToConfigFile(name);
    }
    return success;
  }
}
