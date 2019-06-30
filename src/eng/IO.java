package eng;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import msg.IOMessage;

public class IO
{ 
  ///////////////////////////////////////////////////////////////////////
  ////////////  stores which keys to listen for /////////////////////////
  ///////////////////////////////////////////////////////////////////////
  private String[] _keys;
  
  ///////////////////////////////////////////////////////////////////////
  ////   helps determine which key was pressed on the current frame  ////
  ///////////////////////////////////////////////////////////////////////
  private String _lastKeyPressed;
  private char   _lastCharacterPressed;
  
  ///////////////////////////////////////////////////////////////////////
  ////////////  stores messages directly linked to Input/Output   ///////
  ///////////////////////////////////////////////////////////////////////
  private LinkedList<IOMessage> _ioMessages;

  ///////////////////////////////////////////////////////////////////////
  //////////// input the user has provided from the console   ///////////
  ///////////////////////////////////////////////////////////////////////
  public String ConsoleInput;
  
  ///////////////////////////////////////////////////////////////////////
  ////////stores character states for JustPressed, Held, & Released//////
  ///////////////////////////////////////////////////////////////////////
  public HashMap<Character,Boolean> CharactersPressed;
  public HashMap<Character,Boolean> CharactersHeld;
  public HashMap<Character,Boolean> CharactersJustPressed;
  public HashMap<Character,Boolean> CharactersJustReleased;
  
  ///////////////////////////////////////////////////////////////////////
  ////////    stores key states for JustPressed, Held, & Released   /////
  ///////////////////////////////////////////////////////////////////////
  public HashMap<String,Boolean> KeysPressed;
  public HashMap<String,Boolean> KeysHeld;
  public HashMap<String,Boolean> KeysJustPressed;
  public HashMap<String,Boolean> KeysJustReleased;

  ///////////////////////////////////////////////////////////////////////
  ////////    stores key states for JustPressed, Held, & Released   /////
  ///////////////////////////////////////////////////////////////////////
  private ArrayList<HashMap<String,Boolean>> KeyMaps;
  private ArrayList<HashMap<Character,Boolean>> CharacterMaps;
  
  public IO()
  {
    super();
 
    _lastKeyPressed       = Parameters.UNDEFINED_STRING;
    _lastCharacterPressed = Parameters.UNDEFINED_CHAR;
    _ioMessages  = new LinkedList<IOMessage>();
    
    ConsoleInput = "";
    
    KeysPressed      = new HashMap<String,Boolean>();
    KeysHeld         = new HashMap<String,Boolean>();
    KeysJustPressed  = new HashMap<String,Boolean>();
    KeysJustReleased = new HashMap<String,Boolean>(); 
    
    CharactersPressed      = new HashMap<Character,Boolean>();
    CharactersHeld         = new HashMap<Character,Boolean>();
    CharactersJustPressed  = new HashMap<Character,Boolean>();
    CharactersJustReleased = new HashMap<Character,Boolean>();
   
    _keys = new String[] 
    {
      Parameters.Keys_Escape,   
      Parameters.Keys_Enter,    
      Parameters.Keys_Control,  
      Parameters.Keys_Delete,   
      Parameters.Keys_Backspace,
      Parameters.Keys_Up,       
      Parameters.Keys_Down,   
      Parameters.Keys_Left,
      Parameters.Keys_Right
    };
    	        
    KeyMaps = new ArrayList<HashMap<String,Boolean>>();
    KeyMaps.add(KeysPressed);
    KeyMaps.add(KeysHeld);
    KeyMaps.add(KeysJustPressed);
    KeyMaps.add(KeysJustPressed);
    
    CharacterMaps = new ArrayList<HashMap<Character,Boolean>>();
    CharacterMaps.add(CharactersPressed);
    CharacterMaps.add(CharactersHeld);
    CharacterMaps.add(CharactersJustPressed);
    CharacterMaps.add(CharactersJustPressed);
    
    ResetKeyHashMaps(KeyMaps);
    ResetCharacterHashMaps(CharacterMaps);
  }
  
  public IO(IO io) 
  {
	//Console input
	ConsoleInput = io.ConsoleInput;
	    
    // Keys
    KeysPressed      = io.KeysPressed;
    KeysHeld         = io.KeysHeld;
    KeysJustPressed  = io.KeysJustPressed;
    KeysJustReleased = io.KeysJustReleased;

    // Characters
    CharactersPressed      = io.CharactersPressed;
    CharactersHeld         = io.CharactersHeld;
    CharactersJustPressed  = io.CharactersJustPressed;
    CharactersJustReleased = io.CharactersJustReleased;
  }

  public void Update() 
  {
    Reset();
    while(_ioMessages.size() > 0) 
    {
      IOMessage message = _ioMessages.pop();
      ReadIOMessage(message);
    }
  }
 

  public boolean CharacterIsPressed(char c) 
  {
    Boolean b = this.CharactersPressed.get(c);
    if(b != null) { return b; }
    return false;
  }
  
