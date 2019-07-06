package def;
import processing.core.PApplet;
import processing.core.PFont;
import vis.Assets;
import eng.*;
import msg.IOMessage;

public class Application extends PApplet 
{
	PFont font_ibmthin4_16, font_atismall_8;
	
	public static PApplet PROCESSING;
	public static FileManager FILEMANAGER;
	public static Assets ASSETS;
	public static Engine ENGINE;
	public static String PATH;
	
	public static void main(String[] args) 
	{
		PApplet.main("def.Application");
	}
	
	public void settings()
	{
		size(800,600);
		PROCESSING = this;
		PathFinder.LoadPaths();
		PATH = System.getProperty("user.dir");
	}

	public void setup()
	{ 
	  font_ibmthin4_16 = createFont("fonts/ibmps2thin4.ttf", 16);
	  font_atismall_8  = createFont("fonts/atismall.ttf", 8);
	  
	  rectMode(CORNER);
	  imageMode(CORNER);
	  ellipseMode(CORNER);
	  
	  Assets.LoadSprites();
	  
	  ENGINE = new Engine();
	  ENGINE.Initialize();
	}

	@Override
	public void draw()
	{
		ENGINE.Run();
	}

	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	/////////////[ Event Listening: Key or Char Pressed ]///////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	
	@Override
	public void keyPressed() 
	{   
	  if(ENGINE.IsInitialized() == false) 
	  {
	    return;
	  }
	  
	  IOMessage ioMessage;
	  String keyPress, eventType; 
	  
	  keyPress = Parameters.UNDEFINED_STRING;
	  eventType = Parameters.Events_Key_Pressed;
	  
	  if(key == ESC || key == 27)
	  { 
	    keyPress = Parameters.Keys_Escape; 
	    key = 0; 
	    // setting key to 0 will catch automatic exit();
	  }
	  
	  else if(key == ENTER || key == RETURN ) { keyPress = Parameters.Keys_Enter;     }
	  else if(key == BACKSPACE ) { keyPress = Parameters.Keys_Backspace; }
	  else if(key == DELETE ) { keyPress = Parameters.Keys_Delete;    }  
	  
	  if (key == CODED) 
	  {
	    if (keyCode == UP) { keyPress = Parameters.Keys_Up;    }
	    else if (keyCode == DOWN  ) { keyPress = Parameters.Keys_Down;  }
	    else if (keyCode == LEFT  ) { keyPress = Parameters.Keys_Left;  } 
	    else if (keyCode == RIGHT ) { keyPress = Parameters.Keys_Right; }
	    else if (keyCode == CONTROL) { keyPress = Parameters.Keys_Control; }
	  }
	  
	  if(keyPress.equals(Parameters.UNDEFINED_STRING) == true) 
	  {
	    String s = ProcessCharacterInput(key);
	    if(s != null && s != Parameters.UNDEFINED_STRING) 
	    {
	      keyPress = s;
	      eventType = Parameters.Events_Character_Pressed;
	    }
	  }
	  if(keyPress.equals(Parameters.UNDEFINED_STRING) == false) 
	  {
	      ioMessage = new IOMessage( eventType, keyPress );
	      ENGINE.SendIO(ioMessage);
	  } 
	}

	
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	/////////////[ Event Listening: Key or Char Released ]//////////
	////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	
	@Override
	public void keyReleased() 
	{    
	  if(ENGINE.IsInitialized() == false) 
	  {
	    return;
	  }
	  
	  IOMessage ioMessage;
	  String keyPress, eventType; 
	  
	  keyPress = "";

	  eventType = Parameters.Events_Key_Released;
	  
	  if(key == ESC || key == 27)
	  { 
	    keyPress = Parameters.Keys_Escape; 
	    // catch automatic exit();
	    key = 0; 
	  }
	  else if(key == ENTER || key == RETURN ) { keyPress = Parameters.Keys_Enter;     }
	  else if(key == BACKSPACE ) { keyPress = Parameters.Keys_Backspace; }
	  else if(key == DELETE ) { keyPress = Parameters.Keys_Delete;    }  
	  
	  if (key == CODED) 
	  {
	    if (keyCode == UP) { keyPress = Parameters.Keys_Up; }
	    else if (keyCode == DOWN  ) { keyPress = Parameters.Keys_Down; }
	    else if (keyCode == LEFT  ) { keyPress = Parameters.Keys_Left; } 
	    else if (keyCode == RIGHT ) { keyPress = Parameters.Keys_Right; }
	    else if(keyCode == ALT || keyCode == CONTROL)                   
	    { 
	      keyPress = Parameters.Keys_Control;     
	    }
	  }
	  if(keyPress.equals(Parameters.UNDEFINED_STRING)) 
	  {
	    String s = ProcessCharacterInput(key);
	    if(s != null && s != "") 
	    {
	      keyPress = s;
	      eventType = Parameters.Events_Character_Released;
	    }
	  }
	  if(keyPress.equals(Parameters.UNDEFINED_STRING) == false) 
	  {
	      ioMessage = new IOMessage( eventType, keyPress );
	      ENGINE.SendIO(ioMessage);
	  } 
	}

	private String ProcessCharacterInput(char c) 
	{
	  String s = "";
	  for(int i = 0; i < Parameters.LOWERCASE.length; i += 1) 
	  {
	    if(Parameters.LOWERCASE[i] == c) 
	    {
	      s = String.valueOf(c);
	    }
	  }
	  for(int i = 0; i < Parameters.UPPERCASE.length; i += 1) 
	  {
	    if(Parameters.UPPERCASE[i] == c) 
	    {
	      s = String.valueOf(c);
	    }
	  }
	  for(int i = 0; i < Parameters.MISC.length; i += 1) 
	  {
	    if(Parameters.MISC[i] == c) 
	    {
	      s = String.valueOf(c);
	    }
	  }
	  return s;
	}
}
