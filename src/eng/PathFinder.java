package eng;

import java.io.File;
import java.nio.file.Path;

public class PathFinder 
{
	private static String _appDir;
	
	public static String FILENAME_DEFAULT_USERS  = "def_users.json";
	public static String FILENAME_DEFAULT_WORLD  = "def_world.json";
	public static String FILENAME_DEFAULT_ENTITY = "def_entities.json";
	public static String FILENAME_DEFAULT_MAPS   = "def_maps.json";
	public static String FILENAME_DEFAULT_MEMORY = "def_memory.json";
	public static String FILENAME_MEMORIES       = "memories.json";
	public static String FILENAME_USERS          = "users.json";
	
	public static Path DIRECTORY;
	public static Path DIRECTORY_DATA;
	public static Path DIRECTORY_DATA_CONFIG;
	public static Path DIRECTORY_DATA_FONTS;
	public static Path DIRECTORY_DATA_GRAPHICS;

	public static Path FILE_USERS;
	public static Path FILE_MEMORIES;
	public static Path FILE_MEMORY_DEFAULT;
	public static Path FILE_WORLD_DEFAULT;
	public static Path FILE_ENTITY_DEFAULT;
	public static Path FILE_MAPS_DEFAULT;
	
	private static String _pathBuffer;
	
	public static void LoadPaths() 
	{
		_appDir = System.getProperty("user.dir");
		
		DIRECTORY = java.nio.file.Paths.get(_appDir);
		DIRECTORY_DATA = java.nio.file.Paths.get(_appDir, "data");
		DIRECTORY_DATA_CONFIG = java.nio.file.Paths.get(_appDir,"data", "config");
		DIRECTORY_DATA_FONTS = java.nio.file.Paths.get(_appDir,"data", "fonts");
		
		_pathBuffer = DIRECTORY_DATA + File.separator + FILENAME_USERS;
		FILE_USERS = java.nio.file.Paths.get(_pathBuffer);
		
		_pathBuffer = DIRECTORY_DATA + File.separator + FILENAME_MEMORIES;
		FILE_MEMORIES = java.nio.file.Paths.get(_pathBuffer);
		
		_pathBuffer = DIRECTORY_DATA_CONFIG + File.separator + FILENAME_DEFAULT_MEMORY;
		FILE_MEMORY_DEFAULT = java.nio.file.Paths.get(_pathBuffer);
		
		_pathBuffer = DIRECTORY_DATA_CONFIG + File.separator + FILENAME_DEFAULT_WORLD;
		FILE_WORLD_DEFAULT = java.nio.file.Paths.get(_pathBuffer);
		
		_pathBuffer = DIRECTORY_DATA_CONFIG + File.separator + FILENAME_DEFAULT_ENTITY;
		FILE_ENTITY_DEFAULT = java.nio.file.Paths.get(_pathBuffer);
		
		_pathBuffer = DIRECTORY_DATA_CONFIG + File.separator + FILENAME_DEFAULT_MAPS;
		FILE_MAPS_DEFAULT = java.nio.file.Paths.get(_pathBuffer);
		
		_pathBuffer = null;
	}
	
    ////////////////////////////////////////////////////////////
    ////////////----------------------------------//////////////
    ////////////Generate User Paths               //////////////
    ////////////                                  //////////////
    ////////////////////////////////////////////////////////////

    public static Path[] UserPaths(String userName) 
    {
        String name = userName.toLowerCase();

        String worldPath, entitiesPath, mapsPath;
        
        //worldPath = PathFinder.DIRECTORY_USERS + File.separator + name + File.separator + PathFinder.FILENAME_WORLD;
        entitiesPath = PathFinder.DIRECTORY_DATA_CONFIG + File.separator + name + File.separator + PathFinder.FILENAME_DEFAULT_ENTITY;
        mapsPath = PathFinder.DIRECTORY_DATA_CONFIG + File.separator + name + File.separator + PathFinder.FILENAME_DEFAULT_MAPS;
        
        Path world, entities, maps;
        
        //world = java.nio.file.Paths.get(worldPath);
        entities = java.nio.file.Paths.get(entitiesPath);
        maps = java.nio.file.Paths.get(mapsPath);
        
        return new Path[] { entities, maps };
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
            //FILENAME_WORLD,
            FILENAME_DEFAULT_ENTITY,
            FILENAME_DEFAULT_MAPS
        };
    }
}
