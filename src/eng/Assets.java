package eng;
import processing.core.*;
import java.util.HashMap;
import def.Application;

public class Assets 
{
  ///
  /// This class uses a singleton pattern, see "Main.pde" for its initialization
  ///
  
  public static final String Assets_Images_Player      = "player";
  public static final String Assets_Images_Poo         = "poo";
  public static final String Assets_Images_Orders      = "orders";
  public static final String Assets_Images_Marker      = "marker";
  
  public static final String GRAPHICS_PATH_ACTORS      = "\\data\\graphics\\actors\\";
  public static final String GRAPHICS_PATH_GAMEOBJECTS = "\\data\\graphics\\objs\\";
  
  public static HashMap<String, PImage[]> Images; 
  
  private static PImage[] _playerImages;
  private static PImage[] _poopImages;
  private static PImage[] _orderImages;
  private static PImage[] _markerImages;
   
  private Assets() {}
  
  public static void LoadImages() 
  {
    Images = new HashMap<String,PImage[]>();
     
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.                    ./////////////////
    ////////.   Player Images    ./////////////////
    ////////.                    ./////////////////
    ////////.                    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////   
    _playerImages = new PImage[13];
   
    ////////////[ Forward ]////////////////////////
    _playerImages[0]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_forward.png");
    _playerImages[1]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_forward_walk_a.png");
    _playerImages[2]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_forward_walk_b.png");
    
    ////////////[ Backward ]///////////////////////
    _playerImages[3]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_backward.png");
    _playerImages[4]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_backward_walk_a.png");
    _playerImages[5]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_backward_walk_b.png");
    
    ////////////[ Right ]//////////////////////////
    _playerImages[6]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_right.png");
    _playerImages[7]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_right_walk_a.png");
    _playerImages[8]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_right_walk_b.png");
    
    ////////////[ Left ]///////////////////////////
    _playerImages[9]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_left.png");
    _playerImages[10] = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_left_walk_a.png");
    _playerImages[11] = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_left_walk_b.png");
    
    ////////////[ Poo ]////////////////////////////
    _playerImages[12] = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Assets_Images_Player + "\\player_poo.png");
    
    Images.put(Assets_Images_Player, _playerImages);
    
    
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.                    ./////////////////
    ////////.   Poop Images      ./////////////////
    ////////.                    ./////////////////
    ////////.                    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////    
    _poopImages = new PImage[3];
    
    ////////////[ Map Animation ]//////////////////
    _poopImages[0] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Poo + "\\poo_0.png");
    _poopImages[1] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Poo + "\\poo_1.png");
    _poopImages[2] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Poo + "\\poo_2.png");

    Images.put( Assets_Images_Poo, _poopImages );
    
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.                    ./////////////////
    ////////.   Player Images    ./////////////////
    ////////.                    ./////////////////
    ////////.                    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////    
    _orderImages = new PImage[6];
    
    ////////////[ Map Animation ]//////////////////
    _orderImages[0] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Orders + "\\orders-0.png");
    _orderImages[1] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Orders + "\\orders-1.png");
    _orderImages[2] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Orders + "\\orders-2.png");
    _orderImages[3] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Orders + "\\orders-3.png");
    _orderImages[4] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Orders + "\\orders-4.png");
    _orderImages[5] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Orders + "\\orders-5.png");
 
    Images.put( Assets_Images_Orders, _orderImages );
    
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.                    ./////////////////
    ////////.   Marker Images    ./////////////////
    ////////.                    ./////////////////
    ////////.                    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////    
    _markerImages = new PImage[6];
    
    ////////////[ Map Animation ]//////////////////
    _markerImages[0] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Marker +"\\marker (1).png");
    _markerImages[1] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Marker +"\\marker (2).png");
    _markerImages[2] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Marker +"\\marker (3).png");
    _markerImages[3] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Marker +"\\marker (4).png");
    _markerImages[4] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Marker +"\\marker (5).png");
    _markerImages[5] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Assets_Images_Marker + "\\marker (6).png");                 
    
    Images.put( Assets_Images_Marker, _markerImages );
  }
}