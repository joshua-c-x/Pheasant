package eng;

public final class Parameters 
{		
	/////////////////////////////////////////////
	//////// For local fields   /////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	
	public static final String UNDEFINED_STRING = "";
	
	public static final char UNDEFINED_CHAR= '~';
	
	/////////////////////////////////////////////
	//////// Characters Checked in IO ///////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	
	public static final char[] LOWERCASE    = new char[] { 'a','b','c','d','e','f','g','h','i','j','k','l','m',
	                                                   'n','o','p','q','r','s','t','u','v','w','x','y','z' };                          
	public static final char[] UPPERCASE    = new char[] { 'A','B','C','D','E','F','G','H','I','J','K','L','M',
	                                                   'N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
	public static final char[] MISC         = new char[] { '0','1','2','3','4','5','6','7','8','9','/',' ','.',
	                                                   '!','@','#','$','%','^','&','*','(',')','+','-','=','?'};
	
	/////////////////////////////////////////////
	//////// Keys Checked in IO /////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	
	public static final String Keys_Escape    = "ESCAPE";
	public static final String Keys_Enter     = "ENTER";
	public static final String Keys_Control   = "CONTROL";
	public static final String Keys_Delete    = "DELETE";
	public static final String Keys_Backspace = "BACKSPACE";
	public static final String Keys_Up        = "UP";
	public static final String Keys_Down      = "DOWN";
	public static final String Keys_Left      = "LEFT";
	public static final String Keys_Right     = "RIGHT";
	
	/////////////////////////////////////////////
	//////// IO Events          /////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	
	public static final String Events_Key_Released       = "KEY_RELEASED";
	public static final String Events_Key_Pressed        = "KEY_PRESSED";
	public static final String Events_Character_Pressed  = "CHARACTER_PRESSED";    
	public static final String Events_Character_Released = "CHARACTER_RELEASED";
	
	/////////////////////////////////////////////
	//////// Message Addresses  /////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
 
	public static final String Address_Factory = "FACTORY";
	public static final String Address_Engine  = "ENGINE";
	public static final String Address_Console = "CONSOLE";
	public static final String Address_Input   = "INPUT";
	public static final String Address_Action  = "ACTION";
	public static final String Address_Main    = "MAIN";
  
	/////////////////////////////////////////////
	//////// Message Flags      /////////////////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	
	public static final int Flag_Alert               = 0;
	public static final int Flag_Input               = 1;
	public static final int Flag_BuildObjectFromBox  = 2;
	public static final int Flag_BuildObjectFromName = 3;
	public static final int Flag_LoadObject          = 4;
  
	/////////////////////////////////////////////
	////// Rendering & Collision Constants  /////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	
	public static final int TileDimension = 32;
	public static final int MapX          = 20;
	public static final int MapY          = 15;	
	
	/////////////////////////////////////////////
	////// Entity Tags                    ///////
	/////////////////////////////////////////////
	/////////////////////////////////////////////
	
	public static final String TagType_Player     = "PLAYER";
	public static final String TagType_GameObject = "GAMEOBJECT";
	public static final String TagType_Actor      = "ACTOR";
	
}
