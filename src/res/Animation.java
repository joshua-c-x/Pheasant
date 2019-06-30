package res;

import def.Application;
import processing.core.PImage;

class Animation 
{
  private PImage[] _images;
  
  int[] Frames;
  int Index, AnimationLength;
  float Timer, FrameDelay; 
  
  Animation(PImage[] images,int[] frames) 
  {
    _images = images;
    
    Frames = frames;
    Index = 0;
    
    AnimationLength = Frames.length;
    
    // by default, each frame lasts a quarter of a second / 250 millis
    Timer = Application.PROCESSING.millis();
    FrameDelay = 180.0f;
  }
  
  void SetDuration(float duration) 
  {
    Timer = Application.PROCESSING.millis();
    FrameDelay = AnimationLength / duration;
  }
  
  void Update() 
  {
    if(Application.PROCESSING.millis() - Timer > FrameDelay) 
    {
      Timer = Application.PROCESSING.millis();
      Index += 1;
      Index = Index < AnimationLength ? Index : 0; 
    }
  }
  
  void Play (float X, float Y) 
  {
	  Application.PROCESSING.image(_images[Frames[Index]], X, Y);
  }
  
  void PlayFrameZero(float X, float Y) 
  {
	  Application.PROCESSING.image(_images[Frames[0]], X, Y);
  }
  
  void SetIndex (int index) 
  {
    Index = index;
  } 
}
