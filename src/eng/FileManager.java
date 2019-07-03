package eng;
import processing.data.*;
import java.nio.file.*;
import def.Application;
import usr.*;

public class FileManager
{
    public static final String FILENAME_MAPS = "maps.json";
    public static final String FILENAME_WORLD = "world.json";
    public static final String FILENAME_ENTITIES = "entities.json";

    public static final String CONFIG_JSON_PATH = "\\data\\config\\json\\";
    public static final String CONFIG_PATH = "\\data\\config\\cfg.json";
    public static final String USER_DATA_FILES = "\\data\\users\\";

    private FileManager() {}

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
        while ((index += 1) < (userNames.size())) 
        {
            array.setString(index, userNames.getString(index));
        }
        array.setString(index, name);

        usersObject.setJSONArray("USERS", array);
        Application.PROCESSING.saveJSONObject(usersObject, CONFIG_PATH);
    }

    ////////////////////////////////////////////////////////////
    ////////////----------------------------------//////////////
    ////////////Confirms UserName does not        //////////////
    ////////////already point to local folder     //////////////
    ////////////////////////////////////////////////////////////

    public static boolean UserFolderExists(String name) 
    {
        String userPath = Application.PATH + USER_DATA_FILES + name.toLowerCase() + "\\";
        Path path = Paths.get(userPath);
        if (Files.exists(path)) 
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

    private static boolean UserFilesExist(String userName) 
    {
        String[] userPaths = UserPaths(userName);
        for (int i = 0; i < userPaths.length; i += 1) 
        {
            Path path = Paths.get(Application.PATH + userPaths[i]);
            if (Files.exists(path) != true) 
            {

                return false;
            }
        }
        return true;
    }

    ////////////////////////////////////////////////////////////
    ////////////----------------------------------//////////////
    ////////////Generate User Paths               //////////////
    ////////////                                  //////////////
    ////////////////////////////////////////////////////////////

    private static String[] UserPaths(String userName) 
    {
        String name = userName.toLowerCase();

        return new String[] 
        {
            USER_DATA_FILES + name + "\\" + FILENAME_MAPS,
         // USER_DATA_FILES + userName + "\\" + FILENAME_WORLD,
            USER_DATA_FILES + name + "\\" + FILENAME_ENTITIES,
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
         // FILENAME_WORLD,
            FILENAME_ENTITIES,
        };
    }

    ////////////////////////////////////////////////////////////
    ////////////----------------------------------//////////////
    ////////////Write all relevant session data   //////////////
    ////////////to json files                     //////////////
    ////////////////////////////////////////////////////////////

    public static void WriteSessionToFile(Session session)
    {
        String userName = session.CurrentUserName();

        if (UserFilesExist(userName) != true) 
        {
            System.err.println("error writing user file!");
            Application.PROCESSING.exit();
        }

        String[] paths = UserPaths(userName);
        String[] files = UserFiles();

        JSONArray entitiesJSON = session.EntitiesToJSONArray();
        JSONArray mapsJSON = session.MapsToJSONArray();

        for (int i = 0; i < paths.length; i += 1) 
        {
            String path = paths[i];
            switch (files[i]) 
            {
                case FILENAME_MAPS:
                    Application.PROCESSING.saveJSONArray(mapsJSON, path);
                    break;
                    //        case FILENAME_WORLD:
                    //        	Application.PROCESSING.saveJSONObject(worldJSON, path);
                    //        break;
                case FILENAME_ENTITIES:
                    Application.PROCESSING.saveJSONArray(entitiesJSON, path);
                    break;
                default:
                    break;
            }
        }
    }

    ////////////////////////////////////////////////////////////
    ////////////----------------------------------//////////////
    ////////////Read User JSON Files              //////////////
    ////////////                                  //////////////
    ////////////////////////////////////////////////////////////

    public static Session ReadSessionFromFile(String userName)
    {
        if (UserFilesExist(userName) != true) 
        {
            System.err.println("error reading user file!");
            Application.PROCESSING.exit();
        }

        Session session;

        String[] paths = UserPaths(userName);
        String[] files = UserFiles();

        JSONArray mapsJSON = new JSONArray();
        // JSONObject worldJSON  = new JSONObject();
        JSONArray entitiesJSON = new JSONArray();

        for (int i = 0; i < paths.length; i += 1) 
        {
            String path = paths[i];
            switch (files[i]) 
            {
                case FILENAME_MAPS:
                    mapsJSON = Application.PROCESSING.loadJSONArray(path);
                    break;
                    //      case FILENAME_WORLD:
                    //      	worldJSON = Application.PROCESSING.loadJSONObject(path);
                    //      break;
                case FILENAME_ENTITIES:
                    entitiesJSON = Application.PROCESSING.loadJSONArray(path);
                    break;
            }
        }

        session = new Session(userName, mapsJSON, entitiesJSON);

        return session;
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

        if (UserFolderExists(name) == false) 
        {
            success = 1;

            JSONArray maps = Application.PROCESSING.loadJSONArray(CONFIG_JSON_PATH + FILENAME_MAPS);
            //JSONObject world   = Application.PROCESSING.loadJSONObject(CONFIG_JSON_PATH + FILENAME_WORLD);
            JSONArray entities = Application.PROCESSING.loadJSONArray(CONFIG_JSON_PATH + FILENAME_ENTITIES);

            Application.PROCESSING.saveJSONArray(maps, USER_DATA_FILES + name + "\\" + FILENAME_MAPS);
            //Application.PROCESSING.saveJSONObject(world,   USER_DATA_FILES + name + "\\"  + FILENAME_WORLD);
            Application.PROCESSING.saveJSONArray(entities, USER_DATA_FILES + name + "\\" + FILENAME_ENTITIES);

            AddUserToConfigFile(name);
        }
        return success;
    }
}