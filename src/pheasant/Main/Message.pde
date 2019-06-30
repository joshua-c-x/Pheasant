class Message 
{
  int Flag;
  String[] Box;
  String Address;
  String Description;
  
  // description is used for debugging
  
  Message(int flag, String address, String description) 
  {
    Flag = flag;
    Box = new String[0];
    Address = address;
    Description = description;
  }
  
  Message(int flag, String[] box, String address, String description) 
  {
    Flag = flag;
    Box = box;
    Address = address;
    Description = description;
  }
}

class IOMessage 
{
  // Character, Key, or Mouse
  public String _eventType;
  public String _keyPress;
  
  IOMessage(String eventType, String keyPress) 
  {
    _eventType = eventType;
    _keyPress  = keyPress;
  } 
  
  String EventType() { return _eventType; }
  String KeyPress() { return _keyPress; }
}
