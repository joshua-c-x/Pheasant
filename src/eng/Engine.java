package eng;
import processing.core.PApplet;

import ctx.*;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import def.Application;
import msg.*;
import usr.*;
import vis.Assets;

public class Engine
{ 
  private static int _contextResponse;
  private float _timer, _delta;  
  private boolean _initialized;
  IO _io;
  
  private static Context _mainMenu, _load, _create, _exit, _play;
  private static Context _currentContext;
  private boolean _love = false;
  
  public Engine() 
  {
    _initialized = false; 
    
    this._love = 
    this._love == !this._love 
    ? this._love : !this._love;
    

    _mainMenu    = new MainMenu();
    _load        = new Load();
    _create      = new Create();
    _exit        = new Exit();
    _play        = new Play();
  }
  
  public void Run() 
  {
	if(_initialized != true) 
	{
		return;
	} 
	
    Application.PROCESSING.background(255);
    
    _delta = PApplet.abs(Application.PROCESSING.millis() - _timer);
    _timer = Application.PROCESSING.millis();   
    _io.Update();
    
  
    
    IO io = new IO(_io);
    
    _contextResponse = _currentContext.Update(io, _delta);
    _currentContext.DrawContext(_delta);
    
    // for debugging
    SendInputToConsole();
    
    // gc
    io = null;
  }
  
  public void WriteDefaults() 
  {	
	  String json = "[]";
	  JsonNode jsonNode = null;  
	  try 
	  {
		jsonNode = FileManager.ObjMapper.readTree(json);
	  } 
	  catch (IOException a) 
	  {
		a.printStackTrace();
	  }
	  
		try 
		{
			FileManager.ObjWriter.writeValue(PathFinder.FILE_USERS.toFile(), json);
		} 
		catch (JsonGenerationException b) 
		{
			b.printStackTrace();
		} 
		catch (JsonMappingException c) 
		{
			c.printStackTrace();
		} 
		catch (IOException d) 
		{
			d.printStackTrace();
		}
		
		try 
		{
			FileManager.ObjWriter.writeValue(PathFinder.FILE_MEMORIES.toFile(), json);
		} 
		catch (JsonGenerationException b) 
		{
			b.printStackTrace();
		} 
		catch (JsonMappingException c) 
		{
			c.printStackTrace();
		} 
		catch (IOException d) 
		{
			d.printStackTrace();
		}
  }
  
  public void BeginContext(Context context) 
  {
	context.Open();
	_currentContext = context;
  }
  
  public void Initialize() 
  {
    _io        = new IO();
    _timer     = Application.PROCESSING.millis();
    
    Assets.LoadSprites();
    FileManager.InitializeFileManager();
    
    WriteDefaults();
    
    BeginContext(_mainMenu);
    
    // create a user for testing
    
    String userName;
    long timeCreated;
    
    userName = "joshua";
    timeCreated = FileManager.Time.getTime();
    FileManager.AddNewUserToUsersFile(userName, timeCreated);
    
    userName = "luke";
    timeCreated = FileManager.Time.getTime();
    FileManager.AddNewUserToUsersFile(userName, timeCreated);
    
    userName = "jared";
    timeCreated = FileManager.Time.getTime();
    FileManager.AddNewUserToUsersFile(userName, timeCreated);
    
    userName = "erin";
    timeCreated = FileManager.Time.getTime();
    FileManager.AddNewUserToUsersFile(userName, timeCreated);
    
    _initialized = true;
    
//    if(FileManager.Users.size() > 0) 
//    {
//    	ObjectNode user = FileManager.Users.get(0);
//    	
//    	String userFileUUID, memoryFileUUID;
//    	
//    	for(ObjectNode memory : FileManager.Memories) 
//    	{
//    		userFileUUID = user.path("fileUUID").asText();
//    		memoryFileUUID = memory.findPath("fileUUID").asText();
//    		
//    		if(userFileUUID.equals(memoryFileUUID)) 
//    		{
//    			JsonNode entityContainerJsonNode = memory.path("entities");
//    		}
//    	}
//    }     
//    
  }
  
  public boolean IsInitialized() 
  {
    return _initialized;
  }
  
  public void SendIO(IOMessage ioMessage) 
  {
    _io.BufferIOMessage(ioMessage);
  }
  
  public static void ErrorAndHalt(String error) 
  {
	  System.err.println(error);
	  Application.PROCESSING.exit();
  }
  
  private void SendInputToConsole() 
  {
      // *********this code is for unit testing the responsiveness of each character input ********
    char ioc = _io.GetLastCharacterPressed();
    
    if(_io.CharacterJustPressed(ioc))  { Application.println(ioc + " was just pressed."); }  
    
    if(_io.CharacterIsPressed(ioc))    { Application.println(ioc + " is being held."); } 
    
    if(_io.CharacterJustReleased(ioc)) { Application.println(ioc + " was just released."); }
    //*******************************************************************************************
   
    // *********this code is for unit testing the responsiveness of each key input **************
    String iok = _io.GetLastKeyPressed();
    
    if(_io.KeyJustPressed(iok))  { Application.println(iok + " was just pressed."); }
    
    if(_io.KeyIsPressed(iok))    { Application.println(iok + " is being held."); }
    
    if(_io.KeyJustReleased(iok)) { PApplet.println(iok + " was just released."); }
    // ******************************************************************************************
  }
}

