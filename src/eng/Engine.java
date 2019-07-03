package eng;
import processing.core.PApplet;
import def.Application;
import msg.*;
import usr.*;

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
        FileManager.WriteSessionToFile(SessionManager.ActiveSession);
        System.err.println("game saved.");
      }
    }
  }
  
  public void Initialize() 
  {
    _io        = new IO();
    _fileBlock = false;
    _timer     = Application.PROCESSING.millis();
    
    String userName = "example_user";
       
    
    if(FileManager.CreateUserFiles(userName) == 1) 
    {
    	System.err.println("successfully created new user.");
    }
    else 
    {
    	System.err.println("failed to create new user.");
    }
    
    Session session = FileManager.ReadSessionFromFile(userName);
    
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

