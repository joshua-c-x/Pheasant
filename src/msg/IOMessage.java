package msg;

public class IOMessage 
{
  // Character, Key, or Mouse
  public String _eventType;
  public String _keyPress;
  
  public IOMessage(String eventType, String keyPress) 
  {
    _eventType = eventType;
    _keyPress  = keyPress;
  } 
  
  public String EventType() { return _eventType; }
  public String KeyPress() { return _keyPress; }
}