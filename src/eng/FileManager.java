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
  
  public static final String FILENAME_MAPS        = "maps.json";
  public static final String FILENAME_WORLD       = "world.json";
  public static final String FILENAME_ENTITIES    = "entities.json";
   
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
      USER_DATA_FILES + userName + "\\" + FILENAME_MAPS,
      USER_DATA_FILES + userName + "\\" + FILENAME_WORLD,
      USER_DATA_FILES + userName + "\\" + FILENAME_ENTITIES,
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
      FILENAME_MAPS,
      FILENAME_WORLD,
      FILENAME_ENTITIES,
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
    
    JSONArray  entitiesJSON = session.GetEntitiesExportedAsJSON();
    JSONArray  mapsJSON    = session.GetMapsExportedAsJSON();
    JSONObject worldJSON   = session.GetWorldExportedAsJSON(); 
    
    for(int i = 0; i < paths.length; i += 1) 
    {     
      String path = paths[i]; 
      switch(files[i]) 
      {
        case FILENAME_MAPS:
        	Application.PROCESSING.saveJSONArray(mapsJSON, path);
        break;
        case FILENAME_WORLD:
        	Application.PROCESSING.saveJSONObject(worldJSON, path);
        break;
        case FILENAME_ENTITIES:
        	Application.PROCESSING.saveJSONArray(entitiesJSON, path);
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
     
    JSONArray mapsJSON    = new JSONArray();
    JSONObject worldJSON  = new JSONObject();
    JSONArray entitiesJSON = new JSONArray();
      
    for(int i = 0; i < paths.length; i += 1) 
    {     
      String path = paths[i]; 
      switch(files[i]) 
      {
        case FILENAME_MAPS:
          mapsJSON = Application.PROCESSING.loadJSONArray(path);
        break;
        case FILENAME_WORLD:
          worldJSON = Application.PROCESSING.loadJSONObject(path);
        break;
        case FILENAME_ENTITIES:
          entitiesJSON = Application.PROCESSING.loadJSONArray(path);
        break;
      }     
    }
    
    userFile = new UserFile(userName, mapsJSON, worldJSON, entitiesJSON);
    
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

      JSONArray  maps    = Application.PROCESSING.loadJSONArray(CONFIG_JSON_PATH+ FILENAME_MAPS);
      JSONObject world   = Application.PROCESSING.loadJSONObject(CONFIG_JSON_PATH + FILENAME_WORLD);
      JSONArray entities = Application.PROCESSING.loadJSONArray(CONFIG_JSON_PATH + FILENAME_ENTITIES);
    
      Application.PROCESSING.saveJSONArray(maps,     USER_DATA_FILES + name + "\\"  + FILENAME_MAPS);
      Application.PROCESSING.saveJSONObject(world,   USER_DATA_FILES + name + "\\"  + FILENAME_WORLD);
      Application.PROCESSING.saveJSONArray(entities, USER_DATA_FILES + name + "\\" + FILENAME_ENTITIES);
      
      AddUserToConfigFile(name);
    }
    return success;
  }
}
