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
    Timer = millis();
    FrameDelay = 180.0f;
  }
  
  void SetDuration(float duration) 
  {
    Timer = millis();
    FrameDelay = AnimationLength / duration;
  }
  
  void Update() 
  {
    if(millis() - Timer > FrameDelay) 
    {
      Timer = millis();
      Index += 1;
      Index = Index < AnimationLength ? Index : 0; 
    }
  }
  
  void Play (float X, float Y) 
  {
    image(_images[Frames[Index]], X, Y);
  }
  
  void PlayFrameZero(float X, float Y) 
  {
    image(_images[Frames[0]], X, Y);
  }
  
  void SetIndex (int index) 
  {
    Index = index;
  } 
}
