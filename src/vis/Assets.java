
package vis;
import processing.core.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import def.Application;
import eng.PathFinder;

public class Assets 
{ 
  private static float Player_Walk_Speed = 700.0f;
  
  public static final int Fonts_Font0 = 0;
  
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
  
  public static final String AnimationName_Idle      = "idle";
  public static final String AnimationName_WalkUp    = "walkup";
  public static final String AnimationName_WalkDown  = "walkdown";
  public static final String AnimationName_WalkLeft  = "walkleft";
  public static final String AnimationName_WalkRight = "walkright";

  public static final String SpriteSheet_Player_Path    = "\\data\\graphics\\actors\\player\\player_spritesheet.png";
  public static final int SpriteSheet_Player_Columns = 4;
  public static final int SpriteSheet_Player_Rows    = 3;
  
  
  public static HashMap<String, PImage> Images;
  public static HashMap<Integer, PFont> Fonts;
  public static HashMap<Integer, SpriteSheet> SpriteSheets; 
  public static HashMap<Integer, AnimationLibrary> AnimationLibraries;
  
  private static InputStream _stream;
  private static ImageIcon _iconBuffer;
  private static PFont _fontBuffer;
  private static PImage _imageBuffer;
  private static SpriteSheet _spriteSheetBuffer;
  private static AnimationLibrary _animationLibraryBuffer;
  private static Animation[] _animationsBuffer;
  
  private Assets() {}
  
  public static void LoadSprites() 
  {
	Images = new HashMap<String,PImage>();
	Fonts = new HashMap<Integer, PFont>();
    SpriteSheets = new HashMap<Integer, SpriteSheet>();
    AnimationLibraries = new HashMap<Integer, AnimationLibrary>();
    
    _stream = Application.class.getResourceAsStream("/resources/background.png");
    
    try 
    {
    	_iconBuffer = new ImageIcon(ImageIO.read(_stream));
    	Images.put("mainbg", new PImage(_iconBuffer.getImage()));
	} 
    catch (IOException e) 
    {
		e.printStackTrace();
	}
    
    _fontBuffer = Application.PROCESSING.createFont(PathFinder.FILE_FONT0.toString(), 16);
    Fonts.put(Fonts_Font0, _fontBuffer);
    
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.   Player Sprite    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////   
 
    _imageBuffer = Application.PROCESSING.loadImage(SpriteSheet_Player_Path);
    _spriteSheetBuffer = new SpriteSheet(_imageBuffer, SpriteSheet_Player_Columns, SpriteSheet_Player_Rows);
    
    _animationsBuffer = new Animation[]
    {
    		new Animation(AnimationName_Idle, new int[]{ 0 }, 5000),
    		new Animation(AnimationName_WalkUp, new int[] { 1, 4, 1, 5  }, Player_Walk_Speed),
    		new Animation(AnimationName_WalkDown, new int[] { 0, 6, 0, 7 }, Player_Walk_Speed),
    		new Animation(AnimationName_WalkLeft, new int[] { 2, 8, 2, 9 }, Player_Walk_Speed),
    		new Animation(AnimationName_WalkRight, new int[] { 3, 10, 3, 11 }, Player_Walk_Speed),
    };                 
   
    _animationLibraryBuffer = new AnimationLibrary(_animationsBuffer);
    
    SpriteSheets.put(SpriteID_Player, _spriteSheetBuffer);
    AnimationLibraries.put(SpriteID_Player, _animationLibraryBuffer);
    
    // gc buffers
    
    _fontBuffer             = null;
    _imageBuffer            = null;
    _spriteSheetBuffer      = null;
    _animationLibraryBuffer = null;
   	_animationsBuffer       = null;
  }
}

