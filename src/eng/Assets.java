package eng;
import processing.core.*;
import java.util.HashMap;
import def.Application;

public class Assets 
{  
  public static final String Animations_Paths_Player = "player\\";
  public static final String Animations_Player_Up    = "player_up";
  public static final String Animations_Player_Down  = "player_down";
  public static final String Animations_Player_Left  = "player_left";
  public static final String Animations_Player_Right = "player_right";
  public static final String Animations_Player_Poo   = "player_poo";
  
  public static final String Animations_Paths_Poo     = "poo\\";
  public static final String Animations_Poo_Stink     = "poo_stink";
  
  public static final String Animations_Paths_Marker  = "marker\\";
  public static final String Animations_Marker_Float  = "marker_float";
  
  public static final String Animations_Paths_Orders  = "orders\\";
  public static final String Animations_Orders_Float  = "orders_float";
  
  public static final String GRAPHICS_PATH_ACTORS      = "\\data\\graphics\\actors\\";
  public static final String GRAPHICS_PATH_GAMEOBJECTS = "\\data\\graphics\\objs\\";
  
  public static HashMap<String, PImage[]> Images; 
  
  private static PImage[] _buffer;
   
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
  
   
    ////////////[ Forward ]////////////////////////
    _buffer = new PImage[3];
    _buffer[0]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Up + "\\player_forward.png");
    _buffer[1]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Up + "\\player_forward_walk_a.png");
    _buffer[2]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Up + "\\player_forward_walk_b.png");   
    Images.put(Animations_Player_Up, _buffer);                            
                                                                          
    ////////////[ Backward ]///////////////////////                       
    _buffer = new PImage[3];                                              
    _buffer[0]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Down + "\\player_backward.png");
    _buffer[1]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Down + "\\player_backward_walk_a.png");
    _buffer[2]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Down + "\\player_backward_walk_b.png");
    Images.put(Animations_Player_Down, _buffer);                         
                                                                         
    ////////////[ Right ]//////////////////////////                      
    _buffer = new PImage[3];                                             
    _buffer[0]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Right + "\\player_right.png");
    _buffer[1]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Right + "\\player_right_walk_a.png");
    _buffer[2]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Right + "\\player_right_walk_b.png");
    Images.put(Animations_Player_Right, _buffer);                        
                                                                         
    ////////////[ Left ]///////////////////////////                      
    _buffer = new PImage[3];                                             
    _buffer[0]  = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Left + "\\player_left.png");
    _buffer[1] = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS  + Animations_Paths_Player + Animations_Player_Left + "\\player_left_walk_a.png");
    _buffer[2] = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS  + Animations_Paths_Player + Animations_Player_Left + "\\player_left_walk_b.png");
    Images.put(Animations_Player_Left, _buffer);                          
                                                                          
    ////////////[ Poo ]////////////////////////////                       
    _buffer = new PImage[1];                                              
    _buffer[0] = Application.PROCESSING.loadImage(GRAPHICS_PATH_ACTORS + Animations_Paths_Player + Animations_Player_Poo + "\\player_poo.png");
                                                                         
    Images.put(Animations_Player_Poo, _buffer);
    
    
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.                    ./////////////////
    ////////.   Poop Images      ./////////////////
    ////////.                    ./////////////////
    ////////.                    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////    
    _buffer = new PImage[3];
    
    ////////////[ Map Animation ]//////////////////
    _buffer [0] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Poo + Animations_Poo_Stink + "\\poo_0.png");
    _buffer [1] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Poo + Animations_Poo_Stink + "\\poo_1.png");
    _buffer [2] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Poo + Animations_Poo_Stink + "\\poo_2.png");

    Images.put( Animations_Poo_Stink, _buffer );
    
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.                    ./////////////////
    ////////.   Player Images    ./////////////////
    ////////.                    ./////////////////
    ////////.                    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////    
    _buffer = new PImage[6];
    
    ////////////[ Map Animation ]//////////////////
    _buffer[0] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Orders + Animations_Orders_Float + "\\orders-0.png");
    _buffer[1] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Orders + Animations_Orders_Float + "\\orders-1.png");
    _buffer[2] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Orders + Animations_Orders_Float + "\\orders-2.png");
    _buffer[3] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Orders + Animations_Orders_Float + "\\orders-3.png");
    _buffer[4] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Orders + Animations_Orders_Float + "\\orders-4.png");
    _buffer[5] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Orders + Animations_Orders_Float + "\\orders-5.png");
 
    Images.put( Animations_Orders_Float, _buffer);
    
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ////////.--------------------./////////////////
    ////////.                    ./////////////////
    ////////.   Marker Images    ./////////////////
    ////////.                    ./////////////////
    ////////.                    ./////////////////
    ////////.--------------------./////////////////
    ///////////////////////////////////////////////    
    _buffer = new PImage[6];
    
    ////////////[ Map Animation ]//////////////////
    _buffer[0] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Marker + Animations_Marker_Float + "\\marker (1).png");
    _buffer[1] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Marker + Animations_Marker_Float + "\\marker (2).png");
    _buffer[2] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Marker + Animations_Marker_Float + "\\marker (3).png");
    _buffer[3] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Marker + Animations_Marker_Float + "\\marker (4).png");
    _buffer[4] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Marker + Animations_Marker_Float + "\\marker (5).png");
    _buffer[5] = Application.PROCESSING.loadImage(GRAPHICS_PATH_GAMEOBJECTS + Animations_Paths_Marker + Animations_Marker_Float + "\\marker (6).png");                 
    
    Images.put( Animations_Marker_Float, _buffer );
  }
}
