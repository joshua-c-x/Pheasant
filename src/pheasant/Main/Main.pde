import java.util.HashMap;
import java.util.LinkedList;


// Restrict keyboard detection to these characters >

public static final String UNDEFINED_STRING = "";

public static final char UNDEFINED_CHAR= '~';

public static final char[] LOWERCASE    = new char[] { 'a','b','c','d','e','f','g','h','i','j','k','l','m',
                                                       'n','o','p','q','r','s','t','u','v','w','x','y','z' };                          
public static final char[] UPPERCASE    = new char[] { 'A','B','C','D','E','F','G','H','I','J','K','L','M',
                                                       'N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
public static final char[] MISC         = new char[] { '0','1','2','3','4','5','6','7','8','9','/',' ','.',
                                                       '!','@','#','$','%','^','&','*','(',')','+','-','=','?'};

PFont font_ibmthin4_16, 
      font_atismall_8;
public static Assets ASSETS;
public static FileHandler FILEHANDLER;
public static Engine e;
void setup() 
{
  size(800,600);
  frameRate(30);
  fill(0);
  
  font_ibmthin4_16 = createFont("fonts/ibmps2thin4.ttf", 16);
  font_atismall_8  = createFont("fonts/atismall.ttf", 8);
  
  rectMode(CORNER);
  imageMode(CORNER);
  ellipseMode(CORNER);
  
  Assets ASSETS = Assets.CreateGlobal(this);
  FileHandler FILEHANDLER = Assets.CreateGlobal(this);
  ASSETS.LoadImages();
  
  e = new Engine();
  e.Initialize();
}

void draw() 
Se.Update();
  
}

void keyPressed() 
{   
  if(e.IsInitialized() == false) 
  {
    return;
  }
  
  IOMessage ioMessage;
  String keyPress, eventType; 
  
  keyPress = UNDEFINED_STRING;
  eventType = Engine.Events_Key_Pressed;
  
  if(key == ESC || key == 27)
  { 
    keyPress = Engine.Keys_Escape; 
    // catch automatic exit();
    key = 0; 
  }
  else if(key == ENTER || key == RETURN ) { keyPress = Engine.Keys_Enter;     }
  else if(key == BACKSPACE )              { keyPress = Engine.Keys_Backspace; }
  else if(key == DELETE )                 { keyPress = Engine.Keys_Delete;    }  
  
  if (key == CODED) 
  {
    if (keyCode == UP)          { keyPress = Engine.Keys_Up;    }
    else if (keyCode == DOWN  ) { keyPress = Engine.Keys_Down;  }
    else if (keyCode == LEFT  ) { keyPress = Engine.Keys_Left;  } 
    else if (keyCode == RIGHT ) { keyPress = Engine.Keys_Right; }
    else if(keyCode == ALT || keyCode == CONTROL)                   
    { 
      keyPress = Engine.Keys_Control;     
    }
  }
  
  if(keyPress.equals(UNDEFINED_STRING) == true) 
  {
    String s = ProcessCharacterInput(key);
    if(s != null && s != UNDEFINED_STRING) 
    {
      keyPress = s;
      eventType = Engine.Events_Character_Pressed;
    }
  }
  if(keyPress.equals(UNDEFINED_STRING) == false) 
  {
      ioMessage = new IOMessage( eventType, keyPress );
      e.SendIO(ioMessage);
  } 
}

void keyReleased() 
{    
  if(e.IsInitialized() == false) 
  {
    return;
  }
  
  IOMessage ioMessage;
  String keyPress, eventType; 
  
  keyPress = "";

  eventType = Engine.Events_Key_Released;
  
  if(key == ESC || key == 27)
  { 
    keyPress = Engine.Keys_Escape; 
    // catch automatic exit();
    key = 0; 
  }
  else if(key == ENTER || key == RETURN ) { keyPress = Engine.Keys_Enter;     }
  else if(key == BACKSPACE )              { keyPress = Engine.Keys_Backspace; }
  else if(key == DELETE )                 { keyPress = Engine.Keys_Delete;    }  
  
  if (key == CODED) 
  {
    if (keyCode == UP)          { keyPress = Engine.Keys_Up; }
    else if (keyCode == DOWN  ) { keyPress = Engine.Keys_Down; }
    else if (keyCode == LEFT  ) { keyPress = Engine.Keys_Left; } 
    else if (keyCode == RIGHT ) { keyPress = Engine.Keys_Right; }
    else if(keyCode == ALT || keyCode == CONTROL)                   
    { 
      keyPress = Engine.Keys_Control;     
    }
  }
  if(keyPress.equals(UNDEFINED_STRING)) 
  {
    String s = ProcessCharacterInput(key);
    if(s != null && s != "") 
    {
      keyPress = s;
      eventType = Engine.Events_Character_Released;
    }
  }
  if(keyPress.equals(UNDEFINED_STRING) == false) 
  {
      ioMessage = new IOMessage( eventType, keyPress );
      e.SendIO(ioMessage);
  } 
}

String ProcessCharacterInput(char c) 
{
  String s = "";
  for(int i = 0; i < LOWERCASE.length; i += 1) 
  {
    if(LOWERCASE[i] == c) 
    {
      s = String.valueOf(c);
    }
  }
  for(int i = 0; i < UPPERCASE.length; i += 1) 
  {
    if(UPPERCASE[i] == c) 
    {
      s = String.valueOf(c);
    }
  }
  for(int i = 0; i < MISC.length; i += 1) 
  {
    if(MISC[i] == c) 
    {
      s = String.valueOf(c);
    }
  }
  return s;
}
