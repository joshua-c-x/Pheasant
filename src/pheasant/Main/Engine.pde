class Engine implements IMessaging
{   
  static final String Events_Key_Released       = "KEY_RELEASED";
  static final String Events_Key_Pressed        = "KEY_PRESSED";
  static final String Events_Character_Pressed  = "CHARACTER_PRESSED";    
  static final String Events_Character_Released = "CHARACTER_RELEASED";
 
  static final String Keys_Escape    = "ESCAPE";
  static final String Keys_Enter     = "ENTER";
  static final String Keys_Control   = "CONTROL";
  static final String Keys_Delete    = "DELETE";
  static final String Keys_Backspace = "BACKSPACE";
  static final String Keys_Up        = "UP";
  static final String Keys_Down      = "DOWN";
  static final String Keys_Left      = "LEFT";
  static final String Keys_Right     = "RIGHT";
 
  static final String Address_Factory = "FACTORY";
  static final String Address_Engine  = "ENGINE";
  static final String Address_Console = "CONSOLE";
  static final String Address_Input   = "INPUT";
  static final String Address_Action  = "ACTION";
  static final String Address_Main    = "MAIN";
  
  static final int Flag_Alert               = 0;
  static final int Flag_Input               = 1;
  static final int Flag_BuildObjectFromBox  = 2;
  static final int Flag_BuildObjectFromName = 3;
  static final int Flag_LoadObject          = 4;
  
  static final int TileDimension = 32;
  static final int MapX          = 20;
  static final int MapY          = 15;
  
  private float _timer, _delta;
  
  private boolean _initialized, _fileBlock;
  
  IO _io;
  
  SessionManager _sessionManager;
  
  Engine() 
  {
    _initialized = false; 
  }

  void SendIO(IOMessage ioMessage) 
  {
    _io.BufferIOMessage(ioMessage);
  }
  
  public void ReadMessage(Message message) 
  {
  }
  
  public LinkedList<Message> GetMessages() 
  {
    return new LinkedList<Message>();
  }
  
  void Update() 
  {
    background(255);
    
    _io.Update();
    
    // for debugging
    SendInputToConsole();
    
    if(_fileBlock == true) 
    {
      return;
    }
    
    if(_sessionManager.ActiveSessionExists()) 
    {    
      IO io = new IO(_io);
      
      _delta = abs(millis() - _timer);
      _timer = millis();
      
      _sessionManager.ActiveSession().Update(io,_delta);
      
      // gc
      io = null;
      
      if(_io.KeysJustPressed.get("CONTROL") == true) 
      {
        FILEHANDLER.WriteSessionToJSON(_sessionManager.ActiveSession());
        _sessionManager.Close();
        println("game saved.");
      }
    }
  }
  
  void Initialize() 
  {
    _io        = new IO();
    _fileBlock = false;
    _timer     = millis();
    
    String userName = "tssst";
   
    
    
    if(FILEHANDLER.CreateUserFiles(userName) == 1) 
    {
      println("successfully created new user.");
    }
    else 
    {
      println("failed to create new user.");
    }
    
    FileHandler.UserFile userFile = FILEHANDLER.ReadSessionFiles(userName);
    
    _sessionManager = new SessionManager();
    _sessionManager.LoadSessionFromUserFile(userFile);
    
    _initialized = true;
  }
  
  void SaveSession() 
  {
    if(_sessionManager.ActiveSessionExists())
    {     
      FILEHANDLER.WriteSessionToJSON(_sessionManager.ActiveSession());
      _sessionManager.Close();
    }
  }
  
  boolean IsInitialized() 
  {
    return _initialized;
  }
  
  void SendInputToConsole() 
  {
      // *********this code is for unit testing the responsiveness of each character input ********
    char ioc = _io.GetLastCharacterPressed();
    
    if(_io.CharacterJustPressed(ioc))  { println(ioc + " was just pressed."); }  
    
    if(_io.CharacterIsPressed(ioc))    { println(ioc + " is being held."); } 
    
    if(_io.CharacterJustReleased(ioc)) { println(ioc + " was just released."); }
    //*******************************************************************************************
    
    // *********this code is for unit testing the responsiveness of each key input **************
    String iok = _io.GetLastKeyPressed();
    
    if(_io.KeyJustPressed(iok))  { println(iok + " was just pressed."); }
    
    if(_io.KeyIsPressed(iok))    { println(iok + " is being held."); }
    
    if(_io.KeyJustReleased(iok)) { println(iok + " was just released."); }
    // ******************************************************************************************
  } 
}

public interface IMessaging 
{
  public LinkedList<Message> GetMessages();
  
  public void ReadMessage(Message message);
}