  public boolean CharacterJustReleased(char c) 
  {
    Boolean b = this.CharactersJustReleased.get(c);
    if(b != null) { return b; }
    return false;
  }
  
  public boolean CharacterJustPressed(char c) 
  {
    Boolean b = this.CharactersJustPressed.get(c);
    if(b != null) { return b; }
    return false;
  }
  
  public boolean KeyIsPressed(String k) 
  {
    Boolean b = this.KeysPressed.get(k);
    if(b != null) { return b; }
    return false;
  }
  
  public boolean KeyJustPressed(String k) 
  {
    Boolean b = this.KeysJustPressed.get(k);
    if(b != null) { return b; }
    return false;
  }
  
  public boolean KeyJustReleased(String k) 
  {
    Boolean b = this.KeysJustReleased.get(k);
    if(b != null) { return b; }
    return false;
  }
  
  public String GetLastKeyPressed() 
  {
    return _lastKeyPressed;
  }
  
  public char GetLastCharacterPressed() 
  {
    return _lastCharacterPressed;
  }
  
  public void BufferIOMessage(IOMessage ioMessage) 
  {
    _ioMessages.add(ioMessage);
  }
  
  private void Reset() 
  {
    ArrayList<HashMap<Character,Boolean>> cmaps = new ArrayList<HashMap<Character,Boolean>>();
    cmaps.add(CharactersJustPressed);
    cmaps.add(CharactersJustReleased);
    
    ResetCharacterHashMaps(cmaps);
    
    ArrayList<HashMap<String,Boolean>> kmaps = new ArrayList<HashMap<String,Boolean>>();
    kmaps.add(KeysJustPressed);
    kmaps.add(KeysJustReleased);
    
    ResetKeyHashMaps(kmaps);
  }
  
  private void ResetCharacterHashMaps(ArrayList<HashMap<Character,Boolean>> charMaps) 
  {
    for(HashMap<Character,Boolean> map : charMaps) 
    {
      for(int i = 0; i < Parameters.LOWERCASE.length; i += 1) 
      { 
        char c = Parameters.LOWERCASE[i];
        map.put(c, false);    
      }
      for(int i = 0; i < Parameters.UPPERCASE.length; i += 1) 
      {
        char c = Parameters.UPPERCASE[i];
        map.put(c, false); 
      }
      for(int i = 0; i < Parameters.MISC.length; i += 1) 
      {
        char c = Parameters.MISC[i];
        map.put(c, false); 
      }
    }
  }
  
  private void ResetKeyHashMaps(ArrayList<HashMap<String,Boolean>> maps) 
  { 
    for(HashMap<String,Boolean> map : maps) 
    {
      for(String s : _keys) 
      {
        map.put(s, false);
      }
    }
  }
  
  private void ReadIOMessage(IOMessage message) 
  {
    String _eventType = message.EventType();
    String _keyPress = message.KeyPress();
    
    switch(_eventType) 
    {
      case Parameters.Events_Key_Released:
        OnKeyReleased(_keyPress);
        break;
      case Parameters.Events_Key_Pressed:
        OnKeyPressed(_keyPress);
        break;
      case Parameters.Events_Character_Released:
        OnCharacterReleased(_keyPress);
        break;
      case Parameters.Events_Character_Pressed:
        OnCharacterPressed(_keyPress);
        break;
    }
  }
  
  private void OnKeyReleased(String s) 
  {
    KeysPressed.remove(s);
    KeysPressed.put(s,false);    
    KeysHeld.remove(s);
    KeysHeld.put(s,false);    
    KeysJustReleased.remove(s);
    KeysJustReleased.put(s,true); 
  }
  
  private void OnKeyPressed(String s) 
  {
    KeysPressed.remove(s);
    KeysPressed.put(s,true);
    
    if(KeysHeld.get(s) == false) 
    {
      KeysJustPressed.remove(s);
      KeysJustPressed.put(s,true);      
      KeysHeld.remove(s);
      KeysHeld.put(s,true);
      
      _lastKeyPressed = s;
    }
  }
  
  private void OnCharacterReleased(String s) 
  {
    char c = s.charAt(0);
    
    CharactersHeld.remove(c);
    CharactersHeld.put(c,false); 
    CharactersPressed.remove(c);
    CharactersPressed.put(c,false);
    CharactersJustReleased.remove(c);
    CharactersJustReleased.put(c,true);  
  } 
  
  private void OnCharacterPressed(String s) 
  {
    char c = s.charAt(0);
    
    CharactersPressed.remove(c);
    CharactersPressed.put(c,true);
    
    if(CharactersHeld.get(c) == false) 
    {
      CharactersJustPressed.remove(c);
      CharactersJustPressed.put(c,true);      
      CharactersHeld.remove(c);
      CharactersHeld.put(c,true);
      
      _lastCharacterPressed = c;
    }
  }
}
