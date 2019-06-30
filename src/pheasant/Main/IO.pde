private class IO
{  
  private String[] _keys;
  private String _lastKeyPressed;
  private char _lastCharacterPressed;
  
  private LinkedList<IOMessage> _ioMessages;
  
  public String ConsoleInput;

  public HashMap<Character,Boolean> CharactersPressed;
  public HashMap<Character,Boolean> CharactersHeld;
  public HashMap<Character,Boolean> CharactersJustPressed;
  public HashMap<Character,Boolean> CharactersJustReleased;

  public HashMap<String,Boolean> KeysPressed;
  public HashMap<String,Boolean> KeysHeld;
  public HashMap<String,Boolean> KeysJustPressed;
  public HashMap<String,Boolean> KeysJustReleased;
  
  private ArrayList<HashMap<String,Boolean>> KeyMaps;
  private ArrayList<HashMap<Character,Boolean>> CharacterMaps;
  
  public IO()
  {
    super();
 
    _lastKeyPressed       = UNDEFINED_STRING;
    _lastCharacterPressed = UNDEFINED_CHAR;
    
    _ioMessages  = new LinkedList<IOMessage>();
   
    ConsoleInput = "";
    
    KeysPressed      = new HashMap<String,Boolean>();
    KeysHeld         = new HashMap<String,Boolean>();
    KeysJustPressed  = new HashMap<String,Boolean>();
    KeysJustReleased = new HashMap<String,Boolean>();   
    KeyMaps = new ArrayList<HashMap<String,Boolean>>();
    KeyMaps.add(KeysPressed);
    KeyMaps.add(KeysHeld);
    KeyMaps.add(KeysJustPressed);
    KeyMaps.add(KeysJustPressed);
    
    CharactersPressed      = new HashMap<Character,Boolean>();
    CharactersHeld         = new HashMap<Character,Boolean>();
    CharactersJustPressed  = new HashMap<Character,Boolean>();
    CharactersJustReleased = new HashMap<Character,Boolean>();
    CharacterMaps = new ArrayList<HashMap<Character,Boolean>>();
    CharacterMaps.add(CharactersPressed);
    CharacterMaps.add(CharactersHeld);
    CharacterMaps.add(CharactersJustPressed);
    CharacterMaps.add(CharactersJustPressed);
    
    _keys = new String[] 
    {
      Engine.Keys_Escape,   
      Engine.Keys_Enter,    
      Engine.Keys_Control,  
      Engine.Keys_Delete,   
      Engine.Keys_Backspace,
      Engine.Keys_Up,       
      Engine.Keys_Down,   
      Engine.Keys_Left,
      Engine.Keys_Right
    };
    
    for(String s : _keys) 
    {
      KeysPressed.put(s, false);
      KeysHeld.put(s,false);
      KeysJustPressed.put(s, false);
      KeysJustReleased.put(s, false);
    }

    ResetCharacterHashMaps(CharacterMaps);
  }
  
  public IO(IO io) 
  {
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
    
    //Console input
    ConsoleInput = io.ConsoleInput;
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
  
  public void Reset() 
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
      for(int i = 0; i < LOWERCASE.length; i += 1) 
      { 
        char c = LOWERCASE[i];
        map.put(c, false);    
      }
      for(int i = 0; i < UPPERCASE.length; i += 1) 
      {
        char c = UPPERCASE[i];
        map.put(c, false); 
      }
      for(int i = 0; i < MISC.length; i += 1) 
      {
        char c = MISC[i];
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
  
  public void ReadIOMessage(IOMessage message) 
  {
    String _eventType = message.EventType();
    String _keyPress = message.KeyPress();
    
    switch(_eventType) 
    {
      case Engine.Events_Key_Released:
        OnKeyReleased(_keyPress);
        break;
      case Engine.Events_Key_Pressed:
        OnKeyPressed(_keyPress);
        break;
      case Engine.Events_Character_Released:
        OnCharacterReleased(_keyPress);
        break;
      case Engine.Events_Character_Pressed:
        OnCharacterPressed(_keyPress);
        break;
    }
  }
  
  void OnKeyReleased(String s) 
  {
    KeysPressed.remove(s);
    KeysPressed.put(s,false);    
    KeysHeld.remove(s);
    KeysHeld.put(s,false);    
    KeysJustReleased.remove(s);
    KeysJustReleased.put(s,true); 
  }
  
  void OnKeyPressed(String s) 
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
  
  void OnCharacterReleased(String s) 
  {
    char c = s.charAt(0);
    
    CharactersHeld.remove(c);
    CharactersHeld.put(c,false); 
    CharactersPressed.remove(c);
    CharactersPressed.put(c,false);
    CharactersJustReleased.remove(c);
    CharactersJustReleased.put(c,true);  
  } 
  
  void OnCharacterPressed(String s) 
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
