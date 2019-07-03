
package eng;
import processing.core.*;
import res.Sprite;

import java.util.HashMap;
import def.Application;

public class Assets 
{  
  /////////////////////////////////////////////
  ////// Sprite IDs                     ///////
  /////////////////////////////////////////////
  /////////////////////////////////////////////
	
  public static final int SpriteID_Player  = 1;
  public static final int SpriteID_Markers = 2;
  public static final int SpriteID_Orders  = 3;
  public static final int SpriteID_Poo     = 4;

  /////////////////////////////////////////////
  ////// Generic Animation Names        ///////
  /////////////////////////////////////////////
  /////////////////////////////////////////////
  
  public static final String Animations_Idle      = "idle";
  public static final String Animations_WalkUp    = "walkup";
  public static final String Animations_WalkDown  = "walkdown";
  public static final String Animations_WalkLeft  = "walkleft";
  public static final String Animations_WalkRight = "walkright";
  
  public static final String SpriteSheet_Player_Path    = "\\data\\graphics\\actors\\player\\player_spritesheet.png";
  public static final int SpriteSheet_Player_Columns = 4;
  public static final int SpriteSheet_Player_Rows    = 3;
  
  public static HashMap<Integer, Sprite> Sprites; 
  
  private static Sprite _spriteBuffer;
  private static PImage _imageBuffer;
   
  private Assets() {}
  
  public static void LoadSprites() 
  {
    Sprites = new HashMap<Integer,Sprite>();
     
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.   Player Sprite    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////   
 
    _imageBuffer = Application.PROCESSING.loadImage(SpriteSheet_Player_Path);
    _spriteBuffer = new Sprite(SpriteSheet_Player_Columns, SpriteSheet_Player_Rows, _imageBuffer);
    
    _spriteBuffer.AddAnimation(Animations_Idle,      new int[] { 0 });
    _spriteBuffer.AddAnimation(Animations_WalkUp,    new int[] { 1, 4, 1, 5 });
    _spriteBuffer.AddAnimation(Animations_WalkDown,  new int[] { 0, 6, 0, 7 });
    _spriteBuffer.AddAnimation(Animations_WalkLeft,  new int[] { 2, 8, 2, 9 });
    _spriteBuffer.AddAnimation(Animations_WalkRight, new int[] { 3, 10, 3, 11 });
    
    Sprites.put(SpriteID_Player, _spriteBuffer);
    
  }
}

