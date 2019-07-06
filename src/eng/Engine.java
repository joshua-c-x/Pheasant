package eng;
import processing.core.PApplet;

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
  private float _timer, _delta;  
  private boolean _initialized, _fileBlock;
  IO _io;
  

  public Engine() 
  {
    _initialized = false; 
  }
  
  public void Update() 
  {
    Application.PROCESSING.background(255);
    
    _io.Update();
    
    // for debugging
    SendInputToConsole();
    
    if(_fileBlock == true) 
    {
      return;
    }
    
    if(SessionManager.ActiveSessionExists()) 
    {          
      IO io = new IO(_io);
      
      _delta = PApplet.abs(Application.PROCESSING.millis() - _timer);
      _timer = Application.PROCESSING.millis();
      
      SessionManager.ActiveSession.Update(io,_delta);
      
      // gc
      io = null;
      
      if(_io.KeysJustPressed.get("CONTROL") == true) 
      {
        
      }
    }
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
  
  public void Initialize() 
  {
    _io        = new IO();
    _fileBlock = false;
    _timer     = Application.PROCESSING.millis();
    
    Assets.LoadSprites();
    FileManager.InitializeFileManager();
    
    WriteDefaults();
    
    String userName = "example";
    long timeCreated = FileManager.Time.getTime();
    FileManager.AddNewUserToUsersFile(userName, timeCreated);
  
    Session session = null;
    		
    if(FileManager.Users.size() > 0) 
    {
    	ObjectNode user = FileManager.Users.get(0);
    	
    	String userFileUUID, memoryFileUUID;
    	
    	for(ObjectNode memory : FileManager.Memories) 
    	{
    		userFileUUID = user.path("fileUUID").asText();
    		memoryFileUUID = memory.findPath("fileUUID").asText();
    		
    		if(userFileUUID.equals(memoryFileUUID)) 
    		{
    			JsonNode entityContainerJsonNode = memory.path("entities");
    			session = new Session(entityContainerJsonNode);
    		}
    	}
    }  
    
  SessionManager.LoadSession(session);  
  _initialized = true;
  
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

