package eng;
import processing.data.*;
import java.nio.file.*;
import def.Application;
import usr.*;

public class FileManager 
{
  ///
  /// This class uses a singleton pattern, see "Main.pde" for its initialization
  ///
  
  public static final String FILENAME_GAMEOBJECTS = "gameobjects.json";
  public static final String FILENAME_ACTORS      = "actors.json";
  public static final String FILENAME_MAPS        = "maps.json";
  public static final String FILENAME_WORLD       = "world.json";
  public static final String FILENAME_PLAYER      = "player.json";
   
  public static final String CONFIG_JSON_PATH     = "\\data\\config\\json\\";
  public static final String CONFIG_PATH          = "\\data\\config\\cfg.json";
  public static final String USER_DATA_FILES      = "\\data\\users\\";
  
  private FileManager(){}
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Adds UserName to cfg.json         //////////////
  ////////////                                  //////////////
  ////////////////////////////////////////////////////////////
  
  private static void AddUserToConfigFile(String name) 
  {
    name = name.toLowerCase();
    
    JSONObject usersObject = Application.PROCESSING.loadJSONObject(CONFIG_PATH);
    JSONArray userNames = usersObject.getJSONArray("USERS");
    JSONArray array = new JSONArray();
    
    int index = -1;
    while((index+=1) < (userNames.size())) 
    {
      array.setString(index,userNames.getString(index));
    }
    array.setString(index,name);  
    
    usersObject.setJSONArray("USERS", array);
    Application.PROCESSING.saveJSONObject(usersObject,CONFIG_PATH);
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Confirms UserName does not        //////////////
  ////////////already point to local folder     //////////////
  ////////////////////////////////////////////////////////////
  
  public static boolean UserFolderExists(String name) 
  {
    String userPath = Application.PROCESSING.sketchPath() + USER_DATA_FILES + name.toLowerCase() + "\\";
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
  
  private static int UserFilesExist(String userName) 
  {
    int filesExist = 1;
    String[] userPaths = UserPaths(userName.toLowerCase());
    String localPath = Application.PROCESSING.sketchPath();
    
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
  
  private static String[] UserPaths(String userName) 
  {  
    return new String[] 
    {
      USER_DATA_FILES + userName + "\\" + FILENAME_GAMEOBJECTS,
      USER_DATA_FILES + userName + "\\" + FILENAME_ACTORS,
      USER_DATA_FILES + userName + "\\" + FILENAME_MAPS,
      USER_DATA_FILES + userName + "\\" + FILENAME_WORLD,
      USER_DATA_FILES + userName + "\\" + FILENAME_PLAYER
    };
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Generate User Filenames           //////////////
  ////////////                                  //////////////
  ////////////////////////////////////////////////////////////
  
  private static String[] UserFiles() 
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
  
  public static void WriteSessionToJSON(Session session) 
  {
    String userName = session.CurrentUserName();
    
    // validate username
    if(UserFilesExist(userName) == 0) 
    {
      System.err.println("something is wrong with a user file!");
      Application.PROCESSING.exit();
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
        	Application.PROCESSING.saveJSONArray(objectsJSON, path);
        break;
        case FILENAME_ACTORS:
        	Application.PROCESSING.saveJSONArray(actorsJSON,path);
        break;
        case FILENAME_MAPS:
        	Application.PROCESSING.saveJSONArray(mapsJSON, path);
        break;
        case FILENAME_WORLD:
        	Application.PROCESSING.saveJSONObject(worldJSON, path);
        break;
        case FILENAME_PLAYER:
        	Application.PROCESSING.saveJSONObject(playerJSON, path);
        break;
      }     
    }    
  }
  
  ////////////////////////////////////////////////////////////
  ////////////----------------------------------//////////////
  ////////////Read User JSON Files              //////////////
  ////////////                                  //////////////
  ////////////////////////////////////////////////////////////
  
  public static UserFile ReadSessionFiles(String userName) 
  {
    // validate username
    if(UserFilesExist(userName) == 0) 
    {
      System.err.println("something is wrong with a user file!");
      Application.PROCESSING.exit();
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
          objectsJSON = Application.PROCESSING.loadJSONArray(path);
        break;
        case FILENAME_ACTORS:
          actorsJSON = Application.PROCESSING.loadJSONArray(path);
        break;
        case FILENAME_MAPS:
          mapsJSON = Application.PROCESSING.loadJSONArray(path);
        break;
        case FILENAME_WORLD:
          worldJSON = Application.PROCESSING.loadJSONObject(path);
        break;
        case FILENAME_PLAYER:
          playerJSON = Application.PROCESSING.loadJSONObject(path);
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
  
  public static int CreateUserFiles(String name) 
  { 
    name = name.toLowerCase();
    
    int success = 0;
    
    if(UserFolderExists(name) == false) 
    {
      success = 1;
      JSONArray  gameObjects = Application.PROCESSING.loadJSONArray(CONFIG_JSON_PATH + FILENAME_GAMEOBJECTS);
      JSONArray  actors      = Application.PROCESSING.loadJSONArray(CONFIG_JSON_PATH + FILENAME_ACTORS);
      JSONArray  maps        = Application.PROCESSING.loadJSONArray(CONFIG_JSON_PATH+ FILENAME_MAPS);
      JSONObject world       = Application.PROCESSING.loadJSONObject(CONFIG_JSON_PATH + FILENAME_WORLD);
      JSONObject player      = Application.PROCESSING.loadJSONObject(CONFIG_JSON_PATH + FILENAME_PLAYER);
    
      Application.PROCESSING.saveJSONArray(gameObjects, USER_DATA_FILES + name + "\\" + FILENAME_GAMEOBJECTS);
      Application.PROCESSING.saveJSONArray(actors,      USER_DATA_FILES + name + "\\" + FILENAME_ACTORS);
      Application.PROCESSING.saveJSONArray(maps,        USER_DATA_FILES + name + "\\" + FILENAME_MAPS);
      Application.PROCESSING.saveJSONObject(world,      USER_DATA_FILES + name + "\\" + FILENAME_WORLD);
      Application.PROCESSING.saveJSONObject(player,     USER_DATA_FILES + name + "\\" + FILENAME_PLAYER);
      
      AddUserToConfigFile(name);
    }
    return success;
  }
}
